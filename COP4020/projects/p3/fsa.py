from tkinter import Tk, Canvas, Frame, BOTH, LAST, W
from collections import deque
from random import randint
import re
import math
import sys

#Name: Cesar Santiago
#Course: COP4020
#Project 3
#Date: 03/22/2020
#Description: A program that gives a graphical interpretation of an FSA as described
#	In a file input by the user. It will also run an input string through the FSA
#	and output if that string is a legal part of the language or not.
class Scene(Frame):
	#Constructor of the frame and scene to which a GUI will be drawn.
	def __init__(self):
		super().__init__()
		self.initUI()
	
	#The bulk of the program. It initializes a GUI and draws the FSA onto the Frame.
	#	As well as running the string through the FSA to detect if it is in the language.
	def initUI(self):
		rad = 25 # Constant for the radius of the automata
		scale = 1 # Constant for the scale of the size of the automata
		global spacing = 75
		self.master.title("Final State Automata")
		self.pack(fill=BOTH, expand=1)
		#Reading in the files as described in the arguments passed in by the console
		if(len(sys.argv) == 1):
			f = open("fsa.txt", "r")
			testF = open("legal.txt", "r")
		else:
			f = open(sys.argv[1], "r")
			testF = open(sys.argv[2], "r")
		testString = testF.read()
		tokens = f.read()
		tokenList = re.split(';', tokens) #Split all of the tokens by the delimiter ;
		canvas = Canvas(self)
		#Creating empty arrays for the Relations and the Automata
		language = []
		automata = []
		states = []
		for tok in tokenList:
			print(tok.strip(' '))
		
		language.append(tokenList[0])
		numOfA = int(language[0])
		
		
		canvas.create_text(350, 10, anchor = W, font = "Comic", text = tokenList[1])
		
		#Creates the automata.
		for num in range(numOfA):
			x = spacing * scale
			y = spacing * scale
			scale += 1
			automata.append(Automata(radius = rad, position = Vector(x, y), canvasInput = canvas, token = str(num)))
			
		#Takes the end states input.
		endStates = re.split(',', tokenList[4])
		#Sets the needed automata end states as true
		for token in endStates:
			automata[int(token)].end = True

		#Draws the automata.
		for circles in automata:
			circles.draw()
			
		states = re.split(',', tokenList[2])
		relations = []
		stateCount = 0
		canvas.create_line(automata[int(tokenList[3])].x - rad * 2, automata[int(tokenList[3])].y + rad * 2,
		automata[int(tokenList[3])].x - rad * math.cos(math.pi/4), automata[int(tokenList[3])].y + rad * math.sin(math.pi/4), arrow = LAST, smooth = "true")
		for s in states:
			language = s.strip('(|)')
			aStates = re.split(':', language)
			tok = aStates[2]
			r = Relation(initialVector = automata[int(aStates[0])], finalVector = automata[int(aStates[1])],
			canvasInput = canvas, initalState = int(aStates[0]), finalState = int(aStates[1]), radius = automata[int(aStates[0])].rad, token = tok)
			r.draw()
			relations.append(r)
			stateCount += 1
		
		#Reading the viability of an input string on the FSA
		currentA = int(tokenList[3])
		print(testString)
		ended = False
		error = False
		for letter in testString:
			found = False
			for r in relations:
				if(letter == r.tok and currentA == r.i):
					currentA = r.f
					print("Token: " + letter + " goes from: " + str(r.i) + " to " + str(r.f))
					found = True
			if(not found): 
				print("Illegal string.") #If the string is illegal then output this
				error = True
				break
		for tok in endStates:
			if(currentA == int(tok) and (not error)):
				ended = True
				print("Legal string.") #If the string is legal then output this
		
		canvas.pack(fill=BOTH, expand=1)

def main():
	root = Tk()
	ex = Scene()
	root.geometry("400x400")
	root.mainloop()

#Vector: A vector class that simulates the math needed for this vector
class Vector:
	def __init__(self, _x, _y, _t_x = 0, _t_y = 0):
		self.x = _x
		self.y = _y
		self.t_x = _t_x
		self.t_y = _t_y
		
	def getMagnitude(self):
		mag = math.sqrt(math.pow(self.x - self.t_x, 2) + math.pow(self.y - self.t_y, 2))
		return mag
		
	def asUnitVector(self):
		x = self.x / self.getMagnitude()
		y = self.y / self.getMagnitude()
		return Vector(x, y)
	
	def subtract(self, vector):
		newVector = Vector(self.x - vector.y, self.y - vector.y)
		return newVector

#Automata: A state in which the 
class Automata:
	def __init__(self, radius, position, canvasInput, token = "1", isEnd = False):
		self.rad = radius
		self.pos = position
		self.x = position.x
		self.y = position.y
		self.tok = token
		self.end = isEnd
		self.canvas = canvasInput
		
	def draw(self):
		if(self.end):
			self.rad += 4
			self.canvas.create_oval(self.x - self.rad, self.y - self.rad, self.x + self.rad, self.y + self.rad, fill="White")
			self.rad -= 4
		self.canvas.create_oval(self.x - self.rad, self.y - self.rad, self.x + self.rad, self.y + self.rad, fill="White")
		self.canvas.create_text(self.x - 5, self.y, anchor = W, font = "Comic", text = self.tok)

#Relation: A relation between one Automata class to another.
class Relation:
	def __init__(self, initialVector, finalVector, initalState, finalState, canvasInput, radius, token):
		self.rad = radius
		self.i = initalState
		self.f = finalState
		self.vector = Vector(initialVector.x, initialVector.y, finalVector.x, finalVector.y)
		self.canvas = canvasInput
		self.tok = token
	
	def draw(self):
		if(self.i == self.f):
			#Create a line going from the side of the Automata to the top with an arc of diameter
			self.canvas.create_line(self.vector.x + self.rad, self.vector.y,
			self.vector.x + self.rad * 2, self.vector.y - self.rad * 2,
			self.vector.t_x, self.vector.t_y - self.rad, 
			arrow = LAST, smooth = "true")
			#Draw the token of the state change on a point of the previous line
			self.canvas.create_text(self.vector.x + self.rad + 10, self.vector.y - self.rad - 10, anchor = W, font = "Comic", text = self.tok)
			
		elif(self.vector.getMagnitude() > spacing):
			#Create a line from the left of an Automata to the other with an arc the lentgth of the radius times three
			self.canvas.create_line(self.vector.x - self.rad * math.cos(math.pi/4), self.vector.y + self.rad * math.sin(math.pi/4),
			(self.vector.x + self.vector.t_x) / 2 - self.rad * 3, (self.vector.y + self.vector.t_y) / 2 + self.rad * 3,
			self.vector.t_x - self.rad * math.cos(math.pi/4), self.vector.t_y + self.rad * math.sin(math.pi/4), arrow = LAST, smooth = "true")
			#Draw the token of the state change on the focus point of that line
			self.canvas.create_text((self.vector.x + self.vector.t_x) / 2 - self.rad * 3, (self.vector.y + self.vector.t_y) / 2 + self.rad * 3,
			anchor = W, font = "Comic", text = self.tok)
			
		else:
			#Create a straight diagonal line from one Automata to the other
			self.canvas.create_line(self.vector.x + self.rad * math.cos(math.pi/4), self.vector.y + self.rad * math.sin(math.pi/4),
			self.vector.t_x - self.rad * math.cos(math.pi/4), self.vector.t_y - self.rad * math.sin(math.pi/4), arrow = LAST, smooth = "true")
			#Draw the token of the state adgacent to the line
			self.canvas.create_text(self.vector.x + self.rad - 15,  self.vector.x + self.rad + 5, anchor = W, font = "Comic", text = self.tok)

if __name__ == '__main__':
	main()
	
	
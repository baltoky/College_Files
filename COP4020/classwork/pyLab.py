from collections import deque
from random import randint as rand
import tkinter as tk
import re

root = tk.Tk()
root.title = "Lab"
canvas = tk.Canvas(root, width = 200, height = 200)
canvas.pack()
rad = 25

f = open("tokens.txt", "r")
tokens = f.read()
tokenList = re.split(';|,|\s', tokens)
temp = deque()
for tok in tokenList:
    tok = tok.strip('(')
    tok = tok.strip(')')
    temp.append(tok)

for circle in range(temp.popleft()):
    x = rand.randint(0 + rad, 200 - rad)
    y = rand.randint(0 + rad, 200 - rad)
    canvas.create_oval(x - rad, y - rad, x + rad, y + rad, rad)

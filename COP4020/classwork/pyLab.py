import re
f = open("tokens.txt", "r")
tokens = f.read()
tokenList = re.split(';|,|\s', tokens)
print(tokenList)

CC = gcc

TAGS = -Wall -g

TARG = Project1.exe

SRC = mainfile.c\
	  table.c\
	  lexicon.c

OBJ = $(SRC:.c=.o)

.PHONY: clean

all: $(TARG)
	@echo Compiling Success!

$(TARG): $(OBJ)
	$(CC) $(TAGS) -o $(TARG) $(OBJ)
	@echo Linking Success!

.c.o:
	$(CC) $(TAGS) -c $< -o $@
	@echo Compiling $<

clean:
	rm *.o


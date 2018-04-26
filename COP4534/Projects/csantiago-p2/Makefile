CC = gcc

TAGS = -Wall -g

TARG = Project

SRC = driver.c\
      customer.c\
      priorityQueue.c\
      functions.c\
      simulation.c

LIB = -lm

OBJ = $(SRC:.c=.o)

.PHONY: clean

all: $(TARG)
	@echo Compiling Success!

$(TARG): $(OBJ)
	$(CC) $(TAGS) -o $(TARG) $(OBJ) $(LIB)
	@echo Linking Success!

.c.o:
	$(CC) $(TAGS) -c $< -o $@ $(LIB)
	@echo Compiling $<

clean:
	rm *.o


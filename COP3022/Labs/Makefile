CC = javac

DIR = JDBCLab11/

FLAGS = -g

SRC = $(DIR)*.java

.PHONY: clean

all:
	$(CC) $(FLAGS) -classpath :./$(DIR)db/* $(SRC)

clean:
	rm $(DIR)*.class

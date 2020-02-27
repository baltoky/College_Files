#ifndef CORE_H
#define CORE_H

#include <stdio.h>
#include <stdlib.h>

#define ID 300
#define NUM 301
#define INT 302
#define BEGIN 400
#define END 401
#define ERR 404
#define DONE 500

#define ERROR_STACK_SIZE 200

FILE* file;
int lookahead;
int linenum;
int ch;
char* errStack;
int declToggle;

/*
 * Type variable that defines the information needed to store
 *      each variable token in the table.
 * */
typedef struct variable* variableP;
typedef struct variable
{
    char* value;
    int tokenType;
}variable;


/*
 * Reads from the file alloted in the file pointer.
 * @returns character from file in the form of an integer.
 * */
int getchar();

/*
 * Attempts to match the lookahead with the input parameter.
 *      If a match is true then it will prompt to read the
 *      next token in the file and set it as lookahead.
 *      Otherwise it returns false.
 * @returns true if a match happens, false otherwise.
 * */
int match(int t);

/*
 * Seeks the file pointer backwards the amount of chars
 *      dictated in the parameter.
 * @param int i is the amount of chars it will seek back.
 * @returns true if it could seek back, false otherwise.
 * */
int readBack(int i);

/*
 * Adds the parameter error to the error stack in the program.
 * @param err a contant char that will be added to the error stack.
 * */
void addToErrorStack(const char* err);

/*
 * Reads through the file for the next token and returns it.
 * @returns the next token in the form of it's identifier code
 *      from the defines above. Or it may return a single char
 *      in the form of an int.
 * */
int lexan();

/*
 * First function on the recursive context free matcher.
 *      Will try to match begin, if that happens then it
 *      passes on the matching to stmtList(). After which
 *      will match end or return an err. Or it will return
 *      an error if it could not match begin.
 * @returns an identifier code in the form of END or ERR
 *      depends on how it matches the file.
 * */
int scope();

/*
 * A recursive function that calls stmt and then itself.
 * @returns an identifier depending on how stmt behaves.
 * */
int stmtList();

/*
 * A recursive function that checks for an ID identifier
 *      from lexan.
 * @returns an identifier depending on matching an ID.
 * */
int stmt();

/*
 * Recursive function that checks for parenthesis interaction
 *      via matching and then goes to a term.
 * @returns an identifier depending on how term behaves.
 * */
int expr();

/*
 * Recursive function that checks for an ID or a NUM for
 *      this CFG. Goes into operator if necessary.
 * @returns an identifier.
 * */
int term();

/*
 * Matches an operator char in the form of
 *      + - / * returns depending on that match.
 * @returns an identifier depending on whether it matches
 *      an operator.
 * */
int opr();

#endif

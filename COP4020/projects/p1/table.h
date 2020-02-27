#ifndef TABLE_H
#define TABLE_H

#include "core.h"

/*
 * Type table that defines the information that will
 *      be held in the table variable.
 * */
typedef struct table* tableP;
typedef struct table
{
    variableP vars;
    int curr;
    int size;
    int head;
}table;

/*
 * Global table of variables.
 * */
tableP tokenTable;
tableP postfixArray;

/*
 * Initializes the table with a size given as input.
 * @param initialSize is the size used to initialize the variable.
 * */
void initTable(tableP* array, int initialSize);

/*
 * Adds the token to the token table in the form of a variable.
 * @param token is the char that describes the lexeme name of the variable.
 * @param tokIdent is the identity of the token as described by the defines
 *      above.
 * */
void addToTable(tableP array, variable v);

/*
 * Garbage collection mechanism that frees the memory that was used by
 *      the table*;
 * */
void freeArray(tableP* array);

/*
 * Simple loop to print the array into the console.
 * */
void printTable(tableP array);

/*
 * Simple seach function that looks for an id that has the same string
 *      as the input token.
 * @param token a string to look for in the table.
 * @returns the index in the array where the strings matched.
 * */
int findTokenInTable(tableP array, char* token);

int enqueue(tableP array, variable v);

int dequeue(tableP array, variable v);

#endif

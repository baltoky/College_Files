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
}table;

/*
 * Global table of variables.
 * */
tableP tokenTable;
tableP postfixStack;

/*
 * Initializes the table with a size given as input.
 * @param array the table to initialize.
 * @param initialSize is the size used to initialize the variable.
 * */
void initTable(tableP* array, int initialSize);

/*
 * Adds the token to the token table in the form of a variable.
 *      above.
 * @param array is the table to add to.
 * @param v a variable object to add to the table.
 * */
void addToTable(tableP array, variable v);

/*
 * Garbage collection mechanism that frees the memory that was used by
 *      the table*;
 * @param array the table to free from memory.
 * */
void freeArray(tableP* array);

/*
 * Simple loop to print the array into the console.
 * @param array the table to print.
 * */
void printTable(tableP array);

/*
 * Simple seach function that looks for an id that has the same string
 *      as the input token.
 * @param array the table in which to find a certain token.
 * @param token a string to look for in the table.
 * @returns the index in the array where the strings matched.
 * */
int findTokenInTable(tableP array, char* token);

/*
 * Reads and returns the value stored at that index location on the array.
 * @param array is the table from which to read.
 * @param index is the index at which it should read from the table.
 * @returns the values stored in the variable at the index given of the
 *      array given.
 * */
variable readTableAt(tableP array, int index);

#endif

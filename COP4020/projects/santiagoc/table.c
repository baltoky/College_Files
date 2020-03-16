#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "table.h"

/*
 * Initializes the table with a size given as input.
 * @param initialSize is the size used to initialize the variable.
 * */
void initTable(tableP* array, int initialSize)
{
    (*array) = (table*)malloc(sizeof(table));
    (*array)->vars = (variable*)malloc(initialSize * sizeof(variable));
    (*array)->curr = 0;
    (*array)->size = initialSize;
}

/*
 * Adds the token to the token table in the form of a variable.
 * @param token is the char that describes the lexeme name of the variable.
 * @param tokIdent is the identity of the token as described by the defines
 *      above.
 * */
void addToTable(tableP array, variable v)
{
    if(array->curr == array->size)
    {
        (*array).size *= 2;
        (*array).vars = (variable*)realloc(array->vars, sizeof(variable) * array->size);
    }
    array->vars[array->curr].value = (char*)malloc(1);
    strcpy(array->vars[array->curr].value, v.value);
    array->vars[array->curr++].tokenType = v.tokenType;
}

/*
 * Garbage collection mechanism that frees the memory that was used by
 *      the table*;
 * */
void freeArray(tableP* array)
{
    int i;
    for(i = 0; i <= (*array)->curr; i++)
    {
        free((*array)->vars[i].value);
    }
    free((*array)->vars);
    free((*array));
}

/*
 * Simple seach function that looks for an id that has the same string
 *      as the input token.
 * @param token a string to look for in the table.
 * @returns the index in the array where the strings matched.
 * */
int findTokenInTable(tableP array, char* token)
{
    int i;
    for(i = 0; i < array->curr; i++)
    {
        if(strcmp(token, array->vars[i].value) == 0)
        {
            return i;
        }
    }
    return -1;
}

/*
 * Simple loop to print the array into the console.
 * */
void printTable(tableP array)
{
    int i;
    printf("\nContents of the token table:");
    for(i = 0; i < array->curr; i++)
    {
        printf("\nValue: %s, Type: %d", 
                array->vars[i].value,
                array->vars[i].tokenType);
    }
}

/*
 * Reads and returns the value stored at that index location on the array.
 * @param array is the table from which to read.
 * @param index is the index at which it should read from the table.
 * @returns the values stored in the variable at the index given of the
 *      array given.
 * */
variable readTableAt(tableP array, int index)
{
    variable v = {NULL, 0};
    v = array->vars[index];
    return v;
}



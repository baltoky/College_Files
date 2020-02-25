#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "table.h"

/*
 * Initializes the table with a size given as input.
 * @param initialSize is the size used to initialize the variable.
 * */
void initTable(int initialSize)
{
    tokenTable = (table*)malloc(sizeof(tokenTable));
    tokenTable->vars = (variable*)malloc(initialSize * sizeof(variable));
    tokenTable->curr = 0;
    tokenTable->size = initialSize;
}

/*
 * Adds the token to the token table in the form of a variable.
 * @param token is the char that describes the lexeme name of the variable.
 * @param tokIdent is the identity of the token as described by the defines
 *      above.
 * */
void addToTable(variable v)
{
    if(tokenTable->curr == tokenTable->size)
    {
        (*tokenTable).size *= 2;
        (*tokenTable).vars = (variable*)realloc(tokenTable->vars, sizeof(variable) * tokenTable->size);
    }
    tokenTable->vars[tokenTable->curr].value = (char*)malloc(1);
    strcpy(tokenTable->vars[tokenTable->curr].value, v.value);
    tokenTable->vars[tokenTable->curr++].tokenType = v.tokenType;
}

/*
 * Garbage collection mechanism that frees the memory that was used by
 *      the table*;
 * */
void freeArray()
{
    int i;
    for(i = 0; i <= tokenTable->curr; i++)
    {
        free(tokenTable->vars[i].value);
    }
    free(tokenTable->vars);
    free(tokenTable);
}

/*
 * Simple seach function that looks for an id that has the same string
 *      as the input token.
 * @param token a string to look for in the table.
 * @returns the index in the array where the strings matched.
 * */
int findTokenInTable(char* token)
{
    int i;
    for(i = 0; i < tokenTable->curr; i++)
    {
        if(strcmp(token, tokenTable->vars[i].value) == 0)
        {
            return i;
        }
    }
    return -1;
}

/*
 * Simple loop to print the array into the console.
 * */
void printTable()
{
    int i;
    printf("\nContents of the token table:");
    for(i = 0; i < tokenTable->curr; i++)
    {
        printf("\nValue: %s, Type: %d", 
                tokenTable->vars[i].value,
                tokenTable->vars[i].tokenType);
    }
}

/*
 * Creates an access to the table in a stack data structure manner.
 *      Will push the variable to the array as a stack.
 * @param v is a variable type to add to the stack.
 * */
int pushToStack(variable v)
{

    return 0;
}

/*
 * Removes items from the stack structure.
 * @returns variable as the item that was removed.
 * */
variable* pullFromStack()
{

    return NULL;
}


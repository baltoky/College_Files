#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "table.h"

void initTable(int initialSize)
{
    tokenTable = (table*)malloc(sizeof(tokenTable));
    tokenTable->vars = (variable*)malloc(initialSize * sizeof(variable));
    tokenTable->curr = 0;
    tokenTable->size = initialSize;
}

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



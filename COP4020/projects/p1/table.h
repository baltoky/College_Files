#ifndef TABLE_H
#define TABLE_H

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

void initTable(int initialSize);

/*
 * Adds the token to the token table in the form of a variable.
 * @param token is the char that describes the lexeme name of the variable.
 * @param tokIdent is the identity of the token as described by the defines
 *      above.
 * */
void addToTable(variable v);

/*
 * Garbage collection mechanism that frees the memory that was used by
 *      the table*;
 * */
void freeArray();

/*
 * Simple seach function that looks for an id that has the same string
 *      as the input token.
 * @param token a string to look for in the table.
 * @returns the index in the array where the strings matched.
 * */
int findTokenInTable(char* token);

#endif

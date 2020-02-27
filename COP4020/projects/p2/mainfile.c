#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <regex.h>

#include "core.h"
#include "table.h"


int main(int argc, char** argv)
{
    char* filepath = NULL; // Setting the string filepath to null.
    errStack = (char*)malloc(ERROR_STACK_SIZE); // Setting the error stack to an empty string.
    initTable(&tokenTable, 2);
    initTable(&postfixStack, 2);
    linenum = 1;
    declToggle = 0;

    // Checks if the program recieves external arguments and takes
    //      The first argument as the filepath.
    if(argc > 1){
        filepath = argv[1];
        file = fopen(filepath, "r");
    }
    else{
        file = NULL;
    }

    // Starts the recursive loop with the scope function.
    // If the program reaches the end the it is legal
    //      Otherwise it is illegal.
    int id = scope();
    if(id == END)
    {
        printf("\nProgram is legal.\n");
    }
    else if(id == ERR)
    {
        printf("\nProgram is illegal.\n");
        printf("Error line %d: %s\n", linenum, errStack);
    }

    printTable(tokenTable);
    printf("\n");
    printf("\nPostfix Table:\n");
    printTable(postfixStack);
    printf("\n");

    // Closes the file stream.
    fclose(file);
    free(errStack);
    freeArray(&tokenTable);
    freeArray(&postfixStack);

    return 0;
}

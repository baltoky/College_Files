#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <regex.h>

#include "core.h"
#include "table.h"

int main(int argc, char** argv)
{
    char* filepath = NULL; // Setting the string filepath to null.
    errStack = malloc(200); // Setting the error stack to an empty string.
    initTable(2);

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
        printf("\nProgram is legal.\n\n");
    }
    else if(id == ERR)
    {
        printf("\nProgram is illegal.\n");
        printf("Line %d contains the following errors: \n%s\n\n", linenum, errStack);
    }

    //printTable();

    // Closes the file stream.
    fclose(file);
    free(errStack);
    freeArray();

    return 0;
}

int getchar()
{
    // Reads a byte from the code and returns it.
    int ch = fgetc(file);
    return ch;
}

int match(int t)
{
    // checks if the lookahead matches t.
    if(lookahead == t)
    {
        // If it matched then read the next lookahead.
        // and return true.
        lookahead = lexan();
        return 1;
    }
    // Otherwise return false.
    return 0;
}

void addToErrorStack(const char* err)
{
    strcat(errStack, err);
    strcat(errStack, "\n");
}
int scope()
{
    //Creates the first lookahead.
    //Then it attempts to match for begin, if it can then the program
    //      may continue. Otherwise it sends back an error.
    //      After that it asks stmtList for an end token.
    lookahead = lexan();
    if(match(BEGIN))
    {
        if(stmtList() == END) return END;
        else 
        {
            addToErrorStack("Program does not end the scope with an end statement.");
            return ERR;
        }
    }
    addToErrorStack("Program does not start the scope with a begin statement.");
    return ERR;
}

int stmtList()
{
    // Attempts to match the end identifier
    //      After which it will return end
    //      otherwise goes into stmt until an error
    //      happens.
    //      otherwise it goes into a stmtList recursive call.
    if(match(END))
        return END;
    if(stmt() == ERR)
        return ERR;
    return stmtList();
}

int stmt()
{
    // Attempts to find an ID
    //      if successful it will try to match
    //      an equal sign and go into expression.
    //      After which it will try to match a semicolon.
    //      Else it will check for an error and return.
    if(match(ID))
    {
        if(match('='))
            if(expr() == ERR)
                return ERR;
        if(!match(';'))
        {
            addToErrorStack("The statement does not end with a semicolon.");
            return ERR;
        }
    }
    else if(match(ERR))
        return ERR;
    return 0;
}

int expr()
{
    // Attempts to find a '('
    //      if successful then it rolls into expr()
    //      and tries to match ')' if it does not
    //      succeed then it returns an error otherwise
    //      it goes into term.
    if(match('('))
    {
        expr();
        if(!match(')'))
        {
            addToErrorStack("Expression does not reach an end parenthesis.");
            return ERR;
        }
    }
    return term();
}

int term()
{
    // Attempts to find an ID or NUM
    //      If it happens then lookahead will roll
    //      otherwise it goes onto finding an operator.
    if(match(NUM) || match(ID)) 
    {
        return opr();
    }
    return opr();
}

int opr()
{
    // Attempts to match an operator and if it does then it
    //      goes to another expression.
    if(match('+') || match('-') || match('/') || match('*')) 
    {
        return expr();
    }
    return 0;
}

void printTok(char* token, int size)
{
    printf("%s", token);
}

void refreshTok(char* token, int size)
{
    bzero(token, size);
}

int printAndRefresh(char* token, int size)
{
    printTok(token, size);
    refreshTok(token, size);
    return 0;
}

int readBack(int i)
{
    return fseek(file, -i, SEEK_CUR);
}

int checkValidNUM(char* token, int size)
{
    int i;
    int dec = 0;
    if(token[size] == '.')
    {
        addToErrorStack("A number cannot end in a '.' token.");
        return ERR;
    }
    for(i = 0; i < size; i++)
    {
        if(dec == 1 && token[i] == '.')
        {
            addToErrorStack("A number cannot have multiple '.' tokens in a row.");
            return ERR;
        }
        if(token[i] == '.')
            dec = 1;
    }
    return 1;
}

int checkValidID(char* token, int size)
{
    int i;
    if(token[size - 1] == '_')
    {
        addToErrorStack("An ID cannot end on a '_'.");
        return ERR;
    }
    for(i = 1; i < size; i++)
    {
        if(token[i] == '_' && token[i + 1] == '_')
        {
            addToErrorStack("An ID cannot have multiple '_' in a row.");
            return ERR;
        }
    }
    return 1;
}

int lexan()
{
    const int tokenSize = 32;
    char* token = malloc(tokenSize);
    while((ch = getchar()) != EOF)
    {
        refreshTok(token, tokenSize);
        if(ch == ' ' || ch == '\t') printf("%c", ch);
        else if(ch == '~')
        {
            printf("%c", ch);
	        while((ch = getchar()) != '\n') printf("%c", ch);
            printf("\n");
            linenum++;
        }
        else if(ch == '\n')
        {
            printf("\n");
            linenum++;
        }
        else if(ch >= '0' && ch <= '9')
        {
            char tmp;
            while(ch >= '0' && ch <= '9')
            {
                tmp = ch;
                strncat(token, &tmp, 1);
                ch = getchar();
            }
            readBack(1);

            if(checkValidNUM(token, strlen(token)) == ERR)
            {
                addToErrorStack("Invalid number.");
                return ERR;
            }

            printAndRefresh(token, tokenSize);
            return NUM;
        }
        else if((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z'))
        {
            char tmp;
            while((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z') || ch == '_' || (ch >= '0' && ch <= '9'))
            {
                tmp = ch;
                strncat(token, &tmp, 1);
                ch = getchar();
            }
            
            readBack(1);
            printTok(token, tokenSize);
            
            if(strncmp(token, "begin", 5) == 0) 
            {
                addToTable((variable){token, BEGIN});
                refreshTok(token, tokenSize);
                free(token);
                return BEGIN;
            }
            else if (strncmp(token, "end", 3) == 0)
            {
                addToTable((variable){token, END});
                refreshTok(token, tokenSize);
                free(token);
                return END;  
            } 
            else
            {
                if(checkValidID(token, strlen(token)) == ERR)
                {
                    addToErrorStack("Invalid ID composition.");
                    return ERR;
                }
                if(!(findTokenInTable(token) >= 0))
                    addToTable((variable){token, ID});
                refreshTok(token, tokenSize);
                free(token);
                return ID;
            }
        }
        else
        {
            printf("%c", ch);
            return ch;
        }
    }
    return DONE;
}



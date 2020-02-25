#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "core.h"
#include "table.h"

/*
 * Reads from the file alloted in the file pointer.
 * @returns character from file in the form of an integer.
 * */
int getchar()
{
    // Reads a byte from the code and returns it.
    int ch = fgetc(file);
    return ch;
}

/*
 * Attempts to match the lookahead with the input parameter.
 *      If a match is true then it will prompt to read the
 *      next token in the file and set it as lookahead.
 *      Otherwise it returns false.
 * @returns true if a match happens, false otherwise.
 * */
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

/*
 * Adds the parameter error to the error stack in the program.
 * @param err a contant char that will be added to the error stack.
 * */
void addToErrorStack(const char* err)
{
    strcat(errStack, err);
    strcat(errStack, "\n");
}

/*
 * First function on the recursive context free matcher.
 *      Will try to match begin, if that happens then it
 *      passes on the matching to stmtList(). After which
 *      will match end or return an err. Or it will return
 *      an error if it could not match begin.
 * @returns an identifier code in the form of END or ERR
 *      depends on how it matches the file.
 * */
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

/*
 * A recursive function that calls stmt and then itself.
 * @returns an identifier depending on how stmt behaves.
 * */
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

/*
 * A recursive function that checks for an ID identifier
 *      from lexan.
 * @returns an identifier depending on matching an ID.
 * */
int stmt()
{
    // Attempts to find an ID
    //      if successful it will try to match
    //      an equal sign and go into expression.
    //      After which it will try to match a semicolon.
    //      Else it will check for an error and return.
    if(match(INT))
    {
        if(match(ID))
        {
            if(match(';'))
            {

            }
        }
    }
    else if(match(ID))
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

/*
 * Recursive function that checks for parenthesis interaction
 *      via matching and then goes to a term.
 * @returns an identifier depending on how term behaves.
 * */
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

/*
 * Recursive function that checks for an ID or a NUM for
 *      this CFG. Goes into operator if necessary.
 * @returns an identifier.
 * */
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

/*
 * Matches an operator char in the form of
 *      + - / * returns depending on that match.
 * @returns an identifier depending on whether it matches
 *      an operator.
 * */
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

/*
 * Reads through the file for the next token and returns it.
 * @returns the next token in the form of it's identifier code
 *      from the defines above. Or it may return a single char
 *      in the form of an int.
 * */
int lexan()
{
    const int tokenSize = 32;
    char* token = malloc(tokenSize);
    while((ch = getchar()) != EOF)
    {
        refreshTok(token, tokenSize);
        if(ch == ' ' || ch == '\t') ;
        else if(ch == '~')
        {
	        while((ch = getchar()) != '\n');
            linenum++;
        }
        else if(ch == '\n')
        {
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

            refreshTok(token, tokenSize);
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
            else if(strncmp(token, "int", 3) == 0)
            {
                refreshTok(token, tokenSize);
                return INT;
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
            return ch;
        }
    }
    return DONE;
}



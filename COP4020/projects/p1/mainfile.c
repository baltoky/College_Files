#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <regex.h>

#define ID 300
#define NUM 301
#define BEGIN 400
#define END 401
#define ERR 404
#define DONE 500


typedef struct variable{
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

FILE* file;
int lookahead;
int linenum = 1;
int ch;
char* errStack;

int main(int argc, char** argv)
{
    char* filepath = NULL; // Setting the string filepath to null.
    errStack = ""; // Setting the error stack to an empty string.

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
        printf("\nProgram is legal.");
    }
    else if(id == ERR)
    {
        printf("\nProgram is illegal.\n");
        printf("Error Stack identified the following: \n%s", errStack);
    }

    // Closes the file stream.
    fclose(file);

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
        return ERR;
    for(i = 1; i < size; i++)
    {
        if(token[i] == '_' && token[i + 1] == '_')
            return ERR;
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
        if(ch == ' ' || ch == '\t');
        else if(ch == '~')
        {
            printf("%c", ch);
	        while((ch = getchar()) != '\n') printf("%c", ch);
            linenum++;
        }
        else if(ch == '\n')
        {
            printf("%c", ch);
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
                return ERR;

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
                refreshTok(token, tokenSize);
                free(token);
                return BEGIN;
            }
            else if (strncmp(token, "end", 3) == 0)
            {
                refreshTok(token, tokenSize);
                free(token);
                return END;  
            } 
            else
            {
                if(checkValidID(token, strlen(token)) == ERR)
                {
                    return ERR;
                }
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

int scope()
{
    lookahead = lexan();
    if(match(BEGIN))
    {
        if(stmtList() == END) return END;
        else return ERR;
    }
    return ERR;
}

int stmtList()
{
    if(match(END))
        return END;
    if(stmt() == ERR)
        return ERR;
    return stmtList();
}

int stmt()
{
    if(match(ID))
    {
        if(match('='))
            if(expr() == ERR)
                return ERR;
        if(!match(';'))
            return ERR;
    }
    else if(match(ERR))
        return ERR;
    return 0;
}

int expr()
{
    if(match('('))
    {
        expr();
        if(!match(')'))
            return ERR;
    }
    return term();
}

int term()
{
    if(match(NUM) || match(ID)) 
    {
        return opr();
    }
    return opr();
}

int opr()
{
    if(match('+') || match('-') || match('/') || match('*'))
    {
        return expr();
    }
    return 0;
}


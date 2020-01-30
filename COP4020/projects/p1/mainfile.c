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

int getchar();
int match(int t);
int lexan();

int scope();
int stmtList();
int stmt();
int expr();
int term();
int opr();

FILE* file;
int lookahead;
int linenum = 1;
int feedback = 0;
int ch;

int main(int argc, char** argv)
{
    char* filepath = NULL;

    if(argc > 1){
        filepath = argv[1];
        file = fopen(filepath, "r");
    }
    else{
        file = NULL;
    }

    printf("%s\n", filepath);

    if(scope() == END)
    {
        printf("\nProgram is legal.");
    }
    else
    {
        printf("\nProgram is illegal.");
    }

    fclose(file);

    return 0;
}

int getchar()
{
    int ch = fgetc(file);
    return ch;
}

int match(int t)
{
    if(lookahead == t)
    {
        lookahead = lexan();
        return 1;
    }
    return 0;
}

void printTok(char* token, int size)
{
    printf(" <token> (%s) ", token);
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
        return ERR;
    for(i = 0; i < size; i++)
    {
        if(dec == 1 && token[i] == '.')
            return ERR;
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
            printf("comment\n");
            linenum++;
        }
        else if(ch == '\n')
        {
            printf(" --Line number: %d\n", linenum);
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
            
            printTok(token, tokenSize);
            readBack(1);
            
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
        else if(ch == ';')
        {
            printf(" <operator> (%c)", ch);
            return ch;
        }
        else
        {
            printf(" <operator> (%c)", ch);
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
        term();
        if(!match(')'))
            return ERR;
    }
    return term();
}

int term()
{
    if(match(NUM)) 
    {
        return opr();
    }
    else if(match(ID))
    {
        return opr();
    }
    else 
        return opr();
    return ERR;
}

int opr()
{
    if(match('+') || match('-') || match('/') || match('*'))
    {
        return expr();
    }
    else return 0;
}


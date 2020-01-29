#include <stdio.h>
#include <stdlib.h>
#include <string.h>

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

FILE* file;
int lookahead;
int linenum = 1;
int feedback = 0;

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
    
    //lexan();

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

int printAndRefresh(char* token, int size){
    printTok(token, size);
    refreshTok(token, size);
    return 0;
}

int lexan()
{
    int ch;
    const int tokenSize = 32;
    char* token = malloc(tokenSize);
    while((ch = getchar()) != EOF)
    {
        refreshTok(token, tokenSize);
        if(ch == ' ' || ch == '\t');
        else if(ch == '~')
        {
            while(ch != '\n' || ch != EOF) ch = getchar();
            linenum++;
        }
        else if(ch == '\n')
        {
            printf(" --Line number: %d", linenum);
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

            printAndRefresh(token, tokenSize);

            return NUM;
        }
        else if((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z'))
        {
            char tmp;
            while((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z') || ch == '_')
            {
                tmp = ch;
                strncat(token, &tmp, 1);
                ch = getchar();
            }
            printTok(token, tokenSize);
            
            if(strncmp(token, "begin", 5) == 0) 
            {
                printf(" \n <Began Scope> \n");
                refreshTok(token, tokenSize);
                free(token);
                return BEGIN;
            }
            else if (strncmp(token, "end", 3) == 0)
            {
                printf(" \n <Ended Scope> \n ");
                refreshTok(token, tokenSize);
                free(token);
                return END;  
            } 
            else
            {
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
    printf(" -- In scope");
    lookahead = lexan();
    printf("%d", lookahead);
    if(match(BEGIN))
    {
        if(stmtList() == END) return END;
        else return ERR;
    }
    else return ERR;
    return 0;
}

int stmtList()
{
    printf(" -- In stmtList");
    if(match(END)) 
    {
        return END;
    }
    else{
        stmt();
        stmt();
        //return stmtList();
    }
    return ERR;
}

int stmt()
{
    printf(" -- In stmt");
    if(match(ID))
    {
        if(match('='))
        {
            expr();
        }
        else;
        
        if(match(';'))
        {
            return 0;
        }
        else
        {
            return ERR;
        }
    }
    return 0;
}

int expr()
{
    printf(" -- In expr");
    if(match('('))
    {
        term();
        if(match(')'))
            return 0;
        else return ERR;
    }
    else term();
    return 0;
}

int term()
{
    printf(" -- In temp");
    if(match(NUM)) 
    {
        if(match('+') || match('-'))
        {
            return expr();
        }
        else return 0;
    }
    else if(match(ID))
    {
        if(match('+') || match('-'))
        {
            return expr();
        }
        else return 0;
    }
    return ERR;
}



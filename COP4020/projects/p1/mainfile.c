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
int program();
int statements();
int statement();

FILE* file;
int lookahead;
int linenum = 1;

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
    
    lexan();

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
        lookahead = lexan();
    return 0;
}

int printAndRefresh(char* token, int size){
    printf(" <token> (%s) ", token);
    bzero(token, size);
    return 0;
}

int lexan()
{
    int ch;
    const int tokenSize = 32;
    char* token = malloc(tokenSize);
    printf("begining read\n");
    while((ch = getchar()) != EOF)
    {
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
            //return NUM;
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
            /*
            if(strcmp(token, "begin")) 
            {
                return BEGIN;
            }
            else if (strcmp(token, "end"))
            {
                return END;  
            } 
            else
            {
                return ID;
            }
            */
            printAndRefresh(token, tokenSize);
        }
        //else
            // return ch;
        printf("%c", ch);
    }
    free(token);
    return DONE;
}

int program()
{
    match(BEGIN);
    return statements();
}

int statements()
{
    statement();
    if(match(END)) return END;
    return statements();
}

int statement()
{
    match(ID);
    match('=');
    expression();
    return 0;
}

int expression()
{
    if(match(NUM)) return 0;
    else if(match(ID))
    {
        term();
        match(';');
    }
    return 0;
}

int term()
{
    if(match('+') || match('-')) return 0;
    return ERR;
}



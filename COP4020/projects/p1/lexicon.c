#include <stdlib.h>
#include <string.h>
#include "core.h"

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



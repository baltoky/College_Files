#include <stdio.h>
#include <string.h>

#define ID 300
#define NUM 301
#define BEGIN 400
#define END 401
#define ERR 404
#define DONE 500

int getchar();
int match(int t);
int lexan();

typedef struct variable{
    char* value;
    int tokenType;
}variable;

FILE* file;
int lookahead;
int linenum = 0;

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
    return ERR;
}

int lexan()
{
    int ch;
    char* token = "";
    while((ch = getchar()) != EOF)
    {
        if(ch == ' ' || ch == '\t');
        else if(ch == '\n')
            linenum++;
        else if(ch >= '0' && ch <= '9')
        {
            //Get the number into the numLexeme
            return NUM;
        }
        else if(ch >= 'a' && ch <= 'Z')
        {
            char temp = ch;
            strcat(token, &temp);
            int type = ERR;
            return type;
        }
        else
            return ch;
    }
    return DONE;
}

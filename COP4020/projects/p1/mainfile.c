#include <stdio.h>

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
    int type;
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
    while((lookahead = fgetc(file)) != EOF){
        if(lookahead == ' ' || lookahead == '\t');
        else if(lookahead == '\n')
            linenum++;
        else if(lookahead >= '0' && lookahead <= '9')
        {

        }
    }
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
            int type = ERR;
            return type;
        }
        else
            return ch;
    }
    return DONE;
}

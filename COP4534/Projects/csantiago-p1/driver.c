#include <stdio.h>
#include <stdlib.h>
#include "linkedlist.h"
#include "dictionary.h"
#include "wordlist.h"
#include "spellchecker.h"

void printFloat(void* data){
	printf(" %lf", *(float*)data);
}

int main(int argc, char** argv){

	char* filepath = NULL;
	char* user_filepath = NULL;
	switch(argc){
		case 2:
			filepath = argv[1];
			user_filepath = "testTextFile.txt";
			break;
		case 3:
			filepath = argv[1];
			user_filepath = argv[2];
			break;
		default:
			filepath = "dictionary.txt";
			user_filepath = "testTextFile.txt";
			break;
	}


	DictionaryT dict;
	SpellCheckerT spel;

	dictionaryInit(&dict, (const char*)filepath);
	spellcheckerInit(&spel, &dict, (const char*)user_filepath);

	printf("\n");
	//printDictionary(&dict);

	//saveDictionaryToFile(&dict, "hashedDict.txt");


	deleteDictionary(&dict);
	printf("\n");

	return 0;
}

#include "dictionary.h"
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int dictionaryInit(DictionaryP dictionary, const char* filepath){
	int toReturn = 0;
	for(int i = 0; i < DICT_SIZE; i++)
		wordlistInit(&(*dictionary).table[i]);
	toReturn = wordsFromFile(dictionary, filepath);
	return toReturn;
}

int wordsFromFile(DictionaryP dictionary, const char* filepath){

	FILE* fp = fopen(filepath, "r");

	if(fp == NULL){
		printf("Could not open file %s", filepath);
		return 1;
	}

	int isEOF = 0;
	int wordHash = 0;
	WordP word = (WordP)malloc(sizeof(WordT));
	while(isEOF != EOF){

		for(int i = 0; i < WORD_SIZE; i++){
			(*word).buffer[i] = 0;
		}
		isEOF = fscanf(fp, "%s", (*word).buffer);

		hashWord(word);
		wordHash = word->hash % DICT_SIZE;

		addWord(&(*dictionary).table[wordHash], word);

	}

	free(word);

	fclose(fp);
	return 0;
}

void deleteDictionary(DictionaryP dictionary){
	for(int i = 0; i < DICT_SIZE; i++){
		deleteWordList(&(*dictionary).table[i]);
	}
}

void printDictionary(DictionaryP dictionary ){
	for(int i = 0; i < DICT_SIZE; i++){
		printWordList(&(*dictionary).table[i]);
	}
}

void saveDictionaryToFile(DictionaryP dictionary, const char* filepath){
	FILE *fp = fopen(filepath, "w");
	NodeP current = NULL;
	WordP word = NULL;
	if(fp == NULL){
		printf("\nFailed to open file");
		return;
	}
	else {
		for(int i = 0; i < DICT_SIZE; i++){
			current = dictionary->table[i].head;
			while(current != NULL){
				word = (WordP)current->data;
				fprintf(fp, "%s, %u\n", (*word).buffer,
					(*word).hash);
				current = current->next;
			}
		}
	}
}

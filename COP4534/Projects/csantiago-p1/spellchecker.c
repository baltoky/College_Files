#include "spellchecker.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>


void spellcheckerInit(SpellCheckerP spellchecker,
		DictionaryP dictionary,  const char* user_filepath){
	queueInit(&spellchecker->queue);
	wordlistInit(&spellchecker->wordlist);
	readFromFile(spellchecker, user_filepath);
	NodeP current = (*spellchecker).wordlist.head;
	while(current != NULL){
		if(findWord(dictionary, (WordP)current->data)){
			printf("\nWord found: ");
			printWord(current->data);
			printf("\n");
		}
		else{
			checkForMisspelled(spellchecker,
					dictionary, current->data);
			while(spellchecker->queue.size != 0){
				printWord(dequeue(&(*spellchecker).queue));
			}			
		}
		current = current->next;
		
	}
} 

void readFromFile(SpellCheckerP spellchecker, const char* user_filepath){
	FILE* fp = fopen(user_filepath, "r"); 
	if(fp == NULL){
		printf("Could not open file %s", user_filepath);
		return;
	}

	int isEOF = 0;
	WordP word = (WordP)malloc(sizeof(WordT));
	int punctuation = 0;
	while(isEOF != EOF){

		for(int i = 0; i < WORD_SIZE; i++){
			(*word).buffer[i] = 0;
		}
		isEOF = fscanf(fp, "%s", (*word).buffer);

		hashWord(word);
		punctuation = strcspn((*word).buffer, ".,");

		if(strlen((*word).buffer) != punctuation)
			for(int i = 0; i < punctuation; i++)
				(*word).buffer[i] = word->buffer[i];

		addWord(&(*spellchecker).wordlist, word);

	}

	free(word);

	fclose(fp);

}

int findWord(DictionaryP dictionary, WordP word){
	int wordHash = word->hash % DICT_SIZE;
	NodeP current = (*dictionary).table[wordHash].head;
	printWord(current->data);
	WordP word1 = (WordP)current->data;
	while(current->next != NULL){
		if(strcmp((*word).buffer, (*word1).buffer) == 0)
			return 1;
		current = current->next;
	}
	return 0;
}

void checkForMisspelled(SpellCheckerP spellchecker,
		DictionaryP dictionary, WordP word){

	oneLetterMissing(spellchecker, dictionary, word);
	oneLetterAdded(spellchecker, dictionary, word);
	twoLettersReversed(spellchecker, dictionary, word);

}

void oneLetterMissing(SpellCheckerP spellchecker,
		DictionaryP dictionary, WordP word){
	printf("Here");
	WordT temp = (*word);
	WordT temp2 = temp;
	char newBuffer[WORD_SIZE];

	for(int i = 0; i < strlen(word->buffer); i++){
		strcpy(newBuffer, temp.buffer);
		strcpy(&newBuffer[i + 1], &temp.buffer[i]);
		for(int j = MIN_LETTER; j < MAX_LETTER; j++){
			newBuffer[i] = j;
			strcpy(temp2.buffer, newBuffer);
			hashWord(&temp2);
			if(findWord(dictionary, &temp2))
				queueUp(&(*spellchecker).queue, &temp2);
		}
	}

}

void oneLetterAdded(SpellCheckerP spellchecker,
		DictionaryP dictionary, WordP word){
	printf("\noneLetterAdded not implemented yet");
}

void twoLettersReversed(SpellCheckerP spellchecker,
		DictionaryP dictionary, WordP word){
	printf("\ntwoLettersReversed not implemented yet");
}


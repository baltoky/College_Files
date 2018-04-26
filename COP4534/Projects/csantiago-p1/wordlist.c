#include "wordlist.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

void wordlistInit(WordListP wordlist){
	(*wordlist).head = NULL;
	(*wordlist).tail = NULL;
}

void addWord(WordListP list, WordP word){
	if((*list).head == NULL){
		addLast(&(*list).head, word, sizeof(WordT));
		(*list).tail = list->head;
	}
	else{
		addLast(&(*list).tail, word, sizeof(WordT));
		(*list).tail = list->tail->next;
	}
}

void removeWordAt(WordListP list, int index){
	deleteAt(&(*list).head, index);
}

WordP removeLastWord(WordListP list){
	NodeP node = removeLast(list->head);
	return (WordP)(*node).data;
}

WordP getWordAt(WordListP list, int index){
	NodeP node = nodeAt(list->head, index);
	return (WordP)(*node).data;
}

void deleteWordList(WordListP wordList){
	deleteFrom(&(*wordList).head);
	(*wordList).head = NULL;
	(*wordList).tail = NULL;
}

void printWord(void* word){
	WordP temp = (WordP)word;
	for(int i = 0; i < WORD_SIZE; i++)
		printf("%c", temp->buffer[i]);
	printf(", %d\n", temp->hash);
}

void printWordList(WordListP wordList){
	printFrom((*wordList).head, printWord);
}

void hashWord(WordP wordNode){
	int hashVal = 0;
	char character = 0;
	char buff[WORD_SIZE];
	strcpy(buff, wordNode->buffer);
	char *s = buff;
	while(*s){
		*s = toupper((unsigned char) * s);
		s++;
	}
	for(int i = 0; i < WORD_SIZE; i++){
		character = s[i];

		if(wordNode->buffer[i] % 2 == 0){
			character += i * (WORD_SIZE - i);
		}
		else character -= i * (WORD_SIZE - i);

		if(wordNode->buffer[i] % 5 == 0){
			character +=  i * (WORD_SIZE - i);
		}
		else character -= i * (WORD_SIZE - i);

		if(wordNode->buffer[i] / 2 > 50){
			character += i * (WORD_SIZE - i);
		}
		else character -= i * (WORD_SIZE - i);

		if(wordNode->buffer[i] / 3 > 25){
			character += i * (WORD_SIZE - i);
		}
		else character -= i * (WORD_SIZE - i);

		hashVal += (WORD_SIZE - i) * character;
	}
	if(hashVal < 0)
		hashVal *= -1;
	(*wordNode).hash = hashVal;
}

int compareWords(WordP word1, WordP word2){
	return strcmp((*word1).buffer, (*word2).buffer);
}


#ifndef WORDLIST_H
#define WORDLIST_H

#define WORD_SIZE 32

#include "linkedlist.h"

typedef struct WordList* WordListP;
typedef struct WordList{
	NodeP head;
	NodeP tail;
}WordListT;

typedef struct Word* WordP;
typedef struct Word{
	char buffer[WORD_SIZE];
	unsigned int hash;
}WordT;

void wordlistInit(WordListP wordlist);

/*
 * Introducing a word at the end of the list.
 * */
void addWord(WordListP list, WordP word);

/*
 * Removing a word from the index given. 
 * */
void removeWordAt(WordListP list, int index);

/*
 * Removing the last word of the list.
 * */
WordP removeLastWord(WordListP list);

WordP getWordAt(WordListP list, int index);

/*
 * Calls the delete function of the linked lists on it's linked list.
 * */
void deleteWordList(WordListP wordList);

/*
 * Function to print one word using the 'Word' structure type.
 * */
void printWord(void* word);

/*
 * Function to print the whole dictionary linked list.
 * 	Uses the function printWord(void*).
 * */
void printWordList(WordListP wordList);

/*
 * Gives a word a certain hash code.
 * */
void hashWord(WordP wordNode);

/*
 * Compares two word types, returns 0 if they are equal.
 * */
int compareWords(WordP word1, WordP word2);

#endif

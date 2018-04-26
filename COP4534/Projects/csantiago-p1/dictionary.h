#ifndef DICITONARY_H
#define DICITONARY_H

#include "wordlist.h"

/*
 * TODO:
 * 	Make the dictionary an array of WordLists so when hashing 
 * 	one might hash the number with %1000 and get a number on this 
 * 	array. This will help making it a O(1) and avoiding a lot
 * 	of time constraints.
 * */
#define DICT_SIZE 1000
typedef struct Dictionary* DictionaryP;
typedef struct Dictionary{
	WordListT table[DICT_SIZE];
}DictionaryT;

/*
 * An initializer function that reads through a text file,
 * 	and hashes the words in that text file.
 * 	This function brings together the other functions
 * 	that work the dictionary structure.
 * */
int dictionaryInit(DictionaryP dictionary, const char* filepath);

/*
 * Reads through a given text file and adds the words into a linked
 * 	list.
 * */
int wordsFromFile(DictionaryP dictionary, const char* filepath);

/*
 * Deletes the linked list inside the dictionary type.
 * */
void deleteDictionary(DictionaryP dictionary);

/*
 * Prints the hashed dictionary.
 * */
void printDictionary(DictionaryP dictionary );

/*
 * Saves the dictionary in a file.
 * */
void saveDictionaryToFile(DictionaryP dictionary, const char* filepath);

#endif

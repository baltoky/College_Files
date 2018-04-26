#ifndef SPELLCHECKER_H
#define SPELLCHECKER_H

#include "dictionary.h"
#include "wordlist.h"
#include "queue.h"

#define MAX_LETTER 172
#define MIN_LETTER 141

typedef struct SpellChecker* SpellCheckerP;
typedef struct SpellChecker{
	QueueT queue;
	WordListT wordlist;
}SpellCheckerT;

void spellcheckerInit(SpellCheckerP spellchecker, DictionaryP dictionary,  const char* user_filepath);
void readFromFile(SpellCheckerP spellchecker, const char* user_filepath);
int findWord(DictionaryP dictionary, WordP word);
void checkForMisspelled(SpellCheckerP spellchecker,
		DictionaryP dictionary, WordP word);
void oneLetterMissing(SpellCheckerP spellchecker, DictionaryP dictionary, WordP word);
void oneLetterAdded(SpellCheckerP spellchecker, DictionaryP dictionary, WordP word);
void twoLettersReversed(SpellCheckerP spellchecker, DictionaryP dictionary, WordP word);

#endif

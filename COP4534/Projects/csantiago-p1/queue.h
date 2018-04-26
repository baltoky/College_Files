#ifndef QUEUE_H
#define QUEUE_H

#include "linkedlist.h"
#include "dictionary.h"

typedef struct Queue* QueueP;
typedef struct Queue{
	WordListT list;
	int size;
}QueueT;

void queueInit(QueueP queue);
void queueUp(QueueP q, WordP word);
WordP dequeue(QueueP q);

#endif

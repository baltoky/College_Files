#include "queue.h"
#include <stdio.h>
#include <stdlib.h>

void queueInit(QueueP queue){
	(*queue).size = 0;
}

void queueUp(QueueP queue, WordP word){
	addWord(&(*queue).list, word);
	(*queue).size++;
}

WordP dequeue(QueueP queue){
	(*queue).size--;
	return removeLastWord(&(*queue).list);
}



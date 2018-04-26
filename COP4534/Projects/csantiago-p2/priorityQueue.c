#include <stdio.h>
#include <stdlib.h>
#include "priorityQueue.h"

PriorityQueueP initPriorityQueue(){
	PriorityQueueP q = malloc(sizeof(PriorityQueueT));
	(*q).heap = NULL;
	(*q).fill = 0;
	(*q).size = q->fill;
	return q;
}

int percolateDown(PriorityQueueP queue, int index,
		int (*compFunc)(void* a, void* b)){
	if(index * 2 > queue->fill){
		(*queue).heap[index] = queue->heap[0];
		(*queue).heap[0] = NULL;
		(*queue).fill--;
		return 1;
	}
	else if((queue->heap[index * 2 + 1] != NULL) && (*compFunc)(queue->heap[index * 2], queue->heap[(index * 2) + 1])){
		(*queue).heap[index] = queue->heap[index * 2];
		return percolateDown(queue, index * 2, (*compFunc));
	}
	else{
		(*queue).heap[index] = queue->heap[(index * 2) + 1];
		return percolateDown(queue, (index * 2) + 1, (*compFunc));
	}
	return 0;
}

void* deleteFromQueue(PriorityQueueP queue,
		int (*compFunc)(void* a, void* b)){
	if(queue->heap == NULL)
		return NULL;
	else{
		void* temp = queue->heap[1];
		(*queue).heap[0] = queue->heap[queue->fill];
		percolateDown(queue, 1, (*compFunc));
		return temp;
	}
}

int percolateUp(PriorityQueueP queue, int index,
		int (*compFunc)(void* a, void* b)){
	int parent = index / 2;
	if(index == 1){
		return 1;
	}
	else if((*compFunc)(queue->heap[index], queue->heap[parent])){
		void* temp = queue->heap[parent];
		(*queue).heap[parent] = queue->heap[index];
		(*queue).heap[index] = temp;
	}
	return percolateUp(queue, parent, (*compFunc));
}

int addToQueue(PriorityQueueP queue, void* element,
		int data_size, int (*compFunc)(void* a, void* b)){
	if(queue->heap == NULL){
		(*queue).fill++;
		(*queue).heap = qalloc(queue, data_size);
		(*queue).heap[queue->fill] = element;
		return 1;
	}
	else if(queue->fill < MAX_SIZE_HEAP){
		(*queue).fill++;
		if(queue->fill == queue->size){
			(*queue).heap = qalloc(queue, data_size);
		}
		(*queue).heap[queue->fill] = element;
		return percolateUp(queue, queue->fill, (*compFunc));
	}
	return 0;
}

void* qalloc(PriorityQueueP q, int data_size){
	void* temp = NULL;
	if(q->heap == NULL){
		(*q).size = 2;
		temp = malloc(q->size * data_size);
		return temp;
	}
	else if(q->fill == q->size){
		(*q).size = (*q).size * 2;
		temp = realloc((*q).heap, q->size * data_size);
		return temp;
	}
	else 
		return q->heap;
}

void printQueue(PriorityQueueP queue, void (*printFunc)(void* a)){
	int i;
	for(i = 1; i < queue->fill + 1; i++){
		(*printFunc)(queue->heap[i]);
		printf("\n");
	}
}

void freeQueue(PriorityQueueP queue, int (*compFunc)(void* a, void* b)){
	int i = 1;
	void* cust = deleteFromQueue(queue, (*compFunc));
	do{
		if(cust != NULL)
			free(cust);
		cust = deleteFromQueue(queue, (*compFunc));
	}while(cust != NULL && i <= queue->fill);
	free(queue);
}

int isQueueEmpty(PriorityQueueP queue){
	if(queue->fill == 0)
		return 1;
	else return 0;
}

#ifndef P_QUEUE_H
#define P_QUEUE_H

#define MAX_SIZE_HEAP 200

typedef struct PriorityQueue* PriorityQueueP;
typedef struct PriorityQueue{
	void** heap;
	int fill;
	int size;
}PriorityQueueT;

PriorityQueueP initPriorityQueue();

int percolateDown(PriorityQueueP queue, int index,
		int (*compFunc)(void* a, void* b));

void* deleteFromQueue(PriorityQueueP queue,
		int (*compFunc)(void* a, void* b));

int percolateUp(PriorityQueueP queue, int index,
		int (*compFunc)(void* a, void* b));

int addToQueue(PriorityQueueP queue, void* element,
		int data_size, int (*compFunc)(void* a, void* b));

void* qalloc(PriorityQueueP q, int data_size);

void printQueue(PriorityQueueP queue, void (*printFunc)(void* a));

void freeQueue(PriorityQueueP queue, int (*compFunc)(void* a, void* b));

int isQueueEmpty(PriorityQueueP queue);

#endif

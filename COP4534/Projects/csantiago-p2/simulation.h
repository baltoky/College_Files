#ifndef SIMULATION_H
#define SIMULATION_H

#include "priorityQueue.h"
#include "customer.h"

typedef struct FIFOQueue* FIFOQueueP;
typedef struct FIFOQueue{
	CustomerP head;
	CustomerP tail;
}FIFOQueueT;

void runSimulation(int n, float lambda, float mu, int M);

/*
 * Initializes a FIFOQueue pointer in the memory.
 * */
FIFOQueueP initFIFOQueue();

/*
 * Adds a member to the start of the queue.
 * */
void addMember(FIFOQueueP queue, CustomerP element);

/*
 * Dequeues the last member of the queue.
 * */
CustomerP dequeueMember(FIFOQueueP queue);

/*
 * Function that returns a random interval given an average value.
 * */
float getNextRandomInterval(float avg);

/*
 * Returns 1 if the queue is empty else returns 0.
 * */
int isFIFOEmpty(FIFOQueueT queue);

/*
 * Prints the queue.
 * */
void printFIFO(FIFOQueueT queue);

/*
 * Function used to fill the priority queue with new customers in terms it's average.
 * */
int populateQueue(PriorityQueueP q, int* i, float t, float avg);

#endif

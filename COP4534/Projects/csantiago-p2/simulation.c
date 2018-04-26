#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include "simulation.h"

void runSimulation(int n, float lambda, float mu, int M){
	int i = 0;
	int freeM = 0;
	float temp;
	float t[2] = {0, 0}; //t[0] = Arival time, t[1] = departure time

	CustomerP cust;
	PriorityQueueP queue = initPriorityQueue();
	FIFOQueueT InNOut;
	InNOut.head = NULL;
	InNOut.tail = NULL;

	i = n;
	freeM = M;

	t[0] = getNextRandomInterval(lambda);

	if(queue->size < M && i > 0)
			populateQueue(queue, &i, t[0], lambda);

	while(!isQueueEmpty(queue) || !isFIFOEmpty(InNOut)){

		if(queue->size < M && i > 0){
			populateQueue(queue, &i, t[0], lambda);
		}

		t[0] += getNextRandomInterval(lambda);

		if(!isQueueEmpty(queue)){
			cust = deleteFromQueue(queue, compareCustPriority);
		}
		else if(!isFIFOEmpty(InNOut)){
			cust = dequeueMember(&InNOut);
		}

		if(cust != NULL && (cust)->priority != DEPARTURE_TIME){

			if(freeM == 0){
				addMember(&InNOut, cust);
			}
			else{
				if(isFIFOEmpty(InNOut)){
					(*cust).priority = S_O_S_TIME;
					setTime(cust, t[0]);

					freeM--;

					(*cust).priority = DEPARTURE_TIME;
					temp = getNextRandomInterval(mu);
					t[1] += temp;
					setTime(cust, t[0] + temp);
					addToQueue(queue, cust, sizeof(CustomerT), compareCustPriority);
				}
				else{
					cust = dequeueMember(&InNOut);
					(*cust).priority = S_O_S_TIME;
					setTime(cust, t[0]);

					freeM--;

					(*cust).priority = DEPARTURE_TIME;
					temp = getNextRandomInterval(mu);
					t[1] += temp;
					setTime(cust, t[0] + temp);
					addToQueue(queue, cust, sizeof(CustomerT), compareCustPriority);
				}
			}

		}
		else if((cust)->priority == DEPARTURE_TIME && 
				(cust)->times[DEPARTURE_TIME] < (t[0] + t[1])){
			freeM++;
			free(cust);
		}
	}

	printf("\n");
	printQueue(queue, printCustomer);

	if(!isQueueEmpty(queue)){
		freeQueue(queue, compareCustPriority);
	}
}

FIFOQueueP initFIFOQueue(){
	FIFOQueueP queue = (FIFOQueueP)malloc(sizeof(FIFOQueueT));
	(*queue).head = NULL;
	(*queue).tail = NULL;
	return queue;
}

void addMember(FIFOQueueP queue, CustomerP element){
	if(queue->head == NULL){
		(*queue).head = element;
		(*queue).tail = element;
	}
	else{
		(*(*queue).tail).next = element;
		(*queue).tail = (queue->tail)->next;
	}
}

CustomerP dequeueMember(FIFOQueueP queue){
	if(queue->head == NULL){
		return NULL;
	}
	else if(queue->head == queue->tail){
		CustomerP current = queue->head;
		(*queue).head = NULL;
		(*queue).tail = NULL;
		return current;
	}
	else{
		CustomerP current = queue->head;
		(*queue).head = current->next;
		return current;
	}
}

float getNextRandomInterval(float avg){
	float f = (float)(rand() % 1000 + 1) / 1000;
	float interval = -1 * (1.0/avg) * log((double)f);
	return interval;
}

int isFIFOEmpty(FIFOQueueT queue){
	if(queue.tail == NULL || queue.head == NULL)
		return 1;
	else
		return 0;
	
}

void printFIFO(FIFOQueueT queue){
	if(queue.head != NULL){
		CustomerP current = queue.head;
		while(current != queue.tail){
			printCustomer(current);
			current = current->next;
		}
		printCustomer(current);
	}
}

int populateQueue(PriorityQueueP q, int* i, float t, float avg){
	CustomerP cust = NULL;
	int isQueueFull = 0;
	int j = (*i);
	do{
		cust = initCustomer();
		setTime(cust, t);
		isQueueFull = addToQueue(q, cust, sizeof(CustomerT), compareCustPriority);

		j--;

		t += getNextRandomInterval(avg);

	}while(j > 0 && isQueueFull);
	(*i) = j;
	return 1;
}

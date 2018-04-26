#ifndef CUSTOMER_H
#define CUSTOMER_H

typedef enum CustomerPriority{
	ARIVAL_TIME,
	S_O_S_TIME,  //Start of Service Time.
	DEPARTURE_TIME
}CustomerPriorityE;

typedef struct Customer* CustomerP;
typedef struct Customer{
	float times[DEPARTURE_TIME + 1];
	CustomerPriorityE priority;
	CustomerP next;
}CustomerT;

/*
 * Returns an allocated version of the structure from memory.
 * */
CustomerP initCustomer();

/*
 * Sets the arrival time to a queue from the start of the simulation.
 * */
void setTime(CustomerP customer,  float time);

/*
 * Prints the customer to the console.
 * */
void printCustomer(void* a);

/*
 * Compares the priority of the customers.
 * */
int compareCustPriority(void* a, void* b);

#endif

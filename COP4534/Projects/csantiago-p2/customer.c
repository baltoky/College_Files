#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include "customer.h"

/*
 * Initializes all values of a customer type to default values.
 * */
CustomerP initCustomer(){
	CustomerP c = malloc(sizeof(CustomerT));
	(*c).times[ARIVAL_TIME] = 0;
	(*c).times[S_O_S_TIME] = 0;
	(*c).times[DEPARTURE_TIME] = 0;
	(*c).priority = ARIVAL_TIME;
	(*c).next = NULL;
	return c;
}

/*
 * Sets the time foro the currently prioritized time stamp to update.
 * */
void setTime(CustomerP customer, float time){
	(*customer).times[customer->priority] = time;
}

/*
 * Prints a customer to the terminal screen.
 * */
void printCustomer(void* a){
	CustomerP customer = (CustomerP)a;
	printf("%s%f \n%s%f\n%s%f\n",
			"Arival Time: ", customer->times[ARIVAL_TIME],
			"Start of Service Time: ", customer->times[S_O_S_TIME],
			"Departure Time: ", customer->times[DEPARTURE_TIME]);
}

/*
 * Compares the priority of times between two customer pointers.
 * */
int compareCustPriority(void* a, void* b){
	CustomerP A = ((CustomerP)(a));
	CustomerP B = ((CustomerP)(b));
	if((*A).times[A->priority] < (*B).times[B->priority])
		return 1;
	else
		return 0;
}

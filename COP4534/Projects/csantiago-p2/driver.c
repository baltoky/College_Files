#include <time.h>
#include <stdio.h>
#include <stdlib.h>
#include <math.h>

#include "priorityQueue.h"
#include "customer.h"
#include "functions.h"
#include "simulation.h"

#define TICKS_PER_SEC 100
#define TIMER_IN_SEC 1


/*
 *
 * n - Number of arrivals to simulate.
 * lambda - Average arivals in a time period.
 * mu - Average number served in a time period.
 * M - The number of service channels
 *
 * */

int main(int argc, char** argv){


	int n = 0;
	float lambda = 0.0;
	float mu = 0.0;
	int M = 0;

	
	printf("Set n: ");
	scanf(" %d", &n);
	printf("Set lambda: ");
	scanf(" %f", &lambda);
	printf("Set mu: ");
	scanf(" %f", &mu);
	printf("Set M: ");
	scanf(" %d", &M);
	printf("\n");

	srand(time(NULL));

	printf("Po = %f \n", Po(lambda, mu, M));
	printf("L = %f \n", Lfunc(lambda, mu, M));
	printf("W = %f \n", Wfunc(lambda, mu, M));
	printf("Lavg = %f \n", Lavg(lambda, mu, M));
	printf("Wavg = %f \n", Wavg(lambda, mu, M));
	printf("Rho = %f \n", rho(lambda, mu, M));

	printf("\n\n");

	runSimulation(n, lambda, mu, M);

	return 0;
}

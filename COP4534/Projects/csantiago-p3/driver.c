#include <sys/time.h>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include "bruteforce.h"
#include "generation.h"

#define W_ARR_WIDTH 20

int main(int argc, char** argv)
{
	srand(time(NULL));

	int len = 0, n = 0, m = 0;
	printf("Please input a size of the matrix (n as n x n): ");
	scanf("%d", &len);
	printf("Please input a number of generations to calculate: ");
	scanf("%d", &n);
	printf("Please input a number of members per generation to calculate: ");
	scanf("%d", &m);


	struct timeval * t;
	t = (struct timeval*) malloc(sizeof(struct timeval));

	gettimeofday(t, NULL);
	time_t start = t->tv_sec;
	printf("\nStart of BF: seconds = %ld, microseconds = %ld\n", t->tv_sec - start, t->tv_usec);

	int s[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19};
	double* w = NULL;
	int size = 0;
	int* e = NULL;
	double minB, minG;

	readWeights(&w, &size);
	printWeights(w);
	minB = permutate(factorial(len - 1), &w, s, len, addPermTotal);
	printf("\n");

	gettimeofday(t, NULL);
	printf("\nEnd of BF: seconds = %ld, microseconds = %ld\n", t->tv_sec - start, t->tv_usec);

	gettimeofday(t, NULL);
	start = t->tv_sec;
	printf("\nStart of GA: seconds = %ld, microseconds = %ld\n", t->tv_sec - start, t->tv_usec);

	printf("\n");
	minG = generativePermutate(n, m, &w, s, len, addPermTotal, e);

	gettimeofday(t, NULL);
	printf("\nEnd of GA: seconds = %ld, microseconds = %ld\n", t->tv_sec - start, t->tv_usec);

	printf("Generational algorithm is [%d%%] optimal.\n", (int)((minG / minB) * 100));

	free(w);

	return 0;
}

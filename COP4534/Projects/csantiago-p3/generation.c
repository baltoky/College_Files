#include <stdio.h>
#include <stdlib.h>
#include "bruteforce.h"
#include "generation.h"

int* mutation(int* s, int len)
{
	int a, b;
	a = (rand() % (len - 1)) + 1;
	b = (rand() % (len - 1)) + 1;
	while(a == b)
	{
		b = (rand() % (len - 1)) + 1;
	}

	swap(s, a, b);
	printf("\n");
	printS(s, len);
	return s;
}

double generation(int m, double** w, int* s, int len, double (*f)(double**, int*, int), int* e)
{
	int i;
	double min, temp;
	int* elite = e;
	if(elite == NULL)
	{
		elite = (int*) malloc(len * sizeof(int));
		copy(s, &elite, len);
	}

	min = f(w, s, len);

	for(i = 0; i < m; i++)
	{
		s = mutation(s, len);
		temp = f(w, s, len);
		printf(" , [%lf]", temp);
		if(min > temp)
		{
				min = temp;
				copy(s, &elite, len);
		}
	}

	printf("\nElite of the generation:\n");
	printf("Elite: ");
	printS(elite, len);
	printf(" : [%lf]\n", min);
	return min;
}

double generativePermutate(int n, int m, double** w,  int* s, int len, double (*f)(double**, int*, int), int* e)
{
	int i;
	double min, temp;
	int* scpy = (int*) malloc(len * sizeof(int));

	copy(s, &scpy, len);

	min = generation(m, w, scpy, len, f, e);
	for(i = 0; i < n - 1; i++)
	{
		temp = generation(m, w, scpy, len, f, e);
		if(min > temp)
			min = temp;
	}
	return min;
}

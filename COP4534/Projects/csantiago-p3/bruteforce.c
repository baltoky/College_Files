#include <stdio.h>
#include <stdlib.h>
#include "bruteforce.h"


void swap(int* s, int a, int b)
{
	int temp = s[a];
	s[a] = s[b];
	s[b] = temp;
}

void printS(int* s, int len)
{
	int i;
	for(i = 0; i < len; i++)
		printf("[%d]", s[i]);
	printf("[0]");
}

void copy(int* original, int** copy, int len)
{
	int i;
	for(i = 0; i < len; i++)
		(*copy)[i] = original[i];
}

double permutate(int n, double** w,  int* s, int len, double (*f)(double**, int*, int))
{
	int m, k, p , q, i;
	double min, temp;
	int* scpy = (int*) malloc(len * sizeof(int));
	int* elite = (int*) malloc(len * sizeof(int));

	printf("\n\n");
	copy(s, &scpy, len);
	min = f(w, scpy, len);
	copy(s, &elite, len);

	for(i = 1; i < n; i++){
		m = len - 2;
		while(scpy[m] > scpy[m+1]){
			m = m - 1;
		}
		k = len - 1;
		while(scpy[m] > scpy[k]){
			k = k - 1;
		}
		swap(scpy, m, k);
		p = m + 1;
		q = len - 1;
		while(p < q){
			swap(scpy, p, q);
			p++;
			q--;
		}
		printS(scpy, len);
		temp = f(w, scpy, len);
		if(min > temp)
		{
			min = temp;
			copy(scpy, &elite, len);
		}
		printf(" , [%lf]\n", temp);

	}
	printf("\n\nmin: [%lf] \nElite:  ", min);
	printS(elite, len);
	return min;
}

double addPermTotal(double** w, int* s, int len)
{
	int i;
	double a = 0;
	for(i = 0; i < len; i++)
		a += (*w)[(s[i] * W_ARR_WIDTH) + s[i + 1]];
	a += (*w)[(s[i + 1] * W_ARR_WIDTH) + s[0]];
	return a;
}

int factorial(int n)
{
	if(n == 1)
		return n;
	return n * factorial(n - 1);
}

/*
 * i: col index,
 * j: row index,
 * p: position,
 * d: temporary double,
 * fp: file pointer.
 * */
void readWeights(double** w, int* size)
{
	int i, j, p;
	double d = 0.0;
	FILE* fp = fopen("cityWeights.txt", "r");
	(*size) = (W_ARR_WIDTH * W_ARR_WIDTH);
	(*w) = (double*)malloc((*size) * sizeof(double));

	for(i = 0; i < W_ARR_WIDTH; i++)
	{
		for(j = 0; j < W_ARR_WIDTH; j++)
		{
			if(i == j)
			{
				d = 0.0;
			}
			else
			{
				fscanf(fp, "%lf\n", &d);
			}
			p = ((W_ARR_WIDTH * i) + j);
			(*w)[p] = d;
		}
	}
}

void printWeights(double* w)
{
	int i, j, p;
	for(i = 0; i < W_ARR_WIDTH; i++)
	{
		for(j = 0; j < W_ARR_WIDTH; j++)
		{
			p = (i * W_ARR_WIDTH) + j;
			printf("[%.2lf] ", w[p]);
		}
		printf("\n");
	}
}


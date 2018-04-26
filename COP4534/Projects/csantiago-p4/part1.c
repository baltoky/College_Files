#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <math.h>

#include "part1.h"

int generateBatch(char* f, int num_in_batch, int percent_bad_items, int is_bad)
{
	FILE* fp = fopen(f, "w");
	int i, rnd, bad = 0;
	for(i = 0; i < num_in_batch; i++){
		rnd = rand() % 100;
		if(rnd <= percent_bad_items && is_bad){
			fprintf(fp, "b\n");
			bad++;
		}
		else
			fprintf(fp, "g\n");
	}
	fclose(fp);
	return bad;
}

int randomizeBatches(int num_of_batches, int num_in_batch, int percent_bad_batches, int percent_bad_items)
{
	int i, is_bad = 0, bad = 0, tot_bad = 0;
	srand(time(NULL));
	char tmp[22];
	for(i = 0; i < num_of_batches; i++)
	{
		sprintf(tmp, "./dsFiles/ds%3d.txt", i);
		if(i < 100)
		{
			tmp[12] = '0';
			if(i < 10)
				tmp[13] = '0';
		}

		if((rand() % 100) < percent_bad_batches) 
			is_bad = 1;
		else 
			is_bad = 0;

		bad = generateBatch(tmp, num_in_batch, percent_bad_items, is_bad);
		if(bad > 0)
		{
			printf("Create bad set batch # %3d, totBad =  %3d, total =  %d, badpct =  %d\n",
				i, bad, num_in_batch, percent_bad_items);
			tot_bad++;
		}
	}
	return tot_bad;
}

int analyzeBatches(int num_of_batches, int num_in_batch, int num_to_sample)
{
	FILE* fp;
	char tmp[22], s;
	int i, j, bad = 0, bad_batch = 0;
	for(i = 0; i < num_of_batches; i++)
	{
		bad = 0;
		sprintf(tmp, "./dsFiles/ds%3d.txt", i);
		if(i < 100)
		{
			tmp[12] = '0';
			if(i < 10)
				tmp[13] = '0';
		}

		fp = fopen(tmp, "r");
		if(fp == NULL)
			printf("Encountered an error, trying to read a file failed.");

		for(j = 0; j < num_to_sample; j++)
		{
			fscanf(fp, "%c", &s);
			if(s == 'b')
				bad++;
		}
		if(bad > 0)
		{
			printf("batch #%d is bad\n", i);
			bad_batch++;
		}
		fclose(fp);
	}
	return bad_batch;
}

void readFromFile(char* f, int** d, int d_size)
{
	FILE* fp = fopen(f, "r");
	int i, eof;
	for(i = 0; i < d_size || eof == EOF; i++){
		eof = fscanf(fp, "%d\n", (&(*d)[i]));
	}
	fclose(fp);
	printf("%s%d\n%s%d\n%s%d\n%s%d\n%s%d\n",
			"Number of batches of items:                      ", (*d)[0],
			"Number of items in each batch:                   ", (*d)[1],
			"Percentage of batches containing bad items:      ", (*d)[2],
			"Percentage of items that are bad in a bad batch: ", (*d)[3],
			"Items sampled from each batch:                   ", (*d)[4]);
}

void part1()
{
	int i, tot_bad, bad_found;
	int* c[CUST_NUM];
	char tmp[10];

	double base = 0.0,
	       exponent = 0.0,
	       p_fail = 0.0,
	       p_good = 0.0,
	       p_detected = 0.0;

	for(i = 0; i < CUST_NUM; i++)
	{
		c[i] = (int*) malloc(sizeof(int) * SPEC_NUM);
		sprintf(tmp, "./c%d.txt", i + 1);
		base = 0.0;
		exponent = 0.0;
		p_fail = 0.0;
		p_good = 0.0;
		p_detected = 0.0;

		printf("Running: \n");
		readFromFile(tmp, &c[i], SPEC_NUM);
		printf("\n");

		printf("Generating data sets: \n");
		tot_bad = randomizeBatches(c[i][NUM_BATCHES], c[i][NUM_IN_BATCH], c[i][P_C_BAD_BATCHES], c[i][P_C_BAD_ITEMS]);
		printf("\n");

		printf("Analizing data sets: \n");
		bad_found = analyzeBatches(c[i][NUM_BATCHES], c[i][NUM_IN_BATCH], c[i][NUM_TO_SAMPLE]);
		printf("\n");

		base = 1.0 - ((double)c[i][P_C_BAD_ITEMS] / 100);
		exponent = (c[i][NUM_TO_SAMPLE]);
		p_fail = pow(base, exponent);
		p_good = 1.0 - p_fail;
		p_detected = (double)bad_found / (double)tot_bad;

		printf("%s%lf, %s%lf\n%s%lf\n%s%lf\n%s%lf\n",
				"Base = ", base,
				"exponent = ", exponent,
				"P(failure to detect bad item) = ", p_fail,
				"P(batch is good) = ", p_good,
				"Percentage of bad batches detected = ", p_detected);
		printf("\n");
	}

	for(i = 0; i < CUST_NUM; i++)
	{
		free(c[i]);
	}
}

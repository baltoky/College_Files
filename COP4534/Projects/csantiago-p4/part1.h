#ifndef PART1_H
#define PART1_H

#define SPEC_NUM 5
#define CUST_NUM 4

typedef enum c_opts
{
	NUM_BATCHES = 0,
	NUM_IN_BATCH,
	P_C_BAD_BATCHES,
	P_C_BAD_ITEMS,
	NUM_TO_SAMPLE
}c_optsE;

/*
 * Generates a single batch whether bad or good.
 * parameters:
 * f - filepath to the batch
 * num_in_batch - numbers of items in the batch
 * parcent_bad_items - the percentage of bad items in the batch
 * is_bad - asks if the batch is bad or good
 * */
int generateBatch(char* f, int num_in_batch, int percent_bad_items, int is_bad);

/*
 * Randomizes each batch depending on the settings from the c[x] files.
 * parameters:
 * num_of_batches - total number of batches to be generated
 * num_in_batch - number of items in a batch
 * percent_bad_batches - percentage of bad batches
 * percent_bad_items - percentage of bad items in a batch
 * */
int randomizeBatches(int num_of_batches, int num_in_batch, int percent_bad_batches, int percent_bad_items);

/*
 * Analyzes all of the batches and tells if they are good or bad.
 * parameters:
 * num_of_batches - number of batches to analyze
 * num_in_batch - number of items in each batch
 * num_to_sample - number of items to sample to determine bad/good
 * */
int analyzeBatches(int num_of_batches, int num_in_batch, int num_to_sample);

/*
 * Reads from a file to an array
 * parameters:
 * f - filepath from which the settigns are coming
 * d - reference to an array to read the settings to
 * d_size - size of the array
 * */
void readFromFile(char* f, int** d, int d_size);

/*
 * Actuates the whole part of the project.
 * */
void part1();

#endif

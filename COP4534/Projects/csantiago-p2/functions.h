#ifndef FUNCTIONS_H
#define FUNCTIONS_H

/*
 * Usual reccuring factorial function for the calculation of factorial values.
 * */
float factorial(int n);

/*
 * Series for the calculation of Po.
 * */
float mSeries(float lambda, float mu, int M);

/*
 * Percent Idle time function.
 * */
float Po(float lambda, float mu, int M);

/*
 * Average number of people in the system.
 * */
float Lfunc(float lambda, float mu, int M);

/*
 *Average time a customer spends in the system:
 * */
float Wfunc(float lambda, float mu, int M);

/*
 * Average number of customers in the queue:
 * */
float Lavg(float lambda, float mu, int M);

/*
 * Average time a customer spends waiting in the queue
 * */
float Wavg(float lambda, float mu, int M);

/*
 * Proportion of the System resources.
 * */
float rho(float lambda, float mu, float M);

#endif

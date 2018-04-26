#include"functions.h" 
#include "math.h"

float factorial(int n){
	if(n == 1)
		return (float)n;
	else
		return ((float)n * factorial(n - 1));
}

float mSeries(float lambda, float mu, int M){
	float sum = 0;
	if(M == 0){
		return 1;
	}
	else{
		sum = ((1.0f / factorial(M)) * pow((lambda / mu), M));
	}
	return sum + mSeries(lambda, mu, M - 1);
}

float Po(float lambda, float mu, int M){
	float P = 0.0f;
	P = 1.0f / (mSeries(lambda, mu, M - 1) 
			+ (((1.0f / factorial(M)) * pow((lambda / mu), M)) * ((mu * M) / ((mu * M) - lambda))));
	return P;
}

float Lfunc(float lambda, float mu, int M){
	float numerator = (lambda * mu) * pow((lambda / mu), M);
	float denomin = factorial(M - 1) * pow(((M * mu) - lambda), 2);
	float L = (numerator / denomin) * Po(lambda, mu, M);
	L += (lambda / mu);
	return L;
}

float Wfunc(float lambda, float mu, int M){
	float W = Lfunc(lambda, mu, M) / lambda;
	return W;
}

float Lavg(float lambda, float mu, int M){
	float avg = Lfunc(lambda, mu, M) - (lambda / mu);
	return avg;
}

float Wavg(float lambda, float mu, int M){
	float avg = Lavg(lambda, mu, M) / lambda;
	return avg;
}

float rho(float lambda, float mu, float M){
	return (lambda / (mu * M));
}



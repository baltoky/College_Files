#ifndef BRUTEFORCE_H
#define BRUTEFORCE_H

#define W_ARR_WIDTH 20

void swap(int* s, int a, int b);
void printS(int* s, int len);
void copy(int* original, int** copy, int len);
double permutate(int n, double** w,  int* s, int len, double (*f)(double**, int*, int));
double addPermTotal(double** w, int* s, int len);
int factorial(int n);
void readWeights(double** w, int* size);
void printWeights(double* w);

#endif

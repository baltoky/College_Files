#ifndef GENERATION_H
#define GENERATION_H

int* mutation(int* s, int len);
double generation(int m, double** w, int* s, int len, double (*f)(double**, int*, int), int* e);
double generativePermutate(int n, int m, double** w, int* s, int len, double (*f)(double**, int*, int), int* e);

#endif

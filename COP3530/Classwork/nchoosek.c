#include <stdio.h>

long n_choose_k(int n, int k);
long factorial(int num);

int main()
{

	int n, k;
	printf("Input a number to represent k: \n");
	scanf(" %d", &k);
	printf("Input a number to represent n (has to be larger than k): \n");
	scanf(" %d", &n);
	printf("There are %ld different combinations to choose from %d\n", n_choose_k(n, k), n);

}

long n_choose_k(int n, int k)
{
	return (factorial(n) / (factorial(k) * factorial(n - k)));
}

long factorial(int num)
{

	if(num == 1) return 1;
	return num * factorial(num - 1);

}


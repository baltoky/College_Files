#include <stdio.h>

long factorial(int num);
int sumnatural(int num);

int main()
{

	int num;
	printf("Please input a number to get a factorial!\n");
	scanf(" %d", &num);
	printf("The factorial of %d = %ld\n", num, factorial(num));

}

long factorial(int num)
{

	if(num == 1) return 1;
	return num * factorial(num - 1);

}


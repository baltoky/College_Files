#include <stdio.h>

int sumnatural(int first, int last);

int main()
{

	int first, last;
	printf("Please input a number to start the sum\n");
	scanf(" %d", &first);
	printf("Input a number to end the sequence: \n");
	scanf(" %d", &last);
	printf("The sum of the sequence is: %d\n", sumnatural(first, last));

}

int sumnatural(int first, int last)
{

	int ans = first;
	if(first == last) return last;
	ans += sumnatural(first + 1, last);
	return ans;

}

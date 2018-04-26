#include <stdio.h>

long factorial(int num);
long choose(int n, int k);

int main(){

	int num = 5;
	int from = 8;
	printf("The n_choose_k case of %d from %d: %ld\n", num, from, choose(from, num));

	return 0;
}

long factorial(int num){
	long ans = num;
	if(num == 1) return 1;
	ans *= factorial(num - 1);
	return ans;
}

long choose(int n, int k){
	return (factorial(n) / (factorial(k) * factorial(n - k)));
}


#include <stdio.h>

void addrFunc();
int recuFunc();
long factorial(int num);
int sum(int n);
int fibonacci(int num);

int main(int argc, char** argv){

	addrFunc();
	int i = 8;
	long factor = factorial(i);
	printf("Solution for a recursive of %d: %d\n", i, recuFunc(i));
	printf("The factorial of %d! = %ld\n", i, factor);
	printf("The sum of the numbers recursively to %d! = %d\n", i, sum(i));
	printf("The fibonacci sequence to %d! = %d\n", i, fibonacci(i));
}

void addrFunc(){
	int addr[10] = {0, 23, 45, 12, 22, 54, 56, 75, 12, 34};
	void* ptr;
	int i = 0;
	while(i < 10){ 
		ptr = addr + i;
		printf("Address: %p, Value: %d\n", ptr, addr[i]);
		i++;
	}
}

int recuFunc(int value){
	int solution = 0;
	if(value > 0)
		solution = (2 * recuFunc(value - 1)) + 3;
	return solution;
}

long factorial(int num){
	long answer = num;
	if(num  == 1)
		return 1;
	answer = answer * factorial(num - 1);
	return answer;
}

int sum(int n){
	int s = n;
	if(n == 0)
		return 1;
	s = s + sum(n -1);
	return s;
}

int fibonacci(int num){
	if(num == 0) return 0;
	else if(num == 1) return 1;
	else return fibonacci(num - 1) + fibonacci(num - 2);
}


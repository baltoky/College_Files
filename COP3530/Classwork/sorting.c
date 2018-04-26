#include <stdio.h>
#include <stdlib.h>

/*
 * Cesar A. Santiago
 * Thursday 14, September 2017
 * COP3530 Data Structures and Algorithms I
 * */

void swap(int* arg, int a, int b);
void bubble_sort(int* arg, int num_of_args);
void* getUserInput(int* nums, int* i);

int main()
{
	printf("Please input a series of numbers. To finish type a negative number.\n");

	int* num = (int*) malloc(sizeof(int));
	int i = 0;

	num = (int*)getUserInput(num, &i);

	int j;
	for(j = 0; j < i; j++) printf("%d\n", num[j]);

	bubble_sort(num, i + 1);

	for(j = 0; j < i; j++) printf("%d\n", num[j]);

	free(num);
	return 0;
}

void swap(int* arg, int a, int b){
	int temp = arg[a];
	arg[a] = arg[b];
	arg[b] = temp;
}

void bubble_sort(int* arg, int num_of_args){
	int index = 0;
	while(index < num_of_args){
		if(arg[index] > arg[index + 1] && (index != num_of_args)){
			swap(arg, index, index + 1);
		}
		index++;
	}
}

void* getUserInput(int* nums, int* i){
	int hold = 0;
	do{
		int t;

		scanf(" %d", &hold);
		if(hold > 0){

		i++;
		int* temp = (int*) realloc(nums, (sizeof(int) * (i + 1)));
		nums = temp;

		}

	}while(hold > 0);
	return nums;
}


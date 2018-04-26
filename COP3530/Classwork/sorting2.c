#include <stdio.h>

void quicksort(int* list, int low, int high);
void mergesort(int* list, int left, int right, int indeces);
void swap(int* a, int* b);
void printList(int* list, int indeces);

int main(){
	int num = 0;
	printf("Insert how many numbers in the array:\n");
	scanf(" %d", &num);

	int list[num];
	int i;
	for(i = 0; i < num; i++){
		printf("Enter a number: ");
		scanf(" %d", &list[i]);
	}
	printList(list, num);
	mergesort(list, 0, num, num);
	return 0;
}

void quicksort(int* list, int low, int high){
	int median = high / 2;
	swap(&list[median], &list[high]);
	if(low < median && high > median){
		if(low < high)
			quicksort(list, low + 1, high);
		else if(low > high){
			swap(&list[low], &list[high]);
			quicksort(list, low + 1, high - 1);
		}
	}
}

void mergesort(int* list, int left, int right, int indeces){
	if(left == right || left + 1 == right) {
		if(list[left] > list[right]){
			swap(&list[left], &list[right]);
			printList(list, indeces);
		}
	}
	else{
		int divider = right / 2;
		mergesort(list, 0, divider, indeces);
	}
}

void swap(int* a, int* b){
	int temp = *a;
	*a = *b;
	*b = temp;
}

void printList(int* list, int indeces){
	int i;
	for(i = 0; i < indeces; i++){printf(" %d ", list[i]);}
}

#include <stdio.h>
#include <stdlib.h>

int* init();
int read_from_user(int* list);
int bubblesort(int* list, int size);
void display(int* list, int size);
void swap(int* a, int* b);

int main(int argc, char** argv){
	int* a_init;
	int a_size;

	a_init = init();
	a_size = read_from_user(a_init);
	display(a_init, a_size);

	swap(&a_init[a_size - 1], &a_init[a_size]);
	display(a_init, a_size);

//	if(bubblesort(a_init, a_size)){
//		display(a_init, a_size);
//	}

	return 0;
}

int* init(){
	int* temp;
	if((temp = (int*)malloc(1 * sizeof(int))) == NULL){
		printf("ERROR: on init()");
	}
	return temp;
}

int read_from_user(int* list){
	int* tmp;
	int size = 1;
	int i = 0;
	do{
		printf("Please input a positive number, input 0 to exit: ");
		scanf(" %d", &i);
		if(i != 0){
			list[size - 1] = i;
			size++;
			if((tmp = (int*)realloc(list, size * sizeof(int))) == NULL){
				printf("ERROR: reallocating memory");
				free(list);
			}
			list = tmp;
		}
	}while(i != 0);

	return size;
}

int bubblesort(int* list, int size){
	int i;
	for(i = 0; i < size; i++){
		if(list[i] > list[i + 1]){
			swap(&list[i], &list[i + 1]);
		}
		i++;
	}
}

void display(int* list, int size){
	int i;
	for(i = 0; i < size; i++)
		printf(" %d ", list[i]);
	printf("\n");
}

void swap(int* a, int* b){
	*a = *a + *b;
	*b = *a - *b;
	*a = *a - *b;
}

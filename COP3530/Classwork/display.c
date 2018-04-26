#include <stdio.h>
#include <stdlib.h>

void display(int* list, int indeces);
int search(int* list, int indeces, int num_to_find);
void delete_num(int* list, int indeces, int num_to_delete);
void bubblesort(int* list, int low, int high);
void swap(int* a, int* b);
void insertEnd(int* list, int num, int num_of_indeces);
void insertOrder(int* new_list, int* list, int num, int num_of_indeces);

/*
 * Cesar Santiago
 * COP 3530 Data Structures and Algorithms I
 * Thursday 21, September.
 * */

int main(int argc, char** argv){
	int testArray[] = {10, 54, 12, 44, 23, 35, 15, 21, 20};
	int* temporary_pointer;
	int nItems = 9;
	int searchKey = 15;
	int searchIndex;
	int num_to_delete = 44;

	display(testArray, nItems);
	
	searchIndex = search(testArray, nItems, searchKey);
	if(searchIndex){
		printf("The number %d was found at index %d.\n", searchKey, searchIndex);
	}
	else printf("The number was not found on the array.\n");

	printf("\nDeleting the number at %d, tagging at the end.\n", num_to_delete);
	delete_num(testArray, nItems, num_to_delete);
	display(testArray, nItems);

	printf("\nOrganizing the array with a bubblesort:\n");
	bubblesort(testArray, 0, nItems);
	display(testArray, nItems);

	nItems++;
	if((temporary_pointer = (int*)realloc(testArray, nItems * sizeof(int))) == NULL){
		printf("ERROR:: LINE 43");
		free(testArray);
		return 1;
	}
	testArray = temporary_pointer;
	insertEnd(testArray, 22, nItems);
	printf("\nAdding %d to the list at the end. \n", 22);
	display(testArray, nItems);

	printf("\nOrganizing the array again with a bubblesort:\n");
	bubblesort(testArray, 0, nItems);
	display(testArray, nItems);
	
	int index_new_2 = (index_new + 1);
	int even_newer_list[index_new_2];
	insertOrder(even_newer_list, newer_list, 39, index_new_2);
	printf("\nAdding %d to the list orderly. \n", 39);
	display(even_newer_list, index_new_2);

	return 0;
}

int search(int* list, int indeces, int num_to_find){ // Returns the index of the searched value, if found.
	int index = 0;
	int i;
	for(i = 0; i < indeces; i++){
		if(list[i] == num_to_find)
			index = i;
	}
	return ++index;
}

void delete_num(int* list, int indeces, int num_to_delete){
	int temp = search(list, indeces, num_to_delete);
	int i;
	for(i = temp - 1; i < indeces - 1; i++){
		swap(&list[i], &list[i + 1]);
	}
}

void bubblesort(int* list, int low, int high){
	int i;
	if(low != high){
		for(i = low; i < high - 1; i++){
			if(list[i] > list[i + 1])
				swap(&list[i], &list[i + 1]);
		}
		bubblesort(list, low, high - 1);
	}
}

void insertEnd(int* list, int num, int num_of_indeces){
	list[num_of_indeces] = num;
}

void insertOrder(int* new_list, int* list, int num, int num_of_indeces){
	int i = 0, j = 0, nums_to_include = 1;
	while(i < num_of_indeces){
		if(num < list[j] || nums_to_include == 0){
			new_list[i] = list[j];
			i++;
			j++;
		}
		else{
			i++;
			nums_to_include--;
			new_list[i] = num;
		}
	}
}

void swap(int* a, int* b){
	int t = *a;
	*a = *b;
	*b = t;
}

void display(int* list, int indeces){
	int i;
	for(i = 0; i < indeces; i++) printf(" %d ", list[i]);
	printf("\n");
}

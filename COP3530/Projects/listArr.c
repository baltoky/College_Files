#include <stdio.h>
#include <stdlib.h>

/*
 * Functions:
 * * * */
void traverseList(int* arr);
void insertListLast(int** arr, int num);
void insertAt(int** arr, int num, int pos);
int linearSearch(int* arr, int num);
void removeFrom(int** arr, int pos);
void removeItem(int** arr, int num);
void shellSort(int** arr);
void quickSort(int** arr, int low, int high);
int binarySearch(int* arr, int num, int high, int low);
void insertAtSorted(int** arr, int num);

/*
 * Variable Directory:
 * 	arraySIZE:- - -	the size of the entire array.
 * 	filledSIZE:     the size of the filled parts of an array.
 *	arr:- -	- - - -	an array pointer, or an array "reference".
 *	num:		a constant or variable number that exists within the array.
 *	pos:- -	- - - -	the position of an element in the array plus one. (Starting from one.)
 *	low:		the lower integer. Used in quicksort which is recursive.
 *	high: -	- - - -	the higher integer. Used in quicksort which is recursive.
 *	element:	the position of an element in the array. (Starting from zero.)
 *	temp: -	- - - -	a temporary item used for algorithm operations.
 *	i:		temporary index of an array. Used in for loops.
 *	j:- - -	- - - -	temporary index of an array. Used in nested loops.
 *	size: 	   	temporary size of the filledSize data. So that it is not accidentally distorted.
 *	dist: -	- - - -	the distance between the two integers calculated on the shellsort function.
 * */

int arraySIZE = 0;
int filledSIZE = 0;

void menu(){
	printf("\n\nPick an option:\n");
	printf("1) Print List.\n");
	printf("2) Insert last.\n");
	printf("3) Insert at a position.\n");
	printf("4) Insert in a sorted list.\n");
	printf("5) Remove from a position.\n");
	printf("6) Remove a certain value.\n");
	printf("7) Organize.\n");
	printf("8) Exit.\n");
	printf(" ~~ ");
}

int main(int argc, char** argv){
	int* arr = NULL;

	printf("\n\tWelcome!\n");
	printf("\nPlease input the size of the stack you wish to have: ");
	scanf(" %d", &arraySIZE);
	arr = (int*) malloc(sizeof(int) * arraySIZE);

	int selection = 0;
	while(selection >= 0){
	menu();
	int temp = 0;
	int temp2 = 0;
	scanf(" %d", &selection);
	switch(selection){
			case 1:
				traverseList(arr);
				break;
			case 2:
				printf("Please input a number to insert last: ");
				scanf(" %d", &temp);
				insertListLast(&arr, temp);
				traverseList(arr);
				break;
			case 3:
				printf("Please input a number to insert: ");
				scanf(" %d", &temp);
				printf("Please input where to position it: ");
				scanf(" %d", &temp2);
				insertAt(&arr, temp, temp2);
				traverseList(arr);
				break;
			case 4:
				printf("Please input a number to insert: ");
				scanf(" %d", &temp);
				insertAtSorted(&arr, temp);
				traverseList(arr);
				break;
			case 5:
				printf("Please input a position to remove from: ");
				scanf(" %d", &temp);
				removeFrom(&arr, temp);
				traverseList(arr);
				break;
			case 6:
				printf("Please input a number to remove: ");
				scanf(" %d", &temp);
				removeItem(&arr, temp);
				traverseList(arr);
				break;
			case 7:
				shellSort(&arr);
				traverseList(arr);
				break;
			case 8:
			default:
				selection = -1;
				break;
		}
	}

	return 0;
}

/*
 * Result:
 * 	Goes through an array, it prints to the screen.
 * Param:
 * 	int* arr: Pointer to the first element on an integer array.
 * */
void traverseList(int* arr){
	int i;
	printf("\nPrinted List: \n");
	for(i = 0; i < filledSIZE; i++){
		printf(" (%d), ", arr[i]);
	}
	printf("\n");

}

/*
 * Result:
 * 	Inserts a set integer to the last number of the function.
 * Param:
 * 	int** arr: Reference to an array in memory.
 * 	int num: number to be inserted.
 * */
void insertListLast(int** arr, int num){
	if(filledSIZE != arraySIZE){
		(*arr)[filledSIZE] = num;
		filledSIZE++;
	}else printf("\nERROR: array is full. Delete items.\n");

}

/*
 * Result:
 * 	Inserts an integer to a set position.
 * Param:
 * 	int** arr: Reference to an array in memory.
 * 	int num: number to be inserted.
 * 	int pos: position of the element to be inserted at.
 * */
void insertAt(int** arr, int num, int pos){
	int element = pos - 1;
	int temp = 0;
	int i;
	if(filledSIZE != arraySIZE){
		for(i = filledSIZE; i >= element; i--){
			temp = (*arr)[i];
			(*arr)[i + 1] = temp;
		}
		(*arr)[element] = num;
		filledSIZE++;
	}else printf("\nERROR: array is full. Delete items.\n");

}

/*
 * Result:
 * 	Searches through the array linearly to find a number, returns the position.
 * Param:
 * 	int* arr: A pointer to the first element of an array.
 * 	int num: the number to be found.
 * */
int linearSearch(int* arr, int num){
	int i;
	for(i = 0; i < filledSIZE; i++){
		if(arr[i] == num) return i;
	}
	printf("\nCould not find that specific integer.\n");
	return 0;

}

/*
 * Result:
 * 	Removes an integer from an array from an indicated position.
 * Param:
 * 	int** arr: the Reference to an array.
 * 	int pos: the position of the element to remove.
 * */
void removeFrom(int** arr, int pos){
	int element = pos - 1;
	int temp = 0;
	int i;
	for(i = element; i <= filledSIZE; i++){
		temp = (*arr)[i + 1];
		(*arr)[i] = temp;
	}
	filledSIZE--;
}

/*
 * Result:
 * 	Finds through a search and removes an element from the array.
 * Param:
 * 	int** arr: The Reference to an array.
 * 	int num: the number value to be removed.
 * */
void removeItem(int** arr, int num){
	int element = linearSearch((*arr), num);
	int pos = element + 1;
	removeFrom(arr, pos);
}

/*
 * Result:
 * 	Organizes the array values in accending order using the shellsort algorithm.
 * Param:
 * 	int** arr: The Reference to an array of integers.
 * */
void shellSort(int** arr){
	int size = filledSIZE;
	int dist = size / 2;
	int temp = 0;
	int i;
	int j;
	while(dist > 0){
		for(i = dist; i < size; i++){
			temp = (*arr)[i];
			for(j = i; j >= dist && (*arr)[j - dist] > temp; j -= dist)
				(*arr)[j] = (*arr)[j - dist];
			(*arr)[j] = temp;
		}
		dist /= 2;
	}
}

/* A helper function for quicksort.*/
int partition(int** arr, int low, int high){
	int h_element = (*arr)[high];
	int l_index = (low - 1);
	int temp = 0;
	int j;
	for(j = low; j <= high - 1; j++){
		if((*arr)[j] <= h_element){
			l_index++;

			//	Swap.
			temp = (*arr)[j];
			(*arr)[j] = (*arr)[l_index];
			(*arr)[l_index] = temp;
		}
	}
	
	//	Swap.
	temp = (*arr)[high];
	(*arr)[high] = (*arr)[l_index + 1];
	(*arr)[l_index + 1] = temp;

	return (l_index + 1);
}

/*
 * Result:
 * 	Organizes the array using a recursive algorithm.
 * Param:
 * 	int** arr: the Reference to an array.
 * 	int low: the lowest member of the array.
 * 	int high: the highest member of the array.
 * */
void quickSort(int** arr, int low, int high){
	if(low < high){
		int part = partition(arr, low, high);
		quickSort(arr, low, part - 1);
		quickSort(arr, part + 1, high);
	}
}

/*
 * Result:
 * 	Searches through an ordered array using binary sort.
 * Param:
 * 	int* arr: the first element of an array.
 * 	int num: the number to find.
 * 	int high: the highest number of the array.
 * 	int low: the lowest number of the array.
 * */
int binarySearch(int* arr, int num, int high, int low){
	if(high >= low){
		int middle = low + (high - low) / 2;
		if(arr[middle] == num)return middle;
		if(arr[middle] > num) return binarySearch(arr, num, middle - 1, low);
		else return binarySearch(arr, num, high, middle + 1);
	}
	return 0;
}

/*
 * Result:
 * 	Inserts an integer in it's place on an ordered list.
 * Param:
 * 	int** arr: the Refernece to an array.
 * 	int num: the number to be inserted.
 * */
void insertAtSorted(int** arr, int num){
	int i;
	int element;
	for(i = 0; i < filledSIZE; i++){
		if((*arr)[i] > num){
			element = i;
		}
	}
	insertAt(arr, num, element - 1);
}


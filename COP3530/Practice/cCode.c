#include <stdio.h>
#include <stdlib.h>

/*
 * Cesar Santiago
 * */

typedef struct node{
	int data;
	struct node* previous;
	struct node* next;
}node;

void printList(node* list);
void push(node* list, int data);
void pop(node* list);

int main(int argc, char** argv){
	node* start = NULL;
	printList(start);
	return 0;
}

void printList(node* list){
	if(list == NULL){
		printf("Error: list is empty\n");
	}else{
		printf("\n\n");
		while(list != NULL){
			printf(" (%d) ", list->data);
		}
		printf("\n\n");
	}
}

void push(node* list, int data){
}

void pop(node* list){
}


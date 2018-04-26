#include <stdio.h>
#include <stdlib.h>

typedef struct node{
	int data;
	struct node* next;
}node;

void printList(node* list);
void push(node** list, int data);
int pop(node** list);
void getUserInput(node** list);
void menu();

node* head = NULL;
node* current = NULL;

int main(int argc, char** argv){

	node* header = NULL;
	int x = 1;

	while(x != 0){
		menu();
		printf(" ~~ ");
		scanf(" %d", &x);
		printf("\n");
		int num;
		switch(x){
			case 1:
				getUserInput(&header);
				printList(header);
				printf("\n");
				break;
			case 2:
				num = pop(&header);
				printf("\nPopping the top of the stack. Popped :%d ", num);
				printList(header);
				printf("\n");
				break;
			case 3:
				printf("\n");
				printList(header);
				printf("\n");
				break;
			case 4:
			default:
				x = 0;
				break;
		}
	}

	return 0;

}

/*
 * Prints a list from the top pointer.
 * */
void printList(node* list){
	if(list == NULL){
		printf("\nERROR: List is empty.\n");
	}
	else{
		for(current = list; current != NULL; current = current->next){
			printf(" (%d) ", current->data);
		}
	}
}

/*
 * Checks if the stack is empty,
 * if it is then it creates a new node and returns it as the top pointer.
 * if it's not then it will create a new node, link it to it's
 * 		bottom node and return it as the top pointer.
 * */
void push(node** list, int data){
	if(list == NULL){
		node* element = (node*)malloc(sizeof(node));
		element->data = data;
		(*list) = element;
	}
	else{
		node* temp = (*list);
		node* element = (node*)malloc(sizeof(node));
		element->data = data;
		element->next = temp;
		(*list) = element;
	}
}

/*
 * Checks if the stack is empty,
 * if it is then it will error and perform a message.
 * if it's not then it will return the pointer to the next node from the top
 * 	and return it as the top pointer.
 * */
int pop(node** list){
	if(list == NULL){
		printf("\nERROR: List is empty.\n");
	}
	else{
		int num = (*list)->data;
		(*list) = (*list)->next;
		return num;
	}
	return 0;
}

/*
 * Gets the input from a user, using the functions deffined above.
 * */
void getUserInput(node** list){
	int temp = 0;
	while(temp >= 0){
		if(list == NULL){
		 	printf("\nList is empty, please input a positive integer: ");
		 	scanf(" %d", &temp);
			push(&(*list), temp);
			printf("\n");
		}
		else{
			printf("\nPlease input another number (or '-1' to quit.): ");
			scanf(" %d", &temp);
			if(temp >= 0)
				push(&(*list), temp);
			else printf("\nNegative number input, quiting.\n\n");
			printf("\n\n");
		}
	}
}

/*
 * Prints the menu.
 * */
void menu(){
	printf("\n\tWELCOME\t\n");
	printf("\tPlease select an option from below, otherwise use any other key to quit.\n");
	
	printf("\n1) Push numbers into an extisting stack.");
	printf("\n2) Pop a number from an existing stack.");
	printf("\n3) Print an existing stack.");
	printf("\n4) Exit.");
	printf("\n");
}

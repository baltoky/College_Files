#define TRUE 1
#define FALSE 0

#include <stdio.h>
#include <stdlib.h>

typedef struct node{
	int data;
	struct node* next_node;
}node;

node* head = NULL;
node* current = NULL;

int print_list(node* list);
int is_empty(node* list);
int get_from_user(node* list);
void insertFirst(node* list, int data);


int main(int argc, char** argv){
	node* list = NULL;
	insertFirst(list, 20);
	insertFirst(list, 42);
	print_list(list);
	return 0;
}

int print_list(node* list){
	if(is_empty(list)){
		printf("Error: List is empty\n");
		return FALSE;
	}
	else{
		printf("\n\n");
		while(list != NULL){
			printf(" (%d) ", list->data);
			list = list->next_node;
		}
		printf("\n\n");
		return TRUE;
	}
}

int is_empty(node* list){
	if(list == NULL){
		return TRUE;
	}else return FALSE;
}

int get_from_user(node* list){
	int num;
	char temp;
	if(is_empty(list)){
		printf("List is empty. input a number: ");
		scanf(" %d", &num);
		insertFirst(list, num);
		return TRUE;
	}
	else{
		printf("The list's current top number is %d, would you like to input another number?(y, n)", list->data);
		scanf(" %c", &temp);
		switch(temp){
			case 'Y':
			case 'y': printf("Input a number: ");
				  scanf(" %d", &num);
				  insertFirst(list, num);
				  break;
			case 'N':
			case 'n': 
			default: printf("Finished input process.");
		}
		return TRUE;
	}
}

void insertFirst(node* list, int data){
	if(is_empty(list)){
		node* element = (node*)malloc(sizeof(node));
		element->data = data;
		list = element;
	}
	else{
		node* temp = list;
		node* element = (node*)malloc(sizeof(node));
		element->data = data;
		element->next_node = temp;
		list = element;
	}
}

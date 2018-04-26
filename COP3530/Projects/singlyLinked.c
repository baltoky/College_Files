#include <stdio.h>
#include <stdlib.h>

typedef struct node{
	int data;
	struct node* next;
}node;

void display(node* list);
void insertNode(node** list, int data);
void sort(node** list);

node* current = NULL;
node* tail = NULL;

int main(int argc, char** argv){
	node* head = NULL;
	int i;
	int userInput = 0;
	int answer1 = 0;

	printf("\nWelcome.\n\
			This Program allows a user to create a linked list of their choosing\n\
			First the user mush choose the size of the list,\n\
			then input the same amount of integer numbers.\n\
			the program will then display them, sort them, and allow for the user to input another\n\
			number if the user so chooses. After which will display and close.\n");

	printf("\nHow many nodes would you like on the list: ");
	scanf(" %d", &answer1);
	for(i = 0; i < answer1; i++){
		printf("\nInsert node #%d: ", i);
		scanf(" %d", &userInput);
		insertNode(&head, userInput);
	}

	printf("\nThis is the list the user created:\n");
	display(head);

	sort(&head);

	printf("\nThis is the list the user created after the sorting process:\n");
	display(head);

	printf("\nWould the user like to add another node to the list? (Y/N)");
	char ans;
	scanf(" %c", &ans);
	switch(ans){
		case 'Y':
		case 'y':
			printf("\nWhat number would the user choose: ");
			scanf(" %d", &userInput);
			insertNode(&head, userInput);
			sort(&head);
			printf("\nThis is what the list looks like after the new input:\n");
			display(head);
			break;
		case 'N':
		case 'n':
		default:
			printf("\nThe list remains as previous:");
			display(head);
			break;
	}

	return 0;
}


void insertNode(node** list, int data){
	if((*list) == NULL){
		node* element = (node*)malloc(sizeof(node));
		element->data = data;
		(*list) = element;
		tail = element;
	}
	else{
		node* element = (node*)malloc(sizeof(node));
		element->data = data;
		tail->next = element;
		tail = tail->next;
	}
}

void display(node* list){
	if(list == NULL)
		printf("The List is empty.");
	else{
		printf("List:\n");
		for(current = list; current != NULL; current = current->next){
			printf("\n (%d), (%p)\n", current->data, (void*)current);
		}
		printf("\n");
	}
}

void split(node* l, node** front, node** back){
	node* fast;
	node* slow;
	if(l == NULL || l->next == NULL){
		(*front) = l;
		(*back) = NULL;
	}
	else{
		slow = l;
		fast = l->next;
		while(fast != NULL){
			fast = fast->next;
			if(fast != NULL){
				slow = slow->next;
				fast = fast->next;
			}
		}

		(*front) = l;
		(*back) = slow->next;
		slow->next = NULL;
	}
}

node* sorted(node* a, node* b){
	node* ans = NULL;
	if(a == NULL)
		return b;
	else if(b == NULL)
		return a;

	if(a->data <= b->data){
		ans = a;
		ans->next = sorted(a->next, b);
	}else{
		ans = b;
		ans->next = sorted(a, b->next);
	}
	return ans;
}

void sort(node** list){

	node* l = (*list);
	node* a;
	node* b;

	if(l == NULL || l->next == NULL){
		return;
	}

	split(l, &a, &b);

	sort(&a);
	sort(&b);

	(*list) = sorted(a, b);
}

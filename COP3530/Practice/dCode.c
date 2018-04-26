#include <stdio.h>
#include <stdlib.h>

typedef struct node{
	int data;
}node;

void modify_ptr(node** list);

int main(int argc, char** argv){
	node* list = NULL;
	modify_ptr(&list);
	printf(" %d ", list->data);
	return 0;
}

void modify_ptr(node** list){
	node* temp = (node*)malloc(sizeof(node));
	(*list) = temp;
	(*list)->data = 24;
}

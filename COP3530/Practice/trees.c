#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>


// a & b? return a : return b;

#define NEW_LINE printf("\n");

/*
 * Node structure used for (both) Binary Search Trees
 * 	and Heaps.
 * */
typedef struct node{
	int key;
	struct node* leftNode;
	struct node* rightNode;
}node;

void insertBST(node** headNode, int key);//Works
void inOrderTraversal(node* headNode);//Works
void preOrderTraversal(node* headNode);//Works
void levelOrderTraversal(node* headNode);//Works
bool searchBST(node* headNode, int key);//Works
void printMenu();//Works


int main(int argc, char** argv){
	node* headBST = NULL;
	node* headHeap = NULL;
	int size_of_data;
	int* data_arr;
	bool is_BST;
	int i;
	char temp_c;

	printf("%s%s%s%s%s",
			"This Program lets the user create a Binary Search Tree or a Heap.\n",
			"It asks the user to enter the number of nodes to be created.\n",
			"Then it asks the user to enter the data values for the specified number of nodes.\n",
			"After that, it displays a menu with different options to display, search, insert, or quit.\n",
			"------------------------------------------------------------------------------------------------\n");

	printf("Input amount of nodes in the data structure: ");
	scanf(" %d", &i);
	printf("\n");
	
	size_of_data = i;
	data_arr = (int*)malloc(sizeof(int) * size_of_data);
	for(i = 0; i < size_of_data; i++){
		printf("Please input data: ");
		scanf(" %d", &data_arr[i]); NEW_LINE;

	}

	printf("Would you want a Binary Search Tree or a Heap? (B / H): ");
	scanf(" %c", &temp_c);
	switch(temp_c){
		case 'b':
		case 'B':
			is_BST = true;
			break;
		case 'h':
		case 'H':
			printf("Heap algorythm to be implemented");
			return 0;
			is_BST = false;
			break;
	}
	
	for(i = 0; i < size_of_data; i++){
		insertBST(&headBST, data_arr[i]);
	}

	do{
	printMenu();
	scanf(" %c", &temp_c);
	switch(temp_c){
		case 'p':
		case 'P':
			preOrderTraversal(headBST);
			printf("\n");
			break;
		case 'l':
		case 'L':
			levelOrderTraversal(headBST);
			printf("\n");
			break;
		case 's':
		case 'S':
			printf("\nInsert the key to search: ");
			scanf(" %d", &i); NEW_LINE;
			if(is_BST)
				if(searchBST(headBST, i))
					printf("Key %d is in the tree.", i);
				else
					printf("Key %d is NOT in the tree.", i);
			if(!is_BST);//TODO Implement the heap version of search.

			break;
		case 'i':
		case 'I':
			printf("\nInput the integer you wish to insert: ");
			scanf(" %d", &i); NEW_LINE;
			if(is_BST){
				insertBST(&headBST, i);
			}
			else; //TODO implement the version of insert for a node on a heap.
			break;
		case 'q':
		case 'Q':
			temp_c = 'Q';
			break;
	}
	}while(temp_c != 'Q');
	inOrderTraversal(headBST);NEW_LINE;

	return 0;
}

node* newNode(int key){
	node* element = (node*)malloc(sizeof(node));
	element->key = key;
	return element;
}

void insertBST(node** headNode, int key){
	if(*headNode == NULL){
		*headNode = newNode(key);
	}

	if(key < (*headNode)->key){
		insertBST(&(*headNode)->leftNode, key);
	}
	else if(key > (*headNode)->key){
		insertBST(&(*headNode)->rightNode, key);
	}
}

void inOrderTraversal(node* headNode){
	if(headNode != NULL){
		inOrderTraversal(headNode->leftNode);
		printf(" (%d)", headNode->key);
		inOrderTraversal(headNode->rightNode);
	}
}

void preOrderTraversal(node* headNode){
	if(headNode != NULL){
		printf(" (%d)", headNode->key);
		preOrderTraversal(headNode->leftNode);
		preOrderTraversal(headNode->rightNode);
	}
}

int level(node* headNode){
	if(headNode == NULL)
		return 0;
	else{
		int l_level = level(headNode->leftNode);
		int r_level = level(headNode->rightNode);

		if(l_level > r_level)
			return (l_level + 1);
		else return (r_level + 1);
	}
}

void printLevel(node* headNode, int depth){
	if(headNode == NULL){
		return;
	}
	if(depth == 1){
		printf(" (%d)", headNode->key);
	}
	else if(depth > 1){
		printLevel(headNode->leftNode, depth - 1);
		printLevel(headNode->rightNode, depth - 1);
	}
}

void levelOrderTraversal(node* headNode){
	int depth = level(headNode);
	int i;
	for(i = 1; i <= depth; i++)
		printLevel(headNode, i);
}

void printMenu(){
	printf("%s%s%s", 
		"What do you wish to do?\n",
		"Show Preorder Traversa (P), Show Level Order Traversal (L), Search (S), Insert (I), Quit (Q).\n",
		"Please enter a choice: ");
}

bool searchBST(node* headNode, int key){
	if(headNode == NULL)
		return false;
	if(key == headNode->key)
		return true;
	else if(key < headNode->key)
		searchBST(headNode->leftNode, key);
	else if(key > headNode->key)
		searchBST(headNode->rightNode, key);
	else return false;
}

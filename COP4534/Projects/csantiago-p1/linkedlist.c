#include "linkedlist.h"
#include <stdio.h>
#include <stdlib.h>

void addFirst(NodeP* headNode, void* data_value, int data_size){
	NodeP element = (NodeP)malloc(sizeof(NodeT));
	(*element).data = malloc(data_size);

	for(int i = 0; i < data_size; i++){
		*(char*)((*element).data + i) = *(char*)(data_value + i);
	}

	if(*headNode == NULL){
		*headNode = element;
	}
	else{
		(*element).next = *headNode;
		*headNode = element;
	}

}

void addLast(NodeP* headNode, void* data_value, int data_size){
	NodeP current = *headNode;
	NodeP element = (NodeP)malloc(sizeof(NodeT));
	(*element).data = malloc(data_size);

	for(int i = 0; i < data_size; i++){
		*(char*)((*element).data + i) = *(char*)(data_value + i);
	}

	if(*headNode == NULL){
		*headNode = element;
	}
	else{
		while(current->next != NULL)
		current = current->next;
		(*current).next = element;
	}

}

void addAfter(NodeP* currentNode, void* data_value, int data_size){
	NodeP element = (NodeP)malloc(sizeof(NodeT));
	(*element).data = malloc(data_size);

	for(int i = 0; i < data_size; i++){
		*(char*)((*element).data + i) = *(char*)(data_value + i);
	}

	if((*currentNode)->next == NULL){
		(**currentNode).next = element;
	}
	else{
		(*element).next = (*currentNode)->next;
		(**currentNode).next = element;
	}

}

void addAt(NodeP* headNode, int index, void* data_value, int data_size){
	NodeP element = nodeAt(*headNode, index - 1);
	addAfter(&element, data_value, data_size);
}

void deleteNode(NodeP* node){
	free((*node)->data);
	free((*node));
}

void deleteFirst(NodeP* headNode){
	NodeP current = (*headNode);
	(*headNode) = current->next;
	deleteNode(&current);
}

void deleteLast(NodeP* headNode){
	NodeP current = (*headNode);
	NodeP previous = current;
	while(current->next != NULL){
		previous = current;
		current = current->next;
	}
	deleteNode(&current);
	(*previous).next = NULL;
}

void deleteAt(NodeP* headNode, int index){
	NodeP current = nodeAt(*headNode, index - 1);
	NodeP toDelete = current->next;
	(*current).next = toDelete->next;
	deleteNode(&toDelete);
}

void deleteFrom(NodeP* headNode){
	while(*headNode != NULL)
		deleteFirst(headNode);
}

NodeP nodeAt(NodeP headNode, int index){
	NodeP current = headNode;
	int i;
	for(i = 0; i < index - 1; i++){
		current = current->next;
	}
	return current;
}

NodeP removeAt(NodeP headnode, int index){
	NodeP node = nodeAt(headnode, index - 1);
	NodeP toReturn = node->next;
	(*node).next = NULL;
	return toReturn;
}

NodeP removeLast(NodeP headnode){
	NodeP current = headnode;
	NodeP previous = current;
	while(current->next != NULL){
		previous = current;
		current = current->next;
	}
	(*previous).next = NULL;
	return current;
}

void printFrom(NodeP currentNode, void (*function)(void*)){
	while(currentNode != NULL){
		(*function)(currentNode->data);
		currentNode = currentNode->next;
	}
}


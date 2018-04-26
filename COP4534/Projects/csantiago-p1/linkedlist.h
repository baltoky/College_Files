#ifndef LINKEDLIST_H
#define LINKEDLIST_H

typedef struct Node* NodeP;
typedef struct Node{
	void* data;
	struct Node* next;
}NodeT;

/*
 * Adds a node at the begining of the linked list.
 * */
void addFirst(NodeP* headNode, void* data_value, int data_size);

/*
 * Adds a node at the end of the linked list.
 * */
void addLast(NodeP* headNode, void* data_value, int data_size);

/*
 * Adds a node after a node given in the linked list.
 * */
void addAfter(NodeP* currentNode, void* data_value, int data_size);

/*
 * Adds a node directly into a set index on a linked list.
 * */
void addAt(NodeP* headNode, int index, void* data_value, int data_size);

/*
 * Deletes the first node on a linked list.
 * */
void deleteFirst(NodeP* headNode);

/*
 * Deletes the last node on a linked list.
 * */
void deleteLast(NodeP* headNode);

/*
 * Deletes the node exactly in a position of a linked list.
 * */
void deleteAt(NodeP* headNode, int index);

/*
 * Deletes the linked list from a certain node to the end node.
 * */
void deleteFrom(NodeP* headNode);

/*
 * Traverses the linked list and finds the node at the index given.
 * */
NodeP nodeAt(NodeP headNode, int index);

/*
 * Remove a node at a certain index from 0 to listSize - 1.
 * 	Returns the node and unlinks it from the list.
 * */
NodeP removeAt(NodeP headnode, int index);

/*
 * Returns the last node on a list, also unlinks it from the list.
 * */
NodeP removeLast(NodeP headnode);

/*
 * Prints the nodes from the linked list, from a certain node.
 * */
void printFrom(NodeP currentNode, void (*function)(void*));

#endif

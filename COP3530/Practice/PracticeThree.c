#include <stdio.h>
#include <stdlib.h>


void display(int* list, int indeces);

int main(int argc, char** argv){
	int* p;
	int PSIZE = 2;

	if((p = (int*)malloc(PSIZE * sizeof(int))) == NULL){
		printf("ERROR: detected on line 12.");
		return 1;
	}

	int i;
	for(i = 0; i < PSIZE; i++) p[i] = 0;
	display(p, PSIZE);
	printf("\n");

	PSIZE = 10;

	int* tmp;
	if((tmp = (int*)realloc(p, PSIZE * sizeof(int))) == NULL){
		printf("ERROR: detected on line 21");
		free(p);
		return 1;
	}
	p = tmp;

	for(i = 0; i < PSIZE; i++) p[i] = 3;
	display(p, PSIZE);
	printf("\n");

	free(p);
	return 0;
}

void display(int* list, int indeces){
	int i;
	for(i = 0; i < indeces; i++){
		printf(" %d ", list[i]);
	}
}

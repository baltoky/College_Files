#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/*
 * Longest Common Subsequence function.
 * 	parameters:
 * 	char pointer x - char array for the first sequence.
 * 	char pointer y - char array for the sequence to compare to.
 * 	int sizeX - int value for the length of the x array.
 *	int sizeY - int value for the length of the y array.
 * */
int* LCS(char* x, char* y, int sizeX, int sizeY){
	int m = sizeX, n = sizeY; // m - rows, n - collumns.
	int i, j; // loop variables.
	int* c = (int*)malloc(sizeof(int) * m * n); // pointer to store the LCS matrix.
	int* pc = c;

	for(i = 1; i <= m; i++) // sets the first column to 0s.
		pc[i * n] = 0;
	for(j = 1; j <= n; j++) // sets the first row to 0s.
		pc[j] = 0;

	for(i = 1; i <= m; i++){
		for(j = 1; j <= n; j++){
			// if the sequence letters are equal then add one to the matrix.
			if(x[i - 1] == y[j - 1]) 
				pc[i * n + j] = pc[(i - 1) * n + (j - 1)] + 1;
			// else if the top index is greater or equal to the left index, make current index equal to 
			// 	top index.
			else if(pc[(i - 1) * n + j] >= pc[i * n + (j - 1)]) 
				pc[i * n + j] = pc[(i - 1) * n + j];
			// else make the current index equal to the left index.
			else
				pc[i * n + j] = pc[i * n + (j - 1)];
		}
	}
	// return the pointer to the LCS matrix.
	return c;
}

void readLCSstrings(char** x, char** y, int* a, int* b){
	FILE *fp = fopen("twoSequences.txt", "r");

	// allocation and reading of the first string in the file.
	fscanf(fp, "%d", a); // reads the size of the string first.
	(*x) = (char*) malloc((*a) + 1); // allocation of memory to prepare for recieving the string, + 1 for the string end char.
	fscanf(fp, "%s", (*x)); // reads the string from the file.
	(*x)[(*a)] = '\0'; // initializes the string end char.

	// allocation and reading of the second string.
	fscanf(fp, "%d", b); // reads the size of the string first.
	(*y) = (char*) malloc((*b) + 1); // allocation of memory to prepare for recieving the string, + 1 for the string end char.
	fscanf(fp, "%s", (*y)); // reads the string from the file.
	printf("%s\n", (*y)); 
	(*y)[(*b)] = '\0'; // initializes the string end char.
	printf("%s\n", (*y));

	fclose(fp); // closes the file.
}

void printLCS(int* c, int m, int n){
	int i, j;
	for(i = 1; i <= m; i++){
		for(j = 0; j < n; j++)
			printf("[%i]", c[i * n + j]);
		printf("\n");
	}

}

int main(int argc, char** argv){
	char *x, *y;
	int a, b;

	readLCSstrings(&x, &y, &a, &b);

	int* c = LCS(x, y, a, b);
	printLCS(c, a, b);

	printf("\n");

	char h[] = {'r', 't', 's', '\0'};
	char k[] = {'r', 'p', 'g', '\0'};

	int* t = LCS(h, k, 3, 3);
	printLCS(t, 3, 3);

	return 0;
}

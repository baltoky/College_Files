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
	int i, j, k; // loop variables.
	int* c = (int*)malloc(sizeof(int) * (m + 1) * (n + 1)); // pointer to store the LCS matrix.
	int* pc = c;

	for(i = 0; i <= m; i++)
		for(j = 0; j <= n; j++)
			pc[i * n + j] = 0;

	for(i = 1; i <= m; i++){
		for(j = 1; j <= n; j++){
			// if the sequence letters are equal then add one to the matrix.
			if(x[i - 1] == y[j - 1]) {
				pc[i * n + j] = 1 + pc[(i - 1) * n + (j - 1)];
			}
			// else if the top index is greater or equal to the left index, make current index equal to 
			// 	top index.
			else if(pc[(i - 1) * n + j] >= pc[i * n + (j - 1)]) {
				pc[i * n + j] = pc[(i - 1) * n + j];
			}
			// else make the current index equal to the left index.
			else{
				pc[i * n + j] = pc[i * n + (j - 1)];
			}
		}
		//pc[i * n] = 0;
	}
	// return the pointer to the LCS matrix.
	return c;
}

void printLCS(int* c, char* x, char* y, int m, int n){

}

void readLCSstrings(char** x, char** y, int* m, int* n){
	FILE *fp = fopen("twoSequences.txt", "r");

	// allocation and reading of the first string in the file.
	fscanf(fp, "%d", m); // reads the size of the string first.
	(*x) = (char*) malloc((*m) + 1); // allocation of memory to prepare for recieving the string, + 1 for the string end char.
	fscanf(fp, "%s", (*x)); // reads the string from the file.
	(*x)[(*m)] = '\0'; // initializes the string end char.

	// allocation and reading of the second string.
	fscanf(fp, "%d", n); // reads the size of the string first.
	(*y) = (char*) malloc((*n) + 1); // allocation of memory to prepare for recieving the string, + 1 for the string end char.
	fscanf(fp, "%s", (*y)); // reads the string from the file.
	(*y)[(*n)] = '\0'; // initializes the string end char.

	fclose(fp); // closes the file.
}

void printLCSMatrix(int* c, char* x, char* y, int m, int n){
	int i, j;
	int* pc = c;
	char* a = x, *b = y;

	printf("   [0]");
	for(i = 0; i < n; i++)
		printf("[%c]", a[i]);
	for(i = 0; i <= m; i++){
		if(i == 0){
			printf("\n[0]");
		}
		else
			printf("[%c]", b[i-1]);

		for(j = 0; j <= n; j++)
			printf("[%d]", pc[i * n + j]);
		printf("\n");
	}

}

int main(int argc, char** argv){
	char *x, *y;
	int a, b;

	readLCSstrings(&x, &y, &a, &b);

	int* c = LCS(x, y, a, b);
	printLCSMatrix(c, x, y, a, b);

	char h[] = {'r', 't', 's', '\0'};
	char k[] = {'r', 'p', 'g', '\0'};

	int* t = LCS(h, k, 3, 3);
	printLCSMatrix(t, h, k, 3, 3);

	return 0;
}

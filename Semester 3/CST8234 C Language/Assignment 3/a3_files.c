/* PROGRAM: A more advanced card-shuffling program
   AUTHOR: Brady McIntosh
   DATE: 3 Dec 2018
   PURPOSE: To handle file-management functions
*/

#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <string.h>

#include "a3.h"

/* public */
FILE * getFile(char []);
void prnArgs(FILE*, int, char *[]);
void closeFile(FILE *);

FILE * getFile(char fname[]) {
	return fopen(fname, "w+b");
	
}

void prnArgs(FILE *fp, int argc, char *argv[]){
	int i;	
	fprintf (fp, "Number of arguments = %d\r\n", argc);
	for( i = 0; i < argc; i++ ) {
		/* printf("Argv[%d] = %s\n", i, argv[i]); */
		fprintf(fp, "Argv[%d] = %s\r\n", i, argv[i]);
	}
	fprintf(fp, "\r\nArguments printing completed successfully");	
}

void closeFile(FILE *fp) {
	fclose(fp);
}
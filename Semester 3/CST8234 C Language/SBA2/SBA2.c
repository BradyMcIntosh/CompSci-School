#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

#define OPTIONS "./program name, args..." 
void dispErr(char* , char*);
void prnArgs(FILE*, int, char *[]);
void parseArgs(FILE*, int, char *[]);
/*void Add( your argument);*/
/*void Avg( your argument);*/

char A[8]={'a','b','c','d','e','f','g','h'};
char *W[8]={"Apple", "Banada", "Cherry", "Durian", "Eggplant",
"Fig", "Green Onion", "Haricot"};

 
int main( int argc, char *argv[] ) {

FILE *fp;
	fp = fopen("SBA2_Text.txt", "wb+");
	if (fp==NULL)
	{
		printf("\nFile SBA2_Text.txt does not open");
		exit(EXIT_FAILURE);
		/*return 0;*/
	}
	else {
		printf("File: SBA2_Text.txt opened succcessfully\n");
	}
prnArgs(fp, argc, argv);

if (argc < 2){
	dispErr(argv[0], OPTIONS);	
	printf("\nEnter at least 2 parameters");
	return 0;
}
else {
	parseArgs(fp, argc, argv);
}

	
fclose(fp);
return 0;
}

/*This function prints the arguments by taking three arguments: (i)file pointer (ii) argc and (iii)*argv[] */
void prnArgs(FILE* fp, int argc, char *argv[]){
	int i;	
	fprintf (fp, "\r\nNumber of arguments = %d", argc);
	for( i = 0; i < argc; i++ ) {
		fprintf(fp, "\r\nArgv[%d] = %s", i, argv[i]);
	}
}

void parseArgs(FILE* fp, int argc, char* argv[]) {
	
	int i, j, m;
	
	printf("\n Check file SBA_Text.txt for details");
	
	for(i = 1; i < argc; i++) {
		fprintf(fp, "\r\n\r\nLength of argument: %lu", strlen(argv[i]));
		fprintf(fp, "\r\n--------------------------------");
		for(j = 0; j < (int)strlen(argv[i]); j++) {
			if(isalpha(argv[i][j])) {
				fprintf(fp, "\r\nThe letter(s) = %c", argv[i][j]);
				for(m = 0; m < 8; m++) {
					if(argv[i][j] == A[m]) {
						fprintf(fp, "\r\n\tWith letter %c, word is: %s",
								argv[i][j], W[m]);
						
					}
				}
			}
			else if(!isdigit(argv[i][j])) {
				fprintf(fp, "\r\nThe symbol is = %c", argv[i][j]);
			}
		}
	}
}

void dispErr(char* a1, char* b1){
	printf("\nYour program %s should have following option parameter format:\n%s", a1, b1);

}
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

#define OPTIONS1 "./program name, option, num1, num2, num3, num4"
void prnArgs(FILE*, int, char *[]);
void parseArgs(FILE*, int, char* []);

/*This program will help you understand and finish your Lab07*/
/*But this program has some planned mistake or formatting error*/
/*Your program should match SAMPLE EXPECTED OUTPUT */ 
/*Your modified program should use the argument format as defined in OPTIONS1*/

/* For the functions, modify your code so that you can do the followings: */
/* Option =1 */
/*void Double(your argument list); double each of the last 4 (decimal) arguments, write them to files as stated in the sample output*/

/* Option =2 */
/*void Half(your argument list); Half each of the last 4 (decimal) arguments, write them to files as stated in the sample output*/

 
int main( int argc, char *argv[] ) {
	
	if(argc >= 2) {
		FILE *fp;
		fp = fopen(argv[1], "w+b");
		if (fp==NULL)
		{
			printf("Output file could not be opened.\n");
			return 0;
		}
		
		printf("Output file opened succcessfully\n");
		prnArgs(fp, argc, argv);
		parseArgs(fp, argc, argv);
		fclose(fp);
	}
	else {
		printf("Too few arguments - need at least 2");
	}
	
	return 0;
}

/*This function prints the arguments by taking three arguments: (i)file pointer (ii) argc and (iii)*argv[] */
void prnArgs(FILE* fpL7, int argc, char *argv[]){
	int i;	
	fprintf (fpL7, "Number of arguments = %d\r\n", argc);
	for( i = 0; i < argc; i++ ) {
		/* printf("Argv[%d] = %s\n", i, argv[i]); */
		fprintf(fpL7, "Argv[%d] = %s\r\n", i, argv[i]);
	}
	fprintf(fpL7, "\r\nArguments printing completed successfully");
	
}

void parseArgs(FILE* fp, int argc, char* argv[]) {
	int i, j;
	for(i = 2; i < argc; i++) {
		fprintf(fp, "\r\nLength of argument %d = %lu", i, strlen(argv[i]));
		for(j = 0; j < (int)strlen(argv[i]); j++) {
			if(isalpha(argv[i][j])) {
				if(j > 0 && isalpha(argv[i][j-1])) {
					fprintf(fp, "%c", argv[i][j]);
				}
				else {
					fprintf(fp, "\r\nThe Letter(s) = %c", argv[i][j]);
				}
			}
			else if(isdigit(argv[i][j])) {
				continue;
			}
			else {
				fprintf(fp, "\r\nThe Symbol is = %c", argv[i][j]);
			}
			
		}
		fprintf(fp, "\r\n");
	}
}
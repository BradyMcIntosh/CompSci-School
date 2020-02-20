#include <stdio.h>
#include <stdlib.h>
/*#include <string.h>
#include <ctype.h>*/

#define OPTIONS1 "./program name, option, num1, num2, num3, num4" 
void dispErr(char* , char*);
void prnArgs(FILE*, int, char *[]);
void Double(FILE*, int, char* []);
void Half(FILE*, int, char* []);

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
	
	FILE *fpL7;
		fpL7 = fopen("Lab7F.txt", "w+b");
		if (fpL7==NULL)
		{
			printf("\nFile Lab7.txt does not open");
			exit(EXIT_FAILURE);
			/*return 0;*/
		}
		else {
			printf("Output file opened succcessfully\n");
		}
	prnArgs(fpL7, argc, argv);

	if (argc != 6){
		dispErr(argv[0], OPTIONS1);	
		printf("\nEnter exactly 6 parameters.");
		return 0;
	}

	if (atoi(argv[1])==1)
	{
		printf("\nOption: %s --> Double Selected", argv[0]);
		fprintf(fpL7, "\r\nOption: %s --> Double Selected", argv[1]);
		Double(fpL7, argc, argv);
	}
	else if (atoi(argv[1])==2)
	{
		printf("\nOption: %s --> Half Selected", argv[1]);
		fprintf(fpL7, "\r\nOption: %s --> Half Selected", argv[0]);
		Half(fpL7, argc, argv);
	}
	else {
		dispErr(argv[0], OPTIONS1);
		printf("\nOption must be either 1 or 2.");
	}
		
	fclose(fpL7);
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

void Double(FILE* fpL7, int argc, char* argv[]){
	
	int i;
	float sum;
	fprintf(fpL7, "\r\n\r\n--------------------------");
	for(i = 2; i < argc; i++) {
		sum = atof(argv[i]);
		if(sum == 0) {
			dispErr(argv[0], OPTIONS1);
			printf("\r\nnum1-4 must be numbers and may not be 0");
			return;
		}
		sum *= 2;
		fprintf(fpL7, "\r\nDoubled argv[%d] = %.1f", i, sum);
	}
	
	fprintf(fpL7, "\r\n--------------------------");
	fprintf(fpL7, "\r\nDouble calculation completed successfully");
	printf("\n--------------------------");
	printf("\nOpen output file for detailed result");
}

void Half(FILE* fpL7, int argc, char* argv[]){
	
	int i;
	float sum;
	fprintf(fpL7, "\r\n\r\n--------------------------");
	for(i = 2; i < argc; i++) {
		sum = atof(argv[i]);
		if(sum == 0) {
			dispErr(argv[0], OPTIONS1);
			printf("\r\nnum1-4 must be numbers and may not be 0");
			return;
		}
		sum /= 2;
		fprintf(fpL7, "\r\nHalf argv[%d] = %.1f", i, sum);
	}
	
	fprintf(fpL7, "\r\n--------------------------");
	fprintf(fpL7, "\r\nHalf calculation completed successfully");
	printf("\n--------------------------");
	printf("\nOpen output file for detailed result");
}

void dispErr(char* a1, char* b1){
	printf("\nYour program %s should have following option parameter format:\n%s", a1, b1);

}
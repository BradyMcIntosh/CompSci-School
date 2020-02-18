/* PROGRAM:	numbers.c
   AUTHOR: 	Brady McIntosh
   DATE: 	14 Sep 2018
   PURPOSE: 	Accept a phone number from the user and format it correctly.
   LEVEL OF DIFFICULTY: 3
   CHALLENGES:	Navigating the weirdness of C arrays, chars and "strings"
   HOURS SPENT: 2.5
*/

#include <stdio.h>
#include <ctype.h>
#include <string.h>

#define GOOD	0
#define ERR		1
#define EXIT	2

int main (void) 
{

	char  num[8];
	int error;
	
	do {
		unsigned int i;
		error = GOOD;
		
		printf("Enter a phone number: ");
		scanf("%s", num);
		
		/* check if all characters are digits, error if not */
		for (i = 0; i < strlen(num); i++) {
			if (!isdigit(num[i])) {
				printf("ERROR: Input is not a number (#%d)\n\n", i);
				error = ERR;
				break;
			}
		}
		
		/* exit program if the input is "0" */
		if (!error && strlen(num) == 1 && num[0] == '0') {
			error = EXIT;
		}
		/* check if too many digits were entered */
		else if (!error && strlen(num) > 7) {
			printf("ERROR: Number too long\n\n");
			error = ERR;
		}
		/* check if too few digits were entered */
		else if (!error && strlen(num) < 7) {
			printf("ERROR: Number too short\n\n");
			error = ERR;
		}
		/* check if too many digits were entered */
		else if (!error && (num[0] == '0' || num[0] == '1')) {
			printf("ERROR: First digit cannot be %c\n\n", num[0]);
			error = ERR;
		}
		
		if (!error) {
			char temp[4];
			unsigned int i;
			
			/* copy the last 4 digits */
			for (i = 3; i < 7; i++) {
				temp[i-3] = num[i];
			}
			
			/* add a hyphen after the 3rd character */
			num[3] = '-';
			
			/* put the last 4 numbers back in, after the hyphen */
			for (i = 4; i < 8; i++) {
				num[i] = temp[i-4];
			}
			
			/* print the new, hyphenated number */
			printf("\nYour formatted number is: %s\n\n", num);
		}
		
	} while (error != 2);
	
	printf("Goodbye.\n");
	
	return 0;
}
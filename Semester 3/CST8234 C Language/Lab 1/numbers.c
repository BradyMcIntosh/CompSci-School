/* PROGRAM:	numbers.c
   AUTHOR: 	Brady McIntosh
   DATE: 	12 Sep 2018
   PURPOSE: 	Print numbers from 1 to 100 and give some information.
   LEVEL OF DIFFICULTY: 2
   CHALLENGES: Getting used to syntax/library functions
   HOURS SPENT: 30min
*/

#include <stdio.h>

int main (void) {

    short unsigned int i;

    for(i = 0; i < 100; i++) {
        
        printf("%d\n",i);
        
        if(((i % 3 == 0) && (i % 7 == 0)) && i != 0) {
            
            printf("I'm a multiple of 3 and 7!\n");
        }
        else if((i % 3 == 0) && (i != 0)) {
            
            printf("I'm a multiple of 3!\n");
        }
        else if((i % 7 == 0) && (i != 0)) {
            
            printf("I'm a multiple of 7!\n");
        }
    }

    return 0;
}
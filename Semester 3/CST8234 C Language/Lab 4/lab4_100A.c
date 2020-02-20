/* PROGRAM: A door-opening-and-closing program.
   AUTHOR: Brady McIntosh
   DATE: 6 Oct 2018
   PURPOSE: To simulate going down a hallway of 100 doors and closing or opening each one.
   LEVEL OF DIFFICULTY: 2
   CHALLENGES: 
   HOURS SPENT: 2.5
*/

#include <stdio.h>
#include <stdlib.h>

#define SIZE 100
#define BASE 10

int toggle_door(char a[]);
int door_state(char a[]);

int toggle_door(char a[]) {
	
	int i, j;
	
	for(i = 1; i <= SIZE; i++) {
		
		for(j = i-1; j < SIZE; j+=i) {
			
			if(a[j] == '0') {
				a[j] = '1';
			}
			else {
				a[j] = '0';
			}
		}
	}
	
	return 0;
}

int door_state(char a[]) {
	
	int i;
	
	printf("\n\n");
	
	for(i = 0; i <= BASE; i++) {
		
		printf("%4d", i);
	}
	
	printf("\n");
	
	for(i = 0; i < BASE + 1; i++) {
		
		printf("----");
	}
	
	for(i = 0; i < SIZE; i++) {
		
		if(i % BASE == 0) {
			printf("\n%4d ", i);
		}
		
		printf("%3c ", a[i] == '0'? 'O': 'C');
	}
	
	printf("\n\n");
	
	return 0;
	
}

int main() {
	
	int i;
	
	char doors[SIZE];
	
	for(i = 0; i < SIZE; i++) {
		doors[i] = '0';
	}
	
	toggle_door(doors);
	door_state(doors);
	
	return 0;
}
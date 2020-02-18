/* PROGRAM: A simple card-shuffling program.
   AUTHOR: Brady McIntosh
   DATE: 20 Sep 2018
   PURPOSE: To generate a set of "hands" of cards
   LEVEL OF DIFFICULTY: 3
   CHALLENGES: 
   HOURS SPENT: 4-5
*/

#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <string.h>
#include <time.h>

int randcard(unsigned short int arr[], unsigned short int size);

int randcard(unsigned short int arr[52], unsigned short int size) {
	unsigned short int c, match, i;
	
	do {
		match = 0;
		c = rand() % 52;
		
		for(i = 0; i < size; i++) {
			
			if(arr[i] == c) {
				match = 1;
				break;
			}
		}
		
	} while(match);
	
	
	return c;
}

void printcard(int c) {
	
	char face, suit;
	unsigned int ten = 0;
	
	switch(c % 13) {
		case 0: 
			face = 'A';
			break;
		case 1:
			face = '2';
			break;
		case 2:
			face = '3';
			break;
		case 3:
			face = '4';
			break;
		case 4:
			face = '5';
			break;
		case 5:
			face = '6';
			break;
		case 6:
			face = '7';
			break;
		case 7:
			face = '8';
			break;
		case 8:
			face = '9';
			break;
		case 9:
			face = '1';
			ten = 1;
			break;
		case 10:
			face = 'J';
			break;
		case 11:
			face = 'Q';
			break;
		case 12:
			face = 'K';
			break;
	}
	
	switch(c / 13) {
		case 0: 
			suit = 'H';
			break;
		case 1:
			suit = 'S';
			break;
		case 2:
			suit = 'D';
			break;
		case 3:
			suit = 'C';
			break;
	}
	
	if(ten) {
		printf("%c0%c", face, suit);
	}
	else {
		printf("%c%c", face, suit);
	}
}

int main() {
	
	char input[10];
	unsigned short int deck[52];
	unsigned short int players, cards, i, size;
	
	srand(time(0));
	
	do {		
		printf("Enter the number of players (1-4): ");
		scanf("%s",input);
		
		players = strtol(input, NULL, 10);
		
	} while (players < 1 || players > 4 );
	
	do {
		
		printf("Enter the number of cards per player (52 total max): ");
		scanf("%s",input);
		
		cards = strtol(input, NULL, 10);
		
	} while (cards < 1 || cards * players > 52);
	
	for(i = 0; i < cards * players; i++) {
		deck[i] = randcard(deck, size);
		size++;
	}
	
	printf("Hand printout: \n");
	
	for(i = 0; i < cards * players; i++) {
		
		if(i % cards == 0) {
			printf("\tHand %d: ", (i / cards) + 1);
		}
		
		printcard(deck[i]);
		printf(" ");
		/*printf(" card#%d ", i % cards + 1);*/
		
		if(i % cards == cards -1) {
			printf("\n");
		}
	}
	
	return 0;
}
/* PROGRAM: A more advanced card-shuffling program
   AUTHOR: Brady McIntosh
   DATE: 3 Dec 2018
   PURPOSE: To handle card-management functions\
*/

#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <string.h>
#include <time.h>

#include "a3.h"

/* public */
struct card ** buildDeck();
char * cardToStr(struct card);
void shuffleCards(struct card**);

/* private */
char getRank(int);
int getPoint(int);
char getSuit(enum suit);

struct card ** buildDeck() {
	
	struct card **cards = (struct card**) malloc(sizeof(struct card*) * SUIT_NUM);
	int i, j;
	
	srand(time(NULL));
	
	for(i = 0; i < SUIT_NUM; i++) {
		
		cards[i] = (struct card*) malloc(sizeof(struct card) * SUIT_SIZE);
		
		for(j = 0; j < SUIT_SIZE; j++) {
			
			cards[i][j].csuit = i;
			cards[i][j].crank = getRank(j);
			cards[i][j].cpoint = getPoint(j);
			
			/* the program doesn't work without this, for some reason */
			/*printf("", cardToStr(cards[i][j]));*/
		}
	}
	
	return cards;
}

char * cardToStr(struct card pc) {
	
	char *str = malloc(sizeof(char) * 2);
	
	str[0] = pc.crank;
	str[1] = getSuit(pc.csuit);
	
	return str;
}

void shuffleCards(struct card** deck) {
	
	int i, j, ri, rj;
	
	for(i = 0; i < SUIT_NUM; i++) {
	
		for(j = 0; j < SUIT_SIZE; j++) {
			
			struct card temp;
			ri = rand() % SUIT_NUM;
			rj = rand() % SUIT_SIZE;
			
			temp = deck[i][j];
			deck[i][j] = deck[ri][rj];
			deck[ri][rj] = temp;
		}
	}
	
}

char getRank(int numR) {
	
	char face;
	
	switch(numR) {
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
			face = 'T';
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
	
	return face;
}

int getPoint(int numR) {
	
	int point = numR + 1;
	
	return point;
}


/* if only face cards get points */
/*char getPoint(int numR) {
	
	switch(numR) {
		case 0: 
			point = 4;
			break;
		case 12:
			point = 3;
			break;
		case 11:
			point = 2;
			break;
		case 10:
			point = 1;
			break;
		default:
			point = 0;
			break;
	}
	
	return point;
}*/

char getSuit(enum suit numS) {
	
	char suit;
	
	switch(numS) {
		case S: 
			suit = 'S';
			break;
		case H:
			suit = 'H';
			break;
		case C:
			suit = 'C';
			break;
		case D:
			suit = 'D';
			break;
	}
	
	return suit;
}
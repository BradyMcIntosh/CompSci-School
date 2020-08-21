/* PROGRAM: A more advanced card-shuffling program
   AUTHOR: Brady McIntosh
   DATE: 3 Dec 2018
   PURPOSE: To print hands of cards to a file, based on command-line parameters
   LEVEL OF DIFFICULTY: 3
   CHALLENGES: 
   HOURS SPENT: 5
*/

#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <string.h>

#include "a3.h"

#define ARGS "[executable] [output (.txt)] [number of hands (int)] [number of cards per hand (int)]"

/* imported from a3_cards.c */
extern struct card ** buildDeck();
extern char * cardToStr(struct card);
extern void shuffleCards(struct card**);

/* imported from a3_files.c */
extern FILE * getFile(char []);
extern void prnArgs(FILE*, int, char *[]);
extern void closeFile(FILE *);


int main( int argc, char *argv[] ) {
	
	int i, j, cardTotal, handPoints, totalPoints;
	FILE *fp;
	
	if(argc == 4) {
		
		struct card **deck = buildDeck();
		
		/*for(i = 0; i < SUIT_NUM; i++) {
			for(j = 0; j < SUIT_SIZE; j++) {
				
				printf("%s, ", cardToStr(deck[i][j]));
				
			}
			printf("\n");
		}*/
		
		shuffleCards(deck);
		shuffleCards(deck);
		
		/*for(i = 0; i < SUIT_NUM; i++) {
			for(j = 0; j < SUIT_SIZE; j++) {
				
				printf("%s, ", cardToStr(deck[i][j]));
				
			}
			printf("\n");
		}*/
		
		if(atoi(argv[2]) > 0 && atoi(argv[2]) <= SUIT_NUM) {
			
			if(atoi(argv[3]) > 0 && atoi(argv[3]) <= DECK_SIZE / atoi(argv[2])) {
				
				fp = getFile(argv[1]);
				if(fp == NULL) {
					printf("Error: Output file could not be opened.\n");
					return 0;
				}
				printf("Output file was opened successfully.\n");
				printf("See %s for the results.\n", argv[1]);
				
				cardTotal = atoi(argv[2]) * atoi(argv[3]);
				totalPoints = 0;
				
				for(i = 0; i < atoi(argv[2]); i++) {
					
					fprintf(fp, "\r\nHAND %d: \r\n\t", i);
					handPoints = 0;
					
					for(j = 0; j < atoi(argv[3]); j++) {
						
						if(j + (i * atoi(argv[3])) <= cardTotal) {
							
							int cardr, cardi, cardj;
							cardr = j + (i * atoi(argv[3]));
							cardi = cardr / SUIT_SIZE;
							cardj = cardr % SUIT_SIZE;
							
							fprintf(fp, "%s, ", cardToStr(deck[cardi][cardj]));
							handPoints += deck[cardi][cardj].cpoint;
						}
					}
					fprintf(fp, "points: %d", handPoints);
					totalPoints += handPoints;
				}
				fprintf(fp, "\r\n\r\nTotal points across all hands: %d", totalPoints);
				
			}
			else {
				printf("Error: Cards per hand must be greater than 0 and may not in total exceed 52.");
			}
		}
		else {
			printf("Error: Number of hands must be between 1 and 4.\n");
			return 0;
		}
	
	}
	else {
		printf("Error: Must be exactly 4 arguments, in this format: %s", ARGS);
	}
	
	closeFile(fp);
	
	return 0;
}
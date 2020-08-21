/* PROGRAM: Assign2, a phonebook manager
   AUTHOR: Brady McIntosh
   DATE: 12 Nov, 2018
   PURPOSE: Managing menu and search functions
   LEVEL OF DIFFICULTY: 4
   CHALLENGES: Manipulating pointers & memory management
   HOURS SPENT: 8
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "assign2.h"

#define ANUM 5
#define PNUM 10

extern struct Area* addArea(struct Area*);
extern struct PEntry* addPEntry(struct PEntry*, struct Area*);

extern struct PEntry* editPEntry(struct PEntry*, int);
extern struct PEntry* deletePEntry(struct PEntry*, int);

struct PEntry* cyclePEntry(struct PEntry*, int);
int searchType(struct PEntry*);
int searchNumPEntry(struct PEntry*, char[], char[], char[]);
int searchNamePEntry(struct PEntry*, char []);

int printArea(struct Area*);
void printSingleArea(struct Area*);
int printPEntry(struct PEntry*);
void printSinglePEntry(struct PEntry*);

void flushbuffer() {
	int c;
	do {
		c = getchar();
	} while (c != '\n' && c != EOF);
}

int printArea(struct Area* curr) {
	
	int i = 0;
	
	do {
		printf("  %d:", i);
		printSingleArea(curr);
		i++;
		curr = curr->nextArea;
		
	} while (NULL != curr);
	
	return i;
}

void printSingleArea(struct Area* curr) {
	
	char occ[4];
		
	if(NULL == curr->nextArea) {
		strcpy(occ, "no");
	}
	else {
		strcpy(occ, "yes");
	}
	
	printf("\tregion: %s code: %s hasnext: %s\n",
		curr->region, curr->code, occ);
}

int printPEntry(struct PEntry* curr) {
	
	int i = 0;
	
	do {
		printf("  %d:", i);
		printSinglePEntry(curr);
		i++;
		curr = curr->nextPEntry;
		
	} while (NULL != curr);
	
	return i;
}

void printSinglePEntry(struct PEntry* curr) {
	
	char occ[4];
		
	if(NULL == curr->nextPEntry) {
		strcpy(occ, "no");
	}
	else {
		strcpy(occ, "yes");
	}
	
	printf("\tNumber: (%s)-%s-%s - Name: %s %s - Hasnext: %s\n", 
		curr->code, curr->exc, curr->sub, curr->fname, curr->lname, occ);
}

/*Returns the list item from the passed list in the passed position*/
struct PEntry* cyclePEntry(struct PEntry* curr, int cyc) {
	
	int i;
	
	/* printf("cycles: %d\n", cyc); */
	
	for(i = 0; i < cyc; i++) {
		/*
		printf("  %d:", i);
		printSinglePEntry(curr);
		*/
		curr = curr->nextPEntry;
	}
	
	return curr;
}

int searchCodeArea(struct Area* area, char code[CODELEN+1]) {
	
	 while(NULL != area) {

		if(!strcmp(area->code, code)) {
			printf("Match found!\n");
			return 1;
		}
		area = area->nextArea;
	}
	
	printf("No element with that area code.\n");
	return 0;
}

/* Handles user prompt for search terms, and calls appropriate search function */
int searchType(struct PEntry* curr) {
	
	char usrChoice;
	char usrCode[CODELEN+1], usrNum[EXCLEN+SUBLEN+4];
	char usrExc[EXCLEN+1] = {0}, usrSub[SUBLEN+1] = {0};
	char usrName[NAMELEN+1];
	int ret = 0, valid = 0, i;
	
	/* prompt for search terms - number or name */
	do {
		valid = 0;
		printf("Specify the search terms: \n\t1. Phone Number \n\t2. Last Name\n");
		scanf("%c", &usrChoice);
		flushbuffer();
		
		if(usrChoice == '1' || usrChoice == '2') {
			valid = 1;
		}
	} while (!valid);
	
	/* phone number search */
	if(usrChoice == '1') {
		
		/* prompt and confirm area code from user */
		do {
			valid = 0;
			printf("Please enter the area code: ");
			scanf("%s", usrCode);
			/*printf("You entered: %s\n", usrCode);*/
			flushbuffer();
			
			if(strlen(usrCode) == 3) {
				
				if(usrCode[0] != '1' && usrCode[0] != '0') {
					
					if(strtol(usrCode, NULL, 10) != 0) {
						valid = 1;
					}
					else {
						printf("The area code must be a number.\n");
					}
				}
				else {
					printf("The area code cannot begin with a 1 or 0.\n");
				}
			}
			else {
				printf("The area code must be 3 digits.\n");
			}
		} while (!valid);
		
		/* prompt and confirm phone number from user */
		do {
			valid = 0;
			printf("Please enter the seven-digit phone number: ");
			scanf("%s", usrNum);
			/*printf("You entered: %s\n", usrNum);*/
			flushbuffer();
			
			if(strlen(usrNum) == EXCLEN + SUBLEN) {
				
				if(usrNum[0] != '1' && usrNum[0] != '0') {
					
					if(strtol(usrNum, NULL, 10) != 0) {
						valid = 1;
					}
					else {
						printf("The phone number must be a number.\n");
					}
				}
				else {
					printf("The exchange number cannot begin with a 1 or 0.\n");
				}
			}
			else {
				printf("The phone number must be %d digits.\n", EXCLEN+SUBLEN);
			}
		} while (!valid);
		
		/* copy user input into exc and sub numbers */
		for(i = 0; i < 3; i++) {
			usrExc[i] = usrNum[i];
		}
		for(i = 3; i < 7; i++) {
			usrSub[i-3] = usrNum[i];
		}
		
		/*printf("Formatted: (%s)-%s-%s\n", usrCode, usrExc, usrSub);*/
		ret = searchNumPEntry(curr, usrCode, usrExc, usrSub);
	}
	else {
		
		do {
			printf("Please enter the name: ");
			scanf("%s", usrName);
			/*printf("You entered: %s\n", usrName);*/
			flushbuffer();
			
			if(strlen(usrName) > NAMELEN) {
				printf("Name must be less than %d characters.\n", NAMELEN);
			}
			else if(strlen(usrName) < 2) {
				printf("Name must be more than 2 characters.\n");
			}
		} while (strlen(usrName) > NAMELEN || strlen(usrName) < 2);
		
		ret = searchNamePEntry(curr, usrName);
	}
	
	
	return ret;
	
}

/*Returns the position of the specified list item in the specified list,
	or -1 if no matching item is found*/
int searchNumPEntry(struct PEntry* curr, 
	char code[CODELEN+1], char exc[EXCLEN+1], char sub[SUBLEN+1]) {
		
	int codev, excv, subv, i = 0;
	
	/* sequential search for matching strings in current struct */
	do {
		codev = 0;
		excv = 0;
		subv = 0;
		
		if(!strcmp(code, curr->code)) {
			codev = 1;
			
			if(!strcmp(exc, curr->exc)) {
				excv = 1;
				
				if(!strcmp(sub, curr->sub)) {
					subv = 1;
					break;
				}
			}
		}
		curr = curr->nextPEntry;
		i++;
	} while(NULL != curr);
	
	/* Maybe rearrange so it lists all elements that don't match */
	
	if(!codev) {
		printf("No element with that area code was found.\n");
	}
	else if(!excv) {
		printf("No element with that exchange code was found.\n");
	}
	else if(!subv) {
		printf("No element with that subscription code was found.\n");
	}
	else {
		printf("Match found!\n");
		return i;
	}
	
	return -1;
}

/*Returns the position of the specified list item in the specified list,
	or -1 if no matching item is found*/
int searchNamePEntry(struct PEntry* curr, char name[NAMELEN+1]) {
	
	int i = 0;
	
	do {
		if(!strcmp(curr->lname, name)) {
			printf("Match found!\n");
			return i;
		}
		curr = curr->nextPEntry;
		i++;
	} while(NULL != curr);
	
	printf("No element with that last name was found.\n");
	return -1;
}

int main() {
	
	struct Area* ahead = NULL;
	struct PEntry* phead = NULL;
	char usrChoice;
	int selNum;
	
	/*printf("ahead: %p, size: %lu \nphead: %p, size: %lu\n",
	(void*) ahead, sizeof(*ahead), (void*) phead, sizeof(*phead));*/
	
	do {
		printf("What would you like to do?\n"
			"\t1. Add an Area\n"
			"\t2. Add a Phonebook Entry\n"
			"\t3. Edit a Phonebook Entry\n"
			"\t4. Delete a Phonebook Entry\n"
			"\t5. Find a Phonebook Entry\n"
			"\tQ. Quit\n");
		
		scanf("%c", &usrChoice);
		flushbuffer();
		/*printf("You selected option %c\n", usrChoice);*/
		
		if(usrChoice == '1') {
			ahead = addArea(ahead);
			if(ahead != NULL) {
				printArea(ahead);
			}
		}
		else if(usrChoice == '2') {
			if(ahead != NULL) {
				phead = addPEntry(phead, ahead);
				if(phead != NULL) {
					printPEntry(phead);
				}
			}
			else {
				printf("An area must be created first.\n");
			}
		}
		else if(usrChoice == '3') {
			if(NULL!= phead) {
				phead = editPEntry(phead, searchType(phead));
			}
			else {
				printf("There are no entries to edit.\n");
			}
		}
		else if(usrChoice == '4') {
			
			if(NULL != phead) {
				
				phead = deletePEntry(phead, searchType(phead));
			}
			else {
				printf("There are no entries to delete.\n");
			}
		}
		else if(usrChoice == '5') {
			
			if(NULL != phead) {
				selNum = searchType(phead);
				
				if(selNum != -1) {
					printSinglePEntry(cyclePEntry(phead, selNum));
				}
			}
			else {
				printf("There are no entries to search.\n");
			}
		}
		else if(usrChoice == 'q' || usrChoice == 'Q') {
			
		}
		else {
			printf("That is not a valid option.");
		}
		
		
	} while (usrChoice != 'q' && usrChoice != 'Q');
	
	
	
	return 0;
}
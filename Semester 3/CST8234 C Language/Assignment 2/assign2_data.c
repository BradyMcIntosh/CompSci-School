/* PROGRAM: Assign2, a phonebook manager
   AUTHOR: Brady McIntosh
   DATE: 12 Nov, 2018
   PURPOSE: Managing data functions (adding, editing, deleting)
   LEVEL OF DIFFICULTY: 4
   CHALLENGES: Manipulating pointers & memory management
   HOURS SPENT: 8
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "assign2.h"

struct Area* addArea(struct Area*);
struct PEntry* addPEntry(struct PEntry*, struct Area*);
struct Area* defineArea();
struct PEntry* definePEntry(struct Area*);
struct PEntry* editPEntry(struct PEntry*, struct Area*, int);
struct PEntry* deletePEntry(struct PEntry*, int);

extern int searchCodeArea(struct Area*, char[]);

extern int printArea(struct Area*);
extern void printSingleArea(struct Area*);
extern int printPEntry(struct PEntry*);
extern void printSinglePEntry(struct PEntry*);
extern struct PEntry* cyclePEntry(struct PEntry*, int);

/* Adds an Area struct to the end of the list*/
struct Area* addArea(struct Area* curr) {
	
	if(NULL == curr) {
		
		/*printf("Defining the area head...\n");*/
		curr = defineArea();
	}
	else {
		
		struct Area* last = curr;
		
		while(NULL != last->nextArea) {
			
			last = last->nextArea;
		}
		/*printf("current: %p, size: %lu \n", (void*) curr, sizeof(*curr));
		printf("Defining the area tail...\n");*/
		last->nextArea = defineArea();
	}
	/*
	printf("Internal area print...\n");
	printArea(curr);
	*/
	return curr;
}

/*Adds a PEntry struct to the beginning of the list*/
struct PEntry* addPEntry(struct PEntry* curr, struct Area* area) {
	
	/*printf("Defining the pentry head...\n");*/
	
	if(NULL == curr) {
		curr = definePEntry(area);
	}
	else {
		struct PEntry* new = definePEntry(area);
		/*printf("current: %p, size: %lu \n", (void*) curr, sizeof(*curr));*/
		new->nextPEntry = curr;
		curr = new;
	}
	/*
	printf("Internal pentry print...\n");
	printPEntry(curr);
	*/	
	return curr;
}

/*Allocates memory and prompts user for values for a new Area struct*/
struct Area* defineArea() {
	
	char usrEnt[NAMELEN+1];
	int valid = 0;
	
	struct Area* new = (struct Area*) malloc(sizeof(struct Area));
	
	if(NULL == new) {
		printf("New area memory failed to allocate...");
		return NULL;
	}
	/*printf("new: %p, size: %lu \n", (void*) new, sizeof(*new));*/
	
	do {
		valid = 0;
		printf("Please enter a three-digit area code: ");
		scanf("%21s", usrEnt);
		flushbuffer();
		/*printf("You entered %lu characters...\n", strlen(usrEnt));*/
		
		if(strlen(usrEnt) == 3) {
			
			if(strtol(usrEnt, NULL, 10) != 0) {
				
				if(usrEnt[0] != '0' && usrEnt[0] != '1') {
					strcpy(new->code, usrEnt);
					/*printf("new->code = %s\n", new->code);*/
					valid = 1;
				}
				else {
					printf("The area code must not begin with a 0 or 1.\n");
				}
			}
			else {
				printf("The area code must be a number.\n");
			}
		}
		else {
			printf("The area code must be 3 digits long.\n");
		}
		
	} while (!valid);
	
	do {
		valid = 0;
		printf("Please enter the name of this area: ");
		scanf("%21s", usrEnt);
		flushbuffer();
		/*printf("You entered %lu characters...\n", strlen(usrEnt));*/
		
		if(strlen(usrEnt) <= 20 && strlen(usrEnt) > 1) {
			strcpy(new->region, usrEnt);
			/*printf("new->region = %s\n", new->region);*/
			valid = 1;
		}
		else {
			printf("The name must be between 2 and 20 characters long.\n");
		}
		
	} while(!valid);
	
	new->nextArea = NULL;
	
	return new;
	
}

/*Allocates memory and prompts user for values for a new PEntry struct*/
struct PEntry* definePEntry(struct Area* area) {
	
	char usrEnt[NAMELEN+1];
	char usrEnt2[NAMELEN+1];
	int valid = 0;
	
	struct PEntry* new = (struct PEntry*) malloc(sizeof(struct PEntry));
	
	if(NULL == new) {
		printf("New pentry memory failed to allocate...");
		return NULL;
	}
	printf("new: %p, size: %lu \n", (void*) new, sizeof(*new));
	
	do {
		valid = 0;
		printf("Please enter a three-digit area code: ");
		scanf("%21s", usrEnt);
		flushbuffer();
		/*printf("You entered %lu characters...\n", strlen(usrEnt));*/
		
		if(strlen(usrEnt) == 3) {
			
			if(strtol(usrEnt, NULL, 10) != 0) {
				
				if(usrEnt[0] != '0' && usrEnt[0] != '1') {
					
					if (searchCodeArea(area, usrEnt)){
						strcpy(new->code, usrEnt);
						/*printf("new->code = %s\n", new->code);*/
						valid = 1;
					}
					else {
						printf("That area code does not exist.\n");
					}
				}
				else {
					printf("The area code must not begin with a 0 or 1.\n");
				}
			}
			else {
				printf("The area code must be a number.\n");
			}
		}
		else {
			printf("The area code must be 3 digits long.\n");
		}
		
	} while (!valid);
	
	do {
		valid = 0;
		printf("Please enter a seven-digit phone number: ");
		scanf("%3s%20s", usrEnt, usrEnt2);
		flushbuffer();
		/*printf("You entered %lu characters...\n", strlen(usrEnt)+strlen(usrEnt2));*/
		
		if(strlen(usrEnt)+strlen(usrEnt2) == 7) {
			
			if(strtol(usrEnt, NULL, 10) != 0 && strtol(usrEnt2, NULL, 10) != 0) {
				
				if(usrEnt[0] != '0' && usrEnt[0] != '1') {
					strcpy(new->exc, usrEnt);
					strcpy(new->sub, usrEnt2);
					/*printf("new->exc = %s, new->sub = %s\n", new->exc, new->sub);*/
					valid = 1;
				}
				else {
					printf("The phone number must not begin with a 0 or 1.\n");
				}
			}
			else {
				printf("The phone number must be a number.\n");
			}
		}
		else {
			printf("The phone number must be 7 digits long.\n");
		}
		
	} while (!valid);
	
	do {
		valid = 0;
		printf("Please enter the first name of this entry: ");
		scanf("%21s", usrEnt);
		flushbuffer();
		/*printf("You entered %lu characters...\n", strlen(usrEnt));*/
		
		if(strlen(usrEnt) <= 20 && strlen(usrEnt) > 1) {
			strcpy(new->fname, usrEnt);
			/*printf("new->fname = %s\n", new->fname);*/
			valid = 1;
		}
		else {
			printf("The name must be between 2 and 20 characters long.\n");
		}
		
	} while(!valid);
	
	do {
		valid = 0;
		printf("Please enter the last name of this entry: ");
		scanf("%21s", usrEnt);
		flushbuffer();
		/*printf("You entered %lu characters...\n", strlen(usrEnt));*/
		
		if(strlen(usrEnt) <= 20 && strlen(usrEnt) > 1) {
			strcpy(new->lname, usrEnt);
			/*printf("new->lname = %s\n", new->lname);*/
			valid = 1;
		}
		else {
			printf("The name must be between 2 and 20 characters long.\n");
		}
		
	} while(!valid);
	
	new->nextPEntry = NULL;
	
	return new;
	
}

/*Frees and re-defines the targeted PEntry struct in the list*/
struct PEntry* editPEntry(struct PEntry* curr, struct Area* area, int cyc) {
	
	struct PEntry* target, * post, * exist;
	
	if(NULL != curr) {
		
		target = cyclePEntry(curr, cyc);
		
		printf("edt:");
		printSinglePEntry(target);
		
		exist = target;
		post = target->nextPEntry;
		
		if(cyc == 0) {
			
			printf("Editing head.\n");
		}
		else {
			if(NULL == post) {
				printf("Editing tail.\n");
			}
			else {
				printf("Editing middle.\n");
			}
		}
		
		free(target);
		target = definePEntry(area);
		
		if(NULL == target) {
			printf("Edit failed.");
			target = exist;
		}
		else {
			printf("Edit successful.\n");
			target->nextPEntry = post;
		}
		
		printPEntry(curr);
		}
	else {
		printf("Cannot edit: list is empty.\n");
	}
	
	return curr;
}

/*Frees and removes the targeted PEntry struct in the list*/
struct PEntry* deletePEntry(struct PEntry* curr, int cyc) {
	
	struct PEntry* prior, * target, * post;
	
	if(NULL != curr) {
		
		target = cyclePEntry(curr, cyc);
		printf("del:");
		printSinglePEntry(target);
		
		if(cyc == 0) {
			
			post = target->nextPEntry;
			curr = post;
			/*printf("Deleting head.\n");*/
		}
		else {
			prior = cyclePEntry(curr, cyc-1);
			post = target->nextPEntry;
			prior->nextPEntry = post;
			/*if(NULL == post) {
				printf("Deleting tail\n");
			}
			else {
				printf("Deleting middle.\n");
			}*/
		}
		
		free(target);
		printf("Delete successful.\n");
		
		printPEntry(curr);
	}
	else {
		printf("Cannot delete: list is empty.\n");
	}
	
	return curr;
}
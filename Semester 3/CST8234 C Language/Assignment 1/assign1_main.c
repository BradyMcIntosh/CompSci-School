/* PROGRAM: Assignment 1, a phonebook simualator?
   AUTHOR: Brady McIntosh, Dean Davidson
   DATE: Oct 20th, 2018
   PURPOSE: To initialize the program and allow the user to request information
   LEVEL OF DIFFICULTY: 3
   CHALLENGES: The syntax for the structs and for the makefile part of the assignment
   HOURS SPENT: 8
*/

#include <stdio.h> 
#include <string.h>
#include "assign1.h"

extern void dataentry();

int main() {

  char usrChoice;
  
  struct Area areas[5];
  struct PEntry entries[20];
  
  dataentry( areas, entries );
  
  printf("\n");

  do {
		int c;
    char usrEnter1[4]; /*area code*/
    char usrEnter2[8]; /*exc + sub*/
    char usrEnter3[20]; /*last name student*/
    char dbCompare[12]; /*compare number to entries in database*/
    int i, entrySize, areaSize, stuCompare1, stuCompare2, valid1, valid2, legal;
    printf("\nWhat would you like to do? \nPress 1 to enter full phone number, \n2 to enter the area code,"
      "\n3 to enter the last name of the student, \n4 to display all listed area codes, \n6 to display all data,"
      "\n5 to quit\n");
    /* Problem with scanf: when reading chars, it will stop removing from feed once it takes what it's looking for.*/
    /* Ex. typing "12345" will go to choice 1, enter an invalid area code, get kicked back to menu, then quit.*/
    /* Should look into scan-dumping after every input, or something like that, to avoid this and other errors.*/
    scanf(" %c", &usrChoice);
    
		do {
      c = getchar();
    } while (c != '\n' && c != EOF);
    
    printf("You pressed: %c\n", usrChoice);
		
    if (usrChoice == '1') {
      valid1 = 0;
      valid2 = 0;
      legal = 1;
      printf("Please enter the area code: \n");
      scanf("%s", usrEnter1);
      
      if(usrEnter1[0] == '0' || usrEnter1[0] == '1') {
        printf("That is an illegal area code.\n");
        legal = 0;
      }

      if(legal) {
      	printf("Please enter the rest of the phone number: \n");
      	scanf("%s", usrEnter2);
        printf("Your 10 digit phone number was: %s%s \n", usrEnter1, usrEnter2); /*right place?*/
    	}
      
      if(usrEnter2[0] == '0' || usrEnter2[0] == '1') {
        printf("That is an illegal phone number.\n");
        legal = 0;
      }
      
      if(legal) {
        /*iterate through database of entries*/
        entrySize = 10;
        for (i = 0; i < entrySize; i++) {
          /*if the area code matches the area code in database, continue*/
          stuCompare1 = strcmp(usrEnter1, entries[i].area.code);
          if (stuCompare1 == 0) {
            valid1 = 1;
            /*concatenate the exc and the sub */
            strcpy(dbCompare, entries[i].num.exc);
            strcat(dbCompare, entries[i].num.sub);
            /*if phone number matches phone number in database, print student info of matches - not printing correctly - first names*/
            stuCompare2 = strcmp(usrEnter2, dbCompare);
            if (stuCompare2 == 0) {
              valid2 = 1;
              printf("Phone number: (%s) %s-%s belongs to %s %s,\n and the number is from %s\n",
                     entries[i].area.code, entries[i].num.exc, entries[i].num.sub,
                     entries[i].fname, entries[i].lname, entries[i].area.region);

            }
          }
        }

        if (!valid1) {
          printf("That area code does not exist in the database\n");
        } else if (!valid2) {
          printf("That phone number does not exist in the database\n");
        }
      }
    } 
    else if (usrChoice == '2') {
      valid1 = 0;
      printf("Please enter only the area code: \n");
      scanf("%s", usrEnter1);
      printf("Your 3 digit area code was: (%s)\n", usrEnter1);
      /*iterate through database of entries*/
      entrySize = 10;
      for (i = 0; i < entrySize; i++) {
        stuCompare1 = strcmp(usrEnter1, entries[i].area.code);
        /*if the area code matches the area code in database, print student info*/
        if (stuCompare1 == 0) {
          valid1 = 1;
          printf("%s %s, %s\n", entries[i].fname, entries[i].lname, entries[i].area.region);
        } 
      }
      if(!valid1)	{
        printf("That area code is not in the database\n");
      }
    }
		else if (usrChoice == '3') {
      valid1 = 0;
      printf("Please enter the last name of the student: \n");
      scanf("%s", usrEnter3);
      printf("The name you entered was: %s\n", usrEnter3);
      /*iterate through database of entries*/
      entrySize = 10;
      for (i = 0; i < entrySize; i++) {
        stuCompare1 = strcmp(usrEnter3, entries[i].lname);
        /*if the last name matches that of a student in the database, print student info*/
        if (stuCompare1 == 0) {
          valid1 = 1;
          printf("%s %s, \n%s %s, \n(%s)-%s-%s\n", 
						entries[i].fname, entries[i].lname, 
            entries[i].area.region, entries[i].area.code, entries[i].area.code, 
            entries[i].num.exc, entries[i].num.sub);
        } 
      }
      if(!valid1)	{
        printf("That student is not in the database.\n");
      }
    }
		else if (usrChoice == '4') {
      areaSize = 5;
      /*print the area code followed by region*/
      for (i = 0; i < areaSize; i++) {
        printf("\n%s represents %s", areas[i].code, areas[i].region);
      }
    }
    
    else if (usrChoice == '5') {
      printf("Goodbye!");
    }

    else if (usrChoice == '6') {
			entrySize = 10;
      for (i = 0; i < entrySize; i++) {
        printf("\nEntry %d:  %s %s, %s, (%s)%s-%s", i,
          entries[i].fname, entries[i].lname,
          entries[i].area.region,
          entries[i].area.code, entries[i].num.exc, entries[i].num.sub);
      }
    }
    
    else {
      printf("That is an invalid input, enter a number between 1 and 6.\n");
    }
  } while (usrChoice != '5');

  return 0;
}

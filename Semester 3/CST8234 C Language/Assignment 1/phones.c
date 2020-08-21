/* Assignment 1 */

#include <stdio.h> 
#include <string.h>

struct Area {
	char region[20];
	char code[4];
};

struct Num {
  char exc[4];
  char sub[5];
};

struct PEntry {
  char fname[20];
  char lname[20];
  struct Area area;
  struct Num num;
};

int main() {

  char usrChoice;

  struct Area areas[5];
  /*struct Num phoneNums[10];*/
  struct PEntry entries[20];

  /* Area Entry 1 */
  strcpy(areas[0].code, "613");
  strcpy(areas[0].region, "Ottawa");

  /* Area Entry 2 */
  strcpy(areas[1].code, "519");
  strcpy(areas[1].region, "Windsor");

  /* Area Entry 3 */
  strcpy(areas[2].code, "416");
  strcpy(areas[2].region, "Toronto");

  /* Area Entry 4 */
  strcpy(areas[3].code, "647");
  strcpy(areas[3].region, "Toronto");

  /* Area Entry 5 */
  strcpy(areas[4].code, "905");
  strcpy(areas[4].region, "Niagara");

  /* Number Entry 1 Specification */
  strcpy(entries[0].fname, "Brady");
  strcpy(entries[0].lname, "McIntosh");
  entries[0].area = areas[0];
  strcpy(entries[0].num.exc, "413");
  strcpy(entries[0].num.sub, "7791");

  /* Number Entry 2 Specification */
  strcpy(entries[1].fname, "Dean");
  strcpy(entries[1].lname, "Davidson");
  entries[1].area = areas[0];
  strcpy(entries[1].num.exc, "440");
  strcpy(entries[1].num.sub, "0030");

  /* Number Entry 3 Specification */
  strcpy(entries[2].fname, "Joe");
  strcpy(entries[2].lname, "Jameson");
  entries[2].area = areas[1];
  strcpy(entries[2].num.exc, "989");
  strcpy(entries[2].num.sub, "7721");

  /* Number Entry 4 Specification */
  strcpy(entries[3].fname, "Mary");
  strcpy(entries[3].lname, "Wendilow");
  entries[3].area = areas[1];
  strcpy(entries[3].num.exc, "343");
  strcpy(entries[3].num.sub, "8721");

  /* Number Entry 5 Specification */
  strcpy(entries[4].fname, "Steve");
  strcpy(entries[4].lname, "Clearie");
  entries[4].area = areas[2];
  strcpy(entries[4].num.exc, "441");
  strcpy(entries[4].num.sub, "2056");

  /* Number Entry 6 Specification */
  strcpy(entries[5].fname, "Stephanie");
  strcpy(entries[5].lname, "Glaser");
  entries[5].area = areas[2];
  strcpy(entries[5].num.exc, "452");
  strcpy(entries[5].num.sub, "2648");

  /* Number Entry 7 Specification */
  strcpy(entries[6].fname, "Mike");
  strcpy(entries[6].lname, "Hampton");
  entries[6].area = areas[3];
  strcpy(entries[6].num.exc, "891");
  strcpy(entries[6].num.sub, "5612");

  /* Number Entry 8 Specification */
  strcpy(entries[7].fname, "Carrie");
  strcpy(entries[7].lname, "Slater");
  entries[7].area = areas[3];
  strcpy(entries[7].num.exc, "319");
  strcpy(entries[7].num.sub, "0101");

  /* Number Entry 9 Specification 5413171*/
  strcpy(entries[8].fname, "Joe");
  strcpy(entries[8].lname, "Biden");
  entries[8].area = areas[4];
  strcpy(entries[8].num.exc, "541");
  strcpy(entries[8].num.sub, "3171");

  /* Number Entry 10 Specification 6668123*/
  strcpy(entries[9].fname, "John");
  strcpy(entries[9].lname, "Kricfalusi");
  entries[9].area = areas[4];
  strcpy(entries[9].num.exc, "666");
  strcpy(entries[9].num.sub, "8123");

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
    
    /*printf("You pressed: %c", usrChoice);*/
		
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
        printf("Your 10 digit phone number was: (%s)-%s \n", usrEnter1, usrEnter2); /*right place?*/
    	}
      
      if(usrEnter2[0] == '0' || usrEnter2[0] == '1') {
        printf("That is an illegal phone number.\n");
        legal = 0;
      }
      
      if(legal) {
        printf("entering cycle - legal input");
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
              printf("%s %s, %s\n", entries[i].fname, entries[i].lname, entries[i].area.region);

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
      printf("You pressed %c\n", usrChoice);
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
          printf("\n%s %s, %s\n", entries[i].fname, entries[i].lname, entries[i].area.region);
        } 
      }
      if(!valid1)	{
        printf("That area code is not in the database, please try again.\n");
      }
    }
		else if (usrChoice == '3') {
      valid1 = 0;
      printf("You pressed %c\n", usrChoice);
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
        printf("That student is not in the database, please try again.\n");
      }
    }
		else if (usrChoice == '4') {
      printf("You pressed %c\n", usrChoice);
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
      printf("You pressed %c\n", usrChoice);
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















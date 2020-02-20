#include <stdio.h>
#include <string.h>
#include "lab6.h"

/* Size values are +1 to allow for null character at end */
/* Entry size values are +2 to allow for null char and size checking */

extern void displayStudent();
extern void displayCourse();
extern void courseReg();
extern void editReg();

extern void studentAdd();
extern void studentEdit();
extern void courseAdd();
extern void courseEdit();

void flushbuffer() {
	int c;
	do {
		c = getchar();
	} while (c != '\n' && c != EOF);
}

int main() {
	
	int studentNum = 0, courseNum = 0;
	struct Student students[SNUM];
	struct Course courses[CNUM];
	
	char usrChoice; /* for choosing a menu item, ie "1" or "a" */
	char usrEnter[NAMELEN + 2]; /* for entering data, ie "12345" or "franklin" */
	
	do {
		
		int c;
		
		printf("What would you like to do? Enter the number or letter to choose an option.\n"
			"\t1. Add a student \n\t2. Add a course \n\t3. Register a student to a course\n"
			"\t4. Change a registration \n\t5. Edit a student record \n\t6. Edit a course\n"
			"\t7. Display a student record \n\t8. Display a course \n\tQ. Quit\n");
		
		scanf("%c", &usrChoice);
		flushbuffer();
		
		printf("You chose option \"%c\"\n", usrChoice);
		
		if (usrChoice == '1') {
			
			if(studentNum < SNUM) {
				studentAdd(students, studentNum);
				studentNum++;
			}
			else {
				printf("You have reached the maximum allowed number of students, %d", SNUM);
			}
			
		}
		else if (usrChoice == '2') {
			
			if(courseNum < CNUM) {
				courseAdd(courses, courseNum);
				courseNum++;
			}
			else {
				printf("You have reached the maximum allowed number of courses, %d", CNUM);
			}
			
		}
		else if (usrChoice == '3') {
			
			courseReg(students, studentNum, courses, courseNum);
			
		}
		else if (usrChoice == '4') {
			
			editReg(students, studentNum, courses, courseNum);
			
		}
		else if (usrChoice == '5') {
			
			studentEdit(students, studentNum);
			
		}
		else if (usrChoice == '6') {
			
			courseEdit(courses, courseNum);
			
		}
		else if (usrChoice == '7') {
			
			displayStudent(students, studentNum);
			
			
		}
		else if (usrChoice == '8') {
			
			displayCourse(courses, courseNum, students, studentNum);
			
		}
		else if (usrChoice == 'q' || usrChoice == 'Q') {
			
			printf("Goodbye!");
			
		}
		else {
			
			printf("That is not a valid option.");
			
		}
		
	} while (usrChoice != 'q' && usrChoice != 'Q');

	return 0;
}
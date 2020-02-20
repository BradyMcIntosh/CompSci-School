#include <stdio.h>
#include <string.h>
#include "lab6.h"

extern void allStudentPrint();
extern void allCoursePrint();

/* Size values are +1 to allow for null character at end */
/* Entry size values are +2 to allow for null char and size checking */

void studentEntryPrint(struct Student student) {
	
	printf("Student #%s: %s %s created.\n", 
		student.studentID, student.fname, student.lname);
}

void courseEntryPrint(struct Course course) {
	
	printf("Course #%s: %s created.\n",
		course.courseID, course.courseTitle);
}

void studentAdd(struct Student students[], int num) {
	
	char entsid[SIDLEN + 10]; 
	char entfname[NAMELEN + 10];
	char entlname[NAMELEN + 10];
	float entmarks;
	int valid, i;
	
	printf("studentAdd\n");
	
	do{ 
		valid = 1;
		printf("Please enter the student's student ID: ");
		scanf("%s", entsid);
		if(strlen(entsid) != SIDLEN) {
			printf("The student ID must be 8 characters, you have entered %lu\n", strlen(entsid));
			valid = 0;
		}
		else {
			for(i = 0; i < num; i++) {
				if(!strcmp(entsid, students[i].studentID)) {
					printf("You must enter a unique ID\n");
					valid = 0;
				}
			}
			if (valid) {
				strcpy(students[num].studentID, entsid);
			}
		}
		
		flushbuffer();
		
	} while (!valid);
	
	do{ 
		printf("Please enter the student's first name: ");
		scanf("%s", entfname);
		if(strlen(entfname) > NAMELEN + 1) {
			printf("That's too long! Please keep it under 20 characters.\n");
		}
		else {
			strcpy(students[num].fname, entfname);
		}
		
		flushbuffer();
		
	} while (strlen(entfname) > NAMELEN + 1);
	
	do{ 
		printf("Please enter the student's last name: ");
		scanf("%s", entlname);
		if(strlen(entlname) > NAMELEN + 1) {
			printf("That's too long! Please keep it under 20 characters.\n");
		}
		else {
			strcpy(students[num].lname, entlname);
		}
		
		flushbuffer();
		
	} while (strlen(entlname) > NAMELEN + 1);
	
	strcpy(students[num].courseID, "0");
	studentEntryPrint(students[num]);
}

void studentEdit(struct Student students[]) {
	
	printf("studentEdit\n");
	
}

void courseAdd(struct Course courses[], int num) {
	
	char entcid[CIDLEN + 10]; 
	char enttitle[NAMELEN + 10];
	int i, valid;
	
	printf("courseAdd\n");
	
	do { 
		valid = 0;
		printf("Please enter the course's course ID: ");
		scanf("%s", entcid);
		flushbuffer();
		if(strlen(entcid) != CIDLEN) {
			printf("The course ID must be 4 characters, you have entered %lu\n", strlen(entcid));
		}
		else {
			for(i = 0; i < num; i++) {
				if(!strcmp(entcid, courses[i].courseID)) {
					printf("You must enter a unique ID\n");
					valid = 0;
				}
			}
			if (valid) {
				strcpy(courses[num].courseID, entcid);
			}
		}
		
	} while (strlen(entcid) != CIDLEN);
	
	do{ 
		printf("Please enter the course's title: ");
		scanf("%s", enttitle);
		flushbuffer();
		if(strlen(enttitle) > NAMELEN) {
			printf("That's too long! Please keep it under 20 characters.\n");
		}
		else {
			strcpy(courses[num].courseTitle, enttitle);
		}
		
	} while (strlen(entcid) > SIDLEN);
	
	courses[num].courseTotal = 0;
	courseEntryPrint(courses[num]);
	
}

void courseEdit(struct Course courses[]) {
	
	printf("courseEdit\n");
	
}

void courseReg(struct Student students[], int ssize, struct Course courses[], int csize) {
	
	int usrChoiceS, usrChoiceC, valid;
	
	printf("courseReg");
	printf("ssize %d csize %d", ssize, csize);
	
	if(ssize > 1 || csize > 1) {
		do {
			valid = 1;
			allStudentPrint(students, ssize);
			printf("Which student would you like to register?");
			scanf("%d", &usrChoiceS);
			flushbuffer();
			if(usrChoiceS >= ssize - 1 || usrChoiceS < 0) {
				printf("That is not a valid choice");
				valid = 0;
			}
			
		} while (!valid);
		
		do {
			valid = 1;
			allCoursePrint(courses, csize);
			printf("To which course is this student registering?");
			scanf("%d", &usrChoiceC);
			flushbuffer();
			if(usrChoiceS >= csize - 1 || usrChoiceC < 0) {
				printf("That is not a valid choice");
				valid = 0;
			}
			
		} while (!valid);
		
		strcpy(students[usrChoiceS-1].courseID, courses[usrChoiceC].courseID);
		courses[usrChoiceC-1].courseTotal++;
		

	}
	else {
		printf("Too few students or courses...");
	}
	
	
	
}

void editReg(struct Student students[], int ssize, struct Course courses[], int csize) {
	
	printf("editReg");
	
}
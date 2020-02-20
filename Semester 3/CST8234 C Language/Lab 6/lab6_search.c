#include <stdio.h>
#include <string.h>
#include "lab6.h"

/* Size values are +1 to allow for null character at end */
/* Entry size values are +2 to allow for null char and size checking */

void studentPrint(struct Student student, int num) {
	
	printf("\t%d. Student #%s: %s %s, ", num, student.studentID, student.fname, student.lname);
	
	if(strcmp(student.courseID, "0")) {
		printf("not registered.");
	}
	else {
		printf("registered in course #%s", student.courseID);
	}
	printf("\n");
}

void coursePrint(struct Course course, int num, struct Student students[]) {
	
	int i, n = 1;
	
	printf("\t%d. Course #%s: %s has %d students registered",
		num, course.courseID, course.courseTitle, course.courseTotal);
	
	if(course.courseTotal > 0) {
		printf(": \n");
		for(i = 0; i < course.courseTotal; i++) {
			if(!strcmp(students[i].courseID, course.courseID)) {
				printf("\t\t%dStudent #%s: %s %s\n", n, students[i].studentID,
					students[i].fname, students[i].lname);
				n++;
			}
		}
	}
	else {
		printf(".\n");
	}
}

void allStudentPrint(struct Student students[], int size) {
	
	int i;
	
	for(i = 0; i < size; i++) {
		printf("\t%d. Student #%s: %s %s \n",
			i+1, students[i].studentID, students[i].fname, students[i].lname);
	}
	
}

void allCoursePrint(struct Course courses[], int size) {
	
	int i;
	
	for(i = 0; i < size; i++) {
		printf("\t%d. Course #%s: %s\n",
			i+1, courses[i].courseID, courses[i].courseTitle);
	}
	
}

void displayStudent(struct Student students[], int size) {
	
	int valid; /* for validating input get loop */
	char usrChoice; /* for choosing a menu item, ie "1" or "a" */
	
	char entsid[SIDLEN + 10]; 
	char entfname[NAMELEN + 10];
	char entlname[NAMELEN + 10];
	char entcid[CIDLEN + 10];
	
	do {
		
		/* i for loop counting, n for result counting */
		int i, n = 1;
		valid = 1;
		
		printf("Display student record by: \n\ta: Student ID \n\tb: Last name \n"
			"\tc. First name \n\td. Registered course ID \n\te. All students \n"
			"\tf. All unregistered students \n\tg. All registered students\n");
		
		scanf("%c", &usrChoice);
		flushbuffer();
		
		printf("You chose option \"%c\"\n", usrChoice);
		
		/* Searching by student ID */
		if (usrChoice == 'a' || usrChoice == 'A') {
			
			do {
				printf("Student ID: ");
				scanf("%s", entsid);
				flushbuffer();
				if(strlen(entsid) != SIDLEN) {
					printf("The student ID must be 8 characters, you have entered %lu\n", strlen(entsid));
				}
				else {
					/* search loop through all students */
					for(i = 0; i < size; i++) {
						if(!strcmp(students[i].studentID, entsid)) {
							studentPrint(students[i], n);
							n++;
							
						}
					}
					if (n == 1) {
						printf("No students have that ID.\n");
					}
				}
			} while (strlen(entsid) != SIDLEN);
			
		}
		/* Searching by last name */
		else if (usrChoice == 'b' || usrChoice == 'B') {
			
			do {
				printf("Last name: ");
				scanf("%s", entlname);
				flushbuffer();
				if(strlen(entlname) > NAMELEN) {
					printf("The last name must be less than %d characters, "
						"you entered %lu\n", NAMELEN, strlen(entlname));
				}
				else {
					/* search loop through all students */
					for(i = 0; i < size; i++) {
						if(!strcmp(students[i].lname, entlname)) {
							studentPrint(students[i], n);
							n++;
							
						}
					}
					if (n == 1) {
						printf("No students have that last name.\n");
					}
				}
			} while (strlen(entlname) > NAMELEN);
			
		}
		/* Searching by first name */
		else if (usrChoice == 'c' || usrChoice == 'C') {
			
			do {
				printf("First name: ");
				scanf("%s", entfname);
				flushbuffer();
				if(strlen(entfname) > NAMELEN) {
					printf("The first name must be less than %d characters, "
						"you entered %lu\n", NAMELEN, strlen(entfname));
				}
				else {
					/* search loop through all students */
					for(i = 0; i < size; i++) {
						if(!strcmp(students[i].fname, entfname)) {
							studentPrint(students[i], n);
							n++;
							
						}
					}
					if (n == 1) {
						printf("No students have that first name.\n");
					}
				}
			} while (strlen(entlname) > NAMELEN);
			
		}
		/* Searching by course ID */
		else if (usrChoice == 'd' || usrChoice == 'D') {
			
			do {
				printf("Course ID: ");
				scanf("%s", entcid);
				flushbuffer();
				if(strlen(entcid) != SIDLEN) {
					printf("The student ID must be 8 characters, you have entered %lu\n", strlen(entcid));
				}
				else {
					/* search loop through all students */
					for(i = 0; i < size; i++) {
						if(!strcmp(students[i].studentID, entcid)) {
							studentPrint(students[i], n);
							n++;
							
						}
					}
					if (n == 1) {
						printf("No students have that ID.\n");
					}
				}
			} while (strlen(entcid) != SIDLEN);
			
		}
		/* All students */
		else if (usrChoice == 'e' || usrChoice == 'E') {
			
			for(i = 0; i < size; i++) {
				studentPrint(students[i], n);
				n++;
			}
			
		}
		/* All unregistered students */
		else if (usrChoice == 'f' || usrChoice == 'F') {
			
			for(i = 0; i < size; i++) {
				
				if(strcmp(students[i].courseID, "0") == 0) {
					studentPrint(students[i], n);
					n++;
				}
			}
			if(n == 1) {
				printf("It seems all students are registered in a course.\n");
			}
		}
		/* All registered students */
		else if (usrChoice == 'g' || usrChoice == 'G') {
			
			for(i = 0; i < size; i++) {
				
				if(strcmp(students[i].courseID, "0") != 0) {
					studentPrint(students[i], n);
					n++;
				}
			}
			if(n == 1) {
				printf("It seems no students are registered in a course.\n");
			}
		}
		else {
			printf("Invalid input. Please choose one of the available options.\n");
			valid = 0;
		}
		
	} while (!valid);

	
}

void displayCourse(struct Course courses[], int csize, struct Student students[], int ssize) {
	
	int valid; /* for validating input get loop */
	char usrChoice; /* for choosing a menu item, ie "1" or "a" */
	
	char entsid[SIDLEN + 10];
	char entcid[CIDLEN + 10];
	int enttotal = 0;
	
	do {
		
		/* i for loop counting, n & m for result counting */
		int i, n = 1, m = 1;
		valid = 1;
		
		printf("Display course record by: \n\ta: Course ID \n\tb: Student ID \n"
			"\tc. All courses \n\td. Total students \n\te. Courses without students \n");
		
		scanf("%c", &usrChoice);
		flushbuffer();
		
		printf("You chose option \"%c\"\n", usrChoice);
		
		/* Searching by course ID */
		if (usrChoice == 'a' || usrChoice == 'A') {
			
			do {
				printf("Course ID: ");
				scanf("%s", entcid);
				flushbuffer();
				if(strlen(entcid) != CIDLEN) {
					printf("The course ID must be 4 characters, you have entered %lu\n", strlen(entcid));
				}
				else {
					/* search loop through all courses */
					for(i = 0; i < csize; i++) {
						if(!strcmp(courses[i].courseID, entcid)) {
							coursePrint(courses[i], n, students);
							n++;
							
						}
					}
					if (n == 1) {
						printf("No courses have that ID.\n");
					}
				}
			} while (strlen(entcid) != CIDLEN);
			
		}
		/* Searching by student ID */
		else if (usrChoice == 'b' || usrChoice == 'B') {
			
			do {
				printf("Course ID: ");
				scanf("%s", entsid);
				flushbuffer();
				if(strlen(entsid) != SIDLEN) {
					printf("The student ID must be 8 characters, you have entered %lu\n", strlen(entsid));
				}
				else {
					/* search loop through all students */
					for(i = 0; i < ssize; i++) {
						if(!strcmp(students[i].studentID, entsid)) {
							n++;
							/* search loop through all courses */
							for(i = 0; i < csize; i++) {
								if(!strcmp(courses[i].courseID, students[i].courseID)) {
									coursePrint(courses[i], n, students);
									m++;
								}
							}
							if(m == 1) {
								printf("The student with this ID is not registered.");
							}
							break;
						}
					}
					if (n == 1) {
						printf("No students have that ID.\n");
					}
				}
			} while (strlen(entcid) != SIDLEN);
			
		}
		/* All courses */
		else if (usrChoice == 'c' || usrChoice == 'C') {
			
			for(i = 0; i < csize; i++) {
				coursePrint(courses[i], n, students);
				n++;
			}
			
		}
		/* Searching by amount of registered students */
		else if (usrChoice == 'd' || usrChoice == 'D') {
			
			/*do { */
				printf("Number of students: ");
				scanf("%d", &enttotal);
				flushbuffer();
				/* search loop through all courses */
				for(i = 0; i < csize; i++) {
					if(courses[i].courseTotal == enttotal) {
						coursePrint(courses[i], n, students);
						n++;
						
					}
				}
				if (n == 1) {
					printf("No courses have %d students.\n", enttotal);
				}
			/*} while (strlen(entcid) != CIDLEN); */
		}
		/* Courses with no students */
		else if (usrChoice == 'e' || usrChoice == 'E') {
			
			for(i = 0; i < csize; i++) {
				
				if(courses[i].courseTotal == 0) {
					coursePrint(courses[i], n, students);
					n++;
				}
			}
			if(n == 1) {
				printf("It seems all students are registered in a course.\n");
			}
		}
		
	} while (!valid);

	
}
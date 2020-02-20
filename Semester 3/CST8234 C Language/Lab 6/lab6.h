/* PROGRAM: Lab6, a college course manager
   AUTHOR: Brady McIntosh
   DATE: Oct 28th, 2018
   PURPOSE: To declare global types used in other files
   LEVEL OF DIFFICULTY: 
   CHALLENGES:
   HOURS SPENT:
*/

#define SNUM 6
#define CNUM 3
#define NAMELEN 20
#define SIDLEN 8
#define CIDLEN 4

/* Size values are +1 to allow for null character */

struct Course {
	char courseID[CIDLEN + 1];
	char courseTitle[NAMELEN + 1];
	int courseTotal;
};

struct Student {
	char studentID[SIDLEN + 1];
	char fname[NAMELEN + 1];
	char lname[NAMELEN + 1];
	float courseMarks;
	char courseID[CIDLEN + 1];
};

void flushbuffer();
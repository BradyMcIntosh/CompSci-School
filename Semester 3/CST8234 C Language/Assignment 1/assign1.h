/* PROGRAM: Assignment 1, a phonebook simualator?
   AUTHOR: Brady McIntosh, Dean Davidson
   DATE: Oct 20th, 2018
   PURPOSE: To declare global types used in other files
   LEVEL OF DIFFICULTY: 3
   CHALLENGES: The syntax for the structs and for the makefile part of the assignment
   HOURS SPENT: 8
*/

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
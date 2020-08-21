/* PROGRAM: Assign2, a phonebook manager
   AUTHOR: Brady McIntosh
   DATE: 12 Nov, 2018
   PURPOSE: To declare global types used in other files
   LEVEL OF DIFFICULTY: 4
   CHALLENGES: Manipulating pointers & memory management
   HOURS SPENT: 8
*/

#define EXCLEN 3
#define SUBLEN 4
#define CODELEN 3
#define NAMELEN 20

/* Size values are +1 to allow for null character */

struct Area {
	char region[NAMELEN+1];
	char code[CODELEN+1];
	struct Area *nextArea;
};

struct PEntry {
  char fname[NAMELEN+1];
  char lname[NAMELEN+1];
  char code[CODELEN+1];
  char exc[EXCLEN+1];
  char sub[SUBLEN];
  /*struct Area *area;*/
  struct PEntry *nextPEntry;
};

void flushbuffer();
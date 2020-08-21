/* PROGRAM: Assignment 1, a phonebook simualator?
   AUTHOR: Brady McIntosh, Dean Davidson
   DATE: Oct 20th, 2018
   PURPOSE: To assign hard-coded data to the struct-array database
   LEVEL OF DIFFICULTY: 3
   CHALLENGES: The syntax for the structs and for the makefile part of the assignment
   HOURS SPENT: 8
*/

#include <string.h>
#include "assign1.h"

int dataentry( struct Area areas[], struct PEntry entries[] ) {

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
  
  return 0;
}

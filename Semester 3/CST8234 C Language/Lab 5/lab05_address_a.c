/* PROGRAM: An introduction to pointers and memory addresses
   AUTHOR: Brady McIntosh
   DATE: 12 Oct 2018
   PURPOSE: To experiment with pointers, memory addresses and various datatypes
   LEVEL OF DIFFICULTY: 1
   CHALLENGES: 
   HOURS SPENT: 1
*/

#include <stdio.h>

int main() {
	
	int x, y;
	int * p;
	
	/* print sizes of above variables */
	
	printf("STEP 1: \tPrinting Sizes\n");
	printf("sizeof(int) == %lu\n", sizeof(x));
	printf("sizeof(mem address) == %lu\n", sizeof(p));
	
	printf("STEP 2: Variables -- Before initialization\n");
	printf("x: \t&x = %p \tx = %d\n", (void*)&x, x);
	printf("y: \t&y = %p \ty = %d\n", (void*)&y, y);
	printf("p: \t&p = %p \tp = %p \t*p = %d\n", (void*)&p, (void*)p, *p);
	
	x = 25;
	y = 1986;
	p = &x;
	
	printf("STEP 3: Variables -- After initialization\n");
	printf("x: \t&x = %p \tx = %d\n", (void*)&x, x);
	printf("y: \t&y = %p \ty = %d\n", (void*)&y, y);
	printf("p: \t&p = %p \tp = %p \t*p = %d\n", (void*)&p, (void*)p, *p);
	
	*p = 55;
	
	printf("STEP 4: Pointer content with new value\n");
	printf("x: \t&x = %p \tx = %d\n", (void*)&x, x);
	printf("y: \t&y = %p \ty = %d\n", (void*)&y, y);
	printf("p: \t&p = %p \tp = %p \t*p = %d\n", (void*)&p, (void*)p, *p);
	
	x = p;
	
	printf("STEP 5: Variable assigned to pointer\n");
	printf("x: \t&x = %p \tx = %d\n", (void*)&x, x);
	printf("y: \t&y = %p \ty = %d\n", (void*)&y, y);
	printf("p: \t&p = %p \tp = %p \t*p = %d\n", (void*)&p, (void*)p, *p);
	
	return 0;
}
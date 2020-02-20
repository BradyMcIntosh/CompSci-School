/* PROGRAM: An introduction to pointers and memory addresses
   AUTHOR: Brady McIntosh
   DATE: 12 Oct 2018
   PURPOSE: To experiment with pointers, memory addresses and various datatypes
   LEVEL OF DIFFICULTY: 1
   CHALLENGES: 
   HOURS SPENT: 1
*/

#include <stdio.h>
#include <stdlib.h>

int main() {
	
	int x;
	int * p, * q;
	
	x = 17;
	p = &x;
	
	/* print sizes of above variables */
	
	printf("In main() before memory allocation...\n");
	printf("x: \t&x = %p \tx = %d\n", (void*)&x, x);
	printf("p: \t&p = %p \tp = %p \t*p = %d\n", (void*)&p, (void*)p, *p);
	printf("q: \t&q = %p \tq = %p \t*q = %d\n", (void*)&q, (void*)q, *q);
	
	q = (int*) malloc(4);
	
	printf("\nIn main() after memory allocation...\n");
	printf("x: \t&x = %p \tx = %d\n", (void*)&x, x);
	printf("p: \t&p = %p \tp = %p \t*p = %d\n", (void*)&p, (void*)p, *p);
	printf("q: \t&q = %p \tq = %p \t*q = %d\n", (void*)&q, (void*)q, *q);
	
	return 0;
}
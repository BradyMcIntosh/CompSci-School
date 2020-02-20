/* PROGRAM: An introduction to pointers and memory addresses
   AUTHOR: Brady McIntosh
   DATE: 12 Oct 2018
   PURPOSE: To experiment with pointers, memory addresses and various datatypes
   LEVEL OF DIFFICULTY: 2
   CHALLENGES: 
   HOURS SPENT: 0.5
*/

#include <stdio.h>

int modify_var(int a, int* q);

int main() {
	
	int x = 5, y = 10;
	int * p = &x;
	
	/* print sizes of above variables */
	
	printf("FUNCTION main():\n");
	printf("x: \t&x = %p \tx = %d\n", (void*)&x, x);
	printf("y: \t&y = %p \ty = %d\n", (void*)&y, y);
	printf("p: \t&p = %p \tp = %p \t*p = %d\n", (void*)&p, (void*)p, *p);
	
	printf("Calling modify_var(y,p)...\n");
	modify_var(y, p);
	
	printf("FUNCTION main():\n");
	printf("x: \t&x = %p \tx = %d\n", (void*)&x, x);
	printf("y: \t&y = %p \ty = %d\n", (void*)&y, y);
	printf("p: \t&p = %p \tp = %p \t*p = %d\n", (void*)&p, (void*)p, *p);
	
	return 0;
}

int modify_var(int a, int* q) {
	
	printf("FUNCTION modify_var() - before modification\n");
	printf("a: \t&a = %p \ta = %d\n", (void*)&a, a);
	printf("q: \t&q = %p \tq = %p \t*q = %d\n", (void*)&q, (void*)q, *q);
	
	a = 45;
	*q = 9;
	
	printf("FUNCTION modify_var() - after modification\n");
	printf("a: \t&a = %p \ta = %d\n", (void*)&a, a);
	printf("q: \t&q = %p \tq = %p \t*q = %d\n", (void*)&q, (void*)q, *q);
	
	return 0;
}
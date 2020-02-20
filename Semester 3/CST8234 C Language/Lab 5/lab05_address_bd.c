#include <stdio.h>

main()	{
int x, y;
int * p;
x = 5;
y = 10;

printf("Main method\n");
printf("------------------------------\n");
printf("x:    &x = %p   x = %d\n", (void*)&x, x);
printf("y:    &y = %p   y = %d\n", (void*)&y, y);
printf("p:    &p = %p   p = %p    *p = %d\n", (void*)&p, (void*)p, 1);
printf("------------------------------\n");

modify_var(y, p);
printf("Main method\n");
printf("------------------------------\n");
printf("x:    &x = %p   x = %d\n", (void*)&x, x);
printf("y:    &y = %p   y = %d\n", (void*)&y, y);
printf("p:    &p = %p   p = %p    *p = %d\n", (void*)&p, (void*)p, *p);
printf("------------------------------\n");
}

modify_var(int a, int *q)	{
	printf("Calling mod_var(y, p)\n");
	printf("Function modify_var( ) -- before modifying\n");
	printf("a:    &a = %p   a = %d\n", (void*)&a, a);
	printf("q:    &q = %p   q = %p    *p = %d\n", (void*)&q, (void*)q, *q);
	a = 100;
	q = 45;
	
	printf("Function modify_var( ) -- after modifying\n");
	printf("a:    &a = %p   a = %d\n", (void*)&a, a);
	printf("q:    &q = %p   q = %p    *p = %d\n", (void*)&q, (void*)q, *q);
}
#include <stdio.h>

int Add1(int a, int b) {
	
	return a + b;
}

int Sub1(int a, int b) {
	
	return a - b;
}

int Mul1(int a, int b) {
	
	return a * b;
}

float Div1(int a, int b) {
	
	return (float) a / b;
}

int Power1(int a, int b) {
	
	int result = 1;
	
	while (b > 0) {
		result = result * a;
		b--;
	}
	
	return result;
}

int main() {
	
	int a, b, result;
	float divresult;
	
	printf("Please enter the first number: ");
	scanf("%d", &a);
	
	printf("Please enter the second number: ");
	scanf("%d", &b);
	
	result = Add1(a, b);
	printf("Added: %d\n", result);
	
	result = Sub1(a, b);
	printf("Subtracted: %d\n", result);
	
	result = Mul1(a, b);
	printf("Multiplied: %d\n", result);
	
	divresult = Div1(a, b);
	printf("Divided: %f\n", divresult);
	
	result = Power1(a, b);
	printf("Exponent: %d\n", result);
	
	return 0;
}
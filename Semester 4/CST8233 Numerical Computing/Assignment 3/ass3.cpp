/**********************************************************
File name:			ass3.cpp
Version:			1.0
Author:				Brady McIntosh
Student No:			040706980
Course Name/Number:	Numerical Computing	CST8233
Lab Sect:			302
Assignment #:		3
Assignment Name:	Transient-Response Analysis of 1st Order 
						Systems
Due Date:			December 6 2019
Submission Date:	
Professor:			Abdullah Kadri
Purpose:			Find the solution of 1st order Ordinary 
						Differential Equations (ODE) using 
						well known methods; namely, Euler’s 
						and Runge-Kutta 2nd Order Methods. 
**********************************************************/

#define _CRT_SECURE_NO_WARNINGS

#define MENU_1 1
#define MENU_2 2

#define INPUT_NULL	(-1)
#define STR_LEN		64

#define RANGE_START	0
#define RANGE_END	2
#define INITIAL_Y	3.0

static const float HVALUES[3] = { 0.8, 0.2, 0.05 };

#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <math.h>

char menu(int);
void euler(float);
void runge_kutta_4(float);
float f_xy(float, float);
void flushbuffer(void);

/**********************************************************
Function Name:		main
Purpose:			Main function. Interface with menu and 
						algorithm functions.

In Parameters:		none
Out Parameters:		int - error code
Version:			1.0
Author:				Brady McIntosh
**********************************************************/
int main() 
{
	char option1;
	char option2;
	float h = 0.0;

	while (1)
	{
		option1 = menu(MENU_1);
		if (option1 == 3)
		{
			break;
		}

		option2 = menu(MENU_2);

		switch (option2)
		{
		case 1: h = HVALUES[0]; break;
		case 2: h = HVALUES[1]; break;
		case 3: h = HVALUES[2]; break;
		}

		switch (option1)
		{
		case 1: euler(h); break;
		case 2: runge_kutta_4(h); break;
		}
	}
}

/**********************************************************
Function Name:		menu
Purpose:			Menu handling for regression/extrapolation
					prompt interface

In Parameters:		int menu - the type of menu to show
Out Parameters:		char - user input character
Version:			1.0
Author:				Brady McIntosh
**********************************************************/
char menu(int menu)
{

	char usr_str[STR_LEN];
	char option = INPUT_NULL;
	int RUNNING = 1;

	do {
		if (menu == MENU_1)
		{
			printf(">> Choose the method for solving the ODE: \n"
				"1. Euler's Method \n2. Runge-Kutta 4th Order Method\n"
				"3. Exit \n>> ");
		}

		else //if (menu == MENU_2)
		{
			printf(">> Choose step size \"h\" (%.2f, %.2f, %.2f)\n"
				">> ", HVALUES[0], HVALUES[1], HVALUES[2]);
		}

		scanf("%s", usr_str);
		flushbuffer();
		usr_str[STR_LEN - 1] = '\0';
		
		if (menu == MENU_1)
		{
			if (*usr_str == '1')
			{
				option = (char)1;
				RUNNING = 0;
			}
			else if (*usr_str == '2')
			{
				option = (char)2;
				RUNNING = 0;
			}
			else if (*usr_str == '3')
			{
				option = (char)3;
				RUNNING = 0;
			}

			else
			{
				printf("Invalid input\n");
			}
		}
		//if (menu == MENU_2)
		else {

			float f_input = atof(usr_str);

			if (f_input == HVALUES[0])
			{
				option = (char)1;
				RUNNING = 0;
			}

			else if (f_input == HVALUES[1])
			{
				option = (char)2;
				RUNNING = 0;
			}

			else if (f_input == HVALUES[2])
			{
				option = (char)3;
				RUNNING = 0;
			}

			else {
				printf("Invalid input\n");
			}
		}
		
	} while (RUNNING);

	return option;
}

/**********************************************************
Function Name:		euler
Purpose:			Uses Euler's method to find the solution
					to the project 1st Order Differential Equation.

In Parameters:		float h - the h value, ie differential
Out Parameters:		void
Version:			1.0
Author:				Brady McIntosh
**********************************************************/
void euler(float h)
{
	int iters;		/* number of loop iterations */
	float* y;		/* y values, or temperature in C */
	float* x;		/* x values, or time in seconds */
	float* exact;	/* exact temperature values */
	float* error;	/* relative error */

	iters = (int)((RANGE_END - RANGE_START) / h) + 1;
	y = (float*)malloc(sizeof(float) * iters);
	x = (float*)malloc(sizeof(float) * iters);
	exact = (float*)malloc(sizeof(float) * iters);
	error = (float*)malloc(sizeof(float) * iters);

	/* set initial y value */
	y[0] = INITIAL_Y;

	for (int i = 0; i < iters; ++i)
	{
		float abserr; /* absolute error value */

		/* set time value per loop */
		x[i] = i * h;

		/* one fewer iteration of this, since it accesses i+1 */
		if (i < iters - 1)
		{
			/* euler's method equation for y(i+1) */
			y[i + 1] = y[i] + f_xy(x[i], y[i]) * h;
		}

		/* exact value equation for y(i) */
		exact[i] = (0.1 * cosf(4 * x[i])) + (0.2 * sinf(4 * x[i]))
			+ (2.9 * expf(-2 * x[i]));

		abserr = fabsf(y[i] - exact[i]);

		/* relative error equation */
		error[i] = abserr / exact[i] * 100;
	}

	// 57       2.000            7.28 

	printf("Euler's method w/ h = %.2f\n", h);
	printf("%-20s %-20s %-20s %-20s\n", "Time(seconds)", "Exact Temp(C)",
			"Estimated Temp(C)", "Percentage Error(%)");
	for (int i = 1; i < iters; ++i) {
		printf("%-20.1f %-20.3f %-20.3f %-20.2f\n",
			x[i], exact[i], y[i], error[i]);
	}

	free(y);
	free(x);
	free(exact);
	free(error);
}

/**********************************************************
Function Name:		runge_kutta_4
Purpose:			Uses the Runge-Kutta 4th Order method
					to find the solution to the project 
					1st Order Differential Equation.

In Parameters:		float h - the h value, ie differential
Out Parameters:		void
Version:			1.0
Author:				Brady McIntosh
**********************************************************/
void runge_kutta_4(float h)
{
	int iters;		/* number of loop iterations */
	float* y;		/* y values, or temperature in C */
	float* x;		/* x values, or time in seconds */
	float* exact;	/* exact temperature values */
	float* error;	/* relative error */

	float k1;	/* intermediate K value 1 */
	float k2;	/* intermediate K value 2 */
	float k3;	/* intermediate K value 3 */
	float k4;	/* intermediate K value 4 */

	iters = (int)((RANGE_END - RANGE_START) / h) + 1;
	y = (float*)malloc(sizeof(float) * iters);
	x = (float*)malloc(sizeof(float) * iters);
	exact = (float*)malloc(sizeof(float) * iters);
	error = (float*)malloc(sizeof(float) * iters);

	/* set initial y value */
	y[0] = INITIAL_Y;

	for (int i = 0; i < iters; ++i)
	{
		float abserr; /* absolute error value */

		/* set time value per loop */
		x[i] = i * h;

		/* one fewer iteration of this, since it accesses i+1 */
		if (i < iters - 1)
		{
			/* intermediate calculations */
			k1 = f_xy(x[i], y[i]);
			k2 = f_xy(x[i] + h/2, y[i] + k1 * h/2);
			k3 = f_xy(x[i] + h / 2, y[i] + k2 * h / 2);
			k4 = f_xy(x[i] + h, y[i] + k3 * h);

			/* runge-kutta method equation for y(i+1) */
			y[i + 1] = y[i] + ((k1 + 2*k2 + 2*k3 + k4) / 6) * h;
		}

		/* exact value equation for y(i) */
		exact[i] = (0.1 * cosf(4 * x[i])) + (0.2 * sinf(4 * x[i]))
			+ (2.9 * expf(-2 * x[i]));

		abserr = fabsf(y[i] - exact[i]);

		/* relative error equation */
		error[i] = abserr / exact[i] * 100;
	}

	// 57       2.000            7.28 

	printf("Runge-Kutta 4th Order method w/ h = %.2f\n", h);
	printf("%-20s %-20s %-20s %-20s\n", "Time(seconds)", "Exact Temp(C)",
		"Estimated Temp(C)", "Percentage Error(%)");
	for (int i = 1; i < iters; ++i) {
		printf("%-20.1f %-20.3f %-20.3f %-20.2f\n",
			x[i], exact[i], y[i], error[i]);
	}

	free(y);
	free(x);
	free(exact);
	free(error);
}

/**********************************************************
Function Name:		f_xy
Purpose:			Solves and returns the first-order equation
					using the given values.

In Parameters:		float x - the x value
					float y - the y, or f(x) value
Out Parameters:		float - the result of the equation
Version:			1.0
Author:				Brady McIntosh
**********************************************************/
float f_xy(float x, float y)
{
	return (cosf(4 * x) - 2 * y);
}

/**********************************************************
Function Name:		flushbuffer
Purpose:			Flush the stdin buffer, to avoid reading
					trailing garbage.

In Parameters:		none
Out Parameters:		void
Version:			1.0
Author:				Brady McIntosh
**********************************************************/
void flushbuffer(void)
{
	int c;
	do {
		c = getchar();
	} while (c != '\n' && c != EOF);
}
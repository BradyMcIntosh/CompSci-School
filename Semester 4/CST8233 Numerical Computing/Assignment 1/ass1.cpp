/**********************************
Student Name: Brady McIntosh
Student Number: 040706980
Assignment Number: 1
Course: Numerical Computing CST8233
Lab Section: 302
Professor: Abdullah Kadri
Due Date: Sep 29
Submission Date: 
**********************************/

#define _CRT_SECURE_NO_WARNINGS

#include <stdio.h>
#include <math.h>


int drvs[] = { 1, -1, 0, 2, -4, 4, 0, -8, -16 };

void flushbuffer()
{
	int c;
	do {
		c = getchar();
	} while (c != '\n' && c != EOF);
}

void series()
{
	int terms;				/* number of approximation terms */
	int temp_terms;			/* temporary term value for skipping terms that equal zero */
	int i, j, k;			/* loop index */

	double var;				/* user-specified variable */
	double var_inc;			/* user variable divided into increments */
	double exact_value;		/* exact value of the formula */
	double numerator;		/* number to be divided */
	double denominator;		/* number doing the dividing */
	double approx_value;	/* approximate value of the formula at a particular term */
	double series_total;	/* approximate value of the formula across whole series */
	double first_trunc;		/* series total, plus one more term */
	double exact_err;		/* difference between exact value and approximate value */
	double trunc_err;		/* difference between approximate value and first truncated value */

	printf("Please enter the number of terms in the series (max 5 terms): ");
	scanf("%d", &terms);

	printf("Please enter the range of t to evaluate in 10 increments (0.0 < t < +2.0): ");
	scanf("%lf", &var);

	flushbuffer();

	/* main loop to increment user variable */
	for (i = 0; i < 11; i++)
	{
		/* { 0 -> +0.10 -> var } */
		var_inc = var / 10 * i;

		/* exact value... (this is correct) */
		exact_value = exp(0 - var_inc) * cos(var_inc);

		/* initialize series total */
		series_total = 0;

		temp_terms = terms;

		/* one loop per term, +1 for trunc */
		for (j = 0; j < temp_terms + 1; j++)
		{

			/* initialize numerator to pre-set derivative value */
			numerator = drvs[j];

			/* make sure only VALID terms are counted,
			   but don't fuck with the math */
			if (drvs[j] == 0)
			{
				temp_terms++;
			}
			
			for (k = 0; k < j; k++)
			{
				numerator *= var_inc;
			}

			/* printf("%d(%.2lf^%d) = %f;  ", drvs[j], var_inc, j, numerator); */

			/* initialize denominator (cannot divide by zero) */
			denominator = 1;

			if (j != 0)
			{

				for (k = j; k > 0; k--)
				{
					denominator *= k;
				}
			}

			/* printf("%d! = %.2lf;  ", j, denominator); */

			approx_value = numerator / denominator;

			/*printf("T%d: %.2lf/%.2lf = %.2lf;  ", j, numerator, denominator, approx_value); */


			if (j < temp_terms)
			{
				//printf("%.2lf", approx_value);
				series_total += approx_value;

				/*if (j < (terms - 1))
				{
					printf(" + ");
				}
				else
				{
					printf(" = ");
				}*/
			}
			else
			{
				first_trunc = series_total + approx_value;
			}
		}

		/*
		printf("%.2lf", series_total); 

		printf("\n");
		*/

		/* print out the banner */
		if (i == 0)
		{
			printf("\nt \t\t Series \t Exact \t\t Exact %% Error \t Trunc %% Error\n");
		}

		/* error values */
		exact_err = 100 * (exact_value - series_total) / exact_value;
		trunc_err = 100 * (series_total - first_trunc) / series_total;

		if (i != 0)
		{
			trunc_err *= -1;
		}

		/* series values */
		printf("%.3e \t%.5e \t%.5e \t%.5e \t%.5e \n",
			var_inc, series_total, exact_value,
			exact_err, trunc_err);
	}
}

int main()
{
	char RUN = 1;

	do {

		char usr;
		printf("\nEvaluate the MacLaurin Series Approximation to exp(-t)*cos(t)\n"
			"\n\t1. Evalue the series \n\t2. Quit\n");
		scanf("%c", &usr);

		flushbuffer();

		switch (usr)
		{
			case '1': series(); break;
			case '2': RUN = 0; break;
			default : printf("That is not a valid input.\n");
		}

	} while (RUN);

	return 0;
}
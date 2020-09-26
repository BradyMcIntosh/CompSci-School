/**********************************************************
File name:			ass2.cpp
Version:			1.0
Author:				Brady McIntosh
Student No:			040706980
Course Name/Number:	Numerical Computing	CST8233
Lab Sect:			302
Assignment #:		2
Assignment Name:	Moore's Law
Due Date:			November 15 2019
Submission Date:	November 16 2019
Professor:			Abdullah Kadri
Purpose:			Fit data using linear regression least-squares method
					for an exponential function.
**********************************************************/

#define _CRT_SECURE_NO_WARNINGS

#define MENU_1 1
#define MENU_2 2

#define INPUT_NULL (-1)
#define STR_LEN 64

#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <math.h>


char menu(int);
int countrecords(FILE*);
int parserecords(int*, float*, int, FILE*);
int linear_regression(int*, float*, int, float&, float&);
int extrapolation(float, float, float&, float&);
void flushbuffer();

/**********************************************************
Function Name:		main
Purpose:			Main function. Opens input file and shows
					results of algorithm functions

In Parameters:		none
Out Parameters:		int - error code
Version:			1.0
Author:				Brady McIntosh
**********************************************************/
int main()
{
	FILE* fi = NULL;		/* file input handle */
	char file_str[STR_LEN];	/* file input string */
	char usr_str[STR_LEN];	/* user input string */
	char usr_c;				/* user input char */

	int entries = 0;			/* number of data point entries */
	int* year_list = NULL;		/* year datapoints */
	float* tran_list = NULL;	/* transistor count datapoints */
	float con_a;				/* constant "a" found by linear fit */
	float con_b;				/* constant "b" found by linear fit */

	float tran_count_ex;	/* extrapolated transistor count */
	float rate_ex;			/* extrapolated rate of count increase */
	
	int RUN_1 = 1;	/* run value for first menu loop */
	int RUN_2;		/* run value for second menu loop */

	while (RUN_1)
	{
		printf("LEAST_SQUARES LINEAR REGRESSION\n\n");
		usr_c = menu(MENU_1);

		/* Exponential Fit */
		if (usr_c == '1')
		{
			printf("Please enter the name of the file to open: ");
			scanf("%s", usr_str);
			flushbuffer();
			usr_str[STR_LEN - 1] = '\0';

			fi = fopen(usr_str, "r");

			/* if file could not be opened */
			if (fi == NULL)
			{
				printf("File \"%s\" could not be read\n", usr_str);
				continue;
			}

			/* print & lines in file */
			entries = countrecords(fi);

			
			printf("\nThere are %d records.\n\n", entries-1);

			year_list = (int*) malloc(sizeof(int) * entries - 1);
			tran_list = (float*) malloc(sizeof(float) * entries - 1);

			if (year_list == NULL || tran_list == NULL)
			{
				printf("Memory allocation error... Restarting!\n");
				continue;
			}

			/* reset back to start and skip first line */
			rewind(fi);
			fgets(file_str, STR_LEN, fi);

			/* parse record data into the number lists */
			if (parserecords(year_list, tran_list, entries, fi) == 1)
			{
				printf("There were errors in parsing the given data\n");
			}

			fclose(fi);

			/* line 0 in file is header, should permanently ignore it now */
			//--entries;

			if (linear_regression(year_list, tran_list, entries, con_a, con_b) == 1)
			{
				printf("There were errors in calculating the linear regression fit\n");
			}

			printf("Linear Regression Fit: transistor count = %.3e*exp(%.3e*(year-1970))\n\n",
				con_a, con_b);

			RUN_2 = 1;

			while (RUN_2)
			{
				usr_c = menu(MENU_2);
				/* make an extrapolation */
				if (usr_c == '1')
				{
					if (extrapolation(con_a, con_b, tran_count_ex, rate_ex) == 1)
					{
						printf("There were errors in extrapolating to a particular year\n");
					}

					printf("transistor count = %.3e\n", tran_count_ex);
					printf("rate of count increase = %.3e transistors/year\n", rate_ex);
					printf("\n");
				}
				/* return to main menu */
				else if (usr_c == '2')
				{
					RUN_2 = 0;
				}
			}
		}
		/* Quit */
		else if (usr_c == '2')
		{
			RUN_1 = 0;
		}
	}

	return 0;
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

	char usr = INPUT_NULL;
	int RUNNING = 1;

	do {
		if (menu == MENU_1)
		{
			printf("MENU\n  1. Exponential Fit\n  2. Quit\n");
		}

		else //if (menu == MENU_2)
		{
			printf("MENU\n  1. Extrapolation\n  2. Main Menu\n");
		}

		scanf("%c", &usr);
		flushbuffer();

		if (usr == '1' || usr == '2')
		{
			RUNNING = 0;
		}

		else
		{
			printf("Invalid input");
		}
	} while (RUNNING);

	

	return usr;
}

/**********************************************************
Function Name:		countrecords
Purpose:			Count the number of lines in a file

In Parameters:		FILE* fi - input file
Out Parameters:		int - number of lines
Version:			1.0
Author:				Brady McIntosh
**********************************************************/
int countrecords(FILE* fi)
{
	int records = 0;
	char file_str[STR_LEN];

	while (1)
	{

		/* printing & counting lines */

		if (fgets(file_str, STR_LEN, fi) == NULL)
		{
			break;
		}

		file_str[STR_LEN - 1] = '\0';
		++records;

		printf("%s", file_str);
	}

	return records - 1;
}

/**********************************************************
Function Name:		parserecords
Purpose:			Parse lines of a given file into two lists
					of integer and float datapoints

In Parameters:		int* yr_list - list of integer datapoints
					float* tr_list - list of float datapoints
					int num_records - number of datapoints to read
					FILE* fi - input file
Out Parameters:		int - error code
Version:			1.0
Author:				Brady McIntosh
**********************************************************/
int parserecords(int* yr_list, float* tr_list, int num_records, FILE* fi)
{
	char file_str[STR_LEN];
	char* token;

	int ret;

	for (int i = 0; i < num_records; ++i)
	{
		/* reading & parsing lines */

		if (fgets(file_str, STR_LEN, fi) == NULL)
		{
			ret = 0;
			break;
		}

		file_str[STR_LEN - 1] = '\0';

		/* get first token and try to parse to integer */
		token = strtok(file_str, " \t");

		if (token == NULL)
		{
			printf("Token parsing error... Skipping line %d!\n", i + 1);
			ret = 1;
			continue;
		}
		if (strlen(token) == 1 && *token == '0')
		{
			yr_list[i] = 0;
		}
		else
		{
			if (atoi(token) == 0)
			{
				printf("Invalid integer token... Skipping line %d!\n", i + 1);
				break;
			}
			else
			{
				yr_list[i] = atoi(token);
			}
		}

		/* get second token and try to parse to float */
		token = strtok(NULL, " \t");
		if (token == NULL)
		{
			printf("Token parsing error... Skipping line %d!\n", i + 1);
			ret = 1;
			continue;
		}

		if (strlen(token) == 1 && *token == '0' ||
			strcmp(token, "0.000e+00") == 0 ||
			strcmp(token, "0.000e-00") == 0)
		{
			tr_list[i] = 0.0;
		}
		else
		{
			if (atof(token) == 0.0)
			{
				printf("Invalid float token... Skipping line %d!\n", i + 1);
				ret = 1;
				break;
			}
			else
			{
				tr_list[i] = (float)atof(token);
			}
		}

		//printf("Line %d - i: %d, f: %f\n", i, yr_list[i], tr_list[i]);
	}
}

/**********************************************************
Function Name:		linear_regression
Purpose:			Uses a linear regression algorithm to find
					line equation constants for a given dataset

In Parameters:		int* ti - function parameter
					float* Fi - function value
					int num_records - number of datapoints
					float& con_a - "a" constant
						(being set)
					float& con_b - "b" constant
						(being set)
Out Parameters:		int - error code
Version:			1.0
Author:				Brady McIntosh
**********************************************************/
int linear_regression(int* ti, float* Fi, int num_records, float &con_a, float &con_b)
{
	float* Wi;		/* natural logs of ti */
	float* Zi;		/* natural logs of Fi */
	float* WiZi;	/* Wi * Zi */
	float* Wi2;		/* Wi ^ 2 */

	float sumWi = 0.0;		/* sum of natural logs of ti */
	float sumZi = 0.0;		/* sum of natural logs of Fi */
	float sumWiZi = 0.0;	/* sum of Wi * Zi */
	float sumWi2 = 0.0;		/* sum of Wi ^ 2 */

	float a1;	/* un-corrected slope value */
	float a0;	/* un-corrected intercept value */

	Wi = (float*)malloc(sizeof(float) * num_records);
	Zi = (float*)malloc(sizeof(float) * num_records);
	WiZi = (float*)malloc(sizeof(float) * num_records);
	Wi2 = (float*)malloc(sizeof(float) * num_records);

	//printf("ti\tFi\t\tWi\tZi\tWiZi\tWi2\n");

	for (int k = 0; k < num_records; ++k) 
	{
		if (( Wi[k] = (float)ti[k] - 1970.0 ) == -INFINITY)
		{
			Wi[k] = 0;
		}
		if ((Zi[k] = logf(Fi[k])) == -INFINITY)
		{
			Zi[k] = 0;
		}
		if ((WiZi[k] = (float) (Wi[k] * Zi[k])) == -INFINITY)
		{
			WiZi[k] = 0;
		}
		if ((Wi2[k] = powf(Wi[k], 2.0)) == -INFINITY)
		{
			Wi2[k] = 0;
		}

		sumWi += Wi[k];
		sumZi += Zi[k];
		sumWiZi += WiZi[k];
		sumWi2 += Wi2[k];

		/* printf("%d\t%.3e %8.03f %8.03f %8.03f %8.03f\n", 
			ti[k], Fi[k], Wi[k], Zi[k], WiZi[k], Wi2[k]); */
	}

	//printf("\t\t%8.03f %8.03f %8.03f %8.03f \n", sumWi, sumZi, sumWiZi, sumWi2);

	free(Wi);
	free(Zi);
	free(WiZi);
	free(Wi2);

	/* printf("a1 = ( %d(%.3f) - (%.3f)(%.3f) ) / ( %d(%.3f) - (%.3f)^2 )\n",
		num_records, sumWiZi, sumWi, sumZi, num_records, sumWi2, sumWi); */
	a1 = (num_records * sumWiZi - sumWi * sumZi) / (num_records * sumWi2 - powf(sumWi, 2.0));
	//printf("a1 = %.3f\n", a1);

	/*printf("a0 = (%.3f / %d) - ( %.3f * (%.3f / %d))\n",
		sumZi, num_records, a1, sumWi, num_records); */
	a0 = (sumZi / num_records) - a1 * (sumWi / num_records);
	//printf("a0 = %.3f\n", a0);

	//con_a = (float*)malloc(sizeof(float));
	//con_b = (float*)malloc(sizeof(float));

	con_a = expf(a0);
	con_b = a1;

	//printf("a = %.3f, b = %.3f\n", con_a, con_b);

	return 0;
}

/**********************************************************
Function Name:		extrapolation
Purpose:			Read user input for a year and extrapolate data
					for that year using existing formula constants

In Parameters:		float con_a - "a" constant
					float con_b - "b" constant
					float& tran_count_ex - transistor count
						(being set)
					float& rate_ex - rate of count increase
						(being set)
Out Parameters:		int - error code
Version:			1.0
Author:				Brady McIntosh
**********************************************************/
int extrapolation(float con_a, float con_b, float& tran_count_ex, float& rate_ex)
{
	char usr_str[STR_LEN];
	int year;
	int RUN = 1;

	while (RUN) {
		printf("Please enter the year to extrapolate to: ");

		scanf("%s", usr_str);
		flushbuffer();
		usr_str[STR_LEN - 1] = '\0';

		if (strlen(usr_str) == 0 && *usr_str == '0')
		{
			year = 0;
		}
		else
		{
			year = atoi(usr_str);

			if (year == 0)
			{
				printf("That is an invalid number...\n");
			}
		}

		printf("Year = %d\n", year);

		tran_count_ex = con_a * expf(con_b * (year - 1970));
		rate_ex = con_a * con_b * expf(con_b * (year - 1970));
		RUN = 0;
	}
	
	return 0;
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
void flushbuffer() 
{
	int c;
	do {
		c = getchar();
	} while (c != '\n' && c != EOF);
}
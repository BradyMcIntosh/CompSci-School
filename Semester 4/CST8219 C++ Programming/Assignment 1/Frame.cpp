/************************************************
Filename: Frame.cpp
Version: 1.0
Author: Brady McIntosh
Assignment Number: 1
Assignment Name: Animation Project in C++
Course Name: C++
Course Code: CST8219
Lab Section Number: 302
Professor's Name: Mariusz Makos
Due Date: 2019 Oct 12
Submission Date: 2019 Oct 12
List of source files:
 -- ass1.cpp
 -- Animation.cpp
 -- Frame.cpp

Purpose: Handle creation of new frames, including prompting user for name
**********************************/

#define _CRT_SECURE_NO_WARNINGS

#include <iostream>
#include "Frame.h"

/***********************************************
Function name: Frame
Purpose: Constructor; Prompt and set frame name.

In parameters: none
Out Parameters: none
Version: 1.0
Author: Brady McIntosh
************************************************/
Frame::Frame()
{
	char usr[65];	/* user input string buffer */

	/* prompt and accept user input */
	std::cout << "Please enter the Frame frameName: ";
	std::cin >> usr;

	/* terminate user-input string */
	usr[64] = '\0';

	/* allocate and set data member to user input */
	frameName = new char[strlen(usr) + 1];
	strcpy(frameName, usr);

	/* initialize pNext member */
	pNext = NULL;
}

/***********************************************
Function name: ~Frame
Purpose: Destructor; Remove object from memory, including name.

In parameters: none
Out Parameters: none
Version: 1.0
Author: Brady McIntosh
************************************************/
Frame::~Frame()
{
	/* destroy allocated name */
	delete[] frameName;
}
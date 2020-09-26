/**********************************************************
Filename: Frame.cpp
Version: 1.0
Author: Brady McIntosh
Assignment Number: 3
Assignment Name: Animation in C++ with Inheritance
Course Name: C++
Course Code: CST8219
Lab Section Number: 302
Professor's Name: Mariusz Makos
Due Date: 30Nov2019
Submission Date: 28Nov2019
List of source files:
 --Assignment3.cpp
 --AnimationManager.cpp
 --Animation.cpp
 --Frame.cpp
 --VideoFrame.cpp
 --AudioFrame.cpp

Purpose: Manage default Frame member output
**********************************************************/

#include <string>
#include <iostream>

using namespace std;

#include "Frame.h"

/***********************************************
Function name: operator<<
Purpose: Operator overload - sending Frame to ostream
	(Report details of Frame)

In parameters:
 -- ostream& - output stream
 -- Frame& - the frame
Out Parameter: ostream reference

Version: 1.0
Author: Brady McIntosh
************************************************/
std::ostream& operator<<(std::ostream& out, Frame& frame)
{
	/* sends details of given frame to given output stream */
	out << "frameName = " << frame.frameName;

	return out;
}

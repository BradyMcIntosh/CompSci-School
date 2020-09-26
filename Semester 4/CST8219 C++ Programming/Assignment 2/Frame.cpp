/************************************************
Filename: Frame.cpp
Version: 1.0
Author: Brady McIntosh
Assignment Number: 2
Assignment Name: Animation Project in C++
Course Name: C++
Course Code: CST8219
Lab Section Number: 302
Professor's Name: Mariusz Makos
Due Date: 11Nov2019
Submission Date: 12Nov2019
List of source files:
 -- Frame.cpp
 -- Animation.cpp
 -- AnimationManager.cpp
 -- Assignment2.cpp

Purpose: Manage Frame memory, member values, and string output style
**********************************/

#include <iostream>

#include "Frame.h"

/***********************************************
Function name: Frame
Purpose: Constructor

In parameters: 
 -- char* - the new frame's name
 -- double - the new frame's duration
Out Parameter: Frame object

Version: 1.0
Author: Brady McIntosh
************************************************/
Frame::Frame(char* frameName, double duration)
{
	this->frameName = new char[strlen(frameName) + 1]();
	strcpy_s(this->frameName, strlen(frameName) + 1, frameName);

	this->duration = duration; 
}

/***********************************************
Function name: Frame
Purpose: Constructor

In parameters: 
 -- Frame& - the target frame
Out Parameter: the source frame

Version: 1.0
Author: Brady McIntosh
************************************************/
Frame::Frame(const Frame& frame)
{
	/* copies members from frame argument into this frame */
	this->frameName = new char[strlen(frame.frameName) + 1]();
	strcpy_s(this->frameName, strlen(frame.frameName) + 1, frame.frameName);
	this->duration = frame.duration;
}

/***********************************************
Function name: ~Frame
Purpose: Destructor

In parameters: 
 -- none
Out Parameter: nothing

Version: 1.0
Author: Brady McIntosh
************************************************/
Frame::~Frame()
{
	delete frameName;
}

/***********************************************
Function name: operator=
Purpose: Operator overload - assigning Frame to Frame
	(Copy data from target Frame to source Frame)

In parameters: 
 -- Frame& - frame
Out Parameter: Frame reference

Version: 1.0
Author: Brady McIntosh
************************************************/
Frame& Frame::operator=(const Frame& frame)
{
	/* delete existing frameName */
	delete this->frameName;

	/* copies members from frame argument into this frame */
	this->frameName = new char[strlen(frame.frameName) + 1]();
	strcpy_s(this->frameName, strlen(frame.frameName) + 1, frame.frameName);
	this->duration = frame.duration;

	/* return self-reference */
	return *this;
}

/***********************************************
Function name: operator<<
Purpose: Operator overload - sending Frame to ostream
	(Report details of Frame)

In parameters: 
 -- ostram& - output stream
 -- Frame& - the frame
Out Parameter: ostream reference

Version: 1.0
Author: Brady McIntosh
************************************************/
std::ostream& operator<<(std::ostream& out, Frame& frame)
{
	/* sends details of given frame to given output stream */
	out << "frameName = " << frame.frameName << 
		"; duration = " << frame.duration;

	return out;
}

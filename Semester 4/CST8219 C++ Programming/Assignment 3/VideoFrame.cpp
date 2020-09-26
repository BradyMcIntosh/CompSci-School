/**********************************************************
Filename: VideoFrame.cpp
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

Purpose: Implement particular display outputs of VideoFrame
**********************************************************/

#include <string>
#include <iostream>

using namespace std;

#include "Frame.h"
#include "VideoFrame.h"

/**********************************************************
Function name: CalculateFrameResource
Purpose: Calculate and output resource usages of the VideoFrame,
		as well as the standard Frame details.

In parameters:
 -- none
Out Parameter: nothing

Version: 1.0
Author: Brady McIntosh
**********************************************************/
void VideoFrame::CalculateFrameResource()
{
	cout << *this << endl;
	cout << "Lempel-Ziv-Welch Lossless Compression" << endl;
	cout << "----------------------------------------------------------------------------------------" << endl;

	cout << "colours:        ";

	for (int i = 1; i <= BITS; ++i)
	{
		printf("| %-7d", (int)powf((float)2,(float)i));
	}

	cout << endl;
	cout << "----------------------------------------------------------------------------------------" << endl;

	cout << "file size (MB)  ";

	for (int i = 0; i < BITS; ++i)
	{
		printf("| %-7.2f", size / (COMPRESSION_RATIO * BITDEPTH_FACTOR[i]));
	}

	cout << endl;
	cout << "----------------------------------------------------------------------------------------" << endl;
}

/***********************************************
Function name: operator<<
Purpose: Operator overload - sending VideoFrame to ostream
	(Report details of VideoFrame)

In parameters:
 -- ostream& - output stream
 -- VideoFrame& - the frame
Out Parameter: ostream reference

Version: 1.0
Author: Brady McIntosh
************************************************/
ostream& operator<<(ostream& out, VideoFrame& vframe)
{
	out << "VideoFrame: " << *((Frame*) &vframe);

	return out;
}
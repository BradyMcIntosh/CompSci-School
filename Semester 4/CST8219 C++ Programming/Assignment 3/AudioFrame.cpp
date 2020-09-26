/**********************************************************
Filename: AudioFrame.cpp
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

Purpose: Implement particular display outputs of AudioFrame
**********************************************************/

#include <string>
#include <iostream>

using namespace std;

#include "Frame.h"
#include "AudioFrame.h"

/**********************************************************
Function name: CalculateFrameResource
Purpose: Calculate and output resource usages of the AudioFrame,
		as well as the standard Frame details.

In parameters:
 -- none
Out Parameter: nothing

Version: 1.0
Author: Brady McIntosh
**********************************************************/
void AudioFrame::CalculateFrameResource()
{
	cout << *this << endl;
	cout << "MP3 Lossy Compression" << endl;
	cout << "---------------------------------------------------------" << endl;

	cout << "bitrate (kbits/s):        ";

	for (int i = 0; i < RATES; ++i)
	{
		printf("| %-7d", (int)BITRATE[i]);
	}

	cout << endl;
	cout << "---------------------------------------------------------" << endl;

	cout << "file size (MB):           ";

	for (int i = 0; i < RATES; ++i)
	{
		printf("| %-7.2f", size / COMPRESSION_RATIO[i]);
	}

	cout << endl;
	cout << "---------------------------------------------------------" << endl;
}

/***********************************************
Function name: operator<<
Purpose: Operator overload - sending AudioFrame to ostream
	(Report details of AudioFrame)

In parameters:
 -- ostream& - output stream
 -- AudioFrame& - the frame
Out Parameter: ostream reference

Version: 1.0
Author: Brady McIntosh
************************************************/
ostream& operator<<(ostream& out, AudioFrame& aframe)
{
	out << "AudioFrame: ";
	out << *((Frame*) &aframe);

	return out;
}
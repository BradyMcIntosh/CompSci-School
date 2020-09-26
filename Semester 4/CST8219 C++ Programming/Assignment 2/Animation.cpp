/************************************************
Filename: Animation.cpp
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

Purpose: Manage member Frames & input from/output to streams
**********************************/

#include <iostream>
#include <forward_list>

#include "Frame.h"
#include "Animation.h"

/***********************************************
Function name: EditFrame
Purpose: Select a member Frame and edit its data

In parameters: 
 -- none
Out Parameter: void

Version: 1.0
Author: Brady McIntosh
************************************************/
void Animation::EditFrame()
{
	int i;				/* loop index */
	int f_count;		/* the number of frames in the animation */
	char usr_c1[65];	/* user input char buffer #1 */
	char usr_c2[65];	/* user input char buffer #2 */
	int usr_int = 0;	/* user input converted to integer */
	int index = -1;		/* selected frame index */

	std::forward_list<Frame>::iterator f_iter; /* interator to frame for reference */

	/* initialize frame count */
	f_count = (int)std::distance(frames.begin(), frames.end());
	
	/*                                   */
	/* initial printout & get list index */
	/*                                   */

	std::cout << "Edit a Frame in the Animation" << std::endl;

	/* return if there are no frames */
	if (f_count == 0)
	{
		std::cout << "There are no Frames in the Animation" << std::endl;
		return;
	}
	
	/* prompt user for edit index */
	std::cout << "There are " << f_count << " Frame(s) in the list." << std::endl;
	std::cout << "Please specify the index (<= " << (f_count - 1) << ") to edit at : ";
	std::cin >> usr_c1;

	/* terminate user-input string */
	usr_c1[64] = '\0';

	/* attempt to convert user input to integer */
	/* if entry is 1 char, and char is '0', set to 0 */
	if (strlen(usr_c1) == 1 && *usr_c1 == '0')
	{
		index = 0;
	}
	/* otherwise, convert using standard method */
	else
	{
		usr_int = atoi(usr_c1);

		/* if conversion was invalid */
		if (usr_int < 0 || usr_int >= f_count)
		{
			std::cout << "That is an invalid index." << std::endl;
			return;
		}
		/* if conversion was successful */
		else
		{
			index = usr_int;
		}
	}

	/* initialize frame pointer */
	f_iter = frames.begin();

	/* traverse frame pointer in loop, until user selection found */
	for (i = 0; i < index; ++i)
	{
		++f_iter;
	}

	/*                       */
	/* get new frame details */
	/*                       */

	/* report current frame details and prompt for new ones */
	std::cout << "The name and  duration of this Frame are " <<
		*f_iter << ". What do you wish to replace them with?" << std::endl;
	
	/* read and null-terminate user inputs for new details*/
	std::cin >> usr_c1;
	usr_c1[64] = '\0';

	std::cin >> usr_c2;
	usr_c2[64] = '\0';

	/* attempt to convert user input to integer */
	/* if entry is 1 char, and char is '0', set to 0 */
	if (strlen(usr_c2) == 1 && *usr_c2 == '0')
	{
		usr_int = 0;
	}
	/* otherwise, convert using standard method */
	else
	{
		usr_int = atoi(usr_c2);

		/* if conversion was invalid */
		if (usr_int <= 0)
		{
			std::cout << "That is an invalid duration." << std::endl;
			return;
		}
	}

	/*                                      */
	/* copy new details into existing frame */
	/*                                      */

	/* set existing frame values to new values */
	*f_iter = Frame(usr_c1, usr_int);

	/* Frame #0 edit completed */
	std::cout << "Frame #" << index << " edit completed" << std::endl;
}

/***********************************************
Function name: DeleteFrame
Purpose: Delete member Frame at head (front) of list

In parameters: 
 -- none
Out Parameter: void

Version: 1.0
Author: Brady McIntosh
************************************************/
void Animation::DeleteFrame()
{
	int f_count;

	/* initialize frame count */
	f_count = (int)std::distance(frames.begin(), frames.end());

	/* return if there are no frames */
	if (f_count == 0)
	{
		std::cout << "No frames to delete." << std::endl;
		return;
	}

	/* pop front element */
	frames.pop_front();

	std::cout << "First Frame deleted" << std::endl;
}

/***********************************************
Function name: operator>>
Purpose: Operator overload - accept from input stream
	(Inserts a Frame in the Animation)

In parameters: 
 -- istream& - input stream
 -- Animation& - the animation
Out Parameter: istream reference

Version: 1.0
Author: Brady McIntosh
************************************************/
std::istream& operator>>(std::istream& in, Animation& anim)
{
	char usr_c1[65];	/* user input char buffer #1 */
	char usr_c2[65];	/* user input char buffer #2 */
	int usr_int = 0;	/* user input converted to integer */

	std::cout << "Insert a Frame in the Animation" << std::endl;
	std::cout << "Please enter the Frame frameName: ";

	in >> usr_c1;
	usr_c1[64] = '\0';

	std::cout << "Please enter the Frame duration: ";

	in >> usr_c2;
	usr_c2[64] = '\0';

	/* attempt to convert user input to integer */
	/* if entry is 1 char, and char is '0', set to 0 */
	if (strlen(usr_c2) == 1 && *usr_c2 == '0')
	{
		usr_int = 0;
	}
	/* otherwise, convert using standard method */
	else
	{
		usr_int = atoi(usr_c2);
	}

	/* add  */
	anim.frames.push_front(Frame(usr_c1, usr_int));

	std::cout << "Frame " << usr_c1 << " added at Front of frames" << std::endl;

	return in;
}

/***********************************************
Function name: operator<<
Purpose: Operator overload - send to output stream
	(Report details of Animation)

In parameters: 
 -- ostream& - output stream
 -- Animation& - the animation
Out Parameter: ostream reference

Version: 1.0
Author: Brady McIntosh
************************************************/
std::ostream& operator<<(std::ostream& out, Animation& anim)
{
	int f_count; /* the number of frames in the animation */
	int i;/* loop index */

	std::forward_list<Frame>::iterator f_iter; /* interator to frame for reference */

	/* animation name  */
	out << "\tAnimation name is " << anim.animationName << std::endl;
	out << "\tReport the Animation" << std::endl;

	/* initialize frame count */
	f_count = (int)std::distance(anim.frames.begin(), anim.frames.end());

	/* return if there are no frames */
	if (f_count == 0)
	{
		std::cout << "\tNo frames in the Animation" << std::endl;
		return out;
	}

	f_iter = anim.frames.begin();

	/* traverse frame iterator in loop and report each frame*/
	for (i = 0; i < f_count; ++i)
	{
		out << "\tFrame " << i << ": " << *f_iter << std::endl;
		++f_iter;
	}

	return out;
}

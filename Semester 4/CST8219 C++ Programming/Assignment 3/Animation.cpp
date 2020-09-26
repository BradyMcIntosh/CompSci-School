/**********************************************************
Filename: Animation.cpp
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

Purpose: Manage member Frames & input from/output to streams
**********************************************************/

#include <iostream>
#include <string>
#include <iterator>
#include <typeinfo>
#include <forward_list>

using namespace std;

#include "Frame.h"
#include "AudioFrame.h"
#include "VideoFrame.h"
#include "Animation.h"

/**********************************************************
Function name: ~Animation
Purpose: Destructor

In parameters:
 -- none
Out Parameter: nothing

Version: 1.0
Author: Brady McIntosh
**********************************************************/
Animation::~Animation()
{
	for (Frame* f : frames)
	{
		delete f;
	}
}

/**********************************************************
Function name: EditFrame
Purpose: Select a member Frame and edit its data

In parameters:
 -- none
Out Parameter: void

Version: 1.0
Author: Brady McIntosh
***********************************************************/
void Animation::EditFrame()
{
	int i;				/* loop index */
	int RUN;			/*  */
	int f_count;		/* the number of frames in the animation */
	char usr_cb[65];	/* user input char buffer #1 */
	int usr_int = 0;	/* user input converted to integer */
	int index = -1;		/* selected frame index */

	string usr_str;		/* user input converted to string object */
	Frame* frame;		/* pointer to virtual Frame */
	forward_list<Frame*>::iterator f_iter;	/* interator to frame for reference */
	reverse_iterator<Frame*> f_rev;			/* reverse Frame* iterator */

	/* initialize frame count */
	f_count = (int)distance(frames.begin(), frames.end());

	/*                                   */
	/* initial printout & get list index */
	/*                                   */

	cout << "Edit a Frame in the Animation" << endl;

	/* return if there are no frames */
	if (f_count == 0)
	{
		cout << "There are no Frames in the Animation" << endl;
		return;
	}

	/* prompt user for edit index */
	cout << "There are " << f_count << " Frame(s) in the list." << endl;
	cout << "Please specify the index (<= " << (f_count - 1) << ") to edit at : ";
	cin >> usr_cb;

	/* terminate user-input string */
	usr_cb[64] = '\0';

	/* attempt to convert user input to integer */
	/* if entry is 1 char, and char is '0', set to 0 */
	if (strlen(usr_cb) == 1 && *usr_cb == '0')
	{
		index = 0;
	}
	/* otherwise, convert using standard method */
	else
	{
		usr_int = atoi(usr_cb);

		/* if conversion was invalid */
		if (usr_int < 0 || usr_int >= f_count)
		{
			cout << "That is an invalid index." << endl;
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
	cout << "You selected a Frame whose " << **f_iter << "." << endl;

	cout << "Please enter the new Frame frameName: ";

	/* read in frame name and assign to string */
	cin >> usr_cb;
	usr_cb[64] = '\0';
	usr_str = usr_cb;

	RUN = 1;

	/* input validation loop for Frame size */
	while (RUN)
	{
		cout << "Please enter the new Frame size(MB): ";

		cin >> usr_cb;
		usr_cb[64] = '\0';

		/* attempt to convert user input to integer */
		/* if entry is 1 char, and char is '0', set to 0 */
		if (strlen(usr_cb) == 1 && *usr_cb == '0')
		{
			usr_int = 0;
		}
		/* otherwise, convert using standard method */
		else
		{
			usr_int = atoi(usr_cb);

			/* print error if result is invalid */
			if (usr_int == 0)
			{
				cout << "That is an invalid size." << endl;
			}
			/* if result is valid, exit loop */
			else {
				RUN = 0;
			}
		}
	}

	RUN = 1;

	/* input validation loop for Frame type */
	while (RUN)
	{
		cout << "Please enter the Frame type (AudioFrame = A, VideoFrame = V):";

		cin >> usr_cb;
		usr_cb[64] = '\0';

		/* if input is valid, set type and exit loop */
		if (*usr_cb == 'A' || *usr_cb == 'a'
			|| *usr_cb == 'V' || *usr_cb == 'v')
		{
			if (*usr_cb == 'A' || *usr_cb == 'a')
			{
				frame = new AudioFrame(usr_str, usr_int);
			}

			else if (*usr_cb == 'V' || *usr_cb == 'v')
			{
				frame = new VideoFrame(usr_str, usr_int);
			}

			//anim.frames.push_front(frame);
			RUN = 0;
		}
		/* if input is invalid, print error statement and reset loop */
		else {
			cout << "That is an invalid type." << endl; // THIS LINE NOT FINISHED
		}
	}

	/*                                  */
	/* replace old frame with new frame */
	/*                                  */

	/* insert new Frame after position of current frame */
	frames.insert_after(f_iter, frame);

	/* if list originally contained only one Frame */
	if (f_count == 1)
	{
		delete *(frames.begin());
		/* delete frame at head (original frame) */
		frames.pop_front();
	}

	/* if list originally contained more than one Frame */
	else {
		/* delete original frame */
		delete *f_iter;

		/* re - initialize frame pointer */
		f_iter = frames.begin();

		/* traverse frame pointer in loop, until user selection (less 1) found */
		for (i = 0; i < index - 1; ++i)
		{
			++f_iter;
		}

		/* remove frame after iterator (original frame) */
		frames.erase_after(f_iter);
	}

	/* Frame #X edit completed */
	cout << "Frame #" << index << " edit completed" << endl;
}

/**********************************************************
Function name: DeleteFrame
Purpose: Delete member Frame at head (front) of list

In parameters:
 -- none
Out Parameter: void

Version: 1.0
Author: Brady McIntosh
**********************************************************/
void Animation::DeleteFrame()
{
	int f_count;

	/* initialize frame count */
	f_count = (int)distance(frames.begin(), frames.end());

	/* return if there are no frames */
	if (f_count == 0)
	{
		cout << "No frames to delete." << endl;
		return;
	}

	delete *(frames.begin());

	/* pop front element */
	frames.pop_front();

	cout << "First Frame deleted" << endl;
}

/**********************************************************
Function name: operator>>
Purpose: Operator overload - accept from input stream
	(Inserts a Frame in the Animation)

In parameters:
 -- istream& - input stream
 -- Animation& - the animation
Out Parameter: istream reference

Version: 1.0
Author: Brady McIntosh
**********************************************************/
istream& operator>>(istream& in, Animation& anim)
{
	int RUN = 1;		/* flag set to determine if input loop continues */
	char usr_cb[65];	/* user input char buffer */
	int usr_int = 0;	/* user input converted to integer */

	string usr_str;		/* user input converted to string object */
	Frame* frame;		/* pointer to virtual Frame */

	cout << "Insert a Frame in the Animation" << endl;
	cout << "Please enter the Frame frameName: ";

	/* read in frame name and assign to string */
	in >> usr_cb;
	usr_cb[64] = '\0';
	usr_str = usr_cb;

	/* input validation loop for Frame size */
	while (RUN)
	{
		cout << "Please enter the Frame size(MB): ";

		in >> usr_cb;
		usr_cb[64] = '\0';

		/* attempt to convert user input to integer */
		/* if entry is 1 char, and char is '0', set to 0 */
		if (strlen(usr_cb) == 1 && *usr_cb == '0')
		{
			usr_int = 0;
		}
		/* otherwise, convert using standard method */
		else
		{
			usr_int = atoi(usr_cb);

			/* print error if result is invalid */
			if (usr_int == 0)
			{
				cout << "That is an invalid size." << endl;
			}
			/* if result is valid, exit loop */
			else {
				RUN = 0;
			}
		}
	}

	RUN = 1;

	/* input validation loop for Frame type */
	while (RUN)
	{
		cout << "Please enter the Frame type (AudioFrame = A, VideoFrame = V):";

		in >> usr_cb;
		usr_cb[64] = '\0';

		/* if input is valid, set type and exit loop */
		if (*usr_cb == 'A' || *usr_cb == 'a'
			|| *usr_cb == 'V' || *usr_cb == 'v')
		{
			if (*usr_cb == 'A' || *usr_cb == 'a')
			{
				frame = new AudioFrame(usr_str, usr_int);
			}

			else if (*usr_cb == 'V' || *usr_cb == 'v')
			{
				frame = new VideoFrame(usr_str, usr_int);
			}

			anim.frames.push_front(frame);
			RUN = 0;
		}
		/* if input is invalid, print error statement and reset loop */
		else {
			cout << "That is an invalid type." << endl; // THIS LINE NOT FINISHED
		}
	}

	//Frame Frame1 Frame* added at the front of frames
	cout << "Frame " << usr_str << " Frame* added at the front of frames" << endl;

	return in;
}

/**********************************************************
Function name: operator<<
Purpose: Operator overload - send to output stream
	(Report details of Animation)

In parameters:
 -- ostream& - output stream
 -- Animation& - the animation
Out Parameter: ostream reference

Version: 1.0
Author: Brady McIntosh
**********************************************************/
ostream& operator<<(ostream& out, Animation& anim)
{
	int f_count; /* the number of frames in the animation */
	int i;/* loop index */

	// TODO: account for list of pointers
	forward_list<Frame*>::iterator f_iter; /* interator to frame for reference */

	/* animation name  */
	out << "\tAnimation name is " << anim.animationName << endl;
	out << "\tReport the Animation" << endl;

	/* initialize frame count */
	f_count = (int)distance(anim.frames.begin(), anim.frames.end());

	/* return if there are no frames */
	if (f_count == 0)
	{
		cout << "\tNo frames in the Animation" << endl;
		return out;
	}

	f_iter = anim.frames.begin();

	/* traverse frame iterator in loop and report each frame*/
	for (i = 0; i < f_count; ++i)
	{
		out << "\tFrame #" << i << endl;
		(**f_iter).CalculateFrameResource();

		++f_iter;
	}

	return out;
}
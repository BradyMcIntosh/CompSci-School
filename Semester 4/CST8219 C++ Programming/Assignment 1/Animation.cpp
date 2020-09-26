/************************************************
Filename: Animation.cpp
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

Purpose: Handle user requests to create a new animation, add new frames, edit 
	existing frames, delete existing frames, and report animation details.
**********************************/

#define _CRT_SECURE_NO_WARNINGS

#include <iostream>

#include "Frame.h"
#include "Animation.h"

using namespace std;

/* initialize */

/***********************************************
Function name: Animation
Purpose: Constructor; Prompt and set animation name.

In parameters: none
Out Parameters: none
Version: 1.0
Author: Brady McIntosh
************************************************/
Animation::Animation()
{
	char usr[65];	/* user input string buffer */

	/* prompt and accept user input */
	std::cout << "Please enter the Animation name: ";
	std::cin >> usr;

	/* terminate user-input string */
	usr[64] = '\0';

	/* allocate and set data member to user input */
	animationName = new char[strlen(usr) + 1];
	strcpy(animationName, usr);

	/* initialize frames member */
	frames = NULL;
}

/***********************************************
Function name: ~Animation
Purpose: Destructor

In parameters: none
Out Parameters: none
Version: 1.0
Author: Brady McIntosh
************************************************/
Animation::~Animation()
{
	/* loop to destroy all member frames */
	while (frames != NULL)
	{
		Frame* tempF = frames->GetpNext();
		delete frames;

		frames = tempF;
	}

	/* destroy allocated name */
	delete[] animationName;
}

/***********************************************
Function name: InsertFrame
Purpose: Insert a Frame at the start of the Animation list.

In parameters: none
Out Parameters: none
Version: 1.0
Author: Brady McIntosh
************************************************/
void Animation::InsertFrame()
{
	std::cout << "Insert a Frame in the Animation" << endl;

	/* if there is no first frame */
	if (frames == NULL)
	{
		frames = new Frame();
	}

	/* if first frame exists */
	else
	{
		/* move existing to position [1], create new at [0] */
		Frame* next = frames;
		frames = new Frame();
		frames->GetpNext() = next;
	}
}

/***********************************************
Function name: EditFrame
Purpose: Change the name of a user-selected Frame 
	within the Animation list.

In parameters: none
Out Parameters: none
Version: 1.0
Author: Brady McIntosh
************************************************/
void Animation::EditFrame()
{
	int i;				/* loop index */
	char usr[65];		/* user input char buffer */
	int fNum = 0;		/* number of frames in the animation */
	int usrInt = -1;	/* user input converted to integer */
	Frame* frameTemp;	/* temporary reference to a frame */

	std::cout << "Edit a Frame in the Animation" << endl;

	frameTemp = Animation::frames;

	/* loop to find number of frames in animation */
	while (frameTemp != NULL)
	{
		++fNum;
		frameTemp = frameTemp->GetpNext();
	}

	/* return if there are no frames */
	if (fNum == 0)
	{
		std::cout << "There are 0 frames in the list." << endl;
		return;
	}
	
	/* prompt user for frame index to edit */
	std::cout << "There are " << fNum << " frame(s) in the list. "
		"Please specify the index (<=" << (fNum - 1) << ") "
		"to edit at : ";
	std::cin >> usr;

	/* terminate user-input string */
	usr[64] = '\0';

	/* attempt to convert user input to integer */
	/* if entry is 1 char, and char is '0', set to 0 */
	if (strlen(usr) == 1 && *usr == '0')
	{
		usrInt = 0;
	}
	/* otherwise, convert using standard method */
	else
	{
		usrInt = atoi(usr);

		/* if conversion was invalid */
		if (usrInt <= 0 || usrInt >= fNum)
		{
			std::cout << "That is an invalid index" << endl;
			return;
		}
	}

	/* set frame to beginning of list */
	frameTemp = frames;

	/* traverse to selected frame, if first is not selected */
	if (usrInt != 0)
	{
		for (i = 0; i < usrInt; ++i)
		{
			frameTemp = frameTemp->GetpNext();
		}
	}

	/* prompt and accept user frame index */
	std::cout << "The name of this Frame is" << 
		frameTemp->GetFrameName() << ". " <<
		"What do you wish to replace it with?";
	std::cin >> usr;

	/* terminate user-input string */
	usr[64] = '\0';

	/* allocate and set data member to user input */
	frameTemp->GetFrameName() = new char[strlen(usr) + 1];
	strcpy(frameTemp->GetFrameName(), usr);
}

/***********************************************
Function name: DeleteFrame
Purpose: Delete a Frame at the end of the Animation list.

In parameters: none
Out Parameters: none
Version: 1.0
Author: Brady McIntosh
************************************************/
void Animation::DeleteFrame()
{
	Frame* frameTemp;	/* temporary reference to a frame */
	Frame* frameLast;	/* temporary reference to the previous frame */

	/* if no frames exist */
	if (frames == NULL)
	{
		std::cout << "There are no Frames in the list." << endl;
		return;
	}

	/* hook first frame */
	frameTemp = frames;

	/* if only one frame exists */
	if (frames->GetpNext() == NULL)
	{
		/* nullify animation reference */
		frames = NULL;
	}

	/* if more than one frame exists */
	else
	{
		/* find last frame */
		frameLast = NULL;
		while (frameTemp->GetpNext() != NULL)
		{
			frameLast = frameTemp;
			frameTemp = frameTemp->GetpNext();
		}

		/* nullify next-to-last frame reference */
		frameLast->GetpNext() = NULL;
	}

	/* delete last frame */
	delete frameTemp;
}

/***********************************************
Function name: ReportAnimation
Purpose: Print animation details.

In parameters: none
Out Parameters: none
Version: 1.0
Author: Brady McIntosh
************************************************/
void Animation::ReportAnimation()
{
	int i = 0;			/* loop counter */
	Frame* frameTemp;	/* temporary reference to a frame */


	/* animation details */
	std::cout << "Animation name is " << animationName << endl;
	std::cout << "Report the Animation" << endl;

	/* if there are no frames */
	if (frames == NULL)
	{
		std::cout << "There are 0 frames in the list." << endl;
		return;
	}

	frameTemp = frames;

	/* display loop */
	do {
		std::cout << "Frame #" << i << ", file name = " <<
			frameTemp->GetFrameName() << endl;

		/* cycle to next frame and increment counter */
		frameTemp = frameTemp->GetpNext();
		++i;
	} while (frameTemp != NULL);

	std::cout << endl;
}
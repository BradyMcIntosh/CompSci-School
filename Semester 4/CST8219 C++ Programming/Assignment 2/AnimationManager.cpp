/************************************************
Filename: AnimationManager.cpp
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

Purpose: Manage member Animations & input from/output to streams
**********************************/

#include <iostream>
#include <string>
#include <vector>
#include <forward_list>

#include "Frame.h"
#include "Animation.h"
#include "AnimationManager.h"

/***********************************************
Function name: EditAnimation
Purpose: Select a member Animation and edit its data

In parameters: 
 -- none
Out Parameter: void

Version: 1.0
Author: Brady McIntosh
************************************************/
void AnimationManager::EditAnimation()
{
	char usr_c1[65];		/* user input char buffer #1 */
	int a_count;			/* number of animations in the manager */
	int index;				/* animation index */
	bool RUNNING = true;	/* running state of menu loop */

	Animation* anim;		/* temporary Animation pointer */

	/*                                   */
	/* initial printout & get list index */
	/*                                   */

	/* initialize frame count */
	a_count = (int)animations.size();

	/* return if there are no frames */
	if (a_count == 0)
	{
		std::cout << "\tNo Animations in the Animation Manager" << std::endl;
		return;
	}

	std::cout << "Which Animation do you wish to edit? " <<
		"Please give the index in the range 0 to " << animations.size() - 1 <<
		": " << std::endl;

	std::cin >> usr_c1;
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
		index = atoi(usr_c1);

		/* if conversion was invalid */
		if (index < 0 || index >= a_count)
		{
			std::cout << "That is an invalid index." << std::endl;
			return;
		}
	}

	anim = &animations[index];
	std::cout << "Editing Animation #" << index << std::endl;

	/*                                 */
	/* menu loop for animation editing */
	/*                                 */

	while (RUNNING)
	{
		/* list menu options */
		std::cout << "MENU" << std::endl <<
			"1. Insert a Frame" << std::endl <<
			"2. Delete a Frame" << std::endl <<
			"3. Edit a Frame" << std::endl <<
			"4. Quit" << std::endl;

		/* accept user input */
		/* no termination necessary as only 1st char used */
		std::cin >> usr_c1;;

		/* switch statement determining appropriate response */
		switch (usr_c1[0])
		{
		case '1': std::cin >> *anim; break;
		case '2': anim->DeleteFrame(); break;
		case '3': anim->EditFrame(); break;
		case '4': RUNNING = false; break;
		default: std::cout << "Please enter a valid option" << std::endl;
		}
	}

	std::cout << "Animation #" << index << " edit complete" << std::endl;
}

/***********************************************
Function name: DeleteAnimation
Purpose: Select member Animation and delete it

In parameters: 
 -- none
Out Parameter: void

Version: 1.0
Author: Brady McIntosh
************************************************/
void AnimationManager::DeleteAnimation()
{
	char usr_c1[65];	/* user input char buffer #1 */
	int a_count;		/* number of animations in the manager */
	int index;			/* animation index */

	std::cout << "Delete an Animation from the Animation Manager" << std::endl;

	/* initialize frame count */
	a_count = (int)animations.size();

	/* return if there are no frames */
	if (a_count == 0)
	{
		std::cout << "No Animations in the Animation Manager" << std::endl;
		return;
	}

	std::cout << "Which Animation do you wish to delete? " <<
		"Please give the index in the range 0 to " << animations.size() - 1 <<
		": " << std::endl;

	std::cin >> usr_c1;

	/* attempt to convert user input to integer */
	/* if entry is 1 char, and char is '0', set to 0 */
	if (strlen(usr_c1) == 1 && *usr_c1 == '0')
	{
		index = 0;
	}
	/* otherwise, convert using standard method */
	else
	{
		index = atoi(usr_c1);

		/* if conversion was invalid */
		if (index < 0 || index >= a_count)
		{
			std::cout << "That is an invalid index." << std::endl;
			return;
		}
	}

	/* no individual deletion of frames, may cause leaks */
	animations.erase(animations.begin() + index);

	std::cout << "Animation #" << index << " deleted" << std::endl;
}

/***********************************************
Function name: operator>>
Purpose: Operator overload - accept from input stream
	(Add an Animation to the Manager)

In parameters: 
 -- istream& - input stream
 -- AnimationManager& - the animation manager

Out Parameter: istream& - input stream
Version: 1.0
Author: Brady McIntosh
************************************************/
std::istream& operator>>(std::istream& in, AnimationManager& animManager)
{
	char usr_c1[65];	/* user input char buffer #1 */
	std::string str;	/* user input string pointer */

	std::cout << "Add an Animation to the Animation Manager" << std::endl;
	std::cout << "Please enter the Animation Name: ";

	/* accept and terminate user input */
	in >> usr_c1;
	usr_c1[64] = '\0';

	/* assign input to string value */
	str = std::string(usr_c1);

	/* instantiate and add animation to vector */
	//anim = new Animation(*str);
	animManager.animations.push_back(Animation(str));

	std::cout << "Animation " << str << " added to the back of animations" << std::endl;

	return in;
}

/***********************************************
Function name: operator<<
Purpose: Operator overload - send to output stream
	(Report details of Animation)

In parameters: 
 -- ostream& - output stream
 -- AnimationManager& - the animation manager
Out Parameter: ostream&  - output stream
Version: 1.0
Author: Brady McIntosh
************************************************/
std::ostream& operator<<(std::ostream& out, AnimationManager& animManager)
{
	int i;	/* loop index */

	out << "AnimationManager: " << animManager.managerName << std::endl;

	/* exit with warning if there are no animations */
	if (animManager.animations.size() == 0)
	{
		out << "No Animations to report" << std::endl;
		return out;
	}

	/* report each animation in vector */
	for (i = 0; i < animManager.animations.size(); ++i)
	{
		out << "Animation: " << i << std::endl;
		out << animManager.animations[i];
	}

	return out;
}
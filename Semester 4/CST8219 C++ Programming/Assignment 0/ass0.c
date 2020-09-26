/************************************************
Filename:ass0.c
Version: 1.0
Author: Brady McIntosh
Assignment Number:0
Assignment Name:Animation Project in C
Course Name: C++
Course Code: CST8219
Lab Section Number: 302
Professor's Name: Mariusz Makos
Due Date: 2019/01/21
Submission Date:2019/01/18
List of source files: ass0.c

Purpose: Until the User quits the program. It will read a valid response from the keyboard
in order to go through the process of either creating a frame of animation, 
editing a frame, displaying all frames or even delete a frame.
**********************************/

#define _CRT_SECURE_NO_WARNINGS
#define _CRTDBG_MAP_ALLOC // need this to get the line identification
//_CrtSetDbgFlag(_CRTDBG_ALLOC_MEM_DF|_CRTDBG_LEAK_CHECK_DF); // in main, after local declarations
//NB must be in debug build

#include <crtdbg.h>
#include <stdio.h>
#include <string.h>

typedef enum { FALSE = 0, TRUE } BOOL;

struct Frame {
	char* frameName;
	struct Frame* pNext;
};

typedef struct {
	char* animationName;
	struct Frame* frames;
}Animation;

// Forward declarations
void InitAnimation(Animation*);
void InsertFrame(Animation*);
void DeleteFrame(Animation*);
void EditFrame(Animation*);
void ReportAnimation(Animation*);
void CleanUp(Animation*);

/***********************************************
Function name: main
Purpose: main function

In parameters: None
Out Parameters: 0 for Success
Version: 1.0
Author: Mariusz Makos
************************************************/
int main(void)
{
	char response;
	BOOL RUNNING = TRUE;
	Animation RG;
	_CrtSetDbgFlag(_CRTDBG_ALLOC_MEM_DF | _CRTDBG_LEAK_CHECK_DF);
	InitAnimation(&RG);
	while (RUNNING)
	{
		printf("MENU\n 1. Insert a Frame at the front\n 2. Delete last Frame\n 3. Edit a Frame\n 4. Report the Animation\n 5. Quit\n");
		scanf("%c", &response);
		switch (response)
		{
		case '1':InsertFrame(&RG); break;
		case '2':DeleteFrame(&RG); break;
		case '3':EditFrame(&RG); break;
		case '4':ReportAnimation(&RG); break;
		case '5':RUNNING = FALSE; CleanUp(&RG); break;
		default:printf("Please enter a valid option\n");
		}
	}
	return 0;
}


/***********************************************
Function name: InitAnimation
Purpose: Initialize the Animation list;
Allocate memory for name string.

In parameters: Animation* RG
Out Parameters: none
Version: 1.0
Author: Brady McIntosh
************************************************/
void InitAnimation(Animation* RG)
{	/* create linked list, allocate for struct & name*/
	
	int c;
	char usr[65];
	usr[64] = '\0';

	/* accept user input, then flush input buffer */
	printf("Please enter the Animation name:");
	scanf("%64s", usr);
	do {
		c = getchar();
	} while (c != '\n' && c != EOF);

	/* allocate memory for name, then set user input */
	RG->animationName = (char*)malloc(sizeof(char) * (strlen(usr) + 1));

	/* if memory allocation failed */
	if (RG->animationName == NULL)
	{
		printf("Error: No free memory");
		return;
	}

	strcpy(RG->animationName, usr);
	RG->frames = NULL;
}

/***********************************************
Function name: InsertFrame
Purpose: Insert a Frame at the start of the Animation list.

In parameters: Animation* RG
Out Parameters: none
Version: 1.0
Author: Brady McIntosh
************************************************/
void InsertFrame(Animation* RG)
{	/* add element to linked list, allocate for struct & name */

	int c;
	struct Frame* frame, * next;
	char usr[65];
	usr[64] = '\0';

	printf("Insert a Frame in the Animation\n");

	/* if there is no first frame */
	if (RG->frames == NULL)
	{
		RG->frames = (struct Frame*) malloc(sizeof(struct Frame));
		frame = RG->frames;
		next = NULL;
	}
	/* if first frame exists */
	else
	{
		next = RG->frames;
		frame = (struct Frame*) malloc(sizeof(struct Frame));
		RG->frames = frame;
	}

	/* if memory allocation failed */
	if (frame == NULL)
	{
		printf("Error: No free memory\n");
		return;
	}

	/* accept user input, then flush input buffer */
	printf("Please enter the Frame frameName:");
	scanf("%64s", usr);
	do {
		c = getchar();
	} while (c != '\n' && c != EOF);

	/* allocate memory for name, then set user input */
	frame->frameName = (char*)malloc(sizeof(char) * (strlen(usr) + 1));

	/* if memory allocation failed */
	if (frame->frameName == NULL)
	{
		printf("Error: No free memory\n");
		return;
	}

	strcpy(frame->frameName, usr);
	frame->pNext = next;
}

/***********************************************
Function name: DeleteFrame
Purpose: Delete a Frame at the end of the Animation list.

In parameters: Animation* RG
Out Parameters: none
Version: 1.0
Author: Brady McIntosh
************************************************/
void DeleteFrame(Animation* RG)
{	/* remove and de-allocate element in linked list */
	int c;
	struct Frame* frame = NULL;

	/* if no frames exist */
	if (RG->frames == NULL)
	{
		printf("There are no Frames in the list.\n");
	}
	/* if frames do exist */
	else
	{
		/* hook first frame */
		frame = RG->frames;

		/* if frames == 0 */
		if (RG->frames->pNext == NULL)
		{
			/* unlink frame from animation */
			RG->frames = NULL;
		}
		/* if frames > 1 */
		else
		{
			struct Frame* last = NULL;
			while (frame->pNext != NULL)
			{
				last = frame;
				frame = frame->pNext;
			}

			last->pNext = NULL;
		}
	}

	free(frame->frameName);
	free(frame);

	do {
		c = getchar();
	} while (c != '\n' && c != EOF);
}

/***********************************************
Function name: EditFrame
Purpose: Change the name of a user-selected Frame
within the Animation list.

In parameters: Animation* RG
Out Parameters: none
Version: 1.0
Author: Brady McIntosh
************************************************/
void EditFrame(Animation* RG)
{	/* select element in linked-list to rename, free and allocate for new name */

	int i = 0, j, c, rsp = 0;
	char usr[65];
	struct Frame* frame;
	char* temp;

	usr[64] = '\0';
	frame = RG->frames;

	printf("Edit a Frame in the Animation\n");

	while (frame != NULL)
	{
		i++;
		frame = frame->pNext;
	}

	if (i == 0)
	{
		printf("There are 0 frames in the list.\n");
		return;
	}

	/* accept user input, then flush input buffer */
	printf("There are %d frame(s) in the list. Please specify the index (<=0) to edit at : ", i);
	scanf("%d", &rsp);
	do {
		c = getchar();
	} while (c != '\n' && c != EOF);
	
	/* if response is an invalid index */
	if (rsp < 0 || rsp >= i)
	{
		printf("That is an invalid index\n");
		return;
	}

	frame = RG->frames;

	/* if not-first frame is selected */
	if(rsp != 0)
	{
		for (j = 0; j < rsp; j++)
		{
			frame = frame->pNext;
		}
	}

	printf("The name of this Frame is %s. What do you wish to replace it with?", frame->frameName);
	scanf("%64s", usr);
	do {
		c = getchar();
	} while (c != '\n' && c != EOF);

	/* allocate memory for name, then set user input */
	temp = (char*)malloc(sizeof(char) * (strlen(usr) + 1));
	/* if memory allocation failed */
	if (frame->frameName == NULL)
	{
		printf("Error: No free memory");
		return;
	}

	free(frame->frameName);
	strcpy(temp, usr);
	frame->frameName = temp;
}

/***********************************************
Function name: ReportAnimation
Purpose: Print animation details

In parameters: Animation* RG
Out Parameters: none
Version: 1.0
Author: Brady McIntosh
************************************************/
void ReportAnimation(Animation* RG)
{	/* display animation details (frames, etc.) in printout */

	int c;
	int i = 0;
	struct Frame* frame;

	/* clear input juice from menu */
	do {
		c = getchar();
	} while (c != '\n' && c != EOF);

	printf("Animation name is %s\n", RG->animationName);
	printf("Report the Animation\n");

	if (RG->frames == NULL)
	{
		printf("There are 0 frames in the list.\n");
		return;
	}

	frame = RG->frames;

	do {
		printf("Image #%d, file name = %s\n", i, frame->frameName);

		frame = frame->pNext;
		i++;
	} while (frame != NULL);

}

/***********************************************
Function name: Cleanup
Purpose: Free all memory used by the Animation list
and its Frames.

In parameters: Animation* RG
Out Parameters: none
Version: 1.0
Author: Brady McIntosh
************************************************/
void CleanUp(Animation* RG)
{	/* free all allocated memory and destroy linked list */
	
	int i = 0, j;
	struct Frame* frame = NULL, * prev = NULL;

	frame = RG->frames;

	if (frame != NULL)
	{
		do {
			prev = frame;
			frame = frame->pNext;

			free(prev->frameName);
			free(prev);

		} while (frame != NULL);
	}

	free(RG->animationName);

	return;
}
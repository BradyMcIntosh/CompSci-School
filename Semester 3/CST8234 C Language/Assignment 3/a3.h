/* PROGRAM: A more advanced card-shuffling program
   AUTHOR: Brady McIntosh
   DATE: 3 Dec 2018
   PURPOSE: To contain global constants, types, methods, etc.
*/

#define A 4
#define K 3
#define Q 2
#define J 1
#define SUIT_NUM 4
#define SUIT_SIZE 13
#define DECK_SIZE 52

enum suit {S,H,C,D};

struct card {
	enum suit csuit;
	char crank;
	int cpoint;
};
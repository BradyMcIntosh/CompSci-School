CC = gcc
CC_FLAGS = -g -ansi -pedantic -Wall -W

FILES = a3_cards.c a3_files.c a3_main.c
OUT_EXE = a3

build: $(FILES)
	$(CC) $(CC_FLAGS) -o $(OUT_EXE) $(FILES)

clean:
	rm -f *.o core *.exe *~

rebuild: clean build
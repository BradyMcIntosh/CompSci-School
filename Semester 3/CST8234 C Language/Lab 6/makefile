CC = gcc
CC_FLAGS = -g -ansi -pedantic -Wall

FILES = lab6_main.c lab6_data.c lab6_search.c
OUT_EXE = lab6

build: $(FILES)
	$(CC) $(CC_FLAGS) -o $(OUT_EXE) $(FILES)

clean:
	rm -f *.o core *.exe *~

rebuild: clean build
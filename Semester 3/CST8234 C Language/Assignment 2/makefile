CC = gcc
CC_FLAGS = -g -ansi -pedantic -Wall -W

FILES = assign2_main.c assign2_data.c
OUT_EXE = assign2

build: $(FILES)
	$(CC) $(CC_FLAGS) -o $(OUT_EXE) $(FILES)

clean:
	rm -f *.o core *.exe *~

rebuild: clean build
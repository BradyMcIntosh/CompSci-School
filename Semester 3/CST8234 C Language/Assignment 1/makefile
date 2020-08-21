CC = gcc
CC_FLAGS = -g -ansi -pedantic -Wall

FILES = assign1_data.c assign1_main.c
OUT_EXE = assign1

build: $(FILES)
	$(CC) $(CC_FLAGS) -o $(OUT_EXE) $(FILES)

clean:
	rm -f *.o core *.exe *~

rebuild: clean build
OBJS=menu.o fichier.o date.o
EXEC=prog
INSTALLDIR=.
LIBS=~/librairie
CC=gcc

prog:	$(OBJS)
	$(CC) $(OBJS) -o $(EXEC)
date.o: date.c header.h
	$(CC) -c date.c
menu.o: menu.c header.h
	$(CC) -c menu.c
fichier.o: fichier.c header.h
	$(CC) -c fichier.c
install:
	cp $(EXEC) $(INSTALLDIR)/prog2
clean:
	rm *.o prog

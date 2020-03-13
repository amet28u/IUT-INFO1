#include <stdlib.h>
#include <stdio.h>
#include <errno.h>
#include <string.h>
#include <unistd.h>
#include <fcntl.h>

int main(int argc, char* argv[]){

  FILE* fifo;

  if ((fifo = fopen(argv[1], "r")) == -1){
    perror("fopen");
    exit(1);
  }

  char data[50];
  
  fscanf (fifo, "%s ", data);
  printf("%s\n", data);
  fclose (fifo);

  return 0;
}

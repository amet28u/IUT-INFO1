#include <stdlib.h>
#include <stdio.h>
#include <errno.h>
#include <string.h>
#include <unistd.h>
#include <fcntl.h>

int main(int argc, char* argv[]){

  if (mkfifo(argv[1], 0777) == -1){
    perror("fifo");
    exit(1);
  }

  int fd;

  if ((fd = open(argv[1] , O_WRONLY)) == -1){
    perror("open");
    exit(1);
  }

  if ((write (fd, argv[2], strlen(argv[2]))) == -1){
    perror("write");
    exit(1);
  }
  
  close (fd);

  return 0;
}

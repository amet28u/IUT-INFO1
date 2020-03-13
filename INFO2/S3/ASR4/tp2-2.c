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

  int fd = open(argv[1] , O_WRONLY);
  write (fd, argv[2], strlen(argv[2]));
  close (fd);

  return 0;
}

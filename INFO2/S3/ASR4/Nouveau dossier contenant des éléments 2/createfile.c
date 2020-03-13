#include <stdio.h>
#include <stdlib.h>
#include <errno.h>
#include <fcntl.h>
#include <unistd.h>
#include <string.h>

int main(int argc, char* argv[]){

  int fd;
  char* msg1 = "une ligne1 qui devrait potentiellement même certainement contenir dix mots\n";
  char* msg2 = "une ligne2 qui devrait potentiellement même certainement contenir dix mots\n";
  char* msg3 = "une ligne3 qui devrait potentiellement même certainement contenir dix mots\n";
  char* msg4 = "une ligne4 qui devrait potentiellement même certainement contenir dix mots\n";
  char* msg5 = "une ligne5 qui devrait potentiellement même certainement contenir dix mots\n";

  if ((fd = open(argv[1], O_CREAT | O_WRONLY | O_TRUNC)) == -1){
    perror("open");
    exit(1);
  }

  //msg1
  if ((write(fd, msg1, strlen(msg1))) == -1){
    perror("write");
    exit(1);
  }
  //msg2
  if ((write(fd, msg2, strlen(msg2))) == -1){
    perror("write");
    exit(1);
  }
  //msg3
  if ((write(fd, msg3, strlen(msg3))) == -1){
    perror("write");
    exit(1);
  }
  //msg4
  if ((write(fd, msg4, strlen(msg4))) == -1){
    perror("write");
    exit(1);
  }
  //msg5
  if ((write(fd, msg5, strlen(msg5))) == -1){
    perror("write");
    exit(1);
  }

  return 0;
}

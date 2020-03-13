#include <stdio.h>
#include <stdlib.h>
#include <fcntl.h>

#define NOMBRE_PROCESS 2

int main(int argc, char* argv[]){
  int pid, fd1, fd2, fd3;
  int check = 0;
  if ((fd1 = open("f", O_WRONLY)) == -1){
    perror("open");
    return -1;
  }
  if ((fd2 = open("g", O_WRONLY)) == -1){
    perror("open");
    return -1;
  }
  if ((fd3 = open("h", O_WRONLY)) == -1){
    perror("open");
    return -1;
  }
  for (int i = 0; i < NOMBRE_PROCESS; i++){
    switch (pid = fork()){
      case -1:
        perror("Fork"); exit(1);
      case 0:
        printf("PID du fils %d\n", getpid());
        printf("#---------------------------------------#\n");
        printf("PPID du fils %d\n", getppid());
        printf("#---------------------------------------#\n");
        if (check == 0){
          close(0);
          dup(fd1);
          close(2);
          dup(fd2);
          check = 1;
        }
        else{
          close(0);
          dup(fd1);
          close(1);
          dup(fd3);
        }
        exit(0);
      default:
        sleep(1);
        break;
    }
  }
}

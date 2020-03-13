#include <stdio.h>
#include <stdlib.h>
#include <errno.h>

int main(int argc, char* argv[]){
  int pid, status, pidf;
  for (int i = 1; i < argc; i++){
    switch (pid = fork()){
      case -1:
        perror("Fork"); exit(1);
      case 0:
        printf("PID du fils 1 %d\n", getpid());
        printf("PPID du fils 2 %d\n", getppid());
        execlp(argv[i], argv[i], NULL);
        exit(0);
      default:
        printf("PID du père 3 %d\n", getpid());
        printf("PPID du père 4 %d\n", getppid()); //952
        pidf=wait(&status);
        printf("Fils décédé : %d\n", pidf);
        //sleep(10);
        break;
    }
  }
  return 0;
}

#include <stdio.h>
#include <stdlib.h>
#include <errno.h>

int main(int argc, char* argv[]){
  int pid,status,pidf;
  int N=1;
  if (argc ==2)
    N = atoi(argv[1]);
  for (int i = 0; i < N; i++){
    switch (pid = fork()){
      case -1:
        perror("Fork"); exit(1);
      case 0:
        printf("PID du fils %d\n", getpid());
        printf("PPID du fils %d\n", getppid());
        exit(0);
      default:
        printf("PID du père %d\n", getpid());
        printf("PPID du père %d\n", getppid()); //952
        //pidf=wait(&status);
        //printf("Fils décédé : %d\n", pidf);
        sleep(10);
        break;
    }
  }
  return 0;
}

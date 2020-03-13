#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <errno.h>

int main(int argc, char* argv[]){
  /*
  if (argc != 2)
    printf("Usage: ./a.out <command>");
  else
    execlp(argv[1],argv[1],NULL);
  */
  int pid, status, pidf;
  char path[50];
  strcpy(path, "./");
  strcat(path, argv[1]);
  strcat(path, "/");
  strcat(path, argv[2]);
  printf("%s\n", path);

  switch (pid = fork()){
    case -1:
      perror("Fork"); exit(1);
    case 0:
      execlp("touch", "touch", path, NULL);
      exit(0);
    default:
      execlp("mkdir", "mkdir", argv[1], NULL);
      pidf=wait(&status);
      break;
  }
  return 0;
}

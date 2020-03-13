/*                                               */
/*      Utilisation de PIPE inter processus      */
/*                                               */
#include <stdio.h>
#include <sys/types.h>
#include <sys/uio.h>
#include <unistd.h>
#include <stdlib.h>
//#include <string.h>

int pip[2]; /* descripteur de pipe */

/*                                      */
/*      Le fils lit dans le pipe        */
/*                                      */
void fils(){
  int i,r;
  char rbuf[15];
  char wbuf[15];
  strcpy(wbuf, "Hello World        ");
  for(i=0;i<10;i++){
    rbuf[13]=i+48;
    rbuf[14]=0;
    if ((r=read(pip[0],rbuf,15)) != 15){
      perror("Erreur read");
      exit(1);
    }
    if ((r=write(pip[1],wbuf,15)) != 15){
      perror("Erreur write");
      exit(1);
    }
    printf("\nlu: %s", wbuf);
  }
}

/* */
/* Le pere ecrit dans le pipe */
/* */
void pere(){
  int i,r;
  char rbuf[15];
  char wbuf[15];
  strcpy(wbuf, "Hello World        ");
  for(i=0;i<10;i++){
    wbuf[13]=i+48;
    wbuf[14]=0;
    if ((r=write(pip[1],wbuf,15)) != 15){
      perror("Erreur write");
      exit(1);
    }
    if ((r=read(pip[0],rbuf,15)) != 15){
      perror("Erreur read");
      exit(1);
    }
    printf("\nlu: %s", wbuf);
  }
}

int main(){
  if (pipe(pip)){ /* Ouverture d'un pipe */
    perror("Erreur de pipe");
    exit(1);
  }
  printf("Dernier message avant fork\n");
  switch(fork()){ /* Creation d'un processus */
    case -1 :
      perror("Erreur de fork");
      exit(1);
    case 0 :
      fils();
      break;
    default :
      pere();
  }
  printf("\nFin programme\n");
  return 0;
}

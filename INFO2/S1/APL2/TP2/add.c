// fichier add.c
#include <stdio.h>
#include <stdlib.h>

int main(int argc, char **argv){

  int i; //variable de boucle
  long int sum=0; //somme des arguments

  for (i=1;i<argc;i++){
    long int tmp=strtol(argv[i],0,0); //transformation char* en long int
    sum=sum+tmp; //actualisation de la somme
  }
  printf("Resultat: %ld\n", sum); //%ld car long int
  return 0;
}

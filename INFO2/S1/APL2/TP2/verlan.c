// fichier verlan.c
#include <stdio.h>
#include <stdlib.h>

int main(int argc, char **argv){

  int i; //variable de boucle

  for (i=argc-1;i>0;i--){ //je passe tout les *char a l envers
    printf("%s ", argv[i]);
  }
  printf("\n"); //retour a la ligne
  return 0;
}

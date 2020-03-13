// fichier verlanfull.c
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(int argc, char **argv){

  int i, j; //variables de boucle

  for (i=argc-1;i>0;i--){ //je passe tout les *char a l envers
    for (j=strlen(argv[i])-1;j>=0;j--){ //je passe tout les char a l envers
      printf("%c",argv[i][j]);
    }
    printf(" "); //correspondance des mots
  }
  printf("\n"); //retour a la ligne
  return 0;
}

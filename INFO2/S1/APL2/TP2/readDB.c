// fichier readDB.c
#include <stdio.h>
#include <stdlib.h>

int main(int argc, char **argv){

  FILE *file; //fichier courant
  int age=0, ageTmp, cptH=0, cptPers=0;
  char genre;
  float val; //curseur

  if (argc != 2){ //test d'arguments
    fprintf(stderr, "Erreur: Nombre d'arguments\n");
    return 1;
  }

  file=fopen(argv[1],"r"); //ouverture fichier

  if (!file){ //test de lecture du fichier
    fprintf(stderr, "Impossible d'ouvrir le fichier %s en ecriture\n", argv[1]);
    return 2;
  }

  while (fscanf(file,"%*s %*s %d %c", &ageTmp, &genre) != 1){
    age+=ageTmp;
    if (genre == 'H'){
      cptH+=1;
    }
    cptPers+=1;
  }

  printf("Le fichier contient %d noms de personnes, dont %d hommes et %d femmes, avec un âge moyen de %d ans\n", cptPers, cptH, cptPers-cptH, age/cptPers);

  return 0;
}

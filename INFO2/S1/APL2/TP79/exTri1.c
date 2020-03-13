#include <stdio.h>
#include <stdlib.h>

int cmpFloat(const void *a, const void *b)
{
  const float *fa=(const float *)a, *fb=(const float *)b;
  if (*fa < *fb) return -1;
  if (*fa > *fb) return 1;
  return 0;
}

int main(int argc, char *argv[])
{
  // on vérifie qu'on a au moins un nombre passé en argument
  if (argc < 2)
    {
      fprintf(stderr,"Erreur : il faut au moins un nombre\n");
      return 1;
    }
  // allocation du tableau qui va stocker les nombres passés en argument
  float *tab=malloc((argc-1)*sizeof(float));
  // conversion chaîne vers float de chaque argument, et stockage dans le tableau
  int i;
  for (i=0; i<argc-1; i++)
    tab[i] = strtof(argv[i+1],0);

  // affichage du tableau initial
  printf("Tableau initial\n");
  for (i=0;i<argc-1; i++)
    printf("%f ", tab[i]);
  printf("\n");

  // tri du tableau en utilisant la fonction cmpFloat pour la comparaison
  qsort(tab,argc-1, sizeof(float), cmpFloat);

  // affichage du tableau trié
  printf("Tableau trié\n");
  for (i=0;i<argc-1; i++)
    printf("%f ", tab[i]);
  printf("\n");

  // libération propre de la mémoire allouée
  free(tab);
  return 0;
}

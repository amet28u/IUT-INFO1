// fichier testkdtree.c
#include <stdlib.h>
#include "kdtree.h"

int main(int argc, char *argv[]){
  unsigned int N;
  Point *Ptab=readPoints(argv[1], &N);
  kdTree *K=genKdTree(Ptab, N, 0);
  // affichage arbre
  delKdTree(K);
  free(Ptab);
  return 0;
}

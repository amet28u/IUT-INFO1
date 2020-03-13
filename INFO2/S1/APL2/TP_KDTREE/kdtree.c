// fichier kdtree.c
#include <stdio.h>
#include <stdlib.h>
#include "kdtree.h"

Point *readPoints(char fname[], unsigned int *N){ // fct pour lire les données, fname nom du fichier, N nombre de points
  FILE *data=fopen(fname, "r"); // ouverture fichier

  if (!data){ // impossible lecture fichier
    fprintf(stderr, "Erreur : impossible d'ouvrir le fichier %s en lecture\n", fname);
    *N=0;
    return (Point *)NULL;
  }
  *N=0;

  if (fscanf(data, "%u", N)==0){ // pas entier 1ere ligne
    fprintf(stderr, "Erreur : définition du nombre de points manquant\n");
    *N=0;
    fclose(data);
    return (Point *)NULL;
  }

  Point *p=malloc(*N * sizeof(Point)); // création mémoire

  double x, y;
  for (int i=0; i<*N; i++){
    if (fscanf(data, "%lf %lf", &x, &y)!=2){ // test nombre de valeur = *N
      fprintf(stderr, "Erreur : nombre de points\n");
      *N=0;
      free(p);
      fclose(data);
      return (Point *)NULL;
    }
    p[i].x=x; // attribution de la valeur de la premiere coo dans le tableau p
    p[i].y=y;
  }

  fclose(data);
  return p;
}

int cmpX(const void *a, const void *b){
  Point *fa=(Point *)a, *fb=(Point *)b;
  if (fa->x < fb->x) return -1;
  if (fa->x > fb->x) return 1;
  return 0;
}

int cmpY(const void *a, const void *b){
  Point *fa=(Point *)a; Point *fb=(Point *)b;
  if (fa->y < fb->y) return -1;
  if (fa->y > fb->y) return 1;
  return 0;
}

kdTree *newKdTree(int dir, Point A){
  kdTree *K=malloc(sizeof(kdTree));
  K->dir=dir;
  K->data=A;
  K->sag=(kdTree *)NULL;
  K->sad=(kdTree *)NULL;
  return K;
}

void delKdTree(kdTree *K){
  if (K!=NULL){
    // sous-arbre présent à gauche
    delKdTree(K->sag);
    // sous-arbre présent à droite
    delKdTree(K->sad);
    free(K); // plus de sous arbres
  }
}

kdTree *genKdTree(Point *T, int N, int dir){
  kdTree *K=malloc(sizeof(kdTree));
  if (N<=0) return (kdTree *)NULL;
  if (dir==0) qsort(T, N, sizeof(Point), cmpX);
  else qsort(T, N, sizeof(Point), cmpY);
  K=newKdTree(dir, T[N/2]);
  K->sag=genKdTree(&T[0], N/2, (dir+1)%2);
  K->sad=genKdTree(&T[(N/2)+1], N-(N/2)-1, (dir+1)%2);
  return K;
}

extern void PPV(kdTree *K, Point *Q, Point *P, int dist){
  
}

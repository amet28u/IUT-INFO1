#include <stdio.h>
#include <stdlib.h>
#include <math.h>

int suivant(int i){
  int j=i+1;
  return j;
}

float somme(unsigned int a, short b, float c){
  return a+b+c;
}

void message(char t[], char s[]){
  printf("Message (%s) : %s\n", t, s);
  return;
}

void sommeProduit(int *i1, int *j1){
  int k=(*i1)*(*j1);
  (*i1)=(*i1)+(*j1);
  (*j1)=k;
  return;
}

int main(){
  int k=3;
  unsigned int v1=70000;
  short v2=-16;
  float v3=0.5;
  int i=3;
  int j=4;

  j=suivant(k);
  printf("Le nombre suivant %d est %d\n", k, j);
  printf("La somme de %u, %d et %f est %f\n", v1, v2, v3, somme(v1,v2,v3));
  message("Erreur","Ceci est une erreur grave!!");
  message("Avertissement","Bon je vais laisser courir pour cette fois.");
  printf("i=%d ; j=%d\n", i, j);
  sommeProduit(&i,&j);
  printf("i=%d ; j=%d\n", i, j);
  return 0;
}

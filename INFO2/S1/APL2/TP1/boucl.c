#include <stdio.h>
#include <stdlib.h>
#include <time.h>

float aleat(){
  return (float)rand()/RAND_MAX;
}

int main(){
  srand(time(NULL));

  int N=rand()%200+1;

  float tab[N];

  int i;
  for (i=0; i<N; i++){
    tab[i]=aleat();
  }

  float m=0;
  for (i=0; i<N; i++){
    m+=tab[i];
  }
  m/=N;
  printf("La moyenne est : %f\n", m);

  for (i=0; tab[i]<0.9; i++);
  if (i<N){
    printf("Element trouve %f en position %d\n", tab[i], i);
  }
  else{
    printf("Aucun element trouve\n");
  }
  return 0;
}

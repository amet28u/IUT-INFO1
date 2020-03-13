#include <stdio.h>
#include <stdlib.h>

int main(){
  printf("Taille d'un char : %ld\n", sizeof(char));
  printf("Taille d'un unsigned char : %ld\n", sizeof(unsigned char));
  printf("Taille d'un short : %ld\n", sizeof(short));
  printf("Taille d'un unsigned short : %ld\n", sizeof(unsigned short));
  printf("Taille d'un int : %ld\n", sizeof(int));
  printf("Taille d'un unsigned int : %ld\n", sizeof(unsigned int));
  printf("Taille d'un long int : %ld\n", sizeof(long int));
  printf("Taille d'un long long int : %ld\n", sizeof(long long int));
  printf("Taille d'un float : %ld\n", sizeof(float));
  printf("Taille d'un double : %ld\n", sizeof(double));
  printf("Taille d'un long double : %ld\n", sizeof(long double));
  return 0;
}

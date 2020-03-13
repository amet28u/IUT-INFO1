#include <stdio.h>
#include <stdlib.h>

int f(int x,int n){
  int r=1,x2=x,masque=1;
  do{
    if (n&masque)
      r*=x2;
    x2*=x2;
  }while(n>>=1);
  return r;
}

int main(){
  int x,n,r;
  printf("Entrez x :");
  scanf("%d", &x);
  printf("Entrez n :");
  scanf("%d", &n);
  r=f(x,n);
  printf("Valeur de r : %d\n", r);
  return 0;
}

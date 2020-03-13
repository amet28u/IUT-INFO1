#include <stdio.h>
#include <stdlib.h>

int main(){
  char c='a';
  unsigned char uc=0;
  short s=-1;
  unsigned short us=65535;
  int i=0;
  unsigned int ui=-1;
  long int li=0;
  float f=0.5;
  double d=1e10;
  long double ld=-5e-3;
  printf("char\t\t: valeur=%c, taille=%ld\n", c, sizeof(c));
  printf("unsigned char\t: valeur=%d, taille=%ld\n", uc, sizeof(uc));
  printf("short\t\t: valeur=%d, taille=%ld\n", s, sizeof(s));
  printf("unsigned short\t: valeur=%d, taille=%ld\n", us, sizeof(us));
  printf("int\t\t: valeur=%d, taille=%ld\n", i, sizeof(i));
  printf("unsigned int\t: valeur=%u, taille=%ld\n", ui, sizeof(ui));
  printf("long int\t: valeur=%ld, taille=%ld\n", li, sizeof(li));
  printf("float\t\t: valeur=%f, taille=%ld\n", f, sizeof(f));
  printf("double\t\t: valeur=%lf, taille=%ld\n", d, sizeof(d));
  printf("long double\t: valeur=%Lf, taille=%ld\n", ld, sizeof(ld));
  return 0;
}

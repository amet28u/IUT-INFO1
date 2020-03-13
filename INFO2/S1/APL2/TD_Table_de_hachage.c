// table_de_hachage.c
#include <stdio.h>
#include <stdlib.h>
#include <errno.h>

// définition de la structure Cellule
typedef struct Cellule{
  char nom[50], prenom[50], tel[14]; // champs de la cellule
  struct Cellule *suc; // successeur
}Cellule;

// définition de la structure HachTable
typedef struct HachTable{
  int taille; // nombre élément de la table
  Cellule **table; // pointeur sur la table alloué
}HachTable;

void hachTableInit(HachTable *ht, int n);
Cellule *celMalloc(char *nom, char *prenom, char *tel);
int hach(char *ch, int n);

int main(int argc, char *argv[]){
  return 0;
}

void hachTableInit(HachTable *ht, int n){
  if (ht->table=(Cellule **)malloc(n * sizeof(Cellule *))==0){
    perror("Malloc");
    exit(1);
  }
  ht->taille=n;
  for (int i=0; i<n; i++) ht->table[i]=NULL;
}

Cellule *celMalloc(char *nom, char *prenom, char *tel){
  if (Cellule *C=(Cellule *)malloc(sizeof(Cellule))==0){
    perror("Malloc");
    exit(1);
  }
  strcpy(C->nom, nom);
  strcpy(C->prenom, prenom);
  strcpy(C->tel, tel);
  C->suc=NULL;
  return C;
}

int hach(char *ch, int n){
  return 0;
}

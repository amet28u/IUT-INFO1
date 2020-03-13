// tabledehachage.c
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
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
void listeAffich(Cellule *l);
void hachTableAffich(HachTable ht);
Cellule *hachTableRech(HachTable *ht, char *nom, char *prenom, char *tel);

int main(int argc, char *argv[]){
  char nom[50];
  char prenom[50];
  char tel[14];
  HachTable ht;
  char c='o';
  unsigned int TAILLE=10;

  hachTableInit(&ht, TAILLE);
    do{
      printf("Nom ?"); scanf("%s", nom);
      printf("Prenom ?"); scanf("%s", prenom);
      printf("Tel ?"); scanf("%s", tel);
      hachTableRech(&ht, nom, prenom, tel);
      printf("Encore (o/n) ?"); getchar();
    }while ((c=getchar())!='n');
  hachTableAffich(ht);

  return 0;
}

void hachTableInit(HachTable *ht, int n){
  ht->table=(Cellule **)malloc(n * sizeof(Cellule *));
  if (ht->table==0){
    perror("Malloc");
    exit(1);
  }
  ht->taille=n;
  for (int i=0; i<n; i++) ht->table[i]=NULL;
}

Cellule *celMalloc(char *nom, char *prenom, char *tel){
  Cellule *C=(Cellule *)malloc(sizeof(Cellule));
  if (C==0){
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
  int res=0;
  for (int i=0; i<n; i++) res+=(i+1)*(int)ch[i]; // somme des char et *i pour gérer les anagrammes
  res%=n; // modulo de la somme par la taille
  return res;
}

void listeAffich(Cellule *l){
  while (l!=NULL){ // on affiche tout les éléments d'une cellule du tableau d'adresse
    printf("Nom : %s, Prenom : %s, Tel : %s\n", l->nom, l->prenom, l->tel);
    l=l->suc;
  }
}

void hachTableAffich(HachTable ht){
  for (int i=0; i<ht.taille; i++){ // on affiche toutes les entrées à l'aide d'un for
    if (ht.table[i]!=0){ // on affiche pas si l'entrée est vide
      printf("Entrée %d :\n", i);
      listeAffich(ht.table[i]);
    }
  }
}

Cellule *hachTableRech(HachTable *ht, char *nom, char *prenom, char *tel){
  Cellule *L=ht->table[hach(nom, ht->taille)];
  printf("Cherche : %s - %s, pos : %d\n", L->nom, nom, hach(nom, ht->taille));
  fflush(stdout);
  Cellule *C=celMalloc(nom, prenom, tel);
  if (ht->table[hach(nom, ht->taille)]!=NULL){ // cellule du tableau d'adresse avec au moins 1 élément
    L=ht->table[hach(nom, ht->taille)];
    ht->table[hach(nom, ht->taille)]=C; // on actualise le tableau d'adresse
    C->suc=L; // on rattache le reste
  }
  else{
    ht->table[hach(nom, ht->taille)]=C; // on copie sur NULL notre adresse de C
    C->suc=NULL;
  }

  return C;
}

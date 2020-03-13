#include <stdlib.h>
#include <stdio.h>
#include <time.h>

typedef struct LISTE{
  int data;
  struct LISTE *suc;
  struct LISTE *prec;
}LISTE;

LISTE *creat(int n) {
  LISTE *L0, *temp;
  srand(time(NULL));
  temp=L0=(LISTE *)malloc(sizeof(LISTE));
  L0->prec=NULL;
  L0->data=rand();

  while (--n) {
    temp->suc=(LISTE *)malloc(sizeof(LISTE));
    temp->suc->prec=temp;
    temp=temp->suc;
    temp->data=rand();
  }

  temp->suc=NULL;
  return L0;
}

void affiche(LISTE *l) {
  int i=1;

  while (l!=NULL) {
    printf("Valeur du maillon %d : %d\n", i, l->data);
    l=l->suc;
    i+=1;
  }
}

LISTE *inserer(LISTE *l, int pos, int data) {
  LISTE *temp;

  while (pos>0) {
    l=l->suc;
    pos-=1;
  }

  if (l->prec==NULL) {
    temp=(LISTE *)malloc(sizeof(LISTE));
    temp->prec=NULL;
    temp->suc=l;
    l->prec=temp;
    return l;
  }

  if (l->suc==NULL) {
    temp=(LISTE *)malloc(sizeof(LISTE));
    temp->suc=NULL;
    temp->prec=l;
    l->suc=temp;
    return l;
  }

  temp=(LISTE *)malloc(sizeof(LISTE));
  temp->suc=l->suc;
  temp->prec=l;
  l->suc=temp;
  l->suc->prec=temp;
  temp->data=data;
  return l;
}

LISTE *supprimer(LISTE *l, int pos) {
  LISTE *temp;

  while (pos>0) {
    l=l->suc;
    pos-=1;
  }

  if (l->prec == NULL) {
    temp = l;
    l->suc->prec = NULL;
    free(temp);
    return l;
  }

  if (l->suc == NULL) {
    temp = l;
    l->prec->suc=NULL;
    free(temp);
    return l;
  }

  temp = l;
  l->prec->suc = l->suc;
  l->suc->prec = l->prec;
  free(temp);
  return l;
}

void triBulle(LISTE *L) {
  LISTE *temp;
  temp = L;
  int trie = 0;

  while (trie == 0 && temp != NULL) {
    trie = 1;

    if (temp->data > temp->suc->data)) {
      int tmp = temp->suc->data;
      temp->suc->data = temp->data;
      temp->data = tmp;
      trie = 0;
    }

    temp = temp->suc;

  }

  return temp;

}

void triInsert(LISTE *L) {
  LISTE *temp;
  temp = L;
  int i = 0, j = 0;

  while (temp != NULL) {
    int data = temp->data;
    LISTE *temp2 = temp;
    while (temp2 != NULL && temp2->prec->data > data) {
      temp2->data = temp2->prec->data;
      temp2 = temp2->prec;
    }
    temp2->data = data;
    temp = temp->suc;
  }
}

void triSelect(LISTE *L) {
  LISTE *temp;
  temp = L;
  int minimum = temp->data;

  while (temp != NULL) {
    LISTE *temp2 = temp->suc;
    while (temp2 != NULL) {
      if (temp2->data <= minimum) {
        minimum = temp2->data;
        LISTE *temp3 = temp2;
      }
      temp2 = temp2->suc;
    }
    temp->data = temp3->data
    temp = temp->suc;
  }
}

// ALGORITHME DE TRIS A FAIRE
LISTE *Echange(LISTE *L, int pos1, int pos2){ //A finir
  LISTE *temp, *L0;
  L0=L;
  int datatemp, i, delta=pos2-pos1;
  while (pos1>0){
    L=L->suc;
    pos1-=1;
  }
  temp=L;
  for (i=0;i<=delta;i++){
    L=L->suc;
  }
  datatemp=L->data;
  L->data=temp->data;
  temp->data=datatemp;
  return L0;
}

int main(){
  LISTE *L;
  int size, choix, val, pos;
  printf("Bienvenue dans l'algorithme des LISTEs chainées\n");
  printf("Choisissez la taille d'initialisation de votre LISTE :\n");
  scanf("%d", &size);
  L=creat(size);
  while (1){
    printf("---------------\n");
    printf("Que voulez-vous faire ?\n");
    printf("Fonction Insertion : 1\n");
    printf("Fonction Suppression : 2\n");
    printf("affiche LISTE : 3\n");
    printf("Quitter : 4\n");
    printf("---------------\n");
    scanf("%d", &choix);
    switch (choix){
      case 1:
        printf("Choisissez la valeur à ajouter :\n");
        scanf("%d", &val);
        printf("Choisissez la position où ajouter cette valeur :\n");
        scanf("%d", &pos);
        inserer(L, pos, val);
        printf("Insertion effectuée !\n");
        break;
      case 2:
        printf("Choisissez quelle maillon doit être ajouté :\n");
        scanf("%d", &pos);
        supprimer(L, pos);
        printf("Suppression effectuée !\n");
        break;
      case 3:
        affiche(L);
        printf("\n");
        break;
      case 4:
        return 0;
    }
  }
}

// fichier PILE.c

#include <stdlib.h>
#include <stdio.h>
#define TAILLEMAX 5

typedef struct pile{
  int pile[TAILLEMAX];
  int sommet;
}Pile;

int PileVide(Pile *p){
  if (p->sommet==-1){
    return 1;
  }
  return 0;
}

int PilePleine(Pile *p){
  if (p->sommet==TAILLEMAX-1){
    return 1;
  }
  return 0;
}

int Depiler(Pile *p){
  if (PileVide(p)==0){
    p->sommet-=1;
    return p->pile[p->sommet+1];
  }
  return 0;
}

void Empiler(Pile *p, int elt){
  if (PilePleine(p)==0){
    p->sommet+=1;
    p->pile[p->sommet]=elt;
  }
}

void Initialisation(Pile *p){
  p->sommet=-1;
}

int fact(int n){
  if (n==1){
    return 1;
  }
  else{
    return n*fact(n-1);
  }
}

int main(){
  Pile *p;
  p=(Pile *) malloc(sizeof(Pile));
  int elt, i, choix;
  Initialisation(p);
  printf("Bienvenue dans l'algorithme des piles\n");
  printf("Factorielle(5)=%d\n", fact(5));
  while (1){
    int var=5, r=1;
    printf("---------------\n");
    printf("Que voulez-vous faire ?\n");
    printf("Fonction PilePleine : 1\n");
    printf("Fonction PileVide : 2\n");
    printf("Fonction depiler : 3\n");
    printf("Fonction empiler : 4\n");
    printf("Affichage pile : 5\n");
    printf("Empilement de la fonction factorielle : 6\n");
    printf("Quitter : 7\n");
    printf("---------------\n");
    scanf("%d", &choix);
    switch(choix){
      case 1:
        if (PilePleine(p)==1){
          printf("La pile est pleine !\n");
        }
        else{
          printf("La pile n'est pas pleine !\n");
        }
        break;
      case 2:
        if (PileVide(p)==1){
          printf("La pile est vide !\n");
        }
        else{
          printf("La pile n'est pas vide !\n");
        }
        break;
      case 3:
        if (PileVide(p)==1){
          printf("Erreur: Pile vide!\n");
        }
        else{
          elt=Depiler(p);
          printf("Elément retiré : %d\n", elt);
        }
        break;
      case 4:
        printf("Choisissez un élément à ajouter :\n");
        scanf("%d", &elt);
        if (PilePleine(p)==1){
          printf("Erreur: Pile pleine!\n");
        }
        else{
          Empiler(p, elt);
        }
        break;
      case 5:
        printf("affichage pile %d\n", p->sommet);
        for (i=p->sommet; i>=0; i--){
          printf("%d --> %d\n", i, p->pile[i]);
        }
        break;
      case 6:
        while (var>0){
          Empiler(p, var);
          var-=1;
        }
        printf("affichage pile %d\n", p->sommet);
        for (i=p->sommet; i>=0; i--){
          printf("%d --> %d\n", i, p->pile[i]);
        }
        while (PileVide(p)==0){
          r*=Depiler(p);
        }
        printf("Fact(5)=%d\n", r);
        break;
      case 7:
        return 0;
    }
  }
}

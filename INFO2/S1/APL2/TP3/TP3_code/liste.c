// déclaration des fonctions externes
#include <stdio.h> // nécessaire pour printf
#include <stdlib.h> // nécessaire pour malloc, free, et strtol
#include <math.h> // nécessaire pour NAN

// DEFINITION D'UN TYPE BOOLEEN : un unsigned char qui encode Vrai par 1 et Faux par 0
typedef unsigned char Booleen; // après cette ligne Booleen est un nouveau type équivalent
                               // à unsigned char
Booleen Vrai=1; // Encodage de Vrai par 1
Booleen Faux=0; // Encodage de Faux par 0
Booleen Non(Booleen b) // fonction NOT (utilisée dans EstDans())
{
  return b==Vrai ? Faux : Vrai; // Note: emploi de l'opérateur ternaire
                                // x ? y : z <=> si x alors y sinon z
}

// DEFINITION D'UN TYPE ELEMENT : un float
typedef float Element; // après cette ligne Element est un nouveau type équivalent à float
Element element_invalide=NAN; // Encodage de l'element_invalide par NAN=Not a Number (voir math.h)
void AfficherElement(Element E) // fonction d'affichage d'un élément (utilisée dans Afficher())
{
  if (E == element_invalide)
    printf("INVALID\n");
  else
    printf("%f\n", E);
}

// DEFINITION DES SORTES POUR LA LISTE CHAINEE
// La cellule
struct Cellule
{
  Element data;
  struct Cellule *next;
};
typedef struct Cellule Cellule;

// la liste
typedef Cellule *Liste;
Liste liste_vide=(Liste)NULL;

// OPERATIONS SUR LA SORTE (A COMPLETER)
Liste Creer(Element E, Liste L)
{
  if (E == element_invalide) return liste_vide;

  Liste ret=malloc(sizeof(Cellule)); // allocation de la mémoire pour stocker une cellule (Liste=Cellule*)
  ret->data = E;  // rappel: le type Liste est un pointeur, donc ret->data <=> (*ret).data
  ret->next = L;  // idem que pour data
  return ret;
}

Booleen EstVide(Liste L)
{
  if (L == liste_vide) return Vrai;

  return Faux; // le else est inutile car le if provoque un return.
               // Ce genre de code, même s'il est moins clair qu'un else, permet d'aider le
               // compilateur à vérifier qu'il y a systématiquement un return dans la fonction
}

Element Contenu(Liste L)
{
  if (EstVide(L)) return element_invalide;

  return L->data;
}

Liste Succ(Liste L)
{
  if (EstVide(L)) return liste_vide;
  return L->next;
}

Liste Detruire(Liste L)
{
  if (EstVide(L)) return liste_vide;
  Liste tmp=Succ(L); // stockage de la suite de la liste, qui sera renvoyée par la fonction
  free(L);
  return tmp;
}

// FONCTIONS VUES EN TD
void Afficher(Liste L){
  Liste tmpL=L;
  while (EstVide(tmpL) == Faux){
    AfficherElement(Contenu(tmpL));
    tmpL=Succ(tmpL);
  }
}

unsigned int Longueur(Liste L){
  Liste tmpL=L;
  int n=0;
  while (EstVide(tmpL) == Faux){ //Tant que c'est pas vide on incrémente un compteur
    n+=1;
    tmpL=Succ(tmpL);
  }
  return n;
}

Liste Rechercher(Liste L, Element E){
  Liste tmpL=L;
  while (EstVide(tmpL) == Faux && Contenu(tmpL) != E){ //On regarde chaque cellule et on arrête quand on l'a trouvée ou quand on l'a parcouru entièrement
    tmpL=Succ(tmpL);
  }
  return tmpL;
}

Booleen EstDans(Liste L, Element E){
  return Non(EstVide(Rechercher(L,E)));
}

Liste Supprimer(Liste L, int r){
  /* Cas où on retire le premier élément de la Liste ou si la Liste est vide:
  Dans ce dernier cas, ça marche car Detruire(liste_vide) renvoie liste_vide */
  if (r == 0 || EstVide(L) == Vrai){
    return Detruire(L);
  }
  /* Sinon, recherche de la cellule précédent celle que l'on doit retirer
  -> tmpL */
  int i=0;
  Liste tmpL=L;
  /* Le cas particulier où tmpL est une liste_vide fonctionne ici cas Succ
  renvoie alors liste_vide. Dans ce cas, rien n'est fait par le Tant que
  puis rien n'est fait pour la mise à jour de Succ et enfin L, c'est à dire
  liste_vide, est renvoyé. */
  while (EstVide(Succ(tmpL)) == Faux && i < r-1){
    tmpL=Succ(tmpL);
    i+=1;
  }
  /* On détruit la cellule suivant celle trouvée et met à jour le Succ de la
  cellule détruite. Ceci fonctionne même si Succ(tmpL) = liste_vide (cas où
  r plus grand que la longueur de la Liste puisque Detruire renvoie alors
  liste_vide) */
  tmpL->next=Detruire(Succ(tmpL));
  /* On renvoie la tête de la Liste. */
  return L;
}

Liste Ajouter(Liste L, Element E, int r){
  /* Cas où E est element_invalide: on ne fait rien. */
  if (E == element_invalide){
    return L;
  }
  /* Cas où on ajoute en tête de Liste. Cela marche même si EstVide(L) car
  Succ(L) renvoie alors liste_vide. */
  if (r == 0){
    return Creer(E, L);
  }
  /* Sinon, recherche de la cellule de range r-1 -> tmpL. */
  int i=0;
  Liste tmpL=L;
  while (EstVide(Succ(tmpL)) == Faux && i < r-1){
    tmpL=Succ(tmpL);
    i+=1;
  }
  tmpL->next=Creer(E, Succ(tmpL)); //On rattache la liste
  /* On renvoie la tête de la Liste. */
  return L;
}

Liste Inverser(Liste L){
  Liste Linv=liste_vide, tmpL=L;
  while (EstVide(tmpL) == Faux){ //On met le 1er élément dans une liste puis le 2ème élément devant le premier etc
    Linv=Creer(Contenu(tmpL), Linv);
    tmpL=Succ(tmpL);
  }
  return Linv;
}

Liste Copier(Liste L){
  /* La création suivante fonctionne même quand L est la liste_vide. En effet,
  Contenu renvoie alors element_invalide et Creer renvoie liste_vide, qui est
  bien le résultat escompté. */
  Liste Lcopie=Creer(Contenu(L), liste_vide);
  /* La ligne suivante fonctionne bien si L est liste_vide puisque Succ renvoie
  alors liste_vide. */
  Liste tmpL=Succ(L);
  /* La variable tmpC va recueillir la référence vers la dernière cellule
  ajoutée en fin de Liste. */
  Liste tmpC=Lcopie;
  /* La boucle while suivante ne fera rien si L est liste_vide. Lcopie sera
  alors inchangée et liste_vide sera bien renvoyée par la fonction. */
  while (EstVide(tmpL) == Faux){ //On copie chaque cellule une par une jusqu'à que la liste soit vide
    tmpC->next=Creer(Contenu(tmpL), liste_vide);
    tmpC=Succ(tmpC);
    tmpL=Succ(tmpL);
  }
  return Lcopie;
}

Liste Vider(Liste L){
  while (EstVide(L) == Faux){ //On détruit chaque cellule une par une jusqu'à que la liste soit vide
    L=Detruire(L);
  }
  return L;
}

// EXEMPLE DE MAIN
int main(int argc, char *argv[]){
  int i;
  Liste L=liste_vide, tmpL;
  for (i=1; i<argc; i++) //création liste en fct des arguments passés en paramètres
    {
      Element E=strtof(argv[i],0); // strtof permet de traduire une chaîne de caractères en float
      L = Ajouter(L,E,0); // gère le cas où E est invalide
      printf("ajout de ");
      AfficherElement(E);
    }
  printf("Création et ajouts. Longueur: %d\n",Longueur(L));
  Afficher(L);
  Element P;
  printf("Entrez un élément à rechercher dans cette Liste : ");
  scanf("%f", &P);
  tmpL=Rechercher(L, P); //recherche de P
  if (EstVide(tmpL)){
    printf("Elément introuvable \n");
  }
  else{
    AfficherElement(Contenu(tmpL)); //affichage de P
  }
  Element S;
  printf("Entrez le rang de l'élément à supprimer dans cette liste : ");
  scanf("%f", &S);
  if (S > Longueur(L)){
    printf("Erreur: Rang supérieur à la taille de la liste \n");
    printf("Entrez le rang de l'élément à supprimer dans cette liste : ");
    scanf("%f", &S);
  }
  Afficher(Supprimer(L, S-1));
  Element A, B;
  printf("Entrez un élément à ajouter dans cette liste : ");
  scanf("%f", &A);
  printf("Indiquez le rang où vous voulez le placer : ");
  scanf("%f", &B);
  while (B > Longueur(L)){
    printf("Erreur: Rang supérieur à la taille de la liste \n");
    printf("Indiquez le rang où vous voulez le placer : ");
    scanf("%f", &B);
  }
  Afficher(Ajouter(L, A, B-1));
  printf("Inversion de la liste ... \n");
  Afficher(Inverser(L));
  printf("Effaçage de la liste ... \n");

  return 0;
}

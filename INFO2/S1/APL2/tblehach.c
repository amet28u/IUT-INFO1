//
// Ver:1.0	Janvier 2019			Gestion d'une TABLE DE HACHAGE
//

#include <stdlib.h>
#include <stdio.h>
#include <string.h>

#define TAILLE		// Taille de la Table

// Type des cellules pour les listes chaînées
typedef struct Cellule {
			// Nom 50 caractères
 			// Prénom 50 caractères
 			// Num de Tel 14 caractères
          		// Pointeur sur la cellule suivante
} Cellule;


// Type pour les tables de hachage
typedef struct HachTable {
                	    // Nombre d'éléments de la table
 		            // Pointeur sur la table allouée
}HachTable;


// Allocation et initialisation d'une table de hachage
void hachTableInit(HachTable* ht, int n)
{

  // Nombre d'éléments de la table


  // Allocation de la table




  // Initialisation de la table
  // Au début toutes les listes sont vides


    			// Liste vide

}

// Fonction de hachage
int hach(char* ch, int n)
{
  int h = 0;			// Valeur calculée
  int i;			// Indice de parcours de la chaîne







 return h;
}




// Allocation mémoire et intialisation d'une cellule
Cellule* cellMalloc(char* nom, char *prenom, char *tel)
{
  // Allocation de la cellule proprement dite



  // Copie des informations dans la cellule





  return c;
}


// Recherche et insertion d'une clellule dans une table
// la fonction de hachage retourne un indice calculé par rapport au nom
// Une nouvelle cellule est crée
// Si la table contient déjà l'adresse d'une cellule
// la nouvelle cellule est insérée en tête de liste
// sinon l'adresse de la nouvelle cellule est stocké dans la table
// enfin son adresse est retournée

Cellule* hachTableRech(HachTable* ht, char* nom, char* prenom, char *tel )
{
  // Liste où doit se trouver la cellule
  Cellule *l = ht->table[hach(nom, ht->taille)];
	//printf(" Cherche: %s - %s pos: %d\n",l->nom, nom,hach(nom,ht->taille));fflush(stdout);
  Cellule* c = cellMalloc(nom, prenom, tel);   // création d'une nouvelle cellule



     					// si l'index est déja pris
						// Trouvé
  						// la nouvelle cellule est insérée en tête de liste



    							// sinon
	{   				     		// L'index est libre
  						      	// l'adresse de la nouvelle cellule est mise dans la table

	}
  return c;
}







// Affichage d'une liste
void listeAffich(Cellule* l)
{
  					//tant que la liste contient des éméùents
    	        			// on affiche nom prenomù et Tel
         				// on passe au suivant
  }
}


// Affichage complet d'une table de hachage
void hachTableAffich(HachTable ht)
{
  for (int i = 0; i < ht.taille; i++)
    if (ht.table[i] != 0) {
      printf("\nEntrée %d :\t", i);
      listeAffich(ht.table[i]);
    }
}


// Programme principal
int main(int argc, char* argv[])
{
  char nom[50];			// Nom 50 caractères
  char prenom[50];		// Prénom 50 caractères
  char tel[14];			// Num de Tel 14 caractères
  HachTable ht;
  char c='o';

  // Initialisation de la table
  hachTableInit(&ht, TAILLE);
   do{
	printf("Nom? ");scanf("%s",nom);
	printf("Prénom? ");scanf("%s",prenom);
	printf("Tel? ");scanf("%s",tel);
 	hachTableRech(&ht, nom, prenom, tel );
	printf("Encore (o/n)? ");getchar();
    }while((c=getchar())!='n');

  // Affichage de la table de Hachage
  hachTableAffich(ht);

}

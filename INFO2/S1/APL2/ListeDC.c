#include<stdio.h>
#include<stdlib.h>
#include<time.h>


// Structure d'une liste doublement chaînée
typedef struct LISTE{
	int data;
	struct LISTE *suc;
	struct LISTE *prec;
} LISTE;


// Prototypes des fonctions utilisées
LISTE *creat(int n);				// Fonction de création de la liste
void affiche(LISTE *l); 			// Fonction d'affichage de la liste
void triBulle(LISTE *l);  			// Fonction de tri à bulle de la liste
void triInsert(LISTE *l);  			// Fonction de tri par insertion de la liste
void echange(LISTE *m1, LISTE *m2); 		// Fonction d'échange des données de deux maillons de la liste
LISTE *imin(LISTE *l);   			// Fonction qui retourne l'adresse du maillon contenant la plus petite donnée d'une sous-liste
void triSelect(LISTE *l);  			// Fonction de tri par sélection de la liste
LISTE *inserer(LISTE *l, LISTE *m, int pos);  	// Fonction d'insertion d'un maillon m dans une liste l à une position pos
LISTE *supprimer(LISTE *l, int pos);		// Fonction de suppression d'un maillon d'une position pos





//  Programme principal de manipulation d'une LISTE DOUBLEMENT CHAINÉE
int main(int argc, char * argv[]){
 int n=10,i=1;
 LISTE *l1, *m1;
 if (argc > 1) n=atoi(argv[1]);    // Si un argument est transmis au programme ce sera la taille de la liste par défaut n vaut 10

 l1=creat(n);  // Création de la liste
 affiche(l1);

 //triBulle(l1);
 //triInsert(l1);
 triSelect(l1);
 m1=(LISTE *)malloc(sizeof(LISTE));

 printf("\t\tLISTE TRIéE\n");
 affiche(l1);

 printf("\tINSERTION\n\tDonnée? ");
 scanf("%d",&m1->data);
 printf("\tPosition? ");
 scanf("%d",&i);
 l1=inserer(l1,m1,i);

 printf("\t\tLISTE TRIéE\n");
 affiche(l1);

 printf("\tSUPPRESSION\n\tPosition? ");
 scanf("%d",&i);
 l1=supprimer(l1,i);


 printf("\t\tLISTE TRIéE\n");
 affiche(l1);
}

// Fonction de création de la liste
LISTE *creat(int n){
	LISTE *d,*c;
	srand(time(NULL));
	d=c=(LISTE *)malloc(sizeof(LISTE));  // Création et initialisation du premier maillon de la chaîne
	d->data=rand()%10;
	d->prec=NULL;

	for(int i=0; i<n-1;i++){
		c->suc=malloc(sizeof(LISTE));
		c->suc->data=rand()%100;
		c->suc->prec=c;
		c=c->suc;
	}
	c->suc=NULL;

	return d;
}





// Fonction d'affichage de la liste
void affiche(LISTE *l){
	while(l!=NULL){
		printf("%i\n", l->data);
		l=l->suc;
	}
}


// Fonction de tri à bulle de la liste
void triBulle(LISTE *l){
  LISTE *l1=l;
  int trie=0;

  while(trie==0){
	  trie=1;
	  l1=l;
	  while(l1->suc != NULL){
		  if(l1->data>l1->suc->data){
			  echange(l1,l1->suc);
			  trie=0;
		  }
		  l1=l1->suc;
	  }
  }

}

/*
int main(){
	LISTE *l = creat(5);
	printf(" Liste de base : \n\n");
	affiche(l);
	triInsert(l);
	printf(" Liste triée : \n\n");
	affiche(l);
} */


// Fonction de tri par insertion de la liste
void triInsert(LISTE *l){
	LISTE *l1=l->suc, *t;

	while(l1){
		t=l1->prec;
		int d=l1->data;
		while(t && d < t->data){
			t->suc->data=t->data;
			t=t->prec;
		}
		if(t)
			if(d<t->data) t->data=d;
			else t->suc->data=d;
		else l->data=d;
		l1=l1->suc;
	}
}


// Fonction d'échange des données de deux maillons de la liste
void echange(LISTE *m1, LISTE *m2){
	int data;
	data=m1->data;
	m1->data=m2->data;
	m2->data=data;
}


// Fonction qui retourne l'adresse du maillon contenant la plus petite donnée d'une sous-liste
LISTE *imin(LISTE *l){
  LISTE *l1=l->suc;

	while(l1!=NULL){
		if(l1->data < l->data){
			l=l1;
		}
		l1=l1->suc;
	}

  return l;
}


// Fonction de tri par sélection de la liste
void triSelect(LISTE *l){
	LISTE *l1=l;
	while(l){
		echange(l, imin(l));
		l=l->suc;
	}
}


// Fonction de suppression d'un maillon d'une position pos
LISTE *supprimer(LISTE *l, int pos){
  LISTE *c=l;
  if (c==NULL) return NULL;
  for(int i=0; i<pos-1; i++){
	  c=c->suc;
  }
  if(c->suc && c->prec){
	  c->prec->suc = c->suc;
	  c->suc->prec = c->prec;
  }else if(!c->suc){
	  c->prec->suc=NULL;
  }else if(!c->prec){
	  c=c->suc;
	  free(c->prec);
	  c->prec=NULL;
	  return c;
  }
  free(c);

 return l;
}

// Fonction d'insertion d'un maillon m dans une liste l à une position pos
LISTE *inserer(LISTE *l, LISTE *m, int pos){
  LISTE *c=l;
  if (c==NULL || m==NULL) return NULL;
  if(pos==1){
	m->prec=NULL;
	m->suc=l;
	c->prec=m;
	return m;
  }

  for(int i=0;i<pos-1;i++){
	  c=c->suc;
  }
  if(!c->suc){
	  m->suc=NULL;
	  c->suc=m;
	  m->prec=c;
	  return l;
  }
  m->prec=c->prec;
  m->suc=c;
  c->prec->suc=m;
  c->prec=m;
  return l;
}

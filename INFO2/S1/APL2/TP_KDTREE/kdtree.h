// fichier kdtree.h
// définition de la structure Point
typedef struct{
  double x, y;
}Point;

// définition de la structure kdTree
typedef struct kdTree{
  int dir;
  Point data;
  struct kdTree *sag, *sad;
}kdTree;

// définitions des fonctions
extern Point *readPoints(char fname[], unsigned int *N);
extern int cmpX(const void *a, const void *b);
extern int cmpY(const void *a, const void *b);
extern kdTree *newKdTree(int dir, Point A);
extern void delKdTree(kdTree *K);
extern kdTree *genKdTree(Point *T, int N, int dir);
extern void PPV(kdTree *K, Point *Q, Point *P, int dist);

class Sudoku{
  static int[][] grille = {
          {1,0,0,0,0,7,0,9,0},
          {0,3,0,0,2,0,0,0,8},
          {0,0,9,6,0,0,5,0,0},

          {0,0,5,3,0,0,9,0,0},
          {0,1,0,0,8,0,0,0,2},
          {6,0,0,0,0,4,0,0,0},

          {3,0,0,0,0,0,0,1,0},
          {0,4,0,0,0,0,0,0,7},
          {0,0,7,0,0,0,3,0,0},
  };

  public static void affichage(){
    for (int i = 0; i < 9; i++){
      System.out.print("[ ");
      for (int j = 0; j < 9; j++){
        if (j == 8){
          System.out.print(grille[i][j]+" ");
        }
        else{
          System.out.print(grille[i][j]+", ");
        }
      }
      System.out.println("]");
    }
  }

  public boolean estPossible(int v, int i, int j){
    boolean test;
    // test colonne
    for (int c = 0; c < 9; c++){
      if (grille[i][c] == v){
        return false;
      }
    }
    // test ligne
    for (int l = 0; l < 9; l++){
      if (grille[l][j] == v){
        return false;
      }
    }
    int x1 = 3*(i/3);
    int y1 = 3*(j/3);
    for (int a = x1; a < x1+3; a++){
      for (int b = y1; b < y1+3; b++){
        if (grille[a][b] == v){
          return false;
        }
      }
    }
    // v non present sur toute la ligne et sur toute la colonne et pas dans le carre
    return true;
  }
  /*
  public int[] caseSuivante(int i, int j){
    return int[];
  }
  */

  public static void main(String[] args){
    // i ligne, j colonne
    Sudoku S = new Sudoku();
    Sudoku.affichage();
    System.out.println("i=3, j=2");
    boolean bool = S.estPossible(1, 3, 2);
    System.out.println(bool);
    System.out.println("i=3, j=1");
    bool = S.estPossible(1, 3, 1);
    System.out.println(bool);
    System.out.println("i=3, j=3");
    bool = S.estPossible(1, 3, 3);
    System.out.println(bool);
    System.out.println("i=4, j=2");
    bool = S.estPossible(1, 4, 2);
    System.out.println(bool);
    System.out.println("i=5, j=2");
    bool = S.estPossible(1, 5, 2);
    System.out.println(bool);
    System.out.println("i=5, j=1");
    bool = S.estPossible(1, 5, 1);
    System.out.println(bool);
    System.out.println("i=0, j=0");
    bool = S.estPossible(1, 0, 0);
    System.out.println(bool);
    System.out.println("i=8, j=8");
    bool = S.estPossible(1, 8, 8);
    System.out.println(bool);
  }
}

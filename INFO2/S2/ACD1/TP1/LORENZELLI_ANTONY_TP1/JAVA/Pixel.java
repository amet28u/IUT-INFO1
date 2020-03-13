// Fichier Pixel.java
// Auteur LORENZELLI ANTONY
// Contexte EX 3 PIXEL TP1_ACD1 DUT INFO ST DIE
// Date 07/02/2019

/** Définiton d'un Pixel par ses côtés. */
public class Pixel{
  /** Longueur du premier côté. */
  int x;
  /** Longueur du deuxieme côté. */
  int y;

  /** Constructeur de Pixel via des paramètres donnés. */
  Pixel(int x, int y){
    this.x=x;
    this.y=y;
  }

  /** Constructeur avec des coordonnees par défaut (0,0). */
  Pixel(){
    this(0,0);
  }

  /** Constructeur avec un Pixel en paramètre (Copie d'un Pixel). */
  Pixel(Pixel p){
    this(p.x,p.y);
  }

  /** Constructeur avec deux réels en paramètre. */
  Pixel(double x, double y){
    this((int)(x+0.5),(int)(y+0.5));
  }

  /** Constructeur avec un tableau comprenant les deux longueurs. */
  Pixel(double[] tableau_pixel){
    this(tableau_pixel[0], tableau_pixel[1]);
  }

  /** Methode toString pour Pixel. */
  public String toString(){
    return ("("+x+","+y+")");
  }

  /** Retourne la distance entre le Pixel et l'origine. */
  double distance(){
    return Math.sqrt(Math.pow(x,2.)+Math.pow(y,2.));
  }

  /** Retourne la distance entre deux Pixels. */
  double distance(Pixel p){
    return Math.sqrt((x-p.x)*(x-p.x)+(y-p.y)*(y-p.y));
  }

  /** Retourne la distance de Manhattan entre deux Pixels. */
  int distanceManhattan(Pixel p){
    return (Math.abs(p.x-x)+Math.abs(p.y-y));
  }
}

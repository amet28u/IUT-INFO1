// Fichier Point.java
// Auteur : LORENZELLI ANTONY
// Contexte : TP3 ACD1
// Date : 05/03/2019

package polygones;
import geodessin.dessin.Afficheur;
import geodessin.geo.Visee;

/** Définition d'un point par ses coordonnées. */
public class Point{

  /** Tableau de coordonnée d'un point. */
  private double[] coords = new double[2];
  /** Ecart pour comparer l'égalité de deux réels. */
  final double EPSILON = 0.001;

  /** Constructeur principal construisant un point à l'aide d'un tableau de coordonnées. */
  public Point(double[] coordo){
    for (int i = 0; i < coordo.length; i++){
      coords[i] = coordo[i];
    }
  }

  /** Constructeur construisant un point avec deux coordonnées x et y. */
  public Point(double x, double y){
    this(new double[] {x, y});
  }

  /** Constructeur par recopie d'un point p. */
  public Point(Point p){
    this(p.coords);
  }

  /** Méthode retournant le tableau des coordonnées d'un point. */
  double[] coordonnees(){
    double[] coordo = new double[coords.length];
    for (int i = 0; i < coords.length; i++){
      coordo[i] = coords[i];
    }
    return coordo;
  }

  /** Méthode retournant la première coordonnée du point. */
  public double x(){
    return (this.coords[0]);
  }

  /** Méthode retournant la deuxième coordonnée du point. */
  public double y(){
    return (this.coords[1]);
  }

  /** Méthode déplaçant un point en changeant ses deux premières coordonnées. */
  public void deplacer(double x, double y){
    coords[0] = x;
    coords[1] = y;
  }

  /** Méthode testant si un point est confondu avec un autre point. */
  public boolean confonduAvec(Point p){
    Visee v = new Visee(x(), y(), p.x(), p.y());
    return (v.estNulle());
  }

  /** Méthode retournant la distance entre deux points. */
  public double distance(Point p){
    Visee v = new Visee(x(), y(), p.x(), p.y());
    return (v.distance());
  }

  /** Méthode retournant un point symétrique par un autre point passé en paramètre. */
  public Point symetrique(Point p){
    Visee v = new Visee(x(), y(), p.x(), p.y());
    Point q = new Point(v.but(2));
    return (q);
  }

  /** Méthode permettant de tracer un point. */
  public void tracer(Afficheur aff){
    aff.ajouterPoint(x(), y());
  }

  /** Méthode toString pour un point. */
  public String toString(){
    return ("<"+coords[0]+", "+coords[1]+">");
  }

  /** Méthode main de test de Point. */
  public static void main(String[] args){
    double[] coordo = new double[2];
    coordo[0] = 1.; coordo[1] = 1.;
    Point p = new Point(coordo);
    Point q = new Point(1., 1.);
    Point r = new Point(p);
    System.out.println("p : "+p);
    System.out.println("q : "+q);
    System.out.println("r : "+r);
    coordo = p.coordonnees();
    System.out.println("Coordonnée 1 : "+coordo[0]);
    System.out.println("Coordonnée 2 : "+coordo[1]);
    System.out.println("Coordonnée 1 : "+q.x());
    System.out.println("Coordonnée 2 : "+q.y());
    r.deplacer(1., 1.);
    System.out.println("r : "+r);
    System.out.println("p : "+p);
    System.out.println("q : "+q);
    System.out.println("test q et r confondu ? "+q.confonduAvec(r));
    System.out.println("dist p-q "+p.distance(q));
    System.out.println("sym p par q "+p.symetrique(q));
  }
}

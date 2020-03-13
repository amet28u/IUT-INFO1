// Fichier Triangle.java
// Auteur : LORENZELLI ANTONY
// Contexte : TP3 ACD1
// Date : 05/03/2019

package polygones;
import geodessin.geo.Visee;

/** Définition d'un triangle par un tableau regroupant ses 3 sommets. */
class Triangle{

  /** Tableau regroupant les sommets. */
  Point[] sommets = new Point[3];

  /** Constructeur de base prenant 3 point pour créer un triangle. */
  public Triangle(Point p1, Point p2, Point p3) throws GeometrieException{
    sommets[0] = p1;
    sommets[1] = p2;
    sommets[2] = p3;
    if (sommets[0] == sommets[1]){
      throw new PointsConfondusException();
    }
    if (sommets[0] == sommets[2]){
      throw new PointsConfondusException();
    }
    if (sommets[2] == sommets[1]){
      throw new PointsConfondusException();
    }
  }

  /** Constructeur pour créer un triangle avec le tableau de sommets et les index du tableau. */
  public Triangle(Point[] pts, int i1, int i2, int i3){
    this(pts[i1], pts[i2], pts[i3]);
  }

  /** Méthode retournant un tableau de points représentant les sommets du triangle. */
  public Point[] sommets(){
    Point[] som = new Point[3];
    for (int i = 0; i < 3; i++){
      som[i] = sommets[i];
    }
    return (som);
  }

  /*public Segment[] aretes(){
  }*/

  /** Méthode retournant le périmètre du triangle. */
  public double perimetre(){
    Point a = new Point(sommets[0]);
    Point b = new Point(sommets[1]);
    Point c = new Point(sommets[2]);
    return ((a.distance(b)) + (b.distance(c)) + (c.distance(a)));
  }

  /** Méthode toString pour triangle. */
  public String toString(){
    return ("<"+sommets[0]+", "+sommets[1]+", "+sommets[2]+">");
  }

  /** Méthode main de test de triangle. */
  public static void main(String[] args){
    Point a = new Point(0., 0.);
    Point b = new Point(3., 0.);
    Point c = new Point(0., 4.);
    Triangle t = new Triangle(a, b, c);
    System.out.println(t);
    System.out.println(t.perimetre());
  }







}

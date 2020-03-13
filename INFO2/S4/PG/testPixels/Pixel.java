// Trame des exercices sur les traces de base
// DUT Informatique - 2018/2019
// Prepare par P. Even (Universite de Lorraine / IUT de Saint-Die, Dpt Info)
// Complete par X

import java.util.List;
import java.util.ArrayList;

/** Pixel de la grille.
 */
public class Pixel
{
  /** Coordonnees dans la grille. */
  protected int x, y;


  /** Cree un pixel a partir de ses coordonnees.
   * @param x abscisse du pixel.
   * @param y ordonnee du pixel.
   */
  public Pixel (int x, int y)
  {
    this.x = x;
    this.y = y;
  }

  /** Retourne l'abscisse du pixel.
   */
  public int x ()
  {
    return (x);
  }

  /** Retourne l'ordonnee du pixel.
   */
  public int y ()
  {
    return (y);
  }

  /** Retourne les pixels du segment joignant le pixel a un autre.
   * @param p Le pixel d'arrivee.
   */
   public Pixel[] tracerSegment(Pixel p) {
     Pixel[] pts;
     Pixel origine, arrivee;

     if (p.x() > this.x()) {
       origine = this;
       arrivee = p;
     } else {
       origine = p;
       arrivee = this;
     }

     int octant;
     int dy;
     int dx;
     int coeffdir;

     dy = arrivee.y() - origine.y();
     dx = arrivee.x() - origine.x();

     coeffdir = (dx == 0 ? 0 : dy / dx);

     if (dy < 0) {
       if (-dy > dx)
         octant = 7;
       else
         octant = 8;
     }

     else {
       if (dy > dx)
         octant = 2;
       else
         octant = 1;
     }

     int ci;
     int nb_pts;

     if(dy < 0) dy *= -1;
     int dx2 = 2 * dx;
     int dy2 = 2 * dy;

     switch (octant) {
     case 1:
       System.out.println("Octant 1");
       nb_pts = dx + 1;
       pts = new Pixel[nb_pts];
       pts[0] = origine;
       pts[nb_pts - 1] = arrivee;
       ci = dy2 - dx;
       int y_1 = origine.y();
       Pixel pix1;
       for (int i = 1; i < nb_pts - 1; i++) {
         if (ci < 0) {
           pix1 = new Pixel(origine.x() + i, y_1);
           ci = ci + dy2;
         }

         else {
           pix1 = new Pixel(origine.x() + i, ++y_1);
           ci = ci + dy2 - dx2;
         }

         pts[i] = pix1;
       }
       break;

     case 2:
       System.out.println("Octant 2");
       nb_pts = dy + 1;
       pts = new Pixel[nb_pts];
       pts[0] = origine;
       pts[nb_pts - 1] = arrivee;
       ci = dx2 - dy;
       int x_2 = origine.x();
       Pixel pix2;
       for (int i = 1; i < nb_pts - 1; i++) {
         if (ci < 0) {
           pix2 = new Pixel(x_2, origine.y() + i);
           ci = ci + dx2;
         }

         else {
           pix2 = new Pixel(++x_2, origine.y() + i);
           ci = ci + dx2 - dy2;
         }
         pts[i] = pix2;
       }

       break;

     case 7:
       System.out.println("Octant 7");
       nb_pts = dy + 1;
       pts = new Pixel[nb_pts];
       pts[0] = origine;
       pts[nb_pts - 1] = arrivee;
       ci = dx2 - dy;
       int x_7 = origine.x();
       Pixel pix7;
       for (int i = 1; i < nb_pts - 1; i++) {
         if (ci < 0) {
           pix7 = new Pixel(x_7, origine.y() - i);
           ci = ci + dx2;
         }

         else {
           pix7 = new Pixel(++x_7, origine.y() - i);
           ci = ci + dx2 - dy2;
         }
         pts[i] = pix7;
       }
       break;

     case 8:
       System.out.println("Octant 8");
       nb_pts = dx + 1;
       pts = new Pixel[nb_pts];
       pts[0] = origine;
       pts[nb_pts - 1] = arrivee;
       ci = dy2 - dx;
       int y_8 = origine.y();
       Pixel pix8;
       for (int i = 1; i < nb_pts - 1; i++) {
         if (ci <= 0) {
           pix8 = new Pixel(origine.x() + i, y_8);
           ci = ci + dy2;
         }

         else {
           pix8 = new Pixel(origine.x() + i, --y_8);
           ci = ci + dy2 - dx2;
         }

         pts[i] = pix8;
       }
       break;

     default:
       System.out.println("Default");
       pts = new Pixel[2];
       pts[0] = origine;
       pts[1] = arrivee;
       break;
     }

     return (pts);
   }

  /** Retourne les pixels du cercle dont ce pixel est le centre.
   * @param rayon rayon du cercle.
   */
  public Pixel[] tracerCercle (int rayon)
  {
    List<Pixel> pts = new ArrayList<Pixel>();
    pts.add(this); // centre
    pts.add(new Pixel(x + rayon, y)); // point xMax
    pts.add(new Pixel(x, y + rayon)); // point yMax
    pts.add(new Pixel(x - rayon, y)); // point xMin
    pts.add(new Pixel(x, y - rayon)); // point yMin

    int dx = rayon;
    int dy = 0;

    int q0 = 3 - 2 * rayon; // critère initial
    Pixel pix1, pix2, pix3, pix4, pix5, pix6;
    while (dx > dy + 1){
      if (q0 < 0){ // A
        q0 = q0 + 4 * dy + 6; // màj critère
        pix1 = new Pixel(dx + x, dy + 1 + y); // octant 1
        pix2 = new Pixel(-dx + x, -dy - 1 + y);
        pix3 = new Pixel(dy + 1 + x, dx + y);
        pix4 = new Pixel(-dy - 1 + x, -dx + y);
        pix5 = new Pixel(-dx + x, dy + 1 + y);
        pix6 = new Pixel(dx + x, -dy + 1 + y);



        dx = dx; dy = dy + 1; // màj des coo
      }
      else{ // B
        q0 = q0 + 4 * dy - 4 * dx + 10; // màj critère
        pix1 = new Pixel(dx - 1 + x, dy + 1 + y); // octant 1
        pix2 = new Pixel(-dx + 1 + x, -dy - 1 + y);
        pix3 = new Pixel(dy + 1 + x, dx - 1 + y);
        pix4 = new Pixel(-dy - 1 + x, -dx + 1 + y);
        pix5 = new Pixel(-dx + 1 + x, dy + 1 + y);
        pix6 = new Pixel(dx - 1 + x, -dy + 1 + y);

        dx = dx - 1; dy = dy + 1; // màj des coo
      }
      pts.add(pix1);
      pts.add(pix2);
      pts.add(pix3);
      pts.add(pix4);
      pts.add(pix5);
      pts.add(pix6);
    }

    Pixel[] pts1 = new Pixel[pts.size()];
    for (int i = 0; i < pts.size(); i++){
      pts1[i] = pts.get(i);
    }
    return (pts1);
  }
}

// Trame des exercices sur les traces de base
// DUT Informatique - 2018/2019
// Prepare par P. Even (Universite de Lorraine / IUT de Saint-Die, Dpt Info)
// Complete par X


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
  public Pixel[] tracerSegment (Pixel p)
  {
    Pixel[] pts;
    Pixel origine, arrivee;

    if(p.x() > this.x()) {
    	origine = this;
    	arrivee = p;
    }
    else {
    	origine = p;
    	arrivee = this;
    }

    int octant;
    int dy = arrivee.y() - origine.y();
    int dx = arrivee.x() - origine.x();
    int coeffdir = dy / dx; // non utilisé

    if(dy < 0) {
    	if(-dy > dx)
    		octant = 7;
    	else
    		octant = 8;
    }

    else {
    	if(dy > dx)
    		octant = 2;
    	else
    		octant = 1;
    }

    int ci;
    int nb_pts;

    switch(octant) {
    	case 1:
    		System.out.println("Octant 1");
    		nb_pts = dx + 1;
    		pts = new Pixel[nb_pts];
    		pts[0] = origine;
    		pts[nb_pts - 1] = arrivee;
    		ci = 2 * dy - dx;
    		int y_1 = origine.y();
    		Pixel pix1;
    		for(int i = 1; i < nb_pts - 1; i++) {
    			if(ci < 0) {
    				pix1 = new Pixel(origine.x() + i, y_1);
    				ci = ci + 2 * dy;
    			}

    			else {
    				pix1 = new Pixel(origine.x() + i, ++y_1);
    				ci = ci + 2 * dy - 2 * dx;
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
    		ci = 2 * dx - dy;
    		int x_2 = origine.x();
    		Pixel pix2;
    		for(int i = 1; i < nb_pts - 1; i++) {
    			if(ci < 0) {
    				pix2 = new Pixel(x_2, origine.y() + i);
    				ci = ci + 2 * dx;
    			}

    			else {
    				pix2 = new Pixel(++x_2, origine.y() + i);
    				ci = ci + 2 * dx - 2 * dy;
    			}
    			pts[i] = pix2;
    		}

    		break;

    	case 7:
    		System.out.println("Octant 7");
    		dy *= -1;
    		nb_pts = dy + 1;
    		pts = new Pixel[nb_pts];
    		pts[0] = origine;
    		pts[nb_pts - 1] = arrivee;
    		ci = 2 * dx - dy;
    		int x_7 = origine.x();
    		Pixel pix7;
    		for(int i = 1; i < nb_pts - 1; i++) {
    			if(ci < 0) {
    				pix7 = new Pixel(x_7, origine.y() - i);
    				ci = ci + 2 * dx;
    			}
    			else {
    				pix7 = new Pixel(++x_7, origine.y() - i);
    				ci = ci + 2 * dx - 2 * dy;
    			}
    			pts[i] = pix7;
    		}
    		break;

    	case 8:
    		System.out.println("Octant 8");
    		dy *= -1;
    		nb_pts = dx + 1;
    		pts = new Pixel[nb_pts];
    		pts[0] = origine;
    		pts[nb_pts - 1] = arrivee;
    		ci = 2 * dy - dx;
    		int y_8 = origine.y();
    		Pixel pix8;
    		for(int i = 1; i < nb_pts - 1; i++) {
    			if(ci < 0) {
    				pix8 = new Pixel(origine.x() + i, y_8);
    				ci = ci + 2 * dy;
    			}
    			else {
    				pix8 = new Pixel(origine.x() + i, --y_8);
    				ci = ci + 2 * dy - 2 * dx;
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
    Pixel[] pts = new Pixel[10];
    pts[0] = this; // centre
    pts[1] = new Pixel(x + rayon, y); // point R
    int cx = x + rayon; // cooX de R
    int cy = 0; // cooY de R
    int q0 = 3 - 2 * rayon; // critère initial
    int q1;
    int octant = 1;
    switch(octant){
      case 1:
        System.out.println("Octant 1");
        Pixel pix1;
        for (int i = 1; i <= 10; i++){
          if (q0 < 0){ // A
            q1 = q0 + 4 * cy + 6; q0 = q1; // màj critère
            pix1 = new Pixel(cx, cy + 1); // au dessus
            cx = cx; cy = cy + 1; // màj des coo
          }
          else{ // B
            q1 = q0 + 4 * cy - 4 * cx + 10; q0 = q1; // màj critère
            pix1 = new Pixel(cx - 1, cy + 1); // au dessus à gauche
            cx = cx - 1; cy = cy + 1; // màj des coo
          }
          pts[i] = pix1;
        }
    }


    return (pts);
  }
}

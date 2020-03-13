// Trame des exercices sur les traces de base
// DUT Informatique - 2017/2018
// Prepare par P. Even (Universite de Lorraine / IUT de Saint-Die, Dpt Info)


/** Pixel de la grille (solution naive pour les segments).
 */
public class PixelSeg extends Pixel
{
  /** Cree un pixel a partir de ses coordonnees.
   * @param x abscisse du pixel.
   * @param y ordonnee du pixel.
   */
  public PixelSeg (int x, int y)
  {
    super (x, y);
  }

  /** Cree un pixel a partir d'un autre.
   * @param p l'autre.
   */
  public PixelSeg (Pixel p)
  {
    super (p.x, p.y);
  }

  /** Retourne les pixels du segment joignant le pixel a un autre.
   * @param p Le pixel d'arrivee.
   */
  public Pixel[] tracerSegment (Pixel p)
  {
    int dx = p.x () - x ();
    int dy = p.y () - y ();
    int adx = (dx > 0 ? dx : -dx);
    int ady = (dy > 0 ? dy : -dy);
    Pixel[] pts = new Pixel[adx > ady ? adx + 1 : ady + 1];

    if (adx < ady)
    {
      int y1 = (dy < 0 ? p.y () : y ());
      int x1 = (dy < 0 ? p.x () : x ());
      if (dy < 0) dx = -dx;
      dx *= 2;
      dy = ady * 2;
      int c = ady + dy * x1 - dx * y1;
      for (int i = 0; i < ady + 1; i ++)
        pts[i] = new Pixel ((dx * (y1 + i) + c) / dy, y1 + i);
    }
    else
    {
      int x1 = (dx < 0 ? p.x () : x ());
      int y1 = (dx < 0 ? p.y () : y ());
      if (dx < 0) dy = -dy;
      dy *= 2;
      dx = 2 * adx;
      int c = adx + dx * y1 - dy * x1;
      for (int i = 0; i < adx + 1; i ++)
        pts[i] = new Pixel (x1 + i, (dy * (x1 + i) + c) / dx);
    }
    return (pts);
  }
}

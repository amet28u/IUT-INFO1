// Trame du TP de synthese d'images : primitives OpenGL
// DUT Informatique - 2019/2020
// Prepare par P. Even (Universite de Lorraine / IUT de Saint-Die, Dpt Info)
// Complete par : 


import com.jogamp.opengl.GL2;

/** 2D drawings.
  */
public class Drawing
{
  /** Figures position on the panel. */
  public final static int[] CASE = {1, 2, 3, 4, 5, 10, 6, 9, 7, 8};

  /** Displays points. */
  void displayPoints (GL2 gl)
  {
    System.out.println ("Points : to do");
    // At present just displays four black points : TO BE CHANGED
    gl.glColor3f (0.0f, 0.0f, 0.0f);
    gl.glBegin (GL2.GL_POINTS);
      gl.glVertex2i (5, 2);
      gl.glVertex2i (15, 5);
      gl.glVertex2i (30, 38);
      gl.glVertex2i (0, 30);
    gl.glEnd ();
  }

  /** Displays lines. */
  void displayLines (GL2 gl)
  {
    System.out.println ("Lines : to do");
  }

  /** Displays a line strip. */
  void displayLineStrip (GL2 gl)
  {
    System.out.println ("LineStrip : to do");
  }

  /** Displays a line loop. */
  void displayLineLoop (GL2 gl)
  {
    System.out.println ("LineLoop : to do");
    /* Pour l'interpolation de couleur, il suffit de specifier
         une nouvelle couleur avant de dessiner un sommet : exemple
      gl.glColor3f (0.4f, 0.2f, 0.5f);
      gl.glVertex2f (24.f, 4.f);
      gl.glColor3f (0.2f, 0.7f, 0.4f);
      gl.glVertex2f (24.f, 4.f);
    */
  }

  /** Displays triangles. */
  void displayTriangles (GL2 gl)
  {
    System.out.println ("Triangles : to do");
  }

  /** Displays a triangle strip. */
  void displayTriangleStrip (GL2 gl)
  {
    System.out.println ("TriangleStrip : to do");
  }

  /** Displays a triangle fan. */
  void displayTriangleFan (GL2 gl)
  {
    System.out.println ("TriangleFan : to do");
  }

  /** Displays quadrilaters. */
  void displayQuads (GL2 gl)
  {
    System.out.println ("Quads : to do");
  }

  /** Displays a quadrilater strip. */
  void displayQuadStrip (GL2 gl)
  {
    System.out.println ("QuadStrip : to do");
  }

  /** Displays a polygon. */
  void displayPolygon (GL2 gl)
  {
    System.out.println ("Polygon : tout doux");
  }
}

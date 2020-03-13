// Trame des TP d'algebre lineaire : transformations geometriques
// DUT Informatique - 2018/2019
// Prepare par P. Even, Universite de Lorraine / IUT de Saint-Die

import com.jogamp.opengl.GL2;


/** Cable 3D model */
public class Cable
{
  private int type = 0;

  /** Cable materials */
  private float[] black = {0.0f, 0.0f, 0.0f, 1.0f};
  private float[] specularity = {0.3f, 0.5f, 0.8f, 1.0f};
  private float[] shininess = {50.0f};
  private float[] diffusion = {0.3f, 0.5f, 0.8f, 1.0f};
  private float[] ambiance = {0.2f, 0.4f, 0.7f, 1.0f};

  /** Cable shape */
  private SolidPrimitive part = null;
  private float width = 0.05f;
  private float length = 0.4f;
  private float radius = 4.0f;
  private int nbParts = 15;
  private int resolution = 128;

  /** Cable position */
  private float[] pos = {0.0f, 0.0f, 1.0f};
  private float[] inter = {-1.0f, 0.0f, 1.0f};


  /** Constructs a cable, according to the provided type.
    * @param type Cable geometry
    * @param alea Cable parameters variability
    */
  public Cable (int type, boolean alea)
  {
    this.type = type;
    if (alea)
    {
      pos[0] += -0.5F + (float) Math.random () * 1.0f;
      pos[1] += -0.5F + (float) Math.random () * 1.0f;
      pos[2] += (float) Math.random () * 0.2f;
      length  += -0.05F + (float) Math.random () * 0.1;
      radius += -0.5F + (float) Math.random () * 1.0f;
    }
    if (type == 0)
    {
      pos[0] += -1.0f;
      pos[1] += -3.0f;
      inter[0] = pos[0];
      inter[2] = pos[2];
      part = new Box (width, length, width, SolidPrimitive.REF_CENTER);
    }
    else if (type == 1)
    {
      nbParts = 20;
      pos[0] += 1.0f;
      pos[1] += -2.0f;
      inter[2] = pos[2];
      part = new Box (width, length, width, SolidPrimitive.REF_CENTER);
    }
    else if (type == 2)
    {
      nbParts = 20;
      pos[0] += -3.0f;
      pos[1] += -2.0f;
      inter[2] = pos[2];
      part = new Box (width, length, width, SolidPrimitive.REF_CENTER);
    }
    else if (type == 3)
    {
      nbParts = 20;
      pos[0] += -3.0f;
      pos[1] += 2.0f;
      inter[2] = pos[2];
      part = new Box (width, length, width, SolidPrimitive.REF_CENTER);
    }
    else if (type == 4)
    {
      nbParts = 20;
      pos[0] += 1.0f;
      pos[1] += 2.0f;
      inter[2] = pos[2];
      part = new Box (width, length, width, SolidPrimitive.REF_CENTER);
    }
    else if (type == 10)
    {
      inter[0] += -3.0f;
      part = new Ring (radius, width, width, resolution);
    }
  }

  /** Returns the cable length.
    */
  public float length ()
  {
    return (length * nbParts);
  }

  /** Returns the cable start point position.
    * @param val Position coordinate
    */
  public float pos (int val)
  {
    return (pos[val]);
  }

  /** Returns a cable passage point position.
    * @param val Position coordinate
    */
  public float inter (int val)
  {
    return (inter[val]);
  }

  /** Returns the cable radius.
    */
  public float radius ()
  {
    return (radius);
  }

  /** Renders the cable object.
    * @param gl GL2 context. 
    */
  public void draw (GL2 gl)
  {
    double dx = (double) (inter[0] - pos[0]);
    double dy = (double) (inter[1] - pos[1]);
    double dist = Math.sqrt (dx * dx + dy * dy);
    double angle = Math.acos (dy / dist);
    if (dx > 0.0f) angle = - angle;

    gl.glMaterialfv (GL2.GL_FRONT, GL2.GL_SPECULAR, specularity, 0);
    gl.glMaterialfv (GL2.GL_FRONT, GL2.GL_SHININESS, shininess, 0);
    gl.glMaterialfv (GL2.GL_FRONT, GL2.GL_AMBIENT, black, 0);
    gl.glMaterialfv (GL2.GL_FRONT, GL2.GL_DIFFUSE, diffusion, 0);

    gl.glPushMatrix ();
      if (type == 10)
      {
        gl.glTranslatef (inter[0], inter[1], inter[2] + width / 2);
        part.draw (gl);
      }
      else
      {
        gl.glTranslatef (pos[0], pos[1], pos[2] + width / 2);
        gl.glRotated (angle * 180. / Math.PI, 0.0, 0.0, 1.0);
        gl.glTranslatef (0.0f, length / 2, 0.0f);
        part.draw (gl);

        for (int i = 1; i < nbParts; ++i)
        {
          gl.glTranslatef (0.0f, length, 0.0f);
          part.draw (gl);
        }
      }
    gl.glPopMatrix ();
  }
}

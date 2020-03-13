// Trame des TP d'algebre lineaire : transformations geometriques
// DUT Informatique - 2018/2019
// Prepare par P. Even, Universite de Lorraine / IUT de Saint-Die

import com.jogamp.opengl.GL2;


/** Abstract class for solid primitives */
public abstract class SolidPrimitive
{
  /** Reference system positions wrt the solid primitive.
    */
  public final static int REF_CENTER = 0;
  public final static int REF_BASE = 1;
  public final static int REF_TOP = 2;

  protected float[] refMatrix = {1.0f, 0.0f, 0.0f, 0.0f,
                                 0.0f, 1.0f, 0.0f, 0.0f,
                                 0.0f, 0.0f, 1.0f, 0.0f,
                                 0.0f, 0.0f, 0.0f, 1.0f};

  /** Sets the solid primitive reference system.
    * @param ref Reference system position wrt the box.
    */
  protected abstract void setReference (int ref);

  /** Renders the solid primitive.
    * @param gl GL2 context. 
    */ 
  public abstract void draw (GL2 gl);
}

// Trame des TP d'algebre lineaire : transformations geometriques
// DUT Informatique - 2018/2019
// Prepare par P. Even, Universite de Lorraine / IUT de Saint-Die

import com.jogamp.opengl.GL2;


/** 3D scene used in the exercice */
public abstract class ExoScene
{
  /** Animation status */
  protected boolean alive = false;

  protected float speedIni = 0.1f;
  protected float speedMin = 0.001f;
  protected float speedMax = 1.0f;
  protected float speedInc = 1.1f;
  protected float speed = speedIni;

  protected Observer obs = null;


  /** Returns the next animation step pose.
    * @param pose Already allocated vector to fill in with new pose values.
    */
  protected abstract boolean nextStep (float[] pose);

  /** Initializes the scene.
    * @param gl GL2 context.
    */
  abstract public void init (GL2 gl);

  /** Updates the scene.
    */ 
  abstract public void update ();

  /** Renders the scene.
    * @param gl GL2 context.
    */
  abstract public void draw (GL2 gl);

  /** Sets the scene observer handler.
    * @param obs Observer handler to be associated to the 3D scene.
    */
  public void setObserver (Observer obs)
  {
    this.obs = obs;
  }

  /** Returns the visible volume.
    */
  public float[] defaultVisibleVolume ()
  {
    return (new float[] {- 8.1f, 8.1f, -6.1f, 6.1f, -0.1f, 4.1f});
  }

  /** Returns the volume the observer is allowed to walk through.
    */
  public float[] accessibleVolume ()
  {
    return (new float[] {- 8.0f, 8.0f, -6.0f, 6.0f, 0.1f, 4.0f});
  }

  /** Provides a relevant position of the observer in the hall.
    */
  public float[] viewPoint ()
  {
    return (new float[] {6.0f, 0.0f, 4.0f});
  }

  /** Sets the current projection center.
    * @param vp The new projection center.
    */
  public void setCurrentViewPoint (float[] vp)
  {
    obs.setCenter (vp);
  }

  /** Switch on or off the shuttle motion.
    */
  public void toggleAnimation ()
  {
    alive = ! alive;
  }

  /** Speeds up or down the animation.
    * @param val Acceleration value to be applied.
    */
  public void gear (int val)
  {
    if (val == 0) speed = speedIni;
    else if (val > 0)
    {
      if (speed < speedMax) speed *= speedInc;
    }
    else if (speed > speedMin) speed /= speedInc;
  }

  /** Returns the viewing matrix to be immediately applied.
    */
  public float[] setView ()
  {
    return (null);
  }
}

// Trame des TP d'algebre lineaire : transformations geometriques
// DUT Informatique - 2018/2019
// Prepare par P. Even, Universite de Lorraine / IUT de Saint-Die

import com.jogamp.opengl.GL2;


/** Ropeway scene */
public abstract class RopewayScene extends ExoScene
{
  /** Cable object */
  private Cable cable = null;

  /** Soil features */
  private final float depth_2 = 4.0f;
  private final float width_2 = 3.0f;
  private final float thickness_2 = 0.1f;
  private float[] soilSpecularity = {0.1f, 0.4f, 0.1f, 1.0f};
  private float[] soilShininess = {1.0f};
  private float[] soilDiffusion = {0.4f, 0.9f, 0.2f, 1.0f};
  private float[] soilAmbiance = {0.4f, 0.7f, 0.2f, 1.0f};

  /** Ropeway geometry */
  private float mastHeight = 0.2f;
  private float mastWidth = 0.05f;
  private float carLength = 0.8f;
  private float carWidth = 0.4f;
  private float carHeight = 0.5f;
  private float glassCenterHeight = 0.1f;
  private float glassHeight = 0.2f;
  private float glassOutOffset = 0.005f;
  private float frontGlassWidth = 0.36f;
  private float sideGlassWidth = 0.5f;
  private float sideGlassCenter = 0.1f;

  /** Ropeway materials */
  private float[] mastSpecularity = {0.8f, 0.8f, 0.4f, 1.0f};
  private float[] mastShininess = {50.0f};
  private float[] mastAmbiance = {0.6f, 0.6f, 0.2f, 1.0f};
  private float[] mastDiffusion = {0.8f, 0.8f, 0.4f, 1.0f};
  private float[] carSpecularity = {0.8f, 0.5f, 0.2f};
  private float[] carShininess = {2.0f};
  private float[] carAmbiance = {0.7f, 0.4f, 0.1f};
  private float[] carDiffusion = {0.8f, 0.5f, 0.2f};
  private float[] glassSpecularity = {0.8f, 0.8f, 0.8f};
  private float[] glassShininess = {100.0f};
  private float[] glassAmbiance = {0.1f, 0.1f, 0.1f};
  private float[] glassDiffusion = {0.2f, 0.2f, 0.2f};

  /** Ropeway dynamics */
  private final static float POSE_INIX = 0.0f;
  private final static float POSE_INIY = 0.0f;
  private final static float POSE_INIZ = 1.0f;
  private float[] pose = {1.0f, 0.0f, 0.0f, POSE_INIX,
                          0.0f, 1.0f, 0.0f, POSE_INIY,
                          0.0f, 0.0f, 1.0f, POSE_INIZ,
                          0.0f, 0.0f, 0.0f, 1.0f};
  private float[] transPose = {1.0f, 0.0f, 0.0f, 0.0f,
                               0.0f, 1.0f, 0.0f, 0.0f,
                               0.0f, 0.0f, 1.0f, 0.0f,
                               POSE_INIX, POSE_INIY, POSE_INIZ, 1.0f};

  /** Display lists */
  private int CABLE_DRAWING = 1;
  private int SOIL_DRAWING = 2;
  private int ROPEWAY_DRAWING = 3;


  /** Constructs a specific ropeway scene.
    * @param type Scene geometry
    * @param params Geometry variability option
    */
  protected RopewayScene (int type, String[] params)
  {
    if (type == 0)
      cable = new Cable (0, params.length != 0); 

    else if (type == 1)
    {
      if (params.length != 0)
      {
        if (params[0].equals ("2")) type = 2;
        else if (params[0].equals ("3")) type = 3;
        else if (params[0].equals ("4")) type = 4;
      }
      cable = new Cable (type, params.length != 0);
    }

    else cable = new Cable (10, params.length != 0);
  }

  /** Initializes the ropewayscene.
    * @param gl GL2 context.
    */
  public void init (GL2 gl)
  {
    gl.glNewList (CABLE_DRAWING, GL2.GL_COMPILE);
      cable.draw (gl);
    gl.glEndList ();

    Box soil = new Box (depth_2 * 2 + thickness_2 * 2,
                        width_2 * 2 + thickness_2 * 2, thickness_2);
    gl.glNewList (SOIL_DRAWING, GL2.GL_COMPILE);
      gl.glPushMatrix ();
        gl.glTranslatef (0.0f, 0.0f, - thickness_2);
        gl.glMaterialfv (GL2.GL_FRONT, GL2.GL_SPECULAR, soilSpecularity, 0);
        gl.glMaterialfv (GL2.GL_FRONT, GL2.GL_SHININESS, soilShininess, 0);
        gl.glMaterialfv (GL2.GL_FRONT, GL2.GL_AMBIENT, soilAmbiance, 0);
        gl.glMaterialfv (GL2.GL_FRONT, GL2.GL_DIFFUSE, soilDiffusion, 0);
        soil.draw (gl);
      gl.glPopMatrix ();
    gl.glEndList ();

    Box mastGeo = new Box (mastWidth, mastWidth, mastHeight,
                           SolidPrimitive.REF_CENTER);
    Box carGeo = new Box (carWidth, carLength, carHeight,
                          SolidPrimitive.REF_CENTER);
    Box frontGlassGeo = new Box (frontGlassWidth, glassOutOffset * 2,
                                 glassHeight, SolidPrimitive.REF_CENTER);
    Box sideGlassGeo = new Box (carWidth + glassOutOffset * 2,
                                sideGlassWidth, glassHeight,
                                SolidPrimitive.REF_CENTER);

    gl.glNewList (ROPEWAY_DRAWING, GL2.GL_COMPILE);
      // Mast
      gl.glMaterialfv (GL2.GL_FRONT, GL2.GL_SPECULAR, mastSpecularity, 0);
      gl.glMaterialfv (GL2.GL_FRONT, GL2.GL_SHININESS, mastShininess, 0);
      gl.glMaterialfv (GL2.GL_FRONT, GL2.GL_AMBIENT, mastAmbiance, 0);
      gl.glMaterialfv (GL2.GL_FRONT, GL2.GL_DIFFUSE, mastDiffusion, 0);
      gl.glTranslatef (0.0f, 0.0f, - mastHeight / 2);
      mastGeo.draw (gl);

      // Car
      gl.glMaterialfv (GL2.GL_FRONT, GL2.GL_SPECULAR, carSpecularity, 0);
      gl.glMaterialfv (GL2.GL_FRONT, GL2.GL_SHININESS, carShininess, 0);
      gl.glMaterialfv (GL2.GL_FRONT, GL2.GL_AMBIENT, carAmbiance, 0);
      gl.glMaterialfv (GL2.GL_FRONT, GL2.GL_DIFFUSE, carDiffusion, 0);
      gl.glTranslatef (0.0f, 0.0f, - (mastHeight + carHeight) / 2);
      carGeo.draw (gl);

      // Glasses
      gl.glMaterialfv (GL2.GL_FRONT, GL2.GL_SPECULAR, glassSpecularity, 0);
      gl.glMaterialfv (GL2.GL_FRONT, GL2.GL_SHININESS, glassShininess, 0);
      gl.glMaterialfv (GL2.GL_FRONT, GL2.GL_AMBIENT, glassAmbiance, 0);
      gl.glMaterialfv (GL2.GL_FRONT, GL2.GL_DIFFUSE, glassDiffusion, 0);
      gl.glPushMatrix ();
        gl.glTranslatef (0.0f, carLength / 2, glassCenterHeight);
        frontGlassGeo.draw (gl);
      gl.glPopMatrix ();
      gl.glTranslatef (0.0f, sideGlassCenter, glassCenterHeight);
      sideGlassGeo.draw (gl);
    gl.glEndList ();
  }

  /** Updates the ropeway scene.
    */
  public void update ()
  {
    // Updates the shuttle pose
    if (alive)
    {
      alive = nextStep (pose);
      for (int i = 0; i < 4; i++)
        for (int j = 0; j < 4; j++)
          transPose[i*4+j] = pose[j*4+i];
    }
  }


  /** Renders the ropewayscene.
    * @param gl GL2 context. 
    */ 
  public void draw (GL2 gl)
  {
    // Floor
    gl.glCallList (SOIL_DRAWING);

    // Cable
    gl.glCallList (CABLE_DRAWING);

    // Ropeway
    gl.glMultMatrixf (transPose, 0);
    gl.glCallList (ROPEWAY_DRAWING);
  }

  /** Returns the cable length.
    */
  protected float cableLength ()
  {
    return (cable.length ());
  }

  /** Returns the cable height to the ground.
    */
  protected float cableHeight ()
  {
    return (cable.pos(2));
  }

  /** Returns the cable start point coordinates.
    */
  protected float[] cableStart ()
  {
    return (new float[] {cable.pos (0), cable.pos (1)});
  }

  /** Returns a cable passage point coordinates.
    */
  protected float[] cableInter ()
  {
    return (new float[] {cable.inter (0), cable.inter (1)});
  }

  /** Returns a circular cable center coordinates.
    */
  protected float[] cableCenter ()
  {
    return (new float[] {cable.inter (0), cable.inter (1)});
  }

  /** Returns a circular cable radius value.
    */
  protected float cableRadius ()
  {
    return (cable.radius ());
  }

  /** Returns the ropeway speed value.
    */
  protected float ropewaySpeed ()
  {
    return (speed);
  }
}

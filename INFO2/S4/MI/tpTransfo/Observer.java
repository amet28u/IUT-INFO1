// Trame des TP d'algebre lineaire : transformations geometriques
// DUT Informatique - 2018/2019
// Prepare par P. Even, Universite de Lorraine / IUT de Saint-Die

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;


/** Scene viewing control */
public class Observer
{
  /** Observer motion control */
  public final static int TURN_RIGHT = 1;
  public final static int TURN_LEFT = 2;
  public final static int NO_RIGHT_LEFT_TURN = 3;
  public final static int TURN_UP = 4;
  public final static int TURN_DOWN = 5;
  public final static int NO_UP_DOWN_TURN = 6;
  public final static int GO_FRONTWARDS = 7;
  public final static int GO_BACKWARDS = 8;
  public final static int NO_FRONT_BACK_GO = 9;
  public final static int GO_RIGHTWARDS = 10;
  public final static int GO_LEFTWARDS = 11;
  public final static int NO_RIGHT_LEFT_GO = 12;
  public final static int GO_UPWARDS = 13;
  public final static int GO_DOWNWARDS = 14;
  public final static int NO_UP_DOWN_GO = 15;

  /** Perspective projection on (orthographic when set to off) */
  private boolean perspectiveOn = true;

  /** Projection modification to be processed */
  private boolean projectionChanged = false;
  private int azimuthMobility = 0;
  private int heightMobility = 0;
  private int frontMobility = 0;
  private int rightMobility = 0;
  private int upMobility = 0;

  /** Perspective angle (used in perspective mode) */
  private static final double DEFAULT_PERSPECTIVE_ANGLE = 60.0;
  private static final double PERSPECTIVE_INC = 1.0;
  private static final double PERSPECTIVE_MIN = 1.0;
  private static final double PERSPECTIVE_MAX = 150.0;
  private double perspectiveAngle = DEFAULT_PERSPECTIVE_ANGLE;

  /** Azimuth angle (site) (used in perspective mode) */
  private static final float DEFAULT_AZIMUTH_ANGLE = 0.0f;
  private static final float AZIMUTH_ANGLE_INC = 1.0f;
  private float azimuthAngle = DEFAULT_AZIMUTH_ANGLE;

  /** Height angle (elevation) (used in perspective mode) */
  private static final float DEFAULT_HEIGHT_ANGLE = -20.0f;
  private static final float HEIGHT_ANGLE_INC = 1.0f;
  private float heightAngle = DEFAULT_HEIGHT_ANGLE;

  /** Projection center (used in perspective mode) */
  private float[] defaultCenter = {0.0f, 0.0f, 0.0f};
  private float[] center = {0.0f, 0.0f, 0.0f};
  private static final float TRANSLATION_INC = 0.1f;
  private float centerXmin = -1.0f;
  private float centerXmax = 1.0f;
  private float centerYmin = -1.0f;
  private float centerYmax = 1.0f;
  private float centerZmin = -1.0f;
  private float centerZmax = 1.0f;

  /** Viewport size */
  private int vpWidth = 100, vpHeight = 100;

  /** Size of the displayed scene (used in orthographic mode) */
  private double defaultSceneSize = 2.0f;
  private double sceneSize = defaultSceneSize;
  private static final double SCENE_SIZE_INC = 1.01;
  private static final double SCENE_SIZE_MIN_RATIO = 0.1;
  private static final double SCENE_SIZE_MAX_RATIO = 1.5;
  private double zMin = 0.0f;
  private double zMax = 1.0f;

  /** Focus point (used in orthographic mode) */
  private float[] defaultFocus = {0.0f, 0.0f};
  private float[] focus = {0.0f, 0.0f};
  private static final float FOCUS_INC = 0.05f;

  /** Access to GLU functions */
  private GLU glu = new GLU ();





  /** Resets the projection matrix according to new viewport and
    * projection angle values.
    * @param gl OpenGL2 context.
    * @param width viewport width.
    * @param height viewport height.
    */ 
  public void setProjection (GL2 gl, int width, int height)
  {
    vpWidth = width;
    vpHeight = height;
    setProjection (gl);
  }

  /** Resets the projection matrix according to new projection angle.
    * @param gl OpenGL2 context.
    */ 
  public void setProjection (GL2 gl)
  {
    gl.glMatrixMode (GL2.GL_PROJECTION);
    gl.glLoadIdentity ();
    if (perspectiveOn)
      glu.gluPerspective (perspectiveAngle, (double) vpWidth / vpHeight,
                          0.1, 20.0);
    else
      gl.glOrtho (- sceneSize * vpWidth / (2 * vpHeight),
                  sceneSize * vpWidth / (2 * vpHeight),
                  - sceneSize / 2, sceneSize / 2, zMin, zMax);
    gl.glMatrixMode (GL2.GL_MODELVIEW);
  }

  /** Sets the projection and viewing matrices before diplaying a scene.
    * @param gl GL2 context
    * @param view Viewing matrix to be immediately applied (when provided)
    */
  public void apply (GL2 gl, float[] view)
  {
    // Restore current projection
    if (projectionChanged)
    {
      setProjection (gl);
      projectionChanged = false;
    }

    // Reset The View
    gl.glLoadIdentity ();

    if (view != null)
      gl.glMultMatrixf (view, 0);

    else if (perspectiveOn)
    {
      boolean moved = false;

      // Update azimuth and height angles
      if (azimuthMobility != 0)
      {
        azimuthAngle += AZIMUTH_ANGLE_INC * azimuthMobility;
        if (azimuthAngle > 180.0f) azimuthAngle -= 360.0f;
        else if (azimuthAngle < -180.0f) azimuthAngle += 360.0f;
      }
      if (heightMobility != 0)
      {
        heightAngle += HEIGHT_ANGLE_INC * heightMobility;
        if (heightAngle > 90.0f) heightAngle = 90.0f;
        else if (heightAngle < -90.0f) heightAngle = -90.0f;
      }

      // Process front/back motions
      if (frontMobility != 0)
      {
        float cb = TRANSLATION_INC * frontMobility
                     * (float) Math.cos (heightAngle * Math.PI / 180);
        center[0] -= cb * (float) Math.cos (azimuthAngle * Math.PI / 180);
        center[1] -= cb * (float) Math.sin (azimuthAngle * Math.PI / 180); 
        center[2] += TRANSLATION_INC * frontMobility
                     * (float) Math.sin (heightAngle * Math.PI / 180);
        moved = true;
      }

      // Process right/left motions
      if (rightMobility != 0)
      {
        center[0] -= TRANSLATION_INC * rightMobility
                     * (float) Math.sin (azimuthAngle * Math.PI / 180);
        center[1] += TRANSLATION_INC * rightMobility
                     * (float) Math.cos (azimuthAngle * Math.PI / 180);
        moved = true;
      }
 
      // Process up/down motions
      if (upMobility != 0)
      {
        float sa = TRANSLATION_INC * upMobility
                   * (float) Math.sin (heightAngle * Math.PI / 180);
        center[0] += sa * (float) Math.cos (azimuthAngle * Math.PI / 180);
        center[1] += sa * (float) Math.sin (azimuthAngle * Math.PI / 180); 
        center[2] += TRANSLATION_INC * upMobility
                     * (float) Math.cos (heightAngle * Math.PI / 180);
        moved = true;
      }

      // Threshold the projection center within the accessibility volume
      if (moved)
      {
        if (center[0] > centerXmax) center[0] = centerXmax;
        else if (center[0] < centerXmin) center[0] = centerXmin;
        if (center[1] > centerYmax) center[1] = centerYmax;
        else if (center[1] < centerYmin) center[1] = centerYmin;
        if (center[2] > centerZmax) center[2] = centerZmax;
        else if (center[2] < centerZmin) center[2] = centerZmin;
      }
    
      // Comes to the observation point,
      // then looks at -X direction, with Y direction on the right,
      // finally apply azimuth and elevation angles.
      gl.glRotatef (- heightAngle, 1.0f, 0.0f, 0.0f);
      gl.glRotatef (- azimuthAngle, 0.0f, 1.0f, 0.0f);
      gl.glRotatef (- 90.0f, 1.0f, 0.0f, 0.0f);
      gl.glRotatef (- 90.0f, 0.0f, 0.0f, 1.0f);
      gl.glTranslatef (- center[0], - center[1], - center[2]);
    }

    else // orthographic projection
    {
      // Update and threshold focus point
      if (azimuthMobility != 0)
      {
        focus[1] += FOCUS_INC * azimuthMobility;
	if (focus[1] > centerYmax) focus[1] = centerYmax;
	else if (focus[1] < centerYmin) focus[1] = centerYmin;
      }
      if (heightMobility != 0)
      {
        focus[0] += FOCUS_INC * heightMobility;
	if (focus[0] > centerXmax) focus[0] = centerXmax;
	else if (focus[0] < centerXmin) focus[0] = centerXmin;
      }

      // Comes to the focus and looks downwards
      gl.glTranslatef (focus[1], focus[0], 0.0f);
      gl.glRotatef (-90.0f, 0.0f, 0.0f, 1.0f);
    }
  }

  /** Toggles the projection type (perspective / orthographic).
    */ 
  public void toggleProjection ()
  {
    perspectiveOn = ! perspectiveOn;
    projectionChanged = true; // to be processed at nex display
  }

  /** Narrows the projection field of view.
    */
  public void zoomIn ()
  {
    if (perspectiveOn)
    {
      perspectiveAngle -= PERSPECTIVE_INC;
      if (perspectiveAngle < PERSPECTIVE_MIN)
        perspectiveAngle = PERSPECTIVE_MIN;
    }
    else // orthographic
    {
      sceneSize *= SCENE_SIZE_INC;
      if (sceneSize > defaultSceneSize * SCENE_SIZE_MAX_RATIO)
        sceneSize = defaultSceneSize * SCENE_SIZE_MAX_RATIO;
    }
    projectionChanged = true; // to be processed at nex display
  }

  /** Enlarges the projection field of view.
    */
  public void zoomOut ()
  {
    if (perspectiveOn)
    {
      perspectiveAngle += PERSPECTIVE_INC;
      if (perspectiveAngle > PERSPECTIVE_MAX)
        perspectiveAngle = PERSPECTIVE_MAX;
    }
    else
    {
      sceneSize /= SCENE_SIZE_INC;
      if (sceneSize < defaultSceneSize * SCENE_SIZE_MIN_RATIO)
        sceneSize = defaultSceneSize * SCENE_SIZE_MIN_RATIO;
    }
    projectionChanged = true; // to be processed at nex display
  }

  /** Controls the observer position and orientation.
    * @param control motion type :
    *	- TURN_RIGHT, TURN_LEFT, NO_RIGHT_LEFT_TURN,
    *	- TURN_UP, TURN_DOWN, NO_UP_DOWN_TURN,
    *	- GO_FRONTWARDS, GO_BACKWARDS, NO_FRONT_BACK_GO,
    *	- GO_RIGHTWARDS, GO_LEFTWARDS, NO_RIGHT_LEFT_GO,
    *	- GO_UPWARDS, GO_DOWNWARDS, NO_UP_DOWN_GO
    */	
  public void move (int control)
  {
    switch (control)
    {
      case TURN_RIGHT : azimuthMobility = -1; break;
      case TURN_LEFT : azimuthMobility = 1; break;
      case NO_RIGHT_LEFT_TURN : azimuthMobility = 0; break;
      case TURN_UP : heightMobility = 1; break;
      case TURN_DOWN : heightMobility = -1; break;
      case NO_UP_DOWN_TURN : heightMobility = 0; break;
      case GO_FRONTWARDS : frontMobility = 1; break;
      case GO_BACKWARDS : frontMobility = -1; break;
      case NO_FRONT_BACK_GO : frontMobility = 0; break;
      case GO_RIGHTWARDS : rightMobility = 1; break;
      case GO_LEFTWARDS : rightMobility = -1; break;
      case NO_RIGHT_LEFT_GO : rightMobility = 0; break;
      case GO_UPWARDS : upMobility = 1; break;
      case GO_DOWNWARDS : upMobility = -1; break;
      case NO_UP_DOWN_GO : upMobility = 0; break;
    }
  }

  /** Resets the current projection parameters to default.
    */
  public void resetProjection ()
  {
    if (perspectiveOn)
    {
      perspectiveAngle = DEFAULT_PERSPECTIVE_ANGLE;
      azimuthAngle = DEFAULT_AZIMUTH_ANGLE;
      heightAngle = DEFAULT_HEIGHT_ANGLE;
      center[0] = defaultCenter[0];
      center[1] = defaultCenter[1];
      center[2] = defaultCenter[2];
    }
    else // orthographic
    {
      sceneSize = defaultSceneSize;
      focus[0] = defaultFocus[0];
      focus[1] = defaultFocus[1];
    }
    projectionChanged = true; // to be processed at nex display
  }

  /** Sets the current projection center in the scene.
    * @param vp Projection center coordinates
   */
  public void setCenter (float[] vp)
  {
    vp[0] = center[0];
    vp[1] = center[1];
    vp[2] = center[2];
  }

  /** Sets the orthographic projection parameters (default and bounds).
    * @param volume Default volume to display
    *   (float array containing xmin, xmax, ymin, ymax, zmin the zmax)
    */ 
  public void setViewingVolume (float[] volume)
  {
    // The default size displays the given volume
    float xSize = volume[1] - volume[0];
    float ySize = volume[3] - volume[2];
    if (xSize < 0.0f) xSize = - xSize;
    if (ySize < 0.0f) ySize = - ySize;
    defaultSceneSize = (xSize > ySize ? (double) xSize : (double) ySize);
    sceneSize = defaultSceneSize;
    zMin = - (double) volume[5];
    zMax = - (double) volume[4];

    // The default focus point is the center of the volume.
    defaultFocus[0] = (volume[1] + volume[0]) / 2;
    defaultFocus[1] = (volume[3] + volume[2]) / 2;
    focus[0] = defaultFocus[0];
    focus[1] = defaultFocus[1];
  }

  /** Sets the observer limits.
    * @param volume Accessible volume limits
    *   (float array containing xmin, xmax, ymin, ymax, zmin the zmax)
    */ 
  public void setAccessibleVolume (float[] volume)
  {
    centerXmin = volume[0];
    centerXmax = volume[1];
    centerYmin = volume[2];
    centerYmax = volume[3];
    centerZmin = volume[4];
    centerZmax = volume[5]; }

  /** Sets the default projection center.
    * @param pos Default projection center coordinates.
    */ 
  public void setDefaultPosition (float[] pos)
  {
    defaultCenter[0] = pos[0];
    defaultCenter[1] = pos[1];
    defaultCenter[2] = pos[2];
    center[0] = pos[0];
    center[1] = pos[1];
    center[2] = pos[2];
  }
}

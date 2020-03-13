// Trame du TP de synthese d'images : primitives OpenGL
// DUT Informatique - 2019/2020
// Prepare par P. Even (Universite de Lorraine / IUT de Saint-Die, Dpt Info)


import java.awt.Frame;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.jogamp.opengl.awt.GLCanvas;
//Deprec import javax.media.opengl.GLCapabilities;
import com.jogamp.opengl.GLDrawableFactory;


/** Exercice main window */
public class Exo
{
  /** Creates AWT Frame with OpenGL context */
  public static void main (String[] args)
  {
    // Creation d'une fenetre AWT
    Frame frame = new Frame ("Primitives 2D");
    // Creation de la surface d'affichage GL
    GLCanvas canvas = new GLCanvas ();

    // Ajout d'un contexte OpenGL (vue)
    int num = 0;
    if (args.length > 0) num = (new Integer (args[0])).intValue ();
    ExoView myView = new ExoView (num);
    canvas.addGLEventListener (myView);

    // Adding a user event handler (controller)
    ExoController myController = new ExoController (canvas, myView);
    canvas.addKeyListener (myController);

    // Window closing behavior
    frame.addWindowListener (
      new WindowAdapter ()
      {
        public void windowClosing (WindowEvent e)
        {
          System.exit (0);
        }
      });

    // Fin de la specification de la fenetre
    frame.add (canvas);
    frame.setSize (600, 600);
    frame.setLocation (0, 0);
    frame.setBackground (Color.white);
    frame.setVisible (true);
    canvas.requestFocus ();
  }
}

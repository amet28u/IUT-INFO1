import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GraphicsConfiguration;


/**
 * Jikan planner application.
 * @author Philippe Even
 */
public class Jikan
{
  /** Jikan archiving handler */
  private static JikanIO io = null;
  private final static int TOP_MARGIN = 30;
  private final static int LEFT_MARGIN = 50;


  /**
    * Creates AWT Frame with Jikan tools
    */
  public static void main (String[] args)
  {
    // Sets the access mode according to the application arguments
    boolean isManaging = true;
    boolean duties = false;
    String schedName = null;
    String startWeek = null, endWeek = null;
    if (args.length > 0)
    {
      if (args[0].equals ("manage")) isManaging = true;
      else if (args[0].equals ("duties")) duties = true;
      else
      {
        schedName = args[0];
        if (args.length > 1) startWeek = args[1];
        if (args.length > 2) endWeek = args[2];
      }
    }

    // Jikan data base creation
    JikanData data = new JikanData ();

    // Jikan archiving creation
    io = new JikanIO (data, isManaging);
    io.loadData ();

    // Edition of teachers services
    if (duties)
    {
      data.editDuties ();
      System.exit (0);
    }
 
    // Edition of calendars
    if (schedName != null)
    {
      data.print (schedName, startWeek, endWeek);
      System.exit (0);
    }
      
    // Creation of a AWT window
    JFrame frame = new JFrame ("JIKAN");

    // Creation of the Jikan display surface
    JikanView canvas = new JikanView (io);
    canvas.setManagingMode (isManaging);
    canvas.setData (data);
    // Getting the display size
    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment ();
    GraphicsDevice gd = ge.getScreenDevices()[0];
    GraphicsConfiguration[] gc = gd.getConfigurations ();
    Rectangle gcBounds = gc[0].getBounds ();
    canvas.restrictSize ((int) (gcBounds.getWidth ()) - LEFT_MARGIN,
                         (int) (gcBounds.getHeight ()) - TOP_MARGIN);

    // Adding a input handler (controller)
    JikanController myController
      = new JikanController (canvas, io, data);
    canvas.addKeyListener (myController);
    canvas.addMouseListener (myController);
    // canvas.addMouseMotionListener (myController);

    // Arming the window closing button
    frame.addWindowListener (
      new WindowAdapter ()
      {
        public void windowClosing (WindowEvent e)
        {
          Jikan.io.save ();
          System.exit (0);
        }
      });

    // Setting the window geometry
    frame.add (canvas);
    Insets insets = frame.getInsets ();
    int w = canvas.getWidth () - insets.left - insets.right;
    int h = canvas.getHeight () - insets.top - insets.bottom;
    frame.setSize (canvas.displayWidth () + insets.left + insets.right,
                   canvas.displayHeight () + insets.top + insets.bottom);
    frame.setLocation (LEFT_MARGIN, 0);
    frame.setBackground (Color.white);
    frame.setVisible (true);

    // Input handler loop activation
    canvas.requestFocus ();
  }
}

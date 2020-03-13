import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


/** Controller for the Jikan planner.
  * @author P. Even
  */
public class JikanController
           implements KeyListener, MouseListener
{
  /** Controlled display area. */
  private JikanView canvas;
  /** Input/output interface. */
  private JikanIO myIO;
  /** Controlled application data. */
  private JikanData data;


  /** Constructs a event handler.
    * @param canvas display area.
    */
  public JikanController (JikanView canvas, JikanIO myIO, JikanData data)
  {
    this.canvas = canvas;
    this.myIO = myIO;
    this.data = data;
  }

  /** Invoked when a key has been pressed.
    * Implementation from KeyListener.
    * @param e detected key event.
    */
  public void keyPressed (KeyEvent e)
  {
    processKeyEvent (e, true);
  }

  /** Invoked when a key has been released.
    * Implementation from KeyListener.
    * @param e detected key event.
    */
  public void keyReleased (KeyEvent e)
  {
    processKeyEvent (e, false);
  }

  /** Invoked when a key has been pressed or released.
    * Local implementation from KeyListener.
    * @param e detected key event.
    * @param pressed pressed or released key status.
    */
  private void processKeyEvent (KeyEvent e, boolean pressed)
  {
    switch (e.getKeyCode ())
    {
      case KeyEvent.VK_Q :
      case KeyEvent.VK_ESCAPE :
        if (! pressed)
        {
          myIO.save ();
          System.exit (0);
        }
        break;
      case KeyEvent.VK_X :
        if (pressed && e.isControlDown ())
          if (data.removeSelectedSlot ()) canvas.repaint ();
        break;
      case KeyEvent.VK_P :
        if (pressed && e.isControlDown ()) data.print ();
        break;
      case KeyEvent.VK_SPACE :
        data.releaseSelection ();
        canvas.repaint ();
        break;
      case KeyEvent.VK_LEFT :
      case KeyEvent.VK_KP_LEFT :
        if (pressed)
        {
          data.incrementWeek (-1);
          canvas.repaint ();
        }
        break;
      case KeyEvent.VK_RIGHT :
      case KeyEvent.VK_KP_RIGHT :
        if (pressed)
        {
          data.incrementWeek (1);
          canvas.repaint ();
        }
        break;
      case KeyEvent.VK_DOWN :
      case KeyEvent.VK_KP_DOWN :
        if (pressed)
        {
          if (data.nextSchedulableItem ()) canvas.repaint ();
        }
        break;
      case KeyEvent.VK_UP :
      case KeyEvent.VK_KP_UP :
        if (pressed)
        {
          if (data.previousSchedulableItem ()) canvas.repaint ();
        }
        break;
    }
  }

  /** Invoked when a key has been typed.
    * Implementation from KeyListener.
    * @param e detected key event.
    */
  public void keyTyped (KeyEvent e)
  {
    switch (e.getKeyChar ())
    {
      case 'a' :
      case 'b' :
      case 'c' :
      case 'd' :
      case 'e' :
      case 'f' :
      case 'g' :
      case 'h' :
      case 'i' :
      case 'j' :
        if (data.changeRequisite (Schedulable.TYPE_ROOM,
                          e.getKeyChar () - 'a')) canvas.repaint ();
        break;
      case 'A' :
      case 'B' :
      case 'C' :
      case 'D' :
      case 'E' :
      case 'F' :
      case 'G' :
      case 'H' :
      case 'I' :
      case 'J' :
        if (data.changeRequisite (Schedulable.TYPE_TEACHER,
                          e.getKeyChar () - 'A')) canvas.repaint ();
        break;
    }
  }

  /** Invoked when the mouse button has been clicked (pressed and released)
    * on a component.
    * Implementation from MouseListener.
    * @param e detected mouse event.
    */
  public void mouseClicked (MouseEvent e)
  {
    if (e.getButton () == MouseEvent.BUTTON1)
    {
      if (canvas.select (e.getX (), e.getY ()))
      canvas.repaint ();
    }
    if (e.getButton () == MouseEvent.BUTTON3)
    {
      if (canvas.askInfo (e.getX (), e.getY ()))
        canvas.repaint ();
    }
  }

  /** Invoked when the mouse enters a component.
    * Implementation from MouseListener.
    * @param e detected mouse event.
    */
  public void mouseEntered (MouseEvent e)
  {
  }

  /** Invoked when the mouse exits a component.
    * Implementation from MouseListener.
    * @param e detected mouse event.
    */
  public void mouseExited (MouseEvent e)
  {
  }

  /** Invoked when a mouse button has been pressed on a component.
    * Implementation from MouseListener.
    * @param e detected mouse event.
    */
  public void mousePressed (MouseEvent e)
  {
  }

  /** Invoked when a mouse button has been released on a component.
    * Implementation from MouseListener.
    * @param e detected mouse event.
    */
  public void mouseReleased (MouseEvent e)
  {
  }
}

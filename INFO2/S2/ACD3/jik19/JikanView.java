import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;


/** Display manager for Jikan planner.
  * @author P. Even 
  */
public class JikanView extends JPanel
{
  /** Font type */
  //private final int FONT = GLUT.BITMAP_HELVETICA_12;

  /** Background color */
  private final Color BACK_COLOR = Color.WHITE;
  /** Frame borders color */
  private final Color FRAME_COLOR = Color.BLACK;
  /** Text color */
  private final Color TEXT_COLOR = Color.BLACK;
  /** Selected text color */
  private final Color SELECTED_TEXT_COLOR = Color.WHITE;
  /** Unselectable text color */
  private final Color UNSEL_TEXT_COLOR = new Color (0.6f, 0.6f, 0.6f);
  /** Legend area color */
  private final Color LEGEND_COLOR = new Color (0.8f, 0.8f, 0.8f);
  /** Non working area color */
  private final Color NON_WORKING_COLOR = new Color (0.6f, 0.6f, 0.6f);
  /** Non working and undesirable area color */
  private final Color NON_WORKING_COLOR12 = new Color (0.6f, 0.6f, 0.0f);
  /** Non working and forbidden area color */
  private final Color NON_WORKING_COLOR1 = new Color (0.6f, 0.0f, 0.0f);
  /** Non working and authorized area color */
  private final Color NON_WORKING_COLOR2 = new Color (0.0f, 0.6f, 0.0f);
  /** Selected slot central color */
  private final Color SELECTION_COLOR = new Color (0.6f, 0.6f, 0.6f);

  /** Pixel width of the time title column */
  private final int STD_TIME_WIDTH = 35;
  /** Pixel width of cumulated day columns */
  private final int STD_DAY_WIDTH = 708;
  /** Pixel width of cumulated inter-day columns */
  private final int STD_NIGHT_WIDTH = 12;
  /** Pixel height of a title area */
  private final int TITLE_HEIGHT = 12;
  /** Pixel height of a time period */
  private final int STD_SLOT_HEIGHT = 24;
  /** Pixel length of a time mark */
  private final int STD_TIME_MARK_LENGTH = 5;
  /** Width of the selection frame */
  private final int SELECTION_WIDTH = 5;
  /** Height of a text line */
  private final int TEXT_LINE_HEIGHT = 11;
  /** Vertical margin of information displays */
  private int TEXT_VMARGIN = 10;

  /** Contextual menu items */
  private final static String[] MENU_ITEMS = {
    "RETIRER", "EDITER", "SAUVER", "QUITTER"};
  /** Contextual menu activation */
  private boolean justEdited = false;

  /** Week buttons color */
  private final Color WEEK_BUTTON_COLOR = new Color (0.7f, 0.7f, 0.9f);
  /** Current week button color */
  private final Color CURRENT_WEEK_BUTTON_COLOR = new Color (0.9f, 0.7f, 0.7f);
  /** Week buttons height */
  private final int WEEK_BUTTON_HEIGHT = 16;

  /** Schedulable type buttons color */
  private final Color TYPE_BUTTON_COLOR = new Color (0.7f, 0.9f, 0.5f);
  /** Schedulable type buttons standard width */
  private final int STD_TYPE_BUTTON_WIDTH = 35;
  /** Schedulable type buttons standard height */
  private final int STD_TYPE_BUTTON_HEIGHT = 18;

  /** Schedulable item buttons color */
  private final float[] SCHED_BUTTON_COLOR = {0.9f, 0.6f, 0.3f};
  /** Schedulable item buttons standard width */
  private final int STD_SCHED_BUTTON_WIDTH = 40;
  /** Schedulable item buttons standard height */
  private final int STD_SCHED_BUTTON_HEIGHT = 15;

  /** Module buttons color */
  private final Color MODULE_BUTTON_COLOR = new Color (0.9f, 0.6f, 0.3f);
  /** Selected module button color */
  private final Color SELECTED_MODULE_BUTTON_COLOR
                         = new Color (0.4f, 0.3f, 0.1f);
  /** Module buttons standard width */
  private final int STD_MODULE_BUTTON_WIDTH = 50;
  /** Module buttons standard height */
  private final int STD_MODULE_BUTTON_HEIGHT = 12;

  /** Task buttons color */
  private final Color TASK_BUTTON_COLOR = new Color (0.9f, 0.6f, 0.3f);
  /** Selected task button color */
  private final Color SELECTED_TASK_BUTTON_COLOR = new Color (0.4f, 0.3f, 0.1f);
  /** Unselectable task buttons color */
  private final Color UNSEL_TASK_BUTTON_COLOR = new Color (0.9f, 0.8f, 0.6f);
  /** Task button standard width */
  private final int STD_TASK_BUTTON_WIDTH = 60;
  /** Task button standard height */
  private final int STD_TASK_BUTTON_HEIGHT = 12;

  /** Active menu button color */
  private final Color MENU_BUTTON_COLOR = new Color (0.7f, 0.9f, 0.5f);
  /** Inactive menu button color */
  private final Color NOMENU_TEXT_COLOR = new Color (0.7f, 0.5f, 0.9f);
  /** Menu buttons height */
  private final int STD_MENU_BUTTON_HEIGHT = 36;

  /** Load bar empty color */
  private final Color LOAD_ON_BAR_COLOR = new Color (0.9f, 0.3f, 0.4f);
  /** Load bar full color */
  private final Color LOAD_OFF_BAR_COLOR = new Color (0.3f, 0.9f, 0.4f);
  /** Load bar height */
  private final int STD_LOAD_BAR_HEIGHT = 40;
  /** Load bar margin height */
  private final int LOAD_BAR_SPACE = 2;


  /** Number of slots per day */
  private int gridLineCount = 1;
  /** Number of planable weeks */
  private int countOfWeeks = 1;
  /** Number of days per week */
  private int daysInWeek = 1;
  /** Calendar grid height */
  private int gridHeight = STD_SLOT_HEIGHT;
  /** Calendar grid width */
  private int gridWidth = STD_DAY_WIDTH + STD_NIGHT_WIDTH;
  /** Abscissae of button area */
  private int rightMargin = gridWidth + STD_TIME_WIDTH;
  /** Font height */
  private int fontHeight = 10;
  /** Font metrics */
  private FontMetrics fontMetrics;

  /** Time table area width */
  private int timeWidth = STD_TIME_WIDTH;
  /** Day column width */
  private int dayWidth = STD_DAY_WIDTH;
  /** Day columns separation width */
  private int nightWidth = STD_NIGHT_WIDTH;
  /** Type buttons width */
  private int typeButtonWidth = STD_TYPE_BUTTON_WIDTH;
  /** Schedulable item buttons width */
  private int schedButtonWidth = STD_SCHED_BUTTON_WIDTH;
  /** Module buttons width */
  private int moduleButtonWidth = STD_MODULE_BUTTON_WIDTH;
  /** Task buttons width */
  private int taskButtonWidth = STD_TASK_BUTTON_WIDTH;
  /** Menu buttons width */
  private int menuWidth = typeButtonWidth + schedButtonWidth
                          + moduleButtonWidth + taskButtonWidth;
  /** Week buttons width */
  private int weekButtonWidth = rightMargin + menuWidth;
  /** Load bar width */
  private int loadBarWidth = (weekButtonWidth * 4) / 5;
  /** Time mark length */
  private int timeMarkLength = STD_TIME_MARK_LENGTH;

  /** Slot area height */
  private int slotHeight = STD_SLOT_HEIGHT;
  /** Type buttons height */
  private int typeButtonHeight = STD_TYPE_BUTTON_HEIGHT;
  /** Schedulable item buttons height */
  private int schedButtonHeight = STD_SCHED_BUTTON_HEIGHT;
  /** Module buttons height */
  private int moduleButtonHeight = STD_MODULE_BUTTON_HEIGHT;
  /** Task buttons height */
  private int taskButtonHeight = STD_TASK_BUTTON_HEIGHT;
  /** Menu buttons height */
  private int menuButtonHeight = STD_MENU_BUTTON_HEIGHT;
  /** Load bar height */
  private int loadBarHeight = STD_LOAD_BAR_HEIGHT;

  /** Vertical offset of the display coordinates. */
  private int yRef = 20 * STD_SLOT_HEIGHT;

  /** Application mode */
  private boolean isManaging = false;

  /** Menu enabling */
  private boolean menuOn = true;

  /** Displayed data */
  private JikanData data = null;
  /** IO manager */
  private JikanIO myIO = null;



  /** Creates a jikan view.
   */
  public JikanView (JikanIO io)
  {
    myIO = io;
  }

  /** Sets the application data.
   */
  public void setData (JikanData data)
  {
    this.data = data;
    JikanCalendar cal = data.calendar ();
    gridLineCount = cal.slotTimes().length;
    countOfWeeks = cal.weekDays().length;
    daysInWeek = cal.weekDays()[data.week()].length;
  }

  /** Sets the application mode.
   */
  public void setManagingMode (boolean status)
  {
    isManaging = status;
  }

  /** Sets the initial application size.
   */
  public void restrictSize (int width, int height)
  {
    adaptSize (width, height);
  }

  /** Returns the display area width.
   */
  public int displayWidth ()
  {
    float width = rightMargin;
    width += typeButtonWidth + schedButtonWidth;
    if (isManaging) width += moduleButtonWidth + taskButtonWidth;
    return ((int) width);
  }

  /** Returns the display area height.
   */
  public int displayHeight ()
  {
    float height = gridHeight + 2 * TITLE_HEIGHT + WEEK_BUTTON_HEIGHT;
    if (isManaging) height += loadBarHeight + 2 * LOAD_BAR_SPACE;
    return ((int) height);
  }

  /** Draws Jikan graphical view.
   */
  public void paintComponent (Graphics g)
  {
    int w = getWidth ();
    int h = getHeight ();
    adaptSize (w, h);

    Graphics2D g2 = (Graphics2D) g.create ();
    g2.setFont (new Font ("Helvetica", 0, 12));
    fontMetrics = g2.getFontMetrics ();
    fontHeight = fontMetrics.getAscent ();

    g2.setColor (BACK_COLOR);
    drawBox (g2, 0, 0, w, h);

    displayGridBackground (g2);
    displayOccupancy (g2);
    displayCurrentSchedule (g2);
    displayTasks (g2);
    if (menuOn) displayMenu (g2);
    displaySchedulableTypes (g2);
    displayExploredSchedulables (g2);
    Schedulable[] mods = data.displayedModuleGroup ();
    if (mods != null)
    {
      displayModules (g2, mods);
      displayUnallocatedTasks (g2, data.unallocatedTasks ());
    }
    displayGridForeground (g2);
    displayWeeks (g2);
    displayLoad (g2);
    displayWarning (g2);
    if (isManaging)
    {
      JikanList info = data.infoToBeDisplayed ();
      if (info != null)
        if (isManaging) displayInformation (g2, info);
    }
  }


  /** Detects a zone from window coords.
   */
  public boolean select (int x, int y)
  {
    // Out of the active part of the window
    if (y > gridHeight + 2 * TITLE_HEIGHT + LOAD_BAR_SPACE + WEEK_BUTTON_HEIGHT)
      return (false);

    // Weeks stripe
    if (y > gridHeight + 2 * TITLE_HEIGHT + LOAD_BAR_SPACE)
      return (data.exploreWeek (x / (int) weekButtonWidth));

    // Out of the active part of the window
    if (x > rightMargin + typeButtonWidth + schedButtonWidth
            + moduleButtonWidth + taskButtonWidth) return false;

    // Menu area
    if (menuOn && x > rightMargin && y < gridHeight + 2 * TITLE_HEIGHT
        && y > gridHeight + 2 * TITLE_HEIGHT
               - MENU_ITEMS.length * menuButtonHeight)
      return (manageMenu ((gridHeight + 2 * TITLE_HEIGHT - y)
                          / menuButtonHeight));

    // Unallocated tasks column
    if (x > rightMargin + typeButtonWidth
            + schedButtonWidth + moduleButtonWidth)
      return (data.exploreUnallocatedTasks ((y - 2 * TITLE_HEIGHT)
                                            / taskButtonHeight));

    // Modules column
    if (x > rightMargin + typeButtonWidth + schedButtonWidth)
      return (data.exploreModule ((y - 2 * TITLE_HEIGHT)
                                  / moduleButtonHeight));

    // Schedulable items column
    if (x > rightMargin + typeButtonWidth)
      return (data.exploreSchedulableItem ((y - 2 * TITLE_HEIGHT)
                                           / schedButtonHeight));

    // Groups of schedulable items column
    if (x > rightMargin)
      return (data.exploreType ((y - 2 * TITLE_HEIGHT)
                                / typeButtonHeight, isManaging));

    // Calendar grid
    if (y > 2 * TITLE_HEIGHT && x > timeWidth)
    {
      Schedulable cur = data.current ();
      if (cur == null || ! isManaging) return (false);
      float posx = timeWidth + dayWidth;
      for (int i = 0; i < daysInWeek; i++)
      {
        if (x < posx)
        {
          if (data.selectedTask () != null)
            return (data.exploreSlot (i,
                    (y - 2 * TITLE_HEIGHT) / (int) slotHeight));
          else
          {
            int div = cur.partitionCount ();
            return (data.activateTask (i,
                    (y - 2 * TITLE_HEIGHT) / (int) slotHeight,
                    (int) ((x - posx + dayWidth) * div / dayWidth)));
          }
        }
        posx += nightWidth;
        if (x < posx) return (false);
        posx += dayWidth;
      }
    }

    return (false);
  }

  /** Detects a informative zone.
   */
  public boolean askInfo (int x, int y)
  {
    // Out of the active part of the window
    if (x > rightMargin + typeButtonWidth + schedButtonWidth
            + moduleButtonWidth + taskButtonWidth) return false;

    // Quit button (to force exit without saving)
    if (menuOn && x > rightMargin && y < gridHeight + 2 * TITLE_HEIGHT
        && y > gridHeight + 2 * TITLE_HEIGHT - menuButtonHeight)
    {
      data.resetModified ();
      return true;
    }

    // Unallocated tasks column
    if (data.displayedModuleGroup () != null
        && x > rightMargin + typeButtonWidth
            + schedButtonWidth + moduleButtonWidth)
    {
      JikanList tasks = data.unallocatedTasks ();
      if (tasks != null && tasks.size () != 0)
        return (data.infoAboutUnallocatedTasks ((y - 2 * TITLE_HEIGHT)
                                                / taskButtonHeight));
    }

    // Modules column
    if (data.displayedModuleGroup () != null
        && x > rightMargin + typeButtonWidth + schedButtonWidth)
      return (data.infoAboutModule ((y - 2 * TITLE_HEIGHT)
                                    / moduleButtonHeight));

    // Schedulable items column
    if (x > rightMargin + typeButtonWidth)
      return (data.infoAboutSchedulableItem ((y - 2 * TITLE_HEIGHT)
                                             / schedButtonHeight));

    // Groups of schedulable items column
    if (x > rightMargin)
      return (false);

    // Out of the active part of the window
    if (y > gridHeight + 2 * TITLE_HEIGHT + WEEK_BUTTON_HEIGHT) return (false);

    // Weeks stripe
    if (y > gridHeight + 2 * TITLE_HEIGHT + LOAD_BAR_SPACE)
      return (false);

    // Calendar grid
    if (y > 2 * TITLE_HEIGHT && x > timeWidth)
    {
      Schedulable cur = data.current ();
      if (cur == null) return (false);
      float posx = timeWidth + dayWidth;
      for (int i = 0; i < daysInWeek; i++)
      {
        if (x < posx)
        {
          int div = cur.partitionCount ();
          return (data.infoAboutScheduledTask (i,
                    (y - 2 * TITLE_HEIGHT) / (int) slotHeight,
                    (int) ((x - posx + dayWidth) * div / dayWidth)));
        }
        posx += nightWidth;
        if (x < posx) return (false);
        posx += dayWidth;
      }
    }

    return (false);
  }


  /** Updates the application size.
   */
 private void adaptSize (int width, int height)
  {
    // Height adaption
    height -= 2 * TITLE_HEIGHT + WEEK_BUTTON_HEIGHT + 2 * LOAD_BAR_SPACE;
    float hr = height / (float) (gridLineCount * STD_SLOT_HEIGHT
                                 + STD_LOAD_BAR_HEIGHT);
    slotHeight = (int) (STD_SLOT_HEIGHT * hr);
    typeButtonHeight = (int) (STD_TYPE_BUTTON_HEIGHT * hr);
    schedButtonHeight = (int) (STD_SCHED_BUTTON_HEIGHT * hr);
    moduleButtonHeight = (int) (STD_MODULE_BUTTON_HEIGHT * hr);
    taskButtonHeight = (int) (STD_TASK_BUTTON_HEIGHT * hr);
    menuButtonHeight = (int) (STD_MENU_BUTTON_HEIGHT * hr);
    gridHeight = gridLineCount * slotHeight;
    loadBarHeight = height - gridHeight;
    if (loadBarHeight % 2 == 1) loadBarHeight -= 1;

    // Width adaption
    float stdWidth = STD_TIME_WIDTH + STD_DAY_WIDTH + STD_NIGHT_WIDTH
                     + STD_TYPE_BUTTON_WIDTH + STD_SCHED_BUTTON_WIDTH;
    if (isManaging) stdWidth += STD_MODULE_BUTTON_WIDTH + STD_TASK_BUTTON_WIDTH;
    float wr = width / stdWidth;
    typeButtonWidth = (int) (STD_TYPE_BUTTON_WIDTH * wr);
    schedButtonWidth = (int) (STD_SCHED_BUTTON_WIDTH * wr);
    moduleButtonWidth = (int) (STD_MODULE_BUTTON_WIDTH * wr);
    taskButtonWidth = (int) (STD_TASK_BUTTON_WIDTH * wr);
    menuWidth = typeButtonWidth + schedButtonWidth;
    if (isManaging) menuWidth += moduleButtonWidth + taskButtonWidth;
    timeWidth = (int) (STD_TIME_WIDTH * wr);
    timeMarkLength = (int) (STD_TIME_MARK_LENGTH * wr);
    float wr2 = (float) (width - timeWidth - menuWidth)
                / ((STD_NIGHT_WIDTH + STD_DAY_WIDTH) * daysInWeek);
    nightWidth = (int) (STD_NIGHT_WIDTH * wr2);
    dayWidth = (int) (STD_DAY_WIDTH * wr2);
    gridWidth = daysInWeek * (dayWidth + nightWidth);
    rightMargin = timeWidth + gridWidth;
    weekButtonWidth = (int) (width / countOfWeeks);
    loadBarWidth = (weekButtonWidth * 4) / 5;
    yRef = gridHeight + 2 * TITLE_HEIGHT;
  }

  /** Displays the calendar grid background.
   */
  private void displayGridBackground (Graphics2D g2)
  {
    // Legends
    g2.setColor (LEGEND_COLOR);
    // Time legend
    drawBox (g2, 0, 0, timeWidth, gridHeight);
    // Columns legend
    drawBox (g2, timeWidth, gridHeight,
                 rightMargin - timeWidth, TITLE_HEIGHT);
    // Page legend
    drawBox (g2, 0, gridHeight + TITLE_HEIGHT, rightMargin, TITLE_HEIGHT);
    // Night columns
    int posx = timeWidth;
    for (int i = 0; i < daysInWeek; i++)
    {
      posx += nightWidth + dayWidth;
      drawBox (g2, posx, 0, nightWidth, gridHeight);
    }
  }

  /** Displays the calendar grid foreground (separators and texts).
   */
  private void displayGridForeground (Graphics2D g2)
  {
    JikanCalendar cal = data.calendar ();

    // Separators
    g2.setColor (FRAME_COLOR);

    // Horizontal lines
    drawLine (g2, 0, 0, rightMargin, 0);
    drawLine (g2, 0, gridHeight, timeWidth, gridHeight);
    for (int i = 0; i < daysInWeek; i++)
    {
      drawHLine (g2, timeWidth + i * (dayWidth + nightWidth),
                 gridHeight, dayWidth);
      drawHLine (g2, timeWidth + i * (dayWidth + nightWidth),
                 gridHeight + 2 * TITLE_HEIGHT, dayWidth);
    }
    drawHLine (g2, timeWidth, gridHeight + TITLE_HEIGHT,
                   rightMargin - timeWidth);
    drawHLine (g2, 0, gridHeight + 2 * TITLE_HEIGHT, rightMargin);

    // Vertical lines
    drawVLine (g2, 0, 0, gridHeight + 2 * TITLE_HEIGHT);
    int posx = timeWidth;
    drawVLine (g2, posx, 0, gridHeight + 2 * TITLE_HEIGHT);
    for (int i = 0; i < daysInWeek; i++)
    {
      posx += dayWidth;
      drawVLine (g2, posx, 0, gridHeight + slotHeight / 2);
      posx += nightWidth;
      drawVLine (g2, posx, 0, gridHeight + slotHeight / 2);
    }
    drawVLine (g2, rightMargin, gridHeight + slotHeight - slotHeight / 2,
               gridHeight + slotHeight / 2);

    // Time marks
    for (int i = 1; i < gridLineCount; i++)
      drawHLine (g2, timeWidth - timeMarkLength, i * slotHeight,
                 timeMarkLength);

    // Time text
    String[] sTimes = cal.slotHours ();
    for (int i = 1; i < gridLineCount; i++)
      drawText (g2, 0.0f, gridHeight - i * slotHeight - 4.0f,
                timeWidth - timeMarkLength, TEXT_LINE_HEIGHT, sTimes[i]);

    // Day text
    String[][] wDays = cal.weekDays ();
    for (int i = 0; i < daysInWeek; i++)
      drawText (g2, timeWidth + (dayWidth + nightWidth) * i, gridHeight,
                dayWidth, TITLE_HEIGHT,
                wDays[data.week ()][i]);
 
    // Week text
    drawText (g2, 0, gridHeight + TITLE_HEIGHT, rightMargin, TITLE_HEIGHT,
              "Semaine " + cal.weekNo (data.week ()));
  }

  /** Displays the calendar occupancy grid.
   */
  private void displayOccupancy (Graphics2D g2)
  {
    boolean occupied = false;
    JikanCalendar cal = data.calendar ();
    int posx = timeWidth;
    g2.setColor (NON_WORKING_COLOR);
    for (int i = 0; i < daysInWeek; i++)
    {
      int posy = gridHeight;
      occupied = ! cal.occ.isFreeFirst (data.week (), i);
      for (int j = 0; j < gridLineCount; j++)
      {
        posy -= slotHeight;
        if (occupied) drawBox (g2, posx, posy, dayWidth, slotHeight);
        occupied = ! cal.occ.isFreeNext ();
      }
      posx += dayWidth + nightWidth;
    }
  }

  /** Displays a teacher occupancy grid.
   */
  private void displayTeacherOccupancy (Graphics2D g2)
  {
    Teacher t = (Teacher) data.current ();
    boolean b1 = false, b2 = false;
    int posy, posx = timeWidth;
    JikanCalendar cal = data.calendar ();
    Occupancy avoid = t.avoidableSlots ();

    g2.setColor (NON_WORKING_COLOR12);
    for (int i = 0; i < daysInWeek; i++)
    {
      posy = gridHeight;
      b1 = cal.occ.isFreeFirst (data.week (), i);
      b2 = avoid.isFreeFirst (data.week (), i);
      for (int j = 0; j < gridLineCount; j++)
      {
        posy -= slotHeight;
        if (! (b1 || b2)) drawBox (g2, posx, posy, dayWidth, slotHeight);
        b1 = cal.occ.isFreeNext ();
        b2 = avoid.isFreeNext ();
      }
      posx += dayWidth + nightWidth;
    }

    posx = timeWidth;
    g2.setColor (Color.YELLOW);
    for (int i = 0; i < daysInWeek; i++)
    {
      posy = gridHeight;
      b1 = cal.occ.isFreeFirst (data.week (), i);
      b2 = avoid.isFreeFirst (data.week (), i);
      for (int j = 0; j < gridLineCount; j++)
      {
        posy -= slotHeight;
        if (b1 && ! b2) drawBox (g2, posx, posy, dayWidth, slotHeight);
        b1 = cal.occ.isFreeNext ();
        b2 = avoid.isFreeNext ();
      }
      posx += dayWidth + nightWidth;
    }

    posx = timeWidth;
    Occupancy forbid = t.forbiddenSlots ();
    g2.setColor (NON_WORKING_COLOR1);
    for (int i = 0; i < daysInWeek; i++)
    {
      posy = gridHeight;
      b1 = cal.occ.isFreeFirst (data.week (), i);
      b2 = forbid.isFreeFirst (data.week (), i);
      for (int j = 0; j < gridLineCount; j++)
      {
        posy -= slotHeight;
        if (! (b1 || b2)) drawBox (g2, posx, posy, dayWidth, slotHeight);
        b1 = cal.occ.isFreeNext ();
        b2 = forbid.isFreeNext ();
      }
      posx += dayWidth + nightWidth;
    }

    posx = timeWidth;
    g2.setColor (Color.RED);
    for (int i = 0; i < daysInWeek; i++)
    {
      posy = gridHeight;
      b1 = cal.occ.isFreeFirst (data.week (), i);
      b2 = forbid.isFreeFirst (data.week (), i);
      for (int j = 0; j < gridLineCount; j++)
      {
        posy -= slotHeight;
        if (b1 && ! b2) drawBox (g2, posx, posy, dayWidth, slotHeight);
        b1 = cal.occ.isFreeNext ();
        b2 = forbid.isFreeNext ();
      }
      posx += dayWidth + nightWidth;
    }
  }

  /** Displays a student occupancy grid.
   */
  private void displayStudentOccupancy (Graphics2D g2)
  {
    StudentPromo s = (StudentPromo) data.current ();
    boolean b1 = false, b2 = false;
    int posy, posx = timeWidth;
    JikanCalendar cal = data.calendar ();

    Occupancy forbid = s.forbiddenSlots ();
    g2.setColor (NON_WORKING_COLOR1);
    for (int i = 0; i < daysInWeek; i++)
    {
      posy = gridHeight;
      b1 = cal.occ.isFreeFirst (data.week (), i);
      b2 = forbid.isFreeFirst (data.week (), i);
      for (int j = 0; j < gridLineCount; j++)
      {
        posy -= slotHeight;
        if (! (b1 || b2)) drawBox (g2, posx, posy, dayWidth, slotHeight);
        b1 = cal.occ.isFreeNext ();
        b2 = forbid.isFreeNext ();
      }
      posx += dayWidth + nightWidth;
    }

    posx = timeWidth;
    g2.setColor (Color.RED);
    for (int i = 0; i < daysInWeek; i++)
    {
      posy = gridHeight;
      b1 = cal.occ.isFreeFirst (data.week (), i);
      b2 = forbid.isFreeFirst (data.week (), i);
      for (int j = 0; j < gridLineCount; j++)
      {
        posy -= slotHeight;
        if (b1 && ! b2) drawBox (g2, posx, posy, dayWidth, slotHeight);
        b1 = cal.occ.isFreeNext ();
        b2 = forbid.isFreeNext ();
      }
      posx += dayWidth + nightWidth;
    }
  }

  /** Displays tasks required schedulable items occupancy grid.
   */
  private void displayTaskRequiresOccupancy (Graphics2D g2)
  {
    Color[] colors = {Color.GREEN, NON_WORKING_COLOR2, Color.YELLOW,
                      NON_WORKING_COLOR12, Color.RED, NON_WORKING_COLOR1};
    int posy, posx = timeWidth;
    JikanCalendar cal = data.calendar ();

    Task t = data.selectedTask ();
    int duration = t.nextDuration ();
    if (data.selectedSlot () != -1) t.releaseRequisites (data.selectedSlot ());

    for (int i = 0; i < daysInWeek; i++)
    {
      posy = gridHeight - slotHeight;
      int color = t.occupancyLevelOfFirst (data.week (), i, duration) * 2;
      if (! (cal.occ.isFreeFirst (data.week (), i))) color ++;
      g2.setColor (colors[color]);
      drawBox (g2, posx, posy, dayWidth, slotHeight);
      for (int j = 0; j < gridLineCount - duration; j++)
      {
        posy -= slotHeight;
        int level = t.occupancyLevelOfNext () * 2;
        if (! (cal.occ.isFreeNext ())) level ++;
	if (color != level)
	{
          color = level;
          g2.setColor (colors[color]);
	}
        drawBox (g2, posx, posy, dayWidth, slotHeight);
      }
      for (int j = 0; j < duration - 1; j++)
      {
        posy -= slotHeight;
        int level = (cal.occ.isFreeNext ()) ? 4 : 5;
	if (color != level)
	{
          color = level;
          g2.setColor (colors[color]);
	}
        drawBox (g2, posx, posy, dayWidth, slotHeight);
      }
      posx += dayWidth + nightWidth;
    }
    if (data.selectedSlot () != -1) t.affectRequisites (data.selectedSlot ());
  }

  /** Displays the week bar.
   */
  private void displayWeeks (Graphics2D g2)
  {
    int posx = 0, posy = - WEEK_BUTTON_HEIGHT - LOAD_BAR_SPACE;
    JikanCalendar cal = data.calendar ();
    int nbd = cal.weekDays().length;
    g2.setColor (WEEK_BUTTON_COLOR);
    for (int i = 0; i < nbd; i++)
    {
      if (i != data.week ())
        drawButton (g2, posx, posy, weekButtonWidth, WEEK_BUTTON_HEIGHT);
      posx += weekButtonWidth;
    }

    posx = 0;
    g2.setColor (TEXT_COLOR);
    for (int i = 0; i < nbd; i++)
    {
      if (i != data.week ())
        drawText (g2, posx, posy, weekButtonWidth, WEEK_BUTTON_HEIGHT,
                  "" + cal.weekNo (i));
      posx += weekButtonWidth;
    }

    // Selected week
    g2.setColor (TEXT_COLOR);
    posx = data.week () * weekButtonWidth;
    drawButton (g2, posx, posy, weekButtonWidth, WEEK_BUTTON_HEIGHT);
    g2.setColor (WEEK_BUTTON_COLOR);
    drawText (g2, posx, posy, weekButtonWidth, WEEK_BUTTON_HEIGHT,
              "" + cal.weekNo (data.week ()));
  }

  /** Displays the load bars.
   */
  private void displayLoad (Graphics2D g2)
  {
    Schedulable cur = data.current ();
    JikanCalendar cal = data.calendar ();
    int nbd = cal.weekDays().length;
    if (cur != null)
    {
      int posx = (weekButtonWidth - loadBarWidth) / 2;
      int posy = - WEEK_BUTTON_HEIGHT - loadBarHeight - 2 * LOAD_BAR_SPACE;
  
      g2.setColor (LOAD_OFF_BAR_COLOR);
      for (int i = 0; i < nbd; i++)
      {
        drawBox (g2, posx, posy, loadBarWidth, loadBarHeight);
        posx += weekButtonWidth;
      }

      posx = (weekButtonWidth - loadBarWidth) / 2;
      g2.setColor (LOAD_ON_BAR_COLOR);
      for (int i = 0; i < nbd; i++)
      {
        int height = (int) (cur.getLoad (i) * loadBarHeight / 80. + 0.5);
        if (height > loadBarHeight) height = loadBarHeight;
	if (height != 0) drawBox (g2, posx, posy, loadBarWidth, height);
        posx += weekButtonWidth;
      }
      posx = (weekButtonWidth - loadBarWidth) / 2;
      posy += loadBarHeight / 2;
      g2.setColor (FRAME_COLOR);
      for (int i = 0; i < nbd; i++)
      {
        drawHLine (g2, posx, posy, loadBarWidth);
        posx += weekButtonWidth;
      }
    }
  }

  /** Displays the current schedulable object features.
   */
  private void displayCurrentSchedule (Graphics2D g2)
  {
    Schedulable cur = data.current ();
    if (cur != null)
    {
      // Draws the current schedulable item name
      g2.setColor (cur.color ());
      drawBox (g2, 0, gridHeight, timeWidth, 2 * TITLE_HEIGHT);
      g2.setColor (TEXT_COLOR);
      drawText (g2, 0.0f, gridHeight, timeWidth, 2 * TITLE_HEIGHT,
                cur.name ());

      // Draws the current schedulable item occupancy grid
      if (data.selectedTask () != null) displayTaskRequiresOccupancy (g2);
      else
      {
        int type = cur.type ();
        if (type == Schedulable.TYPE_TEACHER) displayTeacherOccupancy (g2);
        else if (type == Schedulable.TYPE_STUDENT) displayStudentOccupancy (g2);
      }
    }
  }

  /** Displays the positioned tasks on the grid.
   */
  private void displayTasks (Graphics2D g2)
  {
    Schedulable cur = data.current ();
    if (cur == null) return;
    int div = cur.partitionCount ();
    int[][] studentZone = {{0, 1}};
    JikanList slots = cur.getSlots (data.week ());
    if (slots == null) return;
    Slot selection = (data.selectedSlot () == -1 ?
                      null : data.selectedTask().slot(data.selectedSlot ()));
    for (int i = 0; i < slots.size (); i++)
    {
      Slot slot = (Slot) (slots.get(i));
      String[] text = slot.text (cur.type (), slot == selection);
      int[] timeZone = slot.timeZone ();
      if (div != 1) studentZone = slot.studentZone ((Student) cur);
      int posx = timeWidth + (dayWidth + nightWidth) * slot.day ();
      int posy = gridHeight - slotHeight * timeZone[0];
      int height = slotHeight * timeZone[1];
      Color color = slot.color (cur.type ());
      for (int j = 0; j < studentZone.length; j++)
      {
        int sz = (studentZone[j][0] * dayWidth) / div;
        int posx2 = posx + sz;
        int width = ((studentZone[j][0] + studentZone[j][1]) * dayWidth) / div
                    - sz;
        g2.setColor (color);
        drawFrameBox (g2, posx2, posy, width, height);
        if (slot == selection)
        {
          g2.setColor (SELECTION_COLOR);
          drawFrameBox (g2, posx2 + SELECTION_WIDTH, posy + SELECTION_WIDTH,
                   width - 2 * SELECTION_WIDTH, height - 2 * SELECTION_WIDTH);
        }
        if (text != null)
        {
          g2.setColor (TEXT_COLOR);
          drawText (g2, posx2, posy, width, height, text);
        }
      }
    }
  }

  /** Displays the schedulable object types.
   */
  private void displaySchedulableTypes (Graphics2D g2)
  {
    // Schedulable items names
    int nbTypes = Schedulable.numberOfTypes ();
    String[][] groupNames = new String[nbTypes][];
    for (int i = 0; i < nbTypes; i++)
      groupNames[i] = data.groupNames (i);

    // Buttons background
    int posy = gridHeight - typeButtonHeight;
    g2.setColor (TYPE_BUTTON_COLOR);
    for (int i = 0; i < nbTypes; i++)
    {
      for (int j = 0; j < groupNames[i].length; j++)
      {
        drawButton (g2, rightMargin, posy, typeButtonWidth, typeButtonHeight);
        posy -= typeButtonHeight;
      }
      posy -= typeButtonHeight;
    }
 
    // Buttons title
    g2.setColor (FRAME_COLOR);
    posy = gridHeight - typeButtonHeight;
    for (int i = 0; i < nbTypes; i++)
    {
      for (int j = 0; j < groupNames[i].length; j++)
      {
        drawText (g2, rightMargin, posy,
                  typeButtonWidth, typeButtonHeight, groupNames[i][j]);
        posy -= typeButtonHeight;
      }
      posy -= typeButtonHeight;
    }
  }

  /** Displays the explored schedulable items.
   */
  private void displayExploredSchedulables (Graphics2D g2)
  {
    Schedulable[] schedulableItems = data.schedulableItems ();
    if (schedulableItems == null) return;
 
    // Buttons background
    int posy = gridHeight - schedButtonHeight;
    for (int i = 0; i < schedulableItems.length; i++)
    {
      g2.setColor (schedulableItems[i].color ());
      drawButton (g2, rightMargin + typeButtonWidth, posy,
                  schedButtonWidth, schedButtonHeight);
      posy -= schedButtonHeight;
    }
 
    // Buttons text
    g2.setColor (TEXT_COLOR);
    posy = gridHeight - schedButtonHeight;
    for (int i = 0; i < schedulableItems.length; i++)
    {
      drawText (g2, rightMargin + typeButtonWidth, posy,
                schedButtonWidth, schedButtonHeight,
                schedulableItems[i].name ());
      posy -= schedButtonHeight;
    }
  }

  /** Displays the available modules.
   */
  private void displayModules (Graphics2D g2, Schedulable[] mods)
  {
    int num = data.selectedModule ();

    // Buttons background
    int posx = rightMargin + typeButtonWidth + schedButtonWidth;
    int posy = gridHeight - moduleButtonHeight;
    
    g2.setColor (MODULE_BUTTON_COLOR);
    for (int i = 0; i < mods.length; i++)
    {
      if (i != num)
        drawButton (g2, posx, posy, moduleButtonWidth, moduleButtonHeight);
      posy -= moduleButtonHeight;
    }

    // Selected button background
    if (num != -1)
    {
      g2.setColor (SELECTED_MODULE_BUTTON_COLOR);
      drawButton (g2, posx, gridHeight - (num + 1) * moduleButtonHeight,
                  moduleButtonWidth, moduleButtonHeight);
    }

    // Buttons text
    g2.setColor (TEXT_COLOR);
    posy = gridHeight - moduleButtonHeight;
    for (int i = 0; i < mods.length; i++)
    {
      if (i != num)
        drawText (g2, posx, posy, moduleButtonWidth, moduleButtonHeight,
                  mods[i].name ());
      posy -= moduleButtonHeight;
    }
    if (num != -1)
    {
      g2.setColor (SELECTED_TEXT_COLOR);
      drawText (g2, posx, gridHeight - (num + 1) * moduleButtonHeight,
                moduleButtonWidth, moduleButtonHeight, mods[num].name ());
    }
  }

  /** Displays the tasks to be positioned.
   */
  private void displayUnallocatedTasks (Graphics2D g2, JikanList tasks)
  {
    if (tasks == null || tasks.size () == 0) return;
    int num = data.selectedTaskIndex ();

    // Buttons background
    int posx = rightMargin + typeButtonWidth
               + schedButtonWidth + moduleButtonWidth;
    int posy = gridHeight - taskButtonHeight;
    
    g2.setColor (TASK_BUTTON_COLOR);
    for (int i = 0; i < tasks.size (); i++)
    {
      if (i != num && data.isSelectableTask (i))
        drawButton (g2, posx, posy, taskButtonWidth, taskButtonHeight);
      posy -= taskButtonHeight;
    }
    posy = gridHeight - taskButtonHeight;
    g2.setColor (UNSEL_TASK_BUTTON_COLOR);
    for (int i = 0; i < tasks.size (); i++)
    {
      if (i != num && (! data.isSelectableTask (i)))
        drawButton (g2, posx, posy, taskButtonWidth, taskButtonHeight);
      posy -= taskButtonHeight;
    }
    if (num != -1)
    {
      g2.setColor (SELECTED_TASK_BUTTON_COLOR);
      drawButton (g2, posx, gridHeight - (num + 1) * taskButtonHeight,
                  taskButtonWidth, taskButtonHeight);
    }
 
    // Buttons text
    g2.setColor (TEXT_COLOR);
    posy = gridHeight - taskButtonHeight;
    for (int i = 0; i < tasks.size (); i++)
    {
      if (i != num && data.isSelectableTask (i))
        drawText (g2, posx, posy, taskButtonWidth, taskButtonHeight,
                  data.unallocatedCount (i) + " " +
                  ((Task) (tasks.get(i))).name ());
      posy -= taskButtonHeight;
    }
    g2.setColor (UNSEL_TEXT_COLOR);
    posy = gridHeight - taskButtonHeight;
    for (int i = 0; i < tasks.size (); i++)
    {
      if (i != num && (! data.isSelectableTask (i)))
        drawText (g2, posx, posy, taskButtonWidth, taskButtonHeight,
                  data.unallocatedCount (i) + " " +
                  ((Task) (tasks.get(i))).name ());
      posy -= taskButtonHeight;
    }
    if (num != -1)
    {
      g2.setColor (SELECTED_TEXT_COLOR);
      drawText (g2, posx, gridHeight - (num + 1) * taskButtonHeight,
                taskButtonWidth, taskButtonHeight,
                data.unallocatedCount (num) + " " +
                ((Task) (tasks.get(num))).name ());
    }
  }

  /** Displays a possible warning.
   */
  private void displayWarning (Graphics2D g2)
  {
    String warning = data.warning ();
    if (warning == null) return;
    g2.setColor (Color.WHITE);
    drawFrameBox (g2, timeWidth + dayWidth / 2,
                  gridHeight - (slotHeight * 7) / 8,
                  rightMargin - timeWidth - dayWidth,
                  (slotHeight * 3) / 4);
    g2.setColor (TEXT_COLOR);
    drawText (g2, timeWidth + dayWidth / 2,
                  gridHeight - slotHeight * 0.75f,
                  rightMargin - timeWidth - dayWidth,
                  slotHeight / 2, warning);
  }

  /** Displays an information text.
   */
  private void displayInformation (Graphics2D g2, JikanList texts)
  {
    String[] poestry = new String[texts.size ()];
    texts.fill (poestry);
    int width = fontMetrics.stringWidth (poestry[0]);
    for (int i = 0; i < poestry.length; i++)
    {
      int size = fontMetrics.stringWidth (poestry[i]);
      if (size > width) width = size;
    }
    width += 10;

    int height = TEXT_VMARGIN + TEXT_LINE_HEIGHT * (int) (poestry.length);
    g2.setColor (Color.WHITE);
    drawFrameBox (g2, timeWidth + 10, 10, width, height);
    g2.setColor (TEXT_COLOR);
    float posy = height - 5.0f;
    for (int i = 0; i < poestry.length; i++)
    {
      drawText (g2, timeWidth + 10.0f, posy, width, 10.0f, poestry[i]);
      posy -= TEXT_LINE_HEIGHT;
    }
  }


  /** Draws a line.
   */
  private void drawLine (Graphics2D g2, int x1, int y1, int x2, int y2)
  {
    g2.drawLine (x1, yRef - y1, x2, yRef - y2);
  }

  /** Draws a horizontal line.
   */
  private void drawHLine (Graphics2D g2, int x, int y, int l)
  {
    g2.drawLine (x, yRef - y, x + l, yRef - y);
  }

  /** Draws a vertical line.
   */
  private void drawVLine (Graphics2D g2, int x, int y, int l)
  {
    g2.drawLine (x, yRef - y, x, yRef - y - l);
  }

  /** Draws a rectangular frame.
   */
  private void drawFrame (Graphics2D g2, int posx, int posy,
                          int width, int height)
  {
    g2.drawRect (posx, yRef - posy - height, width, height);
  }

  /** Draws a rectangular box.
   */
  private void drawBox (Graphics2D g2, int posx, int posy,
                                       int width, int height)
  {
    g2.fillRect (posx, yRef - posy - height, width, height);
  }

  /** Draws a rectangular box with a frame.
   */
  private void drawFrameBox (Graphics2D g2, int posx, int posy,
                             int width, int height)
  {
    g2.fillRect (posx, yRef - posy - height, width, height);
    g2.setColor (FRAME_COLOR);
    g2.drawRect (posx, yRef - posy - height, width, height);
  }

  /** Draws a button.
   */
  private void drawButton (Graphics2D g2, int posx, int posy, int w, int h)
  {
    int w1 = w / 20; if (w1 == 0) w1 = 1;
    int w2 = (3 * w) / 20; if (w2 < 2) w2 = 2;
    int w3 = w - w2, w4 = w - w1;
    int h1 = h / 10; if (h1 == 0) h1 = 1;
    int h2 = (3 * h) / 10; if (h2 < 2 ) h2 = 2;
    int h3 = h - h2, h4 = h - h1;
    posy = yRef - posy;
    int[] xs = {posx + w1, posx + w2, posx + w3, posx + w4,
                posx + w4, posx + w3, posx + w2, posx + w1};
    int[] ys = {posy - h2, posy - h1, posy - h1, posy - h2,
                posy - h3, posy - h4, posy - h4, posy - h3};
    g2.fillPolygon (xs, ys, 8);
  }

  /** Draws a centered text in the given area.
   */
  private void drawText (Graphics2D g2, float posx, float posy,
                         float width, float height, String text)
  {
    g2.drawString (text,
                   (int) (posx + (width - fontMetrics.stringWidth (text)) / 2),
                   yRef - (int) (posy + (height - fontHeight) / 2));
  }

  /** Draws a centered paragraph in the given area.
   */
  private void drawText (Graphics2D g2, float posx, float posy,
                         float width, float height, String[] text)
  {
    if (text == null) return;
    posy += (height + (text.length - 1) * fontHeight) / 2;
    for (int i = 0; i < text.length; i ++)
    {
      drawText (g2, posx, posy, width, fontHeight, text[i]);
      posy -= fontHeight;
    }
  }

  /** Draws the contextual menu.
   */
  private void displayMenu (Graphics2D g2)
  {
    // Buttons background
    int posy = 0;
    g2.setColor (MENU_BUTTON_COLOR);
    for (int j = 0; j < MENU_ITEMS.length; j++)
    {
      drawButton (g2, rightMargin, posy, menuWidth, menuButtonHeight);
      posy += menuButtonHeight;
    }
 
    // Quit item
    posy = 0;
    if (data.modified ()) g2.setColor (NOMENU_TEXT_COLOR);
    else g2.setColor (FRAME_COLOR);
    drawText (g2, rightMargin, posy,
              menuWidth, menuButtonHeight, MENU_ITEMS[3]);
    // Save item
    posy += menuButtonHeight;
    if (data.modified ()) g2.setColor (FRAME_COLOR);
    else g2.setColor (NOMENU_TEXT_COLOR);
    drawText (g2, rightMargin, posy,
              menuWidth, menuButtonHeight, MENU_ITEMS[2]);
    // Edit item
    posy += menuButtonHeight;
    if (data.current () == null || justEdited) g2.setColor (NOMENU_TEXT_COLOR);
    else g2.setColor (FRAME_COLOR);
    justEdited = false;
    drawText (g2, rightMargin, posy,
              menuWidth, menuButtonHeight, MENU_ITEMS[1]);
    // Remove item
    posy += menuButtonHeight;
    if (data.isSelectedSlot ()) g2.setColor (FRAME_COLOR);
    else g2.setColor (NOMENU_TEXT_COLOR);
    drawText (g2, rightMargin, posy,
              menuWidth, menuButtonHeight, MENU_ITEMS[0]);
  }

  private boolean manageMenu (int item)
  {
    switch (item)
    {
      case 0 : // sortie
        if (! data.modified ()) System.exit (0);
        return (false);
      case 1 : // sauvegarde
        if (data.modified ())
        {
          myIO.save ();
          data.resetModified ();
        }
        return (true);
      case 2 : // edition
        if (data.current () != null) data.print ();
        justEdited = true;
        return (true);
      case 3 : // zigouille
        data.removeSelectedSlot ();
        return (true);
      default :
        return (false);
    }
  }
}

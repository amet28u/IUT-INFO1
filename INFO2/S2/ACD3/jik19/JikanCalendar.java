import java.util.Calendar;
import java.text.DecimalFormat;


/** Calendar frame for Jikan planner.
  * @author Philippe Even
  */
public class JikanCalendar
{
  /** Month names */
  private static final String[] MONTH_FULL_NAMES = {
    "janvier", "fevrier", "mars", "avril", "mai", "juin",
    "juillet", "aout", "septembre", "octobre", "novembre", "decembre"};
  private static final String[] MONTH_ACRONYMS = {
    "janv.", "fev.", "mars", "avr.", "mai", "juin",
    "juil.", "aout", "sept.", "oct.", "nov.", "dec."};
  private static final String[] MONTH_SHORT = {
    "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
  private static final String[] WEEK_DAY_NAMES = {
    "Dimanche", "Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi"};


  /** New IUT schedule modality */
  private final boolean nouveauxHoraires = true;

  /** Number of the first week of the scolar year */
  private int firstWeek;
  /** Number of the last week of the regular year */
  private int lastWeek;
  /** Day names for each week */
  private String[][] weekDays;
  /** Titles of the visible slots on the grid */
  private String[] slotTimes, slotHours;

  /** Standard occupancy grid */
  public Occupancy occ;
  /** Present calendar */
  private Calendar cal = null;


  /** Creates the calendar */
  public JikanCalendar (int year, int firstWeek,
                        int yearLength, int weekLength)
  {
    DecimalFormat df = new DecimalFormat ("00");
    cal = Calendar.getInstance ();
    this.firstWeek = firstWeek;
    weekDays = new String[yearLength][weekLength];
    Calendar tmpcal = Calendar.getInstance ();
    tmpcal.set (Calendar.YEAR, year);
    tmpcal.set (Calendar.WEEK_OF_YEAR, firstWeek);
    tmpcal.set (Calendar.DAY_OF_WEEK, Calendar.MONDAY);
    this.lastWeek = tmpcal.getActualMaximum (Calendar.WEEK_OF_YEAR);
    for (int i = 0; i < yearLength; i++)
      for (int j = 0; j < 7; j++)
      {
        if (j < weekLength) weekDays[i][j] =
          WEEK_DAY_NAMES[tmpcal.get (Calendar.DAY_OF_WEEK) - 1]
          + " " + df.format (tmpcal.get (Calendar.DAY_OF_MONTH))
	  + " " + MONTH_ACRONYMS[tmpcal.get (Calendar.MONTH)];
	tmpcal.add (Calendar.DAY_OF_MONTH, 1);
      }
    occ = new Occupancy (yearLength, weekLength);
  }

  /** Sets working slots for the given week day */
  public void setWorkingSlots (int day, String code)
  {
    occ.forbidEachWeek (day, code);
  }

  /** Sets working slots for the given day of the given week */
  public void setWorkingSlots (int week, int day, String code)
  {
    occ.forbid (week, day, code);
  }

  /** Sets the visible slots on the grid */
  public void setVisibleSlots (int duration, int start, int count)
  {
    DecimalFormat df = new DecimalFormat ("00");
    slotTimes = new String[count];
    slotHours = new String[count];
    for (int i = 0; i < count; i++)
    {
      int myHour = duration * (start + i) / 60;
      int myMinute = duration * (start + i) % 60;
      slotHours[i] = (myMinute == 0 ? df.format (myHour) : "");
      if (nouveauxHoraires)
      {
        if (myHour >= 14)
        {
          myMinute -= 15;
          if (myMinute < 0)
          {
            myHour --;
            myMinute += 60;
          }
        }
      }
      slotTimes[i] = df.format (myHour) + "h" + df.format (myMinute);
    }
  }

  /** Returns the day names for each week */
  public String[][] weekDays ()
  {
    return (weekDays);
  }

  /** Returns the visible slots on the grid */
  public String[] slotTimes ()
  {
    return (slotTimes);
  }

  /** Returns the visible hours on the grid */
  public String[] slotHours ()
  {
    return (slotHours);
  }

  /** Sets holydays */
  public void setHolydays (int week, int day)
  {
    occ.withdrawDay (week, day);
  }

  /** Returns the number of the given week */
  public int weekNo (int index)
  {
    if (index <= lastWeek - firstWeek) return (firstWeek + index);
    return (index + firstWeek - lastWeek);
  }

  /** Returns the index of the given week */
  public int weekIndex (int week)
  {
    if (week < 1) return (-1);
    if (week > lastWeek) return (-1);
    if (week >= firstWeek) return (week - firstWeek);
    if (week >= weekDays.length + firstWeek - lastWeek) return (-1);
    return (week + lastWeek - firstWeek);
  }

  /** Returns the index of the present week (from the official calendar) */
  public int activeWeek ()
  {
    int index = weekIndex (cal.get (Calendar.WEEK_OF_YEAR));
    if (index == -1)
      index = (cal.get (Calendar.YEAR) == 2007 ? 0 : weekDays.length - 1);
    return (index);
  }

  /** Returns the name of the present day */
  public String presentDayName ()
  {
    return (cal.get (Calendar.DAY_OF_MONTH) + " "
            + MONTH_FULL_NAMES[cal.get (Calendar.MONTH)] + " "
            + cal.get (Calendar.YEAR));
  }

  /** Returns the compact name of the present day */
  public String presentShortDayName ()
  {
    return ((cal.get (Calendar.YEAR) - 2000)
            + MONTH_SHORT[cal.get (Calendar.MONTH)]
            + cal.get (Calendar.DAY_OF_MONTH));
  }

  /** Returns the file name code of the present day */
  public String presentDayCode ()
  {
    DecimalFormat df = new DecimalFormat ("00");
    return (cal.get (Calendar.YEAR)
            + df.format (cal.get (Calendar.MONTH) + 1)
            + df.format (cal.get (Calendar.DAY_OF_MONTH)));
  }
}

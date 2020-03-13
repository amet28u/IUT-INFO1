import java.awt.Color;


/** Schedulable ressource for a Jikan planner.
  * May be rooms, students or teachers that are associated to tasks.
  * @author Philippe Even
  */ 
public abstract class Schedulable
{
  /** Schedulable types */
  public final static int TYPE_NONE = -1;
  public final static int TYPE_STUDENT = 0;
  public final static int TYPE_TEACHER = 1;
  public final static int TYPE_ROOM = 2;
  public final static int TYPE_MODULE = 3;

  /** Schedulable directory names */
  private final static String[] types = {
                      "students", "teachers", "rooms", "modules"};
  /** Default complete name */
  private final static String DEFAULT_INFO = "Non documente";

  /** Name */
  protected String name;
  /** Complete name */
  protected String info = DEFAULT_INFO;
  /** Colour */
  private Color col = Color.WHITE;
  /** Occupied slots */
  protected Occupancy occ = null;
  /** Allocated slots (classed per weeks) */
  protected JikanList[] slots = null;


  /** Returns the count of directories */
  public static int numberOfTypes ()
  {
    return (types.length);
  }

  /** Returns the schedulable item type */
  public int type ()
  {
    return (TYPE_NONE);
  }

  /** Returns the name of the schedulable data */
  public String name ()
  {
    return (name);
  }

  /** Returns the color of the schedulable data */
  public Color color ()
  {
    return (col);
  }

  /** Returns if the schedulable item is editable (Tex and services). */
  public boolean isEditable ()
  {
    return (true);
  }

  /** Sets the schedulable object name */
  public void setName (String name)
  {
    this.name = name;
  }

  /** Sets the schedulable object complete name */
  public void setInfo (String info)
  {
    this.info = info;
  }

  /** Gets the schedulable object complete name */
  public String info ()
  {
    return (info);
  }

  /** Sets the schedulable object colour */
  public void setColour (float[] rgb)
  {
    if (rgb[0] > 1.0) rgb[0] = 1.0f;
    if (rgb[1] > 1.0) rgb[1] = 1.0f;
    if (rgb[2] > 1.0) rgb[2] = 1.0f;
    col = new Color (rgb[0], rgb[1], rgb[2]);
  }

  /** Sets the default occupancy to none */
  public void setToDefaultOccupancy (int weekCount, int dayCount)
  {
    occ = new Occupancy (weekCount, dayCount);
    slots = new JikanList[weekCount];
  }

  /** Returns the number of partitions (for students) */
  public int partitionCount ()
  {
    return (1);
  }

  /** Returns the allocated slots for a given week */
  public JikanList getSlots (int week)
  {
    return (slots[week]);
  }

  /** Tests if the schedulable item is free for the given slot */
  public boolean isFree (int week, int day, int period)
  {
    return (occ.isFree (week, day, period));
  }

  /** Tests if the schedulable item is free for the first n slots */
  public int occupancyLevelOfFirst (int week, int day, int n)
  {
    return (occ.isFreeFirst (week, day, n) ? 0 : 2);
  }

  /** Tests if the schedulable item is free for the next slots */
  public int occupancyLevelOfNext ()
  {
    return (occ.isFreeNext () ? 0 : 2);
  }

  /** Affects a new task slot */
  public void affect (Slot slot)
  {
    int wk = slot.week ();
    if (slot.isVisible ())
    {
      if (slots[wk] == null) slots[wk] = new JikanList ();
      slots[wk].add (slot);
      occ.block (wk, slot.day (), slot.period ());
    }
  }

  /** Releases a task slot from the lists */
  public void release (Slot slot)
  {
    int wk = slot.week ();
    occ.release (wk, slot.day (), slot.period ());
    if (slots[wk] != null)
    {
      slots[wk].remove (slot);
      if (slots[wk].size () == 0) slots[wk] = null;
    }
  }

  /** Returns the position into the given schedulable */
  public int[] zone (Schedulable sched)
  {
    return (null);
  }

  /** Returns if the given part belongs to the required items */
  public boolean checkZone (int part, Schedulable[] reqScheds)
  {
    return (true);
  }

  /** Returns if this schedulable item owns the given one */
  public boolean isOwnerOf (Schedulable sched)
  {
    return (this == sched);
  }

  /** Returns the number of allocated slots for the given week */
  public int getLoad (int week)
  {
    return (occ.getLoad (week));
  }
}

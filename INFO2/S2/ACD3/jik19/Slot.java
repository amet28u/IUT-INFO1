import java.awt.Color;


/** Booking for an activity in a calendar.
  * This class associates a task to a time interval in the calendar.
  * @author Philippe Even
  */
public class Slot
{
  /** Week number */
  private int week;
  /** Day number (in the week) */
  private int day;
  /** Period logical code */
  private int period;
  /** Schedulable items choice */
  private int[] choice = null;
  /** Relative order (if displayed) */
  private int order = 0;
  /** Reference to the allocated task */
  private Task task = null;


  /** Creates a new slot */
  public Slot (int week, int day, int period, Task task, int[] choice)
  {
    this.week = week;
    this.day = day;
    this.period = period;
    this.task = task;
    this.choice = choice;
  }

  /** Returns the task of the slot */
  public Task task ()
  {
    return (task);
  }

  /** Returns the week of the slot */
  public int week ()
  {
    return (week);
  }

  /** Returns the day of the slot */
  public int day ()
  {
    return (day);
  }

  /** Returns the time interval of the slot */
  public int period ()
  {
    return (period);
  }

  /** Returns one of the schedulable item choices */
  public int choice (int i)
  {
    return (choice[i]);
  }

  /** Returns all the schedulable item choices */
  public int[] choice ()
  {
    return (choice);
  }

  /** Returns the relative order of the slot */
  public int order ()
  {
    return (order);
  }

  /** Decrements the relative order of the slot */
  public void setHigher ()
  {
    order --;
  }

  /** Increments the relative order of the slot */
  public void setLower ()
  {
    order ++;
  }

  /** Enforces the relative order of the slot */
  public void reorder (int val)
  {
    order = val;
  }

  /** Inquires the administrative only visibility modality */
  public boolean isVisible ()
  {
    return (task.isVisible ());
  }

  /** Inquires the slot printability */
  public boolean isPrintable ()
  {
    return (task.module().isTexable ());
  }

  /** Returns the start index of the slot */
  public int start ()
  {
    int i = 0, per = period;
    while ((per & 1) == 0)
    {
      per = per >> 1;
      i++;
    }
    return (i); 
  }

  /** Returns the slot required schedulable items */
  public Schedulable[] requiredItems ()
  {
    Schedulable[] reqs = new Schedulable[choice.length];
    for (int i = 0; i < choice.length; i++)
      reqs[i] = task.requisite (i, choice[i]);
    return (reqs);
  }

  /** Returns the slot required schedulable items
   * of the given schedulable type */
  public JikanList requiredItems (int type)
  {
    JikanList list = new JikanList ();
    Schedulable[] reqs = requiredItems ();
    for (int i = 0; i < reqs.length; i++)
      if (reqs[i].type () == type) list.add (reqs[i]);
    return (list);
  }

  /** Returns a list indicating
   * if the displayed required items are replaceable or not */
  private JikanList areReplaceable (int type)
  {
    JikanList list = new JikanList ();
    Schedulable[][] reqs = task.requisites ();
    for (int i = 0; i < choice.length; i++)
      if (reqs[i][choice[i]].type () == type)
        list.add (reqs[i].length > 1 ? new Boolean (true) : null);
    return (list);
  }

  /** Changes the indexed required item and
   * returns if the operation succeeded */
  public boolean changeRequisite (int index)
  {
    int current = choice[index];
    int i = current + 1;
    Schedulable[] reqs = task.requisites (index);
    if (i >= reqs.length) i = 0;
    while (i != current)
    {
      if (reqs[i].isFree (week, day, period))
      {
        reqs[choice[index]].release (this);
        reqs[i].affect (this);
        choice[index] = i;
        return (true);
      }
      if (++i >= reqs.length) i = 0;
    }
    return (false);
  }

  /** Changes the indexed required item and
   * returns if the operation succeeded */
  public boolean changeRequisite (int type, int index)
  {
    // Unused! : Schedulable[] reqs = requiredItems ();
    Schedulable[][] reqs = task.requisites ();
    int num = 0;
    for (int i = 0; i < choice.length; i++)
      if (reqs[i][choice[i]].type () == type)
        if (reqs[i].length > 1)
        {
          if (num == index) return (changeRequisite (i));
          else num ++;
        }
    return (false);
  }

  /** Returns the slot positioning along the time axis */
  public int[] timeZone ()
  {
    int[] zone = new int[2];
    zone[0] = 0;
    int per = period;
    while ((per & 1) == 0) { zone[0]++; per = per >> 1; }
    zone[1] = 0;
    while ((per & 1) == 1) { zone[0]++; zone[1]++; per = per >> 1; }
    return (zone);
  }

  /** Returns the slot positioning along the student item axis
   * The returned aray features an array of axes for each occurence
   * of parts of the students */
  public int[][] studentZone (Student st)
  {
    JikanList list = new JikanList (choice.length);
    for (int i = 0; i < choice.length; i++)
    {
      int[] partZone = task.requisite(i,choice[i]).zone (st);
      if (partZone != null) list.add (partZone);
    }
    int size = list.size ();
    if (size == 0) return (null);
    int[][] zone = new int[size][];
    for (int i = 0; i < size; i++) zone[i] = (int[]) list.get (i);
    // sort
    for (int i = 1; i < size; i ++)
    {
      for (int j = 0; j < i; j++)
        if (zone[i][0] < zone[j][0])
        {
          int tmp[] = zone[i];
	  for (int k = j; k < i; k ++) zone[k+1] = zone[k];
	  zone[j] = tmp;
        }
    }
    // fusion
    int fusion = 0;
    for (int i = 1; i < size - fusion; i ++)
    {
      if (zone[i][0] <= zone[i-1][0] + zone[i-1][1])
      {
	if (zone[i-1][0] + zone[i-1][1] < zone[i][0] + zone[i][1])
          zone[i-1][1] = zone[i][0] + zone[i][1] - zone[i-1][0];
	for (int j = i; j > fusion; j--) zone[i] = zone[i-1];
        fusion++;
      }
    }
    // compaction
    if (fusion > 0)
    {
      int[][] zone2 = new int[size - fusion][];
      for (int i = 0; i < size - fusion; i++) zone2[i] = zone[i+fusion];
      zone = zone2;
    }
    return (zone);
  }

  /** Returns the slot color for the given displayed schedulable type */
  public Color color (int st)
  {
    if (st == Schedulable.TYPE_STUDENT || st == Schedulable.TYPE_MODULE)
    {
      Schedulable[] reqs = requiredItems ();
      for (int i = 0; i < reqs.length; i++)
        if (reqs[i].type () == Schedulable.TYPE_TEACHER)
          return (reqs[i].color ());
    }
    return (task.module().color ());
  }

  /** Returns the slot text for the given displayed schedulable type */
  public String[] text (int st)
  {
    return (text (st, false));
  }

  /** Returns the slot text for the given displayed schedulable type */
  public String[] text (int st, boolean selected)
  {
    JikanList poestry = new JikanList ();
    String text = null;

    switch (st)
    {
      case Schedulable.TYPE_STUDENT :
        if (task.lot().moduleNameDisplayed ())
          poestry.add (task.module().alias ());
        if (task.lot().nameDisplayed () || task.lot().orderDisplayed ())
          poestry.add (title ());
        if (task.lot().teachersDisplayed ())
        {
	  text = requiredItemNames (Schedulable.TYPE_TEACHER, selected);
	  if (! text.equals ("")) poestry.add (text);
        }
	text = requiredItemNames (Schedulable.TYPE_ROOM, selected);
	if (! text.equals ("")) poestry.add (text);
        break;
      case Schedulable.TYPE_TEACHER :
        if (task.lot().moduleNameDisplayed ())
          poestry.add (task.module().alias ());
        if (task.lot().nameDisplayed () || task.lot().orderDisplayed ())
          poestry.add (title ());
        if (task.lot().studentsDisplayed ())
        {
	  text = requiredItemNames (Schedulable.TYPE_STUDENT, selected);
	  if (! text.equals ("")) poestry.add (text);
        }
	text = requiredItemNames (Schedulable.TYPE_ROOM, selected);
	if (! text.equals ("")) poestry.add (text);
        break;
      case Schedulable.TYPE_ROOM :
        if (task.lot().moduleNameDisplayed ())
          poestry.add (task.module().alias ());
        if (task.lot().nameDisplayed () || task.lot().orderDisplayed ())
          poestry.add (title ());
        if (task.lot().teachersDisplayed ())
        {
	  text = requiredItemNames (Schedulable.TYPE_TEACHER, selected);
	  if (! text.equals ("")) poestry.add (text);
        }
        if (task.lot().studentsDisplayed ())
        {
	  text = requiredItemNames (Schedulable.TYPE_STUDENT, selected);
	  if (! text.equals ("")) poestry.add (text);
        }
        break;
      case Schedulable.TYPE_MODULE :
        if (task.lot().nameDisplayed () || task.lot().orderDisplayed ())
          poestry.add (title ());
        if (task.lot().teachersDisplayed ())
        {
	  text = requiredItemNames (Schedulable.TYPE_TEACHER, selected);
	  if (! text.equals ("")) poestry.add (text);
        }
        if (task.lot().studentsDisplayed ())
        {
	  text = requiredItemNames (Schedulable.TYPE_STUDENT, selected);
	  if (! text.equals ("")) poestry.add (text);
        }
	text = requiredItemNames (Schedulable.TYPE_ROOM, selected);
	if (! text.equals ("")) poestry.add (text);
        break;
    }

    if (poestry.size () == 0) return (null);
    String[] textLines = new String[poestry.size ()];
    poestry.fill (textLines);
    return (textLines);
  }

  /** Returns the names of the required schedulable items of the given type */
  private String requiredItemNames (int st, boolean selected)
  {
    JikanList scheds = requiredItems (st);
    JikanList replaceables = null;
    char choice = '1';
    if (st == Schedulable.TYPE_TEACHER) choice = 'A';
    else if (st == Schedulable.TYPE_ROOM) choice = 'a';
    if (selected) replaceables = areReplaceable (st);
    String text = "";
    for (int i = 0; i < scheds.size (); i++)
    {
      if (i != 0) text += "-";
      text += ((Schedulable) scheds.get (i)).name;
      if (replaceables != null)
      {
        if (replaceables.get (i) != null) text += "(" + choice + ")";
        choice = (char) (choice + 1);
      }
    }
    return (text);
  }

  /** Returns if the slot is anterior to the given one.
    * Used to sort the slots */
  public boolean anteriorTo (Slot slot)
  {
    if (week < slot.week) return (true);
    if (week > slot.week) return (false);
    if (day < slot.day) return (true);
    if (day > slot.day) return (false);
    int per = period, sper = slot.period;
    while ((per & 1) == 0)
    {
      if ((sper & 1) != 0) return (false);
      per = per >> 1;
      sper = sper >> 1;
    }
    return (true);
  }

  /** Returns the slot title */
  public String title ()
  {
    String text = "";
    if (task.lot().nameDisplayed ()) text += task.lot().alias ();
    if (task.lot().orderDisplayed ()) text += " " + order;
    return (text);
  }
}

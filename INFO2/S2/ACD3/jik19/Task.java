/** Partition of a lot.
  * Tasks within a lot are counted sequentially in time.
  * @author Philippe Even
  */
public class Task
{
  /** Owner lot */
  private Lot lot;

  /** Name */
  private String name = null;

  /** Possible costs */
  private final static String[] COST_NAMES = {"GR", "TP", "TD", "CM"};
  public final static int[] COST_VALS = {0, 4, 6, 9};
  private final static int ETD = 2;
  /** Allocated cost */
  private int cost = 0; // Gratos

  /** Duration (number of necessary slots per each fragment) */
  private int[] duration = null;
  /** Table of task requisites (AND),
   *  each defined by different possible (OR) schedulable items. */
  private Schedulable[][] requisites = null;
  /** Slots for each fragment */
  private Slot[] slots = null;


  /** Constructs a default task with only one occurence */
  public Task (Lot lot)
  {
    this.lot = lot;
    slots = new Slot[1];
    slots[0] = null;
  }

  /** Returns the task lot */
  public Lot lot ()
  {
    return (lot);
  }

  /** Returns the task module */
  public Module module ()
  {
    return (lot.module ());
  }

  /** Returns the task name */
  public String name ()
  {
    return (name);
  }

  /** Sets the task name */
  public void setName (String name)
  {
    this.name = name;
  }

  /** Inquires the administrative only visibility modality */
  public boolean isVisible ()
  {
    return (lot.isVisible ());
  }

  /** Sets the task cost */
  public boolean setCost (String name)
  {
    for (int i = 0; i < COST_NAMES.length; i++)
      if (name.equals (COST_NAMES[i]))
      {
        cost = i;
	return (true);
      }
    return (false);
  }

  /** Returns the task cost */
  public int cost ()
  {
    return (cost);
  }

  /** Returns the task cost name */
  public String costName ()
  {
    return (COST_NAMES[cost]);
  }

  /** Returns the task cost value */
  public int costVal ()
  {
    return (COST_VALS[cost]);
  }

  /** Returns the standard cost value */
  public static int costEtd ()
  {
    return (COST_VALS[ETD]);
  }

  /** Returns the number of necessary slots per each fragment */
  public int[] duration ()
  {
    return (duration);
  }

  /** Returns the global duration of the task */
  public int cumuledDuration ()
  {
    int dur = 0;
    for (int i = 0; i < duration.length; i++) dur += duration[i];
    return (dur);
  }

  /** Inquires if no duration has been set */
  public boolean hasNoDuration ()
  {
    return (duration == null);
  }

  /** Adds a task duration */
  public boolean addDuration (int slotCount)
  {
    if (slotCount <= 0) return (false);
    if (duration == null)
    {
      duration = new int[1];
      duration[0] = slotCount;
    }
    else
    {
      int repet = slots.length / duration.length;
      int[] tmp = duration;
      duration = new int[tmp.length + 1];
      for (int i = 0; i < tmp.length; i++) duration[i] = tmp[i];
      duration[tmp.length] = slotCount;
      slots = new Slot[repet * tmp.length];
      for (int i = 0; i < repet * tmp.length; i++) slots[i] = null;
    }
    return (true);
  }

  /** Sets task requirements from a schedulable items double list */
  public void setRequisites (JikanList reqList)
  {
    if (reqList != null)
    {
      requisites = new Schedulable[reqList.size ()][];
      for (int i = 0; i < requisites.length; i++)
      {
        JikanList reqs = (JikanList) (reqList.get (i));
        requisites[i] = new Schedulable[reqs.size ()];
	for (int j = 0; j < requisites[i].length; j++)
          requisites[i][j] = (Schedulable) (reqs.get (j));
      }
    }
  }

  /** Returns the table of task requisites */
  public Schedulable[][] requisites ()
  {
    return (requisites);
  }

  /** Returns the task requisites for one kind of ressource */
  public Schedulable[] requisites (int i)
  {
    return (requisites[i]);
  }

  /** Returns one of the task requisites */
  public Schedulable requisite (int i, int j)
  {
    return (requisites[i][j]);
  }

  /** Changes the task number of occurences */
  public void repeat (int repetition)
  {
    slots = new Slot[repetition * duration.length];
    for (int i = 0; i < repetition * duration.length; i++) slots[i] = null;
  }

  /** Returns if the given schedulable item is implied in the task */
  public boolean imply (Schedulable sched)
  {
    for (int i = 0; i < requisites.length; i++)
      for (int j = 0; j < requisites[i].length; j++)
        if (sched.isOwnerOf (requisites[i][j])) return (true);
    return (false);
  }

  /** Returns if at least one slot is not set */
  public void unallocated (JikanList freeTasks, JikanList counts)
  {
    int count = 0;
    for (int i = 0; i < slots.length; i++) if (slots[i] == null) count ++;
    if (count != 0)
    {
      freeTasks.add (this);
      counts.add (new Integer (count));
    }
  }

  /** Returns the duration of the first unallocated slot */
  public int nextDuration ()
  {
    int i = 0;
    while (i < slots.length) if (slots[i++] == null) break;
    return (duration[--i % duration.length]);
  }

  /** Returns the first free slot index */
  public int firstFreeSlot ()
  {
    for (int i = 0; i < slots.length; i++)
      if (slots[i] == null) return (i);
    return (-1);
  }

  /** Sets a slot */
  public void setSlot (int num, int week, int day, int start, int[] choice)
  {
    // Determines the tested period
    int dur = duration[num % duration.length];
    int period = 1;
    while (--dur != 0) period = (period << 1) + 1;
    period = period << start;

    // Creates a new slot and assigns it to the relevant items
    slots[num] = new Slot (week, day, period, this, choice);
    affectRequisites (num);
    lot.insert (slots[num]);
  }

  /** Returns the task slots */
  public Slot[] slots ()
  {
    return (slots);
  }

  /** Returns one of the task slots */
  public Slot slot (int i)
  {
    return (slots[i]);
  }

  /** Affects the requirements with the given slot */
  public void affectRequisites (int index)
  {
    for (int i = 0; i < requisites.length; i ++)
      requisites[i][slots[index].choice(i)].affect (slots[index]);
    lot.module().affect (slots[index]);
  }

  /** Releases the requirements from the given slot */
  public void releaseRequisites (int index)
  {
    for (int i = 0; i < requisites.length; i ++)
      requisites[i][slots[index].choice(i)].release (slots[index]);
    lot.module().release (slots[index]);
  }

  /** Removes a slot */
  public void remove (int index)
  {
    releaseRequisites (index);
    lot.remove (slots[index]);
    slots[index] = null;
  }

  /** Returns the given slot index */
  public int indexOf (Slot slot)
  {
    for (int index = 0; index < slots.length; index ++)
      if (slot.equals (slots[index])) return (index);
    return (-1);
  }

  /** Returns the required item index for the given index
   * and the given schedulable item name */
  public int identifyRequisite (int index, String reqName)
  {
    for (int i = 0; i < requisites[index].length; i++)
      if (reqName.equals (requisites[index][i].name)) return (i);
    return (-1);
  }

  /** Tries to position the task to a given slot
   * Return the list of unavailable schedulable items */
  public JikanList trySlot (int num, int week, int day, int start)
  {
    // Looks for the first unallocated occurence
    if (num == -1)
    {
      num = 0;
      while (num < slots.length && slots[num] != null) num++;
      if (num == slots.length) return (null);
    }
    // Allows the new and old slots covering
    else releaseRequisites (num);

    // Determines the tested period
    int dur = duration[num % duration.length];
    int period = 1;
    while (--dur != 0) period = (period << 1) + 1;
    period = period << start;

    // Selects first available schedulable items
    //   or updates the list of unavailable items
    JikanList unavailabilities = new JikanList ();
    int[] choice = new int[requisites.length];
    for (int i = 0; i < requisites.length; i++)
    {
      choice[i] = -1;
      int oldi = (slots[num] == null ? 0 : slots[num].choice (i));
      int testi = oldi;
      do
      {
        if (requisites[i][testi].isFree (week, day, period)) choice[i] = testi;
        testi = (testi + 1) % requisites[i].length;
      }
      while (choice[i] == -1
             && testi != (oldi + requisites[i].length) % requisites[i].length);
      if (choice[i] == -1)
        for (int j = 0; j < requisites[i].length; j++)
          unavailabilities.add (requisites[i][j]);
    }

    // Creates a new slot and assigns it to the relevant items
    if (unavailabilities.size () == 0)
    {
      // Creates a new slot and assigns it to the relevant items
      if (slots[num] != null) lot.remove (slots[num]);
      slots[num] = new Slot (week, day, period, this, choice);
      affectRequisites (num);
      lot.insert (slots[num]);
      return (null);
    }
    // Restores the old slot required schedulables occupancy
    else if (slots[num] != null) affectRequisites (num);

    return (unavailabilities);
  }

  /** Tests if the schedulable items required are free for the first n slots */
  public int occupancyLevelOfFirst (int week, int day, int n)
  {
    int level = 0;
    for (int i = 0; i < requisites.length; i++)
    {
      int sublevel = 2;
      for (int j = 0; j < requisites[i].length; j++)
      {
        int subsublevel = requisites[i][j].occupancyLevelOfFirst (week, day, n);
	if (subsublevel < sublevel) sublevel = subsublevel;
      }
      if (sublevel > level) level = sublevel;
    }
    return (level);
  }

  /** Tests if the schedulable items required are free for the next slots */
  public int occupancyLevelOfNext ()
  {
    int level = 0;
    for (int i = 0; i < requisites.length; i++)
    {
      int sublevel = 2;
      for (int j = 0; j < requisites[i].length; j++)
      {
        int subsublevel = requisites[i][j].occupancyLevelOfNext ();
	if (subsublevel < sublevel) sublevel = subsublevel;
      }
      if (sublevel > level) level = sublevel;
    }
    return (level);
  }

  /** Converts a task into string */
  public String toString ()
  {
    String st = new String ("    Tache " + name
        + " x" + slots.length + " (" + COST_NAMES[cost] + ")");
    for (int i = 0; i < duration.length; i++)
      st += (i == 0 ? " : " : " + ") + duration[i];
    if (requisites != null)
      for (int i = 0; i < requisites.length; i++)
      {
        st += " <" + requisites[i][0].name;
	for (int j = 1; j < requisites[i].length; j++)
          st += ", " + requisites[i][j].name;
	st += ">";
      }
    return (st);
  }

  /** Gets information about the task */
  public JikanList getInformation ()
  {
    JikanList info = new JikanList ();
    info.add ("Module " + lot.module().name ());
    info.add ("Lot " + lot.name ());
    info.add ("Tache " + name);

    String text = "Duree (";
    for (int i = 0; i < duration.length; i++)
    {
      if (i != 0) text += " +";
      text += duration[i];
    }
    text += ") " + costName ();
    if (slots.length > 1) text += " x" + slots.length;
    info.add (text);

    for (int i = 0; i != requisites.length; i++)
    {
      text = "";
      for (int j = 0; j != requisites[i].length; j++)
      {
        if (j != 0) text += " | ";
        text += requisites[i][j].name ();
      }
      info.add (text);
    }
    return (info);
  }
}

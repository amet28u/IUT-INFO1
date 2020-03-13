/** Partition of a student promo for Jikan planner.
  * @author Philippe Even
  */
public class StudentGroup extends Student
{
  /** Reference to the entire students group */
  private StudentPromo promo = null;
  /** Partitions occupied */
  private int[] parts = null;


  /** Returns the occupied partitions. */
  public int[] occupiedParts ()
  {
    return parts;
  }

  /** Sets the occupied partitions. */
  public void setOccupied (int[] parts)
  {
    this.parts = parts;
  }

  /** Returns the number of partitions
   * (inherited from Schedulable) */
  public int partitionCount ()
  {
    return (promo.partOccupancy().length);
  }

  /** Sets the entire group */
  public void setPromo (StudentPromo st)
  {
    promo = st;
  }

  /** Tests if the schedulable item is free for the given slot
   * (inherited from Schedulable) */
  public boolean isFree (int week, int day, int period)
  {
    if (! promo.forbiddenSlots().isFree (week, day, period)) return (false);
    Occupancy[] partition = promo.partOccupancy ();
    for (int i = 0; i < parts.length; i++)
      if (! partition[parts[i]].isFree (week, day, period))
        return (false);
    return (true);
  }

  /** Tests if the schedulable item is free for the first n slots
   * (inherited from Schedulable) */
  public int occupancyLevelOfFirst (int week, int day, int n)
  {
    int level = 0;
    Occupancy[] partition = promo.partOccupancy ();
    if (! promo.forbiddenSlots().isFreeFirst (week, day, n)) level = 2; 
    for (int i = 0; i < parts.length; i++)
      if (! partition[parts[i]].isFreeFirst (week, day, n))
        level = 2;
    return (level);
  }

  /** Tests if the schedulable item is free for the next slots
   * (inherited from Schedulable) */
  public int occupancyLevelOfNext ()
  {
    int level = 0;
    Occupancy[] partition = promo.partOccupancy ();
    if (! promo.forbiddenSlots().isFreeNext ()) level = 2; 
    for (int i = 0; i < parts.length; i++)
      if (! partition[parts[i]].isFreeNext ()) level = 2;
    return (level);
  }

  /** Affects a new task
   * (inherited from Schedulable) */
  public void affect (Slot slot)
  {
    Occupancy[] partition = promo.partOccupancy ();
    int wk = slot.week ();
    for (int i = 0; i < parts.length; i++)
      partition[parts[i]].block (wk, slot.day (), slot.period ());
    if (promo.slots[wk] == null) promo.slots[wk] = new JikanList ();
    promo.slots[wk].add (slot);
  }

  /** Releases the given slot
   * (inherited from Schedulable) */
  public void release (Slot slot)
  {
    Occupancy[] partition = promo.partOccupancy ();
    int wk = slot.week ();
    for (int i = 0; i < parts.length; i++)
      partition[parts[i]].release (wk, slot.day (), slot.period ());
    promo.slots[wk].remove (slot);
    if (promo.slots[wk].size () == 0) promo.slots[wk] = null;
  }

  /** Returns the position into the given schedulable
    * Inherited form Schedulable */
  public int[] zone (Schedulable sched)
  {
    if (sched != promo) return (null);
    return (new int[] {parts[0], parts[parts.length-1] - parts[0] + 1});
  }

  /** Returns the number of allocated slots for the given week */
  public int getLoad (int week)
  {
    return (0);
  }
}

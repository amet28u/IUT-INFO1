/** Student complete promo for Jikan planner.
  * @author Philippe Even
  */
public class StudentPromo extends Student
{
  /** Strict constraints (reddish) */
  private Occupancy forbid = null;
  /** Part occupations */
  private Occupancy[] partition = null;
  /** Students groups */
  private StudentGroup[][] groups = null;


  /** Returns the occupancy of the student partitions. */
  public Occupancy[] partOccupancy ()
  {
    return (partition);
  }

  /** Returns the student partitions strict constraints. */
  public Occupancy forbiddenSlots ()
  {
    return (forbid);
  }

  /** Sets the forbidden slots according to given occupancy */
  public void setForbiddenSlots (Occupancy occ)
  {
    forbid = new Occupancy (occ);
  }

  /** Returns the number of partitions
   * (inherited from Schedulable) */
  public int partitionCount ()
  {
    return (partition.length);
  }

  /** Creates the group partition */
  public void partition (int nb)
  {
    partition = new Occupancy[nb];
    for (int i = 0; i < nb; i++) partition[i] = new Occupancy (occ);
  }

  /** Sets the large groups */
  public void addGroups (StudentGroup[] group)
  {
    StudentGroup[][] tmpGroup = ((groups == null ) ?
                                 new StudentGroup[1][] :
                                 new StudentGroup[groups.length + 1][]);
    for (int i = 0; i < tmpGroup.length - 1; i++) tmpGroup[i] = groups[i];
    tmpGroup[tmpGroup.length - 1] = group;
    groups = tmpGroup;
    int div = partition.length / group.length;
    int rest = partition.length % group.length;
    int repart = 0;
    for (int i = 0; i < group.length; i++)
    {
      group[i].setPromo (this);
      int nbParts = div;
      if (i < rest) nbParts++;
      int[] parts = new int[nbParts];
      for (int j = 0; j < nbParts; j++) parts[j] = repart++;
      group[i].setOccupied (parts);
    }
  }

  /** Returns the sub-group with the given name */
  public Student find (String name)
  {
    if (groups == null) return (null);
    for (int i = 0; i < groups.length; i++)
      for (int j = 0; j < groups[i].length; j++)
        if (name.equals (groups[i][j].name)) return (groups[i][j]);
    return (null);
  }

  /** Tests if the schedulable item is free for the given slot
   * (inherited from Schedulable) */
  public boolean isFree (int week, int day, int period)
  {
    // Partitioned group
    if (partition != null)
    {
      if (! forbid.isFree (week, day, period)) return (false);
      for (int i = 0; i < partition.length; i++)
        if (! partition[i].isFree (week, day, period)) return (false);
      return (true);
    }
    // Non partitioned group
    return (forbid.isFree (week, day, period)
            && occ.isFree (week, day, period));
  }

  /** Tests if the schedulable item is free for the first n slots
   * (inherited from Schedulable) */
  public int occupancyLevelOfFirst (int week, int day, int n)
  {
    if (partition == null) return (super.occupancyLevelOfFirst (week, day, n));
    int level = 0;
    if (! forbid.isFreeFirst (week, day, n)) level = 2; 
    for (int i = 0; i < partition.length; i++)
      if (! partition[i].isFreeFirst (week, day, n)) level = 2;
    return (level);
  }

  /** Tests if the schedulable item is free for the next slots
   * (inherited from Schedulable) */
  public int occupancyLevelOfNext ()
  {
    if (partition == null) return (super.occupancyLevelOfNext ());
    int level = 0;
    if (! forbid.isFreeNext ()) level = 2; 
    for (int i = 0; i < partition.length; i++)
      if (! partition[i].isFreeNext ()) level = 2;
    return (level);
  }

  /** Affects a new task
   * (inherited from Schedulable) */
  public void affect (Slot slot)
  {
    // Partitioned group
    int wk = slot.week ();
    if (partition != null)
    {
      for (int i = 0; i < partition.length; i++)
        partition[i].block (wk, slot.day (), slot.period ());
      if (slots[wk] == null) slots[wk] = new JikanList ();
      slots[wk].add (slot);
    }
    // Non partitioned group
    else super.affect (slot);
  }

  /** Releases the given slot
   * (inherited from Schedulable) */
  public void release (Slot slot)
  {
    // Partitioned group
    if (partition != null)
    {
      int wk = slot.week ();
      for (int i = 0; i < partition.length; i++)
        partition[i].release (wk, slot.day (), slot.period ());
      slots[wk].remove (slot);
      if (slots[wk].size () == 0) slots[wk] = null;
    }
    // Non partitioned group
    else super.release (slot);
  }

  /** Returns the position into the given schedulable
    * Inherited form Schedulable */
  public int[] zone (Schedulable sched)
  {
    return (sched == this ? new int[] {0, partition.length} : null);
  }

  /** Returns if the given part belongs to the required items.
    * Inherited from Schedulable */
  public boolean checkZone (int part, Schedulable[] reqScheds)
  {
    for (int i = 0; i < reqScheds.length; i++)
    {
      if (reqScheds[i] == this) return (true);
      if (isOwnerOf (reqScheds[i]))
      {
        int[] parts = ((StudentGroup) reqScheds[i]).occupiedParts ();
        for (int j = 0; j < parts.length; j++)
          if (part == parts[j]) return (true);
      }
    }
    return (false);
  }

  /** Returns if the schedulable item owns the given one
   *  (inherited from Schedulable) */
  public boolean isOwnerOf (Schedulable sched)
  {
    if (this == sched) return (true);
    if (groups != null)
      for (int i = 0; i < groups.length; i++)
        for (int j = 0; j < groups[i].length; j++)
          if (groups[i][j] == sched) return (true);
    return (false);
  }

  /** Returns the number of allocated slots for the given week */
  public int getLoad (int week)
  {
    return (partition[0].getLoad (week));
  }
}

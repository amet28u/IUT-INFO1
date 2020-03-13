/** Teacher for Jikan planner.
  * @author Philippe Even
  */
public class Teacher extends Schedulable
{
  /** Strict constraints (reddish) */
  private Occupancy forbid = null;
  /** Troubling slots (yellowish) */
  private Occupancy avoid = null;


  /** Returns the schedulable item type
   * (inherited from Schedulable) */
  public int type ()
  {
    return (TYPE_TEACHER);
  }

  /** Returns the teacher strict constraints */
  public Occupancy forbiddenSlots ()
  {
    return (forbid);
  }

  /** Returns the teacher undesireable constraints */
  public Occupancy avoidableSlots ()
  {
    return (avoid);
  }

  /** Sets the default occupancy to none
   * (inherited from Schedulable) */
  public void setToDefaultOccupancy (int weekCount, int dayCount)
  {
    super.setToDefaultOccupancy (weekCount, dayCount);

    // Predefines constraints
    forbid = new Occupancy (weekCount, dayCount);
    avoid = new Occupancy (weekCount, dayCount);
  }

  /** Tests if the schedulable item is free for the given slot
   * (inherited from Schedulable) */
  public boolean isFree (int week, int day, int period)
  {
    return (forbid.isFree (week, day, period)
            && super.isFree (week, day, period));
  }

  /** Tests if the schedulable item is free for the first n slots
   * (inherited from Schedulable) */
  public int occupancyLevelOfFirst (int week, int day, int n)
  {
    int level = ((avoid.isFreeFirst (week, day, n)) ? 0 : 1);
    if (! (forbid.isFreeFirst (week, day, n))) level = 2;
    return (occ.isFreeFirst (week, day, n) ? level : 2);
  }

  /** Tests if the schedulable item is free for the next slots
   * (inherited from Schedulable) */
  public int occupancyLevelOfNext ()
  {
    int level = ((avoid.isFreeNext ()) ? 0 : 1);
    if (! (forbid.isFreeNext ())) level = 2;
    return (occ.isFreeNext () ? level : 2);
  }
}

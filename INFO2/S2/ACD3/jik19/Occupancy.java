/** Occupancy grid for Jikan planner.
  * Defines free, forbidden or avoidable periods in days,
  *   weeks or year for schedulable ressources.
  * @author Philippe Even
  */
public class Occupancy
{
  /** The grid : intervals for each day of each week */
  private int[][] grid;

  /** Currently tested week */
  private int testWeek = 0;
  /** Currently tested day */
  private int testDay = 0;
  /** Currently tested interval (bitwise encoded) */
  private int testInterval = 1;


  /** Creates an occupancy grid, and sets each day to fully available */
  public Occupancy (int weekCount, int dayCount)
  {
    grid = new int[weekCount][dayCount];
    for (int i = 0; i < weekCount; i++)
      for (int j = 0; j < dayCount; j++)
        grid[i][j] = 0;
  }

  /** Creates an occupancy grid similar to the given one */
  public Occupancy (Occupancy occ)
  {
    grid = new int[occ.grid.length][occ.grid[0].length];
    for (int i = 0; i < grid.length; i++)
      for (int j = 0; j < grid[i].length; j++)
        grid[i][j] = occ.grid[i][j];
  }

  /** Sets a week to unavailable */
  public void withdrawWeek (int week)
  {
    for (int i = 0; i < grid[week].length; i++) grid[week][i] = -1;
  }

  /** Sets a day to unavailable */
  public void withdrawDay (int week, int day)
  {
    grid[week][day] = -1;
  }

  /** Sets an interval to unavailable */
  public void forbid (int week, int day, String intervalCode)
  { 
    int interval = 0;
    if (intervalCode.charAt (0) == 'a') interval = -1;
    else
    {
      int val = 1;
      for (int i = 0; i < intervalCode.length (); i++)
      {
        if (intervalCode.charAt (i) == 'x') interval += val;
        val <<= 1;
      }
    }
    grid[week][day] = interval;
  }

  /** Sets an interval to available */
  public void setFree (int week, int day, String intervalCode)
  { 
    int interval = 0;
    if (intervalCode.charAt (0) == 'a') interval = -1;
    else
    {
      int val = 1;
      for (int i = 0; i < intervalCode.length (); i++)
      {
        if (intervalCode.charAt (i) == 'x') interval += val;
        val <<= 1;
      }
    }
    grid[week][day] &= ~interval;
  }

  /** Sets a day interval of each week to unavailable */
  public void forbidEachWeek (int day, String intervalCode)
  {
    int interval = 0;
    if (intervalCode.charAt (0) == 'a') interval = -1;
    else
    {
      int val = 1;
      for (int i = 0; i < intervalCode.length (); i++)
      {
        if (intervalCode.charAt (i) == 'x') interval += val;
        val <<= 1;
      }
    }
    for (int i = 0; i < grid.length; i++) grid[i][day] = interval;
  }

  /** Tests if the given slot is free */
  public boolean isFree (int week, int day, int period)
  {
    return ((period & grid[week][day]) == 0);
  }

  /** Sets the given slot to occupied */
  public void block (int week, int day, int period)
  {
    grid[week][day] |= period;
  }

  /** Sets the given slot to free */
  public void release (int week, int day, int period)
  {
    grid[week][day] &= ~period;
  }

  /** Sets the tested interval to the first slot
   * and returns wether it is free or not */
  public boolean isFreeFirst (int week, int day)
  {
    testWeek = week;
    testDay = day;
    testInterval = 1;
    return (! ((testInterval & grid[testWeek][testDay]) != 0));
  }

  /** Sets the tested interval to the first n slots
   * and returns wether it is free or not */
  public boolean isFreeFirst (int week, int day, int n)
  {
    testWeek = week;
    testDay = day;
    testInterval = 1;
    while (--n != 0) testInterval = 1 + (testInterval << 1);
    return (! ((testInterval & grid[testWeek][testDay]) != 0));
  }

  /** Increments the tested interval
   * and returns wether it is free or not */
  public boolean isFreeNext ()
  {
    testInterval = (testInterval << 1);
    return (! ((testInterval & grid[testWeek][testDay]) != 0));
  }

  /** Debug */
  public void display (int week)
  {
    for (int i = 0; i < grid[week].length; i++)
    {
      String aff = (isFreeFirst (week, i) ? "o" : "x");
      for (int j = 0; j < 21; j++)
        aff += (isFreeNext () ? "o" : "x");
      System.out.println (aff);
    }
  }

  /** Returns the number of allocated slots for the given week */
  public int getLoad (int week)
  {
    int count = 0;
    for (int i = 0; i < grid[week].length; i ++)
    {
      int code = grid[week][i];
      while (code != 0)
      {
        if ((code & 1) != 0) count ++;
	code = code >> 1;
      }
    }
    return (count);
  }
}

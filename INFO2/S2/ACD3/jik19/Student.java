/** Student for Jikan planner.
  * @author Philippe Even
  */
public class Student extends Schedulable
{
  /** Returns the schedulable item type
   * (inherited from Schedulable) */
  public int type ()
  {
    return (TYPE_STUDENT);
  }

  /** Returns the sub-group with the given name */
  public Student find (String name)
  {
    return (null);
  }
}

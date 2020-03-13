/** Activity module that corresponds to courses or other occupations.
  * Modules are split into lots, themselves into groups.
  * @author Philippe Even
  */
public class Module extends Schedulable
{
  /** Series of individual tasks */
  private Lot[] lots = null;
  /** Name for display */
  private String alias = "";
  /** Name display modality */
  private boolean nameDisplayed = true;
  /** Teachers display modality */
  private boolean teachersDisplayed = true;
  /** Students display modality */
  private boolean studentsDisplayed = true;
  /** Module printability */
  private boolean texable = true;
  /** Simple user display modality */
  private boolean visible = true;


  /** Returns the schedulable item type
   * (inherited from Schedulable) */
  public int type ()
  {
    return (TYPE_MODULE);
  }

  /** Sets the schedulable object name
   * Inherited from Schedulable */
  public void setName (String name)
  {
    this.name = name;
    alias = name;
  }

  /** Makes the module unvisible for simple users */
  public void setHidden ()
  {
    visible = false;
  }

  /** Inquires the administrative only visibility modality */
  public boolean isVisible ()
  {
    return (visible);
  }

  /** Sets the name to be displayed */
  public void setAlias (String name)
  {
    alias = name;
  }

  /** Returns the module alias name */
  public String alias ()
  {
    return (alias);
  }

  /** Sets the module name display modality */
  public void displayName (boolean modality)
  {
    nameDisplayed = modality;
  }

  /** Returns if the name is displayed for this module */
  public boolean nameDisplayed ()
  {
    return (nameDisplayed);
  }

  /** Sets the module teachers display modality */
  public void displayTeachers (boolean modality)
  {
    teachersDisplayed = modality;
  }

  /** Returns if teachers are displayed for this module */
  public boolean teacherDisplayed ()
  {
    return (teachersDisplayed);
  }

  /** Sets the module students display modality */
  public void displayStudents (boolean modality)
  {
    studentsDisplayed = modality;
  }

  /** Returns if students are displayed for this module */
  public boolean studentDisplayed ()
  {
    return (studentsDisplayed);
  }

  /** Sets the module printability */
  public void texable (boolean modality)
  {
    texable = modality;
  }

  /** Inquires the module printability */
  public boolean isTexable ()
  {
    return (texable);
  }

  /** Return the lot from the given name */
  public Lot getLot (String name)
  {
    for (int i = 0; i < lots.length; i++)
      if (name.equals (lots[i].name ())) return (lots[i]);
    return (null);
  }

  /** Sets the module lots from a list of lots */
  public void setLots (JikanList list)
  {
    lots = new Lot[list.size ()];
    list.fill (lots);
  }

  /** Returns the array of lots of the module */
  public Lot[] lots ()
  {
    return (lots);
  }

  /** Fills the list of unallocated tasks and their count */
  public void unallocated (JikanList tasks, JikanList counts)
  {
    tasks.empty ();
    counts.empty ();
    for (int i = 0; i < lots.length; i++)
      lots[i].fillWithUnallocatedTasks (tasks, counts);
  }

  /** Converts a module into string */
  public String toString ()
  {
    String st = new String ("Module " + name + "\n");
    if (lots != null)
      for (int i = 0; i < lots.length; i++) st += lots[i];
    return (st);
  }
}

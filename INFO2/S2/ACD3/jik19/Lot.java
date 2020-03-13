/** Countable part of a module.
  * Lots may be parted into tasks.
  * Each occurance is counted sequentially in time.
  * @author Philippe Even
  */
public class Lot
{
  /** Owner module */
  private Module module;
  /** Identification name */
  private String name = "";
  /** Displayed name */
  private String alias = "";
  /** Lot name dislayed or not */
  private boolean nameDisplayed = false;
  /** Order dislayed or not */
  private boolean orderDisplayed = false;
  /** Module name dislayed or not */
  private boolean moduleNameDisplayed = true;
  /** Module teachers dislayed or not */
  private boolean teachersDisplayed = true;
  /** Module students dislayed or not */
  private boolean studentsDisplayed = true;
  /** Tasks */
  private Task[] tasks = null;


  /** Creates a new lot owend by the given module */
  public Lot (Module module, boolean moduleNameDisplayed,
                             boolean teachersDisplayed,
                             boolean studentsDisplayed)
  {
    this.moduleNameDisplayed = moduleNameDisplayed;
    this.teachersDisplayed = teachersDisplayed;
    this.studentsDisplayed = studentsDisplayed;
    this.module = module;
  }

  /** Returns the lot module */
  public Module module ()
  {
    return (module);
  }

  /** Sets the lot name */
  public void setName (String name)
  {
    this.name = name;
    if (alias.equals ("")) alias = name;
  }

  /** returns the lot identification name */
  public String name ()
  {
    return (name);
  }

  /** Inquires the administrative only visibility modality */
  public boolean isVisible ()
  {
    return (module.isVisible ());
  }

  /** Sets the alias name */
  public void setAlias (String name)
  {
    this.alias = name;
  }

  /** returns the lot visible name */
  public String alias ()
  {
    return (alias);
  }

  /** Sets the lot name display modality */
  public void displayName (boolean modality)
  {
    nameDisplayed = modality;
  }

  /** Returns if the lot name is displayed */
  public boolean nameDisplayed ()
  {
    return (nameDisplayed);
  }

  /** Sets the module name display modality */
  public void moduleDisplayName (boolean modality)
  {
    moduleNameDisplayed = modality;
  }

  /** Returns if the module name is displayed for this lot */
  public boolean moduleNameDisplayed ()
  {
    return (moduleNameDisplayed);
  }

  /** Returns if the teachers are displayed for this lot */
  public boolean teachersDisplayed ()
  {
    return (teachersDisplayed);
  }

  /** Returns if the students are displayed for this lot */
  public boolean studentsDisplayed ()
  {
    return (studentsDisplayed);
  }

  /** Sets the lot counter display modality */
  public void displayOrder (boolean modality)
  {
    orderDisplayed = modality;
  }

  /** Returns if tasks are numbered for this lot */
  public boolean orderDisplayed ()
  {
    return (orderDisplayed);
  }

  /** Return the task from the given name */
  public Task getTask (String name)
  {
    for (int i = 0; i < tasks.length; i++)
      if (name.equals (tasks[i].name ())) return (tasks[i]);
    return (null);
  }

  /** Sets the tasks of the lot */
  public void setTasks (JikanList taskList)
  {
    tasks = new Task[taskList.size ()];
    taskList.fill (tasks);
  }

  /** Returns the tasks of the lot */
  public Task[] tasks ()
  {
    return (tasks);
  }

  /** Fills the given list with the unallocated tasks */
  public void fillWithUnallocatedTasks (JikanList freeTasks, JikanList counts)
  {
    for (int i = 0; i < tasks.length; i++)
      tasks[i].unallocated (freeTasks, counts);
  }

  /** Converts a lot into string */
  public String toString ()
  {
    String st = new String ("  Lot " + name
                            + (orderDisplayed ? " (ordered)\n" : "\n"));
    for (int i = 0; i < tasks.length; i++) st += tasks[i] + "\n";
    return (st);
  }

  /** Affects the relevant order to the inserted slot */
  public void insert (Slot slot)
  {
    if (orderDisplayed)
    {
      slot.reorder (1);
      for (int i = 0; i < tasks.length; i ++)
      {
        Slot[] slots = tasks[i].slots ();
        for (int j = 0; j < slots.length; j ++)
          if (slots[j] != null && slots[j] != slot)
            if (slot.order () <= slots[j].order ())
              if (slots[j].anteriorTo (slot))
                slot.reorder (slots[j].order () + 1);
              else slots[j].setLower ();
      }
    }
  }

  /** Updates the orders of the remaining slots */
  public void remove (Slot slot)
  {
    if (orderDisplayed)
    {
      for (int i = 0; i < tasks.length; i ++)
      {
        Slot[] slots = tasks[i].slots ();
        for (int j = 0; j < slots.length; j ++)
          if (slots[j] != null && slots[j] != slot)
            if (slot.order () < slots[j].order ()) slots[j].setHigher ();
      }
    }
  }
}

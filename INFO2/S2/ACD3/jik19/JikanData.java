/** Data base for Jikan planner.
  * It features calendars, ressources, slots, ...
  * @author Philippe Even
  */
public class JikanData
{
  /** Displayed calendar */
  private JikanCalendar cal = null;
  /** Current week */
  private int week = 0;

  /** Schedulable objects
   * - first dimension : individual
   * - second dimension : group
   * - third dimension : type (students, teachers, rooms, modules) */
  private Schedulable[][][] sched
    = new Schedulable[Schedulable.numberOfTypes ()][][];
  /** Schedulable groups names */
  private String[][] groupNames = new String[Schedulable.numberOfTypes ()][];

  /** Displayed module group */
  private int displayedModuleGroup = -1;       // no module group displayed
  /** Selected module */
  private int selectedModule = -1;       // no module selected
  /** Selected task */
  private Task selectedTask = null;       // no task selected
  /** Selected slot */
  private int selectedSlot = -1;       // no slot selected
  /** Unallocated tasks of the selected module */
  private JikanList unallocatedTasks = new JikanList ();
  /** Remaining quantity for each unallocated task */
  private JikanList unallocatedCount = new JikanList ();
  /** Selectable tasks array associated to the unallocated tasks */
  private boolean[] selectableTasks = null;
  /** List of unavailable schedulable items to be displayed */
  private JikanList unavailabilities = null;
  /** Information for display */
  private JikanList infoToBeDisplayed = null;

  /** Selected schedulable item type */
  private int selectedType = -1;       // no type
  /** Selected schedulable item group */
  private int selectedGroup = -1;       // no type
  /** Current schedulable object group index */
  private int currentGroupId = 0;
  /** Current schedulable object index */
  private int currentSchedulableId = 0;
  /** Displayed schedulable object (default is the calendar) */
  private Schedulable currentSchedulable = null;
  /** Indicates if the data base is modified */
  private boolean modified = false;



  /** Creates a empty data base */
  public void setCalendar (int year, int firstWeek,
                           int yearLength, int weekLength)
  {
    // Calendar creation
    cal = new JikanCalendar (year, firstWeek, yearLength, weekLength);
    week = cal.activeWeek ();
  }

  /** Returns the selected schedulable resource */
  public Schedulable current ()
  {
    return (currentSchedulable);
  }

  /** Returns the active calendar */
  public JikanCalendar calendar ()
  {
    return (cal);
  }

  /** Returns the active week */
  public int week ()
  {
    return (week);
  }

  /** Sets the displayed week
   */
  public boolean exploreWeek (int newWeek)
  {
    if (newWeek >= 0 && newWeek < cal.weekDays().length)
    {
      week = newWeek;
      return (true);
    }
    return (false);
  }

  /** Increments the current week of the given value */
  public void incrementWeek (int val)
  {
    week += val;
    if (week >= cal.weekDays().length) week = cal.weekDays().length - 1;
    else if (week < 0) week = 0;
  }


  /** Returns the module groups names */
  public String[] getModuleGroupsNames ()
  {
    return (groupNames[Schedulable.TYPE_MODULE]);
  }


  /** Retrieves a group index from a name */
  public int getGroup (int type, String name)
  {
    for (int i = 0; i < sched[type].length; i++)
      if (name.equals (groupNames[type][i])) return (i);
    return (-1);
  }

  /** Returns the schedulable items of given type and given group */
  public Schedulable[] getSchedulables (int type, int group)
  {
    return (sched[type][group]);
  }

  /** Fills the data base with the given schedulable types */
  public void setSchedules (int type, JikanList names, JikanList groups)
  {
    // Sets the names
    groupNames[type] = new String[names.size ()];
    names.fill (groupNames[type]);

    // Sets the schedulable objects
    sched[type] = new Schedulable[groups.size ()][];
    for (int i = 0; i < sched[type].length; i++)
    {
      JikanList list = (JikanList) (groups.get (i));
      sched[type][i] = new Schedulable[list.size ()];
      list.fill (sched[type][i]);
    }
  }

  /** Returns the names of the groups of the given type
   */
  public String[] groupNames (int type)
  {
    return (groupNames[type]);
  }

  /** Activates a schedulable type
   */
  public boolean exploreType (int num, boolean isManaging)
  {
    for (int i = 0; i < Schedulable.numberOfTypes (); i++)
    {
      if (num < 0) return (false);
      if (num < sched[i].length)
      {
        if ((i <= Schedulable.TYPE_ROOM) || (! isManaging)
            || (selectedType == Schedulable.TYPE_NONE)
            || (selectedType == Schedulable.TYPE_MODULE))
        {
          selectedType = i;
          selectedGroup = num;
        }
        else
	{
          if (num != displayedModuleGroup)
          {
            displayedModuleGroup = num;
            selectedModule = -1;
            selectedTask = null;
            selectedSlot = -1;
            unallocatedTasks.empty ();
            unallocatedCount.empty ();
            selectableTasks = null;
          }
	}
        return (true);
      }
      num -= sched[i].length + 1;
    }
    return (false);
  }

  /** Returns the schedulable items of the selected group
   */
  public Schedulable[] schedulableItems ()
  {
    if (selectedType != -1 && selectedGroup != -1)
      return (sched[selectedType][selectedGroup]);
    else return (null);
  }

  /** Activates a schedulable item
   */
  public boolean exploreSchedulableItem (int num)
  {
    if (selectedType != -1 && selectedGroup != -1)
      if (num >= 0 && num < sched[selectedType][selectedGroup].length)
      {
        selectedTask = null;
	selectedSlot = -1;
        currentGroupId = selectedGroup;
        currentSchedulableId = num;
        currentSchedulable
          = sched[selectedType][currentGroupId][currentSchedulableId];
	if (selectableTasks != null)
	{
	  for (int i = 0; i < selectableTasks.length; i++)
            selectableTasks[i] = (currentSchedulable != null ?
              ((Task) (unallocatedTasks.get (i))).imply (currentSchedulable) :
              false);
	}
	return (true);
      }
    return (false);
  }

  /** Increments the schedulable item
   */
  public boolean nextSchedulableItem ()
  {
    if (selectedType != -1 && selectedGroup != -1
        && currentSchedulableId < sched[selectedType][selectedGroup].length - 1)
    {
      selectedTask = null;
      selectedSlot = -1;
      currentGroupId = selectedGroup;
      currentSchedulableId ++;
      currentSchedulable
        = sched[selectedType][currentGroupId][currentSchedulableId];
      if (selectableTasks != null)
      {
        for (int i = 0; i < selectableTasks.length; i++)
          selectableTasks[i] = (currentSchedulable != null ?
            ((Task) (unallocatedTasks.get (i))).imply (currentSchedulable) :
            false);
      }
      return (true);
    }
    return (false);
  }

  /** Decrements the schedulable item
   */
  public boolean previousSchedulableItem ()
  {
    if (selectedType != -1 && selectedGroup != -1 && currentSchedulableId > 0)
    {
      selectedTask = null;
      selectedSlot = -1;
      currentGroupId = selectedGroup;
      currentSchedulableId --;
      currentSchedulable
        = sched[selectedType][currentGroupId][currentSchedulableId];
      if (selectableTasks != null)
      {
        for (int i = 0; i < selectableTasks.length; i++)
          selectableTasks[i] = (currentSchedulable != null ?
            ((Task) (unallocatedTasks.get (i))).imply (currentSchedulable) :
            false);
      }
      return (true);
    }
    return (false);
  }

  /** Returns the current displayed module */
  public Schedulable[] displayedModuleGroup ()
  {
    if (displayedModuleGroup == -1) return (null);
    else return (sched[Schedulable.TYPE_MODULE][displayedModuleGroup]);
  }

  /** Activates a schedulable module
   */
  public boolean exploreModule (int num)
  {
    if (displayedModuleGroup == -1 || num < 0) return (false);
    if (num < sched[Schedulable.TYPE_MODULE][displayedModuleGroup].length)
    {
      if (num != selectedModule)
      {
        selectedModule = num;
        Module module = (Module)
          sched[Schedulable.TYPE_MODULE][displayedModuleGroup][num];
        module.unallocated (unallocatedTasks, unallocatedCount);
        if (unallocatedTasks.size () != 0)
        {
          selectableTasks = new boolean[unallocatedTasks.size ()];
          for (int i = 0; i < unallocatedTasks.size (); i++)
            selectableTasks[i] = (currentSchedulable != null ?
              ((Task) (unallocatedTasks.get (i))).imply (currentSchedulable) :
              false);
        }
	else selectableTasks = null;
        selectedTask = null;
	selectedSlot = -1;
        return (true);
      }
    }
    return (false);
  }

  /** Returns the selected module index */
  public int selectedModule ()
  {
    return (selectedModule);
  }

  /** Returns the unallocated tasks of the selected module */
  public JikanList unallocatedTasks ()
  {
    return (unallocatedTasks);
  }

  /** Returns the count of the unallocated task with given index*/
  public int unallocatedCount (int index)
  {
    return (((Integer) (unallocatedCount.get (index))).intValue ());
  }

  /** Returns if the task with the given index is selectable */
  public boolean isSelectableTask (int index)
  {
    return (selectableTasks[index]);
  }

  /** Activates a schedulable task
   */
  public boolean exploreUnallocatedTasks (int num)
  {
    if (selectableTasks == null || num < 0) return (false);
    if (num < selectableTasks.length && selectableTasks[num])
    {
      if (selectedTask != (Task) unallocatedTasks.get (num))
      {
        selectedTask = (Task) unallocatedTasks.get (num);
	selectedSlot = -1;
        return (true);
      }
    }
    return (false);
  }

  /** Requires information display about a schedulable task
   */
  public boolean infoAboutUnallocatedTasks (int num)
  {
    if (num < 0) return (false);
    if (num < selectableTasks.length)
    {
      infoToBeDisplayed = ((Task) unallocatedTasks.get (num)).getInformation ();
      return (true);
    }
    return (false);
  }

  /** Requires information display about a module
   */
  public boolean infoAboutModule (int num)
  {
    if (num < 0) return (false);
    if (num < sched[Schedulable.TYPE_MODULE][displayedModuleGroup].length)
    {
      infoToBeDisplayed = new JikanList ();
      infoToBeDisplayed.add (
        sched[Schedulable.TYPE_MODULE][displayedModuleGroup][num].info ());
      return (true);
    }
    return (false);
  }

  /** Requires information display about a complete name
   */
  public boolean infoAboutSchedulableItem (int num)
  {
    if (num < 0) return (false);
    if (num < sched[selectedType][selectedGroup].length)
    {
      infoToBeDisplayed = new JikanList ();
      infoToBeDisplayed.add (sched[selectedType][selectedGroup][num].info ());
      return (true);
    }
    return (false);
  }

  /** Returns the information to be displayed */
  public JikanList infoToBeDisplayed ()
  {
    JikanList info = infoToBeDisplayed;
    infoToBeDisplayed = null;
    return (info);
  }

  /** Returns the selected slot */
  public int selectedSlot ()
  {
    return (selectedSlot);
  }

  /** Returns if a slot is selected */
  public boolean isSelectedSlot ()
  {
    return (selectedSlot != -1);
  }

  /** Returns the selected task */
  public Task selectedTask ()
  {
    return (selectedTask);
  }

  /** Returns the selected task index */
  public int selectedTaskIndex ()
  {
    for (int i = 0; i < selectableTasks.length; i++)
      if (selectedTask == (Task) unallocatedTasks.get (i)) return (i);
    return (-1);
  }

  /** Activates a calendar grid period */
  public boolean exploreSlot (int day, int start)
  {
    // Positioning the selected task
    if (selectedTask != null)
    {
      unavailabilities = selectedTask.trySlot (selectedSlot, week, day, start);
      if (unavailabilities == null)
      {
        selectedTask = null;
        selectedSlot = -1;
        resetSelectableTasks ();
        modified = true;
      }
      return (true);
    }
    return (false);
  }

  /** Resets the selectable tasks */
  private void resetSelectableTasks ()
  {
    selectedTask = null;
    selectedSlot = -1;
    if (displayedModuleGroup == -1 || selectedModule == -1) return;
    Module mod = (Module)
      sched[Schedulable.TYPE_MODULE][displayedModuleGroup][selectedModule];
    mod.unallocated (unallocatedTasks, unallocatedCount);
    if (unallocatedTasks.size () != 0)
    {
      selectableTasks = new boolean[unallocatedTasks.size ()];
      for (int i = 0; i < selectableTasks.length; i++)
        selectableTasks[i] = (currentSchedulable != null ?
          ((Task) (unallocatedTasks.get (i))).imply (currentSchedulable) :
          false);
    }
    else selectableTasks = null;
  }

  /** Activates a positioned task on the grid
   */
  public boolean activateTask (int day, int period, int part)
  {
    JikanList slots = currentSchedulable.getSlots (week);
    if (slots == null) return (false);
    for (int i = 0; i < slots.size (); i++)
    {
      Slot slot = (Slot) (slots.get(i));
      if ((slot.day () == day)
          && ((slot.period () & (1 << period)) != 0)
          && ((currentSchedulable.type () != Schedulable.TYPE_STUDENT)
              || (((Student) currentSchedulable).checkZone (part,
                                                     slot.requiredItems ()))))
      {
        selectedTask = slot.task ();
	selectedSlot = selectedTask.indexOf (slot);
        return (true);
      }
    }
    return (false);
  }

  /** Requires information display about a scheduled task
   */
  public boolean infoAboutScheduledTask (int day, int period, int part)
  {
    JikanList slots = currentSchedulable.getSlots (week);
    if (slots == null) return (false);
    for (int i = 0; i < slots.size (); i++)
    {
      Slot slot = (Slot) (slots.get(i));
      if ((slot.day () == day)
          && ((slot.period () & (1 << period)) != 0)
          && (currentSchedulable.checkZone (part, slot.requiredItems ())))
      {
        infoToBeDisplayed = slot.task().getInformation ();
        return (true);
      }
    }
    return (false);
  }

  /** Releases the selected slot or task
   */
  public boolean releaseSelection ()
  {
    if (selectedSlot != -1 || selectedTask != null)
    {
      selectedTask = null;
      selectedSlot = -1;
      return (true);
    }
    return (false);
  }

  /** Removes the selected slot
   */
  public boolean removeSelectedSlot ()
  {
    if (selectedSlot != -1)
    {
      selectedTask.remove (selectedSlot);
      selectedTask = null;
      selectedSlot = -1;
      resetSelectableTasks ();
      modified = true;
      return (true);
    }
    return (false);
  }

  /** Finds a schedulable item from its name, group and type */
  public Schedulable find (int type, int group, String name)
  {
    for (int i = 0; i < sched[type][group].length; i++)
    {
      if (name.equals (sched[type][group][i].name ()))
        return (sched[type][group][i]);
      if (type == Schedulable.TYPE_STUDENT)
      {
        Student s = ((Student) sched[type][group][i]).find (name);
        if (s != null) return (s);
      }
    }
    return (null);
  }

  /** Finds a schedulable item from its name and type */
  public Schedulable find (int type, String name)
  {
    for (int i = 0; i < sched[type].length; i++)
      for (int j = 0; j < sched[type][i].length; j++)
      {
        if (name.equals (sched[type][i][j].name ()))
          return (sched[type][i][j]);
        if (type == Schedulable.TYPE_STUDENT)
        {
          Student s = ((Student) sched[type][i][j]).find (name);
          if (s != null) return (s);
        }
      }
    return (null);
  }

  /** Changes the indexed teacher of the selected slot */
  public boolean changeRequisite (int type, int index)
  {
    if (selectedSlot == -1) return (false);
    boolean ok = selectedTask.slot(selectedSlot).changeRequisite (type, index);
    if (ok) modified = true;
    return (ok);
  }

  /** Returns if the data base has been modified */
  public boolean modified ()
  {
    return (modified);
  }

  /** Resets the data base modified flag */
  public void resetModified ()
  {
    modified = false;
  }

  /** Returns, then resets the possible warning */
  public String warning ()
  {
    if (unavailabilities == null) return (null);
    String warning = "";
    for (int i = 0; i < unavailabilities.size (); i ++)
      warning += ((i == 0) ? "Indisponibilites : " : ", ")
                 + ((Schedulable) (unavailabilities.get (i))).name ();
    unavailabilities = null;
    return (warning);
  }

  /** Creates output file for the given name
   * from the given start week to the ending one */
  public void print (String schedName, String startWeek, String endWeek)
  {
    int sWeek = week;
    if (startWeek != null)
      sWeek = cal.weekIndex ( (new Integer (startWeek)).intValue ());
    if (sWeek == -1)
    {
      System.out.println ("Desole, semaine " + startWeek + " inconnue");
      return;
    }

    int eWeek = sWeek;
    if (endWeek != null)
      eWeek = cal.weekIndex ( (new Integer (endWeek)).intValue ());
    if (eWeek == -1)
    {
      System.out.println ("Desole, semaine " + endWeek + " inconnue");
      return;
    }

    if (schedName.equals ("all"))
    {
      print ("students", startWeek, endWeek);
      print ("teachers", startWeek, endWeek);
    }
    else if (schedName.equals ("students"))
    {
      for (int i = 0; i < sched[0].length; i ++)
        for (int j = 0; j < sched[0][i].length; j ++)
          print (sched[0][i][j], sWeek, eWeek);
    }
    else if (schedName.equals ("teachers"))
    {
      for (int i = 0; i < sched[1].length; i ++)
        for (int j = 0; j < sched[1][i].length; j ++)
          print (sched[1][i][j], sWeek, eWeek);
    }
    else if (schedName.equals ("rooms"))
    {
      for (int i = 0; i < sched[2].length; i ++)
        for (int j = 0; j < sched[2][i].length; j ++)
          print (sched[2][i][j], sWeek, eWeek);
    }
    else if (schedName.equals ("modules"))
    {
      for (int i = 0; i < sched[3].length; i ++)
        for (int j = 0; j < sched[3][i].length; j ++)
          print (sched[3][i][j], sWeek, eWeek);
    }
    else
    {
      for (int i = 0; i < sched.length; i++)
        for (int j = 0; j < sched[i].length; j++)
        {
          if (schedName.equals (groupNames[i][j]))
            for (int k = 0; k < sched[i][j].length; k ++)
              print (sched[i][j][k], sWeek, eWeek);
	  else
            for (int k = 0; k < sched[i][j].length; k ++)
              if (schedName.equals (sched[i][j][k].name ()))
              {
                print (sched[i][j][k], sWeek, eWeek);
                return;
              }
	}
    }
  }

  /** Creates output file of the given schedulable item
   * from the given start week to the ending one */
  public void print (Schedulable sched, int startWeek, int endWeek)
  {
    if (sched.isEditable ())
      {
      try
      {
        JikanTex tex = new JikanTex (sched, cal);
        for (int i = startWeek; i <= endWeek; i ++) tex.write (i);
        tex.terminate ();
      }
      catch (Exception exc)
      {
        System.out.println ("TeX output construction failed for "
                            + sched.name ());
      }
    }
  }

  /** Creates output file of the current schedulable item on the active week. */
  public void print ()
  {
    print (currentSchedulable, week, week);
  }

  /** Edits the teachers duties */
  public void editDuties ()
  {
    try
    {
      JikanTex jt = JikanTex.createDuties (cal);
      for (int i = 0; i < sched[1].length; i++)
        if (! groupNames[1][i].equals ("Groupes"))
          for (int j = 0; j < sched[1][i].length; j++)
            if (sched[1][i][j].isEditable ())
              editDuties (jt, (Teacher) sched[1][i][j]);
      jt.terminate ();
    }
    catch (Exception exc)
    {
      System.out.println ("TeX output construction failed for duties");
    }
  }

  /** Edits a teacher duties */
  public void editDuties (JikanTex jt, Teacher t) throws Exception
  {
    int counts[] = new int[4];
    int totals[] = {0, 0, 0, 0};

    JikanTex jtt = jt.createTeacherDuties (t.name (), t.info ());

    for (int i = 0; i < sched[3].length; i++)
    {
      boolean inModule = false;
      for (int j = 0; j < sched[3][i].length; j++)
      {
        if (! (groupNames[3][i].equals ("Autres")
               || groupNames[3][i].equals ("Ctes")))
        {
          for (int c = 0; c < counts.length; c++) counts[c] = 0;
          Lot[] lots = ((Module) sched[3][i][j]).lots ();
          for (int k = 0; k < lots.length; k++)
          {
            Task[] tasks = lots[k].tasks ();
            for (int l = 0; l < tasks.length; l++)
            {
              Task task = tasks[l];
              Schedulable[][] reqs = task.requisites ();
              for (int m = 0; m < reqs.length; m++)
                for (int n = 0; n < reqs[m].length; n++)
                  if (reqs[m][n] == t)
                  {
                    Slot[] slots = task.slots ();
                    for (int p = 0; p < slots.length; p++)
                      if (slots[p] != null)
                        if (slots[p].choice (m) == n)
                        {
                          int dur = task.cumuledDuration ();
                          counts[task.cost ()] += dur;
                          totals[task.cost ()] += dur;
                        }
                  }
            }
          }
          int etd = 0;
          boolean edit = false;
          for (int c = 1; c < counts.length; c++)
          {
            if (counts[c] != 0)
            {
              edit = true;
              inModule = true;
            }
            etd += counts[c] * Task.COST_VALS[c];
          }
          if (edit) jtt.add (groupNames[3][i] + " / " + sched[3][i][j].name (),
                             counts, ((float) etd) / Task.costEtd ());
        }
      }
      if (inModule) jtt.otherTeacherDuties ();
      inModule = false;
    }
    int etd = 0;
    boolean ok = false;
    for (int c = 1; c < totals.length; c++)
    {
      if (totals[c] != 0) ok = true;
      etd += totals[c] * Task.COST_VALS[c];
    }
    jtt.closeTeacherDuties (totals, ((float) etd / (2 * Task.costEtd ())));
    if (ok) jt.inputDuties (t.name ());
  }
}

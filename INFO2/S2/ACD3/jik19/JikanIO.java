import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.StreamTokenizer;
import java.io.PrintWriter;


/** Editable file upload and download facilities for Jikan planner.
  * @author Philippe Even
  */
public class JikanIO
{
  /** Data base */
  private JikanData data = null;
  /** Groupable items (schedulable) */
  private JikanList groupables = new JikanList ();

  /** Data directory */
  private File dataDir = null;

  /** Data directory */
  private final static String DATA_DIR = "data";
  /** Release file */
  private final static String RELEASE_FILE = "release.txt";
  /** Calendar file name */
  private final static String CALENDAR_FILE_NAME = "calendar.txt";
  /** Groups contents file */
  private final static String CONTENTS_FILE = "aCharger.txt";
  /** Suffix for files */
  private final static String FILE_SUFFIX = ".txt";

  /** Schedulable directory names */
  private final static String[] typeDirs = {
           "students", "teachers", "rooms", "modules"};
  /** Planing directory name */
  private final static String planingDirName = "slots";
  /** Security temporary planing directory name */
  private final static String tmpDirName = "temps";

  /** Administration mode */
  private final boolean isManaging;

  /** Features deprecated dictionnary */
  private final static String YEAR = "Year";
  private final static String FIRST_WEEK = "FirstWeekOfScolarYear";
  private final static String LAST_WEEK = "LastWeekOfRegularYear";
  private final static String YEAR_LENGTH = "YearLength";
  private final static String WEEK_LENGTH = "WeekLength";
  private final static String HOLYDAY_WEEK = "HolyWeek";
  private final static String HOLYDAY_DAY = "HolyDay";
  private final static String DEFINE_SLOTS = "Slots";
  private final static String TP_EQUALS_TD = "TpEgalTd";

  /** Features upload dictionnary */
  private final static String CAL_YEAR = "Annee";
  private final static String CAL_FIRST_WEEK = "PremiereSemaine";
  private final static String CAL_LAST_WEEK = "DerniereSemaineCivile";
  private final static String CAL_YEAR_LENGTH = "NbSemaines";
  private final static String CAL_WEEK_LENGTH = "DureeSemaines";
  private final static String CAL_REGULAR_CST = "ContrainteReguliere";
  private final static String CAL_HOLYDAY_WEEK = "Vacances";
  private final static String CAL_HOLYDAY_DAY = "Ferie";
  private final static String CAL_DEFINE_SLOTS = "Journees";
  private final static String SCHED_COLOUR = "Couleur";
  private final static String SCHED_INFO = "Info";
  private final static String STUDENT_PARTITION = "Partition";
  private final static String STUDENT_GROUPS = "Groupes";
  private final static String STUDENT_RELEASE_CST = "RelacheContrainte";
  private final static String TEACHER_REGULAR_CST = "ContrainteReguliere";
  private final static String TEACHER_EXCEPTIONAL_CST
      = "ContrainteOccasionnelle";
  private final static String TEACHER_RELEASE_CST = "RelacheContrainte";
  private final static String TEACHER_CST_FORBID = "interdit";
  private final static String TEACHER_CST_AVOID = "indesirable";
  private final static String MODULE_NEW_LOT = "Lot";
  private final static String MODULE_ALIAS = "Alias";
  private final static String MODULE_HIDDEN = "AffichageAdministratif";
  private final static String MODULE_NAME_HIDE = "MasquageDuNom";
  private final static String MODULE_TEACH_HIDE = "MasquageDesEns";
  private final static String MODULE_STUD_HIDE = "MasquageDesEtu";
  private final static String MODULE_NO_TEX = "NoTex";
  private final static String MODULE_LOT_ALIAS = "AliasLot";
  private final static String MODULE_LOT_NAME_DISPLAY = "AffichageDuNom";
  private final static String MODULE_LOT_MODULE_HIDE = "MasquageDuModule";
  private final static String MODULE_LOT_COUNTER_DISPLAY = "Numerotation";
  private final static String MODULE_NEW_TASK = "Tache";
  private final static String MODULE_TASK_COST = "Cout";
  private final static String MODULE_TASK_DURATION = "Duree";
  private final static String MODULE_TASK_STUDENT = "Etudiant";
  private final static String MODULE_TASK_TEACHER = "Enseignant";
  private final static String MODULE_TASK_ROOM = "Salle";
  private final static String MODULE_TASK_STUDENTS = "Etudiants";
  private final static String MODULE_TASK_TEACHERS = "Enseignants";
  private final static String MODULE_TASK_ROOMS = "Salles";
  private final static String MODULE_TASK_REPETITION
      = "Repetition"; // Doit etre en fin de definition de la tache a repeter



  /** Constructs the archive manager */
  public JikanIO (JikanData data, boolean isManaging)
  {
    this.data = data;
    this.isManaging = isManaging;
  }


  /** Uploads application data */
  public void loadData ()
  {
    // Sets data directory
    setDataDir ();
    // Loads calendar
    loadCalendar ();
    // Loads students, teachers, rooms, then modules
    for (int i = 0; i < typeDirs.length; i ++) loadType (i);
    // Loads slots
    loadSlots ();
  }

  /** Sets data directory */
  private void setDataDir ()
  {
    // Current project uploading */
    try
    {
      StreamTokenizer st = new StreamTokenizer (
        new FileReader (
          new File (DATA_DIR, RELEASE_FILE)));
      st.nextToken ();
      dataDir = new File (DATA_DIR, st.sval);
    }
    catch (FileNotFoundException e)
    {
      System.out.println ("Sorry, no release file found");
      System.exit (0);
    }
    catch (Exception e)
    {
      System.out.println ("Release file damaged");
      System.exit (0);
    }
    if (! dataDir.exists ())
    {
      System.out.println ("Sorry, no " + dataDir.getName ()
                          + " data directory found");
      System.exit (0);
    }
  }

  // Loads the present calendar
  private void loadCalendar ()
  {
    // Checks calendar file
    File calFile = new File (dataDir, CALENDAR_FILE_NAME);
    if (! calFile.exists ())
    {
      System.out.println ("Sorry : no " + CALENDAR_FILE_NAME + " file found");
      System.exit (0);
    }

    try
    {
      // Reads calendar features
      StreamTokenizer st = new StreamTokenizer (new FileReader (calFile));
      st.nextToken ();
      if (st.ttype != StreamTokenizer.TT_WORD)
        abort (CALENDAR_FILE_NAME, "?", st.lineno ());
      if (! (st.sval.equals (CAL_YEAR)
             || st.sval.equals (YEAR)))
        abort (CALENDAR_FILE_NAME, "should be " + CAL_YEAR, st.lineno ());
      st.nextToken ();
      if (st.ttype != StreamTokenizer.TT_NUMBER)
        abort (CALENDAR_FILE_NAME, CAL_YEAR, st.lineno ());
      int year = (int) st.nval;
      st.nextToken ();
      if (st.ttype != StreamTokenizer.TT_WORD)
        abort (CALENDAR_FILE_NAME, "?", st.lineno ());
      if (! (st.sval.equals (CAL_FIRST_WEEK)
             || st.sval.equals (FIRST_WEEK)))
        abort (CALENDAR_FILE_NAME, "should be " + CAL_FIRST_WEEK, st.lineno ());
      st.nextToken ();
      if (st.ttype != StreamTokenizer.TT_NUMBER)
        abort (CALENDAR_FILE_NAME, CAL_FIRST_WEEK, st.lineno ());
      int firstWeek = (int) st.nval;
      st.nextToken ();
      if (st.ttype != StreamTokenizer.TT_WORD)
        abort (CALENDAR_FILE_NAME, "?", st.lineno ());
      if (st.sval.equals (CAL_LAST_WEEK) || st.sval.equals (LAST_WEEK))
      {
        st.nextToken ();
        st.nextToken ();
        if (st.ttype != StreamTokenizer.TT_WORD)
          abort (CALENDAR_FILE_NAME, "?", st.lineno ());
      }
      if (! (st.sval.equals (CAL_YEAR_LENGTH)
             || st.sval.equals (YEAR_LENGTH)))
        abort (CALENDAR_FILE_NAME, "should be " + CAL_YEAR_LENGTH, st.lineno ());
      st.nextToken ();
      if (st.ttype != StreamTokenizer.TT_NUMBER)
        abort (CALENDAR_FILE_NAME, CAL_YEAR_LENGTH, st.lineno ());
      int yearLength = (int) st.nval;
      st.nextToken ();
      if (st.ttype != StreamTokenizer.TT_WORD)
        abort (CALENDAR_FILE_NAME, "?", st.lineno ());
      if (! (st.sval.equals (CAL_WEEK_LENGTH)
            || st.sval.equals (WEEK_LENGTH)))
        abort (CALENDAR_FILE_NAME,
               "should be " + CAL_WEEK_LENGTH, st.lineno ());
      st.nextToken ();
      if (st.ttype != StreamTokenizer.TT_NUMBER)
        abort (CALENDAR_FILE_NAME, CAL_WEEK_LENGTH, st.lineno ());
      int weekLength = (int) st.nval;
      data.setCalendar (year, firstWeek, yearLength, weekLength);
      JikanCalendar cal = data.calendar ();

      st.nextToken ();
      while (st.ttype != StreamTokenizer.TT_EOF)
      {
        if (st.ttype != StreamTokenizer.TT_WORD)
          abort (CALENDAR_FILE_NAME, "?", st.lineno ());
        if (st.sval.equals (CAL_REGULAR_CST))
        {
          st.nextToken ();
          if (st.ttype != StreamTokenizer.TT_NUMBER)
            abort (CALENDAR_FILE_NAME, CAL_REGULAR_CST, st.lineno ());
          int dayCst = (int) st.nval;
          if (dayCst < 0 || dayCst >= weekLength)
            abort (CALENDAR_FILE_NAME, CAL_REGULAR_CST, st.lineno ());
          st.nextToken ();
          if (st.ttype != StreamTokenizer.TT_WORD)
            abort (CALENDAR_FILE_NAME, CAL_REGULAR_CST, st.lineno ());
          cal.setWorkingSlots (dayCst, st.sval);
        }
	else if (st.sval.equals (TEACHER_EXCEPTIONAL_CST))
        {
          st.nextToken ();
          if (st.ttype != StreamTokenizer.TT_WORD)
            abort (CALENDAR_FILE_NAME, TEACHER_EXCEPTIONAL_CST, st.lineno ());
          int weekCst = cal.weekIndex (
                (new Integer (st.sval.substring (1))).intValue ());
          if (weekCst < 0)
            abort (CALENDAR_FILE_NAME, TEACHER_EXCEPTIONAL_CST, st.lineno ());
          st.nextToken ();
          if (st.ttype != StreamTokenizer.TT_NUMBER)
            abort (CALENDAR_FILE_NAME, TEACHER_EXCEPTIONAL_CST, st.lineno ());
          int dayCst = (int) st.nval;
          if (dayCst < 0 || dayCst >= weekLength)
            abort (CALENDAR_FILE_NAME, TEACHER_EXCEPTIONAL_CST, st.lineno ());
          st.nextToken ();
          if (st.ttype != StreamTokenizer.TT_WORD)
            abort (CALENDAR_FILE_NAME, TEACHER_EXCEPTIONAL_CST, st.lineno ());
          cal.setWorkingSlots (weekCst, dayCst, st.sval);
        }
        else if (st.sval.equals (CAL_HOLYDAY_WEEK)
                 || st.sval.equals (HOLYDAY_WEEK))
        {
          st.nextToken ();
          if (st.ttype != StreamTokenizer.TT_NUMBER)
            abort (CALENDAR_FILE_NAME, CAL_HOLYDAY_WEEK, st.lineno ());
          int holyWeek = cal.weekIndex ((int) st.nval);
          for (int i = 0; i < weekLength; i++)
            cal.setHolydays (holyWeek, i);
        }
        else if (st.sval.equals (HOLYDAY_DAY)
                 || st.sval.equals (CAL_HOLYDAY_DAY))
        {
          st.nextToken ();
          if (st.ttype != StreamTokenizer.TT_NUMBER)
            abort (CALENDAR_FILE_NAME, CAL_HOLYDAY_DAY, st.lineno ());
          int holyWeek = cal.weekIndex ((int) st.nval);
          st.nextToken ();
          if (st.ttype != StreamTokenizer.TT_NUMBER)
            abort (CALENDAR_FILE_NAME, CAL_HOLYDAY_DAY, st.lineno ());
          int holyDay = (int) st.nval;
	  if (holyDay < 0 || holyDay >= weekLength)
            abort (CALENDAR_FILE_NAME, CAL_HOLYDAY_DAY, st.lineno ());
          cal.setHolydays (holyWeek, holyDay);
        }
        else if (st.sval.equals (DEFINE_SLOTS)
                 || st.sval.equals (CAL_DEFINE_SLOTS))
        {
          st.nextToken ();
          if (st.ttype != StreamTokenizer.TT_NUMBER)
            abort (CALENDAR_FILE_NAME, CAL_DEFINE_SLOTS, st.lineno ());
          int duration = (int) st.nval;
          st.nextToken ();
          if (st.ttype != StreamTokenizer.TT_NUMBER)
            abort (CALENDAR_FILE_NAME, CAL_DEFINE_SLOTS, st.lineno ());
          int start = (int) st.nval;
          st.nextToken ();
          if (st.ttype != StreamTokenizer.TT_NUMBER)
            abort (CALENDAR_FILE_NAME, CAL_DEFINE_SLOTS, st.lineno ());
          cal.setVisibleSlots (duration, start, (int) st.nval);
        }
        st.nextToken ();
      }
    }
    catch (IOException e)
    {
      System.out.println ("Problem in " + CALENDAR_FILE_NAME + " file");
      System.exit (0);
    }
  }

  // Loads schedulable groups of the given type
  private void loadType (int type)
  {
    JikanList names = new JikanList ();
    JikanList groups = new JikanList ();

    // Checks directory
    File typeDir = new File (dataDir, typeDirs[type]);
    if (! typeDir.exists ())
    {
      System.out.println ("Sorry : no " + typeDir.getName ()
                          + " data directory found");
      System.exit (0);
    }

    // Opens group directories
    File contentsFile = new File (typeDir, CONTENTS_FILE);
    if (! contentsFile.exists ())
    {
      System.out.println ("Sorry : no " + CONTENTS_FILE
                          + " file in " + typeDir.getName ());
      System.exit (0);
    }
    JikanList groupDirs = new JikanList ();
    try
    {
      StreamTokenizer st = new StreamTokenizer (new FileReader (contentsFile));
      st.nextToken ();
      while (st.ttype != StreamTokenizer.TT_EOF)
      {
        groupDirs.add (new File (typeDir, st.sval));
	st.nextToken ();
      }
    }
    catch (IOException e)
    {
      System.out.println ("Problem in " + CONTENTS_FILE
                          + " file in " + typeDir.getName ());
      System.exit (0);
    }

    // Loads each group directories
    for (int i = 0; i < groupDirs.size (); i++)
    {
      groups.add (loadGroup ((File) groupDirs.get(i), type));
      names.add (new String (((File) groupDirs.get(i)).getName ()));
    }

    // Fills the data base
    data.setSchedules (type, names, groups);
  }

  /** Loads a group of schedulable objects */
  private JikanList loadGroup (File groupDir, int type)
  {
    // Checks the group directory
    if (! groupDir.isDirectory ())
    {
      System.out.println ("Sorry : no " + groupDir.getName ()
                          + " data directory found");
      System.exit (0);
    }

    // Uploads schedulable items of the group
//    File[] schedFiles = groupDir.listFiles ();
    File contentsFile = new File (groupDir, CONTENTS_FILE);
    if (! contentsFile.exists ())
    {
      System.out.println ("Sorry : no " + CONTENTS_FILE
                          + " file in " + groupDir.getName ());
      System.exit (0);
    }
    JikanList schedFiles = new JikanList ();
    try
    {
      StreamTokenizer st = new StreamTokenizer (new FileReader (contentsFile));
      st.nextToken ();
      while (st.ttype != StreamTokenizer.TT_EOF)
      {
        schedFiles.add (new File (groupDir, st.sval + FILE_SUFFIX));
	st.nextToken ();
      }
    }
    catch (IOException e)
    {
      System.out.println ("Problem in " + CONTENTS_FILE
                          + " file in " + groupDir.getName ());
      System.exit (0);
    }

    JikanList uploadedScheds = new JikanList ();
    for (int i = 0; i < schedFiles.size (); i++)
    {
      // Creates the schedulable item
      if (type == Schedulable.TYPE_STUDENT)
        uploadedScheds.add (loadStudent ((File) schedFiles.get (i)));
      else if (type == Schedulable.TYPE_TEACHER)
        uploadedScheds.add (loadTeacher ((File) schedFiles.get (i)));
      else if (type == Schedulable.TYPE_ROOM)
        uploadedScheds.add (loadRoom ((File) schedFiles.get (i)));
      else if (type == Schedulable.TYPE_MODULE)
        uploadedScheds.add (loadModule ((File) schedFiles.get (i)));
    }
    return (uploadedScheds);
  }

  /** Loads a student */
  private StudentPromo loadStudent (File schedFile)
  {
    JikanCalendar cal = data.calendar ();
    StudentPromo sched = new StudentPromo ();
    String fileName = schedFile.getName();
    sched.setName (fileName.substring (0, fileName.length ()
                                          - FILE_SUFFIX.length ()));
    sched.setToDefaultOccupancy (cal.weekDays().length,
                                 cal.weekDays()[0].length);
    sched.setForbiddenSlots (cal.occ);
    try
    {
      StreamTokenizer st = new StreamTokenizer (new FileReader (schedFile));
      st.quoteChar ('"');
      st.nextToken ();
      boolean ok = false;
      while (st.ttype != StreamTokenizer.TT_EOF)
      {
        if (st.ttype != StreamTokenizer.TT_WORD)
          abort (sched.name (), "?", st.lineno ());
        if (st.sval.equals (SCHED_COLOUR))
          loadColour (st, sched);
        if (st.sval.equals (SCHED_INFO))
          loadInfo (st, sched);
	else if (st.sval.equals (STUDENT_PARTITION))
          partition (st, sched);
	else if (st.sval.equals (STUDENT_GROUPS))
          loadGroups (st, sched);
	else if (st.sval.equals (STUDENT_RELEASE_CST))
          loadReleaseConstraint (st, sched);
	else abort (sched.name (), st.sval, st.lineno ());
      }
    }
    catch (Exception e)
    {
      System.out.println (schedFile.getName () + " file damaged");
      System.exit (0);
    }
    return (sched);
  }

  /** Loads a teacher */
  private Teacher loadTeacher (File schedFile)
  {
    Teacher sched = new Teacher ();
    JikanCalendar cal = data.calendar ();
    String fileName = schedFile.getName();
    sched.setName (fileName.substring (0, fileName.length ()
                                          - FILE_SUFFIX.length ()));
    sched.setToDefaultOccupancy (cal.weekDays().length,
                                 cal.weekDays()[0].length);
    try
    {
      StreamTokenizer st = new StreamTokenizer (new FileReader (schedFile));
      st.quoteChar ('"');
      st.nextToken ();
      boolean ok = false;
      while (st.ttype != StreamTokenizer.TT_EOF)
      {
        if (st.ttype != StreamTokenizer.TT_WORD)
          abort (sched.name (), "?", st.lineno ());
        if (st.sval.equals (SCHED_COLOUR))
          loadColour (st, sched);
        else if (st.sval.equals (SCHED_INFO))
          loadInfo (st, sched);
	else if (st.sval.equals (TEACHER_REGULAR_CST))
          loadRegularConstraint (st, sched);
	else if (st.sval.equals (TEACHER_EXCEPTIONAL_CST))
          loadExceptionalConstraint (st, sched);
	else if (st.sval.equals (TEACHER_RELEASE_CST))
          loadReleaseConstraint (st, sched);
	else if (st.sval.equals (TP_EQUALS_TD)) st.nextToken (); // deprecated
	else abort (sched.name (), st.sval, st.lineno ());
      }
    }
    catch (Exception e)
    {
      System.out.println (schedFile.getName () + " file damaged");
      System.exit (0);
    }
    groupables.add (sched);
    return (sched);
  }

  /** Loads a room */
  private Room loadRoom (File schedFile)
  {
    Room sched = new Room ();
    JikanCalendar cal = data.calendar ();
    String fileName = schedFile.getName();
    sched.setName (fileName.substring (0, fileName.length ()
                                          - FILE_SUFFIX.length ()));
    sched.setToDefaultOccupancy (cal.weekDays().length,
                                 cal.weekDays()[0].length);
    try
    {
      StreamTokenizer st = new StreamTokenizer (new FileReader (schedFile));
      st.quoteChar ('"');
      st.nextToken ();
      boolean ok = false;
      while (st.ttype != StreamTokenizer.TT_EOF)
      {
        if (st.ttype != StreamTokenizer.TT_WORD)
          abort (sched.name (), "?", st.lineno ());
        if (st.sval.equals (SCHED_COLOUR))
          loadColour (st, sched);
        else if (st.sval.equals (SCHED_INFO))
          loadInfo (st, sched);
	else abort (sched.name (), st.sval, st.lineno ());
      }
    }
    catch (Exception e)
    {
      System.out.println (schedFile.getName () + " file damaged");
      System.exit (0);
    }
    return (sched);
  }

  /** Loads a module */
  private Module loadModule (File schedFile)
  {
    Module sched = new Module ();
    JikanCalendar cal = data.calendar ();
    JikanList lots = new JikanList ();
    JikanList tasks = new JikanList ();
    JikanList reqs = new JikanList ();
    String fileName = schedFile.getName();
    sched.setName (fileName.substring (0, fileName.length ()
                                          - FILE_SUFFIX.length ()));
    sched.setToDefaultOccupancy (cal.weekDays().length,
                                 cal.weekDays()[0].length);
    try
    {
      StreamTokenizer st = new StreamTokenizer (new FileReader (schedFile));
      st.nextToken ();
      boolean ok = false;
      while (st.ttype != StreamTokenizer.TT_EOF)
      {
        if (st.ttype != StreamTokenizer.TT_WORD)
          abort (sched.name (), "?", st.lineno ());
	if (st.sval.equals (SCHED_COLOUR))
          loadColour (st, sched);
	else if (st.sval.equals (SCHED_INFO))
          loadInfo (st, sched);
	else if (st.sval.equals (MODULE_ALIAS))
          loadModuleAlias (st, sched);
	else if (st.sval.equals (MODULE_HIDDEN))
          loadModuleHidden (st, sched);
	else if (st.sval.equals (MODULE_NAME_HIDE))
          loadModuleNameHide (st, sched);
	else if (st.sval.equals (MODULE_TEACH_HIDE))
          loadModuleTeacherHide (st, sched);
	else if (st.sval.equals (MODULE_STUD_HIDE))
          loadModuleStudentHide (st, sched);
	else if (st.sval.equals (MODULE_NO_TEX))
          loadModuleNoTex (st, sched);
	else if (st.sval.equals (MODULE_LOT_ALIAS))
          loadLotAlias (st, lots, sched);
	else if (st.sval.equals (MODULE_NEW_LOT))
          loadNewLot (st, lots, tasks, reqs, sched);
	else if (st.sval.equals (MODULE_LOT_NAME_DISPLAY))
          loadLotNameDisplay (st, lots, sched);
	else if (st.sval.equals (MODULE_LOT_MODULE_HIDE))
          loadLotModuleHide (st, lots, sched);
	else if (st.sval.equals (MODULE_LOT_COUNTER_DISPLAY))
          loadLotCounterDisplay (st, lots, sched);
	else if (st.sval.equals (MODULE_NEW_TASK))
          loadNewTask (st, lots, tasks, reqs, sched);
	else if (st.sval.equals (MODULE_TASK_COST))
          loadTaskCost (st, tasks, sched);
	else if (st.sval.equals (MODULE_TASK_DURATION))
          loadTaskDuration (st, tasks, sched);
	else if (st.sval.equals (MODULE_TASK_STUDENT))
          loadOneTaskRequire (st, Schedulable.TYPE_STUDENT,
                              MODULE_TASK_STUDENT, reqs, sched);
	else if (st.sval.equals (MODULE_TASK_TEACHER))
          loadOneTaskRequire (st, Schedulable.TYPE_TEACHER,
                              MODULE_TASK_TEACHER, reqs, sched);
	else if (st.sval.equals (MODULE_TASK_ROOM))
          loadOneTaskRequire (st, Schedulable.TYPE_ROOM,
                              MODULE_TASK_ROOM, reqs, sched);
	else if (st.sval.equals (MODULE_TASK_STUDENTS))
          loadTaskRequires (st, Schedulable.TYPE_STUDENT,
                            MODULE_TASK_STUDENTS, reqs, sched);
	else if (st.sval.equals (MODULE_TASK_TEACHERS))
          loadTaskRequires (st, Schedulable.TYPE_TEACHER,
                            MODULE_TASK_TEACHERS, reqs, sched);
	else if (st.sval.equals (MODULE_TASK_ROOMS))
          loadTaskRequires (st, Schedulable.TYPE_ROOM,
                            MODULE_TASK_ROOMS, reqs, sched);
	else if (st.sval.equals (MODULE_TASK_REPETITION))
          loadTaskRepetition (st, tasks, sched);
	else abort (sched.name (), st.sval, st.lineno ());
      }

      // Terminaison de la tache et du lot en cours
      if (tasks.size () == 0)
        abort (sched.name (), "terminer", st.lineno ());
      addRequiresToLastTask (reqs, tasks);
      if (! addTasksToLastLot (tasks, lots))
        abort (sched.name (), "terminer", st.lineno ());
      sched.setLots (lots);
    }
    catch (Exception e)
    {
      System.out.println (schedFile.getName () + " file damaged");
      System.exit (0);
    }
    return (sched);
  }

  /** Loads a schedulable object colour */
  private void loadColour (StreamTokenizer st, Schedulable sched)
                          throws IOException
  {
    float[] col = new float[3];
    for (int i = 0; i < 3; i++)
    {
      st.nextToken ();
      if (st.ttype != StreamTokenizer.TT_NUMBER)
        abort (sched.name (), SCHED_COLOUR, st.lineno ());
      int color = (int) st.nval;
      if (col[i] < 0 || col[i] > 255)
        abort (sched.name (), SCHED_COLOUR, st.lineno ());
      col[i] = color / 255.0f;
    }
    st.nextToken ();
    sched.setColour (col);
  }

  /** Loads a schedulable object full name */
  private void loadInfo (StreamTokenizer st, Schedulable sched)
                         throws IOException
  {
    st.nextToken ();
    if (st.ttype != '"')
      abort (sched.name (), SCHED_INFO, st.lineno ());
    sched.setInfo (st.sval);
    st.nextToken ();
  }

  /** Loads a students partition */
  private void partition (StreamTokenizer st, StudentPromo sched)
                          throws IOException
  {
    st.nextToken ();
    if (st.ttype != StreamTokenizer.TT_NUMBER)
      abort (sched.name (), STUDENT_PARTITION, st.lineno ());
    int nb = (int) st.nval;
    if (nb < 2) abort (sched.name (), STUDENT_PARTITION, st.lineno ());
    st.nextToken ();
    sched.partition (nb);
  }

  /** Loads a student group division */
  private void loadGroups (StreamTokenizer st, StudentPromo sched)
                          throws IOException
  {
    st.nextToken ();
    if (st.ttype != StreamTokenizer.TT_NUMBER)
      abort (sched.name (), STUDENT_GROUPS, st.lineno ());
    int count = (int) st.nval;
    if (count <= 0 || count > sched.partitionCount ())
      abort (sched.name (), STUDENT_GROUPS, st.lineno ());
    StudentGroup[] groups = new StudentGroup[count];
    for (int i = 0; i < count; i++)
    {
      st.nextToken ();
      if (st.ttype != StreamTokenizer.TT_WORD)
        abort (sched.name (), STUDENT_GROUPS, st.lineno ());
      groups[i] = new StudentGroup ();
      groups[i].setName (st.sval);
    }
    st.nextToken ();
    sched.addGroups (groups);
  }

  /** Loads a teacher regular constraint */
  private void loadRegularConstraint (StreamTokenizer st, Teacher sched)
                                     throws IOException
  {
    // Nature of the constraint
    st.nextToken ();
    if (st.ttype != StreamTokenizer.TT_WORD)
      abort (sched.name (), TEACHER_REGULAR_CST, st.lineno ());
    Occupancy cst = null;
    if (st.sval.equals (TEACHER_CST_FORBID)) cst = sched.forbiddenSlots ();
    else if (st.sval.equals (TEACHER_CST_AVOID)) cst = sched.avoidableSlots ();
    else abort (sched.name (), TEACHER_REGULAR_CST, st.lineno ());

    // Constraint parameters
    st.nextToken ();
    if (st.ttype != StreamTokenizer.TT_NUMBER)
      abort (sched.name (), TEACHER_REGULAR_CST, st.lineno ());
    try
    {
      int day = (int) st.nval;
      st.nextToken ();
      if (st.ttype != StreamTokenizer.TT_WORD)
        abort (sched.name (), TEACHER_REGULAR_CST, st.lineno ());
      cst.forbidEachWeek (day, st.sval);
    }
    catch (Exception e)
    {
      abort (sched.name (), TEACHER_REGULAR_CST, st.lineno ());
    }
    st.nextToken ();
  }

  /** Loads a teacher exceptional constraint */
  private void loadExceptionalConstraint (StreamTokenizer st, Teacher sched)
                                         throws IOException
  {
    // Nature of the constraint
    st.nextToken ();
    if (st.ttype != StreamTokenizer.TT_WORD)
      abort (sched.name (), TEACHER_EXCEPTIONAL_CST, st.lineno ());
    Occupancy cst = null;
    if (st.sval.equals (TEACHER_CST_FORBID)) cst = sched.forbiddenSlots ();
    else if (st.sval.equals (TEACHER_CST_AVOID)) cst = sched.avoidableSlots ();
    else abort (sched.name (), TEACHER_EXCEPTIONAL_CST, st.lineno ());

    // Constraint parameters
    st.nextToken ();
    if (st.ttype != StreamTokenizer.TT_WORD)
      abort (sched.name (), TEACHER_EXCEPTIONAL_CST, st.lineno ());
    try
    {
      int week = data.calendar ().weekIndex (
                       (new Integer (st.sval.substring (1))).intValue ());
      if (week < 0) abort (sched.name (), TEACHER_EXCEPTIONAL_CST, st.lineno ());
      st.nextToken ();
      if (st.ttype != StreamTokenizer.TT_NUMBER)
        abort (sched.name (), TEACHER_EXCEPTIONAL_CST, st.lineno ());
      int day = (int) st.nval;
      st.nextToken ();
      if (st.ttype != StreamTokenizer.TT_WORD)
        abort (sched.name (), TEACHER_EXCEPTIONAL_CST, st.lineno ());
      {
        cst.forbid (week, day, st.sval);
      }
    }
    catch (Exception e)
    {
      abort (sched.name (), TEACHER_EXCEPTIONAL_CST, st.lineno ());
    }
    st.nextToken ();
  }

  /** Exceptionaly releases a teacher constraint */
  private void loadReleaseConstraint (StreamTokenizer st, Schedulable sched)
                                      throws IOException
  {
    // Constraint parameters
    st.nextToken ();
    if (st.ttype != StreamTokenizer.TT_WORD)
      abort (sched.name (), TEACHER_RELEASE_CST, st.lineno ());
    try
    {
      int week = data.calendar ().weekIndex (
                       (new Integer (st.sval.substring (1))).intValue ());
      if (week < 0) abort (sched.name (), TEACHER_RELEASE_CST, st.lineno ());
      st.nextToken ();
      if (st.ttype != StreamTokenizer.TT_NUMBER)
        abort (sched.name (), TEACHER_RELEASE_CST, st.lineno ());
      int day = (int) st.nval;
      st.nextToken ();
      if (st.ttype != StreamTokenizer.TT_WORD)
        abort (sched.name (), TEACHER_RELEASE_CST, st.lineno ());
      if (sched.type () == Schedulable.TYPE_TEACHER)
      {
        ((Teacher) sched).forbiddenSlots().setFree (week, day, st.sval);
        ((Teacher) sched).avoidableSlots().setFree (week, day, st.sval);
      }
      else if (sched.type () == Schedulable.TYPE_STUDENT)
        ((StudentPromo) sched).forbiddenSlots().setFree (week, day, st.sval);
    }
    catch (Exception e)
    {
      abort (sched.name (), TEACHER_RELEASE_CST, st.lineno ());
    }
    st.nextToken ();
  }

  /** Creates a new lot into the module being loaded */
  private void loadNewLot (StreamTokenizer st,
                           JikanList lots, JikanList tasks, JikanList reqs,
                           Module sched)
                          throws IOException
  {
    st.nextToken ();
    if (st.ttype != StreamTokenizer.TT_WORD)
      abort (sched.name (), MODULE_NEW_LOT, st.lineno ());
    if (lots.size () != 0)
    {
      if (tasks.size () == 0)
        abort (sched.name (), MODULE_NEW_LOT, st.lineno ());
      addRequiresToLastTask (reqs, tasks);
      if (! (addTasksToLastLot (tasks, lots)))
        abort (sched.name (), MODULE_NEW_LOT, st.lineno ());
    }
    lots.add (new Lot (sched, sched.nameDisplayed (),
                              sched.teacherDisplayed (),
                              sched.studentDisplayed ()));
    ((Lot) (lots.last ())).setName (st.sval);
    st.nextToken ();
  }

  /** Sets the module name for display */
  private void loadModuleAlias (StreamTokenizer st, Module sched)
                                throws IOException
  {
    st.nextToken ();
    if (st.ttype != StreamTokenizer.TT_WORD)
      abort (sched.name (), MODULE_NEW_LOT, st.lineno ());
    sched.setAlias (st.sval);
    st.nextToken ();
  }

  /** Loads a module user hidden modality */
  private void loadModuleHidden (StreamTokenizer st, Module module)
                                  throws IOException
  {
    if (! isManaging) module.setHidden ();
    st.nextToken ();
  }

  /** Loads a module name hiding modality */
  private void loadModuleNameHide (StreamTokenizer st, Module module)
                                  throws IOException
  {
    module.displayName (false);
    st.nextToken ();
  }

  /** Loads a teachers name hiding modality */
  private void loadModuleTeacherHide (StreamTokenizer st, Module module)
                                  throws IOException
  {
    module.displayTeachers (false);
    st.nextToken ();
  }

  /** Loads a students name hiding modality */
  private void loadModuleStudentHide (StreamTokenizer st, Module module)
                                  throws IOException
  {
    module.displayStudents (false);
    st.nextToken ();
  }

  /** Loads a module unprint modality */
  private void loadModuleNoTex (StreamTokenizer st, Module module)
                                  throws IOException
  {
    module.texable (false);
    st.nextToken ();
  }

  /** Sets the lot name for display */
  private void loadLotAlias (StreamTokenizer st, JikanList lots,
                             Module sched)
                            throws IOException
  {
    st.nextToken ();
    if (lots.size () == 0)
      abort (sched.name (), MODULE_LOT_NAME_DISPLAY, st.lineno ());
    if (st.ttype != StreamTokenizer.TT_WORD)
      abort (sched.name (), MODULE_NEW_LOT, st.lineno ());
    ((Lot) (lots.last ())).setAlias (st.sval);
    st.nextToken ();
  }

  /** Loads a lot name display modality */
  private void loadLotNameDisplay (StreamTokenizer st, JikanList lots,
                                   Schedulable sched)
                                  throws IOException
  {
    if (lots.size () == 0)
      abort (sched.name (), MODULE_LOT_NAME_DISPLAY, st.lineno ());
    ((Lot) (lots.last ())).displayName (true);
    st.nextToken ();
  }

  /** Loads a lot module name hiding modality */
  private void loadLotModuleHide (StreamTokenizer st, JikanList lots,
                                   Schedulable sched)
                                  throws IOException
  {
    if (lots.size () == 0)
      abort (sched.name (), MODULE_LOT_MODULE_HIDE, st.lineno ());
    ((Lot) (lots.last ())).moduleDisplayName (false);
    st.nextToken ();
  }

  /** Loads a task lot counting */
  private void loadLotCounterDisplay (StreamTokenizer st, JikanList lots,
                                      Schedulable sched)
                                     throws IOException
  {
    if (lots.size () == 0)
      abort (sched.name (), MODULE_LOT_COUNTER_DISPLAY, st.lineno ());
    ((Lot) (lots.last ())).displayOrder (true);
    st.nextToken ();
  }

  /** Adds the list of required schedulables to the last task */
  private void addRequiresToLastTask (JikanList reqs, JikanList tasks)
  {
    ((Task) (tasks.last ())).setRequisites (reqs);
    reqs.empty ();
  }

  /** Adds the list of tasks to the last lot */
  private boolean addTasksToLastLot (JikanList tasks, JikanList lots)
  {
    if (((Task) (tasks.last ())).hasNoDuration ()) return (false);
    ((Lot) (lots.last ())).setTasks (tasks);
    tasks.empty ();
    return (true);
  }

  /** Creates a new task into the module being loaded */
  private void loadNewTask (StreamTokenizer st,
                            JikanList lots, JikanList tasks, JikanList reqs,
                            Schedulable sched)
                           throws IOException
  {
    st.nextToken ();
    if (st.ttype != StreamTokenizer.TT_WORD)
      abort (sched.name (), MODULE_NEW_LOT, st.lineno ());
    if (lots.size () == 0)
      abort (sched.name (), MODULE_NEW_TASK, st.lineno ());
    if (tasks.size () != 0)
    {
      if (((Task) (tasks.last ())).hasNoDuration ())
        abort (sched.name (), MODULE_NEW_TASK, st.lineno ());
      addRequiresToLastTask (reqs, tasks);
    }
    tasks.add (new Task (((Lot) (lots.last ()))));
    ((Task) (tasks.last ())).setName (st.sval);
    st.nextToken ();
  }

  /** Loads a task cost */
  private void loadTaskCost (StreamTokenizer st,
                             JikanList tasks, Schedulable sched)
                            throws IOException
  {
    if (tasks.size () == 0)
      abort (sched.name (), MODULE_TASK_COST, st.lineno ());
    st.nextToken ();
    if (st.ttype != StreamTokenizer.TT_WORD)
      abort (sched.name (), MODULE_TASK_COST, st.lineno ());
    if (! ((Task) (tasks.last ())).setCost (st.sval))
      abort (sched.name (), MODULE_TASK_COST, st.lineno ());
    st.nextToken ();
  }

  /** Loads a task duration */
  private void loadTaskDuration (StreamTokenizer st,
                                 JikanList tasks, Schedulable sched)
                                throws IOException
  {
    if (tasks.size () == 0)
      abort (sched.name (), MODULE_TASK_DURATION, st.lineno ());
    st.nextToken ();
    if (st.ttype != StreamTokenizer.TT_NUMBER)
      abort (sched.name (), MODULE_TASK_DURATION, st.lineno ());
    if (! ((Task) (tasks.last ())).addDuration ((int) st.nval))
      abort (sched.name (), MODULE_TASK_DURATION, st.lineno ());
    st.nextToken ();
  }

  /** Loads one task requirement */
  private void loadOneTaskRequire (StreamTokenizer st, int type,
                                   String action, JikanList reqs,
                                   Schedulable sched)
                                  throws IOException
  {
    st.nextToken ();
    JikanList list = new JikanList ();
    if (st.ttype != StreamTokenizer.TT_WORD)
      abort (sched.name (), action, st.lineno ());
    Schedulable required = data.find (type, st.sval);
    if (required == null) abort (sched.name (), action, st.lineno ());
    list.add (required);
    reqs.add (list);
    st.nextToken ();
  }

  /** Loads task requirements */
  private void loadTaskRequires (StreamTokenizer st, int type,
                                 String action, JikanList reqs,
                                 Schedulable sched)
                                throws IOException
  {
    st.nextToken ();
    if (st.ttype != StreamTokenizer.TT_NUMBER)
      abort (sched.name (), action, st.lineno ());
    int nb = (int) st.nval;
    if (nb <= 0) abort (sched.name (), action, st.lineno ());
    JikanList list = new JikanList ();
    for (int i = 0; i < nb; i++)
    {
      st.nextToken ();
      if (st.ttype != StreamTokenizer.TT_WORD)
        abort (sched.name (), action, st.lineno ());
      Schedulable required = data.find (type, st.sval);
      if (required == null) abort (sched.name (), action, st.lineno ());
      list.add (required);
    }
    reqs.add (list);
    st.nextToken ();
  }

  /** Loads a task repetition */
  private void loadTaskRepetition (StreamTokenizer st, JikanList tasks,
                                   Schedulable sched)
                                  throws IOException
  {
    if (tasks.size () == 0)
      abort (sched.name (), MODULE_TASK_REPETITION, st.lineno ());
    st.nextToken ();
    if (st.ttype != StreamTokenizer.TT_NUMBER)
      abort (sched.name (), MODULE_TASK_REPETITION, st.lineno ());
    int repetition = (int) st.nval;
    if (repetition <= 0)
      abort (sched.name (), MODULE_TASK_REPETITION, st.lineno ());
    ((Task) (tasks.last ())).repeat (repetition);
    st.nextToken ();
  }

  /** Programm interruption in case of format problem */
  private void abort (String name, String beacon, int lineno)
  {
    System.out.println (name + ", ligne " + lineno
                        + ": mauvais format pour " + beacon);
    System.exit (0);
  }

  /** Loads already programmed slots */
  private void loadSlots ()
  {
    // Check slots directory
    File planingDir = new File (dataDir, planingDirName);
    if (! planingDir.exists ()) return;

    // Loads module groups directories
    File[] groupDirs = planingDir.listFiles ();
    for (int i = 0; i < groupDirs.length; i++)
    {
      int group = data.getGroup (Schedulable.TYPE_MODULE,
                                 groupDirs[i].getName ());
      if (group == -1)
      {
        System.out.println ("Module group " + groupDirs[i].getName ()
                            + " unknown");
	System.exit (0);
      }

      // Loads module files
      File[] moduleFiles = groupDirs[i].listFiles ();
      for (int j = 0; j < moduleFiles.length; j++)
	loadModuleSlots (moduleFiles[j], group);
    }
  }

  /** Loads module slots from the given file */
  private void loadModuleSlots (File moduleFile, int group)
  {
    String fileName = moduleFile.getName ();
    Schedulable sched = data.find (Schedulable.TYPE_MODULE, group,
           fileName.substring (0, fileName.length () - FILE_SUFFIX.length ()));
    if (sched == null)
    {
      System.out.println (moduleFile.getName () + " module unknown");
      System.exit (0);
    }
    Module module = (Module) sched;
    try
    {
      StreamTokenizer st = new StreamTokenizer (new FileReader (moduleFile));
      st.nextToken ();
      while (st.ttype != StreamTokenizer.TT_EOF)
      {
        if (st.ttype != StreamTokenizer.TT_WORD)
          abort (sched.name (), "un slot (1)", st.lineno ());
        Lot lot = module.getLot (st.sval);
        if (lot == null) abort (sched.name (), "le lot", st.lineno ());
        st.nextToken ();
        if (st.ttype != StreamTokenizer.TT_WORD)
          abort (sched.name (), "un slot (2)", st.lineno ());
        Task task = lot.getTask (st.sval);
        if (task == null) abort (sched.name (), "la tache", st.lineno ());
        int numSlot = task.firstFreeSlot ();
        if (numSlot == -1) abort (sched.name (), "manque de slot", st.lineno ());
        st.nextToken ();
        if (st.ttype != StreamTokenizer.TT_WORD)
          abort (sched.name (), "un slot (4)", st.lineno ());
	int week = data.calendar().weekIndex (
                     (new Integer (st.sval.substring (1))).intValue ());
        if (week < 0) abort (sched.name (), "la semaine", st.lineno ());
        st.nextToken ();
        if (st.ttype != StreamTokenizer.TT_NUMBER)
          abort (sched.name (), "un slot (5)", st.lineno ());
        int day = (int) st.nval;
        if (day < 0) abort (sched.name (), "le jour", st.lineno ());
        st.nextToken ();
        if (st.ttype != StreamTokenizer.TT_NUMBER)
          abort (sched.name (), "un slot (6)", st.lineno ());
        int start = (int) st.nval;
        if (start < 0) abort (sched.name (), "le moment", st.lineno ());
        st.nextToken ();
        if (st.ttype != StreamTokenizer.TT_WORD)
          abort (sched.name (), "un slot (7)", st.lineno ());
	int nbItems = new Integer (st.sval.substring (2)).intValue ();
        Schedulable[][] reqs = task.requisites ();
        if (nbItems != reqs.length)
          abort (sched.name (), "le nombre de contraintes", st.lineno ());
        int[] choice = new int[nbItems];
        for (int i = 0; i < choice.length; i++)
        {
          st.nextToken ();
          if (st.ttype != StreamTokenizer.TT_WORD)
            abort (sched.name (), "un slot", st.lineno ());
	  choice[i] = task.identifyRequisite (i, st.sval);
          if (choice[i] < 0 || choice[i] >= reqs[i].length)
            abort (sched.name (), "la contrainte " + i, st.lineno ());
        }
        task.setSlot (numSlot, week, day, start, choice);
        st.nextToken ();
      }
    }
    catch (IOException e)
    {
      System.out.println (moduleFile.getName () + " file damaged");
      System.exit (0);
    }
  }

  /** Saves the programmed slots in the slots directory */
  public void save ()
  {
    // Safety former slots preserving
    File planingDir = new File (dataDir, planingDirName);
    File tmpSaveDir = null;
    if (planingDir.exists ())
    {
      tmpSaveDir = new File (dataDir, tmpDirName);
      planingDir.renameTo (tmpSaveDir);
    }
 
    // Creates a new slots directory
    planingDir.mkdir ();

    // Creates each module group directory
    String[] groupNames = data.getModuleGroupsNames ();
    for (int i = 0; i < groupNames.length; i++)
    {
      File groupDir = new File (planingDir, groupNames[i]);
      groupDir.mkdir ();

      // Creates each module file
      Schedulable[] modules = data.getSchedulables (Schedulable.TYPE_MODULE, i);
      for (int j = 0; j < modules.length; j++)
      {
        File moduleFile = new File (groupDir, modules[j].name () + FILE_SUFFIX);
        PrintWriter pw = null;
        try
        {
          moduleFile.createNewFile ();
          pw = new PrintWriter (moduleFile);
        }
        catch (IOException exc)
        {
          System.out.println ("Unable to create " + modules[j].name ()
                              + " (" + groupNames[i] + ")");
          System.exit (0);
        }

        // Registers each lot
        Lot[] lots = ((Module) modules[j]).lots ();
        for (int k = 0; k < lots.length; k++)
        {
          // Registers each task
          Task[] tasks = lots[k].tasks ();
          for (int l = 0; l < tasks.length; l++)
          {
            // Registers each slot
            Slot[] slots = tasks[l].slots ();
            for (int m = 0; m < slots.length; m++)
              if (slots[m] != null)
              {
                int[] choice = slots[m].choice ();
                String poestry = lots[k].name () + " " + tasks[l].name ()
                         + " S" + data.calendar ().weekNo (slots[m].week ())
                         + " " + slots[m].day () + " " + slots[m].start ()
                         + " " + "it" + choice.length;
		for (int n = 0; n < choice.length; n++)
                  poestry += " " + tasks[l].requisite(n,choice[n]).name ();
                pw.println (poestry);
              }
          }
        }
        pw.close ();
      }
    }

    // Removal of now useless temporary directory
    File[] tmpDir = tmpSaveDir.listFiles ();
    if (tmpDir != null)
    {
      for (int i = 0; i < tmpDir.length; i++)
      {
        File[] tmpFile = tmpDir[i].listFiles ();
        if (tmpFile != null)
          for (int j = 0; j < tmpFile.length; j++) tmpFile[j].delete ();
        tmpDir[i].delete ();
      }
    }
    tmpSaveDir.delete ();
  }
 
  /** Finds a loaded teacher from its name */
  private Teacher find (String name)
  {
    for (int i = 0; i < groupables.size (); i ++)
      if (((Teacher) (groupables.get (i))).name().equals (name))
        return ((Teacher) (groupables.get (i)));
    return (null);
  }
}

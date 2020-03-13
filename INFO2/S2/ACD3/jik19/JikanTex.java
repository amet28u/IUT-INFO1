import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.awt.Color;


/** LaTeX Editor for Jikan schedules.
  * @author Philippe Even
  */
public class JikanTex
{
  /** Tex outputs directory name */
  private final static String TEX_DIR_PREFIX = "edt";
  /** Tex duties directory name */
  private final static String TEX_DUTIES_DIR_NAME = "duties";
  /** Tex duties file name */
  private final static String TEX_DUTIES_FILE_NAME = "duties";

  /** Width of the time title column */
  private final static float TIME_WIDTH = 1.4f;
  /** Width of a day column */
  private final static float DAY_WIDTH = 4.0f;
  /** Thickness between day columns */
  private final static float NIGHT_WIDTH = 0.2f;
  /** Height of a slot */
  private final static float PERIOD_HEIGHT = 0.75f;
  /** Height of the day line */
  private final static float DAY_HEIGHT = 0.4f;
  /** Height of the week line */
  private final static float WEEK_HEIGHT = 0.4f;
  /** Height of a line of text */
  private final static float TEXT_LINE_HEIGHT = 0.4f;

  /** Color for non working slots display */
  private final static float[] STYLE_SYNDIC_COLOR = {0.9f, 0.9f, 0.7f};
  /** Style for non working slots display */
  private final static String STYLE_SYNDIC =
    "{fillstyle=solid,fillcolor=jaunasse,linecolor=jaunasse}";
  /** Style for light greyed zones */
  private final static String STYLE_LIGHTGRAY =
    "{fillstyle=solid,fillcolor=lightgray,linecolor=lightgray}";

  /** Present calendar */
  private JikanCalendar cal = null;
  /** Number of slots per day */
  private int gridLineCount = 1;
  /** Number of days in the week */
  private int gridColumnCount = 1;
  /** Height of the slots grid */
  private float gridHeight = PERIOD_HEIGHT;
  /** Height of the drawing */
  private float totalHeight = PERIOD_HEIGHT + DAY_HEIGHT + WEEK_HEIGHT;
  /** Width of the drawing */
  private float totalWidth = TIME_WIDTH + DAY_WIDTH;

  /** First week */
  private int firstWeek = 0;
  /** Last week */
  private int lastWeek = 0;
  /** Displayed schedulable */
  private Schedulable sched = null;
  /** Output file */
  private PrintWriter tex = null;
  /** Format for real numbers */
  private DecimalFormat df = null;
  /** Format for colors */
  private DecimalFormat dfcol = null;


  /** Creates and opens a tex output file */
  private JikanTex () throws IOException
  {
    df = new DecimalFormat ("0.0");
    DecimalFormatSymbols dfs = new DecimalFormatSymbols ();
    dfs.setDecimalSeparator ('.');
    df.setDecimalFormatSymbols (dfs);
  }

  /** Creates and opens a tex calendar output file */
  public JikanTex (Schedulable sched, JikanCalendar cal) throws IOException
  {
    this.sched = sched;
    this.cal = cal;
    gridLineCount = cal.slotTimes().length;
    gridHeight = gridLineCount * PERIOD_HEIGHT;
    totalHeight = gridHeight + DAY_HEIGHT + WEEK_HEIGHT;
    df = new DecimalFormat ("0.#");
    dfcol = new DecimalFormat ("0.##");
    DecimalFormatSymbols dfs = new DecimalFormatSymbols ();
    dfs.setDecimalSeparator ('.');
    df.setDecimalFormatSymbols (dfs);
    dfcol.setDecimalFormatSymbols (dfs);

    // Opens the TeX file
    File texDir = new File (TEX_DIR_PREFIX + cal.presentDayCode ());
    if (! texDir.exists ()) texDir.mkdir ();
    File texFile = new File (texDir, sched.name () + ".tex");
    if (texFile.exists ()) texFile.delete ();
    else texFile.createNewFile ();
    tex = new PrintWriter (texFile);

    // Creates the TeX header
    tex.println ("\\documentclass[francais,a4,11pt]{article}");
    tex.println ("\\usepackage[francais]{babel}");
    tex.println ("\\usepackage{pst-all}");
    tex.println ("\\usepackage{graphics}");
    tex.println ("\\usepackage{color}");
    tex.println ("\\setlength{\\unitlength}{1.0mm}");
    tex.println ("\\textwidth 27.0cm");
    tex.println ("\\textheight 17.5cm");
    tex.println ("\\topmargin -1.0cm");
    tex.println ("\\headheight 0.0cm");
    tex.println ("\\headsep 0.0cm");
    tex.println ("\\oddsidemargin -1.0cm");
    tex.println ("\\evensidemargin -1.0cm");
    tex.println ("\\pagestyle{empty}");
    tex.println ("");
    tex.println ("\\begin{document}");
    tex.println ("\\scriptsize \\bf");
    tex.println ("\\definecolor{jaunasse}{rgb}{" + STYLE_SYNDIC_COLOR[0] + ","
                 + STYLE_SYNDIC_COLOR[1] + "," + STYLE_SYNDIC_COLOR[2] + "}");
    tex.println ("  \\newpsstyle{syndic}" + STYLE_SYNDIC);
    tex.println ("  \\newpsstyle{lgray}" + STYLE_LIGHTGRAY);
  }

  /** Creates and opens a duties output file */
  public static JikanTex createDuties (JikanCalendar cal) throws IOException
  {
    JikanTex jt = new JikanTex ();
    jt.cal = cal;

    // Opens the TeX file
    File texDir = new File (TEX_DUTIES_DIR_NAME + cal.presentDayCode ());
    if (! texDir.exists ()) texDir.mkdir ();
    File texFile = new File (texDir, TEX_DUTIES_FILE_NAME + ".tex");
    if (texFile.exists ()) texFile.delete ();
    else texFile.createNewFile ();
    jt.tex = new PrintWriter (texFile);

    // Creates the TeX header
    jt.tex.println ("\\documentclass[francais,a4,11pt]{article}");
    jt.tex.println ("\\usepackage[francais]{babel}");
    jt.tex.println ("\\setlength{\\unitlength}{1.0mm}");
    jt.tex.println ("\\textwidth 16.0cm");
    jt.tex.println ("\\textheight 25.5cm");
    jt.tex.println ("\\topmargin -1.0cm");
    jt.tex.println ("\\headheight 0.0cm");
    jt.tex.println ("\\headsep 0.0cm");
    jt.tex.println ("\\oddsidemargin -1.0cm");
    jt.tex.println ("\\evensidemargin -1.0cm");
    jt.tex.println ("\\pagestyle{empty}");
    jt.tex.println ("");
    jt.tex.println ("\\begin{document}");
    jt.tex.println ("\\begin{center}");
    jt.tex.println ("\\LARGE \\bf {Services programm\\'es au "
                    + cal.presentDayName () + "}");
    jt.tex.println ("\\end{center}");

    return (jt);
  }

  /** Creates and opens a teacher duties file */
  public JikanTex createTeacherDuties (String tname, String tinfo)
                                throws IOException
  {
    JikanTex jt = new JikanTex ();

    // Opens the TeX file
    File texDir = new File (TEX_DUTIES_DIR_NAME + cal.presentDayCode ());
    if (! texDir.exists ()) texDir.mkdir ();
    File texFile = new File (texDir, tname + ".tex");
    if (texFile.exists ()) texFile.delete ();
    else texFile.createNewFile ();
    jt.tex = new PrintWriter (texFile);

    // Creates the TeX header
    jt.tex.println ();
    jt.tex.println ("\\begin{tabular}{|l||r|r|r||r|}");
    jt.tex.println ("\\hline");
    jt.tex.println (tinfo + " & CM & TD & TP & bp (eqTD) \\\\");
    jt.tex.println ("\\hline");

    return (jt);
  }

  /** Adds a reference to a duties file */
  public void inputDuties (String tname)
  {
    tex.println ("\n\\input{" + tname+ "} \\\\");
  }

  public void add (String tname, int[] costs, float etd)
  {
    tex.print (tname);
    for (int i = 1; i < costs.length; i++)
      tex.print (" & " + (((float) costs[costs.length - i]) / 2));
    tex.println (" & " + df.format (etd / 2) + " \\\\");
  }

  public void otherTeacherDuties ()
  {
    tex.println ("\\hline");
  }

  public void closeTeacherDuties (int[] totals, float etd) throws IOException
  {
    for (int i = 1; i < totals.length; i++)
      tex.print (" & " + (((float) totals[totals.length - i]) / 2));
    tex.println (" & " + df.format (etd) + " \\\\");
    tex.println ("\\hline");
    tex.println ("\\end{tabular}");
    tex.println ("\\vspace{0.5cm}");
    tex.close ();
  }

  public void setWeeks (int first, int last)
  {
    firstWeek = first;
    lastWeek = lastWeek;
  }

  public void write ()
  {
    for (int week = firstWeek; week <= lastWeek; week ++)
      write (week);
  }

  public void write (int week)
  {
    gridColumnCount = cal.weekDays()[week].length;
    totalWidth = TIME_WIDTH - NIGHT_WIDTH
                 + gridColumnCount * (DAY_WIDTH + NIGHT_WIDTH);
    writeBackground ();
    writeSyndicalTimes (week);
    writeSchedulableName ();
    writeTasks (week);
    writeDayTitles (week);
    writeForeground ();
  }

  private void writeBackground ()
  {
    // Creates a new picture environment
    tex.println ("");
    tex.println ("\\begin{pspicture}(0.0,0.0)("
                 + df.format (totalWidth) + ","
                 + df.format (totalHeight) + ")");

    // Colors the title bar
    tex.println ("  % Light gray backgrounds and slot lines");
    tex.println ("  \\psframe[style=lgray](" + df.format (TIME_WIDTH) + ","
                 + df.format (gridHeight) +")(" + df.format (totalWidth) + ","
                 + df.format (totalHeight) + ")");

    // Colors the time column
    tex.println ("  \\psframe[style=lgray](0,0)("
                 + df.format (TIME_WIDTH) + "," + df.format (gridHeight) + ")");

    // Adds the slot lines and titles
    float posy = gridHeight - PERIOD_HEIGHT;
    String[] sTimes = cal.slotTimes ();
    for (int i = 1; i < gridLineCount; i ++)
    {
      tex.println ("  \\psline(" + df.format (TIME_WIDTH * 0.8f) + ","
                   + df.format (posy) + ")(" + df.format (TIME_WIDTH) + ","
                   + df.format (posy) + ")");
      tex.println ("  \\psline[linestyle=dashed](" + df.format (TIME_WIDTH)
                   + "," + df.format (posy) + ")(" + df.format (totalWidth)
                   + "," + df.format (posy) + ")");
      tex.println ("  \\rput[r](" + df.format (TIME_WIDTH * 0.75f) + ","
                   + df.format (posy) + "){" + sTimes[i] + "}");
      posy -= PERIOD_HEIGHT;
    }

    // Colors the columns between days
    float posx = TIME_WIDTH + DAY_WIDTH;
    for (int i = 0; i < gridColumnCount - 1; i ++)
    {
      tex.println ("  \\psframe[style=lgray](" + df.format (posx) + ",0)("
                   + df.format (posx + NIGHT_WIDTH) + ","
                   + df.format (gridHeight) + ")");
      posx += NIGHT_WIDTH + DAY_WIDTH;
    }
  }

  private void writeForeground ()
  {
    // Adds the vertical lines
    tex.println ("\n  % Grid frame lines");
    float posx = TIME_WIDTH + DAY_WIDTH;
    for (int i = 0; i < gridColumnCount - 1; i ++)
    {
      tex.println ("  \\psline(" + df.format (posx) + ",0)(" + df.format (posx)
                   + "," + df.format (gridHeight + DAY_HEIGHT) + ")");
      posx += NIGHT_WIDTH;
      tex.println ("  \\psline(" + df.format (posx) + ",0)(" + df.format (posx)
                   + "," + df.format (gridHeight + DAY_HEIGHT) + ")");
      posx += DAY_WIDTH;
    }
    tex.println ("  \\psline(" + df.format (TIME_WIDTH) + ",0)("
            + df.format (TIME_WIDTH) + "," + df.format (totalHeight) + ")");
 
    // Adds the horizontal lines
    float posy = gridHeight;
    posx = TIME_WIDTH + DAY_WIDTH + NIGHT_WIDTH;
    tex.println ("  \\psline(0," + df.format (posy) + ")("
                 + df.format (TIME_WIDTH + DAY_WIDTH) + ","
                 + df.format (posy) + ")");
    for (int i = 1; i < gridColumnCount; i ++)
    {
      tex.println ("  \\psline(" + df.format (posx) + "," + df.format (posy)
                   + ")(" + df.format (posx + DAY_WIDTH) + ","
                   + df.format (posy) + ")");
      posx += DAY_WIDTH + NIGHT_WIDTH;
    }
    posy += DAY_HEIGHT;
    tex.println ("  \\psline(" + df.format (TIME_WIDTH) + ","
                 + df.format (posy) + ")(" + df.format (totalWidth)
                 + "," + df.format (posy) + ")");

    // Adds a frame
    tex.println ("  \\psframe(0,0)(" + df.format (totalWidth)
                 + "," + df.format (totalHeight) + ")");

    // Closes the picture environment
    tex.println ("\\end{pspicture}");
  }

  private void writeDayTitles (int week)
  {
    float posx = TIME_WIDTH + DAY_WIDTH / 2;
    float posy = gridHeight + DAY_HEIGHT / 2;
    tex.println ("\n  % Days, week and version");
    String[][] wDays = cal.weekDays ();
    for (int i = 0; i < gridColumnCount; i ++)
    {
      tex.println ("  \\rput(" + df.format (posx) + "," + df.format (posy)
                   + "){" + wDays[week][i] + "}");
      posx += DAY_WIDTH + NIGHT_WIDTH;
    }
    posy += (DAY_HEIGHT + WEEK_HEIGHT) / 2;
    posx = (TIME_WIDTH + totalWidth) / 4;
    tex.println ("  \\rput(" + df.format (posx) + "," + df.format (posy)
                 + "){Semaine " + cal.weekNo (week) + "}");
    posx = (TIME_WIDTH + totalWidth) * 0.75f;
    tex.println ("  \\rput(" + df.format (posx) + "," + df.format (posy)
                 + "){Version du " + cal.presentDayName () + "}");
  }

  private void writeSchedulableName ()
  {
    tex.println ("\n  % Schedule name");
    float[] col = sched.color().getRGBColorComponents (null);
    tex.println ("  \\newrgbcolor{colsched}{" + dfcol.format (col[0])
                 + " " + dfcol.format (col[1]) + " "
                 + dfcol.format (col[2]) + "}");
    tex.println (
      "  \\psframe[fillstyle=solid,fillcolor=colsched,linecolor=colsched]%");
    tex.println ("           (0," + df.format (gridHeight) + ")("
            + df.format (TIME_WIDTH) + "," + df.format (totalHeight) + ")");
    tex.println ("  \\rput(" + df.format (TIME_WIDTH / 2) + ","
                 + df.format ((totalHeight + gridHeight) / 2) + "){"
                 + sched.name () + "}");
  }

  private void writeSyndicalTimes (int week)
  {
    // Fills the non working slots
    tex.println ("\n  % Non working slots areas");
    boolean occupied = false;
    float posy, posx = TIME_WIDTH;
    for (int i = 0; i < gridColumnCount; i++)
    {
      posy = gridHeight;
      occupied = ! cal.occ.isFreeFirst (week, i);
      for (int j = 0; j < gridLineCount; j++)
      {
        if (occupied)
        {
          tex.println ("  \\psframe[style=syndic]("
            + df.format (posx) + "," + df.format (posy - PERIOD_HEIGHT) + ")("
            + df.format (posx + DAY_WIDTH) + "," + df.format (posy) + ")");
        }
        posy -= PERIOD_HEIGHT;
        occupied = ! cal.occ.isFreeNext ();
      }
      posx += DAY_WIDTH + NIGHT_WIDTH;
    }
  }

  private void writeTasks (int week)
  {
    JikanList slots = sched.getSlots (week);
    if (slots == null) return;
    int colorCount = 0; // color counter
    int div = sched.partitionCount ();
    int[][] studentZone = {{0,1}};
    tex.println ("\n  % Tasks");
    for (int i = 0; i < slots.size (); i ++)
    {
      Slot slot = (Slot) slots.get (i);
      if (slot.isPrintable ())
      {
        String[] text = slot.text (sched.type ());
        int[] timeZone = slot.timeZone ();
        if (div != 1) studentZone = slot.studentZone ((Student) sched);

        float posx = TIME_WIDTH + (DAY_WIDTH + NIGHT_WIDTH) * slot.day ();
        float posy = gridHeight - PERIOD_HEIGHT * timeZone[0];
        float height = PERIOD_HEIGHT * timeZone[1];
        float[] color = slot.color(sched.type ()).getRGBColorComponents (null);
        tex.println ("  \\newrgbcolor{col" + ++colorCount + "}{"
                     + dfcol.format (color[0]) + " " + dfcol.format (color[1])
                     + " " + dfcol.format (color[2]) + "}");
        for (int j = 0; j < studentZone.length; j ++)
        {
          float posx2 = posx + studentZone[j][0] * DAY_WIDTH / div;
          float width = studentZone[j][1] * DAY_WIDTH / div;
          tex.println ("  \\psframe[fillstyle=solid,fillcolor=col"
                       + colorCount + "]("
                       + df.format (posx2) + "," + df.format (posy) + ")("
                       + df.format (posx2 + width) + ","
                       + df.format (posy + height) + ")");
          if (text != null)
          {
            float posy2 = posy
                    + (height + (text.length - 0.5f) * TEXT_LINE_HEIGHT) / 2;
            for (int k = 0; k < text.length; k ++)
            {
              tex.println ("  \\rput(" + df.format (posx2 + width / 2) + ","
                           + df.format (posy2) + "){" + text[k] + "}");
              posy2 -= TEXT_LINE_HEIGHT;
            }
          }
        }
      }
    }
  }

  /** Terminates and closes the tex output file */
  public void terminate ()
  {
    tex.println ("\\end{document}");
    tex.close ();
  }
}

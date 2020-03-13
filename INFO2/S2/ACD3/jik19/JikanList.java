/** Custom list of objects for Jikan applications.
  * @author Philippe Even
  */
public class JikanList
{
  private final static int DEFAULT_SIZE = 10;

  private Object[] elts = null;
  private int count = 0;


  /** Constructs an empty list with default capacity. */
  public JikanList ()
  {
    elts = new Object[DEFAULT_SIZE];
  }

  /** Constructs an empty list with given capacity. */
  public JikanList (int size)
  {
    elts = new Object[size];
  }

  /** Clears the list. */
  public void empty ()
  {
    count = 0;
  }

  /** Adds an object to the list. */
  public void add (Object elt)
  {
    if (count == elts.length) doubleCapacity ();
    elts[count++] = elt;
  }

  /** Augments the list capacity by a factor 2. */
  private void doubleCapacity ()
  {
    Object[] tab = new Object[elts.length * 2];
    for (int i = 0; i < count; i++) tab[i] = elts[i];
    elts = tab;
  }

  /** Returns the length of the list. */
  public int size ()
  {
    return (count);
  }

  /** Removes an object from the list. */
  public void remove (Object elt)
  {
    for (int i = 0; i < count; i ++)
      if (elt == elts[i])
      {
        elts[i] = elts[--count];
	i = count + 1;
      }
  }

  /** Fills in the given array with the list elements. */
  public void fill (Object[] objects)
  {
    for (int i = 0; i < count; i++)
      objects[i] = elts[i];
  }

  /** Returns the list element at given index. */
  public Object get (int index)
  {
    return (elts[index]);
  }

  /** Returns the last element of the list. */
  public Object last ()
  {
    return (elts[count - 1]);
  }

  /** Returns the list elements in a new array. */
  public Object[] toArray ()
  {
    Object[] obj = new Object[count];
    for (int i = 0; i < count; i++) obj[i] = elts[count];
    return (obj);
  }
}

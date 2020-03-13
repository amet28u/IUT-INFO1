public class Personne{
  private String nom;
  public Personne(String nom){
    this.nom = nom;
  }
  public String getNom(){
    return nom;
  }
}

public class Philosophe extends Personne{
  private Disciple d;
  public Philosophe(String nom){
    super(nom);
  }
  public Disciple getDisciple(){
    return d;
  }
  public String toString(){
    return "Disciple";
  }
}

public class Disciple extends Personne{
  private Philosophe p;
  public Disciple(String nom){
    super(nom);
  }
  public Philosophe getPhilosophe(){
    return p;
  }
  public String toString(){
    return "Philosophe";
  }
}

public class Test{
  public static void main(String[] args){
    Philosophe s = new Philosophe("Socrate");
    Philosophe p = new Philosophe("Platon");
    Disciple a = new Disciple("Aristote");
    Disciple x = new Disciple("Xénophon");
    Disciple t = new Disciple("Toto");
    System.out.println(s);

  }
}

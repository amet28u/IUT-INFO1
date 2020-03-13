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
  public Philosophe(String nom, Disciple d){
    super(nom);
    this.d = d;
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
  public Disciple(String nom, Philosophe p){
    super(nom);
    this.p = p;
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

  }
}

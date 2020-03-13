public class Disciple extends Personne{
  private Philosophe p;
  public Disciple(String nom){
    super(nom);
  }
  public Philosophe getPhilosophe(){
    return p;
  }
  public String toString(){
    return "Disciple " + getNom();
  }
}

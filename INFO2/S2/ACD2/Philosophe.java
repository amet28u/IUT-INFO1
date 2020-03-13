public class Philosophe extends Personne{
  private static final int TAILLE_MAX = 50;
  private Personne[] p = new Personne[TAILLE_MAX];
  private int cpt = 0;
  public Philosophe(String nom){
    super(nom);
  }
  public Personne getDisciple(int i){
    return p[i];
  }
  public void addDisciple(Personne pers){
    if (cpt == TAILLE_MAX) System.out.println("Tableau déjà rempli !");
    p[cpt] = pers;
    cpt++;
  }
  public String toString(){
    return "Philosophe " + getNom();
  }
}

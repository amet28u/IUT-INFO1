package exercice_deux;

/** Classe représentant une lampe. */
public class Lampe{

  /** Nom de la lampe. */
  private String nom;
  /** Etat de la lampe. */
  public boolean allume;

  /** Constructeur principal d'une lampe. */
  public Lampe(String nom){
    this.nom = nom;
    allume = false;
  }

  /** Allume la lampe si elle est éteinte. */
  public void allumer(){
    if (!allume){
      allume = true;
    }
  }

  /** Eteind la lampe si elle est allumée. */
  public void eteindre(){
    if (allume){
      allume = false;
    }
  }

  /** Affiche la description d'une lampe. */
  public String toString(){
    return ("Lampe "+nom+" : "+(allume?"allumée":"éteinte"));
  }
}

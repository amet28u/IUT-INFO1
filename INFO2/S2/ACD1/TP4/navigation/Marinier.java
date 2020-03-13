// Fichier Marinier.java
// Auteur : LORENZELLI ANTONY
// Contexte : TP4 ACD1
// Date : 12/03/2019

package navigation;
import voyage.Voyageur;
import voyage.Lieu;

/** Définition d'un marinier par son port d'attache. */
public class Marinier extends Voyageur{
  /** Constante pour tester la joie du marinier. */
  private final int SCORE = 3;
  /** Constructeur avec le port d'attache. */
  public Marinier(Port attache){
    super(attache, attache);
  }
  /** Test de la joie du marinier. */
  public boolean joyeux(){
    if (super.score() > SCORE) return (true);
    return (false);
  }
  /** Polymorphisme pour marinier de ajouterEtape. */
  public void ajouterEtape(Lieu etape){
    // Le lieu est un port
    if (etape instanceof Port){
      // Il reste de la place dans le tableau
      if (!(super.score() == super.maxEtapes())){
        // On ajoute l'étape
        super.etapes[super.score() + 1] = etape;
      }
    }
  }
  /** Polymorphisme pour marinier de changerEtape. */
  public void changerEtape(int num, Lieu etape){
    // Le lieu est un port
    if (etape instanceof Port){
      // num est pas plus grand que la taille du tableau
      if (num <= super.maxEtapes()){
        super.etapes[num] = etape;
      }
    }
  }
  /** Polymorphisme pour marinier de retirerEtape. */
  public void retirerEtape(int num){
    if (num <= super.maxEtapes()){
      super.etapes[num] = (Lieu)null;
    }
  }
}

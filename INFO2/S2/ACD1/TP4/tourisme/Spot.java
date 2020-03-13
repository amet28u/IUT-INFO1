// Fichier Spot.java
// Auteur : LORENZELLI ANTONY
// Contexte : TP4 ACD1
// Date : 12/03/2019

package tourisme;
import voyage.Lieu;

public class Spot extends Lieu{
  /** Constante du min d'étoiles d'un spot. */
  private final int MIN = 1;
  /** Constante du max d'étoiles d'un spot. */
  private final int MAX = 4;
  /** Nombre d'étoiles d'un spot. */
  protected final int etoiles;
  /** Constructeur d'un spot par son nom et son nombre d'étoiles. */
  public Spot(String nom_lieu, int nb_etoiles){
      super(nom_lieu);
      etoiles = nb_etoiles;
  }

  public Spot(Lieu state){
    super(state.toString());
    etoiles = 1;
  }
  /** Méthode toString pour un spot. */
  public String toString(){
    String star;
    switch (etoiles){
      case 0:
        star = "";
        break;
      case 1:
        star = "*";
        break;
      case 2:
        star = "**";
        break;
      case 3:
        star = "***";
        break;
      case 4:
        star = "****";
        break;
      default:
        star = "";
    }
    return (super.toString()+star);
  }
  /** Méthode retournant le nb d'étoiles du spot. */
  public int score(){
    return (etoiles);
  }
  /** Méthode retournant le lieu quand le spot n'as pas d'étoiles. */
  public Lieu declassement(Spot lieu){
    if (lieu.etoiles == 0) return (new Lieu(super.toString()));
    return (Lieu)null;
  }
}

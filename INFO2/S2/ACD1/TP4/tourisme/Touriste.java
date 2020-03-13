// Fichier Touriste.java
// Auteur : LORENZELLI ANTONY
// Contexte : TP4 ACD1
// Date : 05/04/2019

package navigation;

import voyage.Voyageur;
import voyage.Lieu;

public class Touriste extends Voyageur{
  /** Tableau représentant les étapes du touriste. */
  private Lieu[] etapes;

  public Touriste(){
    etapes = new Lieu[0];
  }

  public int score(){
    return (etapes.length);
  }

  public boolean joyeux(){
    int cpt = 0;
    for (int i = 0; i < etapes.length; i++){
      if (etapes[i] instanceof Spot){
        cpt += etapes[i].score();
      }
    }
    if (cpt > etapes.length){
      return (true);
    }
    return (false);
  }

  public int endurance(){
    return (etapes.length);
  }
}

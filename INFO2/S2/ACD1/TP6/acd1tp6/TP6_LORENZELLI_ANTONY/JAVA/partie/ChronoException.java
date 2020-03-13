// Fichier HerosException.java
// Auteur : LORENZELLI ANTONY
// Contexte : TP6 ACD1 - Exceptions
// Date : 02/04/2019

package partie;

/** Issue de la partie : Héros sans vie. */
public class ChronoException extends JeuException{

  /** Convertit une HerosException en chaîne de caractère. */
  public String toString(){
    return ("Il n'y a plus de temps, le jeu est terminé !");
  }
}

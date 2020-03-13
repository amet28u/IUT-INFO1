// Fichier HerosException.java
// Auteur : LORENZELLI ANTONY
// Contexte : TP6 ACD1 - Exceptions
// Date : 02/04/2019

package partie;

/** Issue de la partie : Héros sans vie. */
public class HerosException extends JeuException{

  /** Convertit une HerosException en chaîne de caractère. */
  public String toString(){
    return ("Le héros n'a plus de vie, le jeu est terminé !");
  }
}

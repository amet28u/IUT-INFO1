// Fichier JeuException.java
// Auteur : LORENZELLI ANTONY
// Contexte : TP6 ACD1 - Exceptions
// Date : 02/04/2019

package partie;

/** Exception du jeu d'aventure. */
public abstract class JeuException extends Exception{

  /** Convertit l'issue de la partie en chaîne de caractères. */
  public String toString(){
    return ("Exception du jeu !");
  }
}

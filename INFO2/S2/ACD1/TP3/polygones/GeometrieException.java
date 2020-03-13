// Fichier PointsConfondusException.java
// Auteur : LORENZELLI ANTONY
// Contexte : TP6 ACD1 - 3. Exceptions dans le TP3
// Date : 02/04/2019

package polygones;

/** Problèmes de géométrie. */
public abstract class GeometrieException extends Exception{

  /** Convertit une GeometrieException en chaîne de caractère. */
  public String toString(){
    return ("Problèmes de constructions !");
  }
}

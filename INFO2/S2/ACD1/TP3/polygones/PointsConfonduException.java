// Fichier PointsConfondusException.java
// Auteur : LORENZELLI ANTONY
// Contexte : TP6 ACD1 - 3. Exceptions dans le TP3
// Date : 02/04/2019

package polygones;

/** Problèmes de construction : Points confondus. */
public class PointsConfondusException extends GeometrieException{

  /** Convertit une PointsConfondusException en chaîne de caractère. */
  public String toString(){
    return ("Points confondus !");
  }
}

// Trame des TP d'algebre lineaire : transformations geometriques
// DUT Informatique - 2018/2019
// Prepare par P. Even, Universite de Lorraine / IUT de Saint-Die
// Complete par : 
//
// Objectif : programer le deplacement de la cabine de telepherique
//   pour suivre le cable de sa position de depart a sa position d'arrivee.
//   Le mouvement est declenche par la barre d'espacement.
//
// Mise en oeuvre : completer la methode "nextStep" appelee en tache de
//   fond par l'application. Cette methode doit mettre dans le parametre
//   "pose" les positions successives de la cabine. Une position est codee
//   sous la forme d'une matrice homogene 4x4 avec une convention vecteur
//   colonne.
//   Le parametre "pose" vehicule les termes ligne par ligne de la matrice. 
//   Quand la methode "nextStep" retourne "FAUX", le mouvement s'arrete. 
//
// Repere de travail :
//   La verticale de la scene correspond a l'axe Z
//   L'axe X est oriente vers l'avant et l'axe Y vers la droite
// Repere de reference de la cabine : sommet de la perche
//   L'origine est le sommet de la perche 
//   L'avant de la cabine est dans la direction OY 
//
//   Geometrie de la scene :
//        Methodes d'instance de la classe RopewayScene
//   - float cableHeight () : hauteur du cable
//   - float[] cableStart () : position de depart du cable (2 floats)
//   - float cableLength () : longueur du cable
//   - int ropewaySpeed () : valeur recommandee pour l'increment de mouvement
//



// First exercice on geometric transforms
public class Exo1 extends RopewayScene
{
  /** Ropeway run distance */
  private float distance = 0.0f;


  /** Returns the next animation step pose.
    * @param pose Already allocated vector to fill in with new pose values.
    */
  public boolean nextStep (float[] pose)
  {
    // A MODIFIER
    pose[11] = 0.5f;
    return (false);
  }

  /** Constructs the 3D scene used for the present exercice.
    * Prepares the intermediate storage objects.
    */
  private Exo1 (int p1, String[] p2)
  {
    super (p1, p2);
  }


  /** Runs the exercice.
    */
  public static void main (String[] args)
  {
    new ExoFrame (new Exo1 (0, args), 1);
  }
}

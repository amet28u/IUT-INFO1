// J'appose ici mon en-tete de fichiers ...

import outils3d.Fenetre3D;
import outils3d.Affichable;


/** Application graphique. */
public class MonAppli
{
  /** Construit et affiche une scene 3D. */
  public static void main (String[] args)
  {
    Affichable scene = null; // a construire

    new Fenetre3D (scene);
  }
}

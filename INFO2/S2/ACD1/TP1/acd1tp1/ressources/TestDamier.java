// Fichier  : TestDamier.java
// Auteur   : P. Even (IUT de St Die, Dpt Info)
// Contexte : Utilitaire pour les TP d'ACD1

import java.awt.Color;


/** Test de la classe Damier. */
public class TestDamier
{
  public static void main (String[] args)
  {
    int taillex = 10, tailley = 20;
    boolean[][] grille = new boolean[tailley][taillex];
    Damier fr = new Damier ("La dame y est", tailley, taillex);
    Saisie s = new Saisie ();
    char carac = 'a';
    do
    {
      fr.effacer ();
      int valy = (int) (Math.random () * tailley);
      int valx = (int) (Math.random () * taillex);
      fr.colorier (valy, valx,
                   Color.BLUE);
System.out.println ("Affichage du pixel (" + valy + ", " + valx + ")");
      System.out.print ("Sortie ('q') ? ");
      carac = s.caractere ();
    }
    while (carac != 'q');
    System.exit (0);
  }
}

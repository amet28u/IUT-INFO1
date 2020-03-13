// Imagine par P. Even, IUT de St Die, pour un TP d'ACD1
// Complete par : ANTONY LORENZELLI

import partie.*;


/** Jeu d'aventure. */
public class Aventure
{
  /** Heros du jeu. */
  private Heros petitPatapon;


  /** Initialise un jeu d'aventure avec une limite de temps. */
  public Aventure (int dureeJeu)
  {
    petitPatapon = new Heros (dureeJeu);
  }

  /** Lance le jeu d'aventure. */
  public void jouer ()
  {
    try{
      boolean actif = true;
      while (actif)
      {
        switch (petitPatapon.action ())
        {
          case 'q' :
            actif = false;
            break;
          case 'e' :
            petitPatapon.entrainer ();
            break;
          case 'n' :
            petitPatapon.negocier ();
            break;
          case 's' :
            petitPatapon.ensorceller ();
            break;
          case 'a' :
            petitPatapon.attaquer ();
            break;
        }
        System.out.println (petitPatapon);
      }
    }
    catch(JeuException je){
      System.out.println(je);
    }
  }

  /** Lance le jeu d'aventure.  */
  public static void main (String[] args)
  {
      new Aventure (1000).jouer ();
  }
}

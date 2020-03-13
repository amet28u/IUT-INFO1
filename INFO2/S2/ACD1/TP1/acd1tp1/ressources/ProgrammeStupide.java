// Fichier  : ProgrammeStupide.java
// Auteur   : P. Even (IUT de St Die, Dpt Info)
// Contexte : Exemple errone pour le premier TP d'ACD1


/** Repertorier et rectifier les erreurs dans l'ordre d'apparition */
class Decouverte
{
  /** Exemple de booleen initialise a FAUX */
  boolean toutPair = false;
  /** Exemple de reel simple precision */
  public static float seuil = (float)1.76;
  /** Exemple de tableau d'entiers */
  int[] valeurs = {2, 0, 0, 2};


  /** Determine si le tableau ne comporte que des valeurs paires
    * et met a jour l'attribut toutPair */
  void verifierParite ()
  {
    int cpt = 0;
    toutPair = true;
    for (cpt = 0; toutPair && cpt < valeurs.length; cpt ++)
      if (valeurs[cpt] % 2 == 1) toutPair = false;
  }

  /** Retourne la moyenne des valeurs */
  double moyenne ()
  {
    int i = 0;
    long cumul = 0L;
    while (i < valeurs.length)
    {
      cumul += valeurs[i];
      i += 1;
    }
    return ((double)cumul / (double)i);
  }

  /** Verifie si la moyenne est elevee (par rapport au seuil) */
  boolean moyenneElevee ()
  {
    return (moyenne () > seuil);
  }

  /** Retourne les caracteristiques d'une instance de Decouverte */
  public String toString ()
  {
    String chaine = new String ("Donnees de moyenne ");
    if (! moyenneElevee ()) chaine = chaine + "peu ";
    return (chaine + "elevee");
  }


  /** Point d'entree pour cette classe */
  public static void main (String[] args)
  {
    // Creation d'une instance de la classe Decouverte
    Decouverte monObjet = new Decouverte ();
    monObjet.verifierParite ();

    // Affichage
    System.out.print (monObjet);
    if (! monObjet.toutPair) System.out.print (" pas");
    System.out.println (" toutes paires");

    // Retrait d'une valeur dans le tableau
    // il faut recreer un nouveau tableau
    int[] nouveauTableau = new int[3];
    for (int i = 0; i < 3; i++) nouveauTableau[i] = monObjet.valeurs[i];
    monObjet.valeurs = nouveauTableau;

    // Mise a jour de la parite
    monObjet.verifierParite ();

    // Affichage de la moyenne des valeurs
    System.out.print ("Moyenne (" + monObjet.valeurs[0]);
    for (int i = 1; i < monObjet.valeurs.length; i++)
      System.out.print ("," + monObjet.valeurs[i]);
    double m = monObjet.moyenne ();
    System.out.println (") = " + m);
    System.out.print ("Test ma moyenne = " + m + " : ");
    if (m > 0.67 || m < 0.66) System.out.println ("ERREUR");
    else System.out.println ("OK");

    // Creation d'un nouvel objet et affichage de sa moyenne
    Decouverte autreObjet = new Decouverte();
    m = autreObjet.moyenne ();
    System.out.print ("Test autre moyenne = " + m + " : ");
    if (m > 1.01 || m < 0.99) System.out.println ("ERREUR");
    else System.out.println ("OK");


    // Affichage de l'inverse de la deuxieme valeur
    autreObjet.seuil = 1.F / monObjet.valeurs[1];
    System.out.println ("Inverse n2 = " + autreObjet.seuil);
    System.out.println (autreObjet.seuil - autreObjet.seuil);
  }
}

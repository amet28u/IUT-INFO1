// Imagine par P. Even, IUT de St Die, pour un TP d'ACD1
// Complete par : ANTONY LORENZELLI

package partie;

import rencontre.Rencontre;
import rencontre.RencontreException;
import rencontre.HSException;
import rencontre.KOException;
import java.util.InputMismatchException;


/** Heros d'un jeu d'aventure. */
public class Heros
{
  /** Nombre maximal de points de vie. */
  private final static int MAX_VIE = 208;
  /** Nombre maximal d'actions a mener. */
  private final static int NB_MAX_ACTIONS = 100;
  /** Vie enlevée de 11. */
  private final int VIE_ATTAQUER_SUCCES = 11;
  /** Vie enlevée de 23. */
  private final int VIE_ATTAQUER_ECHEC = 23;
  /** Temps enlevé de 101. */
  private final int TEMPS_ATTAQUER_SUCCES = 101;
  /** Temps enlevé de 517. */
  private final int TEMPS_ATTAQUER_ECHEC = 517;
  /** Temps enlevé de 43. */
  private final int TEMPS_NEGOCIER = 43;
  /** Vie gagnée de 22. */
  private final int VIE_ENTRAINER = 22;
  /** Temps enlevé de 612. */
  private final int TEMPS_ENTRAINER_ECHEC = 612;
  /** Temps enlevé de 123. */
  private final int TEMPS_DANSER_ECHEC = 123;
  /** Vie enlevée de 23. */
  private final int VIE_DANSER = 23;
  /** Durée de 260. */
  private final int DUREE_ENSORCELLER = 260;
  /** Temps enlevé de 507. */
  private final int TEMPS_ENSORCELLER = 507;

  /** Nombre de points de vie du personnage. */
  private int vie = MAX_VIE;
  /** Compte a rebours. */
  private int tempsMission = 0;
  /** Gestionnaire de rencontres. */
  private Rencontre r = new Rencontre ();


  /** Cree un heros avec une duree limite de mission.
   * @param duree Duree limite de la mission.
   */
  public Heros (int duree)
  {
    tempsMission = duree;
  }

  /** Attaque un adversaire.
   */
  public void attaquer () throws JeuException
  {
    int arme = 0;

    try{
      arme = r.choisirArme();
    }
    catch(InputMismatchException ie){
      System.out.println("Entrez un chiffre !");
      vie -= VIE_ATTAQUER_SUCCES;
      tempsMission -= TEMPS_ATTAQUER_SUCCES;
    }

    try{
      tempsMission -= r.gererAttaque(arme);
      vie -= VIE_ATTAQUER_SUCCES;
    }
    catch(KOException koe){
      System.out.println(koe);
      vie -= VIE_ATTAQUER_ECHEC;
      tempsMission -= koe.duree();
    }
    catch(HSException hse){
      System.out.println(hse);
      vie -= hse.perte();
      tempsMission -= TEMPS_ATTAQUER_ECHEC;
    }
    catch(RencontreException re){
    }

    if (!enVie()){
      throw new HerosException();
    }
    if (!enTemps()){
      throw new ChronoException();
    }
  }

  /** Negocie avec un adversaire.
   */
  public void negocier () throws JeuException
  {
    tempsMission -= r.gererNegociation();
    vie -= TEMPS_NEGOCIER;

    if (!enVie()){
      throw new HerosException();
    }
    if (!enTemps()){
      throw new ChronoException();
    }
  }

  /** Procede a un entrainement.
   */
  public void entrainer () throws JeuException
  {
    if ((vie + VIE_ENTRAINER) > MAX_VIE){
      vie = MAX_VIE;
    }
    else{
      vie += VIE_ENTRAINER;
    }

    try{
      tempsMission -= r.gererAttaque(r.BATON);
    }
    catch(RencontreException re){
      tempsMission -= TEMPS_ENTRAINER_ECHEC;
    }

    if (!enVie()){
      throw new HerosException();
    }
    if (!enTemps()){
      throw new ChronoException();
    }
  }

  /** Execute une danse de combat pour impressionner l'adversaire.
   *  Retourne la duree de l'action.
   */
  private int danser () throws JeuException, RencontreException
  {
    try{
      tempsMission -= r.gererAttaque(r.GESTE);
      vie -= VIE_DANSER;
      tempsMission -= TEMPS_DANSER_ECHEC;
    }
    catch(KOException koe){
      throw new KOException(r.choisirArme());
    }
    catch(HSException hse){
      throw new HSException(r.choisirArme());
    }
    catch(RencontreException re){
    }



    if (!enVie()){
      throw new HerosException();
    }
    if (!enTemps()){
      throw new ChronoException();
    }

    return (TEMPS_DANSER_ECHEC);
  }

  /** Gere le lancement d'un sortilege.
   */
  public void ensorceller () throws JeuException
  {
    try{
      tempsMission -= danser();
    }
    catch(KOException koe){
      if (koe.duree() > DUREE_ENSORCELLER){
        vie = 0;
      }
    }
    catch(RencontreException re){
      tempsMission -= TEMPS_ENSORCELLER;
    }

    if (!enVie()){
      throw new HerosException();
    }
    if (!enTemps()){
      throw new ChronoException();
    }
  }

  /** Teste si le heros est encore vivant.
   */
  private boolean enVie ()
  {
    return (vie > 0);
  }

  /** Teste si la duree limite allouee n'est pas atteinte.
   */
  private boolean enTemps ()
  {
    return (tempsMission > 0);
  }

  /** Retourne une chaine indiquant l'etat du heros.
   */
  public String toString ()
  {
    return (vie + " points de vie, temps restant " + tempsMission);
  }

  /** Choisit une action.
   */
  public char action ()
  {
    return (r.choisirAction ());
  }

  /** Change de gestionnaire de rencontre.
   * @param nouveauGestionnaire Nouveau gestionnaire de rencontre.
   */
  void changerRencontre (Rencontre nouveauGestionnaire)
  {
    r = nouveauGestionnaire;
  }
}

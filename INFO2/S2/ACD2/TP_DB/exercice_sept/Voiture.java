package exercice_sept;

/** Classe représentant une voiture. */
public class Voiture{

  /** Moteur de la voiture. */
  private Moteur moteur;
  /** Carosserie de la voiture. */
  private Carosserie caros;

  /** Constructeur principal de la voiture. */
  public Voiture(Moteur mot, Carosserie car){
    moteur = mot;
    caros = car;
  }

  /** Retourne le poids total de la voiture. */
  public double getPoidsVoiture(){
    return (moteur.getPoidsMoteur() + caros.getPoidsCar());
  }
}

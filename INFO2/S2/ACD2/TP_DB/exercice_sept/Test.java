package exercice_sept;

/** Classe de test du poids d'une voiture, de son moteur et de sa carosserie. */
public class Test{

  public static void main(String[] args){
    Moteur mot = new Moteur(10.0);
    Carosserie car = new Carosserie(15.0);
    Voiture voi = new Voiture(mot, car);
    System.out.println("Poids de la voiture : "+voi.getPoidsVoiture());
  }
}

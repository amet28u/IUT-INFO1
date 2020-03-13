// Fichier Spot.java
// Auteur : LORENZELLI ANTONY
// Contexte : TP4 ACD1
// Date : 12/03/2019

package tourisme;
import voyage.Lieu;

public class TestSpot{
  public static void main(String[] args){
    Spot s = new Spot("Nancy", 4);
    System.out.println(s);
    System.out.println(s.score());
  }
}

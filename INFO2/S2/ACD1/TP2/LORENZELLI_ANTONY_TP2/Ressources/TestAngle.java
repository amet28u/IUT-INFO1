// Fichier TestAngle.java
// Auteur : LORENZELLI ANTONY
// Contexte : TP2-1 ACD1 fichier de test
// Date : 27/02/2019


/** Classe de test du type angle. */
public class TestAngle{

  /** Méthode main de test. */
  public static void main(String[] args){
    Angle A = new Angle(-Math.PI+1);
    System.out.println(A);
    Angle B = new Angle(180);
    System.out.println(B);
    double val_rad = A.enRadians();
    int val_deg = A.enDegres();
    System.out.println(val_rad);
    System.out.println(val_deg);
    System.out.println(A.somme(B));
    A.opposer();
    System.out.println(A);
  }

}

// Fichier TestPort.java
// Auteur : LORENZELLI ANTONY
// Contexte : TP4 ACD1
// Date : 12/03/2019

package navigation;
import voyage.Lieu;

/** Classe de test de port. */
public class TestPort{
  /** Méthode main pour un port. */
  public static void main(String[] args){
    
    Port p = new Port("Laval", "Vologne");
    System.out.println("p:"+p);
    boolean test = p.equals(p);
    System.out.println("Test égalité p avec p : "+test);

    System.out.println("-----");

    Port q = new Port("Laval", "Vologne");
    System.out.println("q:"+q);
    test = p.equals(q);
    System.out.println("Test égalité p avec q : "+test);

    System.out.println("-----");

    System.out.println("String : Laval-sur-Vologne");
    test = p.equals("Laval-sur-Vologne");
    System.out.println("Test égalité p avec String : "+test);

    System.out.println("-----");

    System.out.println("Lieu : Lieu(Laval)");
    test = p.equals(new Lieu("Laval"));
    System.out.println("Test égalité p avec Lieu : "+test);

    System.out.println("-----");

    System.out.println("Clone de p : "+p.clone());
    Object obj = p.clone();
    System.out.println("obj : (Object)p.clone()");
    test = p.equals(obj);
    System.out.println("Test d'égalité p avec obj : "+test);
  }
}

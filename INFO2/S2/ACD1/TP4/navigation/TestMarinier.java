// Fichier TestMarinier.java
// Auteur : LORENZELLI ANTONY
// Contexte : TP4 ACD1
// Date : 12/03/2019

package navigation;
import voyage.Voyageur;

/** Classe de test de marinier. */
public class TestMarinier{
  /** Méthode main pour un marinier. */
  public static void main(String[] args){

    Port p = new Port("Marainville", "Madon");
    Marinier m = new Marinier(p);
    System.out.println("m:"+m);
    boolean test = m.equals(m);
    System.out.println("Test égalité m avec m : "+test);

    System.out.println("-----");

    Port q = new Port("Marainville", "Madon");
    Marinier n = new Marinier(q);
    System.out.println("n:"+n);
    test = m.equals(n);
    System.out.println("Test égalité m et n : "+test);

    System.out.println("-----");

    System.out.println("String : Marainville-sur-Madon");
    test = m.equals("Marainville-sur-Madon");
    System.out.println("Test d'égalité m avec String : "+test);

    System.out.println("-----");

    System.out.println("Voyageur : Voyageur(p, p)");
    test = m.equals(new Voyageur(p, p));
    System.out.println("Test d'égalité m avec Voyageur : "+test);

    System.out.println("-----");

    System.out.println("Clone de m : "+m.clone());
    Object obj = m.clone();
    System.out.println("obj : (Object)p.clone()");
    test = m.equals(obj);
    System.out.println("Test d'égalité m avec obj : "+test);
  }
}

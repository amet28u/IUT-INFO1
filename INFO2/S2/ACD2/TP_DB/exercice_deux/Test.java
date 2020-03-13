package exercice_deux;

/** Classe de test d'une télécommande et de lampes. */
public class Test{

  public static void main(String[] args){
    Telecommande tel = new Telecommande();
    Lampe l1 = new Lampe("l1");
    System.out.println(l1);
    Lampe l2 = new Lampe("l2");
    System.out.println(l2);
    Lampe l3 = new Lampe("l3");
    System.out.println(l3);
    Lampe l4 = new Lampe("l4");
    System.out.println(l4);
    tel.ajouterLampe(l1);
    System.out.println(tel);
    l2.allumer();
    System.out.println(l2);
    tel.ajouterLampe(l2);
    System.out.println(tel);
    tel.ajouterLampe(l3);
    System.out.println(tel);
    tel.ajouterLampe(l4);
    System.out.println(tel);
    tel.activerLampe(3);
    System.out.println(l4);
  }
}

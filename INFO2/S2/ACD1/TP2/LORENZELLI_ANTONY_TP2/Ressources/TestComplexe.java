// Fichier TestComplexe.java
// Auteur : LORENZELLI ANTONY
// Contexte : TP2-2 ACD1 fichier de test
// Date : 27/02/2019


/** Classe de test du type complexe. */
class TestComplexe{

  /** Méthode main de test. */
  public static void main(String[] args){
    Complexe c = new Complexe(1.5, 2);
    System.out.println("c : "+c);
    Angle A = new Angle(Math.PI);
    Complexe d = new Complexe(1.5, A);
    System.out.println("d : "+d);
    Complexe e = new Complexe(1.5);
    System.out.println("e : "+e);
    Complexe f = new Complexe(A);
    System.out.println("f : "+f);
    System.out.println("module de d : "+d.module());
    System.out.println("argument de d : "+d.argument());
    System.out.println("test réel pur de d : "+d.estReelPur());
    System.out.println("test imaginaire pur de d : "+d.estImaginairePur());
    System.out.println("conjugue de d : "+d.conjugue());
    System.out.println("somme d et e : "+d.somme(e));
    System.out.println("produit d et e : "+d.produit(e));
    System.out.println("produit d et 1.5 : "+d.produit(1.5));
    System.out.println("inverse de d : "+d.inverse());
    d.ajouter(e);
    System.out.println("ajouter préalable de e à d : "+d);
    c.ajouter(f);
    System.out.println("multiplier préalable de c à f : "+d);
    f.inverser();
    System.out.println("inverser préalable de f : "+d);
  }
}

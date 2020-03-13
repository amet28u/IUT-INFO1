// Fichier TestPixel.java
// Auteur LORENZELLI ANTONY
// Contexte EX 3 MAIN TP1_ACD1 DUT INFO ST DIE
// Date 07/02/2019

/** Classe de test de Pixel. */
public class TestPixel{

  /** Programme test de Pixel. */
  public static void main(String[] args){
    Pixel p = new Pixel(1,1);
    Pixel o = new Pixel();
    Pixel m = new Pixel(p);
    Pixel n = new Pixel(1.5,0.5);
    double[] tab = new double[2];
    tab[0]=1.4;
    tab[1]=1.6;
    Pixel l = new Pixel(tab);
    System.out.println(p);
    System.out.println(o);
    System.out.println(m);
    System.out.println(n);
    System.out.println(l);
    double distance;
    System.out.println("Distance entre "+p+" et l'origine : "+p.distance());
    System.out.println("Distance entre "+p+" et "+o+" : "+p.distance(o));
    System.out.println("Distance de Manhattan entre "+p+" et "+o+" : "+p.distanceManhattan(o));
  }
}

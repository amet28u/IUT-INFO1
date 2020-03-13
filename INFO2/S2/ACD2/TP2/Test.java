public class Test{
  public static void main(String[] args){
    Livre a = new Livre("alpha", "loic", 0);
    Livre b = new Livre("beta", "maxime", 1);
    Livre d = new Livre("delta", "mey", 2);
    Livre e = new Livre("epsilon", "ariel", 3);
    Livre f = new Livre("phi", "loic", 4);
    Livre g = new Livre("gamma", "maxime", 5);
    Catalogue c = new Catalogue();
    c.addLivre(a);
    c.addLivre(b);
    c.addLivre(d);
    c.addLivre(e);
    c.addLivre(f);
    c.addLivre(g);
    for (int i = 0; i < 6; i++){
      System.out.println(c.chercherLivre(i));
    }
    c.delLivre(b);
    for (int i = 0; i < 6; i++){
      System.out.println(c.chercherLivre(i));
    }


  }
}

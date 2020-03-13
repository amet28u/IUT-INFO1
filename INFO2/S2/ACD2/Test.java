public class Test{
  public static void main(String[] args){
    Philosophe s = new Philosophe("Socrate");
    Philosophe p = new Philosophe("Platon");
    Disciple a = new Disciple("Aristote");
    Disciple x = new Disciple("Xénophon");
    Disciple t = new Disciple("Toto");
    System.out.println(s);
    System.out.println(p);
    System.out.println(a);
    System.out.println(x);
    System.out.println(t);
    System.out.println("---");
    s.addDisciple(p);
    s.addDisciple(x);
    p.addDisciple(a);
    p.addDisciple(t);
    System.out.println("Disciple de " + s + " : " + s.getDisciple(0));
    System.out.println("Disciple de " + s + " : " + s.getDisciple(1));
    System.out.println("Disciple de " + p + " : " + p.getDisciple(0));
    System.out.println("Disciple de " + p + " : " + p.getDisciple(1));
  }
}

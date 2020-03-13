Class Militaire{
  String nom;
  String grade;
  Militaire superieur;

  Militaire(String n, String g, Militaire s){
    nom=n;
    grade=g;
    superieur=s;
  }

  public Militaire(String n){
    nom=n;
    superieur=NULL;
  }

  public Militaire(String n, Militaire s){
    nom = n;
    superieur=s;
  }

  public String getNom(){
    return nom;
  }

  public Militaire getSuperieur(){
    return superieur;
  }
  //4-1
  public void affichage1(){
    System.out.println("nom: "+nom+", grade: "+g+", superieur: "+s.getNom());
  }
  //4-2
  public void affichage2(){
    if (this!=null){
      System.out.println("nom: "+nom+", grade: "+g+", superieur: "+getSuperieur().affichage2());
    }
  }
  //4-3
  public void affichage3(){
    System.out.println(nom+", "+g+", "+s.getNom());
  }

  public static void main(String[] args){
    Militaire mil=new Militaire("Foch");
    // dessin 1
    mil = new Militaire("Fayolle", mil);
    // dessin 2
    mil = new Militaire("Durand", mil);
    // dessin 3
    new Militaire("Dubois", mil);
    //dessin 4
    String.out.println(mil.getSuperieur().getNom()); //1
    Militaire chef = mil.getSuperieur().getSuperieur(); //2

  }
}

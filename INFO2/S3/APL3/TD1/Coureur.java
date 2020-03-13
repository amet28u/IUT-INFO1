public class Coureur{
  private String nom;
  private int dossard;
  private Coureur suivant;

  public Coureur(String n, int d, Coureur suiv){
    this.nom=n;
    this.dossard=d;
    this.suivant=suiv;
  }

  public String getNom(){
    return nom;
  }

  public int getDossard(){
    return dossard;
  }

  public Coureur getSuivant(){
    return suivant;
  }

  public void afficheClassement(){
    int rang = 1;
    Coureur aux = this;
    while (aux != null){
      //Terminal.ecrireStringln(rang + ": " + aux.getDossard() + " " + aux.getNom());
      rang++;
      aux = aux.getSuivant();
    }
  }

  public boolean memeClassement(Coureur autre){
    Coureur c = this;
    Coureur d = autre;
    while (d.getSuivant()!=null || c.getSuivant()!=null){
      if (d.getDossard()!=c.getDossard()){
        return false;
      }
      d=d.getSuivant();
      c=c.getSuivant();
    }
    return true;
  }

  public boolean memePodium(Coureur autre){
    Coureur c = this;
    Coureur d = autre;
    int cpt = 0;

    while (d.getSuivant()!=null || c.getSuivant()!=null){
      while (cpt<=3){
        if (d.getDossard()!=c.getDossard()){
          return false;
        }
        d=d.getSuivant();
        c=c.getSuivant();
        cpt++;
      }
      return true;
    }
    return false;
  }

  public static void main(String[] args){
    Coureur C1 = new Coureur("Donald", 125, null);
    C1 = new Coureur("Picsou", 103, C1);
    C1 = new Coureur("Fifi", 129, C1);
    C1 = new Coureur("Loulou", 155, C1);
    C1 = new Coureur("Riri", 157, C1);

    Coureur C2 = new Coureur("Fifi", 77, null);
    C2 = new Coureur("Loulou", 55, C2);
    C2 = new Coureur("Riri", 12, C2);

    System.out.println(C1.memePodium(C2));
    System.out.println(C1.memeClassement(C2));
  }
}

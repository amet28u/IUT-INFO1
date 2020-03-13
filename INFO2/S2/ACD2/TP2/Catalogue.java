public class Catalogue{

  public final static int NB_LIVRE_MAX = 50;
  private Livre[] l;
  private int nb_livre;

  public Catalogue(){
    l = new Livre[NB_LIVRE_MAX];
    nb_livre = 0;
  }

  public Livre chercherLivre(int ISBN){
    for (int i = 0; i < nb_livre+1; i++){
      if (l[i].getISBN() == ISBN){
        return l[i];
      }
    }
    return null;
  }

  public void addLivre(Livre liv){
    if (nb_livre < NB_LIVRE_MAX){
      l[nb_livre] = liv;
      nb_livre++;
    }
    else{
      System.out.println("Nombre de livre maximum atteint !");
    }
  }

  public void delLivre(Livre liv){
    if (nb_livre <= 0){
      System.out.println("Il n'y a pas de livre, impossible d'en supprimer");
    }
    else{
      for (int i = 0; i < nb_livre+1; i++){
        if (l[i].getISBN() == liv.getISBN()){
          l[i] = new Livre("null", "null", 0);
          nb_livre--;
        }
      }
    }
  }

  public int getNbLivre(){
    return (nb_livre);
  }
  
  public Catalogue getCatalogue(){
    return this;
  }

  public String toString(){
    return ("NbLivre : "+nb_livre+", livreMAX : "+NB_LIVRE_MAX);
  }
}

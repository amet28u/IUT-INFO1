public class Livre extends Oeuvre{

  private String auteur;
  private int ISBN;
  private int nbPages;

  public Livre(String titre, String auteur, int ISBN, int pages){
    super(titre);
    this.auteur = auteur;
    this.ISBN = ISBN;
    nbPages = pages;
  }

  public String getTitre(){
    return (super.titre);
  }

  public String getAuteur(){
    return (auteur);
  }

  public int getISBN(){
    return (ISBN);
  }

  public int getNbPages(){
    return (nbPages);
  }

  public String toString(){
    return ("ISBN : "+ISBN+", auteur : "+auteur+", titre : "+super.titre+", nb pages : "+nbPages);
  }
}

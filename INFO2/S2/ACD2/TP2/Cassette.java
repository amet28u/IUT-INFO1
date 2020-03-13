public class Casette extends Oeuvre{

  private String auteur;
  private String acteur;
  private double duree;

  public Livre(String titre, String auteur, String acteur, double duree){
    super(titre);
    this.auteur = auteur;
    this.acteur = acteur;
    this.duree = duree;
  }

  public String getTitre(){
    return (super.titre);
  }

  public String getAuteur(){
    return (auteur);
  }

  public String getActeur(){
    return (acteur);
  }

  public double getDuree(){
    return (duree);
  }

  public String toString(){
    return ("Acteur : "+acteur+", auteur : "+auteur+", titre : "+super.titre+", durée : "+duree);
  }
}

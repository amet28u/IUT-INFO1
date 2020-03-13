public class CD extends Oeuvre{

  private String compositeur;
  private String musicien;
  private double duree;

  public Livre(String titre, String auteur, String acteur, double duree){
    super(titre);
    this.compositeur = compositeur;
    this.musicien = musicien;
    this.duree = duree;
  }

  public String getTitre(){
    return (super.titre);
  }

  public String getCompositeur(){
    return (compositeur);
  }

  public String getMusicien(){
    return (musicien);
  }

  public double getDuree(){
    return (duree);
  }

  public String toString(){
    return ("Compositeur : "+compositeur+", musicien : "+musicien+", titre : "+super.titre+", durée : "+duree);
  }
}

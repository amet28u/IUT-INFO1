// Fichier Port.java
// Auteur : LORENZELLI ANTONY
// Contexte : TP4 ACD1
// Date : 12/03/2019

package navigation;
import voyage.Lieu;

/** Définition d'un port par son emplacement géographique et le nom de la rivière. */
public class Port extends Lieu{
  /** Nom de la rivière. */
  private String nom;
  /** Constructeur principal d'un port avec le nom du lieu et de la rivière. */
  public Port(String nom_lieu, String nom_riv){
    super(nom_lieu);
    nom = nom_riv;
  }
  /** Méthode toString pour un port. */
  public String toString(){
    return (super.toString()+"-sur-"+this.nom);
  }
}

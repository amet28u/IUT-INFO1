package exercice_deux;

import java.util.ArrayList;

/** Classe représentant une télécommande. */
public class Telecommande{

  /** Liste contenant toutes les lampes controlées par la télécommande. */
  private ArrayList<Lampe> liste;

  /** Constructeur principal d'une télécommande. */
  public Telecommande(){
    liste = new ArrayList<Lampe>();
  }

  /** Ajoute une lampe que la télécommande peut controler. */
  public void ajouterLampe(Lampe l){
    liste.add(l);
  }

  /** Active la lampe numéro indiceLampe si elle est éteinte. */
  public void activerLampe(int indiceLampe){
    if (!(liste.get(indiceLampe).allume)){
      liste.get(indiceLampe).allume = true;
    }
  }

  /** Désactive la lampe numéro indiceLampe si elle est allumée. */
  public void desactiverLampe(int indiceLampe){
    if (liste.get(indiceLampe).allume){
      liste.get(indiceLampe).allume = false;
    }
  }

  /** Active toutes les lampes controlées par la télécommande si elles sont éteintes. */
  public void activerTout(){
    for (int i = 0; i < liste.size(); i++){
      if (!(liste.get(i).allume)){
        liste.get(i).allume = true;
      }
    }
  }

  /** Affiche la description d'une télécommande. */
  public String toString(){
    return ("Télécommande contrôlant "+liste.size()+" lampe(s).");
  }
}

import java.util.Date;

public class Bibliotheque{

  public final static int NB_BIB_MAX = 50;
  public final static int NB_PRET_MAX = 50;
  public final static int NB_ADH_MAX = 50;
  private String nomBibliotheque;
  private Bibliothecaire[] bib;
  private int nb_bib = 0;
  private Pret[] p;
  private int nb_pret = 0;
  private Adherent[] adh;
  private int nb_adh = 0;

  public Bibliotheque(String nom){
    nomBibliotheque = nom;
    adh = new Adherent[NB_ADH_MAX];
    p = new Pret[NB_PRET_MAX];
    bib = new Bibliothecaire[NB_BIB_MAX];
  }

  /*public Adherent indiquerEmprunteur(Pret p){
    for (int i = 0; i < nb_adh; i++){
      if (p[i] == p){
        //adherent par pret
      }
    }
  }*/

  /*public editerBulletinDePret(){

  }*/

  public void setBibliothecaire(Bibliothecaire b){
    if (nb_bib < NB_BIB_MAX){
      bib[nb_bib] = b;
      nb_bib++;
    }
    else{
      System.out.println("Nombre de bibliothécaire maximum atteint !");
    }
  }

  public void delBibliothecaire(Bibliothecaire b){
    if (nb_bib <= 0){
      System.out.println("Il n'y a pas de bibliothecaire, impossible d'en supprimer");
    }
    else{
      for (int i = 0; i < nb_bib+1; i++){
        if (bib[i] == b){
          bib[i] = new Bibliothecaire("null", "null", (Date)null);
          nb_bib--;
        }
      }
    }
  }

  public void addAdherent(Adherent a){
    if (nb_adh < NB_ADH_MAX){
      adh[nb_adh] = a;
      nb_adh++;
    }
    else{
      System.out.println("Nombre d'adhérent maximum atteint !");
    }
  }

  public void modAdherent(int numdep, int numarr){
    for (int i =0; i < nb_adh+1; i++){
      if (adh[i].getNumeroAdherent() == numdep){
        adh[i].setNumeroAdherent(numarr);
      }
    }
  }

  public Adherent getAdherent(int num){
    for (int i =0; i < nb_adh+1; i++){
      if (adh[i].getNumeroAdherent() == num){
        return adh[i];
      }
    }
    return null;
  }

  public void delAdherent(Adherent a){
    if (nb_adh <= 0){
      System.out.println("Il n'y a pas d'adhérent, impossible d'en supprimer");
    }
    else{
      for (int i = 0; i < nb_adh+1; i++){
        if (adh[i] == a){
          adh[i] = new Adherent("null", "null", (Date)null, 0);
          nb_adh--;
        }
      }
    }
  }

  public void nouveauPret(Pret pret){
    if (nb_pret < NB_BIB_MAX){
      p[nb_pret] = pret;
      nb_pret++;
    }
    else{
      System.out.println("Nombre de pret maximum atteint !");
    }
  }

  public void finPret(Pret pret){
    if (nb_pret <= 0){
      System.out.println("Il n'y a pas de pret, impossible d'en supprimer");
    }
    else{
      for (int i = 0; i < nb_pret+1; i++){
        if (p[i] == pret){
          p[i] = new Pret((Date)null, (Date)null);
          nb_pret--;
        }
      }
    }
  }

  public void afficherCatalogue(Catalogue c){
    for (int i = 0; i < c.getNbLivre(); i++){
      System.out.println(c.chercherLivre(i));
    }
  }

}

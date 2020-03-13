import java.util.Date;

public class Adherent extends Personne {
	private int numeroAdherent;
	private final static int NB_EMPRUNT_MAX = 3;
	private int nbLivre;
	private Livre[] tabL;

	public Adherent(String nom, String prenom, Date date, int num) {
		super(nom, prenom, date);
		numeroAdherent = num;
		tabL = new Livre[NB_EMPRUNT_MAX];
		nbLivre = 0;
	}

	public void setNumeroAdherent(int num) {
		numeroAdherent = num;
	}

	public int getNumeroAdherent() {
		return numeroAdherent;
	}

	public void emprunterLivre(Livre livre) {
		if (nbLivre < NB_EMPRUNT_MAX) {
			tabL[nbLivre] = livre;
			nbLivre++;
		} else
			System.out.println("Vous avez déja empreinté le maxium autorisé, " + prenom);
	}

	public int getNbPret() {
		return nbLivre;
	}
}

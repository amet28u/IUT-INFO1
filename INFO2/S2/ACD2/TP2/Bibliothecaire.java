import java.util.Date;

public class Bibliothecaire extends Personne {
	
	public Bibliothecaire(String nom, String prenom, Date date) {
		super(nom, prenom, date);
	}
	
	private String getBibliothecaire() {
		return (this.getNom() + " " + this.getPrenom());
	}
}

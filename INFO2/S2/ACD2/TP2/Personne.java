import java.util.Date;

public class Personne {
	protected String nom;
	protected String prenom;
	protected Date dateDeNaissance;
	
	public Personne(String nom, String prenom, Date date) {
		this.nom = nom;
		this.prenom = prenom;
		dateDeNaissance = date;
	}
	
	protected void setNom(String nom) {
		this.nom = nom;
	}
	
	protected String getNom() {
		return nom;
	}

	protected void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	protected String getPrenom() {
		return prenom;
		
	}protected void setDateDeNaissance(Date date) {
		this.dateDeNaissance = date;
	}
	
	protected Date getDateDeNaissance() {
		return dateDeNaissance;
	}
}

import java.util.Date;

public class Pret {
	private Date dateEmprunt;
	private Date dateRetour;
	private Livre livre;
	private Adherent adherent;
	
	public Pret(Date dateEmprunt, Date dateRetour) {
		this.dateEmprunt = dateEmprunt;
		this.dateRetour = dateRetour;
	}
	
	public void setDateEmprunt(Date date) {
		dateEmprunt = date;
	}
	
	public Date getDateEmprunt() {
		return dateEmprunt;
	}
	
	public void setDateRetour(Date date) {
		dateRetour = date;
	}
	
	public Date getDateRetour() {
		return dateRetour;
	}
}

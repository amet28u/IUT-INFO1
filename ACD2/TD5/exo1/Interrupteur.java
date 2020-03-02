package exo1v4;

public class Interrupteur {

	private Lampe lampe;
	private boolean etatInterrupteur = false;

	Interrupteur(Lampe l) {
		this.lampe = l;
	}
	
	// Interrupteur avec 2 positions ou états : A (allumer) et E (Eteindre)
	public void mettreEtatLampe() {
		if (etatInterrupteur)
			lampe.allumer();
		else
			lampe.eteindre();
	}


	public boolean isEtatInterrupteur() {
		return etatInterrupteur;
	}

	public void setEtatInterrupteur(boolean etatInterrupteur) {
		this.etatInterrupteur = etatInterrupteur;
	}

	public String toString() {
		return isEtatInterrupteur()?"Interrupteur ON":"Interrupteur OFF";
	}

}

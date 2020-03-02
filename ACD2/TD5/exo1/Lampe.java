package exo1v4;

public class Lampe {

	private boolean etatLampe = false;
	
	public void allumer() {
		setEtatLampe(true);
	}

	public void eteindre() {
		setEtatLampe(false);
	}

	public boolean isEtatLampe() {
		return etatLampe;
	}

	public void setEtatLampe(boolean etatLampe) {
		this.etatLampe = etatLampe;
	}

	@Override
	public String toString() {
		return isEtatLampe()? "Lampe allumée":"Lampe éteinte";
	}

}

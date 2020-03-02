package exo1v4;

import java.util.Scanner;

public class ClavierOrdinateur {

	private Interrupteur interrupteur;

	ClavierOrdinateur(Interrupteur interrupteur){
		this.interrupteur = interrupteur;
	}

	public void saisirPositionInterrupteurAuClavier() {
		String saisiePosition = "Aucune";
		char charPosition = 'Z';
		Scanner sc = new Scanner(System.in);
				while(charPosition != 'A' && charPosition != 'E')
				{
					System.out.println("Saisir les caractères A (Allumer) ou E (Eteindre) ");
					saisiePosition = sc.nextLine();
					if ( saisiePosition.length() < 2 ) {
						charPosition = saisiePosition.charAt(0);
					}
				}

		switch(charPosition) {
		case 'A':
			interrupteur.setEtatInterrupteur(true);
			interrupteur.mettreEtatLampe();
			break;
		case 'E':
			interrupteur.setEtatInterrupteur(false);
			interrupteur.mettreEtatLampe();
			break;
		}

	}

}

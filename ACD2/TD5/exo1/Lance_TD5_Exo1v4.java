package exo1v4;

public class Lance_TD5_Exo1v4 {

	public static void main(String[] args) {
		Lampe lampe = new Lampe();
		Interrupteur interrupteur = new Interrupteur(lampe);
		ClavierOrdinateur clavierOrdinateur =  new ClavierOrdinateur(interrupteur);

		// Simulation pour saisir plusieurs fois un état pour l'interrupteur
		for(int i = 1; i <= 4; i++)
		{
			clavierOrdinateur.saisirPositionInterrupteurAuClavier();
			System.out.println( interrupteur.toString()+" ==> "+lampe.toString() );
		}
		System.out.println("Fin de la saisie");
	}

}

// Fichier AssemblageCompose.java
// Auteur : LORENZELI Antony
// Contexte : TP5 ACD1
// Date : 19/03/2019

package graphescene;

import outils3d.Dessinateur;

/** Défintion d'un AssemblageCompose avec deux Assemblage. */
public class AssemblageCompose extends Forme{
	
	/** Assemblage à gauche du futur AssemblageCompose. */
	private Assemblage fus;
	/** Assemblage à droite du futur AssemblageCompose. */
	private Assemblage ion;

	/** Constructeur d'un AssemblageCompose compose de deux Assemblage. */
	public AssemblageCompose(Assemblage gauche, Assemblage droite){
		fus = gauche;
		ion = droite;
	}

	/** Polymorphisme de afficher pour un AssemblageCompose. */
	public void afficher(Dessinateur dess){
		fus.afficher(dess);
		ion.afficher(dess);
	}
}


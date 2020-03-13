// Fichier Assemblage.java
// Auteur : LORENZELI Antony
// Contexte : TP5 ACD1
// Date : 19/03/2019

package graphescene;

import outils3d.Dessinateur;

/** Définition d'un Assemblage qui est aussi une Forme. */
public abstract class Assemblage extends Forme{

	/** Forme à gauche du futur assemblage. */
	private Forme fus;
	/** Forme à droite du futur assemblage. */
	private Forme ion;

	/** Constructeur d'un assemblage avec deux Formes. */
	public Assemblage(Forme gauche, Forme droite){
		fus = gauche;
		ion = droite;
	}
	
	/** Polymorphisme de afficher pour un assemblage. */
	public void afficher(Dessinateur dess){
		fus.afficher(dess);
		dess.preparerAssemblage();
		assembler(dess);
		ion.afficher(dess);
		dess.annulerAssemblage();
	}

	/** Signature de la méthode abstraite assembler. */
	public abstract void assembler(Dessinateur dess);
}

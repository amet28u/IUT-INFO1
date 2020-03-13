// Fichier Cylindre.java
// Auteur : LORENZELI Antony
// Contexte : TP5 ACD1
// Date : 19/03/2019

package graphescene;

import outils3d.Dessinateur;

/** Défintion d'une forme Cylindre par son rayon et sa hauteur. */
public class Cylindre extends Forme{

	/** Rayon du cylindre. */
	private double rayon;
	/** Hauteur du cylindre. */
	private double hauteur;

	/** Constructeur principal d'un Cylindre. */
	public Cylindre(double rayon, double hauteur){
		if (rayon < 0.0) this.rayon = 0.0; if (rayon > 1.0) this.rayon = 1.0;
		if (rayon >= 0.0 && rayon <= 1.0) this.rayon = rayon;
		if (hauteur < 0.0) this.hauteur = 0.0; if (hauteur > 1.0) this.hauteur = 1.0;
		if (hauteur >= 0.0 && hauteur <= 1.0) this.hauteur = hauteur;
	}

	/** Utilisation de la classe Dessinateur pour utiliser la méthode afficherCylindre. */
	public void afficher(Dessinateur dess){
		super.afficher(dess);
		dess.afficherCylindre(rayon, hauteur);
	}

}

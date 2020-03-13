// Fichier Boite.java
// Auteur : LORENZELI Antony
// Contexte : TP5 ACD1
// Date : 19/03/2019

package graphescene;

import outils3d.Dessinateur;

/** Défintion de la classe Boite qui est une Forme définit par sa largeur, hauteur et profondeur. */
public class Boite extends Forme{

	/** Largeur de la boite. */
	private double largeur;
	/** Hauteur de la boite. */
	private double hauteur;
	/** Profondeur de la boite. */
	private double profondeur;

	/** Constructeur principal d'une Boite. */
	public Boite(double largeur, double hauteur, double profondeur){
		if (largeur < 0.0) this.largeur = 0.0; if (largeur > 1.0) this.largeur = 1.0;
		if (largeur >= 0.0 && largeur <= 1.0) this.largeur = largeur;
		if (hauteur < 0.0) this.hauteur = 0.0; if (hauteur > 1.0) this.hauteur = 1.0;
		if (hauteur >= 0.0 && hauteur <= 1.0) this.hauteur = hauteur;
		if (profondeur < 0.0) this.profondeur = 0.0; if (profondeur > 1.0) this.profondeur = 1.0;
		if (profondeur >= 0.0 && profondeur <= 1.0) this.profondeur = profondeur;
	}

	/** Utilisation de la classe Dessinateur pour utiliser la méthode afficherBoite. */
	public void afficher(Dessinateur dess){
		super.afficher(dess);
		dess.afficherBoite(largeur, hauteur, profondeur);
	}

}


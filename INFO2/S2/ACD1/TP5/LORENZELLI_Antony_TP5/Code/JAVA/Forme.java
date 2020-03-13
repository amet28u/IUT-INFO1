// Fichier Forme.java
// Auteur : LORENZELI Antony
// Contexte : TP5 ACD1
// Date : 19/03/2019

package graphescene;

import outils3d.Dessinateur;

/** Forme est une scène qui est définit par une couleur. */
public class Forme extends Scene{

	/** Codage de l'intensité du rouge. */
	private double Red;
	/** Codage de l'intensité du vert. */
	private double Green;
	/** Codage de l'intensité du bleu. */
	private double Blue;


	/** Mutateur des couleurs de la classe Forme. */
	public void colorier(double r, double g, double b){
		if (r < 0.0) Red = 0.0; if (r > 1.0) Red = 1.0;
		if (r >= 0.0 && r <= 1.0) Red = r;
		if (g < 0.0) Green = 0.0; if (g > 1.0) Green = 1.0;
		if (g >= 0.0 && g <= 1.0) Green = g;
		if (b < 0.0) Blue = 0.0; if (b > 1.0) Blue = 1.0;
		if (b >= 0.0 && b <= 1.0) Blue = b;
	}
	/** Implémentation de afficher pour la classe Forme. */
	public void afficher(Dessinateur dess){
		dess.changerCouleur(Red, Green, Blue);
	}
}

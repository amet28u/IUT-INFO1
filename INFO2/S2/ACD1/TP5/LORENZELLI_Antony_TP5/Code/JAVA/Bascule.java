// Fichier Bascule.java
// Auteur : LORENZELI Antony
// Contexte : TP5 ACD1
// Date : 19/03/2019

package graphescene;

import outils3d.Dessinateur;

/** Définition de la Bascule par 3 vecteurs et un angle. */
public class Bascule extends Assemblage{
	/** Angle x de Bascule. */
	private double ax;
	/** Angle y de Bascule. */
	private double ay;
	/** Angle z de Bascule. */
	private double az;
	/** Angle de rotation de Bascule. */
	private double alpha;

	/** Constructeur de la Bascule. */
	public Bascule(Forme fus, Forme ion, double ax, double ay, double az, double alpha){
		super(fus, ion);
		this.ax = ax;
		this.ay = ay;
		this.az = az;
		this.alpha = alpha;
	}

	/** Assemblage avec une Bascule. */
	public void assembler(Dessinateur dess){
		dess.basculer(ax, ay, az, alpha);
	}
}

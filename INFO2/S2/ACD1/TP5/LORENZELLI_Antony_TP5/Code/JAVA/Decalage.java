// Fichier Decalage.java
// Auteur : LORENZELI Antony
// Contexte : TP5 ACD1
// Date : 19/03/2019

package graphescene;

import outils3d.Dessinateur;

/** Définiton du décalage par 3 vecteurs. */
public class Decalage extends Assemblage{

	/** Vecteur x de Decalage. */
	private double tx;
	/** Vecteur y de Decalage. */
	private double ty;
	/** Vecteur z de Decalage. */
	private double tz;

	/** Constructeur d'un Decalage. */
	public Decalage(Forme fus, Forme ion, double tx, double ty, double tz){
		super(fus, ion);
		this.tx = tx;
		this.ty = ty;
		this.tz = tz;
	}

	/** Assemblage avec un Decalage. */
	public void assembler(Dessinateur dess){
		dess.decaler(tx, ty, tz);
	}
}

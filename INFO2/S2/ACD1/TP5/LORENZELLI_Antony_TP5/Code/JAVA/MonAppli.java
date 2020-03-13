// Fichier MonAppli.java
// Auteur : LORENZELI Antony
// Contexte : TP5 ACD1
// Date : 19/03/2019

import outils3d.Fenetre3D;
import outils3d.Affichable;
import graphescene.*;

/** Application graphique. */
public class MonAppli{

	/** Construit et affiche une scene 3D. */
	public static void main (String[] args){

		Affichable scene = null;

    	Cylindre c = new Cylindre(0.5, 0.5);
    	c.colorier(0.0, 0.5, 0.5);
		Boite b = new Boite(0.5, 0.5, 0.5);
		b.colorier(0.5, 0.5, 0.0);
		Bascule b1 = new Bascule(c, b, 0.5, 0.5, 0.5, 0.5);

		Cylindre c1 = new Cylindre(0.8, 0.8);
		c.colorier(0.9, 0.8, 0.7);
		Boite b2 = new Boite(0.6, 0.6, 0.6);
		b2.colorier(0.4, 0.5, 0.6);
		Decalage d = new Decalage(c1, b2, 0.5, 0.5, 0.6);

		AssemblageCompose a = new AssemblageCompose(b1, d);


    	scene = a;

    	new Fenetre3D (scene);
  	}
}

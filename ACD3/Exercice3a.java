import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Exercice3a extends JFrame implements ActionListener {
	private JPanel p1, p2;
	private JLabel l1, l2;
	private JTextField t1;
	private JPasswordField t2;
	private JButton b1, b2;

	public Exercice3a(String title) {
		super(title);

		// Déclaration de tous les éléments de notre JFrame
		p1 = new JPanel(new GridLayout(0, 2));
		p2 = new JPanel(new FlowLayout());
		l1 = new JLabel("Login");
		l2 = new JLabel("Mot de passe");
		t1 = new JTextField();
		t2 = new JPasswordField();
		b1 = new JButton("OK");
		b2 = new JButton("Annuler");

		// Ajout des éléments de p1
		p1.add(l1);
		p1.add(t1);
		p1.add(l2);
		p1.add(t2);

		// Ajout des éléments de p2
		p2.add(b1);
		p2.add(b2);

		// Ajout des JPanel à notre JFrame
		this.add(p1, BorderLayout.NORTH);
		this.add(p2, BorderLayout.SOUTH);

		// Déclaration des écouteurs pour nos boutons
		b1.addActionListener(this);
		b2.addActionListener(this);
	}

	@Override // Facultatif, mais il est préférable de le mettre | Sert à dire que la méthode ci-dessous est surchargée
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

		if (source.equals(b1)) {
			p1.setBackground(Color.green); // Color.green fonctionne aussi, mais d'un point du vue logique vu que les couleurs sont des constantes mettez les en majuscule
			p2.setBackground(Color.green); // Pour je ne sais quelle raison, this.setBackground() ne fonctionne pas, alors on change la couleur des JPanel ...
			this.getContentPane().setBackground(Color.green); // ... et de l'espacement vide
		}

		if (source.equals(b2)) {
			t1.setText("");
			t2.setText("");
		}
	}

	// Fonction main ; ne vous y fiez pas c'est uniquement pour créer et afficher la fenêtre
	public static void main(String[] args) {
		Exercice3a exo = new Exercice3a("Exercice 3.a");
		exo.setSize(270, 120);
		exo.setVisible(true);
		exo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

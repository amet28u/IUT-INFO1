package calcultableau;

import java.util.*;

public class SommeArray {
    private ArrayList<Integer> tab;
    private int i;
    private int n;
    private int Sum;


    public SommeArray()
    {
        this.tab = new ArrayList<Integer>();
        this.n = 0;
        this.Sum = 0;
        this.i = 0;
    }


    public void defTaille()
    {
        do {
            System.out.println("Veuillez entrer la taille du tableau");
            Scanner sc = new Scanner(System.in);
            this.n = sc.nextInt();
        } while (n > 50);
        System.out.print(this.n);
    }

    public void remplirTab()
    {
        System.out.println("****DEBUT PROGRAMME MDR****");
        for (i = 0; i < n; i++) {
            System.out.println("Veuillez entrer un nombre");
            Scanner sc1 = new Scanner(System.in);
            tab.add(sc1.nextInt());
        }
    }

    public void affichageTab()
    {
        System.out.println("Les élements de tableau sont:");
        for (i = 0; i < n; i++) {
            System.out.println(this.tab.get(i));
        }
    }

    public void affichageSomme()
    {
        for (i = 0; i < n; i++) {
            this.Sum += this.tab.get(i);
        }
        System.out.println("La somme des éléments est égal à = " + Sum);
        System.out.println("****FIN PROGRAMME****");
    }
}

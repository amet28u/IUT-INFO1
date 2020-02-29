package calcultableau;

import java.util.*;

public class Main {
    public static void main(String[] args){

        SommeArray tableau = new SommeArray();

        tableau.defTaille();
        tableau.remplirTab();
        tableau.affichageTab();
        tableau.affichageSomme();

    }
}

package dominations;

import dominations.model.*;

public class Main {

    public static void main(String[] args) {

        // System.out.println(tab.length);
        int[] tab={2,3};
        char[] ch={'a','a'};
        Cartes cartes = new Cartes(1, tab, ch);
        Cartes[] deuxJoueurs=cartes.pourLaPile(36);
        System.out.println(deuxJoueurs[0].getNumeroDeCarte());
         deuxJoueurs[0].getInfoCarte();
        int i;
        System.out.print("Les dominos en jeu sont les suivants  : ");
        for (i=0;i<deuxJoueurs.length;i++){
            System.out.print(deuxJoueurs[i].getNumeroDeCarte());
            System.out.print(" || ");
        }
    }
}

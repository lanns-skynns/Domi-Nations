package dominations;

import dominations.model.*;

public class Main {

    public static void main(String[] args) {

        Pile pile = new Pile(36);

        Carte[] cartesDansPile = pile.getCartesPile();
        System.out.println(cartesDansPile[20].getInfoCarte());
        int i;

        //System.out.print("Les dominos en jeu sont les suivants  : ");


        for (i=0;i<cartesDansPile.length;i++){
            System.out.println(cartesDansPile[i].getInfoCarte());
        }
    }
}

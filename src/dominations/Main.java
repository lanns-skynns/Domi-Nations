package dominations;

import dominations.model.*;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        Pile pile = new Pile(36,4);

        List <Carte> cartesDansPile = pile.getCartesPile();
       // System.out.println(cartesDansPile.get(0).getInfoCarte());
        int i;

        for (i=0;i<cartesDansPile.size();i++){
            System.out.println(cartesDansPile.get(i).getInfoCarte());
        }
        System.out.println(" ");
        System.out.println("Cartes restantes -> "+pile.getNombreCartesRestantes());

        List <Carte> cartesPiochees= pile.piocherCartes();
        int j;
        System.out.println("cartes piochées : ");
        for (j=0;j<cartesPiochees.size();j++){
            System.out.println(cartesPiochees.get(j).getInfoCarte());
        }

        System.out.println("Carte restantes -> "+pile.getNombreCartesRestantes());

        List <Carte> cartesPiopio= pile.piocherCartes();

        System.out.println("cartes piochées : ");
        for (j=0;j<cartesPiochees.size();j++){
            System.out.println(cartesPiopio.get(j).getInfoCarte());
        }

        System.out.println("Carte restantes -> "+pile.getNombreCartesRestantes());

    }
}

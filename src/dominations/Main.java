package dominations;

import dominations.model.*;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        //création de la pile et de la défausse

        Pile pile = new Pile(36,4);
        CartesJouer defausse = new CartesJouer();

        List <Carte> cartesDansPile = pile.getCartesPile();
       // System.out.println(cartesDansPile.get(0).getInfoCarte());

        System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");

        //On créé les cartes en jeu (4 cartes y sont automatiquement ajoutées)
        CartesEnJeu cartesEnJeu = new CartesEnJeu(pile, defausse);

        cartesEnJeu.getCartesPile();

        pile.getNombreCartesRestantes();
        defausse.getCartesPile();

    }

}

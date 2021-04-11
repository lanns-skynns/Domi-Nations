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
        Couleur couleur = Couleur.BLEU;
        Royaume roy = new Royaume(couleur);
        Cellule cell = roy.getCellule(4, 0);

        List<String> voisins = cell.getVoisins();

        System.out.println(voisins);

        roy.afficherTypesGrille();

        roy.afficherEtatGrille();

    }

}

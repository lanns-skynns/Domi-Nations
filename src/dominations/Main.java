package dominations;

import dominations.model.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
/*
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
*/
       //Partie partie =new Partie();

        Scanner scanner = new Scanner(System.in);

        Couleur couleur = Couleur.BLEU;
        Royaume roy = new Royaume(couleur);
        roy.genererRoyaumeAleatoire();

        roy.afficherTypesGrille();
        /*
        System.out.println("abs: ");
        int i = scanner.nextInt();

        System.out.println("ord: ");
        int j = scanner.nextInt();
        */

        //List<List<Integer>> listRef = new ArrayList<>();
        //List<List<Integer>> xxx = roy.detectionVoisinsCellule(i, j, 0, listRef);
        //System.out.println("resultat: "+xxx);

        List<List<List<Integer>>> xxx = roy.analyserGroupesGrille();

        for (int i = 0; i < xxx.size(); i++) {
            System.out.println(xxx.get(i));
        }

    }


}

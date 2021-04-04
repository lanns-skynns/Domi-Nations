package dominations.model;

import java.util.ArrayList;
import java.util.List;

public class CartesJouer extends Pile{
    /*
    Classe définissant les cartes jouées, c'est à dire déjà placées sur un royaume, qui ne peuvent donc plus etre jouées.
     */

    public CartesJouer() {
        //Création d'une pile vide dans laquelle on ne peut pas piocher de cartes (car elles sont déjà jouées)
        super(0, 0);
    }

    public void ajouterCartes(List<Carte> cartes){
        if(this.cartesPile == null) {
            System.out.println("hey pelo");
            this.cartesPile = new ArrayList<Carte>(cartes);
        } else {
            System.out.println("heya pelo");
            Carte test = cartes.get(0);

            for(Carte carte: cartes){
                this.cartesPile.add(carte);
            }
        }
    }

}

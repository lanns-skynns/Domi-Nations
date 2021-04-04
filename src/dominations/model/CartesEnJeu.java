package dominations.model;

import java.util.List;

public class CartesEnJeu extends Pile  {

    public CartesEnJeu(Pile pileSource, CartesJouer defausse) {
        super(0, 0);

        this.cartesPile = pileSource.piocherCartes();
        defausse.ajouterCartes(this.cartesPile);
    }

    @Override
    public List<Carte> getCartesPile(){

        System.out.println("Les cartes au centre sont les suivantes: ");
        for(Carte carte : this.cartesPile){
            System.out.print(carte.getInfoCarte() + " || ");
        }
        System.out.println(" ");

        return this.cartesPile;
    }


    //Trois cartes placées au milieu du plateau sur lesquelles les joueurs posent les rois.

}

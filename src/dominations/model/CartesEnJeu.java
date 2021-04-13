package dominations.model;

import java.util.*;

public class CartesEnJeu extends Pile {
    protected int nbrCartesEnJeuRestantes;
    protected Pile pileSource;

    public CartesEnJeu(Pile pileSource) {
        super(0, 0);
        this.pileSource = pileSource;
        // this.cartesPile = nouvellesCartes();

    }

    public int carteChoisie(int choix) {
        cartesPile.remove(cartesPile.get(choix));
        nbrCartesEnJeuRestantes = cartesPile.size();
        return nbrCartesEnJeuRestantes;
    }

    @Override
    public List<Carte> getCartesPile() {
        System.out.println("Les cartes au centre sont les suivantes: ");
        for (Carte carte : this.cartesPile) {
            System.out.print(carte.getInfoCarte() + " || ");
        }
        System.out.println(" ");

        return this.cartesPile;
    }

    public int getNbrCartesEnJeuRestantes() {
        return nbrCartesEnJeuRestantes;
    }

    public List<Carte> nouvellesCartes() {
        List<Carte> cartesPiochees = new ArrayList<Carte>(this.pileSource.piocherCartes());
        List<Carte> cartesPiocheesTriees = new ArrayList<>();
        int[] intermediaire = new int[cartesPiochees.size()];
        Map<Integer, Carte> map = new HashMap<>();
        int i;
        for (i = 0; i < cartesPiochees.size(); i++) {
            intermediaire[i] = cartesPiochees.get(i).getNumeroDeCarte();
            map.put(intermediaire[i], cartesPiochees.get(i));
        }
        Arrays.sort(intermediaire);
        int j;
        for (j = 0; j < intermediaire.length; j++) {
            cartesPiocheesTriees.add(map.get(intermediaire[j]));
        }
        this.cartesPile = cartesPiocheesTriees;
        nbrCartesEnJeuRestantes = cartesPile.size();
        return cartesPile;
    }

    // Trois cartes placées au milieu du plateau sur lesquelles les joueurs posent
    // les rois.

}

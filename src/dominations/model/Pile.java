package dominations.model;

import java.util.*;

public class Pile {

    private final int nbrCarte;
    private List<Carte> cartesPile;
    private int nbrCartesRestantes;
    private final int nbrCartesApiocher;

    /* ************
    Constructeur de la classe
    ************ */

    public Pile(int nbrCartes,int nbrCartesApiocher) {
        this.nbrCarte = nbrCartes;
        this.cartesPile = creerPile(this.nbrCarte);
        this.nbrCartesRestantes = cartesPile.size();
        this.nbrCartesApiocher=nbrCartesApiocher;
    }

    public List<Carte> getCartesPile() {
        return cartesPile;
    }

    public int getNombreCartesRestantes() {
        return nbrCartesRestantes;
    }

    /* ***************
    La méthode piocherCartes permet  de piocher un certain nombre de carte à chaque de jeu
    *************** */

    public List<Carte> piocherCartes() {
        List<Carte> cartesPiochees = new ArrayList<>();// cette liste de carte contiendra les cartes qui seront piochées
        int i;
        for (i = 0; i < nbrCartesApiocher; i++) {// cette boucle permet de piocher un certain de nombre de carte à chaque tour  piocher à chaque tour;
            cartesPiochees.add(this.cartesPile.get(0));// on pioche la prémière carte de la pile
            this.cartesPile.remove(0);// on supprime chaque carte déja piochée
        }
        this.nbrCartesRestantes = cartesPile.size();
        return cartesPiochees;
    }

    public void melanger() {

    }



    /* ************
        Cette méthode permet de choisir un certain nombre de carte de manière alétoire pour la pile de carte.
    ************* */


    private List<Carte> creerPile(int nbrDeCarte) {

        int i;
        int carteAleatoire;
        List<Carte> pourLapioche = new ArrayList<>();// cette veriable contiendra la liste de carte qui servira de pioche
        for (i = 0; i < nbrDeCarte; i++) {// cette boucle permet de sélectionner un nombre précis de carte pour la pile
            carteAleatoire = (int) (Math.round(Math.random() * (numCartes.size() - 1)));// on sélectionne  un nombre aléatoire entre 0 et et le nombre de carte qui n'ont pas encore été ajouté dans le pile
            int[] nbrCouronne = {this.couronnes.get(carteAleatoire)[0], this.couronnes.get(carteAleatoire)[1]};
            char[] typePaysage = {this.paysages.get(carteAleatoire)[0], this.paysages.get(carteAleatoire)[1]};
            Carte intermediaire = new Carte(this.numCartes.get(carteAleatoire), nbrCouronne, typePaysage);// création d'une carte
            pourLapioche.add(intermediaire);// ajout de  ²                                                                                             cette carte dans la pioche
            this.numCartes.remove(carteAleatoire);//suppression...
            this.couronnes.remove(carteAleatoire);//de la carte
            this.paysages.remove(carteAleatoire);// choisi
        }
        return pourLapioche; // retourne la pioche
    }


    final private int[][] LISTECOURONNES = {
            {0, 0},
            {0, 0},
            {0, 0},
            {0, 0},
            {0, 0},
            {0, 0},
            {0, 0},
            {0, 0},
            {0, 0},
            {0, 0},
            {0, 0},
            {0, 0},
            {0, 0},
            {0, 0},
            {0, 0},
            {0, 0},
            {0, 0},
            {0, 0},
            {1, 0},
            {1, 0},
            {1, 0},
            {1, 0},
            {1, 0},
            {1, 0},
            {1, 0},
            {1, 0},
            {1, 0},
            {1, 0},
            {1, 0},
            {1, 0},
            {1, 0},
            {1, 0},
            {1, 0},
            {1, 0},
            {1, 0},
            {0, 1},
            {0, 1},
            {0, 1},
            {0, 1},
            {1, 0},
            {0, 2},
            {0, 2},
            {0, 2},
            {0, 2},
            {2, 0},
            {0, 2},
            {0, 2},
            {0, 3},
    };



   final private char[][] LISTEPAYSAGES = {
            {'c', 'c'},
            {'c', 'c'},
            {'f', 'f'},
            {'f', 'f'},
            {'f', 'f'},
            {'f', 'f'},
            {'s', 's'},
            {'s', 's'},
            {'s', 's'},
            {'p', 'p'},
            {'p', 'p'},
            {'m', 'm'},
            {'c', 'f'},
            {'c', 's'},
            {'c', 'p'},
            {'c', 'm'},
            {'f', 's'},
            {'f', 'p'},
            {'c', 'f'},
            {'c', 's'},
            {'c', 'p'},
            {'c', 'm'},
            {'c', 'n'},
            {'f', 'c'},
            {'f', 'c'},
            {'f', 'c'},
            {'f', 'c'},
            {'f', 's'},
            {'f', 'p'},
            {'s', 'c'},
            {'s', 'c'},
            {'s', 'f'},
            {'s', 'f'},
            {'s', 'f'},
            {'s', 'f'},
            {'c', 'p'},
            {'s', 'p'},
            {'c', 'm'},
            {'p', 'm'},
            {'n', 'c'},
            {'c', 'p'},
            {'s', 'p'},
            {'c', 'm'},
            {'p', 'm'},
            {'n', 'c'},
            {'m', 'n'},
            {'m', 'n'},
            {'c', 'n'},
    };

    private List<Integer> numCartes() {// créer une "List" qui contient les numéros de cartes au lieu du tableau de base.
        int i;
        List<Integer> numC;
        numC = new ArrayList<>();
        for (i = 0; i < 48; i++) {
            numC.add(i);
        }
        return numC;
    }

    private List<int[]> couronnes() { //créer une "List" qui contient les coruonnes au lieu du tableau de base.
        int j;
        List<int[]> c = new ArrayList<>();
        for (j = 0; j < 48; j++) {
            c.add(LISTECOURONNES[j]);
        }
        return c;
    }

    private List<char[]> paysages() {// créer une "List" qui paysages de cartes au lieu du tableau de base.
        int j;
        List<char[]> c = new ArrayList<>();
        for (j = 0; j < 48; j++) {
            c.add(LISTEPAYSAGES[j]);
        }
        return c;
    }
     private List<Integer> numCartes = numCartes();
     private List<char[]> paysages = paysages();
     private List<int[]> couronnes = couronnes();

}





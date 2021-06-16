package dominations.model;

import java.util.ArrayList;
import java.util.List;

public class Pile {

    private final int nbrCarte;
    private final int nbrCartesApiocher;
    final private int[][] LISTECOURONNES = { // liste des couronnes, récupérées dans le fichier excel du projet.
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
    final private char[][] LISTEPAYSAGES = {//liste des paysages  : récupérée dans le fichier excel du proje
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

    /* ************
        Constructeur de la classe
    ************ */
    protected List<Carte> cartesPile;
    protected int nbrCartesRestantes;
    private final List<Integer> numCartes = numCartes();// contient une liste de 48 cartes classé par ordre croissant

    /* ***************
         La méthode piocherCartes permet  de piocher un certain nombre de carte à chaque de jeu
    *************** */
    private final List<char[]> paysages = paysages();//contient un couple de deux paysages

    /* ************
        Cette méthode permet de choisir un certain nombre de carte de manière alétoire pour la pile de carte.
    ************* */
    private final List<int[]> couronnes = couronnes();//contient un couple  de nombre de  couronnes.


    public Pile(int nbrCartes, int nbrCartesApiocher) {
        this.nbrCarte = nbrCartes;
        this.cartesPile = creerPile(this.nbrCarte);
        this.nbrCartesRestantes = cartesPile.size();
        this.nbrCartesApiocher = nbrCartesApiocher;
    }

    public List<Carte> getCartesPile() {
        return cartesPile;
    }

    /* *********
         numCartes() :  crée une liste  qui contient les numéros de cartes au lieu du tableau de base.
    ******** */

    public int getNombreCartesRestantes() {
        return nbrCartesRestantes;
    }

    /*
       couronnes() :  crée une "List" qui contient les couronnes au lieu du tableau de base.
     */

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

     /* *********
        paysages(): créer une "List" qui paysages de cartes au lieu du tableau de base.
     ********* */

    private List<Carte> creerPile(int nbrDeCarte) {

        int i;
        int carteAleatoire;
        Carte intermediaire;
        List<Carte> pourLapioche = new ArrayList<>();// cette veriable contiendra la liste de carte qui servira de pioche
        for (i = 0; i < nbrDeCarte; i++) {// cette boucle permet de sélectionner un nombre précis de carte pour la pile
            carteAleatoire = (int) (Math.round(Math.random() * (numCartes.size() - 1)));// on sélectionne  un nombre aléatoire entre 0 et et le nombre de carte qui n'ont pas encore été ajouté dans le pile
            int[] nbrCouronne = {this.couronnes.get(carteAleatoire)[0], this.couronnes.get(carteAleatoire)[1]};
            char[] typePaysage = {this.paysages.get(carteAleatoire)[0], this.paysages.get(carteAleatoire)[1]};
            intermediaire = new Carte(this.numCartes.get(carteAleatoire), nbrCouronne, typePaysage);// création d'une carte
            pourLapioche.add(intermediaire);// ajout de cette carte dans la pioche
            this.numCartes.remove(carteAleatoire);//suppression...
            this.couronnes.remove(carteAleatoire);//de la carte
            this.paysages.remove(carteAleatoire);// choisi
        }
        return pourLapioche; // retourne la pioche
    }

    private List<Integer> numCartes() {
        int i;
        List<Integer> numC;
        numC = new ArrayList<>();
        for (i = 1; i < 49; i++) {// crée une liste d'entier entre 1 et 49
            numC.add(i);
        }
        return numC;
    }

    private List<int[]> couronnes() {
        int j;
        List<int[]> c = new ArrayList<>();
        for (j = 0; j < 48; j++) {
            c.add(LISTECOURONNES[j]);
        }
        return c;
    }

    private List<char[]> paysages() {
        int j;
        List<char[]> c = new ArrayList<>();
        for (j = 0; j < 48; j++) {
            c.add(LISTEPAYSAGES[j]);
        }
        return c;
    }

}





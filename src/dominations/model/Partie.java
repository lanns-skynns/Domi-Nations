package dominations.model;

import javafx.event.ActionEvent;
import javafx.scene.Scene;

import java.util.*;
import dominations.Controller.CAccueil;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import dominations.Controller.*;

public class Partie {
    private final int NBRTOUR = 12;
    private Joueur[] joueur;
    private Joueur tourJoueur;
    private Pile pile;
    private CartesEnJeu cartesEnJeu;
    private int tourActuel;
    private CartesJouer cartesJouees;
    Scene scene;
    private int nbrJoueurs;
    private List<String> recupListeNomsJoueurs;
    private  CAccueil cAccueil;
    private int choix = 1;

    public  void setRecupListeNomsJoueurs(List <String> recupListeNomsJoueurs){
        this.recupListeNomsJoueurs=recupListeNomsJoueurs;
    }

    public  void setNbrJoueurs(int nbrJoueurs){
        this.nbrJoueurs=nbrJoueurs;
    }

    public void  Partie2(ActionEvent e) {

        Iterator nomsListesJouers= this.recupListeNomsJoueurs.listIterator();
        while (nomsListesJouers.hasNext()){
            System.out.println(nomsListesJouers.next());
        }
      //  new Test().test1(e);
        


        int[] initialisationPartie = initialisationPartie();// c'est un tableau contenant trois valeurs :  nbr de joueurs, nbr carte pour la pile, nbr carte a piocher; nbr couronne
        List<Joueur> listeJoueurs = creerJoueurs(initialisationPartie[2]);// Création joueurs
        System.out.println(listeJoueurs.get(0).getNbrCouronnes());
        Pile pile = new Pile(initialisationPartie[0], initialisationPartie[1]);//création pile
        CartesEnJeu cartesEnJeu = new CartesEnJeu(pile);// initialisation de la classe cartes en jeu
        Paysage[] paysages;
        Carte carteChoisie;
        List<Integer> numeroCartesTemoins = null;
        Royaume rj;

        //tour 1
        // List<Carte> carteEGN=cartesEnJeu.nouvellesCartes();
        List<Joueur> ordreJoueurs = ordreJoueurs(listeJoueurs);// ordre de jeu des joueurs
        Map pourTourSuivant = new HashMap();
        List<Joueur> quandJouer = new ArrayList<>();
        boolean isPossible;
        int tourActuel = 1;
        List<Carte> carteEGN;
        List<Carte> temoins = null;


            CChoixCarte choixCartes = new CChoixCarte(e, ordreJoueurs);
            choixCartes.setCartesEnG(cartesEnJeu);
            choixCartes.setPile(pile);
            choixCartes.ChoixScene();

            //new Test().test1(e);




    }



    /* ************
        tourDeQUi(Map joueur) :  cette méthode permet de classer les joueurs dans le bon ordre pour le tour suivant
    *********** */
    private List<Joueur> tourDeQUi(Map joueur) {
        List<Joueur> tourDeQui = new ArrayList<>();
        int i;
        for (i = 0; i < joueur.size(); i++) {
            tourDeQui.add((Joueur) joueur.get(i));
        }
        return tourDeQui;
    }

    /* ********
    numeroCarteTemoin(List<Carte>  l) : Cette méthode permet participe au classement des joueurs dans le bon ordre à chaque tour de jeu
    ******** */

    private List<Integer> numeroCarteTemoin(List<Carte> l) {
        List<Integer> lInt = new ArrayList<>();
        int j;
        for (j = 0; j < l.size(); j++) {
            lInt.add(l.get(j).getNumeroDeCarte());
        }
        return lInt;
    }

        /* ***********
            Cette méthode affiche les cartes en jeu
     ********** */

    private void affichageCartesEnJeu(List<Carte> cartesEnJeu) {
        int j;
        System.out.print("Les cartes sont les suivantes : ");
        for (j = 0; j < cartesEnJeu.size(); j++) {
            System.out.print(cartesEnJeu.get(j).getNumeroDeCarte() + " - ");
        }
        System.out.println(" ");
    }

    /* *****
        Cette méthode est lancée qu'au premier tour de jeu et permet de choisir aléatoirement l'ordre de jeu pour ledit tour.
    ************ */
    private List<Joueur> ordreJoueurs(List<Joueur> listeJoueurs) {
        Map map = new HashMap();
        int i;
        List<Joueur> listeJoueursOrdonnee = new ArrayList<>();
        List<Integer> intermediaire = new ArrayList<>();
        for (i = 0; i < listeJoueurs.size(); i++) {
            intermediaire.add(i);
            map.put(i, listeJoueurs.get(i));
        }
        Collections.shuffle(intermediaire);
        int j;
        for (j = 0; j < intermediaire.size(); j++) {
            listeJoueursOrdonnee.add((Joueur) map.get(intermediaire.get(j)));
        }

        return listeJoueursOrdonnee;
    }

    private void afficherOrdreListeJoueurs(List<Joueur> listeJoueurs) {
        int j;
        System.out.print("Ordre de jeu des joueurs : ");
        for (j = 0; j < listeJoueurs.size(); j++) {
            System.out.print("" + listeJoueurs.get(j).getNom() + " || ");
        }
        System.out.println(" ");
    }

    private void afficherListeJoueurs(List<Joueur> listeJoueurs) {
        listeJoueurs.get(0).setTour(true);
        int j;
        System.out.println("");
        for (j = 0; j < listeJoueurs.size(); j++) {
            System.out.println(listeJoueurs.get(j).getInfosJoueur());
            System.out.println("-------");
        }
    }

    private void afficherListeCartesPile(List<Carte> listeCartesPile) {
        int j;
        System.out.println("");
        for (j = 0; j < listeCartesPile.size(); j++) {
            System.out.println(listeCartesPile.get(j).getInfoCarte());
        }
    }

    /* ***********
    La méthode crerJoueurs permet de créer le nombre joueurs correspondant à son unique argument.
    Elle retourne une liste de Joueur

     */

    private List<Joueur> creerJoueurs( int nbrCouronnes) {
        Couleur couleurRoyaume;
        String nomJoueurs;
        Joueur intermediaire;
        Royaume royaume;
        List<Joueur> listeJoueurs = new ArrayList<>();
        int j;
        for (j = 1; j < this.nbrJoueurs + 1; j++) {
            if (j == 1) {
                couleurRoyaume = Couleur.BLEU; // associe une couleur au royaume
            } else if (j == 2) {
                couleurRoyaume = Couleur.ROUGE;
            } else if (j == 3) {
                couleurRoyaume = Couleur.VERT;
            } else {
                couleurRoyaume = Couleur.JAUNE;
            }
            nomJoueurs =this.recupListeNomsJoueurs.get(j-1);
            royaume = new Royaume(couleurRoyaume);
            intermediaire = new Joueur(nomJoueurs, royaume, nbrCouronnes);// création du joueur
            listeJoueurs.add(intermediaire);// ajout dans la liste

        }
        return listeJoueurs;
    }

    /*
    La fonction  initialisationPartie() permet d'initialiser une partie, elle retourne un tableau
     contenant trois valeurs :  nbr de joueurs, nbr carte pour la pile, nbr carte a piocher
    */

    private int[] initialisationPartie() {
        int[] initialisationPartie = new int[3];
        int nbrCartesPourPile = 0;

        switch (this.nbrJoueurs) {
            case 2:
                nbrCartesPourPile = 48;
                break;
            case 3:
                nbrCartesPourPile = 36;
                break;
            case 4:
                nbrCartesPourPile = 48;
                break;
            default:
                break;
        }

        int nbrCouronnes = (nbrJoueurs == 2) ? 2 : 1;
        int nbrCarteAPiocher = (nbrJoueurs == 3) ? 3 : 4;
        initialisationPartie[0] = nbrCartesPourPile;
        initialisationPartie[1] = nbrCarteAPiocher;
        initialisationPartie[2] = nbrCouronnes;
        System.out.println(",nbr de carte a piocher"+nbrCarteAPiocher);
        return initialisationPartie;
    }

    public Joueur getToutJoueur() {
        return tourJoueur;
    }

    public void setToutJoueur(Joueur toutJoueur) {
        this.tourJoueur = toutJoueur;
    }
}

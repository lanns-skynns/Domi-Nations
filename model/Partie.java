package dominations.model;

import dominations.Controller.CChoixCarte;
import javafx.event.ActionEvent;

import java.util.*;

public class Partie {
    private int nbrJoueurs;
    private List<String> recupListeNomsJoueurs;

    public void setRecupListeNomsJoueurs(List<String> recupListeNomsJoueurs) {
        this.recupListeNomsJoueurs = recupListeNomsJoueurs;
    }

    public void setNbrJoueurs(int nbrJoueurs) {
        this.nbrJoueurs = nbrJoueurs;
    }

    public void Partie2(ActionEvent e) {

        Iterator nomsListesJouers = this.recupListeNomsJoueurs.listIterator();
        while (nomsListesJouers.hasNext()) {
            System.out.println(nomsListesJouers.next());
        }


        int[] initialisationPartie = initialisationPartie();// c'est un tableau contenant trois valeurs :  nbr de joueurs, nbr carte pour la pile, nbr carte a piocher; nbr couronne
        List<Joueur> listeJoueurs = creerJoueurs(initialisationPartie[2]);// Création joueurs
        System.out.println(listeJoueurs.get(0).getNbrCouronnes());
        Pile pile = new Pile(initialisationPartie[0], initialisationPartie[1]);//création pile
        CartesEnJeu cartesEnJeu = new CartesEnJeu(pile);// initialisation de la classe cartes en jeu

        List<Joueur> ordreJoueurs = ordreJoueurs(listeJoueurs);// ordre de jeu des joueurs



        CChoixCarte choixCartes = new CChoixCarte(e, ordreJoueurs, initialisationPartie[3], this.nbrJoueurs);
        choixCartes.setCartesEnG(cartesEnJeu);
        choixCartes.setPile(pile);
        choixCartes.ChoixScene();


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
            if (listeJoueurs.size() == 2) {
                listeJoueursOrdonnee.add((Joueur) map.get(intermediaire.get(j)));
            }

        }

        return listeJoueursOrdonnee;
    }


    /* ***********
    La méthode crerJoueurs permet de créer le nombre joueurs correspondant à son unique argument.
    Elle retourne une liste de Joueur

     */

    private List<Joueur> creerJoueurs(int nbrCouronnes) {
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
            nomJoueurs = this.recupListeNomsJoueurs.get(j - 1);
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
        int[] initialisationPartie = new int[4];
        int nbrCartesPourPile = 0;

        switch (this.nbrJoueurs) {
            case 2:
                nbrCartesPourPile = 24;
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
        int nbrdeTour = (nbrJoueurs == 2) ? 6 : 12;
        initialisationPartie[0] = nbrCartesPourPile;
        initialisationPartie[1] = nbrCarteAPiocher;
        initialisationPartie[2] = nbrCouronnes;
        initialisationPartie[3] = nbrdeTour;

        System.out.println(",nbr de carte a piocher" + nbrCarteAPiocher);
        return initialisationPartie;
    }

}

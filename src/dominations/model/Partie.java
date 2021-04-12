package dominations.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Partie {
    private  Joueur[] joueur;
    private Joueur tourJoueur;
    private Pile pile;
    private CartesEnJeu cartesEnJeu;
    private final int NBRTOUR=12;
    private int tourActuel;
    private CartesJouer cartesJouees;
    public  Partie(){
        int[] initialisationPartie=initialisationPartie();// c'est un tableau contenant trois valeurs :  nbr de joueurs, nbr carte pour la pile, nbr carte a piocher
        List <Joueur> listeJoueurs=creerJoueurs(initialisationPartie[0]);
       // afficherListeJoueurs(listeJoueurs);
        int i=0;
        while (i<20) {
            Pile pile = new Pile(initialisationPartie[1], initialisationPartie[2]);
            // afficherListeCartesPile(pile.getCartesPile());
            CartesEnJeu cartesEnJeu = new CartesEnJeu(pile);
            //System.out.println(cartesEnJeu.getNbrCartesEnJeuRestantes());

            cartesEnJeu.getCartesPile();
            i++;
        }



       // System.out.println(pile.getNombreCartesRestantes());



    };

    private void afficherListeJoueurs(List <Joueur> listeJoueurs){
        listeJoueurs.get(0).setTour(true);
        int j;
        System.out.println("");
        for (j=0;j<listeJoueurs.size();j++){
            System.out.println(listeJoueurs.get(j).getInfosJoueur());
            System.out.println("-------");
        }
    }

    private  void afficherListeCartesPile(List <Carte> listeCartesPile){
        int j;
        System.out.println("");
        for (j=0;j<listeCartesPile.size();j++){
            System.out.println(listeCartesPile.get(j).getInfoCarte());
        }
    }

    /* ***********
    La fonction crerJoueurs permet de créer le nombre joueurs correspondant à son unique argument.
    Elle retourne une liste de Joueur

     */

    private   List<Joueur> creerJoueurs(int nbrJoueurs){
        Couleur couleurRoyaume;
        Scanner scanner;
        String nomJoueurs;
        Joueur intermediaire;
        Royaume royaume;
        scanner=new Scanner(System.in);
        List <Joueur> listeJoueurs=new ArrayList<>();
        int j;
        for (j=1;j<nbrJoueurs+1;j++){
            if(j==1){couleurRoyaume=Couleur.BLEU;}
            else if (j==2){couleurRoyaume=Couleur.ROUGE;}
            else if(j==3){couleurRoyaume=Couleur.VERT;}
            else {couleurRoyaume=Couleur.JAUNE;}
            System.out.print("nom du joueur "+j+ " : ");
            nomJoueurs=scanner.nextLine();
            royaume=new Royaume(couleurRoyaume);
            intermediaire=new Joueur(nomJoueurs,royaume);
            listeJoueurs.add(intermediaire);

        }
        return listeJoueurs;
    }

    /*
    La fonction  initialisationPartie() permet d'initialiser une partie, elle retourne un tableau
     contenant trois valeurs :  nbr de joueurs, nbr carte pour la pile, nbr carte a piocher
    */

    private  int[] initialisationPartie() {
        int[] initialisationPartie= new int[3];
        int nbrCartesPourPile=0;
        boolean nbrCorrect=true;
        System.out.print("nombre de joueurs : ");
        Scanner scanner = new Scanner(System.in);
        int nbrJoueurs=scanner.nextInt();

        switch (nbrJoueurs){
            case 2:
               nbrCartesPourPile=24;
                break;
            case 3:
                nbrCartesPourPile=36;
                break;
            case 4:
                nbrCartesPourPile=48;
                break;
            default:
                break;
        }

        int nbrCarteAPiocher=(nbrJoueurs==3)?3:4;
        initialisationPartie[0]=nbrJoueurs;
        initialisationPartie[1]=nbrCartesPourPile;
        initialisationPartie[2]=nbrCarteAPiocher;
        return initialisationPartie;
    }

    public Joueur getToutJoueur() {
        return tourJoueur;
    }

    public void setToutJoueur(Joueur toutJoueur) {
        this.tourJoueur = toutJoueur;
    }
}

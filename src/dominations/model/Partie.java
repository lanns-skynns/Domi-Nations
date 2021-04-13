package dominations.model;
import java.util.*;

public class Partie {
    private  Joueur[] joueur;
    private Joueur tourJoueur;
    private Pile pile;
    private CartesEnJeu cartesEnJeu;
    private final int NBRTOUR=12;
    private int tourActuel;
    private CartesJouer cartesJouees;
   // private   List <Joueur> aQuileTour;
    public  Partie(){
        int[] initialisationPartie=initialisationPartie();// c'est un tableau contenant trois valeurs :  nbr de joueurs, nbr carte pour la pile, nbr carte a piocher; nbr couronne
        List <Joueur> listeJoueurs=creerJoueurs(initialisationPartie[0],initialisationPartie[3]);
        System.out.println(listeJoueurs.get(0).getNbrCouronnes());
        Pile pile = new Pile(initialisationPartie[1], initialisationPartie[2]);
        CartesEnJeu cartesEnJeu = new CartesEnJeu(pile);

        //tour 1
       // List<Carte> carteEGN=cartesEnJeu.nouvellesCartes();
        List <Joueur> ordreJoueurs= ordreJoueurs(listeJoueurs);
        Map pourTourSuivant=new HashMap();
        int t;
        List <Joueur> quandJouer= new ArrayList<>();

        int tourActuel=1;
        List <Carte> carteEGN;
        List <Carte> temoins ;

        List<Joueur> aQuileTour= tourDeQUi(pourTourSuivant);
        while(tourActuel<NBRTOUR+1) {

             carteEGN=cartesEnJeu.nouvellesCartes();
             temoins = new ArrayList<>(carteEGN);
             List <Integer> numeroCartesTemoins=numeroCarteTemoin(temoins);
            afficherListeCartesPile(temoins);
            System.out.println(pile.getNombreCartesRestantes());
            t=0;

            for (t=0;t<ordreJoueurs.size();t++){
                tourJoueur=ordreJoueurs.get(t);
               // tourJoueur.setTour(true);
                afficherOrdreListeJoueurs(ordreJoueurs);
                Scanner scanner=new Scanner(System.in);
                affichageCartesEnJeu(carteEGN);
                System.out.println(ordreJoueurs.get(t).getNom()+"Saisissez la position de la carte que vous souhaitez :  entre 1 et "+ cartesEnJeu.nbrCartesEnJeuRestantes );
                int choix=scanner.nextInt();
                // System.out.println(carteEG1.remove(carteEG1.indexOf(choix)));
             //   tourJoueur.setTour(false);
                if ((t < ordreJoueurs.size() - 1)) {
                    System.out.print("Bien jouée, maintenant autour de : " + ordreJoueurs.get(t + 1).getNom()+ " ");

                } else {
                    System.out.println("Passons au tour suivant ");
                }
                int k;
                for (k=0;k<initialisationPartie[2];k++){
                    if(numeroCartesTemoins.get(k)==carteEGN.get(choix-1).getNumeroDeCarte()){
                        System.out.println(numeroCartesTemoins.indexOf(temoins.get(k).getNumeroDeCarte()));
                        System.out.println("huùù "+carteEGN.get(choix-1).getNumeroDeCarte());
                        pourTourSuivant.put(numeroCartesTemoins.indexOf(carteEGN.get(choix-1).getNumeroDeCarte()),ordreJoueurs.get(t));
                    }
                }
                cartesEnJeu.carteChoisie(choix-1);
            }
            temoins.clear();
            ordreJoueurs.clear();
            ordreJoueurs=tourDeQUi(pourTourSuivant);
            tourActuel++;
        }
    }

    private List<Joueur> tourDeQUi(Map joueur){
        List <Joueur> tourDeQui=new ArrayList<>();
        int i;
        for (i=0;i<joueur.size();i++){
            tourDeQui.add((Joueur) joueur.get(i));
        }
        return  tourDeQui;
    }

    private List<Integer> numeroCarteTemoin(List <Carte> l){
        List <Integer> lInt=new ArrayList<>();
        Map map=new HashMap();

        int j;
        for (j=0;j<l.size();j++){
            lInt.add(l.get(j).getNumeroDeCarte());
        }
        return  lInt;
    }

    private  void affichageCartesEnJeu(List<Carte> cartesEnJeu){
        int j;
        System.out.print("Les cartes sont les suivantes : ");
        for (j=0;j<cartesEnJeu.size();j++){
            System.out.print(cartesEnJeu.get(j).getNumeroDeCarte()+" - ");
        }
        System.out.println(" ");
    }

    private List <Joueur>  ordreJoueurs(List <Joueur> listeJoueurs){
        Map map=new HashMap();
        int i;
        List<Joueur> listeJoueursOrdonnee=new ArrayList<>();
        List <Integer> intermediaire =new ArrayList<>();
        for (i=0;i<listeJoueurs.size();i++){
           intermediaire.add(i);
            map.put(i,listeJoueurs.get(i));
        }
        Collections.shuffle(intermediaire);
        int j;
        for(j=0;j<intermediaire.size();j++){
            listeJoueursOrdonnee.add((Joueur) map.get(intermediaire.get(j)));
        }

        return listeJoueursOrdonnee ;
    }

    private void afficherOrdreListeJoueurs(List<Joueur> listeJoueurs){
        int j;
        System.out.print("Ordre de jeu des joueurs : ");
        for (j=0;j<listeJoueurs.size();j++){
            System.out.print(""+listeJoueurs.get(j).getNom()+" || ");
        }
        System.out.println(" ");
    }

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

    private   List<Joueur> creerJoueurs(int nbrJoueurs, int nbrCouronnes){
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
            intermediaire=new Joueur(nomJoueurs,royaume,nbrCouronnes);
            listeJoueurs.add(intermediaire);

        }
        return listeJoueurs;
    }

    /*
    La fonction  initialisationPartie() permet d'initialiser une partie, elle retourne un tableau
     contenant trois valeurs :  nbr de joueurs, nbr carte pour la pile, nbr carte a piocher
    */

    private  int[] initialisationPartie() {
        int[] initialisationPartie= new int[4];
        int nbrCartesPourPile=0;
        boolean nbrCorrect=true;
        System.out.print("nombre de joueurs : ");
        Scanner scanner = new Scanner(System.in);
        int nbrJoueurs=scanner.nextInt();

        switch (nbrJoueurs){
            case 2:
               nbrCartesPourPile=48;
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

        int nbrCouronnes=(nbrJoueurs==2)?2:1;
        int nbrCarteAPiocher=(nbrJoueurs==3)?3:4;
        initialisationPartie[0]=nbrJoueurs;
        initialisationPartie[1]=nbrCartesPourPile;
        initialisationPartie[2]=nbrCarteAPiocher;
        initialisationPartie[3]=nbrCouronnes;
        return initialisationPartie;
    }

    public Joueur getToutJoueur() {
        return tourJoueur;
    }

    public void setToutJoueur(Joueur toutJoueur) {
        this.tourJoueur = toutJoueur;
    }
}

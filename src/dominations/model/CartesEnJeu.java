package dominations.model;
import java.util.*;

public class CartesEnJeu extends Pile {
   protected  int nbrCartesEnJeuRestantes;
   protected Pile pileSource;

     /* **************
        Constructeur de la classe
     ************** */


    public CartesEnJeu(Pile pileSource) {
        super(0, 0);
        this.pileSource=pileSource;
        //this.cartesPile =  nouvellesCartes();

    }

    /* **********
        carteChoisie() : Cette méthode permet de supprimer dans la pile de carteEnJeu la carte
        qu'un joueur a choisi de  placer dans son royaume, elle rétourne la carte choisi.
     ********* */

    public Carte  carteChoisie(int choix){
        Carte carteChoisie=cartesPile.get(choix);// récupération de la carte choisi par le joueur,
        cartesPile.remove(cartesPile.get(choix));// suppression de cette carte de la pile de carte en jeu
        nbrCartesEnJeuRestantes=cartesPile.size();// modification du nombre de cartes restantes dans la pile de carte en jeu
        return carteChoisie;
    }

    /* **************
        getCartesPile() :  Modification de la méthode getCartesPile créée dans Carte
    ************** */
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

    /* ************
        nouvellesCartes() : Cette méthode  permet de picoher des nouvelles cartes
        et de les classer par ordre croissant avant de les retourner.
    ************ */

    public List<Carte> nouvellesCartes() {
        List<Carte> cartesPiochees = new ArrayList<Carte>(this.pileSource.piocherCartes());//piochage des cartes
        List<Carte> cartesPiocheesTriees=new ArrayList<>(); // liste qui contiendra les cartes triées
        int[] intermediaire= new int[cartesPiochees.size()];// liste qui contiendra les numéros des cartes piochées afin de les trier
        Map <Integer,Carte> map=new HashMap<>();// Map qui associera chaque numéro de cartes à cartes
        int i;
        for (i=0;i<cartesPiochees.size();i++){
            intermediaire[i]=cartesPiochees.get(i).getNumeroDeCarte();// remplissage de la liste intermédiaire
            map.put(intermediaire[i],cartesPiochees.get(i));// remplissage du map avec un numéro de carte et la carte associée
        }
       Arrays.sort(intermediaire);
        int j;
        for(j=0;j<intermediaire.length;j++){
            cartesPiocheesTriees.add(map.get(intermediaire[j]));// ajout des cartes dans l'ordre croissant dans le liste de cartes à retourner
        }
        this.cartesPile = cartesPiocheesTriees;
        nbrCartesEnJeuRestantes=cartesPile.size();//MAJ du nombre de cartes restantes dans la pile de cartes en jeu.
        return cartesPile;
    }

    // Trois cartes placées au milieu du plateau sur lesquelles les joueurs posent
    // les rois.

}

package dominations.model;

public class Joueur {
    private String nom;
    private Royaume royaume;
    private Couleur couleur;
    private int nbrCouronnes;
    private int score = 0;
    private boolean tour = false;
    private int[] coordonneesChateau = new int[2];

    /* **************
        Constructeur de la classe
    ************** */

    public Joueur(String nom, Royaume royaume, int nbrCouronnes) {
        this.nom = nom;
        this.royaume = royaume;
        this.couleur = royaume.getCouleur();
        this.nbrCouronnes = nbrCouronnes;
        setChateau(4, 4);// passager, les joeurs devront placer leurs chateaux eux même;
    }

    public String getNom() {
        return nom;
    }

    public Couleur getCouleur() {
        return this.couleur;
    }

    public int getScore() {
        return this.score;
    }

    public boolean getTour() {
        return this.tour;
    }

    public void setTour(boolean tour) {
        this.tour = tour;
    }


    /* **********
         Cette fonction retourne un string qui est une concaténation des infos concernant la carte :
     ********* */

    public String getInfosJoueur() {
        return "nom :" + this.nom + "  \n" + "couleur royaume  : " + this.couleur + "\nscore : " + this.score + " \n" + "tour : " + this.tour;
    }

    public int getNbrCouronnes() {
        return nbrCouronnes;
    }

    /* ***********
        setChateau(int lon, int lat) : Cette méthode permet de placer le chateau dans le royaume
    *********** */
    public void setChateau(int lon, int lat) {
        this.royaume.getCellule(lon, lat).setPaysage(new Paysage("chateau"));//surtout ne pas modifie le type chateau
        this.coordonneesChateau[0] = lon;
        this.coordonneesChateau[1] = lat;
    }

    public Royaume getRoyaume() {
        return this.royaume;
    }

    public int[] getCoordonneesChateau() {
        if( this.coordonneesChateau.length!=0 && this.coordonneesChateau !=null) {
            return this.coordonneesChateau;
        }
        return  null;
    }

    //public CartesEnJeu choxCarte(){}

}

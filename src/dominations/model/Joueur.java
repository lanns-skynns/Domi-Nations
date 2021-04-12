package dominations.model;

public class Joueur {
    private String nom;
    private Royaume royaume;
    private Couleur couleur;
    private int score=0;
    private boolean tour=false;
    private int[] coordonneesChateau = new int[2];

    public  Joueur(String nom,Royaume royaume){
        this.nom=nom;
        this.royaume=royaume;
        this.couleur=royaume.getCouleur();
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
         this.tour=tour;
    }

    public String  getInfosJoueur(){
        return "nom :"+ this.nom+"  \n" + "couleur royaume  : "+this.couleur +"\nscore : "+ this.score+ " \n"+ "tour : "+this.tour;
    }

    public void setChateau(int lon, int lat){
        this.royaume.getCellule(lon, lat).setPaysage(new Paysage("chateau"));
        this.royaume.updateGrille();
        this.coordonneesChateau[0] = lon;
        this.coordonneesChateau[1] = lat;
    }

    public int[] getCoordonneesChateau(){
        return this.coordonneesChateau;
    }

    //public CartesEnJeu choxCarte(){}

}

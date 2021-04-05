package dominations.model;

public class Joueur {
    private String nom;
    private Royaume royaume;
    private Couleur couleur;
    private int score=0;
    private boolean tour=false;

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



    //public CartesEnJeu choxCarte(){}

}

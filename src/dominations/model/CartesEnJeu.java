package dominations.model;

public class CartesEnJeu  {
    private CartesJouer[] carteEnJeu;
    private String[] roi; // tu ne crois pas qu'on devrait prendre un tableau de roi de préférence, comme ça, on associe roi et cartes en jeu

    public  CartesEnJeu(){}

    public void setRoi(String[] roi){
        this.roi=roi;
    }

    public String[] getRoi(){
        return this.roi;
    }
}

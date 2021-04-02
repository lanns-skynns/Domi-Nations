package dominations.model;

public class Royaume {
    private Carte[] carte;
    private  Couleur couleur;
    private Cellule[] grille;

    public  Royaume(){}

    public Couleur couleur(){
        return  couleur;
    }

    public  int score(){
        return 1;
    }
}



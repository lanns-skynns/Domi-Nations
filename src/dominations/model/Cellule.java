package dominations.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Cellule  {

    private Paysage paysage;
    private final int longitude; // abscisse
    private final int latitude; // ordonnées
    private int numeroCarte;
    private boolean caseVide;
    private final Royaume royaume; // Royaume auquel appartient la cellule


    public  Cellule(Royaume royaumeRef, int lon, int lat){
        this.royaume = royaumeRef;
        this.longitude = lon;
        this.latitude = lat;
        this.caseVide = true;
        this.paysage = new Paysage("vide");
    }

    public int[] getPosition(){
        int[] position = {this.longitude, this.latitude};

        return position;
    }

    public boolean isEmpyy(){
        //Vrai si vide, faux si occupée
        return this.caseVide;
    }

    public void setPaysage(Paysage nouveauPaysage){
        this.paysage=nouveauPaysage;
    };

    public Paysage getPaysage(){

        return this.paysage;
    }

    public List<String> getVoisins(){
        //Retourne le type des casses voi

        String droite;
        String gauche;
        String haut;
        String bas;

        //droite
        if(this.longitude < 4){
            droite = "pas vide";
        } else {
            droite = "vide";
        }

        //gauche
        if(this.longitude > 0){
            gauche = "pas vide";
        } else {
            gauche = "vide";
        }

        //haut
        if(this.latitude < 4){
            haut = "pas vide";
        } else {
            haut = "vide";
        }
        //bas
        if(this.latitude > 0){
            bas = "pas vide";
        } else {
            bas = "vide";
        }

        //retourne les types en partant du haut dans l'ordre des aiguilles
        List<String> voisins = Arrays.asList(haut, droite, bas, gauche);

        return voisins;
    }

    public int getNumeroCarte(){
        return this.numeroCarte;
    }

}

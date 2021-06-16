package dominations.model;

import java.util.Arrays;
import java.util.List;

/*
changePaysageType ??
 */
public class Cellule {

    private final int longitude; // abscisse / Numero de colonne
    private final int latitude; // ordonnées / Numero de ligne
    private final Royaume royaume; // Royaume auquel appartient la cellule
    private final int[] coordonnees = new int[2];
    private final int numeroCarte;
    private Paysage paysage;
    private boolean caseVide;


    /* **************
        Constructeur de la classe
    ************** */

    public Cellule(Royaume royaumeRef, int lon, int lat) {
        this.royaume = royaumeRef;
        this.longitude = lon;
        this.latitude = lat;
        this.caseVide = true;
        this.paysage = new Paysage("vide");
        this.numeroCarte = 666;
        this.coordonnees[0] = lon;
        this.coordonnees[1] = lat;
    }

    public int[] getPosition() {
        int[] position = {this.latitude, this.longitude};

        return position;
    }

    public boolean isEmpty() {
        //Vrai si vide, faux si occupée
        return this.caseVide;
    }

    public void changePaysageType(String typePaysage) {
        this.paysage.changeType(typePaysage);

        changeCellState();
    }

    public void changeCellState() {
        //Vérifie si la case est vide et change la valeur de "caseVide" en conséquence

        this.caseVide = this.paysage.getType() == "x";
    }

    public Paysage getPaysage() {

        return this.paysage;
    }

    public void setPaysage(Paysage nouveauPaysage) {
        this.paysage = nouveauPaysage;
        changeCellState();
    }

    public List<String> getVoisins() {
        //Retourne le type des casses voisines dans une liste de cellules.
        //dans l'ordre suivant: [haut droit bas gauche].
        //Si la cellule ne possède pas de case voisine en haut/bas/droite/gauche, la valeur retournée
        //Pour la case correspondante est "bordure"

        String droite;
        String gauche;
        String haut;
        String bas;

        //droite
        if (this.longitude < 8) {
            Cellule cellule = this.royaume.getCellule(this.longitude + 1, this.latitude);
            Paysage type = cellule.getPaysage();

            droite = type.getType();
        } else {
            droite = "bordure";
        }

        //gauche
        if (this.longitude > 0) {
            Cellule cellule = this.royaume.getCellule(this.longitude - 1, this.latitude);
            Paysage type = cellule.getPaysage();

            gauche = type.getType();
        } else {
            gauche = "bordure";
        }

        //bas
        if (this.latitude < 8) {
            Cellule cellule = this.royaume.getCellule(this.longitude, this.latitude + 1);
            Paysage type = cellule.getPaysage();

            bas = type.getType();
        } else {
            bas = "bordure";
        }
        //haut
        if (this.latitude > 0) {
            Cellule cellule = this.royaume.getCellule(this.longitude, this.latitude - 1);
            Paysage type = cellule.getPaysage();

            haut = type.getType();
        } else {
            haut = "bordure";
        }

        //retourne les types en partant du haut dans l'ordre des aiguilles
        List<String> voisins = Arrays.asList(haut, droite, bas, gauche);

        return voisins;
    }

    public int getNumeroCarte() {
        return this.numeroCarte;
    }

}

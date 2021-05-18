package dominations.model;

import java.util.Arrays;

public class Carte {

    private int numeroCarte;
    private int[] nombreCouronne;
    private char[] typeCarte;
    private boolean jouee;
    private int orientation;
    //0: vers le haut
    //1: vers la droite
    //2: vers le bas
    //3: vers la gauche

    /* **************
        Constructeur de la classe
    ************** */

    public Carte(int numCarte, int[] nombreCouronne, char[] typeCarte) {
        this.nombreCouronne = nombreCouronne;
        this.typeCarte = typeCarte;
        this.nombreCouronne[0] = nombreCouronne[0];
        this.nombreCouronne[1] = nombreCouronne[1];
        this.numeroCarte = numCarte;
        this.typeCarte[0] = typeCarte[0];
        this.typeCarte[1] = typeCarte[1];
    }


    public int getNumeroDeCarte() {
        return numeroCarte;
    }

    public int[] getNombreCouronne() {
        return nombreCouronne;
    }

    public char[] getTypeCarte() {
        return typeCarte;
    }

    /* **********
         Cette fonction retourne un string qui est une concaténation des infos concernant la carte :
     ********* */

    public String getInfoCarte() {
        return this.numeroCarte + ". " + this.typeCarte[0] + this.nombreCouronne[0] + "-" + this.typeCarte[1] + this.nombreCouronne[1];
    }

    /* *********
        Cette fonction split la carte en deux paysages.
     ******** */

    public Paysage[] toPaysages() {
        Paysage[] toPaysages = new Paysage[2];
        //ligne ci-dessous : creation du premier paysage et placement dans la liste toPaysages
        toPaysages[0] = new Paysage(Character.toString(this.typeCarte[0]), this.numeroCarte, this.nombreCouronne[0]);
        //ligne ci-dessous : creation du du second paysage et placement dans la liste toPaysages
        toPaysages[1] = new Paysage(Character.toString(this.typeCarte[1]), this.numeroCarte, this.nombreCouronne[1]);
        return toPaysages;
    }



}

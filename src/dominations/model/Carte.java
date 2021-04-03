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

    public Carte(int numCarte, int[] nombreCouronne, char[] typeCarte){
        this.nombreCouronne = nombreCouronne;
        this.typeCarte = typeCarte;
        this.nombreCouronne[0]=nombreCouronne[0];
        this.nombreCouronne[1]=nombreCouronne[1];
        this.numeroCarte=numCarte;
        this.typeCarte[0]=typeCarte[0];
        this.typeCarte[1]=typeCarte[1];
        this.jouee = false;
    };

    public int getNumeroDeCarte(){
        return numeroCarte;
    }

    public int[] getNombreCouronne(){
        return nombreCouronne;
    }

    public char[] getTypeCarte(){
        return typeCarte;
    }

    public String  getInfoCarte(){
        return this.numeroCarte+". "+this.typeCarte[0]+this.nombreCouronne[0]+"-"+this.typeCarte[1]+this.nombreCouronne[1];

    }
}

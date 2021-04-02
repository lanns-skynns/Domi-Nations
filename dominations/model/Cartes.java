package dominations.model;

import java.util.Arrays;
import java.util.List;

public class Cartes {

    private int numeroCarte;
    private int[] nombreCouronne;
    private char[] typeCarte;

    public Cartes(int numCarte,int[] nombreCouronne, char[] typeCarte){
        this.nombreCouronne = nombreCouronne;
        this.typeCarte = typeCarte;
        this.nombreCouronne[0]=nombreCouronne[0];
        this.nombreCouronne[1]=nombreCouronne[1];
        this.numeroCarte=numCarte;
        this.typeCarte[0]=typeCarte[0];
        this.typeCarte[1]=typeCarte[1];
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

    public void  getInfoCarte(){
        System.out.println(this.numeroCarte+". "+this.typeCarte[0]+this.nombreCouronne[0]+"-"+this.typeCarte[1]+this.nombreCouronne[1]);

    }

    public Cartes[] pourLaPile(int nbrDeCarte){
        int i;
        int carteAleatoire;
        int[] indiceDejaChoisi = new int[nbrDeCarte];
        Cartes[] pourLapioche=new Cartes[nbrDeCarte];
        for (i=0;i<nbrDeCarte;i++){
            carteAleatoire=(int)(Math.round(Math.random()*47));

            while(contains(indiceDejaChoisi,Integer.toString(carteAleatoire))){
                carteAleatoire=(int)(Math.round(Math.random()*47));
            }
            indiceDejaChoisi[i]=carteAleatoire;
            int[] nbrCouronne={this.couronne[carteAleatoire][0],this.couronne[carteAleatoire][1]};
            char [] typePaysage={this.type[carteAleatoire][0],this.type[carteAleatoire][1]};
            Cartes intermediaire=new Cartes(this.numCarte[carteAleatoire],nbrCouronne,typePaysage);
            pourLapioche[i]=intermediaire;
        }
        return pourLapioche;
    }

    private boolean contains(int[] T, String val) {
        return Arrays.toString(T).contains(val);
    }


    private  int[] numCarte(){
        int i;
        int[] numC= new int[48];
        for(i=0;i<48;i++){
            numC[i]=i;
        }
        return numC;
    }

    private int[] numCarte=numCarte();
   private int[][] couronne = {
            {0, 0},
            {0, 0},
            {0, 0},
            {0, 0},
            {0, 0},
            {0, 0},
            {0, 0},
            {0, 0},
            {0, 0},
            {0, 0},
            {0, 0},
            {0, 0},
            {0, 0},
            {0, 0},
            {0, 0},
            {0, 0},
            {0, 0},
            {0, 0},
            {1, 0},
            {1, 0},
            {1, 0},
            {1, 0},
            {1, 0},
            {1, 0},
            {1, 0},
            {1, 0},
            {1, 0},
            {1, 0},
            {1, 0},
            {1, 0},
            {1, 0},
            {1, 0},
            {1, 0},
            {1, 0},
            {1, 0},
            {0, 1},
            {0, 1},
            {0, 1},
            {0, 1},
            {1, 0},
            {0, 2},
            {0, 2},
            {0, 2},
            {0, 2},
            {2, 0},
            {0, 2},
            {0, 2},
            {0, 3},
    };
  private  char[][] type = {
            {'c', 'c'},
            {'c', 'c'},
            {'f', 'f'},
            {'f', 'f'},
            {'f', 'f'},
            {'f', 'f'},
            {'s', 's'},
            {'s', 's'},
            {'s', 's'},
            {'p', 'p'},
            {'p', 'p'},
            {'m', 'm'},
            {'c', 'f'},
            {'c', 's'},
            {'c', 'p'},
            {'c', 'm'},
            {'f', 's'},
            {'f', 'p'},
            {'c', 'f'},
            {'c', 's'},
            {'c', 'p'},
            {'c', 'm'},
            {'c', 'n'},
            {'f', 'c'},
            {'f', 'c'},
            {'f', 'c'},
            {'f', 'c'},
            {'f', 's'},
            {'f', 'p'},
            {'s', 'c'},
            {'s', 'c'},
            {'s', 'f'},
            {'s', 'f'},
            {'s', 'f'},
            {'s', 'f'},
            {'c', 'p'},
            {'s', 'p'},
            {'c', 'm'},
            {'p', 'm'},
            {'n', 'c'},
            {'c', 'p'},
            {'s', 'p'},
            {'c', 'm'},
            {'p', 'm'},
            {'n', 'c'},
            {'m', 'n'},
            {'m', 'n'},
            {'c', 'n'},
    };



}

package dominations.model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Pile {

    Map map = new HashMap();

    private int nbrCarte;
    private Carte[] cartesPile;
    private int nbrCartesRestantes;

    public Pile(int nbrCartes){
        this.nbrCarte = nbrCartes;
        cartesPile = creerPile(this.nbrCarte);
    };

    //public  Carte[] enleverCarte(){ }

    public  void melanger(){

    }

    private Carte[] creerPile(int nbrDeCarte){
        int i;
        int carteAleatoire;
        int[] indiceDejaChoisi = new int[nbrDeCarte];
        Carte[] pourLapioche=new Carte[nbrDeCarte];
        for (i=0;i<nbrDeCarte;i++){
            carteAleatoire=(int)(Math.round(Math.random()*47));

            while(contains(indiceDejaChoisi,Integer.toString(carteAleatoire))){
                carteAleatoire=(int)(Math.round(Math.random()*47));
            }

            indiceDejaChoisi[i]=carteAleatoire;
            int[] nbrCouronne={this.couronne[carteAleatoire][0],this.couronne[carteAleatoire][1]};
            char [] typePaysage={this.type[carteAleatoire][0],this.type[carteAleatoire][1]};
            Carte intermediaire=new Carte(this.numCarte[carteAleatoire],nbrCouronne,typePaysage);
            pourLapioche[i]=intermediaire;
        }
        return pourLapioche;
    }

    public Carte[] getCartesPile(){
        return cartesPile;
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

    public int getNombreCartesRestantes(){
        return nbrCartesRestantes;
    }





}





package dominations.model;

public class Cellule  {

    private  Paysage paysage;
    private int longitude;
    private  char latitude;
    private int nnumeroCarte;

    public  Cellule(){};

    public String getPosition(){
        return "a";
    }

    public boolean isEmpyy(){
        return true;
    }

    public void serPaysage(Paysage nouveauPaysage){
        this.paysage=nouveauPaysage;
    };

    public int getNumeroCarte(){
        return this.nnumeroCarte;
    }

    public String getCellule(){
        return this.latitude+""+this.latitude;
    }



}

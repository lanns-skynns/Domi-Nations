package dominations.model;

public class Paysage {
    private String typePaysage;
    private int nbrDeCouronne;

    public Paysage(String type){

        this.typePaysage = type;

    };

    public String getType(){
        return this.typePaysage;
    }

    public void changeType(String newPaysage){
        this.typePaysage = newPaysage;
    }

}

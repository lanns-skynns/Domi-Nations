package dominations.model;

public class Paysage {
    private String typePaysage;
    private int nbrDeCouronne;
    private int numeroPaysage;
    private int[] celluleCible;//cellule sur laquelle le joueur souhaite placer son paysage

    /* **************
        Constructeur de la classe
    ************** */

    public Paysage(String type, int numeroPaysage, int nbrDeCouronne) {
        this.typePaysage = type;
        this.nbrDeCouronne = nbrDeCouronne;
        this.numeroPaysage = numeroPaysage;
    }

    public Paysage(String type) {
        this.typePaysage = type;
    }

    public String getType() {
        return this.typePaysage;
    }

    public int getNbrDeCouronne() {
        return nbrDeCouronne;
    }

    public void changeType(String newPaysage) {
        this.typePaysage = newPaysage;
    }

    public int[] getCelluleCible() {
        return this.celluleCible;
    }

    public void setCelluleCible(int[] cc) {
        this.celluleCible = cc;
    }

}

package dominations.model;

public class Royaume {
    private  Couleur couleur;
    private Cellule[][] grilleCellules;

    public  Royaume(Couleur couleur){
        this.couleur=couleur;
        creerGrille();
    }

    public Couleur getCouleur(){
        return  couleur;
    }

    public  int score(){
        return this.score();
    }

    public void creerGrille(){
        //création de la grille
        Cellule[][] grille = new Cellule[5][5];

        int i;
        int j;
        for(i = 0; i<5; i++){
            for(j = 0; j<5; j++){
                Cellule cell = new Cellule(this, j, i);
                cell.setPaysage(new Paysage(""+i + j+""));
                grille[i][j] = cell;
            }
        }
        this.grilleCellules = grille;
    }

    public Cellule getCellule(int lon, int lat){
        Cellule cell = this.grilleCellules[lat][lon];

        return cell;
    }

    public void afficherGrille(){
        int i;
        int j;
        for(i = 0; i<5; i++){
            System.out.print("| ");
            for(j = 0; j<5; j++){
                Cellule c = this.grilleCellules[i][j];
                Paysage paysage = c.getPaysage();
                String type = paysage.getType();

                System.out.print(type + " | " );
            }
            System.out.println("");
        }
    }
}



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
        //création d'une grille de 5x5 avec toutes les cases vides.
        Cellule[][] grille = new Cellule[5][5];

        int i;
        int j;
        for(i = 0; i<5; i++){
            for(j = 0; j<5; j++){
                Cellule cell = new Cellule(this, j, i);
                cell.setPaysage(new Paysage("vide"));
                grille[i][j] = cell;
            }
        }
        this.grilleCellules = grille;
    }

    public Cellule getCellule(int lon, int lat){
        Cellule cell = this.grilleCellules[lat][lon];

        return cell;
    }

    public Cellule[] getLine(int ligne){
        Cellule[] ligneCell = this.grilleCellules[ligne];

        for(Cellule cell : ligneCell){
            System.out.print(cell.getPaysage().getType() + " ");
        }

        return ligneCell;

    }

    public void afficherTypesGrille(){
        //Affiche une grille des types de chaque case de la grille
        int i;
        int j;
        System.out.println("Paysages de la grille: ");
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

    public void afficherEtatGrille(){
        //Affiche une grille des types de chaque case de la grille
        int i;
        int j;
        System.out.println("Cases vides de la grille: ");
        for(i = 0; i<5; i++){
            System.out.print("| ");
            for(j = 0; j<5; j++){
                Cellule c = this.grilleCellules[i][j];

                System.out.print(c.isEmpty() + " | " );

            }
            System.out.println("");
        }
    }

    public void updateGrille(){
        //update l'etat de toutes les cellule de la grille
        int i;
        int j;

        for(i = 0; i<5; i++){
            for(j = 0; j<5; j++){
                this.grilleCellules[i][j].changeCellState();
            }
        }

        afficherEtatGrille();
    }
}



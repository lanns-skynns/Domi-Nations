package dominations.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Royaume {
    private Couleur couleur;
    private Cellule[][] grilleCellules;

    /* **************
        Constructeur de la classe
    ************** */

    public Royaume(Couleur couleur) {
        this.couleur = couleur;
        creerGrille();
    }

    public Couleur getCouleur() {
        return couleur;
    }

    public int score() {
        return this.score();
    }

    public void creerGrille() {
        //création d'une grille de 5x5 avec toutes les cases vides.
        Cellule[][] grille = new Cellule[5][5];

        int i;
        int j;
        for (i = 0; i < 5; i++) {
            for (j = 0; j < 5; j++) {
                Cellule cell = new Cellule(this, j, i);
                cell.setPaysage(new Paysage("vide"));
                grille[i][j] = cell;
            }
        }
        this.grilleCellules = grille;
    }

    /* **********
        placerCarte(Paysage[] nouveauPaysage) : permet de placer une carte dans la royaume,
        elle modifie donc deux cellules adjacent
    ********** */

    public boolean placerCarte(Paysage[] nouveauPaysage) {
        boolean correct=bonPlacement(nouveauPaysage);
        if(correct) {
            int longp1 = nouveauPaysage[0].getCelluleCible()[0];// recup longitude paysage 1
            int latp1 = nouveauPaysage[0].getCelluleCible()[1];// recup latitude paysage 1
            int longp2 = nouveauPaysage[1].getCelluleCible()[0];//  recup longitude paysage 2
            int latp2 = nouveauPaysage[1].getCelluleCible()[1]; // recup latitude paysage 2
            this.grilleCellules[longp1][latp1].setPaysage(nouveauPaysage[0]);//ajout du paysage dans la bonne cellule
            this.grilleCellules[longp2][latp2].setPaysage(nouveauPaysage[1]);// ajout du seconde paysage dans la bonne cellule
            return  true;
        }
        else{
            return false;
        }
    }

    public Cellule getCellule(int lon, int lat) {
        Cellule cell = this.grilleCellules[lat][lon];

        return cell;
    }

    public Cellule[] getLine(int ligne) {
        Cellule[] ligneCell = this.grilleCellules[ligne];

        for (Cellule cell : ligneCell) {
            System.out.print(cell.getPaysage().getType() + " ");
        }

        return ligneCell;

    }

    public void afficherTypesGrille() {
        //Affiche une grille des types de chaque case de la grille
        int i;
        int j;
        System.out.println("Paysages de la grille: ");
        for (i = 0; i < 5; i++) {
            System.out.print("| ");
            for (j = 0; j < 5; j++) {
                Cellule c = this.grilleCellules[i][j];
                Paysage paysage = c.getPaysage();
                String type = paysage.getType();

                System.out.print(type + " | ");
            }
            System.out.println("");
        }
    }

    public void afficherEtatGrille() {
        //Affiche une grille des types de chaque case de la grille
        int i;
        int j;
        System.out.println("Cases vides de la grille: ");
        for (i = 0; i < 5; i++) {
            System.out.print("| ");
            for (j = 0; j < 5; j++) {
                Cellule c = this.grilleCellules[i][j];
                System.out.print(c.isEmpty() + " | ");
            }
            System.out.println("");
        }
    }

    public void updateGrille() {
        //update l'etat de toutes les cellule de la grille
        int i;
        int j;

        for (i = 0; i < 5; i++) {
            for (j = 0; j < 5; j++) {
                this.grilleCellules[i][j].changeCellState();
            }
        }

        afficherEtatGrille();
    }

    /* **********
        bonPlacement(Paysage[] paysages) :  Cette méthode permet de vérifier si une carte est correctement
        placer dans le royaume
     */

    private boolean bonPlacement(Paysage[] paysages) {
        // récupération des coordonnées de la cellule cible de chaque paysages
        int[] tabCellulePaysage1 = paysages[0].getCelluleCible();
        int[] tabCellulePaysage2 = paysages[1].getCelluleCible();

        //si au moins l'une des deux paysages n'est pas sur le plateau, la méthode retourne false
        if ((tabCellulePaysage1[0] == -1) || (tabCellulePaysage2[0] == -1)) {
            return false;
        }

        // vérifiacation de l'atat des cellules cibles : ie, si elles sont vides
        Cellule cellPaysage1 = grilleCellules[tabCellulePaysage1[0]][tabCellulePaysage1[1]];
        Cellule cellPaysage2 = grilleCellules[tabCellulePaysage2[0]][tabCellulePaysage2[1]];
        boolean etatCelluleP1 = cellPaysage1.isEmpty();
        boolean etatCelluleP2 = cellPaysage2.isEmpty();

        if (etatCelluleP1 && etatCelluleP2) {// si elles sont toutes les deux vides, ie,
            List<String> listeVoisinsP1 = cellPaysage1.getVoisins();
            List<String> listeVoisinsP2 = cellPaysage2.getVoisins();
            for (String voisin : listeVoisinsP1) {
                // si le paysage a le chateau parmi ses voisin, on retourne true
                if (voisin.equals("chateau")) {
                    return true;
                }
                //si l'un des voisin est est du même type que le paysage, on retourne true
                if (voisin.equals(paysages[0].getType())) {
                    return true;
                }
            }
            for (String voisin : listeVoisinsP2) {
                if (voisin.equals("chateau")) {
                    return true;
                }
                if (voisin.equals(paysages[1].getType())) {
                    return true;
                }
            }
        }
        //si au moins une cellule cible n'est pas vides, on retourne false
        return false;
    }

    public void genererRoyaumeAleatoire(){
        //fonction qui génère un royaume aléatoire avce tous les paysages remplis (deboggage)

        String[] typesDispo = {"A", "B", "C", "D"};
        int[] couronnesDispo = {0, 1, 2};

        int i;
        int j;

        for(i=0; i<5; i++){
            for(j=0; j<5; j++){
                Random r=new Random();
                int randomType=r.nextInt(typesDispo.length);
                String type = typesDispo[randomType];

                int randomCour=r.nextInt(couronnesDispo.length);
                int couronne = couronnesDispo[randomCour];

                Paysage paysage = new Paysage(type, 1, couronne);

                this.grilleCellules[i][j].setPaysage(paysage);
            }
        }


    }

    public List<List<Integer>> detectionVoisinsCellule(int i, int j, int direction, List<List<Integer>> dejaVisites){
        //Fonction regroupant les cellules adjacentes de meme type en groupes à partir d'une cellule
        /*direction est la position relative à la cellule précédente dans la recurcivité:
        0 -> premiere fois qu'on execute la fonction
        1 -> la cellule précédente est a droite
        2 -> la cellule précédente est en haut
        3 -> la cellule precedente est a gauche
        4 -> la cellule precedente est en bas
        cela empeche d'avoir une boucle ou la recurcivité fait gauche/droite en boucle.
         */

        List<Integer> coordonneesCellule =new ArrayList<Integer>();
        coordonneesCellule.add(i);
        coordonneesCellule.add(j);

        Cellule cellule = this.getCellule(i, j);
        String typeCellule = cellule.getPaysage().getType();

        List<String> voisins = cellule.getVoisins();


        List<List<Integer>> coords = new ArrayList<>();
        coords.add(coordonneesCellule);

        System.out.println(coords);


        if(typeCellule.toString() == voisins.get(1).toString() && direction != 1){
            //droite
            Integer[] coord = {i+1, j};
            List<Integer> coordList = Arrays.asList(coord);

            if(!dejaVisites.contains(coordList)) {

                //coords.add(coordList);
                dejaVisites.add(coordList);

                //System.out.println(Arrays.toString(coord));
                //System.out.println("----------");

                //System.out.println(dejaVisites + "e");
                List<List<Integer>> results = detectionVoisinsCellule(coord[0], coord[1], 3, dejaVisites);
                coords.addAll(results);
            }
        }

        if(typeCellule.toString() == voisins.get(2).toString() && direction != 4){
            //bas
            Integer[] coord = {i, j+1};
            List<Integer> coordList = Arrays.asList(coord);

            if(!dejaVisites.contains(coordList)) {

                //coords.add(coordList);
                dejaVisites.add(coordList);

                //System.out.println(Arrays.toString(coord));
                //System.out.println("----------");

                //System.out.println(dejaVisites + "e");
                List<List<Integer>> results = detectionVoisinsCellule(coord[0], coord[1], 2, dejaVisites);
                coords.addAll(results);
            }
        }

        if(typeCellule.toString() == voisins.get(3).toString() && direction != 3){
            //gauche
            Integer[] coord = {i-1, j};
            List<Integer> coordList = Arrays.asList(coord);

            if(!dejaVisites.contains(coordList)) {

                //coords.add(coordList);
                dejaVisites.add(coordList);

                //System.out.println(Arrays.toString(coord));
                //System.out.println("----------");
                //System.out.println(dejaVisites + "e");
                List<List<Integer>> results = detectionVoisinsCellule(coord[0], coord[1], 1, dejaVisites);
                coords.addAll(results);
            }

        }

        if(typeCellule.toString() == voisins.get(0).toString() && direction != 2){
            //haut
            Integer[] coord = {i, j-1};
            List<Integer> coordList = Arrays.asList(coord);

            if(!dejaVisites.contains(coordList)) {

                //coords.add(coordList);
                dejaVisites.add(coordList);

                //System.out.println(Arrays.toString(coord));
                //System.out.println("----------");
                //System.out.println(dejaVisites + "e");
                List<List<Integer>> results = detectionVoisinsCellule(coord[0], coord[1], 4, dejaVisites);
                coords.addAll(results);
            }

        }

        return coords;

    }

}

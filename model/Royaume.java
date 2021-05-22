package dominations.model;

import java.util.*;

public class Royaume {
    private Couleur couleur;
    private Cellule[][] grilleCellules;
    private int longMin;
    private int longMax;
    private int latMin;
    private int latMax;
    private int [] bordureRoyaume = {0,8,0,8};//longi min, lonig max,lati min, lati max



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
        Cellule[][] grille = new Cellule[9][9];

        int i;
        int j;
        for (i = 0; i < 9; i++) {
            for (j = 0; j < 9; j++) {
                Cellule cell = new Cellule(this, j, i);
                cell.setPaysage(new Paysage("x"));
                grille[i][j] = cell;
            }
        }
        this.grilleCellules = grille;
    }

    /* **********
        placerCarte(Paysage[] nouveauPaysage) : permet de placer une carte dans la royaume,
        elle modifie donc deux cellules adjacent
    ********** */

    public void setLongMax(int longMax) {
        this.longMax = longMax;
    }

    public int[] getBorduresRoyaume() {
        return this.bordureRoyaume;
    }

    public void setLongMin(int longMin) {
        this.longMin = longMin;
    }

    public void setLatMin(int latMin) {
        this.latMin = latMin;
    }

    public void setLatMax(int latMax) {
        this.latMax = latMax;
    }

    public boolean placerCarte(Paysage[] nouveauPaysage) {
        boolean correct = bonPlacement(nouveauPaysage);
        if (correct) {
            int longp1 = nouveauPaysage[0].getCelluleCible()[0];// recup longitude paysage 1
            int latp1 = nouveauPaysage[0].getCelluleCible()[1];// recup latitude paysage 1
            int longp2 = nouveauPaysage[1].getCelluleCible()[0];//  recup longitude paysage 2
            int latp2 = nouveauPaysage[1].getCelluleCible()[1]; // recup latitude paysage 2
            this.grilleCellules[latp1][longp1].setPaysage(nouveauPaysage[0]);//ajout du paysage dans la bonne cellule
            this.grilleCellules[latp2][longp2].setPaysage(nouveauPaysage[1]);// ajout du seconde paysage dans la bonne cellule
            return true;
        } else {
            System.out.println("fuck");
            return false;
        }
    }

    public Cellule[][] getGrilleCellules() {
        return grilleCellules;
    }

    public Cellule getCellule(int lon, int lat) {
        Cellule cell = this.grilleCellules[lat][lon];

        return cell;
    }

    public ArrayList<ArrayList<Character>> getTypesCellules() {
        ArrayList<ArrayList<Character>> typesArray;
        typesArray = new ArrayList<>();

        int i;
        int j;

        for (i = 0; i < 9; i++) {
            ArrayList<Character> ligne = new ArrayList<>();
            for (j = 0; j < 9; j++) {
                Cellule c = this.getCellule(j, i);
                char type = c.getPaysage().getType().charAt(0);
                ligne.add(type);
            }
            typesArray.add(ligne);
        }
        return typesArray;
    }

    public ArrayList<ArrayList<Integer>> getNbrCouronnesGrille() {
        ArrayList<ArrayList<Integer>> nbrArray;
        nbrArray = new ArrayList<>();

        int i;
        int j;

        for (i = 0; i < 9; i++) {
            ArrayList<Integer> ligne = new ArrayList<>();
            for (j = 0; j < 9; j++) {
                Cellule c = this.getCellule(j, i);
                int nbr = c.getPaysage().getNbrDeCouronne();
                ligne.add(nbr);
            }
            nbrArray.add(ligne);
        }
        return nbrArray;
    }

    public void afficherNbrCouronnes(){
        int i;
        int j;
        System.out.println("Nbr couronnes de la grille: ");
        for (i = 0; i < 9; i++) {
            System.out.print("| ");
            for (j = 0; j < 9; j++) {
                Cellule c = this.grilleCellules[i][j];
                Paysage paysage = c.getPaysage();
                int nbrC = paysage.getNbrDeCouronne();

                System.out.print(nbrC + " | ");
            }
            System.out.println("");
        }
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
        for (i = 0; i < 9; i++) {
            System.out.print("| ");
            for (j = 0; j < 9; j++) {
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
        for (i = 0; i < 9; i++) {
            System.out.print("| ");
            for (j = 0; j < 9; j++) {
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

        for (i = 0; i < 9; i++) {
            for (j = 0; j < 9; j++) {
                this.grilleCellules[i][j].changeCellState();
            }
        }

        afficherEtatGrille();
    }

    /* **********
        bonPlacement(Paysage[] paysages) :  Cette méthode permet de vérifier si une carte est correctement
        placer dans le royaume
     */

    private int[] tailleLongLat(){

        return new int[]{(this.longMax - this.longMin+1), (this.latMax - this.latMin+1)};
    }

    public void mAJLimiteReelleRoyaume(int[]coordoPaysage1, int[]coordoPaysage2){
        int [] tailleLongLat=tailleLongLat();
        if(tailleLongLat[0]<5){
            if(coordoPaysage1[0]<this.longMin){
                this.longMin =coordoPaysage1[0];
            }
            if(coordoPaysage1[0]>this.longMax){
                this.longMax =coordoPaysage1[0];
            }

            if(coordoPaysage2[0]<this.longMin){
                this.longMin =coordoPaysage2[0];
            }
            if(coordoPaysage2[0]>this.longMax){
                this.longMax =coordoPaysage2[0];
            }
        }

        if (tailleLongLat[1]<5){
            if(coordoPaysage1[1]<this.latMin){
                this.latMin =coordoPaysage1[1];
            }
            if(coordoPaysage1[1]>this.latMax){
                this.latMax =coordoPaysage1[1];
            }

            if(coordoPaysage2[1]<this.latMin){
                this.latMin =coordoPaysage2[1];
            }
            if(coordoPaysage2[1]>this.latMax){
                this.latMax =coordoPaysage2[1];
            }
        }
        mAJLimiteAbsolueRoyaume();
    }

    public void testLimiteRoyaume(){
        int[]t=tailleLongLat();
        System.out.println("-----------------------\n");
        System.out.println("longueur long : " +t[0]);
        System.out.println("longueur lat : " +t[1]);
        System.out.println("long min : "+this.longMin +"\nlong max : "+ this.longMax);
        System.out.println("lat min : "+this.latMin +"\nlong max : "+ this.latMax);
        System.out.println("lim long min : "+this.bordureRoyaume[0]+ "\nlim lon max : "+ this.bordureRoyaume[1]);
        System.out.println("lim lat min : "+this.bordureRoyaume[2]+ "\nlim latmax : "+ this.bordureRoyaume[3]);
        System.out.println("-----------------------\n");

    }

    private void mAJLimiteAbsolueRoyaume(){
        int [] tailleLongLat=tailleLongLat();
        this.bordureRoyaume = limiteRoyaume(tailleLongLat);
    }

    public  int[] limiteRoyaume(int[] tailleLongLat){
        int [] limiteRoyaume = new int[4];//longi min, lonig max,lati min, lati max
        int tailleLongitude = tailleLongLat[0];
        int tailleLatitude = tailleLongLat[1];


        if(tailleLongitude==5 && tailleLatitude == 5){
            limiteRoyaume[0]=this.longMin;
            limiteRoyaume[1]=this.longMax;
            limiteRoyaume[2]=this.latMin;
            limiteRoyaume[3]=this.latMax;
        }
        else {
            int i;
            for (i = 1; i < 5; i++) {
                if (tailleLongitude == 5) {
                    limiteRoyaume[0] = this.longMin;
                    limiteRoyaume[1] = this.longMax;
                } else {
                    if (tailleLongitude == i) {
                        limiteRoyaume[0] = this.longMin - (5 - i);
                        limiteRoyaume[1] = this.longMax + (5 - i);
                    }
                }

                if (tailleLatitude == 5) {
                    limiteRoyaume[2] = this.latMin;
                    limiteRoyaume[3] = this.latMax;
                } else {

                    if (tailleLatitude == i) {
                        limiteRoyaume[2] = this.latMin - (5 - i);
                        limiteRoyaume[3] = this.latMax + (5 - i);
                    }
                }
            }
        }

        return limiteRoyaume;

    }

    private boolean dansRoyaume(int[] tabCellulePaysage1, int[] tabCellulePaysage2){
        int [] tailleLongLat=tailleLongLat();
        if (tailleLongLat[0]==5 || tailleLongLat[1]==5){
            if(tailleLongLat[0]==5 ) {
                if (!(tabCellulePaysage1[0] >= this.longMin && tabCellulePaysage1[0] <= this.longMax))
                {
                    return false;
                }

                if (!(tabCellulePaysage2[0] >= this.longMin && tabCellulePaysage2[0] <= this.longMax))
                {
                    return false;
                }
            }
            if(tailleLongLat[1]==5 )
            {
                if (!(tabCellulePaysage1[1] >= this.latMin && tabCellulePaysage1[1] <= this.latMax))
                {
                    return false;
                }
                if (!(tabCellulePaysage2[1] >= this.latMin && tabCellulePaysage2[1] <= this.latMax))
                {
                    return false;
                }
            }
        }
        else {
            if (!(tabCellulePaysage1[0] >= this.bordureRoyaume[0] && tabCellulePaysage1[0] <= this.bordureRoyaume[1]))
            {
                return false;
            }

            if (!(tabCellulePaysage2[0] >= this.bordureRoyaume[0] && tabCellulePaysage2[0] <= this.bordureRoyaume[1]))
            {
                return false;
            }

            if (!(tabCellulePaysage1[1] >= this.bordureRoyaume[2] && tabCellulePaysage1[1] <= this.bordureRoyaume[3]))
            {
                return false;
            }
            if (!(tabCellulePaysage2[1] >= this.bordureRoyaume[2] && tabCellulePaysage2[1] <= this.bordureRoyaume[3]))
            {
                return false;
            }
        }

        return true;

    }

    public boolean bonPlacement(Paysage[] paysages) {
        // récupération des coordonnées de la cellule cible de chaque paysages
        int[] tabCellulePaysage1 = paysages[0].getCelluleCible();
        int[] tabCellulePaysage2 = paysages[1].getCelluleCible();

        //si au moins l'une des deux paysages n'est pas sur le plateau, la méthode retourne false
        if ((tabCellulePaysage1[0] == -1) || (tabCellulePaysage2[0] == -1)) {
            return false;
        }

        if( ! dansRoyaume(tabCellulePaysage1,tabCellulePaysage2)){return  false;}

            // vérifiacation de l'atat des cellules cibles : ie, si elles sont vides
        Cellule cellPaysage1 = grilleCellules[tabCellulePaysage1[1]][tabCellulePaysage1[0]];
        Cellule cellPaysage2 = grilleCellules[tabCellulePaysage2[1]][tabCellulePaysage2[0]];

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

    public void genererRoyaumeAleatoire() {
        //fonction qui génère un royaume aléatoire avce tous les paysages remplis (deboggage)

        String[] typesDispo = {"c", "f", "s", "p", "m", "n", "x", "x", "x", "x"};
        int[] couronnesDispo = {0, 1, 2, 3};

        int i;
        int j;

        for (i = 0; i < 9; i++) {
            for (j = 0; j < 9; j++) {
                Random r = new Random();
                int randomType = r.nextInt(typesDispo.length);
                String type = typesDispo[randomType];

                int randomCour = r.nextInt(couronnesDispo.length);

                int couronne = couronnesDispo[randomCour];

                Paysage paysage = new Paysage(type, 1, couronne);

                this.grilleCellules[i][j].setPaysage(paysage);
            }
        }


    }

    public List<List<Integer>> detectionVoisinsCellule(int i, int j, int direction, List<List<Integer>> dejaVisites) {
        //Fonction regroupant les cellules adjacentes de meme type en groupes à partir d'une cellule
        /*direction est la position relative à la cellule précédente dans la recurcivité:
        0 -> premiere fois qu'on execute la fonction
        1 -> la cellule précédente est a droite
        2 -> la cellule précédente est en haut
        3 -> la cellule precedente est a gauche
        4 -> la cellule precedente est en bas
        cela empeche d'avoir une boucle ou la recurcivité fait gauche/droite en boucle.
         */

        List<Integer> coordonneesCellule = new ArrayList<Integer>();
        coordonneesCellule.add(i);
        coordonneesCellule.add(j);

        Cellule cellule = this.getCellule(i, j);
        String typeCellule = cellule.getPaysage().getType();

        List<String> voisins = cellule.getVoisins();


        List<List<Integer>> coords = new ArrayList<>();
        coords.add(coordonneesCellule);

        //System.out.println(coords);


        if (typeCellule.toString() == voisins.get(1).toString() && direction != 1) {
            //droite
            Integer[] coord = {i + 1, j};
            List<Integer> coordList = Arrays.asList(coord);

            if (!dejaVisites.contains(coordList)) {

                //coords.add(coordList);
                dejaVisites.add(coordList);

                //System.out.println(Arrays.toString(coord));
                //System.out.println("----------");

                //System.out.println(dejaVisites + "e");
                List<List<Integer>> results = detectionVoisinsCellule(coord[0], coord[1], 3, dejaVisites);
                coords.addAll(results);
            }
        }

        if (typeCellule.toString() == voisins.get(2).toString() && direction != 4) {
            //bas
            Integer[] coord = {i, j + 1};
            List<Integer> coordList = Arrays.asList(coord);

            if (!dejaVisites.contains(coordList)) {

                //coords.add(coordList);
                dejaVisites.add(coordList);

                //System.out.println(Arrays.toString(coord));
                //System.out.println("----------");

                //System.out.println(dejaVisites + "e");
                List<List<Integer>> results = detectionVoisinsCellule(coord[0], coord[1], 2, dejaVisites);
                coords.addAll(results);
            }
        }

        if (typeCellule.toString() == voisins.get(3).toString() && direction != 3) {
            //gauche
            Integer[] coord = {i - 1, j};
            List<Integer> coordList = Arrays.asList(coord);

            if (!dejaVisites.contains(coordList)) {

                //coords.add(coordList);
                dejaVisites.add(coordList);

                //System.out.println(Arrays.toString(coord));
                //System.out.println("----------");
                //System.out.println(dejaVisites + "e");
                List<List<Integer>> results = detectionVoisinsCellule(coord[0], coord[1], 1, dejaVisites);
                coords.addAll(results);
            }

        }

        if (typeCellule.toString() == voisins.get(0).toString() && direction != 2) {
            //haut
            Integer[] coord = {i, j - 1};
            List<Integer> coordList = Arrays.asList(coord);

            if (!dejaVisites.contains(coordList)) {

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

    public List<List<List<Integer>>> analyserGroupesGrille() {
        //Fonction qui regroupe les differentes zones de la grille

        List<List<Integer>> cellulesAnalysees = new ArrayList<>();
        List<List<List<Integer>>> regroupements = new ArrayList<>();


        int i;
        int j;

        for (i = 0; i < 9; i++) {
            for (j = 0; j < 9; j++) {

                List<Integer> coordonneesCellule = new ArrayList<Integer>();
                coordonneesCellule.add(i);
                coordonneesCellule.add(j);

                if (!cellulesAnalysees.contains(coordonneesCellule)) {
                    //System.out.println(coordonneesCellule);

                    List<List<Integer>> listRef = new ArrayList<>();
                    List<List<Integer>> regroupement = this.detectionVoisinsCellule(coordonneesCellule.get(0), coordonneesCellule.get(1), 0, listRef);

                    cellulesAnalysees.addAll(regroupement);

                    Set<List<Integer>> setGroupement = new HashSet<>(regroupement);
                    regroupement.clear();
                    regroupement.addAll(setGroupement);

                    regroupements.add(regroupement);



                    //System.out.println(cellulesAnalysees);
                }


            }
        }

        //System.out.println(regroupements);
        return regroupements;
    }

    public int calculerPoints(){
        List<List<List<Integer>>> groupes = this.analyserGroupesGrille();
        List<List<Integer>> groupementsCouronnes = new ArrayList<>();

        //On regroupe les nbr de couronnes par groupe
        //on inclue les 0, ce qui fait qu'un groupe de nbrCouronne a autant d'élément
        //qu'un groupe de cellules (utile pour la suite)
        for(List<List<Integer>> groupe : groupes){
            List<Integer> groupeCouronnes = new ArrayList<>();

            for(List<Integer> cellule : groupe){
                int nbrCouronnes = this.getCellule(cellule.get(0), cellule.get(1)).getPaysage().getNbrDeCouronne();
                groupeCouronnes.add(nbrCouronnes);
            }

            //on évité d'ajouter les groupements de cellules vides
            if(!this.getCellule(groupe.get(0).get(0), groupe.get(0).get(1)).getPaysage().getType().equals("x")){
                groupementsCouronnes.add(groupeCouronnes);
            }
        }

        List<Integer> totaux = new ArrayList<>();

        //On calcul le total de point de chaque groupe à partir de la liste créée précédemment
        for(List<Integer> groupe : groupementsCouronnes){
            int somme = 0;

            for(int i : groupe)
                somme += i;

            int total = somme * groupe.size();
            totaux.add(total);
        }

        int total = 0;

        for(int i : totaux)
            total += i;

        return total;
    }

}

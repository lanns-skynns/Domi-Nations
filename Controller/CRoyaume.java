package dominations.Controller;

import dominations.model.*;
import javafx.event.ActionEvent;
import javafx.geometry.Bounds;
import javafx.geometry.Rectangle2D;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.*;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.net.URL;
import java.util.*;

public class CRoyaume {

    String nomJoueur;
    Royaume royaume;
    Cellule[][] grille;

    int numeroCarte;
    char paysage1;
    char paysage2;
    int numCouronnes1;
    int numCouronnes2;

    int spots = 9;
    int grilleWidth = 770;
    int grilleHeight = 630;
    int cellWidth = (grilleHeight)/spots;
    private ArrayList<PaysagePane> tileArray;
    private List<Joueur> ordreJoueur;
    private List<Carte> ordreCartes;
    private boolean sceneIntermediaire = false;

    private List<Image>imagesRoyaume=new ArrayList<>();

    private Color couleurJoueur;

    private int[] borduresRoyaume;

    CChoixCarte choixCarte;


    Button rotateButtonAntiHorr;
    Button rotateButtonHorr;

    String pathImage1;
    String pathImage2;
    Image imagePays1;
    Image imagePays2 ;
    ImageView imageViewP2;
    ImageView imageViewP1;
    PaysagePane cartePays1;
    PaysagePane cartePays2;
    Button validerButton;

    int indice;

    Carte carteAPlacer;

    ActionEvent event;

    public CRoyaume(ActionEvent event,List<Joueur> joueur, List <Carte> carteAPlacer,int indice,CChoixCarte choixCarte) {
        this.choixCarte =choixCarte;
        this.indice = indice;
        this.ordreJoueur = joueur;
        this.ordreCartes = carteAPlacer;
        imagesRoyaume.clear();
        initialiserRoyaume(this.indice, event);
    }

    public List<Image> getImagesRoyaume() {
        return imagesRoyaume;
    }

    public  void initialiserRoyaume(int indice, ActionEvent event){
        this.event = event;
        this.nomJoueur = ordreJoueur.get(indice).getNom();
        System.out.println("J suivant "+this.nomJoueur);
        this.couleurJoueur= styleEnFonctionJoueur(ordreJoueur.get(indice).getCouleur());
        this.royaume = ordreJoueur.get(indice).getRoyaume();
        this.borduresRoyaume = royaume.getBorduresRoyaume();
        this.grille = this.royaume.getGrilleCellules();

        this.carteAPlacer = ordreCartes.get(indice);

        this.numeroCarte = ordreCartes.get(indice).getNumeroDeCarte();
        this.paysage1 = LISTEPAYSAGES[numeroCarte - 1][0];
        this.paysage2 = LISTEPAYSAGES[numeroCarte - 1][1];

        this.numCouronnes1 = LISTECOURONNES[numeroCarte - 1][0];
        this.numCouronnes2 = LISTECOURONNES[numeroCarte - 1][1];

        String typeImage1;
        String typeImage2;

        switch (this.paysage1) {
            case 'c':
                typeImage1 = "F";
                break;
            case 'f':
                typeImage1 = "G";
                break;
            case 'n':
                typeImage1 = "S";
                break;
            case 's':
                typeImage1 = "W";
                break;
            case 'p':
                typeImage1 = "D";
                break;
            case 'm':
                typeImage1 = "M";
                break;
            default:
                typeImage1 = "error";
        }

        switch (this.paysage2) {
            case 'c':
                typeImage2 = "F";
                break;
            case 'f':
                typeImage2 = "G";
                break;
            case 'n':
                typeImage2 = "S";
                break;
            case 's':
                typeImage2 = "W";
                break;
            case 'p':
                typeImage2 = "D";
                break;
            case 'm':
                typeImage2 = "M";
                break;
            default:
                typeImage2 = "error";
        }

        this.pathImage1 = "/dominations/images/dominos/" + typeImage1 + numCouronnes1 + ".png";
        this.pathImage2 = "/dominations/images/dominos/" + typeImage2 + numCouronnes2 + ".png";

        RoyaumeScene();
    }

    public void RoyaumeScene() {

        final URL cssURL = getClass().getResource("/dominations/css/royaume.css");//css

        final Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        Stage window;
        window = (Stage) ((Node) event.getSource()).getScene().getWindow();



        List<Node> listeNode=new  ArrayList<>();

        BorderPane bp=new BorderPane();

        Scene scene = new Scene(bp,bounds.getWidth(), bounds.getHeight());
        scene.getStylesheets().add(cssURL.toExternalForm());

        String labelTopText = "C'est à " + nomJoueur + " de jouer";


        //top
        HBox topHBox = new HBox();
        Label labelTourActuel=new Label(choixCarte.affichageTour);
        labelTourActuel.setFont(Font.font(30));
        Label labelTop = new Label(labelTopText);
        labelTop.setAlignment(Pos.CENTER);
        labelTop.setFont(Font.font(50));
        labelTop.setTextFill(couleurJoueur);
        topHBox.setAlignment(Pos.TOP_LEFT);
        topHBox.getChildren().add(labelTourActuel);
        topHBox.getChildren().add(labelTop);
        labelTourActuel.setAlignment(Pos.CENTER);
        labelTourActuel.setLayoutX(0);
        labelTourActuel.setPadding(new Insets(0,bounds.getWidth()*0.30,0,0));

        //center (grille)
        StackPane grillePaneBody = new StackPane();
        Pane grillePane = new Pane();

        grillePane.setMaxSize(grilleWidth, grilleHeight);

        int i;
        int j;

        //Création de la grille
        for(i = 0; i<spots+2; i++){
            for(j = 0; j<spots; j++){
                Rectangle r = new Rectangle(i*cellWidth, j*cellWidth, cellWidth, cellWidth);
                if(i<borduresRoyaume[0] || i>borduresRoyaume[1] || j<borduresRoyaume[2] || j>borduresRoyaume[3])
                {
                    r.setFill(Color.BLACK);
                    if(i>8){
                        r.setFill(Color.rgb(220,235,235));
                    }
                }
                else {
                    r.setFill(Color.WHITE);
                }
                if(i<9){
                    r.setStroke(couleurJoueur);
                    r.setOpacity(0.7);
                } else if (i == 10 && (j==1 || j==0)){
                    r.setStroke(Color.BLACK);
                }

                grillePane.getChildren().add(r);

            }
        }

        //Affichage des tiles

        ArrayList<ArrayList<Character>> typesGrille = this.royaume.getTypesCellules();
        ArrayList<ArrayList<Integer>> couronnesGrille = this.royaume.getNbrCouronnesGrille();

        ArrayList<PaysagePane> tiles = new ArrayList<>();

        for(i=0; i<spots; i++){
            for(j=0; j<spots; j++){

                char type = typesGrille.get(j).get(i);
                int couronnes = couronnesGrille.get(j).get(i);

                String typeImage = "";

                switch (type) {
                    case 'c':
                        typeImage = "F";
                        break;
                    case 'f':
                        typeImage = "G";
                        break;
                    case 'n':
                        typeImage = "S";
                        break;
                    case 's':
                        typeImage = "W";
                        break;
                    case 'p':
                        typeImage = "D";
                        break;
                    case 'm':
                        typeImage = "M";
                        break;
                    default:
                        typeImage = "error";
                }

                if(type != 'x') {
                    String imagePath = "/dominations/images/dominos/"+typeImage+""+couronnes+".png";

                    Image tileImage = new Image(imagePath);
                    ImageView tileIW = new ImageView(tileImage);

                    PaysagePane tile = new PaysagePane(i * cellWidth, j * cellWidth, cellWidth, tileIW);

                    grillePane.getChildren().add(tileIW);
                    tile.draw();
                    tiles.add(tile);
                }
            }
        }

        grillePaneBody.setStyle("-fx-border-color: black");
        grillePane.setStyle("-fx-border-color: red");

        grillePaneBody.getChildren().add(grillePane);

        StackPane.setAlignment(grillePane, Pos.CENTER);

        bp.setCenter(grillePaneBody);

        //carte à placer

        imagePays1 = new Image(pathImage1);
        imagePays2 = new Image(pathImage2);

        imageViewP1 = new ImageView(imagePays1);
        imageViewP2 = new ImageView(imagePays2);

        cartePays1 = new PaysagePane(10*cellWidth, 0, cellWidth, imageViewP1);
        cartePays2 = new PaysagePane(10*cellWidth, 1*cellWidth, cellWidth, imageViewP2);

        grillePane.getChildren().add(imageViewP1);
        grillePane.getChildren().add(imageViewP2);

        cartePays1.draw();
        cartePays2.draw();

        imageViewP1.setOnMousePressed(event -> pressed(event, cartePays1, cartePays2, 0));
        imageViewP1.setOnMouseDragged(event -> dragged(event, cartePays1, cartePays2));
        imageViewP1.setOnMouseReleased(event -> released(event, cartePays1, cartePays2, 0));

        imageViewP2.setOnMousePressed(event -> pressed(event, cartePays2, cartePays1, 1));
        imageViewP2.setOnMouseDragged(event -> dragged(event, cartePays2, cartePays1));
        imageViewP2.setOnMouseReleased(event -> released(event, cartePays2, cartePays1, 1));

        //right

        VBox rightVBox = new VBox();
        rightVBox.setAlignment(Pos.CENTER);

        rotateButtonHorr = new Button("Rotation horraire");
        rotateButtonAntiHorr = new Button("Rotation anti-horraire");

        validerButton = new Button("Valider le placement");
        rotateButtonHorr.setAlignment(Pos.CENTER);
        validerButton.setAlignment(Pos.CENTER);
        validerButton.setDisable(true);

        rightVBox.getChildren().add(rotateButtonHorr);
        rightVBox.getChildren().add(rotateButtonAntiHorr);
        rightVBox.getChildren().add(validerButton);
        rightVBox.setSpacing(15);
        rightVBox.setPadding(new Insets(10,15,10,15));

        rotateButtonHorr.setOnAction(event -> rotationHorraire(cartePays2, cartePays1));
        rotateButtonAntiHorr.setOnAction(event -> rotationAntiHorraire(cartePays2, cartePays1));
        validerButton.setOnAction(event -> validerPlacement(event,grillePane));

        rightVBox.setStyle("-fx-border-color: black");

        //BP
        bp.setTop(topHBox);
        bp.setRight(rightVBox);


        window.setScene(scene);
        Image logo = new Image("dominations/images/Kingdomino-Logo.png");
        window.getIcons().add(logo);
        window.setTitle("King-Domino");
        window.show();


    }

    private void pressed(MouseEvent event, PaysagePane tile, PaysagePane tile2, int tileN){
        //System.out.println("pressed");
    }

    private void dragged(MouseEvent event, PaysagePane tile, PaysagePane tile2){
        tile.setX(tile.getX() + event.getX() - (tile.getWidth() / 2));
        tile.setY(tile.getY() + event.getY() - (tile.getWidth() / 2));

        tile2.setX(tile2.getX() + event.getX() - (tile2.getWidth() / 2));
        tile2.setY(tile2.getY() + event.getY() - (tile2.getWidth() / 2));

        tile.draw();
        tile2.draw();
    }

    private void released(MouseEvent event, PaysagePane tile, PaysagePane tile2, int tileN){
        int gridx = (int) (tile.getX() + (tile.getWidth() / 2)) / cellWidth;
        int gridy = (int) (tile.getY() + (tile.getWidth() / 2)) / cellWidth;

        int gridx2 = (int) (tile2.getX() + (tile2.getWidth() / 2)) / cellWidth;
        int gridy2 = (int) (tile2.getY() + (tile2.getWidth() / 2)) / cellWidth;

        //vérification que les dominos sont bien dans la grille

        if(gridx > borduresRoyaume[1] || gridx < borduresRoyaume[0] || gridx2 > borduresRoyaume[1] || gridx2 < borduresRoyaume[0] || gridy2 > borduresRoyaume[3] || gridy2 < borduresRoyaume[2] || gridy > borduresRoyaume[3] || gridy < borduresRoyaume[2] ){
            //On les replaces dans la réserve en haut à droite
            if(tileN == 0){
                gridx = 10;
                gridy = 0;
                gridx2 = 10;
                gridy2 = 1;
                validerButton.setDisable(true);
            } else {
                gridx2 = 10;
                gridy2 = 0;
                gridx = 10;
                gridy = 1;
                validerButton.setDisable(true);
            }
        } else {
            //vérification que le placement au sein de la grille est valide
            //Si non: on place le domino mais on grise le bouton pour valider le placement
            if(tileN==0) {
                if (verifierPlacement(gridx, gridy, gridx2, gridy2) == true) {
                    validerButton.setDisable(false);
                } else {
                    validerButton.setDisable(true);
                }
            }else{
                if (verifierPlacement(gridx2, gridy2, gridx, gridy) == true) {
                    validerButton.setDisable(false);
                } else {
                    validerButton.setDisable(true);
                }
            }
        }

        //En cas de bug (les deux tiles sont positionnées sur la meme case) on renvoie le domino dans la reserve
        if(gridx == gridx2 && gridy == gridy2){
            if(tileN == 0){
                gridx = 10;
                gridy = 0;
                gridx2 = 10;
                gridy2 = 1;
            } else {
                gridx2 = 10;
                gridy2 = 0;
                gridx = 10;
                gridy = 1;
            }
        }


        int newX = gridx * cellWidth;
        int newY = gridy * cellWidth;
        int newX2 = gridx2 * cellWidth;
        int newY2 = gridy2* cellWidth;

        tile.setX(newX);
        tile.setY(newY);

        tile2.setX(newX2);
        tile2.setY(newY2);

        tile.draw();
        tile2.draw();

        //vérification qu'un rotation est possible
        if(verifierRotationH() == true){
            rotateButtonHorr.setDisable(false);
        } else {
            rotateButtonHorr.setDisable(true);
        }

        if(verifierRotationAntiH() == true){
            rotateButtonAntiHorr.setDisable(false);
        } else {
            rotateButtonAntiHorr.setDisable(true);
        }

    }

    public boolean verifierRotationH(){
        boolean possible = true;

        double  dx = (cartePays1.getX() - cartePays2.getX())/cellWidth;
        double  dy = (cartePays1.getY() - cartePays2.getY())/cellWidth;


        if(dx == 0 && dy == -1 && cartePays2.getX() == 0){
            possible = false;
        } else if(dx == 1 && dy == 0 && cartePays2.getY() == 0) {
            possible = false;
        } else if(dx == 0 && dy == 1 && cartePays2.getX()/cellWidth == 8){
            possible = false;
        } else if(dx == -1 && dy == 0 && cartePays2.getY()/cellWidth == 8){
            possible = false;
        }

        return possible;
    }

    public boolean verifierRotationAntiH(){
        boolean possible = true;

        double  dx = (cartePays1.getX() - cartePays2.getX())/cellWidth;
        double  dy = (cartePays1.getY() - cartePays2.getY())/cellWidth;


        if(dx == 0 && dy == -1 && cartePays2.getX()/cellWidth == 8){
            possible = false;
        } else if(dx == 1 && dy == 0 && cartePays2.getY()/cellWidth == 8) {
            possible = false;
        } else if(dx == 0 && dy == 1 && cartePays2.getX()/cellWidth == 0){
            possible = false;
        } else if(dx == -1 && dy == 0 && cartePays2.getY()/cellWidth == 0){
            possible = false;
        }

        return possible;
    }

    public void rotationHorraire(PaysagePane tile, PaysagePane axe){
        tile.setDirection(tile.getDirection() + 1);

        if(tile.getDirection() == 5){
            tile.setDirection(1);
        }

        //System.out.println(tile.getDirection());

        int gridx = (int) (tile.getX() + (tile.getWidth() / 2)) / cellWidth;
        int gridy = (int) (tile.getY() + (tile.getWidth() / 2)) / cellWidth;
        int newX = 0;
        int newY = 0;

        int axex = (int) (axe.getX() + (axe.getWidth() / 2)) / cellWidth;
        int axey = (int) (axe.getY() + (axe.getWidth() / 2)) / cellWidth;

        int deltaX = axex - gridx;
        int deltaY = axey - gridy;

        if(deltaX == 0 && deltaY == -1){
            newX = gridx - 1;
            newY = gridy - 1;
        } else if (deltaX == 1 && deltaY == 0){
            newX = gridx + 1;
            newY = gridy - 1;
        } else if (deltaX == 0 && deltaY == 1){
            newX = gridx + 1;
            newY = gridy + 1;
        } else if (deltaX == -1 && deltaY == 0){
            newX = gridx - 1;
            newY = gridy + 1;
        }

        int newposX = newX * cellWidth;
        int newposY = newY* cellWidth;

        tile.setX(newposX);
        tile.setY(newposY);

        tile.draw();

        //on grise les boutons de rotation si besoin
        if(verifierRotationH() == true){
            rotateButtonHorr.setDisable(false);
        } else {
            rotateButtonHorr.setDisable(true);
        }

        if(verifierRotationAntiH() == true){
            rotateButtonAntiHorr.setDisable(false);
        } else {
            rotateButtonAntiHorr.setDisable(true);
        }

        //on vérifie qu'on peut valider le placement en grisant ou non le bouton valider
        System.out.println(axex + ": "+ axey);
        System.out.println(newX + ": " + newY);

        System.out.println(verifierPlacement(axex, axey, newX, newY));

        if(verifierPlacement(axex, axey, newX, newY)){
            validerButton.setDisable(false);
        } else {
            validerButton.setDisable(true);
        }

    }

    public void rotationAntiHorraire(PaysagePane tile, PaysagePane axe){
        tile.setDirection(tile.getDirection() - 1);

        if(tile.getDirection() == 0){
            tile.setDirection(4);
        }

        int gridx = (int) (tile.getX() + (tile.getWidth() / 2)) / cellWidth;
        int gridy = (int) (tile.getY() + (tile.getWidth() / 2)) / cellWidth;
        int newX = 0;
        int newY = 0;

        int axex = (int) (axe.getX() + (axe.getWidth() / 2)) / cellWidth;
        int axey = (int) (axe.getY() + (axe.getWidth() / 2)) / cellWidth;

        int deltaX = axex - gridx;
        int deltaY = axey - gridy;

        if(deltaX == 0 && deltaY == -1){
            newX = gridx + 1;
            newY = gridy - 1;
        } else if (deltaX == 1 && deltaY == 0){
            newX = gridx + 1;
            newY = gridy + 1;
        } else if (deltaX == 0 && deltaY == 1){
            newX = gridx - 1;
            newY = gridy + 1;
        } else if (deltaX == -1 && deltaY == 0){
            newX = gridx - 1;
            newY = gridy - 1;
        }

        int newposX = newX * cellWidth;
        int newposY = newY* cellWidth;

        tile.setX(newposX);
        tile.setY(newposY);

        tile.draw();

        //on grise les boutons de rotation si besoin
        if(verifierRotationH() == true){
            rotateButtonHorr.setDisable(false);
        } else {
            rotateButtonHorr.setDisable(true);
        }

        if(verifierRotationAntiH() == true){
            rotateButtonAntiHorr.setDisable(false);
        } else {
            rotateButtonAntiHorr.setDisable(true);
        }

        if(verifierPlacement(axex, axey, newX, newY)){
            validerButton.setDisable(false);
        } else {
            validerButton.setDisable(true);
        }
    }

    private boolean verifierPlacement(int gridx, int gridy, int gridx2, int gridy2){

        int[] celluleCible1 = {gridx, gridy};
        int[] celluleCible2 = {gridx2, gridy2};

        Paysage paysageCarte1 = carteAPlacer.toPaysages()[0];
        Paysage paysageCarte2 = carteAPlacer.toPaysages()[1];

        paysageCarte1.setCelluleCible(celluleCible1);
        paysageCarte2.setCelluleCible(celluleCible2);

        Paysage[] paysages = {paysageCarte1, paysageCarte2};

        return royaume.bonPlacement(paysages);
    }

    public void validerPlacement(ActionEvent event,Node root){
        int x1 = (int) (cartePays1.getX()/cellWidth);
        int y1 = (int) (cartePays1.getY()/cellWidth);

        int[] cible1 = {x1, y1};

        int x2 = (int) (cartePays2.getX()/cellWidth);
        int y2 = (int) (cartePays2.getY()/cellWidth);

        int[] cible2 = {x2, y2};

        int numCouronnes1 = carteAPlacer.getNombreCouronne()[0];
        int numCouronnes2 = carteAPlacer.getNombreCouronne()[1];

        Paysage nvxPaysage1 = new Paysage(String.valueOf(this.paysage1), numeroCarte, numCouronnes1);
        Paysage nvxPaysage2 = new Paysage(String.valueOf(this.paysage2), numeroCarte, numCouronnes2);

        nvxPaysage1.setCelluleCible(cible1);
        nvxPaysage2.setCelluleCible(cible2);

        Paysage[] paysagesAPlacer = {nvxPaysage1, nvxPaysage2};

        royaume.placerCarte(paysagesAPlacer);

        System.out.println(paysage1 + " ; " + paysage2);
        System.out.println(x2 + " ; " + y2);
        System.out.println(numCouronnes1 + " ; " + numCouronnes2);

        royaume.testLimiteRoyaume();
        royaume.mAJLimiteReelleRoyaume(cible1,cible2);
        royaume.testLimiteRoyaume();


        royaume.afficherTypesGrille();
        if( this.indice<this.ordreCartes.size()-1) {
            this.indice++;
            System.out.println("indiceeeeeeeeeeeeee "+ this.indice);
           // Image image =snap(root); // capture que le node
            imagesRoyaume.add(snap(root));
            System.out.println(imagesRoyaume.size()+" iiiiiiiiiiiiiiiiiiqssssss");
            initialiserRoyaume(indice,event);
        }
        else{
            imagesRoyaume.add(snap(root));
            if(choixCarte.toursRestants!=8){
                choixCarte.setImagesRoyaume(imagesRoyaume);
                choixCarte.setActionEvent(event);
                choixCarte.ChoixScene();

            }
            else{
                Set<Joueur> ordreJ=new HashSet<>(ordreJoueur);
                List<Joueur> classement =new ArrayList<>();
                System.out.println("lololoolo"+ordreJ.size());


                List<Integer> scoreTrie=new ArrayList<>();
                if(choixCarte.nbrTotalJoueurs==2){
                    for (Joueur j:ordreJ){
                        scoreTrie.add(j.getRoyaume().calculerPoints());
                        //associationJoueurScore.put(j,j.getRoyaume().calculerPoints());
                    }
                    
                }
                
                else {
                    for (Joueur j:ordreJ){
                        scoreTrie.add(j.getRoyaume().calculerPoints());
                        //associationJoueurScore.put(j,j.getRoyaume().calculerPoints());
                    }
                }
                Collections.sort(scoreTrie);
                System.out.println("abba");
                System.out.println(scoreTrie);
                Collections.reverse(scoreTrie);
                System.out.println("yeaaa");
                System.out.println(scoreTrie);
                System.out.println(scoreTrie.size());
                Set<Integer> scoreSansRepetion=new HashSet<>(scoreTrie);
                for(int score:scoreSansRepetion){
                    //System.out.println(score);
                //classement.put(associationJoueurScore.get(scoreTrie.get(score)),score);
                    for(Joueur j:ordreJ){
                        if(j.getRoyaume().calculerPoints()==score){
                            classement.add(j);
                            System.out.println(j.getNom());
                            System.out.println(score);
                        }
                    }
                }
                System.out.println("juuuuuuu"+classement.size());

                new CResultat(event,classement);
            }



        }

    }

    final private int[][] LISTECOURONNES = { // liste des couronnes, récupérées dans le fichier excel du projet.
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


    final private char[][] LISTEPAYSAGES = {//liste des paysages  : récupérée dans le fichier excel du proje
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

    public  Image snap(Node node) {
        try {
            final Bounds bounds = node.getLayoutBounds();
            double scale = 1;
            int imageWidth = (int) Math.round(bounds.getWidth() * scale-2*cellWidth);
            int imageHeight = (int) Math.round(bounds.getHeight() * scale);


            final SnapshotParameters snapPara = new SnapshotParameters();
            snapPara.setFill(Color.TRANSPARENT);
            snapPara.setTransform(javafx.scene.transform.Transform.scale(scale, scale));
            WritableImage snapshot = new WritableImage(imageWidth, imageHeight);
            snapshot = node.snapshot(snapPara, snapshot);
            return snapshot;
        } catch (Exception e) {
            //logger.debug(e.toString());
            return null;
        }

    }

    public static Color styleEnFonctionJoueur(Couleur couleurJoueur){
        Color styleCouleurJoueur;
        if(couleurJoueur == Couleur.BLEU ){
            styleCouleurJoueur = Color.BLUE;
        }else  if(couleurJoueur == Couleur.ROUGE){
            styleCouleurJoueur= Color.RED;
        }
        else  if(couleurJoueur == Couleur.VERT){
            styleCouleurJoueur= Color.GREEN;
        }else {
            styleCouleurJoueur = Color.rgb(200,150,50);
        }
        return styleCouleurJoueur;
    }

}

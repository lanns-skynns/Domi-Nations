package dominations.Controller;

import dominations.model.Carte;
import dominations.model.*;
import javafx.event.ActionEvent;
import javafx.geometry.Rectangle2D;
import javafx.scene.*;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.*;
import javafx.scene.shape.*;

import java.lang.Math;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.w3c.dom.css.Rect;

import javax.swing.border.EmptyBorder;
import java.io.File;
import java.net.URL;
import java.sql.Array;
import java.sql.Struct;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class CChoixCarte {

    private ArrayList<Integer> numerosCartes = new ArrayList<>();
    private List<Joueur> ordreJoueurs;
    private List<Carte> cartesEnJeu;
    private Joueur joueurQuiChoisis;
    private ArrayList<Carte> choixDesJoueurs = new ArrayList<>();
    private Carte choixJoueur;
    private CarteChoix choixJoueurPane;
    private ArrayList<CarteChoix> listeCarteVBox = new ArrayList<>();
    private int n = 0;
    private  ActionEvent event;
    //Liste des joueurs dans l'ordre pour le tour suivant
    private Joueur[] ordreJoueursTourSuivant;
    int nbrJoueursTourPrecedent=-1;
    Pile pile;
    CartesEnJeu cartesEnG;
    Label labelTop=new Label("");
    private Color couleurJoueur;
    int nbrTotalJoueurs;

    Rectangle2D bounds;

    int nombreDeTour;
    int tourActuel;
    private List<Image>imagesRoyaume=new ArrayList<>();
    String affichageTour;

    Map map = new HashMap();
    List<Integer> ordreCroissant=new ArrayList<>();

    Button validerChoixButton = new Button("Valider");
    int toursRestants;


    public String getAffichageTour() {
        return affichageTour;
    }

    public int getToursRestants(){
        return this.toursRestants;
    }
    public int getNbrTotalJoueurs(){
        return this.nbrTotalJoueurs;
    }

    public void setImagesRoyaume(List <Image> imagesRoyaume) {
        this.imagesRoyaume=imagesRoyaume;
    }

    public  void setPile(Pile pile){
        this.pile = pile;
    }

    public  void setActionEvent(ActionEvent event){
        this.event = event;
    }

    public  void setCartesEnG(CartesEnJeu cartesEnG){
        this.cartesEnG = cartesEnG;

    }

    public CChoixCarte(ActionEvent event, List<Joueur> ordreJoueurs,int nbrTour,int nbrTotalJoueurs){
        this.event = event;
        this.ordreJoueurs = ordreJoueurs;
        this.nombreDeTour = nbrTour;
        this.nbrTotalJoueurs=nbrTotalJoueurs;

    }

    void  piocherCartes(){
        tourActuel++;
        this.affichageTour ="tour " +Integer.toString(this.tourActuel)+"/"+this.nombreDeTour;
        this.toursRestants= this.nombreDeTour-this.tourActuel;
        this.cartesEnJeu = cartesEnG.nouvellesCartes();
        for(int i = 0; i<cartesEnJeu.size(); i++){
            numerosCartes.add(cartesEnJeu.get(i).getNumeroDeCarte());
        }
        System.out.println("nbr de carte restantes :  "+ this.pile.getNombreCartesRestantes());
        for (Joueur j : ordreJoueurs){
            System.out.println(ordreJoueurs.size()+" pioooooooooooooo");
            System.out.println("ordre "+j.getNom());
        }
        this.joueurQuiChoisis = ordreJoueurs.get(0);
        this.couleurJoueur = CRoyaume.styleEnFonctionJoueur(ordreJoueurs.get(0).getCouleur());
        this.labelTop.setTextFill(couleurJoueur);
    }

    public void ChoixScene() {
        piocherCartes();
        final URL cssURL = getClass().getResource("/dominations/css/choixCartes.css");//css
       // final URL cssURL = getClass().getResource("/dominations/css/accueil.css");//css

        BorderPane bp = new BorderPane();

        final Screen screen = Screen.getPrimary();
         bounds = screen.getVisualBounds();
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setResizable(false);


        //Top
        HBox topHBox = new HBox();
        labelTop.setText("C'est à "+joueurQuiChoisis.getNom()+" de choisir");
        affichageNomsJoueurs(labelTop);

        labelTop.setAlignment(Pos.CENTER);
        topHBox.setAlignment(Pos.TOP_LEFT);
        topHBox.setId("topHBox");

        labelTop.setFont(Font.font(50));
        Label labelTourActuel = new Label(this.affichageTour);
        labelTourActuel.setFont(Font.font(30));
       // topHBox.setSpacing(bounds.getWidth()/2-10*labelTop.getPrefWidth());
        //labelTop.setPadding(new Insets(0,0,0,600));
        labelTourActuel.setAlignment(Pos.CENTER);
        labelTourActuel.setLayoutX(0);
        labelTourActuel.setPadding(new Insets(0,bounds.getWidth()*0.30,0,0));
        System.out.println("image R taille "+imagesRoyaume.size());
        if (imagesRoyaume.size()>1){
            System.out.println( "tailllllle : " +imagesRoyaume.size());
            ImageView imv =new ImageView(imagesRoyaume.get(0));
            imv.setFitWidth(bounds.getWidth()*0.30);
            imv.setFitHeight(bounds.getWidth()*0.30);
            bp.setLeft(imv);

            BorderPane.setAlignment(imv,Pos.CENTER);
            BorderPane.setMargin(imv,new Insets(0,10,0,10));
        }

        topHBox.getChildren().add(labelTourActuel);
        topHBox.getChildren().add(labelTop);

        bp.setTop(topHBox);

        //Bot
        HBox botHbox = new HBox();

        validerChoixButton.setFont(Font.font(40));
        validerChoixButton.setDisable(true);
        validerChoixButton.setOnAction(event -> confirmerChoix(choixJoueurPane,event,bp));

        botHbox.getChildren().add(validerChoixButton);
        botHbox.setPadding(new Insets(1,0,0,1));
        bp.setBottom(botHbox);
botHbox.setId("botHbox");
        botHbox.setAlignment(Pos.CENTER);


        //Center
        HBox centerHbox = new HBox();
        centerHbox.setId("centerHbox");

        centerHbox.setAlignment(Pos.CENTER);

        HBox cartesHbox = new HBox(20);
        cartesHbox.setMinWidth(cartesEnJeu.size() * 200 + (cartesEnJeu.size()-1)*20);
        cartesHbox.setMinHeight(400 + 100);
        cartesHbox.setMaxHeight(400 + 100);
        System.out.println(cartesEnJeu.size());

        Map typesmap = new HashMap();
        typesmap.put('c', "F");
        typesmap.put('f', "G");
        typesmap.put('n', "S");
        typesmap.put('s', "W");
        typesmap.put('p', "D");
        typesmap.put('m', "M");


        //Cartes
        for(int i = 0; i<cartesEnJeu.size(); i++){
            VBox carteVbox = new VBox();
            carteVbox.setPrefHeight(500);
            carteVbox.setPrefWidth(200);

            VBox paysagesVBox = new VBox();

            //System.out.println(typesmap.get("W"));
            Object type1 = typesmap.get(cartesEnJeu.get(i).getTypeCarte()[0]);
            Object type2 = typesmap.get(cartesEnJeu.get(i).getTypeCarte()[1]);

            int couronne1 = cartesEnJeu.get(i).getNombreCouronne()[0];
            int couronne2 = cartesEnJeu.get(i).getNombreCouronne()[1];

            Image image1 = new Image("/dominations/images/dominos/"+type1+couronne1+".png");
            Image image2 = new Image("/dominations/images/dominos/"+type2+couronne2+".png");
            ImageView iw1 = new ImageView(image1);
            ImageView iw2 = new ImageView(image2);

            iw1.setFitWidth(200);
            iw1.setFitHeight(200);
            iw2.setFitWidth(200);
            iw2.setFitHeight(200);

            cartesHbox.getChildren().add(carteVbox);

            paysagesVBox.getChildren().add(iw1);
            paysagesVBox.getChildren().add(iw2);
            carteVbox.getChildren().add(paysagesVBox);

            Label numeroLabel = new Label(Integer.toString(numerosCartes.get(i)));

            numeroLabel.setFont(Font.font(30));
            numeroLabel.setTranslateY(20);

            carteVbox.setAlignment(Pos.TOP_CENTER);
            Carte carte = cartesEnJeu.get(i);


            CarteChoix cartePane = new CarteChoix(paysagesVBox, carte);

            carteVbox.setOnMouseClicked(event -> setChoice(event, carte, cartePane));

            carteVbox.getChildren().add(numeroLabel);

            listeCarteVBox.add(cartePane);

        }


        centerHbox.getChildren().add(cartesHbox);


        bp.setCenter(centerHbox);

        Scene scene= new Scene(bp,bounds.getWidth(),bounds.getHeight());
        scene.getStylesheets().add(cssURL.toExternalForm());
        window.setScene(scene);
        Image logo = new Image("dominations/images/Kingdomino-Logo.png");
        window.getIcons().add(logo);
        window.setMaximized(true);
       /* window.setX(bounds.getMinX());
        window.setY(bounds.getMinY());
        window.setWidth(bounds.getWidth());
        window.setHeight(bounds.getHeight());
*/
        window.setResizable(false);
        //window.setFullScreen(true);
        window.setTitle("King-Domino");
        window.show();

    }

    private void setChoice(MouseEvent event, Carte carte, CarteChoix carteChoisie){
        this.choixJoueur = carte;
        this.choixJoueurPane = carteChoisie;

        validerChoixButton.setDisable(false);
        if(carteChoisie.getChoosed() == false){
            for(int i = 0; i<listeCarteVBox.toArray().length; i++){
                listeCarteVBox.get(i).setFocused(false);
            }
            carteChoisie.setFocused(true);
            System.out.println("carte selectionnée par "+joueurQuiChoisis.getNom()+": "+carte.getNumeroDeCarte());
        } else {
            carteChoisie.setFocused(false);
            System.out.println("Carte déjà choisie");
        }
    }

    private void confirmerChoix(CarteChoix cartechoisie,ActionEvent event,BorderPane bp){

        if(this.nbrJoueursTourPrecedent == this.choixDesJoueurs.size()-2){
            this.nbrJoueursTourPrecedent++;
        }
        System.out.println("uoooooooooop  " +nbrJoueursTourPrecedent);
        choixDesJoueurs.add(choixJoueur);
        Set<Carte> setListe =choixDesJoueurs.stream().collect(Collectors.toSet());
        choixDesJoueurs.clear();
        choixDesJoueurs.addAll(setListe);

        System.out.println("yasssssssssssssssssss " +choixDesJoueurs.size());

        for  (Carte c : choixDesJoueurs){
            System.out.println(c.getInfoCarte());
        }
        if (choixDesJoueurs.size() ==  nbrJoueursTourPrecedent+2) {
            cartechoisie.setChoosed(true, joueurQuiChoisis);
            //on augmente de un le numero du joueur qui joue
            System.out.println("iciiiiiiiiiiiiiiiiiiiii");
            ordreCroissant.add(choixJoueur.getNumeroDeCarte());
            map.put(choixJoueur.getNumeroDeCarte(),joueurQuiChoisis);
            if (n < ordreJoueurs.size() - 1) {
                n++;
                if(imagesRoyaume.size()>0) {
                    ImageView imv =new ImageView(imagesRoyaume.get(n));
                    imv.setFitWidth(bounds.getWidth()*0.30);
                    imv.setFitHeight(bounds.getWidth()*0.30);
                    bp.setLeft(imv);

                    BorderPane.setAlignment(imv,Pos.CENTER);
                    BorderPane.setMargin(imv,new Insets(0,10,0,10));
                }

                joueurQuiChoisis = ordreJoueurs.get(n);
                this.couleurJoueur = CRoyaume.styleEnFonctionJoueur(ordreJoueurs.get(n).getCouleur());
                labelTop.setTextFill(couleurJoueur);
                affichageNomsJoueurs(labelTop);
                System.out.println("Joueur qui choisis: " + joueurQuiChoisis.getNom());
                validerChoixButton.setDisable(true);

                //On modifie le text du boutton si c'est le dernier joueur qui choisi la pièce
                if (n == ordreJoueurs.size() - 1) {
                    validerChoixButton.setText("Valider et placer les pièces");
                }
            }
        }
        else{
            System.out.println("iiiiiiiiiiiiiiiiiiii");
        }

        System.out.println("n :"+n );
        if(this.cartesEnJeu.size() == choixDesJoueurs.size()){
            System.out.println("ready to go next");
            List<Joueur> bonOrdre =ordreJoueur();

            validerChoixButton.setText("Valider");

            this.n=0;
            this.nbrJoueursTourPrecedent = -1;
            choixDesJoueurs.clear();
            ordreCroissant.clear();

            new CRoyaume(event,bonOrdre,this.cartesEnJeu,0,this);
        }
    }

    List <Joueur> ordreJoueur(){
        Collections.sort(ordreCroissant);

        List<Joueur> bonOrdre = new ArrayList<>();

        for (int c: ordreCroissant){
            bonOrdre.add((Joueur) map.get(c));
        }
        ordreJoueurs.clear();
        this.ordreJoueurs =bonOrdre;
        return  bonOrdre;
    }

    private void affichageNomsJoueurs(Label label){
        label.setText("C'est à "+joueurQuiChoisis.getNom()+" de choisir");
    }
}

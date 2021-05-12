package dominations.Controller;

import dominations.model.Carte;
import dominations.model.*;
import javafx.scene.*;
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

public class CChoixCarte {

    private ArrayList<Integer> numerosCartes = new ArrayList<>();
    private Joueur[] ordreJoueurs;
    private Carte[] cartesEnJeu;
    private Joueur joueurQuiChoisis;
    private ArrayList<Carte> choixDesJoueurs = new ArrayList<>();
    private Carte choixJoueur;
    private CarteChoix choixJoueurPane;
    private ArrayList<CarteChoix> listeCarteVBox = new ArrayList<>();
    private int n = 0;
    //Liste des joueurs dans l'ordre pour le tour suivant
    private Joueur[] ordreJoueursTourSuivant;

    Button validerChoixButton = new Button("Valider");

    public CChoixCarte(Carte[] cartesEnJeu, Joueur[] ordreJoueurs){
        this.ordreJoueurs = ordreJoueurs;
        this.cartesEnJeu = cartesEnJeu;

        for(int i = 0; i<cartesEnJeu.length; i++){
            numerosCartes.add(cartesEnJeu[i].getNumeroDeCarte());
        }

        this.joueurQuiChoisis = ordreJoueurs[0];

        System.out.println("Joueur qui choisis: " + joueurQuiChoisis.getNom());

    }

    public Scene ChoixScene() {
        final URL cssURL = getClass().getResource("/dominations/css/royaume.css");//css

        BorderPane bp = new BorderPane();

        final Screen screen = Screen.getPrimary();
        double height = screen.getBounds().getHeight();
        double width = screen.getBounds().getWidth();

        //Top
        HBox topHBox = new HBox();
        Label labelTop = new Label("C'est à "+joueurQuiChoisis.getNom()+" de choisir");

        labelTop.setAlignment(Pos.CENTER);
        topHBox.setAlignment(Pos.CENTER);
        labelTop.setFont(Font.font(50));
        topHBox.getChildren().add(labelTop);

        bp.setTop(topHBox);

        //Bot
        HBox botHbox = new HBox();

        validerChoixButton.setFont(Font.font(40));
        validerChoixButton.setDisable(true);
        validerChoixButton.setOnAction(event -> confirmerChoix(choixJoueurPane));

        botHbox.getChildren().add(validerChoixButton);

        bp.setBottom(botHbox);

        botHbox.setAlignment(Pos.CENTER);

        //Center
        HBox centerHbox = new HBox();

        centerHbox.setAlignment(Pos.CENTER);

        HBox cartesHbox = new HBox(20);
        cartesHbox.setMinWidth(cartesEnJeu.length * 200 + (cartesEnJeu.length-1)*20);
        cartesHbox.setMinHeight(400 + 100);
        cartesHbox.setMaxHeight(400 + 100);
        System.out.println(cartesEnJeu.length);

        Map typesmap = new HashMap();
        typesmap.put('c', "F");
        typesmap.put('f', "G");
        typesmap.put('n', "S");
        typesmap.put('s', "W");
        typesmap.put('p', "D");
        typesmap.put('m', "M");

        //Cartes
        for(int i = 0; i<cartesEnJeu.length; i++){
            VBox carteVbox = new VBox();
            carteVbox.setPrefHeight(500);
            carteVbox.setPrefWidth(200);

            VBox paysagesVBox = new VBox();

            //System.out.println(typesmap.get("W"));
            Object type1 = typesmap.get(cartesEnJeu[i].getTypeCarte()[0]);
            Object type2 = typesmap.get(cartesEnJeu[i].getTypeCarte()[1]);

            int couronne1 = cartesEnJeu[i].getNombreCouronne()[0];
            int couronne2 = cartesEnJeu[i].getNombreCouronne()[1];

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
            Carte carte = cartesEnJeu[i];

            CarteChoix cartePane = new CarteChoix(paysagesVBox, carte);

            carteVbox.setOnMouseClicked(event -> setChoice(event, carte, cartePane));

            carteVbox.getChildren().add(numeroLabel);

            listeCarteVBox.add(cartePane);

        }

        centerHbox.getChildren().add(cartesHbox);

        centerHbox.setStyle("-fx-border-color: black");

        bp.setCenter(centerHbox);

        return new Scene(bp);
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

    private void confirmerChoix(CarteChoix cartechoisie){
        choixDesJoueurs.add(choixJoueur);
        cartechoisie.setChoosed(true, joueurQuiChoisis);

        //on augmente de un le numero du joueur qui joue
        if(n<ordreJoueurs.length - 1) {
            n++;
            joueurQuiChoisis = ordreJoueurs[n];
            System.out.println("Joueur qui choisis: " + joueurQuiChoisis.getNom());
            validerChoixButton.setDisable(true);

            //On modifie le text du boutton si c'est le dernier joueur qui choisi la pièce
            if(n== ordreJoueurs.length - 1){
                validerChoixButton.setText("Valider et placer les pièces");
            }
        }


        if(n == ordreJoueurs.length - 1){
            System.out.println("ready to go next");
        }

    }
}

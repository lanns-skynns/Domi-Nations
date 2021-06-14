package dominations.Controller;


import dominations.model.Joueur;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CResultat {
    ActionEvent event;
    List<Joueur> classement;
    GridPane gridPane =new GridPane();


    public CResultat(ActionEvent event, List<Joueur> classement){
        this.event=event;
        this.classement=classement;
        sceneResultat();
    }

    public void sceneResultat(){
        final URL cssURL = getClass().getResource("/dominations/css/recap.css");//css
        final Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        Stage window;
        window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        BorderPane bp=new BorderPane();
        bp.setPadding(new Insets(20,20,20,20));
        Scene scene = new Scene(bp,bounds.getWidth(), bounds.getHeight());
        scene.getStylesheets().add(cssURL.toExternalForm());

        HBox hb =new HBox();
        Label label = new Label("Classement");
        label.setAlignment(Pos.CENTER);
        label.setFont(Font.font(50));
        label.setId("labelTop");
        hb.setAlignment(Pos.CENTER);
        hb.getChildren().add(label);
       // bp.getChildren().add(hb);
        bp.setTop(hb);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(0, 0,bounds.getHeight()*0.4,0));
        gridPane.setVgap(20);
        gridPane.setHgap(20);
        afficherClassement();

        bp.setCenter(gridPane);
        HBox hBbas=new HBox();
        Button btnRecommencer = new Button();
        btnRecommencer.setText("Rejouer");
        Button btnQuitter = new Button();
        btnQuitter.setAlignment(Pos.CENTER);
        btnRecommencer.setAlignment(Pos.CENTER);
        btnQuitter.setText("Quitter");
        hBbas.getChildren().addAll(btnRecommencer,btnQuitter);
        hBbas.setAlignment(Pos.CENTER);
        bp.setBottom(hBbas);
        hBbas.setSpacing(20);

        btnRecommencer.setOnAction(event ->Recommencer(event));
        btnQuitter.setOnAction(event -> Quitter(window,bp));

        window.setScene(scene);
        Image logo = new Image("dominations/images/Kingdomino-Logo.png");
        window.getIcons().add(logo);
        window.setTitle("King-Domino");
        window.show();
    }

    public void afficherClassement(){
        int i=0;
        int score;
        String nomDuJoueur;
        int nbrJoueursClasse=classement.size();

        for (Joueur j:classement){
            System.out.println("tailllllle"+classement.size());
            nomDuJoueur=j.getNom();
            score=j.getRoyaume().calculerPoints();
            System.out.println(String.valueOf(score));
           // Label lab+"i" = new Label(nomDuJoueur);
            Label position = new Label("-"+String.valueOf(nbrJoueursClasse-i)+"-");
            Label infosJoueurClasse= new Label(nomDuJoueur);
            Label scoreJoueur= new Label (" ("+String.valueOf(score)+" points)");
            Color couleurJoueur=CRoyaume.styleEnFonctionJoueur(j.getCouleur());
            position.setTextFill(couleurJoueur);
            scoreJoueur.setTextFill(couleurJoueur);
            infosJoueurClasse.setTextFill(couleurJoueur);
            if( i==nbrJoueursClasse-1){
                position.setStyle("-fx-font-weight: bold");
                infosJoueurClasse.setStyle("-fx-font-weight: bold");
                scoreJoueur.setStyle("-fx-font-weight: bold");
            }

            gridPane.add(position,0,nbrJoueursClasse-i);
            gridPane.add(infosJoueurClasse,1,nbrJoueursClasse-i);
            gridPane.add(scoreJoueur,2,nbrJoueursClasse-i);
            i++;
        }

    }

    public  void Recommencer(ActionEvent event){

        CAccueil cAccueil=new  CAccueil();
        cAccueil.actionEvent=event;
        cAccueil.Recommencer();
    }

    public  void Quitter(Stage stage,BorderPane bp){
            Alert alert= new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Quitter");
            alert.setHeaderText(" Fermeture de la fenêtre");
            alert.setContentText("êtes vous sûr de vouloir quitter");

            if(alert.showAndWait().get()== ButtonType.OK){
                stage=(Stage)bp.getScene().getWindow();// pour recup la fenetre courant;
                stage.close();
            }

    }

}

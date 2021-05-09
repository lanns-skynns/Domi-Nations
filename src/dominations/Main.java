package dominations;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Screen;
import javafx.stage.Stage;
import dominations.model.*;
import dominations.Controller.*;

import java.awt.*;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Main   extends Application {

    // final URL cssURL = getClass().getResource("style.css");
    // scene.getStylesheets().add(cssURL.toExternalForm());

    public void start(Stage primaryStage) throws Exception {
        // recup taille ecran
        final Screen screen = Screen.getPrimary();
        double height = screen.getBounds().getHeight();
        double width = screen.getBounds().getWidth();

        //creation d'un royaume aleatoire
        Royaume royaume1 = new Royaume(Couleur.BLEU);
        royaume1.genererRoyaumeAleatoire();
        royaume1.afficherTypesGrille();

        Royaume royaume2 = new Royaume(Couleur.ROUGE);
        Royaume royaume3 = new Royaume(Couleur.VERT);

        //creation d'une carte
        int[] courronnes = {0, 1};
        char[] paysages = {'c', 'p'};
        Carte cartetest = new Carte(36, courronnes , paysages);

        int[] courronnes1 = {1, 0};
        char[] paysages1 = {'f', 'c'};
        Carte cartetest1 = new Carte(27, courronnes1 , paysages1);

        int[] courronnes2 = {0, 0};
        char[] paysages2 = {'c', 'f'};
        Carte cartetest2 = new Carte(13, courronnes2 , paysages2);

        Carte[] cartesAPiocher = {cartetest, cartetest1, cartetest2};

        Joueur joueur1 = new Joueur("Julo", royaume1, 0);
        Joueur joueur2 = new Joueur("Kevin", royaume2, 0);
        Joueur joueur3 = new Joueur("Ketchup", royaume3, 0);

        Joueur[] joueursliste = {joueur1, joueur2, joueur3};

        //Scene scene = new CAccueil().Accueil(); //scene page d'accueil
        Scene sceneR = new CRoyaume(joueur1, cartetest).RoyaumeScene(); //scene royaume
        Scene sceneC = new CChoixCarte(cartesAPiocher, joueursliste).ChoixScene();

        // paramètre Stage
        Image logo =new Image("dominations/images/Kingdomino-Logo.png");
        primaryStage.getIcons().add(logo);
        primaryStage.setMaximized(true);
        primaryStage.setResizable(false);
        primaryStage.setTitle("King-Domino");
        primaryStage.setScene(sceneC);
        primaryStage.show();

    }

    public static void main(String[] args) throws Exception {
        //new Partie();
        launch();
        //new Partie();
    }
}

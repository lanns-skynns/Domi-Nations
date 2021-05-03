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

        Scene scene = new CAccueil().Accueil(); //scene page d'accueil

        // paramètre Stage
        Image logo=new Image("dominations/images/Kingdomino-Logo.png");
        primaryStage.getIcons().add(logo);
        primaryStage.setMaximized(true);
        primaryStage.setTitle("King-Domino");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) throws Exception {
        new Partie();
       // launch();
        //new Partie();
    }
}

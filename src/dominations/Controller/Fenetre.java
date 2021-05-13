package dominations.Controller;

import dominations.model.Partie;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;
import dominations.Controller.CAccueil;

public class Fenetre  extends Application {

    public void start(Stage primaryStage) throws Exception {
        // recup taille ecran

        final Screen screen = Screen.getPrimary();
        double height = screen.getBounds().getHeight();
        double width = screen.getBounds().getWidth();

     //   primaryStage= new CAccueil().Accueil();
        // paramètre Stage
        Image logo = new Image("dominations/images/Kingdomino-Logo.png");
        primaryStage.getIcons().add(logo);
        primaryStage.setMaximized(true);
        primaryStage.setTitle("King-Domino");
        primaryStage.show();

    }

    public void lancer(){
        launch();
    }

}

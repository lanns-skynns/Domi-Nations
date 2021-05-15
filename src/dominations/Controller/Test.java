package dominations.Controller;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

;

public class Test {


    public  void test1(ActionEvent event) {
        Pane pane = new Pane();
        Button button = new Button("yes bebe");
        System.out.println("allalaalallallala");
        button.setLayoutX(500);
        button.setLayoutY(500);
        pane.getChildren().add(button);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene= new Scene(pane);
        window = parametresStages(window);
        window.setScene(scene);
        window.setFullScreen(true);
        window.show();
    }

    public Stage parametresStages(Stage stage){
        Image logo = new Image("dominations/images/Kingdomino-Logo.png");
        stage.getIcons().add(logo);
        stage.setMaximized(true);
        stage.setTitle("King-Domino");
        return  stage;
    }

}

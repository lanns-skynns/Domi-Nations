package dominations.Controller;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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

import javax.swing.border.EmptyBorder;
import java.io.File;
import java.net.URL;
import java.sql.Struct;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class CAccueil  {
    private int nbrJoueurs = 0;

    List<String> listeJoueurs =new ArrayList<>();

    public void CAccueil(){

    }

    public Scene Accueil (){

        final URL cssURL = getClass().getResource("/dominations/css/accueil.css");//css


        final Screen screen = Screen.getPrimary();
        double height = screen.getBounds().getHeight();
        double width = screen.getBounds().getWidth();

        List<Node> listeNode=new  ArrayList<>();




        // les différents conteneurs
        BorderPane bp=new BorderPane();
        HBox hBox=new HBox();
        GridPane gridPane = new GridPane();
        Label alert=new Label();
        gridPane.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));

        //paramètre border pan
        bp.setPadding(new Insets(20,20,20,20));

        //top
        Label label=new Label("Nouvelle partie");
        label.setAlignment(Pos.CENTER);
        label.setFont(Font.font(50));
        hBox.getChildren().add(label);
        hBox.setAlignment(Pos.CENTER);

        //center
        //paramètre grid pan
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(0,width*0.05,height*0.4,0));
        gridPane.setVgap(20);
        gridPane.setHgap(20);

        AtomicReference<List<Node>> joueurs= new AtomicReference<>(ajouterJoeur());
        gridPane.add(joueurs.get().get(0), 0, 0);
        gridPane.add(joueurs.get().get(1), 1, 0);
        TextField tfj1=(TextField)joueurs.get().get(1);



        System.out.println(this.nbrJoueurs);
        joueurs.set(ajouterJoeur());
        gridPane.add(joueurs.get().get(0), 0, 1);
        gridPane.add(joueurs.get().get(1), 1, 1);
        TextField tfj2=(TextField)joueurs.get().get(1);


        System.out.println(this.nbrJoueurs);

        ImageView imageViewPlus=creerImageView("src/dominations/images/accueil/boutonPlus.png",30,30);

        gridPane.add(imageViewPlus, nbrJoueurs-1, 2);
        ImageView imageViewSupprimerj3=creerImageView("src/dominations/images/accueil/boutonSupprimer.jpg",30,30);
        ImageView imageViewSupprimerj4=creerImageView("src/dominations/images/accueil/boutonSupprimer.jpg",30,30);
        imageViewPlus.setOnMouseClicked(mouseEvent -> {
            if(nbrJoueurs+1<5) {

                joueurs.set(ajouterJoeur());
                gridPane.add(joueurs.get().get(0), 0, nbrJoueurs - 1);
                gridPane.add(joueurs.get().get(1), 1, nbrJoueurs - 1);
                listeNode.add(joueurs.get().get(0));
                listeNode.add(joueurs.get().get(1));

                if (nbrJoueurs==3){
                    gridPane.add(imageViewSupprimerj3,2,nbrJoueurs-1);
                }
                else {
                    gridPane.add(imageViewSupprimerj4,2,nbrJoueurs-1);
                    imageViewSupprimerj3.setVisible(false);
                }

                System.out.println(nbrJoueurs);
                try {
                    gridPane.add(imageViewPlus, 1, nbrJoueurs);

                }
                catch(Exception e) {
                   // System.out.println(e);
                }
            }
            else{
                System.out.println("assez de joueurs");
            }

            if (nbrJoueurs==4) {
                gridPane.getChildren().remove(imageViewPlus);
            }
        });

        Button button=new Button("Commencer");
        button.setId("bouton");
        button.setMinWidth(30);
        button.setMaxWidth(300);

        imageViewSupprimerj3.setOnMouseClicked(mouseEvent ->{
            gridPane.getChildren().removeAll(imageViewSupprimerj3,listeNode.get(0),listeNode.get(1));
            listeNode.remove(0);
            listeNode.remove(0);
            this.nbrJoueurs--;
            gridPane.getChildren().remove(imageViewPlus); // je supprime l'image afin de le récréé dans à nouveau en la changeant de position
            gridPane.add(imageViewPlus,1,nbrJoueurs);


        });

        imageViewSupprimerj4.setOnMouseClicked(mouseEvent ->{
            gridPane.getChildren().removeAll(imageViewSupprimerj4,listeNode.get(2),listeNode.get(3));
            listeNode.remove(2);
            listeNode.remove(2);
            imageViewSupprimerj3.setVisible(true);
            this.nbrJoueurs--;
            gridPane.add(imageViewPlus,1,nbrJoueurs);
        });

         button.setOnAction((e)->{
             int i;
             this.listeJoueurs=getNomJsoueurs(gridPane);
             System.out.println(listeJoueurs.size());
             if(listeJoueurs.get(0)==null){
                 String champsVide="";
                 for (i=1;i<listeJoueurs.size();i++){
                     System.out.println("Attention ! champs joueur"+listeJoueurs.get(i)+ " vide \n");
                 }
                 //alert.setText(champsVide);
             }
             System.out.println("nb : "+nbrJoueurs);
         });

        gridPane.add(button,1,this.nbrJoueurs+2);



        URL url = getClass().getResource("style.css");
        // setUserAgentStylesheet("style.css");
       // bp.getChildren().add(gridPane);
      ;



        bp.setCenter(gridPane);
        bp.setTop(hBox);

        Scene scene=new Scene(bp);

        scene.getStylesheets().add(cssURL.toExternalForm());
        return scene;


       // final URL cssURL = getClass().getResource("style.css");
       // scene.getStylesheets().add(cssURL.toExternalForm());



    }

    private ImageView creerImageView(String url,int width,int height){
        File file = new File(url);

        Image image = new Image(file.toURI().toString());
        ImageView imageView = new ImageView(image);
        imageView.setId("imageView");
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        return  imageView;
    }

    private List <String> getNomJsoueurs(GridPane gridPane){
            List <String> nomsJoueurs= new ArrayList<>();
            boolean dejaClear=false;
            byte i;
            TextField tf;
            for (i=0;i<nbrJoueurs;i++){
                tf= (TextField) getNodeFromGridPane(gridPane,1,i);
                if (tf.getText().length()==0){
                    if( ! dejaClear) {
                        nomsJoueurs.clear();
                        nomsJoueurs.add(null);
                        nomsJoueurs.add(String.valueOf(i+1));
                        dejaClear=true;
                    }
                    else {
                        nomsJoueurs.add(String.valueOf(i+1));
                        System.out.println("Veillez saisir un nom pour le joueur " + i);
                    }
                    if(i==nbrJoueurs-1) {
                        return nomsJoueurs;
                    }
                }
                if( ! dejaClear) {
                    nomsJoueurs.add(tf.getText());
                }
            }
            return  nomsJoueurs;
    }

    private Node getNodeFromGridPane(GridPane gridPane, int col, int row) {
        for (Node node : gridPane.getChildren()) {
            if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                return node;
            }
        }
        return null;
    }

    private List<Node> ajouterJoeur(){
        List<Node> list=new ArrayList();
        this.nbrJoueurs+=1;
        Text j = new Text("Joueur "+this.nbrJoueurs);
        TextField champ = new TextField();
        list.add(j);
        list.add(champ);
        return  list;

    }




}

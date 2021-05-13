package dominations.Controller;

import dominations.model.Partie;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class CAccueil extends Application {
    List<String> listeJoueurs =new ArrayList<>();
    Map map = new HashMap();
    private int nbrJoueurs = 0;

    @Override
    public void start(Stage primaryStage) throws Exception {

        final URL cssURL = getClass().getResource("/dominations/css/accueil.css");//css


        final Screen screen = Screen.getPrimary();
        double height = screen.getBounds().getHeight();
        double width = screen.getBounds().getWidth();

        List<Node> listeNode=new  ArrayList<>();

        // les différents conteneurs
        BorderPane bp=new BorderPane();
        HBox hBox=new HBox();
        GridPane gridPane = new GridPane();
        gridPane.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));

        //paramètre border pan
        bp.setPadding(new Insets(20,20,20,20));

        //top
        Label label=new Label("Nouvelle partie");
        label.setAlignment(Pos.CENTER);
        label.setTextFill(Color.WHITE);
        label.setId("textHeader");
        label.setFont(Font.font(50));
        hBox.getChildren().add(label);
        hBox.setAlignment(Pos.CENTER);
        hBox.setId("hboxHeader");


        //center
        //paramètre grid pan
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(0,width*0.05,height*0.4,0));
        gridPane.setVgap(20);
        gridPane.setHgap(20);
       // gridPane.setMaxWidth(width*0.5);

        AtomicReference<List<Node>> joueurs= new AtomicReference<>(ajouterJoeur());
        gridPane.add(joueurs.get().get(0), 0, 0);
        gridPane.add(joueurs.get().get(1), 1, 0);



        System.out.println(this.nbrJoueurs);
        joueurs.set(ajouterJoeur());
        gridPane.add(joueurs.get().get(0), 0, 1);
        gridPane.add(joueurs.get().get(1), 1, 1);


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
        SplitPane sp=new SplitPane();
        sp.setOrientation(Orientation.VERTICAL);


        gridPane.add(button,1,this.nbrJoueurs+2);
        bp.setCenter(sp);
        bp.setTop(hBox);
        HBox hBox1 = new HBox();
        Label texte =new Label(" Que le meilleur gagne ...");
        texte.setId("texteErreur");
        hBox1.getChildren().add(texte);

        BorderPane.setMargin(hBox,new Insets(5,0,1,0));
        gridPane.setId("gridPane");

        sp.getItems().addAll(hBox1,gridPane);
        sp.setDividerPositions(0.1);
        hBox1.setAlignment(Pos.CENTER);//

        bp.setCenter(sp);
        bp.getStyleClass().add("grey");

        BorderPane.setAlignment(hBox1, Pos.CENTER);

        Scene scene=new Scene(bp);
        scene.setFill(new LinearGradient(
                0, 0, 1, 1, true,                      //sizing
                CycleMethod.NO_CYCLE,                  //cycling
                new Stop(0, Color.web("#81c483")),     //colors
                new Stop(1, Color.web("#fcc200")))
        );
        scene.getStylesheets().add(cssURL.toExternalForm());
        Stage stage= new Stage();
        stage.setScene(scene);
        Image logo = new Image("dominations/images/Kingdomino-Logo.png");
        stage.getIcons().add(logo);
        stage.setMaximized(true);
        stage.setTitle("King-Domino");
        stage.show();


         button.setOnAction((e)->{
             this.listeJoueurs=getNomJsoueurs(gridPane);
             System.out.println(listeJoueurs.size());
             if(listeJoueurs.get(0)==null){
                 String champsVide;
                 int d;
                 int tailleListeoueurs=listeJoueurs.size();
                 String bonAccord = " le joueur numéro ";
                 if (tailleListeoueurs>2){
                     bonAccord= " les joueurs numéros ";
                 }
                 TextField tf;
                 champsVide= "Attention, Veillez saisir un pseudo pour" +bonAccord;
                 for (d=1;d<tailleListeoueurs;d++){
                     champsVide += ( d !=tailleListeoueurs-1)? (listeJoueurs.get(d) + " et "):listeJoueurs.get(d);
                     tf= (TextField) map.get(Integer.parseInt(listeJoueurs.get(d)));
                     tf.setBorder(new Border(new BorderStroke(Color.rgb(250,50,100), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
                 }
                 texte.setText(champsVide);

                 int s,q;
                 boolean supprimerBorder=true;
                 for (s=1;s< map.size()+1;s++){
                     supprimerBorder=true;
                     for (q=1;q<tailleListeoueurs;q++){
                         if(s==Integer.parseInt(listeJoueurs.get(q))){
                             supprimerBorder=false;
                         }
                     }
                     if (supprimerBorder){
                         tf= (TextField) map.get(s);
                         tf.setBorder(new Border(new BorderStroke(Color.GREEN, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
                     }

                 }


                 texte.setTextFill(Color.rgb(250,50,100));
                  //TextField lab= (TextField)listeNode.get(3);
                  // lab.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));

             }
             else if( listeJoueurs.get(0)=="00"){
                 String champsIdentique;
                 int d;
                 champsIdentique= "Attention, les joueurs numéros ";
                 int tailleListeoueurs=listeJoueurs.size();
                 TextField tf;
                 for (d=1;d<tailleListeoueurs;d++){
                     champsIdentique += ( d !=tailleListeoueurs-1)? (listeJoueurs.get(d) + " et "):listeJoueurs.get(d) + "  ";
                      tf= (TextField) map.get(Integer.parseInt(listeJoueurs.get(d)));
                     tf.setBorder(new Border(new BorderStroke(Color.rgb(200,75,150), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
                 }
                 champsIdentique +=  "ont le même pseudo";

                 int s,q;
                 boolean supprimerBorder;
                 for (s=1;s<map.size()+1;s++){
                     supprimerBorder=true;
                     for (q=1;q<tailleListeoueurs;q++){
                         if(s==Integer.parseInt(listeJoueurs.get(q))){
                             supprimerBorder=false;
                         }
                     }
                     if (supprimerBorder){
                         tf= (TextField) map.get(s);
                         tf.setBorder(new Border(new BorderStroke(Color.GREEN, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
                     }

                 }
                 texte.setText(champsIdentique);
                 texte.setTextFill(Color.rgb(200,75,150));

             }
             else{
                 //pas.getChildren().add(new Text("yes"));
                  Partie partie = new Partie();
                 partie.setRecupListeNomsJoueurs(listeJoueurs);
                 partie.setNbrJoueurs(listeJoueurs.size());
                 partie.Partie2(e);
                // new Test().test1(e);
                 System.out.println("kfkf");
             }
             System.out.println("nb : "+nbrJoueurs);
         });

    }

    public void lancer(){
        launch();
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
            }
            if( ! dejaClear) {
                nomsJoueurs.add(tf.getText());
            }
        }
        if (dejaClear){
            return nomsJoueurs;
        }
        int j,k;
        int nbrJoueurs=nomsJoueurs.size();
        boolean nomsIdentiques = false;
        List<String> memeNoms = new ArrayList<>();
        for (j=0;j<nbrJoueurs;j++){
            for (k=j+1;k<nbrJoueurs;k++){
                if(nomsJoueurs.get(j).equals(nomsJoueurs.get(k))){
                    if ( ! nomsIdentiques) {
                        memeNoms.add("00");
                        memeNoms.add(Integer.toString(j+1));
                        memeNoms.add(Integer.toString(k+1));
                        nomsIdentiques = true;
                    }else{
                        memeNoms.add(Integer.toString(j+1));
                        memeNoms.add(Integer.toString(k+1));
                    }
                }
            }
        }

        if(nomsIdentiques){
            Set<String> setListe = memeNoms.stream().collect(Collectors.toSet());
            memeNoms.clear();
            for (String noms:setListe){
                memeNoms.add(noms);
            }
            return memeNoms;
        }
        // faire ube double boucle pour vérifier que tous les noms sont différents.
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
        Label j = new Label("Joueur "+this.nbrJoueurs);
        j.setId("labelJoueurs");
        TextField champ = new TextField();
        list.add(j);
        list.add(champ);
        this.map.put(this.nbrJoueurs,champ);
        return  list;

    }
}

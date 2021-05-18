package dominations.model;

import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import java.lang.Math;
import dominations.model.Cellule;
import dominations.model.Joueur;
import dominations.model.Royaume;
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
import java.sql.Struct;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class PaysagePane {

    private double x;
    private double y;
    private double width;
    private ImageView pieceIW;
    private int direction;

    private String pathImage;

    public PaysagePane(double x, double y, double width, ImageView pieceIW) {
        this.width = width;
        this.y = y;
        this.x = x;
        this. pieceIW = pieceIW;
        this.direction = 3;
    }


    public void draw(){
        pieceIW.setFitHeight(width);
        pieceIW.setFitWidth(width);

        pieceIW.setTranslateX(x);
        pieceIW.setTranslateY(y);

    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double w){
        this.width = w;
        pieceIW.setFitHeight(w);
        pieceIW.setFitWidth(w);
    }

    public double getX(){
        return x;
    }

    public void setX(double x){
        this.x = x;
    }

    public void setY(double y){
        this.y = y;
    }

    public void setDirection(int dire){
        this.direction = dire;
    }

    public int getDirection(){
        return this.direction;
    }

    public double getY() {
        return y;
    }
}


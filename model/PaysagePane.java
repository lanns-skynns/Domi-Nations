package dominations.model;

import javafx.scene.image.ImageView;

public class PaysagePane {

    private double x;
    private double y;
    private double width;
    private final ImageView pieceIW;
    private int direction;

    private String pathImage;

    public PaysagePane(double x, double y, double width, ImageView pieceIW) {
        this.width = width;
        this.y = y;
        this.x = x;
        this.pieceIW = pieceIW;
        this.direction = 3;
    }


    public void draw() {
        pieceIW.setFitHeight(width);
        pieceIW.setFitWidth(width);

        pieceIW.setTranslateX(x);
        pieceIW.setTranslateY(y);

    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double w) {
        this.width = w;
        pieceIW.setFitHeight(w);
        pieceIW.setFitWidth(w);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public int getDirection() {
        return this.direction;
    }

    public void setDirection(int dire) {
        this.direction = dire;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}


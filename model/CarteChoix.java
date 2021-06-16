package dominations.model;

import javafx.scene.layout.VBox;

public class CarteChoix {
    private boolean isFocused;
    private boolean isChoosed;
    private Joueur joueur;
    private final VBox carteVbox;

    public CarteChoix(VBox carteVbox, Carte carte) {
        this.isFocused = false;
        this.isChoosed = false;
        this.carteVbox = carteVbox;
    }

    public void setChoosed(boolean state, Joueur joueur) {
        this.isChoosed = state;
        this.joueur = joueur;
        drawBorders();
    }

    public boolean getFocused() {
        return this.isFocused;
    }

    public void setFocused(boolean state) {
        this.isFocused = state;
        drawBorders();
    }

    public boolean getChoosed() {
        return this.isChoosed;
    }

    public void drawBorders() {
        if (this.isFocused == true) {
            this.carteVbox.setStyle("-fx-border-color: black; -fx-border-width: 3px");
        } else if (this.isFocused == false) {
            this.carteVbox.setStyle("-fx-border-color: white; -fx-border-width: 0px");
        }

        if (this.isChoosed == true) {

            Couleur couleur = this.joueur.getCouleur();
            String couleurAffichee = "black";

            if (couleur == Couleur.BLEU) {
                couleurAffichee = "blue";
            } else if (couleur == Couleur.ROUGE) {
                couleurAffichee = "red";
            } else if (couleur == Couleur.JAUNE) {
                couleurAffichee = "yellow";
            } else if (couleur == Couleur.VERT) {
                couleurAffichee = "green";
            }

            this.carteVbox.setStyle("-fx-border-color: " + couleurAffichee + "; -fx-border-width: 4px");
        }

    }

}

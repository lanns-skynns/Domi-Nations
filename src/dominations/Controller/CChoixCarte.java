package dominations.Controller;

import dominations.model.Carte;
import dominations.model.*;
import javafx.scene.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import java.lang.Math;

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
import java.sql.Array;
import java.sql.Struct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class CChoixCarte {

    private int[] numerosCartes;
    private Joueur[] ordreJoueurs;

    public CChoixCarte(Carte[] cartesEnJeu){

    }

    public Scene ChoixScene() {
        final URL cssURL = getClass().getResource("/dominations/css/royaume.css");//css

        final Screen screen = Screen.getPrimary();
        double height = screen.getBounds().getHeight();
        double width = screen.getBounds().getWidth();
    }
}

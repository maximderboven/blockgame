package blockgame.view.game;

import blockgame.view.menu.MenuView;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

/**
 * Alexie Chaerle
 * 26/02/2021
 */
public class GameView extends MenuView {

    private Label lblUser;
    private Label lblScore;
    private Label lblHighscores;
    private Label brdBoard;
    private VBox vbox;
    private HBox hbox1;
    private HBox hbox2;

    private Label imgBlock1;
    private Label imgBlock2;
    private Label imgBlock3;

    // Constructor
    public GameView() {
        super();
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        lblUser = new Label("Logged in as: ");
        lblScore = new Label("Current score: ");
        lblHighscores = new Label("Highscore: ");
        brdBoard = new Label("Speelbord");
        imgBlock1 = new Label("block 1");
        imgBlock2 = new Label("block 2");
        imgBlock3 = new Label("block 3");

        hbox1 = new HBox(lblUser,lblScore,lblHighscores);
        hbox2 = new HBox(imgBlock1,imgBlock2,imgBlock3);
        vbox = new VBox(hbox1,brdBoard,hbox2);
    }

    private void layoutNodes() {
        super.setCenter(vbox);
        this.setMinHeight(800);
        this.setMinWidth(500);
    }



}

package blockgame.view.game;

import blockgame.model.Board;
import blockgame.model.Piece;
import blockgame.view.menu.MenuView;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import javax.swing.border.AbstractBorder;
import java.util.ArrayList;

/**
 * Alexie Chaerle
 * 26/02/2021
 */
public class GameView extends BorderPane {

    private Label lblUser;
    private Label lblScore;
    private Label lblHighscores;
    private GridPane Board;
    private VBox vbox;
    private HBox hbox2;

    private MenuView mv;

    private int boardsize;

    private int capacity;
    private ArrayList<Piece> pieces = new ArrayList<>(capacity);


    // Constructor
    public GameView() {
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        mv = new MenuView();
        lblUser = new Label("Logged in as: ");
        lblScore = new Label("Current score: ");
        lblHighscores = new Label("Highscore: ");
        Board = new GridPane();
        boardsize = 10;
    }

    public GridPane getBoard() {
        return Board;
    }

    private void layoutNodes() {
        HBox hbox1 = new HBox(lblUser, lblScore, lblHighscores);
        Board.getStyleClass().add("game-grid");

        for (int i = 0; i < boardsize; i++) {
            ColumnConstraints column = new ColumnConstraints(40);
            Board.getColumnConstraints().add(column);
        }

        for (int i = 0; i < boardsize; i++) {
            RowConstraints row = new RowConstraints(40);
            Board.getRowConstraints().add(row);
        }

        for (int i = 0; i < boardsize; i++) {
            for (int j = 0; j < boardsize; j++) {
                Pane pane = new Pane();
                pane.getStyleClass().add("game-grid-cell");
                if (i == 0) {
                    pane.getStyleClass().add("first-column");
                }
                if (j == 0) {
                    pane.getStyleClass().add("first-row");
                }
                Board.add(pane, i, j);
            }
        }
        hbox2 = new HBox();
        vbox = new VBox(hbox1,Board,hbox2);
        this.setTop(mv);
        this.setCenter(vbox);
        this.setMinHeight(500);
        this.setMinWidth(500);

        this.getStylesheets().add("/stylesheets/style.css");
    }

    public void setBoardsize(int boardsize) {
        this.boardsize = boardsize;
    }

    public void setLblUser(String text) {
        this.lblUser.setText(text);
    }

    public void setLblScore(String text) {
        this.lblScore.setText(text);
    }

    public void setLblHighscores(String text) {
        this.lblHighscores.setText(text);
    }

    public MenuView getMv() {
        return mv;
    }

    public ArrayList<Piece> getPieces() {
        return pieces;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public HBox getHbox2() {
        return hbox2;
    }
}

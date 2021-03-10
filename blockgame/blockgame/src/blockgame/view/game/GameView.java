package blockgame.view.game;

import blockgame.model.Piece;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.util.ArrayList;

/**
 * Alexie Chaerle
 * 26/02/2021
 */
public class GameView extends BorderPane {

    private Label lblUser;
    private Label lblScore;
    private Label lblHighscores;
    private GridPane gridBoard;
    private VBox vbox;
    private VBox vBoxBlocks;
    private int boardsize;
    private int capacity;
    private ArrayList<Piece> pieces = new ArrayList<>(capacity);


    // Constructor
    public GameView() {
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        lblUser = new Label("Logged in as: ");
        lblScore = new Label("Current score: ");
        lblHighscores = new Label("Highscore: ");
        gridBoard = new GridPane();
        boardsize = 10;
    }


    private void layoutNodes() {

        // Top
        GridPane gridInfo = new GridPane();
        gridInfo.add(lblUser, 0, 0);
        gridInfo.add(lblScore, 1, 0);
        gridInfo.add(lblHighscores, 2, 0);
        gridInfo.setPadding(new Insets(70, 0, 0 ,100));
        lblScore.setPadding(new Insets(0,0,0,115));
        lblHighscores.setPadding(new Insets(0,0,0,105));
        lblScore.getStyleClass().add("game-label");
        lblHighscores.getStyleClass().add("game-label");
        lblUser.getStyleClass().add("game-label");
        super.setTop(gridInfo);

        //gridBoard.getStyleClass().add("game-grid");
        super.setCenter(gridBoard);
        gridBoard.setAlignment(Pos.CENTER);

        for (int i = 0; i < boardsize; i++) {
            ColumnConstraints column = new ColumnConstraints(40);
            gridBoard.getColumnConstraints().add(column);
        }
        for (int i = 0; i < boardsize; i++) {
            RowConstraints row = new RowConstraints(40);
            gridBoard.getRowConstraints().add(row);
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
                gridBoard.add(pane, i, j);
            }
        }
        vBoxBlocks = new VBox();
        vBoxBlocks.setPadding(new Insets(80,0,0,0));
        vBoxBlocks.setPrefWidth(250);

        for (Node nd : vBoxBlocks.getChildren()){
            nd.getStyleClass().add("blocks");
        }

        this.setRight(vBoxBlocks);
        this.setMinHeight(675);
        this.setMinWidth(900);
        setBackground(new Background(new BackgroundImage(new Image("/images/gamebgedit.png"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
        this.getStylesheets().add("/stylesheets/style.css");
    }

    void setBoardsize(int boardsize) {
        this.boardsize = boardsize;
    }

    void setLblUser(String text) {
        this.lblUser.setText(text);
    }

    void setLblScore(String text) {
        this.lblScore.setText(text);
    }

    void setLblHighscores(String text) {
        this.lblHighscores.setText(text);
    }

    ArrayList<Piece> getPieces() {
        return pieces;
    }

    void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    VBox getBlocksBox() {
        return vBoxBlocks;
    }

    GridPane getGridBoard() {
        return gridBoard;
    }
}

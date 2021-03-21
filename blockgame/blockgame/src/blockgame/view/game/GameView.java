package blockgame.view.game;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

/**
 * Maxim Derboven
 * 26/02/2021
 */
public class GameView extends BorderPane {

    private Label lblUser;
    private Label lblScore;
    private Label lblHighscores;
    private GridPane gridBoard;
    private VBox vBoxBlocks;
    private int boardsize;
    private DropShadow borderGlow;


    // Constructor
    public GameView(int boardsize) {
        initialiseNodes(boardsize);
        layoutNodes();
    }

    private void initialiseNodes(int boardsize) {
        lblUser = new Label("Logged in as: ");
        lblScore = new Label("Current score: ");
        lblHighscores = new Label("Highscore: ");
        gridBoard = new GridPane();
        this.boardsize = boardsize;
        borderGlow = new DropShadow();
    }

    private void layoutNodes() {

        int depth = 70;
        borderGlow.setOffsetY(0f);
        borderGlow.setOffsetX(0f);
        borderGlow.setColor(Color.WHITE);
        borderGlow.setWidth(depth);
        borderGlow.setHeight(depth);

        // Top
        GridPane gridInfo = new GridPane();
        gridInfo.add(lblUser, 0, 0);
        gridInfo.add(lblScore, 1, 0);
        gridInfo.add(lblHighscores, 2, 0);
        gridInfo.setPadding(new Insets(70, 0, 0 ,100));
        lblScore.setPadding(new Insets(0,0,0,85));
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
        /* View gaat het bord aanmaken : Presenter gaat het bord handelen */
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
        vBoxBlocks.setSpacing(20);
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

    void setLblUser(String text) {
        this.lblUser.setText(text);
    }

    void setLblScore(String text) {
        this.lblScore.setText(text);
    }

    void setLblHighscores(String text) {
        this.lblHighscores.setText(text);
    }

    VBox getBlocksBox() {
        return vBoxBlocks;
    }

    GridPane getGridBoard() {
        return gridBoard;
    }

    DropShadow getBorderGlow() {
        return borderGlow;
    }

}

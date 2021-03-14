package blockgame.view.gameover;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

/**
 * Alexie Chaerle
 * 12/03/2021
 */
public class GameOverView extends BorderPane {

    // Attributen
    private Label lblScore;
    private Label lblHighscore;
    private ImageView imgSave;
    private Label lblDifficulty;

    public GameOverView() {
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        lblScore = new Label("Score: ");
        lblHighscore = new Label("Highscore: ");
        imgSave = new ImageView("/images/SaveButton.png");
        lblDifficulty = new Label();

        GridPane grid = new GridPane();
        grid.add(lblScore, 0, 0);
        grid.add(lblHighscore, 0, 1);
        grid.add(imgSave, 0, 2);
        grid.setAlignment(Pos.CENTER);
        super.setCenter(grid);
        super.setBottom(lblDifficulty);
    }

    private void layoutNodes() {
        setBackground(new Background(new BackgroundImage(new Image("/images/gameOver.png"),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT)));
        Font.loadFont(getClass().getResourceAsStream("/fonts/Woodtrap.ttf"), 12);
        getStylesheets().add("/stylesheets/gameover.css");
        lblScore.setId("label");
        lblHighscore.setId("label");
        lblDifficulty.setId("difficulty");
        lblScore.setPadding(new Insets(195, 0, 0, 0));
        lblHighscore.setPadding(new Insets(65, 0, 40, 0));
        imgSave.setFitWidth(120);
        imgSave.setFitHeight(70);
    }

    Label getLblScore() {
        return lblScore;
    }

    Label getLblHighscore() {
        return lblHighscore;
    }

    ImageView getImgSave() {
        return imgSave;
    }

    Label getLblDifficulty() {
        return lblDifficulty;
    }
}

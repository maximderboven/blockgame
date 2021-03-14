package blockgame.view.gameover;

import blockgame.model.Game;
import blockgame.view.mainMenu.MainMenuPresenter;
import blockgame.view.mainMenu.MainMenuView;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Alexie Chaerle
 * 12/03/2021
 */
public class GameOverPresenter {
    private Game model;
    private GameOverView view;
    public static final char FILE_SEPARATOR = System.getProperties().getProperty("file.separator").charAt(0);
    private Media mdGameOver;
    private Media mdGameOverHighscore;
    private Media clicksound;

    public GameOverPresenter(Game model, GameOverView view) {
        this.model = model;
        this.view = view;
        Path soundPath1 = Paths.get("blockgame" + FILE_SEPARATOR + "resources" + FILE_SEPARATOR + "sounds" + FILE_SEPARATOR + "gameover.mp3");
        mdGameOver = new Media(new File(soundPath1.toString()).toURI().toString());
        Path soundPath2 = Paths.get("blockgame" + FILE_SEPARATOR + "resources" + FILE_SEPARATOR + "sounds" + FILE_SEPARATOR + "highscore.mp3");
        mdGameOverHighscore = new Media(new File(soundPath2.toString()).toURI().toString());
        Path soundPath = Paths.get("blockgame" + FILE_SEPARATOR + "resources" + FILE_SEPARATOR + "sounds" + FILE_SEPARATOR + "click.mp3");
        clicksound = new Media(new File(soundPath.toString()).toURI().toString());
        addEventHandlers();
        updateView();
    }

    private void updateView() {
        // Als de difficulty aan staat, label tonen
        if (!model.getPlayablePieces().isDifficulty()){
            view.getLblDifficulty().setText("Difficulty is disabled, score wont be updated.");
        }
        // Score en highscore updaten
        view.getLblScore().setText("Score : " + model.getScoreboard().getScore());
        view.getLblHighscore().setText("Highscore : " + model.getPlayer().getHighscore());
        if (model.getPlayer().getHighscore() == model.getScoreboard().getScore()){
            view.setBackground(new Background(new BackgroundImage(new Image("/images/gameOverNewHighscore.png"),
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.DEFAULT,
                    BackgroundSize.DEFAULT)));
            if (model.isMusic()) {
                new MediaPlayer(mdGameOverHighscore).play();
            }
        }else if (model.isMusic()) {
            new MediaPlayer(mdGameOver).play();
        }
    }

    private void addEventHandlers() {
        view.getImgSave().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (model.isMusic()) {
                    new MediaPlayer(clicksound).play();
                }
                if (model.getScoreboard().getScore() == model.getPlayer().getHighscore() && model.getPlayablePieces().isDifficulty()) {
                    try {
                        model.getAm().save();
                    } catch (IOException e) {
                        view.getLblError().setText(e.getMessage());
                    }
                }

                // Terug naar main menu
                MainMenuView mv = new MainMenuView();
                MainMenuPresenter mp = new MainMenuPresenter(model, mv);
                view.getScene().setRoot(mv);

            }
        });

        view.getImgSave().setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                view.getImgSave().setScaleX(1.15);
                view.getImgSave().setScaleY(1.15);
                view.setCursor(Cursor.HAND);
            }
        });

        view.getImgSave().setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                view.getImgSave().setScaleX(1);
                view.getImgSave().setScaleY(1);
                view.setCursor(Cursor.DEFAULT);
            }
        });
    }

}

package blockgame.view.gameover;

import blockgame.model.Game;
import blockgame.view.mainMenu.MainMenuPresenter;
import blockgame.view.mainMenu.MainMenuView;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

/**
 * Alexie Chaerle
 * 12/03/2021
 */
public class GameOverPresenter {
    private Game model;
    private GameOverView view;

    public GameOverPresenter(Game model, GameOverView view) {
        this.model = model;
        this.view = view;
        addEventHandlers();
        updateView();
    }

    private void addEventHandlers() {
        view.getImgSave().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                if (model.getScoreboard().getScore() == model.getPlayer().getHighscore() && model.getPlayablePieces().isDifficulty()) {
                    model.getAm().save();
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
        }


    }

}

package blockgame.view.settings;

import blockgame.model.Game;
import blockgame.view.about.AboutAlert;
import blockgame.view.game.GamePresenter;
import blockgame.view.game.GameView;
import blockgame.view.highscores.HighscoresPresenter;
import blockgame.view.highscores.HighscoresView;

/**
 * @author Maxim Derboven
 * @version 1.0 17/02/2021 13:18
 */
public class SettingsPresenter {
    private Game model;
    private SettingsView view;

    public SettingsPresenter(Game model, SettingsView view) {
        this.model = model;
        this.view = view;
        addEventHandlers();
        updateView();
    }

    private void updateView() {
        //fill view with data from model
    }

    private void addEventHandlers() {
        view.getMv().getLblAbout().setOnMouseClicked(mouseEvent -> {
            AboutAlert aboutAlert = new AboutAlert();
            aboutAlert.showAndWait();
        });
        view.getMv().getLblGame().setOnMouseClicked(mouseEvent -> {
            GameView gameView = new GameView();
            GamePresenter gamePresenter = new GamePresenter(model, gameView);
            view.getScene().setRoot(gameView);
            gameView.getScene().getWindow().sizeToScene();
        });
        view.getMv().getLblHighscores().setOnMouseClicked(mouseEvent -> {
            HighscoresView highscoresView = new HighscoresView();
            HighscoresPresenter highscoresPresenter = new HighscoresPresenter(model, highscoresView);
            view.getScene().setRoot(highscoresView);
            highscoresView.getScene().getWindow().sizeToScene();
        });
    }

}

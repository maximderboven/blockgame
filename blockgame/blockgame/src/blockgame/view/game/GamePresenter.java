package blockgame.view.game;

import blockgame.model.Game;
import blockgame.view.about.AboutAlert;
import blockgame.view.highscores.HighscoresPresenter;
import blockgame.view.highscores.HighscoresView;
import blockgame.view.settings.SettingsPresenter;
import blockgame.view.settings.SettingsView;

/**
 * Alexie Chaerle
 * 26/02/2021
 */
public class GamePresenter {
    private Game model;
    private GameView view;

    public GamePresenter(Game model, GameView view) {
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
        view.getMv().getLblHighscores().setOnMouseClicked(mouseEvent -> {
            HighscoresView highscoresView = new HighscoresView();
            HighscoresPresenter highscoresPresenter = new HighscoresPresenter(model, highscoresView);
            view.getScene().setRoot(highscoresView);
            highscoresView.getScene().getWindow().sizeToScene();
        });
        view.getMv().getLblSettings().setOnMouseClicked(mouseEvent -> {
            SettingsView settingsView = new SettingsView();
            SettingsPresenter settingsPresenter = new SettingsPresenter(model, settingsView);
            view.getScene().setRoot(settingsView);
            settingsView.getScene().getWindow().sizeToScene();
        });
    }
}

package blockgame.view.highscores;

import blockgame.model.Game;
import blockgame.view.about.AboutAlert;
import blockgame.view.game.GamePresenter;
import blockgame.view.game.GameView;
import blockgame.view.menu.MenuView;
import blockgame.view.settings.SettingsPresenter;
import blockgame.view.settings.SettingsView;

/**
 * @author Maxim Derboven
 * @version 1.0 7/03/2021 16:35
 */
public class HighscoresPresenter {
    private Game model;
    private HighscoresView view;


    public HighscoresPresenter(Game model, HighscoresView view) {
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
        view.getMv().getLblSettings().setOnMouseClicked(mouseEvent -> {
            SettingsView settingsView = new SettingsView();
            SettingsPresenter settingsPresenter = new SettingsPresenter(model, settingsView);
            view.getScene().setRoot(settingsView);
            settingsView.getScene().getWindow().sizeToScene();
        });
    }

}

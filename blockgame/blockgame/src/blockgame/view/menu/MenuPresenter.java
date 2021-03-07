package blockgame.view.menu;

import blockgame.model.Game;
import blockgame.view.about.AboutAlert;
import blockgame.view.game.GamePresenter;
import blockgame.view.game.GameView;
import blockgame.view.highscores.HighscoresPresenter;
import blockgame.view.highscores.HighscoresView;
import blockgame.view.settings.SettingsPresenter;
import blockgame.view.settings.SettingsView;

/**
 * @author Maxim Derboven
 * @version 1.0 7/03/2021 16:30
 */
public class MenuPresenter {
    private Game model;
    private MenuView view;

    public MenuPresenter(Game model, MenuView view) {
        this.model = model;
        this.view = view;
        addEventHandlers();
        updateView();
    }

    private void updateView() {
        //fill view with data from model
    }

    private void addEventHandlers() {
        view.getLblAbout().setOnMouseClicked(mouseEvent -> {
            AboutAlert aboutAlert = new AboutAlert();
        });
        view.getLblGame().setOnMouseClicked(mouseEvent -> {
            System.out.println("test");
            GameView gameView = new GameView();
            GamePresenter gameRegister = new GamePresenter(model, gameView);
            view.getScene().setRoot(gameView);
            gameView.getScene().getWindow().sizeToScene();
        });
        view.getLblHighscores().setOnMouseClicked(mouseEvent -> {
            HighscoresView highscoresView = new HighscoresView();
            HighscoresPresenter highscoresPresenter = new HighscoresPresenter(model, highscoresView);
            view.getScene().setRoot(highscoresView);
            highscoresView.getScene().getWindow().sizeToScene();
        });
        view.getLblSettings().setOnMouseClicked(mouseEvent -> {
            SettingsView settingsView = new SettingsView();
            //SettingsPresenter settingsPresenter = new SettingsPresenter(model, settingsView);
            view.getScene().setRoot(settingsView);
            settingsView.getScene().getWindow().sizeToScene();
        });
    }
}

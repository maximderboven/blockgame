package blockgame.view.highscores;

import blockgame.model.Game;
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
        //forward events to calls in model
    }

}

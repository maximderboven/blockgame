package blockgame.view.settingsScreen;

import blockgame.model.Game;

/**
 * @author Maxim Derboven
 * @version 1.0 17/02/2021 13:18
 */
public class SettingsPresenter {
    private Game model;
    private SettingsView view;
    public SettingsPresenter(Game model,SettingsView view) {
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

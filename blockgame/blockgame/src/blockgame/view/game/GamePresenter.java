package blockgame.view.game;

import blockgame.model.Game;

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
        //forward events to calls in model
    }
}

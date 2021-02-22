package blockgame.view.menuBarScreen;

import blockgame.model.Game;

/**
 * Alexie Chaerle
 * 22/02/2021
 */
public class MenuBarPresenter {
    private Game model;
    private MenuBarView view;

    public MenuBarPresenter(Game model, MenuBarView view) {
        this.model = model;
        this.view = view;
        this.addEventHandlers();
        this.updateView();
    }

    private void addEventHandlers() {

    }

    private void updateView() {

    }
}

package blockgame.view.identificationScreen;

import blockgame.model.Game;
import blockgame.view.aboutScreen.AboutAlert;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * Alexie Chaerle
 * 22/02/2021
 */
public class LoginPresenter {
    private Game model;
    private LoginView view;

    public LoginPresenter(Game model, LoginView view) {
        this.model = model;
        this.view = view;
        this.addEventHandlers();
        this.updateView();
    }

    public void addEventHandlers() {
    }

    private void updateView() {

    }
}

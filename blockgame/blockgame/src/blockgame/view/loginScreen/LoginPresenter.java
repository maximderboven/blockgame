package blockgame.view.loginScreen;

import blockgame.model.Game;
import blockgame.view.registerScreen.RegisterView;

/**
 * Alexie Chaerle
 * 22/02/2021
 */
public class LoginPresenter extends RegisterView {
    private Game model;
    private LoginView view;

    public LoginPresenter(Game model, LoginView view) {
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
package blockgame.view.registerScreen;

import blockgame.model.Game;
import blockgame.view.loginScreen.LoginPresenter;
import blockgame.view.loginScreen.LoginView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * Alexie Chaerle
 * 17/02/2021
 */
public class RegisterPresenter {
    private Game model;
    private RegisterView view;

    public RegisterPresenter(Game model, RegisterView view) {
        this.model = model;
        this.view = view;
        this.addEventHandlers();
        this.updateView();
    }

    private void addEventHandlers() {
        view.getLoginButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                LoginView logView = new LoginView();
                LoginPresenter regPresenter = new LoginPresenter(model, logView);
                view.getScene().setRoot(logView);
            }
        });
    }

    private void updateView() {

    }

}

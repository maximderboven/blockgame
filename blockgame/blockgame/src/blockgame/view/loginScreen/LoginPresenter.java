package blockgame.view.loginScreen;

import blockgame.model.Game;
import blockgame.view.aboutScreen.AboutAlert;
import blockgame.view.registerScreen.RegisterPresenter;
import blockgame.view.registerScreen.RegisterView;
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
        // Event voor wanneer de register button wordt gedrukt
        view.getRegisterButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                RegisterView regView = new RegisterView();
                RegisterPresenter regPresenter = new RegisterPresenter(model, regView);
                view.getScene().setRoot(regView);
                regView.getScene().getWindow().sizeToScene();
            }
        });

        // Event voor about
        view.getAbout().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                AboutAlert al = new AboutAlert();
                al.showAndWait();
            }
        });
    }

    private void updateView() {

    }
}

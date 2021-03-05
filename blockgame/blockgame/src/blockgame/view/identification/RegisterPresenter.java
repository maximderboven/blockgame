package blockgame.view.identification;

import blockgame.model.Game;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

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
        view.getLblRedirect().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                LoginView loginView = new LoginView();
                LoginPresenter registerPresenter = new LoginPresenter(model, loginView);
                view.getScene().setRoot(loginView);
                loginView.getScene().getWindow().sizeToScene();
            }
        });
    }

    private void updateView() {

    }

}

package blockgame.view.identification;

import blockgame.model.Game;
import blockgame.view.game.GamePresenter;
import blockgame.view.game.GameView;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

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
        // Om te gaan naar registerview
        view.getLblRedirect().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                RegisterView registerView = new RegisterView();
                RegisterPresenter registerPresenter = new RegisterPresenter(model, registerView);
                view.getScene().setRoot(registerView);
                registerView.getScene().getWindow().sizeToScene();
            }
        });

        view.getBtnId().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    model.login(view.getTxtUsername().getText(),view.getTxtPassword().getText());
                    GameView gameView = new GameView();
                    GamePresenter gameRegister = new GamePresenter(model,gameView);
                    view.getScene().setRoot(gameView);
                    gameView.getScene().getWindow().sizeToScene();
                } catch (Exception e) {
                    view.setLblError(e.getMessage());
                }
            }
        });
    }

    private void updateView() {

    }
}

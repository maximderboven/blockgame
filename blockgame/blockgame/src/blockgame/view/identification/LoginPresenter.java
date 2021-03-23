package blockgame.view.identification;

import blockgame.model.Game;
import blockgame.view.game.GamePresenter;
import blockgame.view.game.GameView;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.MediaPlayer;

/**
 * Alexie Chaerle
 * 22/02/2021
 */
public class LoginPresenter extends AuthorizationPresenter {

    /**
     * Constructor voor de Settings
     * Sounds, (window)Eventhandlers & updaten van de view.
     */
    public LoginPresenter(Game model, LoginView view) {
        super(model, view);
        super.addEventHandlers();
        super.updateView();
    }

    public void addEventHandlers() {

        /* Eventhandler wanneer gebruiker op de login button klikt */
        view.getImgId().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    if (model.isMusic()) new MediaPlayer(clicksound).play();
                    model.login(view.getTxtUsername().getText(), view.getTxtPassword().getText());
                    GameView gameView = new GameView(model.getBoard().getSize());
                    view.getScene().setRoot(gameView);
                    new GamePresenter(model, gameView);
                } catch (Exception e) {
                    view.setLblError(e.getMessage());
                }
            }
        });

        /* Redirect naar RegisterView */
        view.getLblRedirect().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                RegisterView registerView = new RegisterView();
                view.getScene().setRoot(registerView);
                new RegisterPresenter(model, registerView);
            }
        });
    }

    public void updateView() {
        // ...
    }
}

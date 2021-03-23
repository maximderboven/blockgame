package blockgame.view.identification;

import blockgame.model.Game;
import blockgame.view.game.GamePresenter;
import blockgame.view.game.GameView;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.MediaPlayer;


/**
 * Alexie Chaerle
 * 17/02/2021
 */
public class RegisterPresenter extends AuthorizationPresenter {

    /**
     * Constructor voor de Settings
     * Sounds, (window)Eventhandlers & updaten van de view.
     */
    public RegisterPresenter(Game model, RegisterView view) {
        super(model, view);
        super.addEventHandlers();
        super.updateView();
    }

    public void addEventHandlers() {

        /* Eventhandler voor register button */
        view.getImgId().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (model.isMusic()) new MediaPlayer(clicksound).play();
                try {
                    model.register(view.getTxtUsername().getText(), view.getTxtPassword().getText());
                    GameView gameView = new GameView(model.getBoard().getSize());
                    view.getScene().setRoot(gameView);
                    new GamePresenter(model, gameView);
                } catch (Exception ioe) {
                    view.setLblError(ioe.getMessage());
                }
            }
        });

        /* OnMouseClick om terug naar Login te gaan */
        view.getLblRedirect().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                LoginView lv = new LoginView();
                view.getScene().setRoot(lv);
                new LoginPresenter(model, lv);
            }
        });

    }

    public void updateView() {
        // ...
    }

}

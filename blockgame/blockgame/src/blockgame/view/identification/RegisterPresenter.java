package blockgame.view.identification;

import blockgame.model.Game;
import blockgame.view.game.GamePresenter;
import blockgame.view.game.GameView;
import blockgame.view.mainMenu.MainMenuPresenter;
import blockgame.view.mainMenu.MainMenuView;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Alexie Chaerle
 * 17/02/2021
 */
public class RegisterPresenter {

    /**
     * Attributen
     */
    private Game model;
    private RegisterView view;
    private Media clicksound;
    private Path soundPath = Paths.get("blockgame" + File.separator + "resources" + File.separator + "sounds" + File.separator + "click.mp3");


    /**
     * Constructor voor de Settings
     * Sounds, (window)Eventhandlers & updaten van de view.
     */
    public RegisterPresenter(Game model, RegisterView view) {
        this.model = model;
        this.view = view;
        this.clicksound = new Media(new File(soundPath.toString()).toURI().toString());
        this.addEventHandlers();
        this.updateView();
    }

    private void addEventHandlers() {

        /* Wanneer de gebruiker op close button klikt -> ga naar MainMenu */
        view.getImgClose().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (model.isMusic()) new MediaPlayer(clicksound).play();
                MainMenuView mmv = new MainMenuView();
                view.getScene().setRoot(mmv);
                new MainMenuPresenter(model, mmv);
            }
        });

        /* Hover effect close button */
        view.getImgClose().setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                view.getImgClose().setCursor(Cursor.HAND);
            }
        });

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

        /* Hover effect voor RegisterButton */
        view.getImgId().setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                view.getImgId().setScaleX(1.15);
                view.getImgId().setScaleY(1.15);
                view.getImgId().setCursor(Cursor.HAND);
            }
        });

        /* Hover effect verwijderen voor RegisterButton */
        view.getImgId().setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                view.getImgId().setCursor(Cursor.DEFAULT);
                view.getImgId().setScaleX(1);
                view.getImgId().setScaleY(1);
            }
        });

    }

    private void updateView() {
        // ...
    }

}

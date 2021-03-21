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
 * 22/02/2021
 */
public class LoginPresenter {
    private Game model;
    private LoginView view;
    private Media clicksound;
    private Path soundPath = Paths.get("blockgame" + File.separator + "resources" + File.separator + "sounds" + File.separator + "click.mp3");

    public LoginPresenter(Game model, LoginView view) {
        this.model = model;
        this.view = view;
        this.clicksound = new Media(new File(soundPath.toString()).toURI().toString());
        this.addEventHandlers();
        this.updateView();
    }

    public void addEventHandlers() {

        // Redirect naar RegisterView
        view.getLblRedirect().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                RegisterView registerView = new RegisterView();
                view.getScene().setRoot(registerView);
                new RegisterPresenter(model, registerView);
            }
        });

        // Login "button"
        view.getImgId().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    if (model.isMusic()) {
                        new MediaPlayer(clicksound).play();
                    }
                    model.login(view.getTxtUsername().getText(), view.getTxtPassword().getText());

                    GameView gameView = new GameView(model.getBoard().getSize());
                    view.getScene().setRoot(gameView);
                    new GamePresenter(model, gameView);
                } catch (Exception e) {
                    view.setLblError(e.getMessage());
                }
            }
        });

        view.getImgId().setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                view.getImgId().setScaleX(1.15);
                view.getImgId().setScaleY(1.15);
                view.getImgId().setCursor(Cursor.HAND);
            }
        });

        view.getImgId().setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                view.getImgId().setCursor(Cursor.DEFAULT);
                view.getImgId().setScaleX(1);
                view.getImgId().setScaleY(1);
            }
        });

        view.getImgClose().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (model.isMusic()) {
                    new MediaPlayer(clicksound).play();
                }
                MainMenuView mv = new MainMenuView();
                view.getScene().setRoot(mv);
                new MainMenuPresenter(model, mv);
            }
        });

        view.getImgClose().setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                view.getImgClose().setCursor(Cursor.HAND);
            }
        });
    }

    private void updateView() {

    }
}

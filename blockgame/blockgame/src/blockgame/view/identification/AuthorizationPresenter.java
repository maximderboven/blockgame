package blockgame.view.identification;

import blockgame.model.Game;
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
 * 23/03/2021
 */
public abstract class AuthorizationPresenter {

    Game model;
    AuthorizationView view;
    Media clicksound;
    Path soundPath = Paths.get("blockgame" + File.separator + "resources" + File.separator + "sounds" + File.separator + "click.mp3");

    public AuthorizationPresenter(Game model, AuthorizationView view) {
        this.model = model;
        this.view = view;
        this.clicksound = new Media(new File(soundPath.toString()).toURI().toString());
        this.addEventHandlers();
        this.updateView();
    }

    void addEventHandlers() {

        /* Eventhandler wanneer de gebruiker op het kruisje (button) klikt. */
        view.getImgClose().setOnMouseClicked(mouseEvent -> {
            if (model.isMusic()) new MediaPlayer(clicksound).play();
            /* Terug naar MainMenu gaan */
            MainMenuView mv = new MainMenuView();
            view.getScene().setRoot(mv);
            new MainMenuPresenter(model, mv);
        });

    }

    void updateView() {

        /* Hover effect voor de Login/Register Button */
        view.getImgId().setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                view.getImgId().setScaleX(1.15);
                view.getImgId().setScaleY(1.15);
                view.getImgId().setCursor(Cursor.HAND);
            }
        });

        /* Hover effect voor de Login/Register button verwijderen */
        view.getImgId().setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                view.getImgId().setCursor(Cursor.DEFAULT);
                view.getImgId().setScaleX(1);
                view.getImgId().setScaleY(1);
            }
        });

        /* Hover effect voor kruisje (button) */
        view.getImgClose().setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                view.getImgClose().setCursor(Cursor.HAND);
            }
        });

    }

}

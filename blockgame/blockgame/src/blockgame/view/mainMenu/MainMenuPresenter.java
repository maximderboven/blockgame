package blockgame.view.mainMenu;

import blockgame.model.Game;
import blockgame.view.about.AboutAlert;
import blockgame.view.game.GamePresenter;
import blockgame.view.game.GameView;
import blockgame.view.highscores.HighscoresPresenter;
import blockgame.view.highscores.HighscoresView;
import blockgame.view.identification.LoginPresenter;
import blockgame.view.identification.LoginView;
import blockgame.view.settings.SettingsPresenter;
import blockgame.view.settings.SettingsView;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Alexie Chaerle
 * 9/03/2021
 */
public class MainMenuPresenter {

    private Game model;
    private MainMenuView view;

    public MainMenuPresenter(Game model, MainMenuView view) {
        this.model = model;
        this.view = view;
        addEventHandlers();
        updateView();
    }

    private void addEventHandlers() {

        /* Play */
        view.getImgPlay().setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                view.getImgPlay().setCursor(Cursor.HAND);
                view.getImgPlay().setScaleX(1.15);
                view.getImgPlay().setScaleY(1.15);
            }
        });

        view.getImgPlay().setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                view.getImgPlay().setScaleX(1);
                view.getImgPlay().setScaleY(1);
            }
        });

        view.getImgPlay().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                LoginView lv = new LoginView();
                LoginPresenter lp = new LoginPresenter(model, lv);
                view.getScene().setRoot(lv);
            }
        });

        /* Highscores */
        view.getImgHighscores().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                HighscoresView hv = new HighscoresView();
                HighscoresPresenter hp = new HighscoresPresenter(model, hv);
                view.getScene().getStylesheets().add("/stylesheets/chart.css");
                view.getScene().setRoot(hv);
            }
        });

        view.getImgHighscores().setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                view.getImgHighscores().setCursor(Cursor.HAND);
                view.getImgHighscores().setScaleX(1.15);
                view.getImgHighscores().setScaleY(1.15);
            }
        });

        view.getImgHighscores().setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                view.getImgHighscores().setScaleX(1);
                view.getImgHighscores().setScaleY(1);
            }
        });

        /* Settings */
        view.getImgSettings().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                SettingsView sv = new SettingsView();
                SettingsPresenter sp = new SettingsPresenter(model, sv);
                view.getScene().setRoot(sv);
            }
        });

        view.getImgSettings().setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                view.getImgSettings().setCursor(Cursor.HAND);
                view.getImgSettings().setScaleX(1.15);
                view.getImgSettings().setScaleY(1.15);
            }
        });

        view.getImgSettings().setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                view.getImgSettings().setScaleX(1);
                view.getImgSettings().setScaleY(1);
            }
        });

        /* About */
        view.getImgAbout().setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                view.getImgAbout().setCursor(Cursor.HAND);
                view.getImgAbout().setScaleX(1.15);
                view.getImgAbout().setScaleY(1.15);
            }
        });

        view.getImgAbout().setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                view.getImgAbout().setScaleX(1);
                view.getImgAbout().setScaleY(1);
            }
        });

        view.getImgAbout().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                AboutAlert alert = new AboutAlert();
                alert.setResizable(false);
                alert.setGraphic(new ImageView(this.getClass().getResource("/images/logo.png").toString()));
                alert.showAndWait();
            }
        });

    }

    private void updateView() {

    }


}

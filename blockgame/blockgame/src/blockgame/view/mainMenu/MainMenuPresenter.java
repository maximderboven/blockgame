package blockgame.view.mainMenu;

import blockgame.model.Game;
import blockgame.view.about.AboutAlert;
import blockgame.view.highscores.HighscoresPresenter;
import blockgame.view.highscores.HighscoresView;
import blockgame.view.identification.LoginPresenter;
import blockgame.view.identification.LoginView;
import blockgame.view.settings.SettingsPresenter;
import blockgame.view.settings.SettingsView;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
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
    private Media clicksound;
    private Path soundPath = Paths.get("blockgame" + File.separator + "resources" + File.separator + "sounds" + File.separator + "click.mp3");

    public MainMenuPresenter(Game model, MainMenuView view) {
        this.model = model;
        this.view = view;
        this.clicksound = new Media(new File(soundPath.toString()).toURI().toString());
        addEventHandlers();
        updateView();
    }

    private void addEventHandlers() {

        // Play "button"
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
                if (model.isMusic()) {
                    new MediaPlayer(clicksound).play();
                }
                LoginView lv = new LoginView();
                new LoginPresenter(model, lv);
                view.getScene().setRoot(lv);
            }
        });

        // Highscores "button"
        view.getImgHighscores().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (model.isMusic()) {
                    new MediaPlayer(clicksound).play();
                }
                HighscoresView hv = new HighscoresView();
                new HighscoresPresenter(model, hv);
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

        // Settings "button"
        view.getImgSettings().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (model.isMusic()) {
                    new MediaPlayer(clicksound).play();
                }
                SettingsView sv = new SettingsView();
                new SettingsPresenter(model, sv);
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

        // About "button"
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
                if (model.isMusic()) {
                    new MediaPlayer(clicksound).play();
                }
                AboutAlert alert = new AboutAlert();
                alert.setResizable(false);
                alert.setGraphic(new ImageView(this.getClass().getResource("/images/logo.png").toString()));
                alert.showAndWait();
            }
        });

    }

    private void updateView() {
        // ...
    }

}

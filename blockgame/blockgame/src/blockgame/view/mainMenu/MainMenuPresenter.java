package blockgame.view.mainMenu;

import blockgame.model.Game;
import blockgame.view.about.AboutAlert;
import blockgame.view.highscores.HighscoresPresenter;
import blockgame.view.highscores.HighscoresView;
import blockgame.view.identification.LoginPresenter;
import blockgame.view.identification.LoginView;
import blockgame.view.settings.SettingsPresenter;
import blockgame.view.settings.SettingsView;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

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
        addWindowEventHandlers();
    }

    public void addWindowEventHandlers() {
        view.getScene().getWindow().setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                Platform.exit();
            }
        });
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
                view.getScene().setRoot(lv);
                new LoginPresenter(model, lv);
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
                view.getScene().getStylesheets().add("/stylesheets/chart.css");
                view.getScene().setRoot(hv);
                new HighscoresPresenter(model, hv);
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
                view.getScene().setRoot(sv);
                new SettingsPresenter(model, sv);
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

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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.WindowEvent;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Alexie Chaerle
 * 9/03/2021
 */
public class MainMenuPresenter {

    /**
     * Attributen
     */
    private Game model;
    private MainMenuView view;
    private Media clicksound;
    private Path soundPath = Paths.get("blockgame" + File.separator + "resources" + File.separator + "sounds" + File.separator + "click.mp3");

    /**
     * Constructor voor de MainMenu
     * Sounds, (window)Eventhandlers & updaten van de view.
     */
    public MainMenuPresenter(Game model, MainMenuView view) {
        this.model = model;
        this.view = view;
        this.clicksound = new Media(new File(soundPath.toString()).toURI().toString());
        addEventHandlers();
        updateView();
        addWindowEventHandlers();
    }

    /* Wanneer de gebruiker op "X" klikt van de window sluit het programma zich af. */
    public void addWindowEventHandlers() {
        view.getScene().getWindow().setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                Platform.exit();
            }
        });
    }

    /* Eventhandlers */
    private void addEventHandlers() {

        /* Hover effect voor de "Play" button */
        view.getImgPlay().setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                view.getImgPlay().setCursor(Cursor.HAND);
                view.getImgPlay().setScaleX(1.15);
                view.getImgPlay().setScaleY(1.15);
            }
        });

        /* Hover effect verwijderen voor de "Play" button on mouse exit */
        view.getImgPlay().setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                view.getImgPlay().setScaleX(1);
                view.getImgPlay().setScaleY(1);
            }
        });

        /* Wanneer de gebruiker op de play button klikt */
        view.getImgPlay().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (model.isMusic()) new MediaPlayer(clicksound).play();
                /* Naar login scherm gaan */
                LoginView lv = new LoginView();
                view.getScene().setRoot(lv);
                new LoginPresenter(model, lv);
            }
        });

        /* Wanneer de gebruiker op de highscores button klikt */
        view.getImgHighscores().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (model.isMusic()) new MediaPlayer(clicksound).play();
                /* Naar highscores scherm gaan */
                HighscoresView hv = new HighscoresView();
                view.getScene().getStylesheets().add("/stylesheets/chart.css");
                view.getScene().setRoot(hv);
                new HighscoresPresenter(model, hv);
            }
        });

        /* Hover effect voor de "highscores" button */
        view.getImgHighscores().setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                view.getImgHighscores().setCursor(Cursor.HAND);
                view.getImgHighscores().setScaleX(1.15);
                view.getImgHighscores().setScaleY(1.15);
            }
        });

        /* Hover effect verwijderen voor de "highscores" button on mouse exit */
        view.getImgHighscores().setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                view.getImgHighscores().setScaleX(1);
                view.getImgHighscores().setScaleY(1);
            }
        });

        /* Wanneer de gebruiker op de "Settings" button klikt */
        view.getImgSettings().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (model.isMusic()) new MediaPlayer(clicksound).play();
                /* Naar de settings gaan */
                SettingsView sv = new SettingsView();
                view.getScene().setRoot(sv);
                new SettingsPresenter(model, sv);
            }
        });

        /* Hover effect voor "Settings" */
        view.getImgSettings().setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                view.getImgSettings().setCursor(Cursor.HAND);
                view.getImgSettings().setScaleX(1.15);
                view.getImgSettings().setScaleY(1.15);
            }
        });

        /* Hover effect verwijderen voor "Settings" */
        view.getImgSettings().setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                view.getImgSettings().setScaleX(1);
                view.getImgSettings().setScaleY(1);
            }
        });

        /* Hover effect voor "About" */
        view.getImgAbout().setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                view.getImgAbout().setCursor(Cursor.HAND);
                view.getImgAbout().setScaleX(1.15);
                view.getImgAbout().setScaleY(1.15);
            }
        });

        /* Hover effect verwijderen voor "About" */
        view.getImgAbout().setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                view.getImgAbout().setScaleX(1);
                view.getImgAbout().setScaleY(1);
            }
        });

        /* Eventhandler wanneer de gebruiker op de "About" button klikt */
        view.getImgAbout().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (model.isMusic()) new MediaPlayer(clicksound).play();
                /* Nieuwe AboutAlert aanmaken */
                AboutAlert alert = new AboutAlert();
                alert.setResizable(false);
                alert.setGraphic(new ImageView(this.getClass().getResource("/images/logo.png").toString()));
                alert.showAndWait();
            }
        });

    }

    private void updateView() {
        /* De presenter moet geen wijzigingen in de view zelf teweeg brengen (wat de gebruiker kan zien tenminste). */
    }

}

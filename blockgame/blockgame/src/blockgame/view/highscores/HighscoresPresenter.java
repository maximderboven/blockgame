package blockgame.view.highscores;

import blockgame.model.Game;
import blockgame.view.mainMenu.MainMenuPresenter;
import blockgame.view.mainMenu.MainMenuView;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.chart.XYChart;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Maxim Derboven
 * @version 1.0 7/03/2021 16:35
 */
public class HighscoresPresenter {

    /**
     * Attributen
     */
    private Game model;
    private HighscoresView view;
    private Media clicksound;
    private Path soundPath = Paths.get("blockgame" + File.separator + "resources" + File.separator + "sounds" + File.separator + "click.mp3");


    /**
     * Constructor voor Het Game scherm
     * Sounds, (window)Eventhandlers & updaten van de view.
     */
    public HighscoresPresenter(Game model, HighscoresView view) {
        this.model = model;
        this.view = view;
        this.clicksound = new Media(new File(soundPath.toString()).toURI().toString());
        addEventHandlers();
        updateView();
    }

    private void updateView() {
        /* Barchart aanvullen in HighscoresView */
        String[] cutted; /* Lege 1D array maken */
        for (String row : model.getAm().getLeaderboard()) { /* Itereren door de ArrayList highscores */
            cutted = row.split(";"); /* Regex split op ; */
            view.getInfo().getData().add(new XYChart.Data<>(cutted[0], Integer.parseInt(cutted[1]))); /* Username en score in XYChart toevoegen */
        }
        view.getChart().getData().add(view.getInfo()); /* De XYChart koppelen aan de Barchart */
    }

    private void addEventHandlers() {

        /* Hover effect voor close button */
        view.getImgClose().setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                view.getImgClose().setCursor(Cursor.HAND);
            }
        });

        /* Close button event handler */
        view.getImgClose().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (model.isMusic()) new MediaPlayer(clicksound).play();
                /* Terug naar MainMenu */
                MainMenuView mv = new MainMenuView();
                view.getScene().setRoot(mv);
                new MainMenuPresenter(model, mv);
            }
        });
    }

}

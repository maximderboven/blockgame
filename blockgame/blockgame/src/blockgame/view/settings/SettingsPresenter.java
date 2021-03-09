package blockgame.view.settings;

import blockgame.model.Game;
import blockgame.view.about.AboutAlert;
import blockgame.view.game.GamePresenter;
import blockgame.view.game.GameView;
import blockgame.view.highscores.HighscoresPresenter;
import blockgame.view.highscores.HighscoresView;
import blockgame.model.Game;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 * @author Maxim Derboven
 * @version 1.0 17/02/2021 13:18
 */
public class SettingsPresenter {
    private Game model;
    private SettingsView view;

    public SettingsPresenter(Game model, SettingsView view) {
        this.model = model;
        this.view = view;
        addEventHandlers();
        updateView();
    }

    private void updateView() {
        //fill view with data from model
    }

    private void addEventHandlers() {
        view.getTfPlayablePieces().setText(String.valueOf(model.getPlayablePieces().getCapacity()));

        view.getBtnSave().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                /* Board size */
                model.getBoard().setSize((int) view.getSlSize().getValue());
                System.out.println(model.getBoard().getSize());

                /* Difficulty button */
                model.getPlayablePieces().setDifficulty(view.getChkDifficulty().isSelected());
                System.out.println(model.getPlayablePieces().isDifficulty());

                /* Playable pieces */
                model.getPlayablePieces().setCapacity(Integer.parseInt(view.getTfPlayablePieces().getText()));
                System.out.println(model.getPlayablePieces().getPieces());

            }
        });

        /* Slider label dynamisch veranderen */
        view.getSlSize().setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                view.getBoardSizeSliderLabel().setText(String.format("Size: %.0fx%.0f", view.getSlSize().getValue(), view.getSlSize().getValue()));
            }
        });

        view.getMv().getLblAbout().setOnMouseClicked(mouseEvent -> {
            AboutAlert aboutAlert = new AboutAlert();
            aboutAlert.showAndWait();
        });

        view.getMv().getLblGame().setOnMouseClicked(mouseEvent -> {
            GameView gameView = new GameView();
            GamePresenter gamePresenter = new GamePresenter(model, gameView);
            view.getScene().setRoot(gameView);
            gameView.getScene().getWindow().sizeToScene();
        });

        view.getMv().getLblHighscores().setOnMouseClicked(mouseEvent -> {
            HighscoresView highscoresView = new HighscoresView();
            HighscoresPresenter highscoresPresenter = new HighscoresPresenter(model, highscoresView);
            view.getScene().setRoot(highscoresView);
            highscoresView.getScene().getWindow().sizeToScene();
        });

    }

}

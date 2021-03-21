package blockgame.view.settings;

import blockgame.model.Game;
import blockgame.view.mainMenu.MainMenuPresenter;
import blockgame.view.mainMenu.MainMenuView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Maxim Derboven
 * @version 1.0 17/02/2021 13:18
 */
public class SettingsPresenter {
    private Game model;
    private SettingsView view;
    private Media clicksound;
    private Path soundPath = Paths.get("blockgame" + File.separator + "resources" + File.separator + "sounds" + File.separator + "click.mp3");


    public SettingsPresenter(Game model, SettingsView view) {
        this.model = model;
        this.view = view;
        this.clicksound = new Media(new File(soundPath.toString()).toURI().toString());
        addEventHandlers();
        updateView();
    }

    private void updateView() {

        /* Alvorens de settingsview wordt getoond, de waarden van het huidig spel instellen in de view. */
        view.getSlSize().setValue(model.getBoard().getSize());
        view.getBoardSizeSliderLabel().setText(String.format("Size: %dx%d", model.getBoard().getSize(), model.getBoard().getSize()));

        /* Waarden instellen van de difficulty */
        view.getChkDifficulty().setSelected(model.getPlayablePieces().isDifficulty());

        /* Waarden instellen van de Drag and drop */
        if (model.getBoard().isDraganddrop()) {
            view.getRbDrag().setSelected(true);
        } else {
            view.getRbClick().setSelected(true);
        }

        /* Waarden instellen van de playable pieces */
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(3, 5, model.getPlayablePieces().getCapacity());
        view.getSpPlayablePieces().setValueFactory(valueFactory);

        /* Waarden instellen van de file location */
        view.getTxtFileLocation().setText(model.getAm().getLocation());

        view.getChkSoundEffects().setSelected(model.isMusic());
    }

    private void addEventHandlers() {

        // Settings opslaan:
        view.getImgSave().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (model.isMusic()) {
                    new MediaPlayer(clicksound).play();
                }

                model.getBoard().setSize((int) view.getSlSize().getValue()); /* Board size */
                model.getPlayablePieces().setDifficulty(view.getChkDifficulty().isSelected()); /* Difficulty button */
                model.getBoard().setDraganddrop(view.getRbDrag().isSelected()); /* Drag and drop */
                model.getPlayablePieces().setCapacity(view.getSpPlayablePieces().getValueFactory().getValue()); /* Playable pieces */

                // Terug naar Main Menu
                MainMenuView mv = new MainMenuView();
                view.getScene().setRoot(mv);
                new MainMenuPresenter(model, mv);

                model.setMusic(view.getChkSoundEffects().isSelected());

            }
        });

        // Slider label dynamisch veranderen
        view.getSlSize().setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                view.getBoardSizeSliderLabel().setText(String.format("Size: %.0fx%.0f", view.getSlSize().getValue(), view.getSlSize().getValue()));
            }
        });
        view.getSlSize().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                view.getBoardSizeSliderLabel().setText(String.format("Size: %.0fx%.0f", view.getSlSize().getValue(), view.getSlSize().getValue()));
            }
        });

        // Button effect (vergroten on hover)
        view.getImgSave().setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                view.getImgSave().setCursor(Cursor.HAND);
                view.getImgSave().setScaleX(1.1);
                view.getImgSave().setScaleY(1.1);
            }
        });
        view.getImgSave().setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                view.getImgSave().setScaleX(1);
                view.getImgSave().setScaleY(1);
            }
        });

        // File location aanpassen van de highscores
        view.getBtnFileLocation().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                FileChooser fc = new FileChooser();
                fc.setTitle("Choose highscores location");
                fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Textfiles", "*.txt"));
                fc.setInitialDirectory(new File("./"));
                File selectedFile = fc.showOpenDialog(view.getScene().getWindow());

                // Kijken of locatie niet NULL is en of het leesbaar/writable is. Anders error alert
                if ((selectedFile != null) && (Files.isReadable(Paths.get(selectedFile.toURI()))) && (Files.isWritable(Paths.get(selectedFile.toURI())))) {
                    model.getAm().setLocation(selectedFile.getAbsolutePath());
                    view.getTxtFileLocation().setText(model.getAm().getLocation());
                } else {
                    Alert errorWindow = new Alert(Alert.AlertType.ERROR);
                    errorWindow.setHeaderText("Problem with selected file!");
                    errorWindow.setContentText("Please try again.");
                    errorWindow.showAndWait();
                }
            }
        });
    }
}

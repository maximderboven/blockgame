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
    public static final char FILE_SEPARATOR = System.getProperties().getProperty("file.separator").charAt(0);
    private Media clicksound;


    public SettingsPresenter(Game model, SettingsView view) {
        this.model = model;
        this.view = view;
        Path soundPath = Paths.get("blockgame" + FILE_SEPARATOR + "resources" + FILE_SEPARATOR + "sounds" + FILE_SEPARATOR + "click.mp3");
        clicksound = new Media(new File(soundPath.toString()).toURI().toString());
        addEventHandlers();
        updateView();
    }

    private void updateView() {

        /* Waarden instellen van de slider */
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
        view.getImgSave().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (model.isMusic()) {
                    new MediaPlayer(clicksound).play();
                }
                /* Board size */
                model.getBoard().setSize((int) view.getSlSize().getValue());
                System.out.println("Board size: " + model.getBoard().getSize());

                /* Difficulty button */
                model.getPlayablePieces().setDifficulty(view.getChkDifficulty().isSelected());
                System.out.println("Difficulty: " + model.getPlayablePieces().isDifficulty());

                /* Drag and drop */
                model.getBoard().setDraganddrop(view.getRbDrag().isSelected());
                System.out.println("Drag and drop: " + view.getRbDrag().isSelected());

                /* Playable pieces */
                model.getPlayablePieces().setCapacity(view.getSpPlayablePieces().getValueFactory().getValue());
                System.out.println("Playable pieces: " + model.getPlayablePieces().getPieces());

                MainMenuView mv = new MainMenuView();
                MainMenuPresenter mp = new MainMenuPresenter(model, mv);
                view.getScene().setRoot(mv);

                model.setMusic(view.getChkSoundEffects().isSelected());

            }
        });

        /* Slider label dynamisch veranderen */
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

        view.getBtnFileLocation().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                // Filechooser
                FileChooser fc = new FileChooser();
                fc.setTitle("Choose highscores location");
                fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Textfiles", "*.txt"));
                fc.setInitialDirectory(new File("./"));
                File selectedFile = fc.showOpenDialog(view.getScene().getWindow());

                if ((selectedFile != null) && (Files.isReadable(Paths.get(selectedFile.toURI())))) {
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

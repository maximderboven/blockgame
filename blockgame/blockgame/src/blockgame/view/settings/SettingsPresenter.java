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

    /**
     * Attributen
     */
    private Game model;
    private SettingsView view;
    private Media clicksound;
    private Path soundPath = Paths.get("blockgame" + File.separator + "resources" + File.separator + "sounds" + File.separator + "click.mp3");

    /**
     * Constructor voor de Settings
     * Sounds, (window)Eventhandlers & updaten van de view.
     */
    public SettingsPresenter(Game model, SettingsView view) {
        this.model = model;
        this.view = view;
        this.clicksound = new Media(new File(soundPath.toString()).toURI().toString());
        addEventHandlers();
        updateView();
    }

    private void updateView() {

        /* ---- Alle settings waarden ophalen in model-klasse en tonen op settingsvenster. ---- */
        /* Slider en slider-label instellen */
        view.getSlSize().setValue(model.getBoard().getSize());
        view.getBoardSizeSliderLabel().setText(String.format("Size: %dx%d", model.getBoard().getSize(), model.getBoard().getSize()));

        /* De difficulty check button instellen */
        view.getChkDifficulty().setSelected(model.getPlayablePieces().isDifficulty());

        /* Drag and Drop Radio button instellen */
        if (model.getBoard().isDraganddrop()) {
            view.getRbDrag().setSelected(true);
        } else {
            view.getRbClick().setSelected(true);
        }

        /* Het aantal playable pieces instellen */
        /* De Spinner van PP heeft een (Spinner)ValueFactory nodig (Default waarden instellen, increment, decrement, ...) */
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(3, 5, model.getPlayablePieces().getCapacity());
        view.getSpPlayablePieces().setValueFactory(valueFactory);

        /* FileLocation path (tekst) instellen */
        view.getTxtFileLocation().setText(model.getAm().getLocation());

        /* Geluidseffecten button instellen */
        view.getChkSoundEffects().setSelected(model.isMusic());
    }

    private void addEventHandlers() {

        /* ---- Eventhandler wanneer de speler op de "save" knop klikt. ---- */
        view.getImgSave().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                /* De waarden van het settingsvenster ophalen en attributen van de klassen wijzigen. */
                if (model.isMusic()) new MediaPlayer(clicksound).play(); /* Geluidseffect bij het klikken op "save" */
                model.getBoard().setSize((int) view.getSlSize().getValue()); /* Board size */
                model.getPlayablePieces().setDifficulty(view.getChkDifficulty().isSelected()); /* Difficulty button */
                model.getBoard().setDraganddrop(view.getRbDrag().isSelected()); /* Drag and drop */
                model.getPlayablePieces().setCapacity(view.getSpPlayablePieces().getValueFactory().getValue()); /* Playable pieces */
                model.setMusic(view.getChkSoundEffects().isSelected()); /* Geluidseffecten van het hele spel */

                /* Terug naar MainMenu gaan */
                MainMenuView mv = new MainMenuView();
                view.getScene().setRoot(mv);
                new MainMenuPresenter(model, mv);

            }
        });

        /* De tekst boven de slider van board size dynamisch laten veranderen */
        /* Bij het SLEPEN van de slider */
        view.getSlSize().setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                view.getBoardSizeSliderLabel().setText(String.format("Size: %.0fx%.0f", view.getSlSize().getValue(), view.getSlSize().getValue()));
            }
        });
        /* Bij het KLIKKEN van de slider */
        view.getSlSize().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                view.getBoardSizeSliderLabel().setText(String.format("Size: %.0fx%.0f", view.getSlSize().getValue(), view.getSlSize().getValue()));
            }
        });

        /* Save button met 10% vergroten on hover + andere cursor instellen */
        view.getImgSave().setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                view.getImgSave().setCursor(Cursor.HAND);
                view.getImgSave().setScaleX(1.1);
                view.getImgSave().setScaleY(1.1);
            }
        });
        /* Save button naar normale grootte instellen wanneer gebruiker met zijn muis weg hovered */
        view.getImgSave().setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                view.getImgSave().setScaleX(1);
                view.getImgSave().setScaleY(1);
            }
        });

        /* FileLocation eventhandler, wanneer men op de button "browse" klikt */
        view.getBtnFileLocation().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                /* Nieuwe FileChooser fc aanmaken, waarden instellen: "Enkel .txt files", "Huidige directory als default bij openen" */
                FileChooser fc = new FileChooser();
                fc.setTitle("Choose highscores location");
                fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Textfiles", "*.txt"));
                fc.setInitialDirectory(new File("./"));
                /* FileChooser openen en opslaan als instantie van de klasse File */
                File selectedFile = fc.showOpenDialog(view.getScene().getWindow());

                /* FileChooser extra check */
                if ((selectedFile != null) && (Files.isReadable(Paths.get(selectedFile.toURI()))) && (Files.isWritable(Paths.get(selectedFile.toURI())))) {
                    /* Als er een txt file werd gekozen EN de gebruiker heeft lees/schrijfrechten op dat bestand: */
                    /* Locatie van de highscores wijzigen in FileManagement klasse en de text aanpassen van de label */
                    model.getAm().setLocation(selectedFile.getAbsolutePath());
                    view.getTxtFileLocation().setText(model.getAm().getLocation());
                } else {
                    /* Indien er geen bestand werd gekozen of de gebruiker heeft geen lees/schrijfrechten op dat bestand: */
                    /* Nieuwe alert maken van het type ERROR */
                    Alert errorWindow = new Alert(Alert.AlertType.ERROR);
                    errorWindow.setHeaderText("Problem with selected file!");
                    errorWindow.setContentText("Please try again.");
                    errorWindow.showAndWait();
                }
            }
        });
    }
}

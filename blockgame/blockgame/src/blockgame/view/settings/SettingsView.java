package blockgame.view.settings;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

import java.io.File;


/**
 * @author Maxim Derboven
 * @version 1.0 17/02/2021 13:17
 */
public class SettingsView extends BorderPane {

    // Attributen
    private Label lblTitel;
    private Label lblBoardSize;
    private Label lblBoardSizeSlider;
    private Label lblDifficulty;
    private Label lblPlayablePieces;
    private Label lblFileLocation;
    private TextField txtFileLocation;
    private Spinner<Integer> spPlayablePieces;
    private Button btnFileLocation;
    private Slider slSize;
    private CheckBox chkDifficulty;
    private Label lblMode;
    private RadioButton rbClick;
    private RadioButton rbDrag;
    private ToggleGroup tgMode;
    private ImageView imgSave;
    private CheckBox chkSoundEffects;
    private Label lblSoundEffects;

    // Constructor
    public SettingsView() {
        initialiseNodes();
        layoutNodes();
    }

    // Initialise nodes
    private void initialiseNodes() {
        lblTitel = new Label("SETTINGS");
        lblBoardSize = new Label("Board size");
        lblBoardSizeSlider = new Label("Size: 5x5 ");
        lblDifficulty = new Label("Difficulty");
        lblPlayablePieces = new Label("Playable Pieces");
        txtFileLocation = new TextField("File location");
        spPlayablePieces = new Spinner<>();
        btnFileLocation = new Button("Browse");
        slSize = new Slider(5, 10, 5);
        chkDifficulty = new CheckBox();
        lblMode = new Label("Mode");
        rbClick = new RadioButton("Click");
        rbDrag = new RadioButton("Drag");
        tgMode = new ToggleGroup();
        rbClick.setToggleGroup(tgMode);
        rbDrag.setToggleGroup(tgMode);
        imgSave = new ImageView(File.separator + "images" + File.separator + "SaveButton.png");
        lblFileLocation = new Label("File location");
        chkSoundEffects = new CheckBox();
        lblSoundEffects = new Label("Sound effects");
    }

    // Layout nodes
    private void layoutNodes() {

        // Extra node declaration
        GridPane grid = new GridPane();
        VBox vb1 = new VBox(lblBoardSizeSlider, slSize);

        // Algemeen
        super.setPadding(new Insets(90));
        super.setCenter(grid);

        // Node settings
        // Value factory, meer info bij SettingsPresenter (line 65)
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(3, 5, 3);
        spPlayablePieces.setValueFactory(valueFactory);
        chkDifficulty.setSelected(true);
        slSize.setBlockIncrement(1);
        slSize.setMajorTickUnit(1);
        slSize.setMinorTickCount(0);
        slSize.setShowTickLabels(true);
        slSize.setSnapToTicks(true);
        lblTitel.setId("title");
        lblBoardSize.setId("label-settings");
        lblBoardSizeSlider.setId("label-settings");
        lblDifficulty.setId("label-settings");
        lblPlayablePieces.setId("label-settings");
        lblMode.setId("label-settings");
        rbDrag.setId("label-settings");
        rbClick.setId("label-settings");
        lblFileLocation.setId("label-settings");
        lblSoundEffects.setId("label-settings");
        spPlayablePieces.setPrefWidth(50);

        // CSS + fonts
        Font.loadFont(getClass().getResourceAsStream("/fonts/Woodtrap.ttf"), 12);
        getStylesheets().add("/stylesheets/settings.css");
        setBackground(new Background(new BackgroundImage(new Image("/images/menu/bgstandard.png"),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT)));

        // Grid settings
        grid.setPadding(new Insets(0, 0, 0, 50));
        grid.setHgap(20);
        grid.setVgap(15);
        grid.setAlignment(Pos.BASELINE_CENTER);
        grid.add(lblTitel, 0, 0, 2, 1);
        grid.add(lblBoardSize, 0, 1);
        grid.add(vb1, 1, 1);
        grid.add(lblDifficulty, 0, 2);
        grid.add(chkDifficulty, 1, 2);
        grid.add(lblMode, 0, 3);
        grid.add(lblPlayablePieces, 0, 4);
        grid.add(spPlayablePieces, 1, 4);
        grid.add(lblFileLocation, 0, 5);
        grid.add(txtFileLocation, 0, 6);
        grid.add(lblSoundEffects, 0, 6);
        grid.add(chkSoundEffects, 1, 6);
        grid.add(imgSave, 0, 8);
        txtFileLocation.setPrefWidth(200);
        txtFileLocation.setDisable(true);
        imgSave.setFitWidth(100);
        imgSave.setFitHeight(50);
        HBox hb1 = new HBox(rbClick, rbDrag);
        rbDrag.setPadding(new Insets(0, 0, 0, 10));
        grid.add(hb1, 1, 3);
        HBox hb2 = new HBox(txtFileLocation, btnFileLocation);
        grid.add(hb2, 1, 5);
    }

    // Getters

    CheckBox getChkDifficulty() {
        return chkDifficulty;
    }

    ImageView getImgSave() {
        return imgSave;
    }

    Label getBoardSizeSliderLabel() {
        return lblBoardSizeSlider;
    }

    Slider getSlSize() {
        return slSize;
    }

    Spinner<Integer> getSpPlayablePieces() {
        return spPlayablePieces;
    }

    RadioButton getRbDrag() {
        return rbDrag;
    }

    RadioButton getRbClick() {
        return rbClick;
    }

    Button getBtnFileLocation() {
        return btnFileLocation;
    }

    TextField getTxtFileLocation() {
        return txtFileLocation;
    }

    CheckBox getChkSoundEffects() {
        return chkSoundEffects;
    }
}
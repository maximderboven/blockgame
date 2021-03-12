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
    private Spinner<Integer> spPlayablePieces;
    private TextField tfFileLocation;
    private Slider slSize;
    private CheckBox chkDifficulty;
    private Label lblMode;
    private RadioButton rbClick;
    private RadioButton rbDrag;
    private ToggleGroup tgMode;
    private ImageView imgSave;


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
        lblFileLocation = new Label("File location");
        spPlayablePieces = new Spinner<>();
        tfFileLocation = new TextField();
        slSize = new Slider(5, 10, 5);
        chkDifficulty = new CheckBox();
        lblMode = new Label("Mode");
        rbClick = new RadioButton("Click");
        rbDrag = new RadioButton("Drag");
        tgMode = new ToggleGroup();
        rbClick.setToggleGroup(tgMode);
        rbDrag.setToggleGroup(tgMode);
        imgSave = new ImageView("/images/SaveButton.png");
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
        // Value factory.
        final int initialValue = 3;
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(3, 5, initialValue);
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
        lblFileLocation.setId("label-settings");
        lblPlayablePieces.setId("label-settings");
        lblMode.setId("label-settings");
        rbDrag.setId("label-settings");
        rbClick.setId("label-settings");
        spPlayablePieces.setPrefWidth(50);

        // CSS + fonts
        Font.loadFont(getClass().getResourceAsStream("/fonts/Woodtrap.ttf"), 12);
        getStylesheets().add("/stylesheets/settings.css");
        setBackground(new Background(new BackgroundImage(new Image("/images/menu/bgstandard.png"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
        //tfFileLocation.setDisable(true);
        //lblFileLocation.setDisable(true);

        // Grid settings
        grid.setPadding(new Insets(50, 0, 0, 50));
        grid.setHgap(20);
        grid.setVgap(15);
        grid.setAlignment(Pos.BASELINE_CENTER);
        grid.add(lblTitel, 0, 0, 2, 1);
        grid.add(lblBoardSize, 0, 1);
        grid.add(vb1, 1, 1);
        //https://stackoverflow.com/questions/23956861/how-to-span-columns-of-a-gridpane-manually-in-javafx
        grid.add(lblDifficulty, 0, 2);
        grid.add(chkDifficulty, 1, 2);
        grid.add(lblMode, 0, 3);
        grid.add(rbClick, 1, 3);
        grid.add(rbDrag, 2, 3);
        grid.add(lblPlayablePieces, 0, 4);
        grid.add(spPlayablePieces, 1, 4);
        grid.add(lblFileLocation, 0, 5);
        grid.add(tfFileLocation, 1, 5);
        grid.add(imgSave, 1, 6);
        imgSave.setFitWidth(100);
        imgSave.setFitHeight(50);
        //grid.setGridLinesVisible(true);
    }

    /**
     * Returns: checkbox van de difficulty
     */
    CheckBox getChkDifficulty() {
        return chkDifficulty;
    }

    /**
     * Returns: save changes button
     */
    ImageView getImgSave() {
        return imgSave;
    }

    /**
     * Returns: Label van de board slider
     */
    Label getBoardSizeSliderLabel() {
        return lblBoardSizeSlider;
    }

    /**
     * Returns: Slider size
     */
    Slider getSlSize() {
        return slSize;
    }

    /**
     * Returns: Textfield van de playable pieces
     */
    Spinner<Integer> getSpPlayablePieces() {
        return spPlayablePieces;
    }

    RadioButton getRbDrag() {
        return rbDrag;
    }

    RadioButton getRbClick() {
        return rbClick;
    }

    public TextField getTfFileLocation() {
        return tfFileLocation;
    }
}
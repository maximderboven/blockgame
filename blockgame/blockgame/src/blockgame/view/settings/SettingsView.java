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
    private TextField tfPlayablePieces;
    private TextField tfFileLocation;
    private Button btnSave;
    private Slider slSize;
    private CheckBox chkDifficulty;
    private ImageView imgClose;


    // Constructor
    public SettingsView() {
        initialiseNodes();
        layoutNodes();
    }

    // Initialise nodes
    private void initialiseNodes() {

        //SETTINGS DRAG AND DROP TOEVOEGEN

        lblTitel = new Label("SETTINGS");
        lblBoardSize = new Label("Board size");
        lblBoardSizeSlider = new Label("Size: 5x5 ");
        lblDifficulty = new Label("Difficulty");
        lblPlayablePieces = new Label("Playable Pieces");
        lblFileLocation = new Label("File location");
        tfPlayablePieces = new TextField();
        tfFileLocation = new TextField();
        btnSave = new Button("Save Changes");
        slSize = new Slider(5, 10, 5);
        chkDifficulty = new CheckBox();
        imgClose = new ImageView("/images/menu/close.png");
    }

    // Layout nodes
    private void layoutNodes() {

        // Extra node declaration
        GridPane grid = new GridPane();
        VBox vb1 = new VBox(lblBoardSizeSlider, slSize);

        // Node settings
        chkDifficulty.setSelected(true);

        slSize.setBlockIncrement(1);
        slSize.setMajorTickUnit(1);
        slSize.setMinorTickCount(0);
        slSize.setShowTickLabels(true);
        slSize.setSnapToTicks(true);

        lblTitel.setId("title");
        btnSave.setId("btnSave");

        lblBoardSize.setId("label-settings");
        lblBoardSizeSlider.setId("label-settings");
        lblDifficulty.setId("label-settings");
        lblFileLocation.setId("label-settings");
        lblPlayablePieces.setId("label-settings");


        // CSS
        super.setPadding(new Insets(90));
        super.setCenter(grid);
        super.setRight(imgClose);
        imgClose.setFitHeight(40);
        imgClose.setFitWidth(40);
        Font.loadFont(getClass().getResourceAsStream("/fonts/Woodtrap.ttf"), 12);
        this.getStylesheets().add("/stylesheets/settings.css");
        this.setBackground(new Background(new BackgroundImage(new Image("/images/menu/bgstandard.png"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
        tfFileLocation.setDisable(true);
        lblFileLocation.setDisable(true);
        tfFileLocation.setText("Experimental");

        // Grid settings --> node, kolom, rij, ...
        grid.setHgap(20);
        grid.setVgap(15);
        grid.setAlignment(Pos.BASELINE_CENTER);
        grid.add(lblTitel, 0, 0, 2, 1);
        grid.add(lblBoardSize, 0, 2);
        grid.add(vb1, 1, 2);
        grid.add(lblDifficulty, 0, 4);
        grid.add(chkDifficulty, 1, 4);
        grid.add(lblPlayablePieces, 0, 6);
        grid.add(tfPlayablePieces, 1, 6);
        grid.add(lblFileLocation, 0, 8);
        grid.add(tfFileLocation, 1, 8);
        grid.add(btnSave, 1, 9);
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
    Button getBtnSave() {
        return btnSave;
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
    TextField getTfPlayablePieces() {
        return tfPlayablePieces;
    }

    ImageView getImgClose() {
        return imgClose;
    }
}
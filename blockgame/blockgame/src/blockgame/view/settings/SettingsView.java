package blockgame.view.settings;

import blockgame.view.menu.MenuView;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
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

    private MenuView mv;

    // Constructor
    public SettingsView() {
        initialiseNodes();
        layoutNodes();
    }

    // Initialise nodes
    private void initialiseNodes() {
        mv = new MenuView();
        lblTitel = new Label("SETTINGS");
        lblBoardSize = new Label("Board size");
        lblBoardSizeSlider = new Label("Size: 5x5 ");
        lblDifficulty = new Label("Difficulty");
        lblPlayablePieces = new Label("Playable Pieces");
        lblFileLocation = new Label("File location");

        tfPlayablePieces = new TextField();
        tfFileLocation = new TextField();

        btnSave = new Button("Save Changes");

        slSize = new Slider(1, 10, 1);

        chkDifficulty = new CheckBox();
    }

    // Layout nodes
    private void layoutNodes() {

        // Algemeen
        super.setTop(mv);
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
        grid.setPadding(new Insets(30));
        Font.loadFont(getClass().getResourceAsStream("/fonts/Woodtrap.ttf"), 12);
        this.getStylesheets().add("/stylesheets/settings.css");
        this.setBackground(new Background(new BackgroundImage(new Image("/images/bg.jpg"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
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
        setCenter(grid);
    }
    public MenuView getMv() {
        return mv;
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


}
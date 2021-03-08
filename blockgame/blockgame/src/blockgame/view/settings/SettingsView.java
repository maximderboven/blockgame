package blockgame.view.settings;

import blockgame.view.menu.MenuView;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

/**
 * @author Maxim Derboven
 * @version 1.0 17/02/2021 13:17
 */
public class SettingsView extends BorderPane {

    // Attributen
    private Label lblSettings;
    private Slider slSize;
    private CheckBox tbtnDifficulty;
    private TextField tfPieces;
    private TextField tfFileLocation;
    private Button btnMap;
    private Button btnClose;
    private Button btnSave;
    //private MenuBarView menu;
    private GridPane grid;
    private Label lblSize;
    private Label lblDifficulty;
    private Label lblPlayablePieces;
    private Label lblFileLocation;

    private MenuView mv;

    // Constructor
    public SettingsView() {
        initialiseNodes();
        layoutNodes();
    }

    // Initialise nodes
    private void initialiseNodes() {
        mv = new MenuView();
        lblSettings = new Label("SETTINGS");
        slSize = new Slider();
        tbtnDifficulty = new CheckBox();
        tfPieces = new TextField();
        tfFileLocation = new TextField();
        btnMap = new Button();
        btnClose = new Button("Close");
        btnSave = new Button("Save Changes");
        //menu = new MenuBarView();
        grid = new GridPane();
        lblSize = new Label("Board size:");
        lblDifficulty = new Label("Difficulty:");
        lblPlayablePieces = new Label("Playable Pieces:");
        lblFileLocation = new Label("File location:");
    }

    // Layout nodes
    private void layoutNodes() {

        // Algemeen
        //this.setTop(menu);
        super.setCenter(grid);
        super.setTop(mv);
        super.setMinHeight(650);
        super.setMinWidth(400);

        // Grid Settings
        grid.setHgap(35);
        grid.setVgap(30);
        grid.setAlignment(Pos.TOP_CENTER);
        grid.add(lblSettings, 0, 0, 2, 1);
        grid.add(new Separator(), 0, 1, 2, 1);
        grid.add(lblSize, 0, 2);
        grid.add(slSize, 1, 2);
        grid.add(new Separator(), 0, 3, 2, 1);
        grid.add(lblDifficulty, 0, 4);
        grid.add(tbtnDifficulty, 1, 4);
        grid.add(new Separator(), 0, 5, 2, 1);
        grid.add(lblPlayablePieces, 0, 6);
        grid.add(tfPieces, 1, 6);
        grid.add(new Separator(), 0, 7, 2, 1);
        grid.add(lblFileLocation, 0, 8);
        grid.add(tfFileLocation, 1, 8);
        grid.add(btnClose, 0, 9);
        grid.add(btnSave, 1, 9);

        // CSS layout + overige layout
        // grid.setGridLinesVisible(true);
        lblSize.setStyle("-fx-font-size: 24; -fx-font-weight: bolder");
        lblDifficulty.setStyle("-fx-font-size: 24; -fx-font-weight: bolder");
        lblSettings.setStyle("-fx-font-size: 24; -fx-font-weight: bolder");
        lblFileLocation.setStyle("-fx-font-size: 24; -fx-font-weight: bolder");
        lblPlayablePieces.setStyle("-fx-font-size: 24; -fx-font-weight: bolder");
        btnSave.setStyle("-fx-background-color: #1687a7; -fx-text-fill: #fff; -fx-font-weight: bolder;");
        btnClose.setStyle("-fx-background-color: #a6a9b6; -fx-text-fill: #fff; -fx-font-weight: bolder");
        lblSettings.setStyle("-fx-font-size: 36");
    }
    public MenuView getMv() {
        return mv;
    }


}
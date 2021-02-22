package blockgame.view.settingsscreen;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.awt.*;

/**
 * @author Maxim Derboven
 * @version 1.0 17/02/2021 13:17
 */
public class SettingsView extends GridPane {
    private Slider slSize;
    private ToggleButton tbtnDifficulty;
    private TextField tfPieces;
    private TextField tfFileLocation;
    private Button btnMap;

    public SettingsView() {
        initialiseNodes();
        layoutNodes();
    }
    private void initialiseNodes() {
        slSize = new Slider();
        tbtnDifficulty = new ToggleButton();
        tfPieces = new TextField();
        tfFileLocation = new TextField();
        btnMap = new Button();
    }

    private void layoutNodes() {
        // Algemeen
        //this.setPadding(new Insets(15));
        this.setAlignment(Pos.CENTER);
        this.setHgap(10);
        this.setVgap(10);

        // Items toevoegen aan de grid
        this.add(slSize, 0, 0);
        this.add(tbtnDifficulty, 0, 1);
        this.add(tfFileLocation, 0, 2);
        this.add(tfPieces, 1, 1);
        this.add(btnMap, 1, 2);

        /* CSS + overige
        this.lblRegister.setStyle("-fx-font-weight: bold; -fx-font-size: 22px");
        this.lblUsername.setStyle("-fx-font-size: 14px");
        this.lblPassword.setStyle("-fx-font-size: 14px");
        this.btnRegister.setStyle("-fx-background-color: #1687a7; -fx-text-fill: #fff; -fx-font-weight: bolder;");
        this.btnRegister.setPrefSize(80, 20);
        this.btnRegister.setCursor(Cursor.HAND);
        this.btnLogin.setStyle("-fx-background-color: #a6a9b6; -fx-text-fill: #fff; -fx-font-weight: bolder");
        this.btnLogin.setCursor(Cursor.HAND);*/
    }


    Slider getSlSize() {
        return slSize;
    }

    ToggleButton getTbtnDifficulty() {
        return tbtnDifficulty;
    }

    TextField getTfPieces() {
        return tfPieces;
    }

    TextField getTfFileLocation() {
        return tfFileLocation;
    }

    Button getBtnMap() {
        return btnMap;
    }
}
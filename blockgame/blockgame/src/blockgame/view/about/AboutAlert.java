package blockgame.view.about;

import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Alexie Chaerle
 * 22/02/2021
 */
public class AboutAlert extends Alert {
    // Constructor
    public AboutAlert() {
        super(AlertType.INFORMATION);
        this.setTitle("About");
        this.setHeaderText("About this game");
        this.setContentText("Wooden Block Puzzle - JavaFX\nAuthors: Maxim Derboven & Alexie Chaerle\nVersion: 1.0\nGroup: INF105A");
        Stage stage = (Stage) this.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(this.getClass().getResource("/images/logo.png").toString()));
    }

}

package blockgame.view.alerts;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;


/**
 * Alexie Chaerle
 * 12/03/2021
 */
public class ConfirmationAlertView extends Alert {

    // Constructor
    public ConfirmationAlertView() {
        super(AlertType.CONFIRMATION);
        this.setTitle("Confirm action");
        this.setHeaderText("Hold on!");
        this.setContentText("Are you sure you want to quit? Unsaved changes will be lost!\n ");
        ((Button) this.getDialogPane().lookupButton(ButtonType.OK)).setText("Quit game");
        Stage stage = (Stage) this.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(this.getClass().getResource("/images/logo.png").toString()));
        ButtonType btnHome = new ButtonType("Home");
        this.getButtonTypes().add(btnHome);
    }

}

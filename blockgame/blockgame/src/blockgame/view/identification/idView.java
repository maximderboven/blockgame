package blockgame.view.identification;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;


/**
 * Alexie Chaerle
 * 26/02/2021
 */
public class idView extends GridPane {

    // Attributen (gemeenschappelijk voor login en register)
    private Label lblTitel;
    private Label lblUsername;
    private Label lblPassword;
    private TextField txtUsername;
    private PasswordField txtPassword;
    private Button btnId;
    private Label lblRedirect;


    // Constructor
    public idView() {
        initialiseNodes();
        layoutNodes();
    }

    // Initialisatie van de nodes
    private void initialiseNodes() {
        this.lblTitel = new Label();
        this.lblUsername = new Label("Username");
        this.lblPassword = new Label("Password");
        this.txtUsername = new TextField();
        this.txtPassword = new PasswordField();
        this.btnId = new Button();
        this.lblRedirect = new Label();

        super.add(lblTitel, 0, 0, 2, 1);
        super.add(lblUsername, 0, 1);
        super.add(txtUsername, 1, 1);
        super.add(lblPassword, 0, 2);
        super.add(txtPassword, 1, 2);
        super.add(lblRedirect, 0, 3, 2, 1);
        super.add(btnId, 0, 4);
    }

    // Layout van de nodes
    private void layoutNodes() {

        lblTitel.setStyle("-fx-font-size: 42px; -fx-font-weight: bold; -fx-text-fill: white");
        lblUsername.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: white");
        lblPassword.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: white");
        lblRedirect.setStyle("-fx-font-size: 12px; -fx-text-fill: white");
        btnId.setMinWidth(80);
        super.setVgap(15);
        super.setHgap(15);
        super.setAlignment(Pos.CENTER);
        this.setBackground(new Background(new BackgroundImage(new Image("/images/woodenbggray.jpg"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
        this.setMinHeight(350);
        this.setMinWidth(500);
    }

    // Setter om de hoofdtitel in te stellen
    protected void setLblTitel(String text) {
        this.lblTitel.setText(text);
    }

    // Setter om de titel van de button in te stellen
    protected void setBtnIdTitel(String text) {
        this.btnId.setText(text);
    }

    public void setLblRedirect(String text) {
        this.lblRedirect.setText(text);
    }

    public Label getLblRedirect() {
        return lblRedirect;
    }
}

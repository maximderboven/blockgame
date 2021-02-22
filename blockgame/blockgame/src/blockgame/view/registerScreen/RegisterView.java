package blockgame.view.registerScreen;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.control.*;

import java.awt.*;

/**
 * Alexie Chaerle
 * 17/02/2021
 */
public class RegisterView extends GridPane {
    // Attributen
    private Label lblRegister;
    private Label lblUsername;
    private Label lblPassword;
    private Button btnLogin;
    private Button btnRegister;
    private TextField txtUsername;
    private PasswordField txtPassword;

    // Constructor
    public RegisterView() {
        this.initialiseNodes();
        this.layoutNodes();
    }

    // Initialisatie van de Nodes
    private void initialiseNodes() {
        this.lblRegister = new Label("REGISTER");
        this.lblUsername = new Label("Username");
        this.lblPassword = new Label("Password");
        this.btnLogin = new Button("Login");
        this.btnRegister = new Button("Register");
        this.txtUsername = new TextField();
        this.txtPassword = new PasswordField();
    }

    // Layout van de Nodes
    private void layoutNodes() {

        // Algemeen
        this.setPadding(new Insets(15));
        this.setAlignment(Pos.CENTER);
        this.setHgap(10);
        this.setVgap(10);

        // Items toevoegen aan de grid
        this.add(lblRegister, 0, 0);
        this.add(lblUsername, 0, 1);
        this.add(lblPassword, 0, 2);
        this.add(txtUsername, 1, 1);
        this.add(txtPassword, 1, 2);
        this.add(btnLogin, 0, 4);
        this.add(btnRegister, 1, 4);

        // CSS + overige
        this.lblRegister.setStyle("-fx-font-weight: bold; -fx-font-size: 22px");
        this.lblUsername.setStyle("-fx-font-size: 14px");
        this.lblPassword.setStyle("-fx-font-size: 14px");
        this.btnRegister.setStyle("-fx-background-color: #1687a7; -fx-text-fill: #fff; -fx-font-weight: bolder;");
        this.btnRegister.setPrefSize(80, 20);
        this.btnRegister.setCursor(Cursor.HAND);
        this.btnLogin.setStyle("-fx-background-color: #a6a9b6; -fx-text-fill: #fff; -fx-font-weight: bolder");
        this.btnLogin.setCursor(Cursor.HAND);

    }
}
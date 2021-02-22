package blockgame.view.registerScreen;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

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
    private Separator sep;
    private Label lblLogin;

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
        this.sep = new Separator();
        this.lblLogin = new Label("Click the button below if you want to login.");
    }

    // Layout van de Nodes
    private void layoutNodes() {

        // Algemeen
        setMinHeight(280);
        setMinWidth(250);
        setPadding(new Insets(15));
        setHgap(10);
        setVgap(10);
        setAlignment(Pos.CENTER);

        // Items toevoegen aan de grid
        add(lblRegister, 0, 0);
        add(lblUsername, 0, 1);
        add(lblPassword, 0, 2);
        add(txtUsername, 1, 1);
        add(txtPassword, 1, 2);
        add(btnRegister, 1, 4);
        add(sep, 0,5,4,1);
        add(lblLogin, 0,6,2,1);
        add(btnLogin, 0, 7);

        // CSS + overige
        lblRegister.setStyle("-fx-font-weight: bold; -fx-font-size: 22px");
        lblUsername.setStyle("-fx-font-size: 14px");
        lblPassword.setStyle("-fx-font-size: 14px");
        btnRegister.setStyle("-fx-background-color: #1687a7; -fx-text-fill: #fff; -fx-font-weight: bolder;");
        btnRegister.setPrefSize(80, 20);
        btnRegister.setCursor(Cursor.HAND);
        btnLogin.setStyle("-fx-background-color: #a6a9b6; -fx-text-fill: #fff; -fx-font-weight: bolder");
        btnLogin.setCursor(Cursor.HAND);
        sep.setPadding(new Insets(15,0,0,0));
    }

    // Getters

    Button getBtnLogin() {
        return btnLogin;
    }
}
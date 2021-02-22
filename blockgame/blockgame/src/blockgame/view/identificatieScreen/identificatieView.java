package blockgame.view.identificatieScreen;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

/**
 * Alexie Chaerle
 * 22/02/2021
 */
public abstract class identificatieView extends GridPane {

    // Attributen
    protected Label lblRegister;
    protected Label lblUsername;
    protected Label lblPassword;
    protected Button btnLogin;
    protected Button btnRegister;
    protected TextField txtUsername;
    protected PasswordField txtPassword;
    protected Separator sep;
    protected Label lblLogin;


    // Initialisatie van de Nodes
    protected void initialiseNodes(String titel, String subTxt) {
        this.lblRegister = new Label(titel);
        this.lblUsername = new Label("Username");
        this.lblPassword = new Label("Password");
        this.btnLogin = new Button("Login");
        this.btnRegister = new Button("Register");
        this.txtUsername = new TextField();
        this.txtPassword = new PasswordField();
        this.sep = new Separator();
        this.lblLogin = new Label(subTxt);
    }


    // Layout van de Nodes
    protected void layoutNodes() {
        setMinHeight(280);
        setMinWidth(250);
        setPadding(new Insets(15));
        setHgap(10);
        setVgap(10);
        setAlignment(Pos.CENTER);

        // Items toevoegen aan grid
        add(lblRegister, 0, 0);
        add(lblUsername, 0, 1);
        add(lblPassword, 0, 2);
        add(txtUsername, 1, 1);
        add(txtPassword, 1, 2);
        add(sep, 0, 5, 4, 1);
        add(lblLogin, 0, 6, 2, 1);
        setRegisterButton();
        setLoginButton();

        // Style CSS + overige
        lblRegister.setStyle("-fx-font-weight: bold; -fx-font-size: 22px");
        lblUsername.setStyle("-fx-font-size: 14px");
        lblPassword.setStyle("-fx-font-size: 14px");
        btnRegister.setCursor(Cursor.HAND);
        btnLogin.setCursor(Cursor.HAND);
        sep.setPadding(new Insets(15, 0, 0, 0));
    }

    protected abstract void setRegisterButton();

    protected abstract void setLoginButton();

}

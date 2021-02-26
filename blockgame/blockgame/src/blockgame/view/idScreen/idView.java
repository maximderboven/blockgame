package blockgame.view.idScreen;

import blockgame.view.idMenuScreen.idMenuView;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;


/**
 * Alexie Chaerle
 * 26/02/2021
 */
public class idView extends BorderPane {

    // Attributen (gemeenschappelijk voor login en register)
    private idMenuView menu;
    private Label lblTitel;
    private Label lblUsername;
    private Label lblPassword;
    private TextField txtUsername;
    private PasswordField txtPassword;
    private Button btnId;
    private GridPane grdpContent;
    private Separator sep;

    // Constructor
    public idView() {
        initialiseNodes();
        layoutNodes();
    }

    // Initialisatie van de nodes
    private void initialiseNodes() {
        this.menu = new idMenuView();
        this.lblTitel = new Label();
        this.lblUsername = new Label("Username");
        this.lblPassword = new Label("Password");
        this.txtUsername = new TextField();
        this.txtPassword = new PasswordField();
        this.btnId = new Button();
        this.grdpContent = new GridPane();
        this.sep = new Separator(Orientation.HORIZONTAL);
    }

    // Layout van de nodes
    private void layoutNodes() {
        super.setTop(menu);
        grdpContent.add(lblTitel, 0, 0,2,1);
        grdpContent.add(sep, 0,1,2,1);
        grdpContent.add(lblUsername, 0, 2);
        grdpContent.add(lblPassword, 0, 3);
        grdpContent.add(txtUsername, 1, 2);
        grdpContent.add(txtPassword, 1, 3);
        super.setCenter(grdpContent);
        lblTitel.setStyle("-fx-font-size: 24");
        grdpContent.setPadding(new Insets(15));
        lblUsername.setPadding(new Insets(15,15,15,0));
        super.setBottom(btnId);
        BorderPane.setMargin(btnId, new Insets(15));
        btnId.setMinWidth(80);
        btnId.setMinHeight(30);
    }

    // Setter om de hoofdtitel in te stellen
    protected void setLblTitel(String text) {
        this.lblTitel.setText(text);
    }

    // Setter om de titel van de button in te stellen
    protected void setBtnIdTitel(String text) {
        this.btnId.setText(text);
    }

    public MenuItem getRegisterButton() {
        return menu.getRegButton();
    }

    public MenuItem getLoginButton() {
        return menu.getLogButton();
    }

    public MenuItem getAbout(){
        return menu.getBtnAbout();
    }
}

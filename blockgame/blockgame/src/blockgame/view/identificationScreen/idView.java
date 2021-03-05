package blockgame.view.identificationScreen;

import blockgame.view.menuScreen.idMenuView;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;


/**
 * Alexie Chaerle
 * 26/02/2021
 */
public class idView extends BorderPane {

    // Attributen (gemeenschappelijk voor login en register)
    private Label lblTitel;
    private Label lblUsername;
    private Label lblPassword;
    private TextField txtUsername;
    private PasswordField txtPassword;
    private Button btnId;
    private HBox hb1;
    private HBox hb2;
    private VBox vb;

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

        this.hb1 = new HBox(lblUsername,txtUsername);
        this.hb2 = new HBox(lblPassword,txtPassword);

        this.vb = new VBox(lblTitel,hb1,hb2,btnId);
    }

    // Layout van de nodes
    private void layoutNodes() {
        super.setCenter(vb);
        this.setBackground(new Background(new BackgroundImage(new Image("/images/woodenbg.jpg"), BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT)));
        this.setMinHeight(350);
        this.setMinWidth(500);
        super.setCenter(vb);

    }

    // Setter om de hoofdtitel in te stellen
    protected void setLblTitel(String text) {
        this.lblTitel.setText(text);
    }

    // Setter om de titel van de button in te stellen
    protected void setBtnIdTitel(String text) {
        this.btnId.setText(text);
    }
}

package blockgame.view.identification;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;


/**
 * Alexie Chaerle
 * 26/02/2021
 */
public abstract class AuthorizationView extends BorderPane {

    // Attributen (gemeenschappelijk voor login en register)
    private Label lblTitel;
    private Label lblUsername;
    private Label lblPassword;
    private TextField txtUsername;
    private PasswordField txtPassword;
    private Label lblRedirect;
    private Label lblError;
    private ImageView imgClose;
    private GridPane grid;
    private ImageView imgId;

    // Constructor
    public AuthorizationView() {
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
        this.lblRedirect = new Label();
        this.lblError = new Label("");
        this.grid = new GridPane();
        this.imgClose = new ImageView("/images/menu/close.png");
    }

    // Layout van de nodes
    private void layoutNodes() {
        super.setPadding(new Insets(90));
        super.setRight(imgClose);
        super.setCenter(grid);

        imgClose.setFitWidth(40);
        imgClose.setFitHeight(40);

        grid.setAlignment(Pos.CENTER);

        grid.add(lblTitel, 0, 0, 2, 1);
        grid.add(lblUsername, 0, 1);
        grid.add(txtUsername, 1, 1);
        grid.add(lblPassword, 0, 2);
        grid.add(txtPassword, 1, 2);
        grid.add(lblRedirect, 0, 3, 2, 1);
        grid.add(lblError, 0, 5, 2, 1);

        Font.loadFont(getClass().getResourceAsStream("/fonts/Woodtrap.ttf"), 12);
        getStylesheets().add("/stylesheets/authorization.css");
        lblTitel.setId("titel");
        lblUsername.setId("username");
        lblPassword.setId("password");
        lblError.setId("error");
        lblRedirect.setId("redirect");
        grid.setVgap(15);
        grid.setHgap(15);
        this.setBackground(new Background(new BackgroundImage(new Image("/images/menu/bgstandard.png"),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT)));
    }


    /* Getters */
    TextField getTxtUsername() {
        return txtUsername;
    }

    PasswordField getTxtPassword() {
        return txtPassword;
    }

    Label getLblRedirect() {
        return lblRedirect;
    }

    ImageView getImgClose() {
        return imgClose;
    }

    ImageView getImgId() {
        return imgId;
    }


    /* Setters */
    void setLblTitel(String text) {
        this.lblTitel.setText(text);
    }

    void setLblError(String text) {
        this.lblError.setText(text);
    }

    void setLblRedirect(String text) {
        this.lblRedirect.setText(text);
    }

    void setImgId(ImageView v) {
        this.imgId = v;
        grid.add(v, 0, 4);
        v.setFitHeight(50);
        v.setFitWidth(100);
    }

}

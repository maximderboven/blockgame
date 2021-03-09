package blockgame.view.identification;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;


/**
 * Alexie Chaerle
 * 26/02/2021
 */
public class AuthorizationView extends BorderPane {

    // Attributen (gemeenschappelijk voor login en register)
    private Label lblTitel;
    private Label lblUsername;
    private Label lblPassword;
    private TextField txtUsername;
    private PasswordField txtPassword;
    private Button btnId;
    private Label lblRedirect;
    private Label lblError;
    private ImageView imgClose;
    private GridPane grid;


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
        this.btnId = new Button();
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
        grid.add(btnId, 0, 4);
        grid.add(lblError, 0, 5, 2, 1);

        lblTitel.setStyle("-fx-font-size: 42px; -fx-font-weight: bold; -fx-text-fill: white");
        lblUsername.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: white");
        lblPassword.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: white");
        lblError.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: red");
        lblRedirect.setStyle("-fx-font-size: 12px; -fx-text-fill: white");
        btnId.setMinWidth(80);
        grid.setVgap(15);
        grid.setHgap(15);
        this.setBackground(new Background(new BackgroundImage(new Image("/images/menu/bgstandard.png"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
    }

    // Setter om de hoofdtitel in te stellen
    void setLblTitel(String text) {
        this.lblTitel.setText(text);
    }

    // Setter om de titel van de button in te stellen
    void setBtnIdTitel(String text) {
        this.btnId.setText(text);
    }

    TextField getTxtUsername() {
        return txtUsername;
    }

    void setLblError(String text) {
        this.lblError.setText(text);
    }

    PasswordField getTxtPassword() {
        return txtPassword;
    }

    Button getBtnId() {
        return btnId;
    }

    void setLblRedirect(String text) {
        this.lblRedirect.setText(text);
    }

    Label getLblRedirect() {
        return lblRedirect;
    }

    ImageView getImgClose() {
        return imgClose;
    }
}

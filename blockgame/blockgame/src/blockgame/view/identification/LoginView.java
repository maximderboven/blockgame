package blockgame.view.identification;

import javafx.scene.image.ImageView;

/**
 * Alexie Chaerle
 * 22/02/2021
 */
public class LoginView extends AuthorizationView {

    // Constructor
    public LoginView() {
        super();
        super.setLblTitel("LOGIN");
        super.setImgId(new ImageView("/images/LoginButton.png"));
        super.setLblRedirect("Don't have an account? Click here to register.");
    }

}

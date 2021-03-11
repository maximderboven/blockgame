package blockgame.view.identification;

import javafx.scene.image.ImageView;

/**
 * Alexie Chaerle
 * 17/02/2021
 */
public class RegisterView extends AuthorizationView {

    // Constructor
    public RegisterView() {
        super();
        super.setLblTitel("REGISTER");
        super.setImgId(new ImageView("/images/RegisterButton.png"));
        super.setLblRedirect("Already have an account? Click here to login.");
    }

}
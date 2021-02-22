package blockgame.view.loginScreen;

import blockgame.view.identificatieScreen.identificatieView;

/**
 * Alexie Chaerle
 * 22/02/2021
 */
public class LoginView extends identificatieView {

    public LoginView() {
        super.initialiseNodes("LOGIN", "Click here to if you want to register.");
        super.layoutNodes();
    }

    @Override
    protected void setRegisterButton() {
        add(btnRegister, 0, 7);
        btnRegister.setStyle("-fx-background-color: #a6a9b6; -fx-text-fill: #fff; -fx-font-weight: bolder");
    }

    @Override
    protected void setLoginButton() {
        add(btnLogin, 1, 4);
        btnLogin.setStyle("-fx-background-color: #1687a7; -fx-text-fill: #fff; -fx-font-weight: bolder;");
        btnLogin.setPrefSize(80, 20);
    }
}

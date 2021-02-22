package blockgame.view.registerScreen;

import blockgame.view.identificatieScreen.identificatieView;

/**
 * Alexie Chaerle
 * 17/02/2021
 */
public class RegisterView extends identificatieView {

    public RegisterView() {
        super.initialiseNodes("REGISTER", "Click here to if you want to login.");
        super.layoutNodes();
    }

    @Override
    protected void setRegisterButton() {
        add(btnRegister, 1, 4);
        btnRegister.setStyle("-fx-background-color: #1687a7; -fx-text-fill: #fff; -fx-font-weight: bolder;");
        btnRegister.setPrefSize(80, 20);
    }

    @Override
    protected void setLoginButton() {
        add(btnLogin, 0, 7);
        btnLogin.setStyle("-fx-background-color: #a6a9b6; -fx-text-fill: #fff; -fx-font-weight: bolder");
    }
}
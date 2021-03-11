package blockgame.view.identification;

import blockgame.model.Game;
import blockgame.view.mainMenu.MainMenuPresenter;
import blockgame.view.mainMenu.MainMenuView;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;

/**
 * Alexie Chaerle
 * 17/02/2021
 */
public class RegisterPresenter {
    private Game model;
    private RegisterView view;

    public RegisterPresenter(Game model, RegisterView view) {
        this.model = model;
        this.view = view;
        this.addEventHandlers();
        this.updateView();
    }

    private void addEventHandlers() {

        // Return to main menu
        view.getImgClose().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                MainMenuView mmv = new MainMenuView();
                MainMenuPresenter mmp = new MainMenuPresenter(model, mmv);
                view.getScene().setRoot(mmv);
            }
        });

        // Set cursor pointer
        view.getImgClose().setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                view.getImgClose().setCursor(Cursor.HAND);
            }
        });

        // Gebruikers registreren
        view.getImgId().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    model.getAm().register(view.getTxtUsername().getText(), view.getTxtPassword().getText());
                    System.out.println("gebruiker aangemaakt!");
                } catch (Exception ioe) {
                    System.out.println("Deze gebruiker bestaat al");
                }
            }
        });

        view.getLblRedirect().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                LoginView lv = new LoginView();
                LoginPresenter lp = new LoginPresenter(model, lv);
                view.getScene().setRoot(lv);
            }
        });

        view.getImgId().setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                view.getImgId().setScaleX(1.15);
                view.getImgId().setScaleY(1.15);
                view.getImgId().setCursor(Cursor.HAND);
            }
        });

        view.getImgId().setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                view.getImgId().setCursor(Cursor.DEFAULT);
                view.getImgId().setScaleX(1);
                view.getImgId().setScaleY(1);
            }
        });

    }

    private void updateView() {

    }

}

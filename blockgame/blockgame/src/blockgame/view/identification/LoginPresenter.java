package blockgame.view.identification;

import blockgame.model.Game;
import blockgame.view.game.GamePresenter;
import blockgame.view.game.GameView;
import blockgame.view.mainMenu.MainMenuPresenter;
import blockgame.view.mainMenu.MainMenuView;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;

/**
 * Alexie Chaerle
 * 22/02/2021
 */
public class LoginPresenter {
    private Game model;
    private LoginView view;

    public LoginPresenter(Game model, LoginView view) {
        this.model = model;
        this.view = view;
        this.addEventHandlers();
        this.updateView();
    }

    public void addEventHandlers() {

        // Om te gaan naar registerview
        view.getLblRedirect().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                RegisterView registerView = new RegisterView();
                RegisterPresenter registerPresenter = new RegisterPresenter(model, registerView);
                view.getScene().setRoot(registerView);
            }
        });


        view.getImgId().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    model.login(view.getTxtUsername().getText(), view.getTxtPassword().getText());

                    System.out.println("ingelogd");

                    GameView gameView = new GameView(model.getBoard().getSize());
                    GamePresenter gp = new GamePresenter(model, gameView);

                    view.getScene().setRoot(gameView);

                } catch (Exception e) {
                    view.setLblError(e.getMessage());
                }
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

        view.getImgClose().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                MainMenuView mv = new MainMenuView();
                MainMenuPresenter mp = new MainMenuPresenter(model, mv);
                view.getScene().setRoot(mv);
            }
        });

        view.getImgClose().setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                view.getImgClose().setCursor(Cursor.HAND);
            }
        });
    }

    private void updateView() {

    }
}

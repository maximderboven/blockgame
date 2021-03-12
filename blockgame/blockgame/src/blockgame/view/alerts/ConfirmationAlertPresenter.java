package blockgame.view.alerts;

import blockgame.model.Game;
import blockgame.view.mainMenu.MainMenuPresenter;
import blockgame.view.mainMenu.MainMenuView;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.util.Optional;

/**
 * Alexie Chaerle
 * 12/03/2021
 */
public class ConfirmationAlertPresenter {

    // Attributen
    private Game model;
    private ConfirmationAlertView view;


    // Constructor
    public ConfirmationAlertPresenter(Game model, ConfirmationAlertView view) {
        this.model = model;
        this.view = view;
        addEventHandlers();
        updateView();
    }

    // Event handlers
    private void addEventHandlers() {

        // Eventhandler voor alert confirmation
        Optional<ButtonType> result = view.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            Platform.exit();
        } else if (result.isPresent() && result.get().getText().equalsIgnoreCase("Home")) {
            Stage primaryStage = new Stage();
            MainMenuView mv = new MainMenuView();
            MainMenuPresenter mp = new MainMenuPresenter(model, mv);
            Scene scene = new Scene(mv);
            primaryStage.setScene(scene);
        }

    }

    // Updateview
    private void updateView() {

    }

}

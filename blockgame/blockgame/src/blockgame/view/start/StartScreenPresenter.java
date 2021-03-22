package blockgame.view.start;

import blockgame.model.Game;
import blockgame.view.mainMenu.MainMenuPresenter;
import blockgame.view.mainMenu.MainMenuView;
import javafx.event.*;

public class StartScreenPresenter {

    /**
     * Attributen
     */
    private Game model;
    private StartScreenView view;


    /**
     * Constructor
     */
    public StartScreenPresenter(Game model, StartScreenView view) {
        this.model = model;
        this.view = view;
        updateView();
        addEventHandlers();
    }

    /* Override de andere */
    private void addEventHandlers() {
        view.getTransition().setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MainMenuView mv = new MainMenuView();
                view.getScene().setRoot(mv);
                new MainMenuPresenter(model, mv);
            }
        });
    }

    private void updateView() {
        // ...
    }

}

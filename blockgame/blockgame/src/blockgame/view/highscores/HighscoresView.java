package blockgame.view.highscores;

import blockgame.view.menu.MenuView;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

/**
 * @author Maxim Derboven
 * @version 1.0 7/03/2021 16:35
 */
public class HighscoresView extends BorderPane {

    private MenuView mv;

    // Constructor
    public HighscoresView() {
        initialiseNodes();
        layoutNodes();
    }

    // Initialise nodes
    private void initialiseNodes() {
        mv = new MenuView();
    }

    // Layout nodes
    private void layoutNodes() {
        // Algemeen
        this.setTop(mv);
    }

    public MenuView getMv() {
        return mv;
    }
}

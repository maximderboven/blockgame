package blockgame.view.menuBarScreen;

import javafx.geometry.Insets;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;


/**
 * Alexie Chaerle
 * 22/02/2021
 */
public class MenuBarView extends BorderPane {

    // Attributen
    private MenuBar menuBar;
    private Menu menuGame;
    private Menu menuSettings;
    private Menu menuHighscores;
    private Menu menuAbout;

    public MenuBarView() {
        this.initialiseNodes();
        this.layoutNodes();
    }

    private void initialiseNodes() {
        this.menuGame = new Menu("Game", null);
        this.menuSettings = new Menu("Settings", null);
        this.menuHighscores = new Menu("Highscores", null);
        this.menuAbout = new Menu("About", null);
        this.menuBar = new MenuBar(menuGame, menuSettings, menuHighscores, menuAbout);
    }

    private void layoutNodes() {
        this.setTop(menuBar);
        BorderPane.setMargin(menuBar, new Insets(0.0, 0.0, 60.0, 0.0));
    }
}

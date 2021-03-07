package blockgame.view.menu;

import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;

/**
 * Alexie Chaerle
 * 26/02/2021
 */
public class MenuView extends BorderPane {

    // id in "idMenuView" staat voor: identificatie
    // Deze klasse bevat de menu die gebruikt wordt door de klassen loginView & registerView

    // Attributen
    private MenuBar menubar;

    private Label lblGame;
    private Label lblSettings;
    private Label lblHighscores;
    private Label lblAbout;

    private Menu mnGame;
    private Menu mnSettings;
    private Menu mnHighscores;
    private Menu mnAbout;


    // Constructor
    public MenuView() {
        initialiseNodes();
        layoutNodes();
    }

    // Initialisatie van de nodes
    private void initialiseNodes() {
        this.menubar = new MenuBar();
        this.lblGame  = new Label("Game");
        mnGame = new Menu("",lblGame);
        this.lblSettings  = new Label("Settings");
        mnSettings = new Menu("",lblSettings);
        this.lblHighscores  = new Label("Highscores");
        mnHighscores = new Menu("",lblHighscores);
        this.lblAbout  = new Label("About");
        mnAbout = new Menu("",lblAbout);
    }

    // Layout van de nodes
    private void layoutNodes() {
        this.setTop(menubar);
        this.menubar.getMenus().addAll(mnGame,mnSettings,mnHighscores,mnAbout);
    }

    public Label getLblGame() {
        return lblGame;
    }

    public Label getLblSettings() {
        return lblSettings;
    }

    public Label getLblHighscores() {
        return lblHighscores;
    }

    public Label getLblAbout() {
        return lblAbout;
    }
}

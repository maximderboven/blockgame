package blockgame.view.menuScreen;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;

/**
 * Alexie Chaerle
 * 26/02/2021
 */
public class idMenuView extends BorderPane {

    // id in "idMenuView" staat voor: identificatie
    // Deze klasse bevat de menu die gebruikt wordt door de klassen loginView & registerView

    // Attributen
    private MenuBar menubar;
    private Menu menuAuthorize;
    private MenuItem btnLogin;
    private MenuItem btnRegister;

    private Menu menuHelp;
    private MenuItem btnAbout;

    // Constructor
    public idMenuView() {
        initialiseNodes();
        layoutNodes();
    }

    // Initialisatie van de nodes
    private void initialiseNodes() {
        this.menubar = new MenuBar();
        this.menuAuthorize = new Menu("Authorize");
        this.btnLogin = new MenuItem("Login");
        this.btnRegister = new MenuItem("Register");
        this.menuHelp = new Menu("Help");
        this.btnAbout = new MenuItem("About");
    }

    // Layout van de nodes
    private void layoutNodes() {
        this.setTop(menubar);
        this.menubar.getMenus().addAll(menuAuthorize, menuHelp);
        this.menuAuthorize.getItems().addAll(btnLogin, btnRegister);
        this.menuHelp.getItems().add(btnAbout);
    }

    public MenuItem getRegButton() {
        return btnRegister;
    }

    public MenuItem getLogButton() {
        return btnLogin;
    }

    public MenuItem getBtnAbout() {
        return btnAbout;
    }
}

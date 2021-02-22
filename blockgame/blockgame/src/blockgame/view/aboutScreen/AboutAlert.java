package blockgame.view.aboutScreen;

import javafx.scene.control.Alert;

/**
 * Alexie Chaerle
 * 22/02/2021
 */
public class AboutAlert extends Alert {

    // Attributen
    private Alert alert;

    // Constructor
    public AboutAlert() {
        super(AlertType.INFORMATION);
        this.setTitle("About");
        this.setHeaderText("About this game");
        this.setContentText("Wooden Block Puzzle - JavaFX\nAuthors: Maxim Derboven & Alexie Chaerle\nVersion: 1.0\nGroup: INF105A");
    }

}

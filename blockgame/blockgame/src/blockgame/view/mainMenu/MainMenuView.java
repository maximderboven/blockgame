package blockgame.view.mainMenu;

import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

/**
 * Alexie Chaerle
 * 9/03/2021
 */
public class MainMenuView extends GridPane {

    /* Attributen */
    private ImageView imgPlay;
    private ImageView imgHighscores;
    private ImageView imgSettings;
    private ImageView imgAbout;


    /* Constructor */
    public MainMenuView() {
        initialiseNodes();
        layoutNodes();
    }


    /* initialisatie van de nodes */
    private void initialiseNodes() {
        imgPlay = new ImageView("/images/menu/play.png");
        imgHighscores = new ImageView("/images/menu/highscores.png");
        imgSettings = new ImageView("/images/menu/settings.png");
        imgAbout = new ImageView("/images/menu/about.png");
    }


    /* Layout van de nodes */
    private void layoutNodes() {
        setMargin(imgPlay, new Insets(303, 0, 0, 420));
        setMargin(imgHighscores, new Insets(0, 0, 0, 390));
        setMargin(imgAbout, new Insets(35, 0, 0, 195));
        setMargin(imgSettings, new Insets(0, 50, 0, 390));
        setBackground(new Background(new BackgroundImage(new Image("/images/menu/menubgsmall.png"),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT)));

        add(imgPlay, 0, 0);
        add(imgHighscores, 0, 1);
        add(imgSettings, 0, 2);
        add(imgAbout, 1, 1);
    }


    ImageView getImgPlay() {
        return imgPlay;
    }

    ImageView getImgHighscores() {
        return imgHighscores;
    }

    ImageView getImgSettings() {
        return imgSettings;
    }

    ImageView getImgAbout() {
        return imgAbout;
    }

}

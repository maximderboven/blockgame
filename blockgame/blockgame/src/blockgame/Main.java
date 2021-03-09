package blockgame;

import blockgame.model.Game;
import blockgame.view.identification.LoginPresenter;
import blockgame.view.identification.LoginView;
import blockgame.view.mainMenu.MainMenuPresenter;
import blockgame.view.mainMenu.MainMenuView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Maxim Derboven
 * @version 1.0 9/12/2020 18:44
 */
public class Main extends Application {

    private static final char FILE_SEPARATOR = System.getProperties().getProperty("file.separator").charAt(0);
    private Path soundPath = Paths.get("blockgame" + FILE_SEPARATOR + "resources" + FILE_SEPARATOR + "sounds" + FILE_SEPARATOR + "Lukewarm.mp3");
    private Media sound = new Media(new File(soundPath.toString()).toURI().toString());
    private MediaPlayer mediaPlayer = new MediaPlayer(sound);

    @Override
    public void start(Stage primaryStage) throws Exception {
        Game model = new Game();
        MainMenuView mmv = new MainMenuView();
        MainMenuPresenter mmp = new MainMenuPresenter(model, mmv);

        LoginView lv = new LoginView();
        LoginPresenter lp = new LoginPresenter(model, lv);

        mediaPlayer.play();
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setVolume(0.2);

        Scene scene = new Scene(mmv);
        primaryStage.setScene(scene);
        primaryStage.setTitle("KDG BLOCK GAME");
        primaryStage.setResizable(false);
        primaryStage.setWidth(900);
        primaryStage.setHeight(675);
        primaryStage.getIcons().add(new Image("/images/logo.png"));
        primaryStage.show();
    }

    private static blockgame.model.Game game;
    //private static final Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args) {
        game = new blockgame.model.Game(); //init
        Application.launch(args);
    }

}
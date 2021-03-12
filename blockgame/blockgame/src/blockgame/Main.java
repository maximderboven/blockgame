package blockgame;

import blockgame.model.Game;
import blockgame.view.alerts.ConfirmationAlertView;
import blockgame.view.gameover.GameOverPresenter;
import blockgame.view.gameover.GameOverView;
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

        /*mediaPlayer.play();
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setVolume(0.2);*/

        MainMenuView mmv = new MainMenuView();
        MainMenuPresenter mmp = new MainMenuPresenter(model, mmv);

        //GameOverView gv = new GameOverView();
        //GameOverPresenter gop = new GameOverPresenter(model, gv);

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
        /*game = new blockgame.model.Game(); //init
        while (true) {
        System.out.println(game.getBoard().toString());
        System.out.println(game.getPlayablePieces().getPieces());
        int x,y,p;
        Scanner kb = new Scanner(System.in);
        p = kb.nextInt()-1;
        System.out.println("Geef X (horizontaal waar) : ");
        x = kb.nextInt()-1;
        System.out.println("Geef Y (verticaal waar) : ");
        y = kb.nextInt()-1;

        game.play(game.getPlayablePieces().getPieces().get(p),new Point(x,y));
        }*/
        Application.launch(args);
    }

}
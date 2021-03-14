package blockgame;

import blockgame.model.Game;
import blockgame.view.mainMenu.MainMenuPresenter;
import blockgame.view.mainMenu.MainMenuView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * @author Maxim Derboven
 * @version 1.0 9/12/2020 18:44
 */
public class Main extends Application {

    private static final char FILE_SEPARATOR = System.getProperties().getProperty("file.separator").charAt(0);

    @Override
    public void start(Stage primaryStage) throws Exception {
        Game model = new Game();
        MainMenuView mmv = new MainMenuView();
        MainMenuPresenter mmp = new MainMenuPresenter(model, mmv);
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
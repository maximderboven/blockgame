package blockgame;

import blockgame.model.Game;
import blockgame.view.identificationScreen.LoginPresenter;
import blockgame.view.identificationScreen.LoginView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * @author Maxim Derboven
 * @version 1.0 9/12/2020 18:44
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Game model = new Game();
        LoginView lv = new LoginView();
        LoginPresenter lp = new LoginPresenter(model, lv);

        primaryStage.setScene(new Scene(lv));
        primaryStage.setTitle("Authorization");
        primaryStage.setResizable(false);
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
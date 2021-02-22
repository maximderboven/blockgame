package blockgame;

import blockgame.model.Game;
import blockgame.view.registerScreen.RegisterPresenter;
import blockgame.view.registerScreen.RegisterView;
import blockgame.view.settingsscreen.SettingsPresenter;
import blockgame.view.settingsscreen.SettingsView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.text.View;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author Maxim Derboven
 * @version 1.0 9/12/2020 18:44
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Game model = new Game();
        SettingsView view1 = new SettingsView();
        RegisterView view = new RegisterView();
        new RegisterPresenter(model, view);
        primaryStage.setScene(new Scene(view));
        primaryStage.show();
    }

    private static blockgame.model.Game game;
    //private static final Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args) {

        game = new blockgame.model.Game(); //init
        Application.launch(args);

    }
}
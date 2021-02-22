package blockgame;

import blockgame.model.Game;
import blockgame.view.settingsscreen.SettingsPresenter;
import blockgame.view.settingsscreen.SettingsView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Maxim Derboven
 * @version 1.0 9/12/2020 18:44
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Game model = new Game();

        // Settingsview
        SettingsView view1 = new SettingsView();
        new SettingsPresenter(model, view1);
        primaryStage.setScene(new Scene(view1));
        primaryStage.setResizable(true);
        primaryStage.setTitle("test");
        primaryStage.show();

        // AboutAlert
        // AboutAlert about = new AboutAlert();
        // about.showAndWait();

    }

    private static blockgame.model.Game game;
    //private static final Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args) {
        game = new blockgame.model.Game(); //init
        Application.launch(args);
    }

}
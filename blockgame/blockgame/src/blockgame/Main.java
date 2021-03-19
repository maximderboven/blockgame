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
    public void start(Stage primaryStage) {
        Game model = new Game();
        MainMenuView mmv = new MainMenuView();
        new MainMenuPresenter(model, mmv);
        Scene scene = new Scene(mmv);
        primaryStage.setScene(scene);
        primaryStage.setTitle("KdG Block Game");
        primaryStage.setResizable(false);
        primaryStage.setWidth(900);
        primaryStage.setHeight(675);
        primaryStage.getIcons().add(new Image(FILE_SEPARATOR + "images" + FILE_SEPARATOR + "logo.png"));
        primaryStage.show();
    }
    public static void main(String[] args) {
        Application.launch(args);
    }
}
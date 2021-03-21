package blockgame;

import blockgame.model.Game;
import blockgame.view.start.StartScreenPresenter;
import blockgame.view.start.StartScreenView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;

/**
 * @author Maxim Derboven
 * @version 1.0 9/12/2020 18:44
 */
public class Main extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }
    @Override
    public void start(Stage primaryStage) {
        Game model = new Game();
        StartScreenView ssv = new StartScreenView();
        new StartScreenPresenter(model, ssv);
        Scene scene = new Scene(ssv);
        primaryStage.setScene(scene);
        primaryStage.setTitle("KdG Block Game");
        primaryStage.setResizable(false);
        primaryStage.setWidth(900);
        primaryStage.setHeight(675);
        primaryStage.getIcons().add(new Image(File.separator + "images" + File.separator + "logo.png"));
        primaryStage.show();
    }
}
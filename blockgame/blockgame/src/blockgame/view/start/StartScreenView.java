package blockgame.view.start;

import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class StartScreenView extends BorderPane {

    private ProgressIndicator timeProgress;
    private StartScreenTransition trans;
    private HBox hbox;

    public StartScreenView() {
        initialiseNodes();
        layoutNodes();
        animate();
    }

    private void initialiseNodes() {
        this.timeProgress = new ProgressBar();
        this.hbox = new HBox();
    }

    private void layoutNodes() {
        this.getStylesheets().add("/stylesheets/splashscreen.css");
        ImageView centralImage;
        centralImage = new ImageView(new Image("/images/loadingscherm.png"));
        centralImage.setSmooth(true);
        hbox.getChildren().addAll(centralImage,timeProgress);
        this.setCenter(hbox);
    }

    ProgressIndicator getTimeProgress() {
        return (timeProgress);
    }

    StartScreenTransition getTransition() {
        return trans;
    }

    private void animate() {
        trans = new StartScreenTransition(this, 6);
        trans.play();
    }

}

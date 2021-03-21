package blockgame.view.start;

import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class StartScreenView extends BorderPane {

    private ProgressIndicator timeProgress;
    private StartScreenTransition trans;

    public StartScreenView() {
        initialiseNodes();
        layoutNodes();
        animate();
    }

    private void initialiseNodes() {
        this.timeProgress = new ProgressBar();
    }

    private void layoutNodes() {
        this.getStylesheets().add("/stylesheets/splashscreen.css");
        ImageView centralImage;
        centralImage = new ImageView(new Image("/images/load.png"));
        centralImage.setSmooth(true);
        this.setCenter(centralImage);
        this.setBottom(timeProgress);
        timeProgress.setPadding(new Insets(10));
        timeProgress.setPrefWidth(900);
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

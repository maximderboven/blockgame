package blockgame.view.highscores;

import javafx.geometry.Insets;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;


/**
 * @author Maxim Derboven
 * @version 1.0 7/03/2021 16:35
 */
public class HighscoresView extends BorderPane {

    // Attributen
    private CategoryAxis namen;
    private NumberAxis scores;
    private BarChart<String, Number> chart;
    private XYChart.Series<String, Number> info = new XYChart.Series<>();
    private ImageView imgClose;

    // Constructor
    public HighscoresView() {
        initialiseNodes();
        layoutNodes();
    }

    // Initialise nodes
    private void initialiseNodes() {
        namen = new CategoryAxis();
        scores = new NumberAxis();
        chart = new BarChart<>(namen, scores);
        imgClose = new ImageView("/images/menu/close.png");
    }

    // Layout nodes
    private void layoutNodes() {
        super.setPadding(new Insets(90));
        super.setRight(imgClose);
        super.setCenter(chart);
        imgClose.setFitHeight(40);
        imgClose.setFitWidth(40);
        setBackground(new Background(new BackgroundImage(new Image("/images/menu/bgstandard.png"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
        chart.setLegendVisible(false);
        chart.setVerticalGridLinesVisible(false);
        chart.setHorizontalGridLinesVisible(false);
        chart.setTitle("Highscores");
    }

    // Getters
    BarChart<String, Number> getChart() {
        return chart;
    }

    XYChart.Series<String, Number> getInfo() {
        return info;
    }

    ImageView getImgClose() {
        return imgClose;
    }
}

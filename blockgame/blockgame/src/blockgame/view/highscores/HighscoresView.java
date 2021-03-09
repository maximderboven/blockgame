package blockgame.view.highscores;

import blockgame.view.menu.MenuView;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.BorderPane;


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
    private MenuView mv;

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
        mv = new MenuView();
    }

    // Layout nodes
    private void layoutNodes() {
        super.setTop(mv);
        super.setCenter(chart);
        chart.setLegendVisible(false);
        chart.setVerticalGridLinesVisible(false);
        chart.setTitle("Scores");
    }

    BarChart<String, Number> getChart() {
        return chart;
    }

    public XYChart.Series<String, Number> getInfo() {
        return info;
    }

    public MenuView getMv() {
        return mv;
    }
}

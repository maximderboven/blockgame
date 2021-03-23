package blockgame.view.start;

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.util.Duration;

public class StartScreenTransition extends Transition {
    /**
     * Hulp klasse voor transitions (loading bar & handeling van de fades)
     */
    private final StartScreenView view;

    public StartScreenTransition(StartScreenView view, int maxDuration) {
        this.view = view;
        this.setCycleDuration(Duration.seconds(maxDuration));
        this.setCycleCount(1);
        this.setInterpolator(Interpolator.LINEAR);
        //Laden met fadeIn effect
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(3), view);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);
        fadeIn.setCycleCount(1);
        //fadeout override de transition time van de totale load bar (kan ook de setToValue aanpassen)
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(5), view);
        fadeOut.setFromValue(1);
        fadeOut.setToValue(0);
        fadeOut.setCycleCount(1);

        fadeIn.play();

        //After fade in, start fade out
        fadeIn.setOnFinished((e) -> {
            fadeOut.play();
        });
    }

    @Override
    protected void interpolate(double frac) {
        this.view.getTimeProgress().setProgress(frac);
    }
}

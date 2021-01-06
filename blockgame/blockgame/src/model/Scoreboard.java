package model;

/**
 * @author Maxim Derboven
 * @version 1.0 9/12/2020 18:45
 * @description Het scoreboard is het beheer van de scores en het tonen van het scoreboard voor de huidige speler.
 */
public class Scoreboard {
    /**
     * ATTRIBUTEN
     * score = De huidige score behoort tot de game zolang het spel bezig is. Wanneer het spel afgelopen is wordt de score toegewezen (indien de highscore overschreden is) aan de huidige speler.
     */
    private int score;
    private Player player;


    /**
     * CONSTRUCTOR
     */
    public Scoreboard(Player player) {
        this.player = player;
        this.score = 0;
    }


    /**
     * Hiermee wordt het scoreboard weergegeven
     */

    @Override
    public String toString() {
        return "--------\nScore: " + score + "\nHighscore: " + player.getHighscore() + "\n--------";
    }


    /**
     * Score/highscore updaten tijdens het spel
     */
    public void updateScore(int score) {
        this.score += score;
        if (this.score > player.getHighscore()) {
            player.setHighscore(this.score);
        }
    }


    /**
     * Highescore updaten na het spel
     */
    public void updateHighscore() {
        if (this.score > player.getHighscore()) {
            player.updateHighscore();
        }
    }


    /**
     * @return huidig behaalde score
     */
    public int getScore() {
        return score;
    }
}

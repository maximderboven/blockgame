package blockgame.model;

/**
 * @author Maxim Derboven
 * @version 1.0 9/12/2020 18:45
 * @since 1.0
 * @description Het scoreboard is het beheer van de scores en het tonen van het scoreboard voor de huidige speler.
 */
public class Scoreboard {
    /**
     * Wanneer het spel afgelopen is wordt de score toegewezen (indien de highscore overschreden is) aan de huidige speler.
     * score        De huidige score behoort tot de game zolang het spel bezig is.
     * player       De huidige speler.
     */
    private int score;
    private Player player;


    /**
     * Constructor:
     * @param player Object van de huidige speler.
     */
    public Scoreboard(Player player) {
        this.player = player;
        this.score = 0;
    }


    /**
     * Hiermee wordt het scoreboard weergegeven.
     * @return String Geformatteerde scoreboard string.
     */
    @Override
    public String toString() {
        return "--------\nScore: " + score + "\nHighscore: " + player.getHighscore() + "\n--------";
    }


    /**
     * Score/highscore updaten tijdens het spel.
     * @param score De huidige score van de speler.
     */
    public void updateScore(int score) {
        this.score += score;
        if (this.score > player.getHighscore()) {
            player.setHighscore(this.score);
        }
    }


    /**
     * Toont de huidige score.
     * @return int Huidig behaalde score.
     */
    public int getScore() {
        return score;
    }
}

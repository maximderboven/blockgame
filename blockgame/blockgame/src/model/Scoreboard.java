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
     * ?
     */
    public Scoreboard(Player player) {
        this.player = player;
        this.score = 0;
    }


    /**
     * Hiermee wordt het scoreboard weergegeven
     */

    /*
    public void draw() {
        System.out.println("--------\nScore: " + score + "\nHighscore: " + player.getHighscore() + "\n--------");
    }*/

    @Override
    public String toString() {
        return "--------\nScore: " + score + "\nHighscore: " + player.getHighscore() + "\n--------";
    }


    /**
     * Score updaten
     */
    public void updateScore(int score) {
        this.score += score;
        // Highscore dynamisch updaten
        //updateHighscore();
    }

    /**
     * update indien nodig de highscore wanneer het spel gedaan is
     */
    public void updateHighscore() {
        if (score > player.getHighscore()) {
            player.setHighscore(score);
        }
    }

    public int getScore() {
        return score;
    }
}

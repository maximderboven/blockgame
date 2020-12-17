package model;

/**
 * @author Maxim Derboven
 * @version 1.0 9/12/2020 18:45
 * @description Het scoreboard is het beheer van de scores en het tonen van het scoreboard voor de huidige speler.
 */
public class Scoreboard {
    // Huidige score van de speler
    // De huidige score behoort tot de game zolang het spel bezig is. Wanneer het spel afgelopen is wordt de score toegewezen (indien de highscore overschreden is) aan de huidige speler.
    private int score;
    //de Huidige speler om de scores ervan te beheren:
    private Player player;

    public Scoreboard(Player player) {
        this.player = player;
        this.score = 0;
    }

    //Hiermee wordt het scoreboard weergegeven
    /*
    -------------
    Score: x
    Highscore: x
    -------------
     */

    /*
    public void draw() {
        System.out.println("--------\nScore: " + score + "\nHighscore: " + player.getHighscore() + "\n--------");
    }*/

    @Override
    public String toString() {
        System.out.println("--------\nScore: " + score + "\nHighscore: " + player.getHighscore() + "\n--------");
    }

    //Geeft de behaalde score van deze poging weer.
    public int getScore() {
        return score;
    }
    //Verander de highscore van de speler
    public void setHighscore(int highscore) {
        player.setHighscore(highscore);
    }

    public void addScore(int score) {
        this.score += score;
    }

    public void updateScore() {
        //Wanneer het spel gedaan is, kijken of score groter is dan highscore
        if (score > player.getHighscore()) {
            player.setHighscore(score);
        }
    }
}

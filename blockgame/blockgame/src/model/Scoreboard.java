package model;

/**
 * @author Maxim Derboven
 * @version 1.0 9/12/2020 18:45
 */
public class Scoreboard {
    private int highscore;
    private int score;

    public Scoreboard(int highscore) {
        this.highscore = highscore;
        this.score = 0;
    }

    public void draw() {
        System.out.println("--------\nScore: " + score + "\nHighscore: " + highscore + "\n--------");
    }

    public int getScore() {
        return score;
    }

    public void setHighscore(int highscore) {
        //
    }
}

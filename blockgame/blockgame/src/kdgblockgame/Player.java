package kdgblockgame;

/**
 * @author Maxim Derboven
 * @version 1.0 9/12/2020 18:44
 */
public class Player {
    private String name;
    private Scoreboard scoreboard;

    public Player(String name, Scoreboard scoreboard) {
        this.name = name;
        this.scoreboard = scoreboard;
    }
}

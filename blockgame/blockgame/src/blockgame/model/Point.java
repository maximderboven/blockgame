package blockgame.model;

/**
 * @author Maxim Derboven & Alexie Chaerle
 * @version 1.0 17/02/2021 12:04
 * @since 2.0 (na feedback)
 * @description
 * Deze klasse vervangt 'java.awt.Point'. Enkel een x, y coord nodig.
 */

public class Point {
    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Get X
     * @return int x waarde
     */
    public int getX() {
        return x;
    }

    /**
     * Get Y
     * @return int y waarde
     */
    public int getY() {
        return y;
    }
}

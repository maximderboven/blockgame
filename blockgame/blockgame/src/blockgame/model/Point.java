package blockgame.model;

/**
 * @author Maxim Derboven & Alexie Chaerle
 * @version 1.0 17/02/2021 12:04
 * @since 1.0
 * @description
 * Deze klasse vervangt 'java.awt.Point'. Enkel een x, y coord nodig.
 */

public class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        if (x < 0) {
            //new exception zetten
        this.x = x;
        }
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        if (y < 0) {
            //new exception zetten
            this.y = y;
        }
    }
}

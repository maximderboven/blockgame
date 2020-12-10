package model;

import java.awt.*;

/**
 * @author Maxim Derboven
 * @version 1.0 9/12/2020 18:46
 */
public class Tile {
    private Color color;
    private Point point;
    private boolean used;

    public Tile(Point point) {
        this.point = point;
        this.used = false;
    }

    public Tile(Color color, Point point) {
        this.color = color;
        this.point = point;
        this.used = false;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Tile(boolean used) {
        this.used = used;
    }

    public boolean isUsed() {
        return used;
    }
}

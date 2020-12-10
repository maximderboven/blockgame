package model;

import java.awt.*;

/**
 * @author Maxim Derboven
 * @version 1.0 9/12/2020 18:45
 */
public enum Piece {
    KLEIN(2,Color.red,new Point[]{new Point(0, 0)}),
    MIDDEL(2,Color.red,new Point[]{new Point(0, 0),new Point(0,-1)}),
    GROOT(2,Color.red,new Point[]{new Point(0, 0),new Point(0,-1),new Point(0,+1)});

    private final int value;
    private final Color color;
    private final Point[] tiles;

    Piece(int value, Color color, Point[] tiles) {
        this.value = value;
        this.color = color;
        this.tiles = tiles;
    }
}
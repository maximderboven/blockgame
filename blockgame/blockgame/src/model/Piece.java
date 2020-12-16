package model;

import java.awt.*;

/**
 * @author Maxim Derboven
 * @version 1.0 9/12/2020 18:45
 * @description Deze klasse bevat alle soorten blokken die door de speler op het bord geplaatst kunnen worden
 */
public enum Piece {
    // De blokken bevatten een locatie die wordt vergeleken met de locatie op het bord. Zo worden de tegels op die plaatsen aangepast.
    KLEIN(1,Color.red,new Point[]{new Point(0, 0)}),
    MIDDEL(2,Color.red,new Point[]{new Point(0, 0),new Point(0,-1)}),
    GROOT(3,Color.red,new Point[]{new Point(0, 0),new Point(0,-1),new Point(0,+1)});

    private final int value;
    private final Color color;
    private final Point[] tiles;

    public Point[] getTiles() {
        return tiles;
    }

    public int getValue() {
        return value;
    }

    Piece(int value, Color color, Point[] tiles) {
        this.value = value;
        this.color = color;
        this.tiles = tiles;
    }

}
package model;

import java.awt.*;

/**
 * @author Maxim Derboven
 * @version 1.0 9/12/2020 18:45
 * @description Deze klasse bevat alle soorten blokken die door de speler op het bord geplaatst kunnen worden
 */
public enum Piece {

    /**
     * De blokken bevatten een locatie die wordt vergeleken met de locatie op het bord. Zo worden de tegels op die plaatsen aangepast.
     */
    // X
    EEN(1,Color.red,new Point[]{new Point(0, 0)}),

    // XX
    TWEE(2,Color.red,new Point[]{new Point(0, 0),new Point(0,-1)}),

    // XXX
    DRIE(3,Color.red,new Point[]{new Point(0, 0),new Point(0,-1),new Point(0,+1)}),

    // XX
    // XX
    VIER(4,Color.red,new Point[]{new Point(0, 0),new Point(0,-1),new Point(-1,0),new Point(-1,-1)}),

    // X
    // X
    // XX
    VIJF(4,Color.red,new Point[]{new Point(0, 0),new Point(0,-1),new Point(+1,0),new Point(+2,0)});


    /**
     * ATTRIBUTEN
     * value = hoeveel punten hij waard is
     * color = kleur dat deze blok heeft
     * tiles = Welke vorm adhv tegels die ten opzichte van punt 0x0 worden gelegd met Point
     */
    private final int value;
    private final Color color;
    private final Point[] tiles;


    /**
     * Construcor voor een Piece
     */
    Piece(int value, Color color, Point[] tiles) {
        this.value = value;
        this.color = color;
        this.tiles = tiles;
    }

    /**
     * Krijg de vorm van de blok
     */
    public Point[] getTiles() {
        return tiles;
    }


    /**
     * Krijg de waarde van de blok
     */
    public int getValue() {
        return value;
    }
}
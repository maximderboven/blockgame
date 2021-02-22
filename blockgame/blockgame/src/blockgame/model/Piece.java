package blockgame.model;

import java.awt.*;

/**
 * @author Maxim Derboven
 * @version 1.0 9/12/2020 18:45
 * @since 1.0
 * @description Deze klasse bevat alle soorten blokken die door de speler op het bord geplaatst kunnen worden
 * */
public enum Piece {

    /**
     * De blokken bevatten een locatie die wordt vergeleken met de locatie op het bord. Zo worden de tegels op die plaatsen aangepast.
     * */
    // Piece1x1 [X]
    PIECE1x1(1, Color.green, new Point[]{
            new Point(0, 0)}),

    // Piece1x2 [X][ ]
    PIECE1x2(1, Color.green, new Point[]{
            new Point(0, 0),
            new Point(0, 1)}),

    // Piece1x3 [ ][X][ ]
    PIECE1x3(2, Color.green, new Point[]{
            new Point(0, -1),
            new Point(0, 0),
            new Point(0, 1),}),

    // Piece1x4 [ ][X][ ][ ]
    PIECE1x4(2, Color.green, new Point[]{
            new Point(0, -1),
            new Point(0, 0),
            new Point(0, 1),
            new Point(0, 2)}),

    // Piece1x5 [ ][ ][X][ ][ ]
    PIECE1x5(3, Color.green, new Point[]{
            new Point(0, -2),
            new Point(0, -1),
            new Point(0, 0),
            new Point(0, 1),
            new Point(0, 2)}),

    // Piece2x1
    // [X]
    // [ ]
    PIECE2x1(1, Color.green, new Point[]{
            new Point(0, 0),
            new Point(1, 0)}),

    // Piece2x2
    // [X][ ]
    // [ ][ ]
    PIECE2x2(1, Color.green, new Point[]{
            new Point(0, 0),
            new Point(0, 1),
            new Point(1, 0),
            new Point(1, 1)}),

    // Piece3x1
    // [ ]
    // [X]
    // [ ]
    PIECE3x1(2, Color.green, new Point[]{
            new Point(-1, 0),
            new Point(0, 0),
            new Point(1, 0)}),

    // Piece3x3
    // [ ][ ][ ]
    // [ ][X][ ]
    // [ ][ ][ ]
    PIECE3x3(4, Color.green, new Point[]{
            new Point(-1, -1),
            new Point(-1, 0),
            new Point(-1, 1),
            new Point(0, -1),
            new Point(0, 0),
            new Point(0, 1),
            new Point(1, -1),
            new Point(1, 0),
            new Point(1, 1)}),

    // Piece4x1
    // [ ]
    // [X]
    // [ ]
    // [ ]
    PIECE4x1(2, Color.green, new Point[]{
            new Point(-1, 0),
            new Point(0, 0),
            new Point(1, 0),
            new Point(2, 0)}),

    // PieceBigL
    // [ ][ ][X]
    //       [ ]
    //       [ ]
    PIECEBIGL(4, Color.green, new Point[]{
            new Point(0, -2),
            new Point(0, -1),
            new Point(0, 0),
            new Point(1, 0),
            new Point(2, 0)}),

    // PieceHorJ
    // [ ]
    // [X][ ][ ]
    PIECEHORJ(3, Color.green, new Point[]{
            new Point(-1, 0),
            new Point(0, 0),
            new Point(0, 1),
            new Point(0, 2)}),

    // PieceS
    //    [X][ ]
    // [ ][ ]
    PIECES(4, Color.green, new Point[]{
            new Point(1, -1),
            new Point(1, 0),
            new Point(0, 0),
            new Point(0, 1)}),

    // PieceSmallL
    // [X][ ]
    // [ ]
    PIECESMALLL(2, Color.green, new Point[]{
            new Point(0, 0),
            new Point(0, 1),
            new Point(0, 1)}),

    // PieceT
    //    [ ]
    // [ ][X][ ]
    PIECET(3, Color.green, new Point[]{
            new Point(-1, 0),
            new Point(0, -1),
            new Point(0, 0),
            new Point(0, 1)}),

    // PieceVertJ
    // [X][ ]
    // [ ]
    // [ ]
    PIECEVERTJ(3, Color.green, new Point[]{
            new Point(0, 0),
            new Point(0, 1),
            new Point(1, 0),
            new Point(2, 0)});


    /**
     * value          Hoeveel punten hij waard is.
     * color          Kleur dat deze blok heeft.
     * tiles          Welke vorm a.d.h.v. tegels die ten opzichte van punt 0x0 worden gelegd met Point.
     * */
    private final int value;
    private final Color color;
    private final Point[] blockTiles;


    /**
     * Constructor voor een Piece.
     * @param value Hoeveel punten het blok waard zal zijn.
     * @param color Kleur dat deze blok zal hebben.
     * @param tiles Welke vorm a.d.h.v. tegels die ten opzichte van punt 0x0 worden gelegd met Point.
     * */
    Piece(int value, Color color, Point[] tiles) {
        this.value = value;
        this.color = color;
        this.blockTiles = tiles;
    }


    /**
     * @return Point[] De vorm/locaties van de blocktiles van de blok.
     * */
    public Point[] getTiles() {
        return blockTiles;
    }


    /**
     * @return int De waarde van de blok.
     * */
    public int getValue() {
        return value;
    }
}
package model;

import java.awt.*;

/**
 * @author Maxim Derboven & Alexie Chaerle
 * @version 1.0 9/12/2020 18:44
 * @description Deze klasse bevat het spelbord. Het bord is een 2 dimensional array die bestaat uit allemaal tegels (klasse Tile). De speler plaats hier blokken op.
 * De taak van deze klasse is om het bord te beheren na het plaatsen van de blokken maw het weghalen van de rijen of volle kolommen en aan scoreboard vragen om de score te verhogen.
 */

public class Board {

    /**
     * Attributen
     * grid[][] - Grid van tegels
     * size - Grootte van het spelbord.
     * DEFAULT_COLOR - Standaard kleur van de tegels op het bord.
     * BASE_POINTS - Standaard punten voor het vervolledigen van een rij of kolom.
     */
    private Tile grid[][];
    private int size;
    public final Color DEFAULT_COLOR = Color.black;
    private final int BASE_POINTS = 5;


    /**
     * Constructors
     * Constructor met size van het bord x by y
     * Constructor vult de grid met instanties van Tile
     */
    public Board(int size) {
        this.size = size;
        this.grid = new Tile[size][size];
        fillBoard();
    }
    public Board() {
        this.size = 10;
        this.grid = new Tile[size][size];
        fillBoard();
    }


    /**
     * Vult het bord met tiles
     */
    private void fillBoard() {
        this.grid = new Tile[size][size];
        for(int i = 0; i < size; i++)
        {
            for(int j = 0; j < size; j++)
            {
                grid[i][j] = new Tile(DEFAULT_COLOR,new Point(i,j));
            }
        }
    }

    /**
    * @return Het bord wordt weergegeven
    * de toStrings van de tiles worden geprint.
    */
    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {
                s += grid[i][j];
            }
            s += "\n";
        }
        return s;
    }


    /**
     * Plaatste een blokje meegegeven blokje op het bord op het opgegeven punt.
     * @return boolean of de move succesvol was.
     */
    public boolean Move(Piece piece, Point point) {
        return placeBlock(piece, point);
    }


    /**
     * @return boolean of de plaats vrij is voor dat soort blok op point point.
     */
    private boolean isFree(Piece piece, Point point) {
        for (Point p : piece.getTiles()) {
            int r = point.x + p.x;
            int c = point.y + p.y;
            if (!((r >= 0 && r < size) && (c >= 0 && c < size)) || grid[r][c].isUsed()) {
                return false;
            }
        }
        return true;
    }


    /**
     * Plaats een block op het speelvel
     * @return of de tiles succesvol op used gezet zijn
     */
    private boolean placeBlock(Piece piece, Point point) {
        if (isFree(piece,point)) {
            for (Point p : piece.getTiles()) {
                int r = point.x + p.x;
                int c = point.y + p.y;
                grid[r][c].setUsed(true);
            }
            return true;
        } else {
            return false;
        }
    }


    /**
     * @return of het mogelijk is om de blok eender waar te plaatsen
     */
    public boolean isPossible(Piece piece) {
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                if(isFree(piece, new Point(i,j))) {
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * Verwijdert alle horizontale en verticale rijen.
     * @return score verkregen door de verwijderde rijen of kollommen
     */
    public int clearLines() {
        int points = 0;
        boolean fullHor;
        boolean fullVert;

        for(int i = 0; i < size; i++) {
            fullHor = true;
            fullVert = true;
            for(int j = 0; j < size; j++) {
                if(!grid[i][j].isUsed()) {
                    fullHor = false;
                }
                if(!grid[j][i].isUsed()) {
                    fullVert = false;
                }
            }

            if(fullHor) {
                for(int j = 0; j < size; j++) {
                    points += BASE_POINTS;
                    grid[i][j].setMarkdelete(true);
                }
            }

            if(fullVert) {
                for(int j = 0; j < size; j++) {
                    points += BASE_POINTS;
                    grid[j][i].setMarkdelete(true);
                }
            }
        }


        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                if(grid[i][j].isMarkdelete()) {
                    grid[i][j].setUsed(false);
                }
            }
        }
        return points;
    }


    /**
     * Stelt de grootte van het speelveld in.
     */
    public void setSize(int size) {
        if (size > 4 && size < 100) {
            this.size = size;
            fillBoard();
        }
    }


    /**
     * Geeft de grootte van het speelveld terug.
     */
    public int getSize() {
        return size;
    }

}

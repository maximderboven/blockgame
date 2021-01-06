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
     * Attributen van Board
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
        for(int i = 0; i < size; i++)
        {
            for(int j = 0; j < size; j++)
            {
                grid[i][j] = new Tile(DEFAULT_COLOR,new Point(i,j));
            }
        }
    }
    public Board() {
        this.size = 10;
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
    * Hiermee wordt het bord weergegeven
    * waarbij true de gebruikte vakjes zijn
    */
    /*
    public void draw() {
        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {
                if (grid[i][j].isUsed()){
                    System.out.printf("%1s", "x");
                }else {
                System.out.printf("%1s", "o");
                }
            }
            System.out.println();
        }
    }
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
     */
    public boolean Move(Piece piece, Point point) {
        return placeBlock(piece, point);
    }


    /**
     * Controleren of de opgegeven locatie vrij is voor die gegeven blok
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
     * controleert of het nog mogelijk is om zetten te doen met de gegeven blok
     */
    public boolean isPossible(Piece piece) {
        //boolean isvalid = false;

        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                if(isFree(piece, new Point(i,j))) {
                    //isvalid = true;
                    //break;
                    return true;
                }
            }
            /*if(isvalid) {
                break;
            }*/
        }
        return false;
        //return isvalid;
    }


    /**
     * Geeft de grotte van het speelveld.
     */
    public int getSize() {
        return size;
    }


    /**
     * Verwijdert alle horizontale en verticale rijen.
     */
    public int clearLines() {
        int points = 0;
        boolean fullHor = false;
        boolean fullVert = false;

        for(int i = 0; i < size; i++) {
            fullHor = true;
            fullVert = true;

            //Zet full line to false if any of the tiles are unused;
            for(int j = 0; j < size; j++) {

                // Horizontal
                if(!grid[i][j].isUsed()) {
                    fullHor = false;
                }

                // Vertical
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

        // Deletes all tiles marked for deletion
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                if(grid[i][j].isMarkdelete()) {
                    grid[i][j].setUsed(false);
                }
            }
        }
        return points;
    }
}

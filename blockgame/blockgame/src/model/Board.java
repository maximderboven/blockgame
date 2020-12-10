package model;

import java.awt.*;

/**
 * @author Maxim Derboven
 * @version 1.0 9/12/2020 18:44
 */

public class Board {
    //Grid of game tiles
    private Tile grid[][];
    public int size;
    private Player player;
    public final Color DEFAULT_COLOR = Color.black;

    public Board(int size, Player player) {
        this.size = size;
        this.player = player;
        for(int i = 0; i < size; i++)
        {
            for(int j = 0; j < size; j++)
            {
                grid[i][j] = new Tile(DEFAULT_COLOR,new Point(i,j));
            }
        }
    }

    public Board(Player player) {
        this.grid = new Tile[10][10];
        this.size = 10;
        this.player = player;
        for(int i = 0; i < size; i++)
        {
            for(int j = 0; j < size; j++)
            {
                grid[i][j] = new Tile(DEFAULT_COLOR,new Point(i,j));
            }
        }
    }

    public void draw() {
        for (int i = 0; i < size; i++)//Cycles through rows
        {
            for (int j = 0; j < size; j++)//Cycles through columns
            {
                System.out.printf("%8b", grid[i][j].isUsed()); //prints value
            }
            System.out.println(); //Makes a new row
        }
    }

    public boolean isFinished() {
        //maken
        return false;
    }

    public void update() {
        //stappen uitwerken
    }

    public Player getPlayer() {
        return player;
    }
}

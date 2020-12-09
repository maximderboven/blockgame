package kdgblockgame;

/**
 * @author Maxim Derboven
 * @version 1.0 9/12/2020 18:44
 */

public class Board {
    //Grid of game tiles
    private Tile grid[][];
    public int size;
    private Player player;

    public Board(Tile[][] grid, int size, Player player) {
        this.grid = grid;
        this.size = size;
        this.player = player;
    }

    public Board(Player player) {
        this.grid = new Tile[10][10];
        this.size = 10;
        this.player = player;
    }

    public void draw() {
        for (int row = 0; row < size; row++)//Cycles through rows
        {
            for (int col = 0; col < size; col++)//Cycles through columns
            {
                System.out.printf("%5d", grid[row][col]); //prints value
            }
            System.out.println(); //Makes a new row
        }
    }
}

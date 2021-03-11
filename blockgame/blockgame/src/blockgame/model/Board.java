package blockgame.model;
import java.awt.*;

/**
 * @author Maxim Derboven & Alexie Chaerle
 * @version 1.0 9/12/2020 18:44
 * @since 1.0
 * @description
 * Deze klasse bevat het spelbord. Het bord is een 2 dimensional array die bestaat uit allemaal tegels (klasse Tile). De speler plaats hier blokken op.
 * De taak van deze klasse is om het bord te beheren na het plaatsen van de blokken maw het weghalen van de rijen of volle kolommen en aan scoreboard vragen om de score te verhogen.
 */

public class Board {

    /**
     * grid[][]          Grid van tegels.
     * size              Grootte van het spelbord.
     * DEFAULT_COLOR     Standaard kleur van de tegels op het bord.
     * BASE_POINT        Standaard punten voor het vervolledigen van een rij of kolom.
     */
    private Tile grid[][];
    private int size;
    private boolean draganddrop;
    public final Color DEFAULT_COLOR = Color.black;
    private final int BASE_POINTS = 5;


    /**
     * Constructor zet de size van het bord vast en vult het bord met met tiles.
     */
    public Board(int size) {
        this.size = size;
        this.draganddrop = true;
        this.grid = new Tile[size][size];
        fillBoard();
    }
    public Board() {
        this(10);
    }


    /**
     * Deze methode vult het bord met tiles.
     */
    private void fillBoard() {
        this.grid = new Tile[size][size];
        for(int x = 0; x < size; x++)
        {
            for(int y = 0; y < size; y++)
            {
                grid[x][y] = new Tile(DEFAULT_COLOR,new Point(x,y));
            }
        }
    }

    public Tile[][] getGrid() {
        return grid;
    }

    /**
     * Geeft de grootte van het speelveld terug.
     * @return  int   De grootte van het spelbord.
     */
    public int getSize() {
        return size;
    }

    public boolean isDraganddrop() {
        return draganddrop;
    }

    public void setDraganddrop(boolean draganddrop) {
        this.draganddrop = draganddrop;
    }

    /**
     * Stelt de grootte van het speelveld in.
     * En vult het bord opnieuw met tiles.
     * @param   size    De grootte waarin hij veranderd moet worden.
     */
    public void setSize(int size) {
        if (size > 4 && size < 100) {
            this.size = size;
            fillBoard();
        }
    }


    /**
     * Deze methode simuleert een "zet".
     * @param   piece   Het blokje dat geplaatst gaat worden.
     * @param   point   De locatie op het bord waar het geplaatst moet worden.
     * @return  boolean Dit geeft terug of de blok succesvol geplaatst is.
     */
    public boolean Move(Piece piece, Point point) {
        return placeBlock(piece, point);
    }


    /**
     * Deze methode kijkt na of er plaats is voor het betreffende piece op locatie point.
     * @param   piece   Het betreffende blokje
     * @param   point   De betreffende locatie
     * @return  boolean De plaats vrij is voor dat soort blok op de lcoatie.
     */
    public boolean isFree(Piece piece, Point point) {
        for (Point p : piece.getTiles()) {
            int r = point.getX() + p.getX();
            int c = point.getY() + p.getY();
            if (!((r >= 0 && r < size) && (c >= 0 && c < size)) || grid[r][c].isUsed()) {
                return false;
            }
        }
        return true;
    }


    /**
     * Plaatst een block op het speelvel
     * @param   piece   Het betreffende blokje
     * @param   point   De betreffende locatie
     * @return  boolean De tiles succesvol op used gezet zijn / block is geplaatst.
     */
    private boolean placeBlock(Piece piece, Point point) {
        if (isFree(piece,point)) {
            for (Point p : piece.getTiles()) {
                int r = point.getX() + p.getX();
                int c = point.getY() + p.getY();
                grid[r][c].setUsed(true);
            }
            return true;
        } else {
            return false;
        }
    }


    /**
     * Plaatst een block op het speelveld.
     * @param   piece   Het betreffende blokje
     * @return  boolean Mogelijk om de blok nog ergens te plaatsen
     */
    public boolean isPossible(Piece piece) {
        for(int x = 0; x < size; x++) {
            for(int y = 0; y < size; y++) {
                if(isFree(piece, new Point(x,y))) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Verwijderd alle horizontale en verticale rijen.
     * @return  int  Score verkregen door de verwijderde rijen of kolommen
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
     * Met de toString wordt het bord agedrukt adhv de tostrings van tiles.
     * @return  string   Het bord wordt teruggegeven
     * @see String
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {
                s.append(grid[i][j]);
            }
            s.append("\n");
        }
        return s.toString();
    }

}

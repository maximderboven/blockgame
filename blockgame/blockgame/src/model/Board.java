package model;

import java.awt.*;

/**
 * @author Maxim Derboven
 * @version 1.0 9/12/2020 18:44
 * @description Deze klasse bevat het spelbord. Het bord is een 2 dimensional array die bestaat uit allemaal tegels (klasse Tile). De speler plaats hier blokken op.
 * De taak van deze klasse is om het bord te beheren na het plaatsen van de blokken maw het weghalen van de rijen of volle kolommen en aan scoreboard vragen om de score te verhogen.
 */

public class Board {
    //Grid van tegels
    private Tile grid[][];
    //We maken de size private omdat deze anders aangepast kan worden en we willen er controle over.
    private int size;
    // de standaard kleur van de tiles op het board (kan in later versie eventueel een instelling worden)
    public final Color DEFAULT_COLOR = Color.black;
    // Scoreboard om aan te passen indien nodig
    private Scoreboard scoreboard;

    //bord met een andere grootte dan standaard 10
    public Board(int size, Scoreboard scoreboard) {
        this.size = size;
        this.grid = new Tile[size][size];
        //bord vullen met ongebruikte tegels
        for(int i = 0; i < size; i++)
        {
            for(int j = 0; j < size; j++)
            {
                grid[i][j] = new Tile(DEFAULT_COLOR,new Point(i,j));
            }
        }
        this.scoreboard = scoreboard;
    }
    //bord met standaard grootte
    public Board(Scoreboard scoreboard) {
        this.size = 10;
        this.grid = new Tile[size][size];
        //bord vullen met ongebruikte tegels
        for(int i = 0; i < size; i++)
        {
            for(int j = 0; j < size; j++)
            {
                grid[i][j] = new Tile(DEFAULT_COLOR,new Point(i,j));
            }
        }
        this.scoreboard = scoreboard;
    }
    //Hiermee wordt het bord weergegeven
    // waarbij true de gebruikte vakjes zijn
    /*
    false true  false
    false true  true
    false false false
     */

    public void draw() {
        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {
                System.out.printf("%8b", grid[i][j].isUsed());
            }
            System.out.println();
        }
    }

    //private methode om te controleren of de locatie vrij is (ext voor methode dropBlock)
    private boolean isFree(Piece piece, Point point) {
        //Controleer of de plek waar de blok eventueel zou gezet worden door de speler vrij is
        for (Point p : piece.getTiles()) {
            int r = point.x + p.x;
            int c = point.y + p.y;
            if (!((r >= 0 && r < size) && (c >= 0 && c < size)) || grid[r][c].isUsed()) {
                return false;
            }
        }
        return true;
    }

    public boolean dropBlock(Piece piece, Point point) {
        if (isFree(piece,point)) {
            //Plaats de block op het grid door middel van de locatie van de kleiner blokjes binnen de piece te vergelijken met de point (parameter) waar hij zou neerkomen.
            //Zet de Tegels used op true op die locatie in de 2 dimensionale array.
            //Maak gebruik van de array & setused van tiles etc.
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

    //controleert of het nog mogelijk is om zetten te doen met de gegeven blokken
    public boolean isPossible(Piece piece) {
        // wordt uigevoerd bij het random generaten van 3 blokken die de speler moet zetten en na een zet wordt het nogmaals uigevoerd.
        // De blok(ken) worden meegegeven met een parameter vanuit Game en hier gecontroleerd of deze nog geplaatst kunnen worden.
        // Zo Ja, dan kan het spel doorgaan: return true
        // Zo niet, dan heeft de speler geen mogelijkheden meer en is het gedaan: return false
        // Wanneer de speler nog meerdere blokken kan zetten is 1 van de blokken genoeg als true.

        boolean isvalid = false;

        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                if(isFree(piece, new Point(i,j))) {
                    isvalid = true;
                    break;
                }
            }
            if(isvalid) {
                break;
            }
        }

        return isvalid;
    }

    public int getSize() {
        return size;
    }
}

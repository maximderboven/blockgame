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

    //controleert of het nog mogelijk is om zetten te doen met de gegeven blokken
    public boolean isFinished() {
        //maken
        return false;
    }

    //laat de speler een zet doen.
    public void update() {
        //stappen uitwerken
    }
}

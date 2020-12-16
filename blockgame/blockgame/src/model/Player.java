package model;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;

/**
 * @author Maxim Derboven
 * @version 1.0 9/12/2020 18:44
 * @description De speler speelt het spel en speelt de blokken. Deze klasse bevat de gegevens van de huidige speler.
 */
public class Player {
    //op welk bord de speler speelt en blokken mag plaatsen
    private Board board;
    //De naam van de speler
    private String name;
    //Highscore van deze speler:
    private int highscore;

    //voor een nieuwe speler zonder highscore:
    public Player(String name, Board board) {
        this.name = name;
        this.highscore = 0;
        this.board = board;
    }

    //voor een speler die terugkeert:
    public Player(String name, int highscore, Board board) {
        this.name = name;
        this.highscore = highscore;
        this.board = board;
    }

    //naam verkrijgen voor bv scoreboard op te maken:
    public String getName() {
        return name;
    }

    //Highscore verkrijgen om te vergelijken met de nieuw behaalde score / weergeven
    public int getHighscore() {
        return highscore;
    }

    //nieuwe highscore neerzetten indien verbroken (ook in file aanpassen)
    public void setHighscore(int highscore) {
        this.highscore = highscore;
        //Update  highschore method
        updateHighscore();
    }

    //ZIE GAME VOOR EVT INGO OVER VOID TOV BOOL
    public boolean play(Piece piece, Point point) {
        return board.placeBlock(piece, point);
        //Hiermee plaats je een mogelijke blok (parameter) van de drie blokken in het board van deze klasse
    }

    public void updateHighscore() {
        try {
            BufferedReader file = new BufferedReader(new FileReader("../blockgame/blockgame/src/model/resources/highscores.txt"));
            StringBuilder buffer = new StringBuilder();
            String line;

            while ((line = file.readLine()) != null) {
                if (line.contains(this.name)){
                    // If line contains the current player's name:
                    String[] credentials = line.split(":");
                    line = credentials[0] + ":" + credentials[1] + ":" + this.getHighscore();
                }
                buffer.append(line);
                buffer.append('\n');
            }
            file.close();

            // Write new line to highscores.txt
            FileOutputStream fileOut = new FileOutputStream("../blockgame/blockgame/src/model/resources/highscores.txt");
            fileOut.write(buffer.toString().getBytes());
            fileOut.close();

        } catch (Exception e) {
            System.out.println("File not found");
        }
    }
}
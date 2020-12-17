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
    /**
     * ATTRIBUTEN
     * naam = naam van de speler
     * highscore = highscore van deze speler
     */
    private String name;
    private int highscore;

    /**
     * CONSTRUCTORS
     * Nieuwe speler zonder highscore en een speler met highscore
     */
    public Player(String name) {
        this.name = name;
        this.highscore = 0;
    }

    public Player(String name, int highscore) {
        this.name = name;
        this.highscore = highscore;
    }


    /**
     * huidige highscore verkrijgen om te vergelijken met de nieuw behaalde score / weergeven
     */
    public int getHighscore() {
        return highscore;
    }


    /**
     * nieuwe highscore neerzetten indien verbroken (ook in file aanpassen)
     */
    public void setHighscore(int highscore) {
        this.highscore = highscore;
        updateHighscore();
    }


    /**
     * Update de highscore in de file
     */
    private void updateHighscore() {
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

    /**
     * Geeft de naam van de speler weer
     */
    @Override
    public String toString() {
        return String.format(this.name);
    }
}
package model;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;

/**
 * @author Maxim Derboven
 * @version 1.0 9/12/2020 18:44
 * @description De speler speelt het spel. Deze klasse bevat de gegevens van de huidige speler.
 */
public class Player {
    /**
     * ATTRIBUTEN
     * naam = naam van de speler
     * highscore = highscore van deze speler
     */
    private String username;
    private String password;
    private int highscore;

    /**
     * CONSTRUCTORS
     * Nieuwe speler zonder highscore en een speler met highscore
     */
    public Player(String username, String password, int highscore){
        this.username = username;
        this.password = password;
        this.highscore = highscore;
    }

    public Player(String username, String password){
        this(username, password, 0);
    }


    /**
     * @return huidige highscore verkrijgen
     */
    public int getHighscore() {
        return highscore;
    }


    /**
     * nieuwe highscore zetten (tijdens game)
     */
    public void setHighscore(int highscore) {
        this.highscore = highscore;
    }


    /**
     * @return password van de user
     */
    public String getPassword() {
        return password;
    }

    /**
     * Geeft de naam van de speler weer
     */
    @Override
    public String toString() {
        return String.format(this.username);
    }

    public String getUsername() {
        return username;
    }
}
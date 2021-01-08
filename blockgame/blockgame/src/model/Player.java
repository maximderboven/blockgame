package model;


/**
 * @author Maxim Derboven
 * @version 1.0 9/12/2020 18:44
 * @since 1.0
 * @description De speler speelt het spel. Deze klasse bevat de gegevens van de huidige speler.
 * */
public class Player {
    /**
     * naam          Naam van de speler.
     * password      Wachtwoord van de speler.
     * highscore     Highscore van deze speler.
     * */
    private String username;
    private String password;
    private int highscore;


    /**
     * Constructor mer 3 parameters.
     * Nieuwe speler met highscore.
     * @param username Naam.
     * @param password Wachtwoord.
     * @param highscore Highscore.
     * */
    public Player(String username, String password, int highscore){
        this.username = username;
        this.password = password;
        this.highscore = highscore;
    }


    /**
     * Constructor mer 3 parameters.
     * Speler zonder highscore.
     * @param username Naam.
     * @param password Wachtwoord.
     * */
    public Player(String username, String password){
        this(username, password, 0);
    }


    /**
     * @return int Huidige highscore verkrijgen.
     * */
    public int getHighscore() {
        return highscore;
    }


    /**
     * Nieuwe highscore zetten (tijdens game).
     * @param highscore Highscore.
     * */
    public void setHighscore(int highscore) {
        this.highscore = highscore;
    }


    /**
     * Geeft het wachtwoord van de gebruiker
     * @return String Wachtwoord van de user.
     * */
    protected String getPassword() {
        return password;
    }


    /**
     * Geeft de naam van de speler weer (ToString() methode).
     * @return String Naam van de speler.
     * */
    @Override
    public String toString() {
        return this.username;
    }


    /**
     *  Geeft de naam van de speler weer
     * @return String Naam van de speler.
     * */
    public String getUsername() {
        return username;
    }
}
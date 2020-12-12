package model;

import java.awt.*;

/**
 * @author Maxim Derboven
 * @version 1.0 9/12/2020 18:44
 * @description De speler speelt het spel en speelt de blokken. Deze klasse bevat de gegevens van de huidige speler.
 */
public class Player {
    //op welk bord de speler speelt en blokken mag plaatsen
    private Board board;
    private String name;
    private int highscore;

    //voor een nieuwe speler zonder highscore:
    public Player(String name) {
        this.name = name;
    }

    //voor een speler die terugkeert:
    public Player(String name, int highscore) {
        this.name = name;
        this.highscore = highscore;
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
    }

    //ZIE GAME VOOR EVT INGO OVER VOID TOV BOOL
    public void play(Piece piece, Point point) {
        board.dropBlock(piece, point);
        //Hiermee plaats je een mogelijke blok (parameter) van de drie blokken in het board van deze klasse
    }
}
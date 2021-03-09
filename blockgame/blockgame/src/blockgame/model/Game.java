package blockgame.model;

import java.util.Random;

/**
 * @author Maxim Derboven & Alexie Chaerle
 * @version 1.0 9/12/2020 18:44
 * @description Dit is het beheer van het spel, deze klasse leidt de andere klasse die deel uitmaken van het spel.
 * @since 1.0
 */

public class Game {
    /**
     * player          Welke speler er gaat spelen.
     * scoreboard      Welk scoreboard er gebruikt gaat worden.
     * playeblePieces  De speelblokken.
     * board           Welk bord er gebruikt wordt.
     * am              User beheer voor gebruikers te registreren en in te loggen
     * random          Random
     */
    private Player player;
    private Scoreboard scoreboard;
    private PlayablePieces playablePieces;
    private Board board;
    private final FileManagement am = new FileManagement();


    /**
     * Bij het aanmaken van een nieuwe in de constructor het spel starten.
     * Deze constructor omvat het hele spelverloop
     */
    public Game() {
        this.board = new Board(10);
        this.playablePieces = new PlayablePieces(3);
    }


    /**
     * Controleert of het spel gedaan is a.d.h.v. het bord dat gaat kijken of een van de blokken nog geplaatst kan worden.
     *
     * @return boolean True als een zet mogelijk is, False als het niet mogelijk is.
     */
    public boolean isPossible() {
        for (Piece piece : playablePieces.getPieces()) {
            if (board.isPossible(piece)) {
                return true;
            }
        }
        return false;
    }


    /**
     * Play doet een spelzet op het bord.
     */
    public void play(Piece piece, Point point) {
        if (board.Move(piece, point)) {
            scoreboard.updateScore(piece.getValue() + board.clearLines());
            playablePieces.remove(piece);
            playablePieces.newPieces(scoreboard.getScore());
        }
    }

    /**
     * Gebruiker registreren.
     *
     * @param username Gebruikersnaam.
     * @param password Wachtwoord.
     * @throws Exception Als de gegevens incorrect zijn.
     */
    public void register(String username, String password) throws Exception {
        this.player = am.register(username, password);
    }


    /**
     * Gebruiker laten aanmelden.
     *
     * @param lusername Gebruikersnaam.
     * @param lpassword Wachtwoord.
     * @throws Exception Als de gegevens incorrect zijn.
     */
    public void login(String lusername, String lpassword) throws Exception {
        this.player = am.login(lusername, lpassword);
        this.scoreboard = new Scoreboard(player);
    }


    /**
     * @return de instantie van de klasse PlayablePieces.
     */
    public PlayablePieces getPlayablePieces() {
        return playablePieces;
    }


    /**
     * @return de instantie van de klasse Board.
     */
    public Board getBoard() {
        return board;
    }


    /**
     * @return de instantie van de klasse FileManagement.
     */
    public FileManagement getAm() {
        return am;
    }

    public Player getPlayer() {
        return player;
    }

    public Scoreboard getScoreboard() {
        return scoreboard;
    }
}

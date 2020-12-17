package model;

import java.awt.*;
import java.util.Random;
import java.util.Scanner;

/**
 * @author Maxim Derboven & Alexie Chaerle
 * @version 1.0 9/12/2020 18:44
 * @description Dit is het beheer van het spel, deze klasse leid de andere klasse die deel uitmaken van het spel.
 * start van het spel
 *
 *     Volgorde;
 *     1. Inloggen of registreren
 *     2. op basis van de gegevens een speler aanmaken
 *     3. Bord maken
 *     4. Scoreboard maken
 *     5. Begin situatie schetsen
 *     6. 3 random blokken geven
 *     6.1 Kijken of de blokken nog passen anders is het gedaan
 *     7. De speler een blok laten kiezen
 *     8. Kijken of de blok past waar de speler hem wilt zetten
 *     9. De Tegels op locatie used zetten
 *     10. Herhaal 6.1 voor de overige 2 blokken
 *     11. Herhaal 6 tot een van de blokken niet meer past.
 *
 */

public class Game {
    /**
     * ATTRIBUTEN:
     * Welke speler er gaat spelen:
     * Welk scoreboard er gebruikt gaat worden:
     * Random voor de console aplicatie
     * Zetten die de speler gaat doen
     * Welk bord er gebruikt wordt:
     * User beheer voor gebruiker te registreren en in te loggen
    */
    private Player player;
    private Scoreboard scoreboard;
    private Random random = new Random();
    private PlayablePieces playablePieces;
    private Board board;
    private Management am = new Management();


    /**
     * Bij het aanmaken van een nieuwe in de constructor het spel starten.
     * Deze constructor omvat het hele spelverloop
     */
    public Game() {
        if (this.login()) {
            this.board = new Board(3);
            this.scoreboard = new Scoreboard(player);
            this.playablePieces = new PlayablePieces();
            start();
        }
    }


    /**
     * Controleert of het spel gedaan is adhv het bord dat gaat kijken of een van de blokken nog geplaatst kan worden
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
     * Start van het spel na het initialiseren van al de nodige klasse (objecten aangemaakt)
     */
    public void start() {
        /* Voor het spel start: */
        showHUD();

        /* Tijdens het spel: */
        while (isPossible()) {
            play();
            showHUD();
            playablePieces.newPieces();


            try {
                Thread.sleep(800);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        /* Na het spel: */
        scoreboard.updateHighscore();
        System.out.println("Game over: (╯°□°）╯︵ ┻━┻");
    }


    /**
     * Toont de HUD
     */
    public void showHUD() {
        scoreboard.toString();
        board.toString();
        playablePieces.toString();
    }


    /**
     * Loginsysteem van de user alvorens het spel kan starten
     */
    public boolean login() {
        Scanner keyboard = new Scanner(System.in);
        System.out.print("-------------- \nLOGIN\n --------------\n");

        System.out.print("Username: ");
        String username = keyboard.next();

        System.out.print("Password: ");
        String password = keyboard.next();

        if (am.login(username,password)){
            this.player = new Player(username, 5); // <-- hoe highscore ophalen?
            return true;
        }else {
            System.out.println("foute login");
            return false;
        }
    }


    /**
     * Play doet een spelzet op het bord.
     */
    public void play() {
        Piece selectedPiece = playablePieces.randomPiece();
        if (board.Move(selectedPiece, new Point(random.nextInt(board.getSize()), random.nextInt(board.getSize())))) {
            scoreboard.updateScore(selectedPiece.getValue());
            playablePieces.remove(selectedPiece);
        }
    }


}

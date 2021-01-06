package model;

import java.awt.*;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/**
 * @author Maxim Derboven & Alexie Chaerle
 * @version 1.0 9/12/2020 18:44
 * @description Dit is het beheer van het spel, deze klasse leid de andere klasse die deel uitmaken van het spel.
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
    private FileManagement am = new FileManagement();


    /**
     * Bij het aanmaken van een nieuwe in de constructor het spel starten.
     * Deze constructor omvat het hele spelverloop
     */
    public Game() {
        if (this.identify()) {
            this.board = new Board(10);
            this.scoreboard = new Scoreboard(player);
            this.playablePieces = new PlayablePieces(3);
            settings();
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
        System.out.println();
        System.out.println("Loading...");
        System.out.println();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        showHUD();



        /* Tijdens het spel: */
        while (isPossible()) {
            play();
            showHUD();
            playablePieces.newPieces(scoreboard.getScore());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        /* Na het spel: */
        if (playablePieces.isGrading()) {
            scoreboard.updateHighscore();
        }
        System.out.println("Game over: (╯°□°）╯︵ ┻━┻");
    }


    /**
     * Toont de HUD
     */
    public void showHUD() {
        System.out.println(scoreboard);
        System.out.print(board);
        System.out.println(playablePieces);
        for (int i = 0; i < 5; i++) {
            System.out.println();
        }
    }


    /**
     * Play doet een spelzet op het bord.
     */
    public void play() {
        Piece selectedPiece = playablePieces.randomPiece();
        if (board.Move(selectedPiece, new Point(random.nextInt(board.getSize()), random.nextInt(board.getSize())))) {
            scoreboard.updateScore(selectedPiece.getValue() + board.clearLines());
            playablePieces.remove(selectedPiece);
        }
    }


    /**
     * Loginsysteem van de user alvorens het spel kan starten
     */
    public boolean identify() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println();
        System.out.print("-------------- \nMENU\n--------------\n");
        System.out.println("(1) Login");
        System.out.println("(2) Register");
        System.out.println("(3) Leaderboard");
        System.out.print("Choice : ");
        int choice;
        try {
            choice = keyboard.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Please choose one of the following options: [1 for login, 2 for register]");
            return false;
        }
        String username;
        String password;
        switch (choice) {
            case 1:
                System.out.println();
                System.out.print("-------------- \nLOGIN\n--------------\n");
                System.out.print("Username: ");
                username = keyboard.next();

                System.out.print("Password: ");
                password = keyboard.next();

                if (am.login(username, password)) {
                    this.player = new Player(username, am.getHighscore(username));
                    return true;
                } else {
                    System.out.println("Incorrect credentials");
                    return false;
                }
            case 2:
                System.out.println();
                System.out.print("-------------- \nREGISTER\n--------------\n");
                System.out.print("Username: ");
                username = keyboard.next();

                System.out.print("Password: ");
                password = keyboard.next();

                if (am.register(username, password)) {
                    this.player = new Player(username);
                    return true;
                } else {
                    System.out.println("Username already exists");
                    return false;
                }
            case 3:
                System.out.println();
                System.out.println("-------------- \nLEADERBOARD\n--------------\n");
                System.out.print(am.getLeaderboard());
                System.out.println("--------------");
                System.out.println("(1) return");
                System.out.print("Choice : ");
                try {
                    choice = keyboard.nextInt();
                    if (choice == 1){
                        identify();
                    }else{
                        System.out.println("Please select a valid choice");
                    }
                }catch (InputMismatchException e){
                    System.out.println("Please select a valid choice");
                }
                break;
            default:
                System.out.println("Please select a valid choice");
                break;
        }
        return false;
    }


    /**
     * Instellingen menu
     */
    public void settings() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println();
        System.out.print("-------------- \nMENU\n--------------\n");
        System.out.println("(1) Settings");
        System.out.println("(2) Start game");
        System.out.print("Choice : ");
        int choice = 0;
        try {
            choice = keyboard.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Please choose one of the following options: [1 for settings, 2 for starting the game]");
        }
        switch (choice) {
            case 1:
                System.out.println();
                System.out.print("-------------- \nSETTINGS\n--------------\n");
                System.out.println("(1) board size: " + "\u001B[34m" + board.getSize() + "\033[0m");
                System.out.println("(2) with grading: " + "\u001B[34m" + playablePieces.isGrading() + "\033[0m");
                System.out.println("(3) playable pieces: " + "\u001B[34m" + playablePieces.getCapacity() + "\033[0m");
                System.out.println("(4) return");
                System.out.print("Choice : ");
                choice = keyboard.nextInt();
                switch (choice) {
                    case 1:
                        System.out.println();
                        System.out.print("-------------- \nSETTINGS\n--------------\n");
                        System.out.print("board size: ");
                        board.setSize(keyboard.nextInt());
                        settings();
                        break;
                    case 2:
                        System.out.println();
                        System.out.print("-------------- \nSETTINGS\n--------------\n");
                        System.out.print("[Highscores do not count when grading is disabled.]\n");
                        System.out.print("grading (true/false): ");
                        playablePieces.setGrading(keyboard.nextBoolean());
                        settings();
                        break;
                    case 3:
                        System.out.println();
                        System.out.print("-------------- \nSETTINGS\n--------------\n");
                        System.out.print("capacity : ");
                        playablePieces.setCapacity(keyboard.nextInt());
                        settings();
                        break;
                    case 4:
                        settings();
                }
            case 2:
        }
    }
}

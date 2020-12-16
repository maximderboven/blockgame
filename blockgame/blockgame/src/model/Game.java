package model;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * @author Maxim Derboven
 * @version 1.0 9/12/2020 18:44
 * @description Dit is het beheer van het spel, deze klasse leid de andere klasse die deel uitmaken van het spel.
 */

public class Game {
    //items nodig om het spel vanuit deze hoofdklasse te leiden.
    //Welk bord er gebruikt wordt:
    private Board board;
    //Welke speler er gaat spelen:
    private Player player;
    //Welk scoreboard er gebruikt gaat worden:
    private Scoreboard scoreboard;
    //Welke stukjes er aan de beurt zijn om geplaats te worden
    //private Piece[] pieces = new Piece[3];
    private final int CAPACITY = 3;
    private ArrayList<Piece> pieces = new ArrayList<>(CAPACITY);
    //Random voor de console aplicatie
    private Random random = new Random();

    //Eventueel een tijd om bij te houden hoe lang hij het heeft uitgehouden:
    //private LocalTime time;


    //start van het spel
    /*
    Volgorde;
    1. Inloggen of registreren
    2. op basis van de gegevens een speler aanmaken
    3. Bord maken
    4. Scoreboard maken
    5. Begin situatie schetsen
    6. 3 random blokken geven
    6.1 Kijken of de blokken nog passen anders is het gedaan
    7. De speler een blok laten kiezen
    8. Kijken of de blok past waar de speler hem wilt zetten
    9. De Tegels op locatie used zetten
    10. Herhaal 6.1 voor de overige 2 blokken
    11. Herhaal 6 tot een van de blokken niet meer past.
     */

    public Game() {
        if (login()) {
            this.scoreboard = new Scoreboard(player);
            this.board = new Board(scoreboard);
            start();
        } else {
            System.out.println("Foute login");
        }
    }

    //controleert of het spel gedaan is adhv het bord dat gaat kijken of de blok nog geplaatst kan worden
    public boolean isPossible() {

        //loop door de array van pieces hier:
        for (Piece piece : this.pieces) {
            if (board.isPossible(piece)) {
                return true;
            } else {
                return false;
            }
        }
        return true; //? missing return statement
    }

    // Start van het spel na het initialiseren van al de nodige klasse (objecten aangemaakt)
    public void start() {
        //voor het spel start:
        //Laat de situatie van het bord zien:
        board.draw();
        //geeft 3 blokken om te beginnen spelen
        RandomPiece();

        //Tijdens het spel:
        //Zolang het mogelijk is om blokken te zetten:
        while (!isPossible()) {
            //Laat de speler een zet doen:
            // EVT EEN BOOL MEEGEVEN vanuit player.play OF DE PLAY IS GELUKT EN ALS HET IS GELUKT:
            // piece op null zetten en game opnieuw drawen anders fout melding

            // HIER RANDOM PIECE UIT PIECES ATTR GAME MEEGEVEN
            Piece selectedPiece = pieces.get(random.nextInt(pieces.size()));
            if (player.play(selectedPiece, new Point(random.nextInt(board.getSize()), random.nextInt(board.getSize())))) {
                pieces.remove(selectedPiece);
            }

            //Laat de nieuwe  HUD zien (scoreboard en speler naam)
            showHUD();
            // laat de nieuwe situatie van het bord zien
            board.draw();

            //geeft 3 blokken om te beginnen spelen ENKEL ALS DE OUDE DRIE OP ZIJN!

            RandomPiece();

            //wacht even (eerste rudimentaire versie is zonder input van de gebruiker) automatisch
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //Wanneer het spel gedaan is:
        // Check of score groter is dan highscore
        int score = scoreboard.getScore();
        if (score > player.getHighscore()) {
            player.setHighscore(score);
        }
    }


    //toont de HUD (alles ronddom het spelbord)
    public void showHUD() {
        scoreboard.draw();
    }

    //Zorgt voor de login van de user alvoorens het spel kan starten
    public boolean login() {
        // Op welke manier gegevens opslaan?
        // 1. In object van players steken?
        // 2. Of in tweedimensionale array?

        Scanner keyboard = new Scanner(System.in);
        System.out.print("-------------- \nLOGIN\n--------------\n");

        //Asks for username:
        System.out.print("username: ");
        String username = keyboard.next();

        //Asks for password:
        System.out.print("password: ");
        String password = keyboard.next();

        //Reading the lines from highscores.txt using Scanner class:

        Scanner sc = null;
        try {
            sc = new Scanner(new File("..\\blockgame\\blockgame\\src\\model\\resources\\highscores.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ArrayList<String> rows = new ArrayList<>();
        while (sc.hasNext()) {
            rows.add(sc.nextLine());
        }
        sc.close();

        //Get password from rows and store them in new array:
        String[] credentials;
        for (String line : rows) {
            credentials = line.split(":");
            for (int i = 0; i < credentials.length; i++) {
                if (credentials[0].equals(username) && credentials[1].equals(password)) {


                    //If login is successful:
                    //Huidige gebruiker aanmaken

                    //Highscore normaal 0 als hij ooit geregistreerd was maar niet gespeeld heeft
                    // geregistreerd andere constructor gebruiken
                    this.player = new Player(username, Integer.parseInt(credentials[2]));
                    return true;
                }
            }
        }
        return false;
    }

    public void RandomPiece() {
        if (pieces.isEmpty()) {
            for (int i = 0; i < CAPACITY; i++) {
                pieces.add(Piece.values()[random.nextInt(Piece.values().length)]);
            }
        }
        // TOEVOEGEN IN PIECES ATTR GAME
        // System.out.printf("[%s\t%s\t%s]", pieces[0], pieces[1], pieces[2]);
    }

}

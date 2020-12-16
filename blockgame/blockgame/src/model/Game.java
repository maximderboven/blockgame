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
        //board.draw();
        //geeft 3 blokken om te beginnen spelen
        RandomPiece();
        //Laat de nieuwe  HUD zien (scoreboard en speler naam)
        showHUD();
        // laat de nieuwe situatie van het bord zien
        board.draw();

        // Toont overige pieces
        showPieces();

        //Tijdens het spel:
        //Zolang het mogelijk is om blokken te zetten:
        while (isPossible()) {
            //Laat de speler een zet doen:
            // EVT EEN BOOL MEEGEVEN vanuit player.play OF DE PLAY IS GELUKT EN ALS HET IS GELUKT:
            // piece op null zetten en game opnieuw drawen anders fout melding

            // HIER RANDOM PIECE UIT PIECES ATTR GAME MEEGEVEN
            Piece selectedPiece = pieces.get(random.nextInt(pieces.size()));
            if (player.play(selectedPiece, new Point(random.nextInt(board.getSize()), random.nextInt(board.getSize())))) {
                pieces.remove(selectedPiece);
                this.scoreboard.addScore(selectedPiece.getValue());

                // Highscore dynamisch updaten
                int score = scoreboard.getScore();
                if (score > player.getHighscore()){
                    player.setHighscore(score);
                }

            }

            //Laat de nieuwe  HUD zien (scoreboard en speler naam)
            showHUD();
            // laat de nieuwe situatie van het bord zien
            board.draw();

            // Toont overige pieces
            showPieces();

            //geeft 3 blokken om te beginnen spelen ENKEL ALS DE OUDE DRIE OP ZIJN!

            RandomPiece();

            //wacht even (eerste rudimentaire versie is zonder input van de gebruiker) automatisch
            try {
                Thread.sleep(800);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //Wanneer het spel gedaan is, kijken of score groter is dan highscore
        int score = scoreboard.getScore();
        if (score > player.getHighscore()) {
            player.setHighscore(score);
        }
        System.out.println("Game over: (╯°□°）╯︵ ┻━┻");
    }

    // Toont de HUD (alles ronddom het spelbord)
    public void showHUD() {
        scoreboard.draw();
    }

    // Loginsysteem van de user alvorens het spel kan starten
    public boolean login() {
        Scanner keyboard = new Scanner(System.in);
        System.out.print("-------------- \nLOGIN\n --------------\n");

        // Input voor gebruikersnaam
        System.out.print("Gebruikersnaam: ");
        String username = keyboard.next();

        // Input voor wachtwoord
        System.out.print("Wachtwoord: ");
        String password = keyboard.next();

        ArrayList<String> rows = new ArrayList<>();
        try {
            BufferedReader file = new BufferedReader(new FileReader("../blockgame/blockgame/src/model/resources/highscores.txt"));
            String line;
            while ((line = file.readLine()) != null) {
                // Ingelezen lijn toevoegen in de arraylist
                rows.add(line);
            }
            file.close();
        } catch (IOException e){
            e.printStackTrace();
        }

        // Gebruikersnaam en wachtwoord ophalen van highscores.txt
        String[] credentials;
        for (String row : rows) {
            credentials = row.split(":");
            for (int i = 0; i < credentials.length; i++) {
                if (credentials[0].equals(username) && credentials[1].equals(password)) {
                    // Huidige gebruiker aanmaken

                    // Highscore normaal 0 als hij ooit geregistreerd was maar niet gespeeld heeft
                    // Ggeregistreerd andere constructor gebruiken
                    this.board = new Board(3);
                    this.player = new Player(username, Integer.parseInt(credentials[2]), board);
                    this.scoreboard = new Scoreboard(player);
                    return true;
                }
            }
        }
        return false;
    }

    // Genereert random pieces indien ze op zijn
    public void RandomPiece() {
        if (pieces.isEmpty()) {
            for (int i = 0; i < CAPACITY; i++) {
                pieces.add(Piece.values()[random.nextInt(Piece.values().length)]);
            }
        }
        // TOEVOEGEN IN PIECES ATTR GAME
    }

    // Toont de huidige pieces
    public void showPieces() {
        System.out.println("------------");
        for (Piece overigePiece : pieces){
            System.out.printf("[%s] ", overigePiece);
        }
        System.out.println("\n\n\n");
    }

}

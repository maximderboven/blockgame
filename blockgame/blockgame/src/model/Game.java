package model;

import java.util.Random;
import java.util.Scanner;

/**
 * @author Maxim Derboven
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
    //Welke speler er gaat spelen:
    private Player player;
    //Welk scoreboard er gebruikt gaat worden:
    private Scoreboard scoreboard;
    //Random voor de console aplicatie
    private Random random = new Random();
    //Zetten die de speler gaat doen
    private Move move;
    //Welk bord er gebruikt wordt:
    private Board board;
    //Eventueel een tijd om bij te houden hoe lang hij het heeft uitgehouden:
    //private LocalTime time;


    public Game() {
        if (login()) {
            this.board = new Board(3);
            this.player = new Player(username, Integer.parseInt(credentials[2]), board);
            this.scoreboard = new Scoreboard(player);
            this.move = new Move(player,board);
            start();
        } else {
            System.out.println("Foute login");
        }
    }

    //controleert of het spel gedaan is adhv het bord dat gaat kijken of de blok nog geplaatst kan worden
    public boolean isPossible() {
        Move.isPossible();
    }

    // Start van het spel na het initialiseren van al de nodige klasse (objecten aangemaakt)
    public void start() {
        //Laat de nieuwe  HUD zien (scoreboard en speler naam)
        showHUD();

        //Tijdens het spel:
        //Zolang het mogelijk is om blokken te zetten:
        while (isPossible()) {
            //Laat de speler een zet doen:
            // EVT EEN BOOL MEEGEVEN vanuit player.play OF DE PLAY IS GELUKT EN ALS HET IS GELUKT:
            // piece op null zetten en game opnieuw drawen anders fout melding
            //Laat de nieuwe  HUD zien (scoreboard en board en speler naam)
            showHUD();

            //geeft 3 blokken om te beginnen spelen
            randomPiece();

            //wacht even (eerste rudimentaire versie is zonder input van de gebruiker) automatisch
            try {
                Thread.sleep(800);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        scoreboard.updateScore();
        System.out.println("Game over: (╯°□°）╯︵ ┻━┻");
    }

    // Toont de HUD (alles ronddom het spelbord)
    public void showHUD() {
        scoreboard.toString();
        board.toString();
        Move.toString();
    }

    // Loginsysteem van de user alvorens het spel kan starten
    public boolean login() {
        Scanner keyboard = new Scanner(System.in);
        System.out.print("-------------- \nLOGIN\n --------------\n");

        // Input voor gebruikersnaam
        System.out.print("Username: ");
        String username = keyboard.next();

        // Input voor wachtwoord
        System.out.print("Password: ");
        String password = keyboard.next();
        accessManagement.login(username, password);
    }

    // Genereert random pieces indien ze op zijn
    public void randomPiece() {
        Move.randomPiece();
    }
}

package model;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * @author Maxim Derboven
 * @version 1.0 9/12/2020 18:44
 */

public class Game {
    private Board board;
    private Player player;
    //private LocalTime time;
    private int highscore;

    public Game() {
        //Mag ik die hier handelen die Exception ? Of liever in Login direct ?
        try {
            if (login()) {
                this.board = new Board(player);
                start();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean isFinished() {
        return board.isFinished();
    }

    public void start() {
        while (!isFinished()) {
            board.update();
            showHUD();
            board.draw();
            drawRandomPiece();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //Checks if score > highscore
        int score = board.getPlayer().getScoreboard().getScore();
        if (score > highscore){
            player.getScoreboard().setHighscore(score);
        }

    }

    public void showHUD() {
        board.getPlayer().showScoreboard();
    }

    public boolean login() throws FileNotFoundException {
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
        Scanner sc = new Scanner(new File("..\\blockgame\\blockgame\\src\\model\\resources\\highscores.txt"));
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
                    this.highscore = Integer.parseInt(credentials[3]);
                    this.player = new Player(username, new Scoreboard(highscore));
                    return true;
                }
            }
        }
        return false;
    }

    public void drawRandomPiece() {
        Random random = new Random();
        Piece piece1 = Piece.values()[random.nextInt(Piece.values().length)];
        Piece piece2 = Piece.values()[random.nextInt(Piece.values().length)];
        Piece piece3 = Piece.values()[random.nextInt(Piece.values().length)];
        System.out.printf("%s\t%s\t%s", piece1, piece2, piece3);
    }

}

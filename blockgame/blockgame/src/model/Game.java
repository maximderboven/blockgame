package model;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Maxim Derboven
 * @version 1.0 9/12/2020 18:44
 */

public class Game {
    private Board board;
    private LocalTime time;
    private int highscore;

    public Game() throws FileNotFoundException {
        if (login()) {
            start();
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

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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
        String[] arraysplit;
        for (String line : rows) {
            arraysplit = line.split(":");
            for (int i = 0; i < arraysplit.length; i++) {
                if (arraysplit[0].equals(username) && arraysplit[1].equals(password)) {
                    //If login is successful:
                    Player player = new Player(username, new Scoreboard(highscore));
                    this.board = new Board(player);
                    return true;
                }
            }
        }
        return false;
    }

}

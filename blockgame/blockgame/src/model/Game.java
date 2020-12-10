package model;

import java.time.LocalTime;
import java.util.Scanner;

/**
 * @author Maxim Derboven
 * @version 1.0 9/12/2020 18:44
 */

public class Game {
    private Board board;
    private LocalTime time;
    private int highscore;

    public Game() {
        Scanner keyboard = new Scanner(System.in);
        System.out.print("-------------- \nLOGIN\n--------------\n");
        System.out.print("username: ");
        String username = keyboard.next();
        System.out.print("password: ");
        keyboard.next();
        System.out.print("--------------\n");
        //login {
        Player player = new Player(username,new Scoreboard(highscore));
        this.board = new Board(player);
        // }
        start();
    }

    public boolean isFinished() {
        return board.isFinished();
    }

    public void start(){
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
}

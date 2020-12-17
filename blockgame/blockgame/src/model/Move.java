package model;

import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Maxim Derboven
 * @version 1.0 17/12/2020 10:13
 */
public class Move {
    //Welke stukjes er aan de beurt zijn om geplaats te worden
    //private Piece[] pieces = new Piece[3];
    private final int CAPACITY = 3;
    private ArrayList<Piece> pieces = new ArrayList<>(CAPACITY);
    //Welk bord er gebruikt wordt:
    private Board board;
    private Player player;

    public Move(Board board, Player player) {
        this.board = board;
        this.player = player;
        randomPiece();
    }

    public void randomPiece() {
        if (pieces.isEmpty()) {
            for (int i = 0; i < CAPACITY; i++) {
                pieces.add(Piece.values()[random.nextInt(Piece.values().length)]);
            }
        }
    }

    public boolean isPossible() {
        //loop door de array van pieces hier:
        for (Piece piece : this.pieces) {
            if (board.isPossible(piece)) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public boolean move(Player player) {
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
    }

    @Override
    public String toString() {
        System.out.println("------------");
        for (Piece overigePiece : pieces){
            System.out.printf("[%s] ", overigePiece);
        }
        System.out.println("\n\n\n");
        return "";
    }
}

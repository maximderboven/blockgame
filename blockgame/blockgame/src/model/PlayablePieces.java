package model;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author Maxim Derboven
 * @version 1.0 17/12/2020 10:13
 */
public class PlayablePieces {
    private int capacity = 3;
    private ArrayList<Piece> pieces = new ArrayList<>(capacity);
    private Random random = new Random();

    public PlayablePieces() {
        newPieces();
    }

    public PlayablePieces(int capacity) {
        this.capacity = capacity;
        newPieces();
    }

    public void newPieces() {
        if (pieces.isEmpty()) {
            for (int i = 0; i < capacity; i++) {
                pieces.add(Piece.values()[random.nextInt(Piece.values().length)]);
            }
        }
    }

    public ArrayList<Piece> getPieces() {
        return pieces;
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

    public Piece randomPiece() {
        return pieces.get(random.nextInt(pieces.size()));
    }

    public void remove(Piece selectedPiece) {
        pieces.remove(selectedPiece);
    }
}

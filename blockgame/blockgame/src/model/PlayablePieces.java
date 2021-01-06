package model;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author Maxim Derboven
 * @version 1.0 17/12/2020 10:13
 * @description Deze klasse houdt de 3 speelblokken bij en verzorgt ook de methodes voor deze blokken.
 */
public class PlayablePieces {
    /**
     * ATTRIBUTEN
     * capacity = aantal blokken
     * Aantal blokken met capacity
     * Random voor de rudimentaire vorm
     */
    private int capacity = 3;
    private ArrayList<Piece> pieces = new ArrayList<>(capacity);
    private Random random = new Random();


    /**
     * CONSTRUCTOR
     * Eentje met standaard capacity en eentje met
     */
    public PlayablePieces() {
        newPieces();
    }

    public PlayablePieces(int capacity) {
        this.capacity = capacity;
        newPieces();
    }


    /**
     * Vult de Array opnieuw moet worden opgeroepen na elke zet.
     */
    public void newPieces() {
        if (pieces.isEmpty()) {
            for (int i = 0; i < capacity; i++) {
                pieces.add(Piece.values()[random.nextInt(Piece.values().length)]);
            }
        }
    }


    /**
     * Krijgt de huidige arraylist (nodig voor bv te controleren of er nog een zet mogelijk is)
     */
    public ArrayList<Piece> getPieces() {
        return pieces;
    }


    /**
     * Geef een randompiece terug nodig voor de rudimentaire vorm
     */
    public Piece randomPiece() {
        return pieces.get(random.nextInt(pieces.size()));
    }


    /**
     * Verwijderd een blok uit de array
     */
    public void remove(Piece selectedPiece) {
        pieces.remove(selectedPiece);
    }


    /**
     * Print de blokken uit
     */
    @Override
    public String toString() {
        String s = "------------\n";
        for (Piece piece : pieces){
            s += String.format("[%s] ", piece.toString());
        }
        return s;
    }
}

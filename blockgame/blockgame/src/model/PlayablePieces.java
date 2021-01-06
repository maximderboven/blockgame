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
    private int capacity;
    private ArrayList<Piece> pieces = new ArrayList<>(capacity);
    private Random random = new Random();
    private boolean grading = true;


    /**
     * CONSTRUCTOR
     * Eentje met standaard capacity en eentje met een aanpasbare
     */
    public PlayablePieces() {
        this.capacity = 3;
        newPieces();
    }
    public PlayablePieces(int capacity) {
        this.capacity = capacity;
        newPieces();
    }


    /**
     * Vult de Array met nieuwe blokken
     * eerste is op basis van grading
     */
    public void newPieces(int score) {
        if (grading) {
            if (pieces.isEmpty()) {
                for (int i = 0; i < capacity; i++) {
                    Piece piece = Piece.values()[random.nextInt(Piece.values().length)];
                    if (score < 100) {
                        pieces.add(piece);
                    } else if (score < 150) {
                        if (piece.getValue() >= 2) {
                            pieces.add(piece);
                        }
                    } else if (score < 200) {
                        if (piece.getValue() >= 3) {
                            pieces.add(piece);
                        }
                    } else if (score < 250) {
                        if (piece.getValue() >= 4) {
                            pieces.add(piece);
                        }
                    }
                }
            }
        } else {
            newPieces();
        }
    }
    public void newPieces() {
        if (pieces.isEmpty()) {
            for (int i = 0; i < capacity; i++) {
                pieces.add(Piece.values()[random.nextInt(Piece.values().length)]);
            }
        }
    }


    /**
     * @return de huidige arraylist
     */
    public ArrayList<Piece> getPieces() {
        return pieces;
    }


    /**
     * @return  geeft een random piece terug (console)
     */
    public Piece randomPiece() {
        return pieces.get(random.nextInt(pieces.size()));
    }


    /**
     * Verwijderd de gegeven blok uit de array
     */
    public void remove(Piece selectedPiece) {
        pieces.remove(selectedPiece);
    }


    /**
     * Print de huidige blokken uit
     */
    @Override
    public String toString() {
        String s = "------------\n";
        for (Piece piece : pieces) {
            s += String.format("[%s] ", piece.toString());
        }
        return s;
    }


    /**
     * @return moeilijkheids vordering staat aan of af.
     */
    public boolean isGrading() {
        return grading;
    }


    /**
     * toggle de moeilijkheid vordering
     */
    public void setGrading(boolean grading) {
        this.grading = grading;
    }


    /**
     * @return hoeveelheid van blokken waarmee gespeeld word.
     */
    public int getCapacity() {
        return capacity;
    }


    /**
     * @return Verander da hoeveelheid blokken van dit spel.
     */
    public void setCapacity(int capacity) {
        if (capacity > 2 && capacity < 11) {
        this.capacity = capacity;
        pieces.clear();
        newPieces();
        }
    }
}

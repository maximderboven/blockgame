package blockgame.model;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author Maxim Derboven
 * @version 1.0 17/12/2020 10:13
 * @description Deze klasse houdt de 3 speelblokken bij en verzorgt ook de methodes voor deze blokken.
 */
public class PlayablePieces {
    /**
     * ATTRIBUTEN:
     * capacity          Aantal blokken.
     * pieces            Aantal blokken met capacity.
     * random            Random voor de rudimentaire vorm.
     * difficulty        Difficulty(modus) van het spel.
     * */
    private int capacity;
    private ArrayList<Piece> pieces = new ArrayList<>(capacity);
    private final Random random = new Random();
    private boolean difficulty = true;


    /**
     * Constructor zonder parameter(s):
     * Inclusief standaard capacity.
     * */
    public PlayablePieces() {
        this.capacity = 3;
        newPieces();
    }


    /**
     * Constructor met parameter(s):
     * Inclusief aanpasbare capacity.
     * @param capacity  Aantal blokken in het spel.
     * */
    public PlayablePieces(int capacity) {
        this.capacity = capacity;
        newPieces();
    }


    /**
     * Vult de Array met nieuwe blokken.
     * Eerste blok is op basis van score (difficulty).
     * @param score  Huidige score om moeilijkheidsgraad te bepalen.
     * */
    public void newPieces(int score) {
        if (difficulty) {
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


    /**
     * Vult de Array met nieuwe blokken.
     * */
    private void newPieces() {
        if (pieces.isEmpty()) {
            for (int i = 0; i < capacity; i++) {
                pieces.add(Piece.values()[random.nextInt(Piece.values().length)]);
            }
        }
    }


    /**
     * @return ArrayList<Piece>  De huidige arraylist.
     * */
    public ArrayList<Piece> getPieces() {
        return pieces;
    }


    /**
     * Verwijderd de gegeven blok uit de array.
     * @param selectedPiece  de geselecteerde blok.
     * */
    public void remove(Piece selectedPiece) {
        pieces.remove(selectedPiece);
    }


    /**
     * Print de huidige blokken uit.
     * */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("------------\n");
        for (Piece piece : pieces) {
            s.append(String.format("[%s] ", piece.toString()));
        }
        return s.toString();
    }


    /**
     * @return boolean Toestand van de moeilijkheidsgraad.
     * */
    public boolean isDifficulty() {
        return difficulty;
    }


    /**
     * Toggle de moeilijkheidsgraad.
     * @param difficulty  Ingegeven toestand van de moeilijkheidsgraad.
     * */
    public void setDifficulty(boolean difficulty) {
        this.difficulty = difficulty;
    }


    /**
     * @return int Hoeveelheid van blokken waarmee gespeeld wordt.
     * */
    public int getCapacity() {
        return capacity;
    }


    /**
     * Verander de hoeveelheid blokken van dit spel.
     * @param capacity Hoeveelheid blokken.
     * */
    public void setCapacity(int capacity) {
        if (capacity > 2 && capacity < 11) {
        this.capacity = capacity;
        pieces.clear();
        newPieces();
        }
    }
}

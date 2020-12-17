package model;

import java.awt.*;

/**
 * @author Maxim Derboven
 * @version 1.0 9/12/2020 18:46
 * @description We gebruiken een Tegel om deze een kleur te kunnen geven op het speelveld en zo onderscheid te kunnen maken tussen gebruikte en niet gebruikte velde.
 */
public class Tile {
    /**
     * ATRIBUTEN
     * eigenschappen van een tegel in het speelveld
     * De Kleur om in te kleuren
     * de locatie op het bord (nodig om used or unused te zetten)
     * Of de tegel in gebruik is of niet.
     * markdelete om rij te "verwijderen" zodat een kolom die een rij splitst niet mee verwijderd wordt.
     */
    private Color color;
    private Point point;
    private boolean used;
    private boolean markdelete = false;

    /**
     * CONSTRUCTOR
     * Tegel met kleur aanmaken & een zonder kleur
     */
    public Tile(Point point) {
        this.point = point;
        this.used = false;
    }

    public Tile(Color color, Point point) {
        this.color = color;
        this.point = point;
        this.used = false;
    }


    /**
     * opvragen of hij gedelete mag worden
     */
    public boolean isMarkdelete() {
        return markdelete;
    }


    /**
     * Markeren voor het deleten
     */
    public void setMarkdelete(boolean markdelete) {
        this.markdelete = markdelete;
    }


    /**
     * verkrijg of de tegel in gebruik is of niet
     */
    public boolean isUsed() {
        return used;
    }


    /**
     * zet de tegen in gebruik of verwijder deze "gebruik" van het bord
     */
    public void setUsed(boolean used) {
        this.used = used;
    }
}
package blockgame.model;

import java.awt.*;

/**
 * @author Maxim Derboven
 * @version 1.0 9/12/2020 18:46
 * @since 1.0
 * @description We gebruiken een Tegel om deze een kleur te kunnen geven op het speelveld en zo onderscheid te kunnen maken tussen gebruikte en niet gebruikte velde.
 */
public class Tile {
    /**
     * Eigenschappen van een tegel in het speelveld.
     * color        De Kleur om in te kleuren.
     * point        De locatie op het bord (nodig om used or unused te zetten).
     * used         Of de tegel in gebruik is of niet.
     * markdelete   om rij te "verwijderen" zodat een kolom die een rij splitst niet mee verwijderd wordt.
     */
    private Color color;
    private Point point;
    private boolean used;
    private boolean markdelete = false;


    /**
     * Constructor met 1 parameter.
     * Tegel zonder kleur aanmaken.
     * @param point Tegel.
     */
    public Tile(Point point) {
        this.point = point;
        this.used = false;
    }


    /**
     * Constructor met 2 parameters.
     * Tegel met kleur aanmaken.
     * @param color Kleur van de tegel.
     * @param point Tegel.
     */
    public Tile(Color color, Point point) {
        this.color = color;
        this.point = point;
        this.used = false;
    }


    /**
     * Opvragen of de Tile gedelete mag worden.
     * @return boolean Of de Tile verwijderd is.
     */
    public boolean isMarkdelete() {
        return markdelete;
    }


    /**
     * Markeren voor het deleten.
     * @param markdelete Keuze of hij gedelete mag worden.
     */
    public void setMarkdelete(boolean markdelete) {
        this.markdelete = markdelete;
    }


    /**
     * Verkrijg of de tegel in gebruik is of niet.
     * @return boolean of te Tegel gebruikt is.
     */
    public boolean isUsed() {
        return used;
    }


    /**
     * Zet de tegel in gebruik of verwijder deze "gebruik" van het bord.
     * @param used Keuze voor de Tegel in "used modus" te zetten.
     */
    public void setUsed(boolean used) {
        this.used = used;
    }


    /**
     * toString van het veld voor de console applicatie (X or 0) (kan gebruik gemaakt worden van attr color).
     * @return de grafische omgeving.
     */
    @Override
    public String toString() {
        if (isUsed()){
            return String.format("\033[1;31m" + "%1s" + "\033[0m", "x");
        }else {
            return String.format("%1s", "o");
        }
    }
}
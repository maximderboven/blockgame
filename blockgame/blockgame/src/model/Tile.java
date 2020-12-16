package model;

import java.awt.*;

/**
 * @author Maxim Derboven
 * @version 1.0 9/12/2020 18:46
 * @description We gebruiken een Tegel om deze een kleur te kunnen geven op het speelveld en zo onderscheid te kunnen maken tussen gebruikte en niet gebruikte velde.
 */
public class Tile {
    //eigenschappen van een tegel in het speelveld
    // De Kleur om in te kleuren
    // de locatie op het bord (nodig om used or unused te zetten)
    // of de tegel in gebruik is of niet.
    private Color color;
    private Point point;
    private boolean used;
    private boolean markdelete = false;

    //Contructur voor het aanmaken van een tegel zonder kleur.
    public Tile(Point point) {
        this.point = point;
        this.used = false;
    }
    //Contructur voor het aanmaken van een tegel met kleur.
    public Tile(Color color, Point point) {
        this.color = color;
        this.point = point;
        this.used = false;
    }

    public boolean isMarkdelete() {
        return markdelete;
    }

    public void setMarkdelete(boolean markdelete) {
        this.markdelete = markdelete;
    }

    // de locatie van een tegel krijgen adhv een plaats in de two dimensional array
    public Point getPoint() {
        return point;
    }

    // verkrijg de kleur van deze tegel
    public Color getColor() {
        return color;
    }

    //verander de kleur van deze tegel
    public void setColor(Color color) {
        this.color = color;
    }

    // verkrijg of de tegel in gebruik is of niet
    public boolean isUsed() {
        return used;
    }

    // zet de tegen in gebruik of verwijder deze "gebruik" van het bord
    public void setUsed(boolean used) {
        this.used = used;
    }
}
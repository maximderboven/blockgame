package model;

import java.io.*;
import java.util.ArrayList;

/**
 * @author Maxim Derboven & Alexie Chaerle
 * @version 1.0 17/12/2020 10:27
 */
public class Management {
    /**
     * ATTRIBUTEN
     * Arraylist van highscores.txt
     */
    private ArrayList<String> rows = new ArrayList<>();


    /**
     * Laat de gebruiker inloggen + controle van wachtwoord.
     */
    public boolean login(String username, String password) {
        readFile();
        if (playerExists(username)) {
            return checkPassword(username, password);
        }
        return false;
    }


    /**
     * Gaat een gebruiker aanmaken door een lijn toe te voegen in het bestand highscores.txt
     */
    public boolean register(String username, String password) {
        if (!playerExists(username)) {
            if (password.contains(":") | username.contains(":")){
                return false;
            }
            try {
                Writer output = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("../blockgame/blockgame/src/model/resources/highscores.txt", true), "UTF-8"));
                output.append(String.format("\n%s:%s:%d", username, password, 0));
                output.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return true;
        }
        return false;
    }


    /**
     * Geeft de highscore van de huidige gebruiker terug.
     * */
    public int getHighscore(String username) {
        String[] line;
        for (String row : rows) {
            if (row.contains(username)) {
                line = row.split(":");
                return Integer.parseInt(line[2]);
            }
        }
        return 0;
    }


    /**
     * File highscores.txt inlezen en in een ArrayList steken.
     * (Wordt enkel in deze klasse gebruikt)
     * */
    private void readFile() {
        try {
            BufferedReader file = new BufferedReader(new FileReader("../blockgame/blockgame/src/model/resources/highscores.txt"));
            String line;
            while ((line = file.readLine()) != null) {
                rows.add(line);
            }
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Kijkt na of speler bestaat doormiddel van ArrayList te splitten.
     * (Wordt enkel in deze klasse gebruikt)
     * */
    private boolean playerExists(String username) {
        String[] credentials;
        // Elke lijn van bestand splitten {username}:{password}:{highscore}
        for (String row : rows) {
            credentials = row.split(":");
            for (int i = 0; i < credentials.length; i++) {
                if (credentials[0].equals(username)) {
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * Controleert het wachtwoord van een gebruiker.
     * (wordt enkel in deze klasse gebruikt).
     * */
    private boolean checkPassword(String username, String password) {
        String[] line;
        for (String row : rows) {
            if (row.contains(username)) {
                line = row.split(":");
                if (line[1].equals(password)) {
                    return true;
                }
            }
        }
        return false;
    }
}

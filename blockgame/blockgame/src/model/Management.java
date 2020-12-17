package model;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
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
     * Login
     */
    public boolean login(String username, String password) {
        readFile();
        if (playerExists(username)) {
            return checkPassword(username, password);
        } else {
            register(username, password);
        }
        return false;
    }

    public void readFile() {
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

    public boolean playerExists(String username) {
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

    public void register(String username, String password) {
        if (!playerExists(username)) {
            rows.add(String.format("%s:%s:%d", username, password, 0));
            try {
                FileOutputStream fileOut = new FileOutputStream("../blockgame/blockgame/src/model/resources/highscores.txt");
                fileOut.write(rows.toString().getBytes());
                fileOut.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public boolean checkPassword(String username, String password) {
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

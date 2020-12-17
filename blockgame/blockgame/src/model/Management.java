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

    // Arraylist van highscores.txt
    private ArrayList<String> rows = new ArrayList<>();

    public boolean login(String username, String password) {
        readFile();
        if (!playerExists(username)) {
            register(username, password);
            return false;
        }else {
            return true;
        }
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


}
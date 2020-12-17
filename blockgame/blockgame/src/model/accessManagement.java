package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Maxim Derboven
 * @version 1.0 17/12/2020 10:27
 */
public class accessManagement {
    public boolean login(String username, String password) {
        ArrayList<String> rows = new ArrayList<>();
        try {
            BufferedReader file = new BufferedReader(new FileReader("../blockgame/blockgame/src/model/resources/highscores.txt"));
            String line;
            while ((line = file.readLine()) != null) {
                // Ingelezen lijn toevoegen in de arraylist
                rows.add(line);
            }
            file.close();
        } catch (IOException e){
            e.printStackTrace();
        }

        // Gebruikersnaam en wachtwoord ophalen van highscores.txt
        String[] credentials;
        for (String row : rows) {
            credentials = row.split(":");
            for (int i = 0; i < credentials.length; i++) {
                if (credentials[0].equals(username) && credentials[1].equals(password)) {
                    // Huidige gebruiker aanmaken

                    // Highscore normaal 0 als hij ooit geregistreerd was maar niet gespeeld heeft
                    // Ggeregistreerd andere constructor gebruiken
                    return true;
                }
            }
        }
        return false;
    }


}

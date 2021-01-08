package model;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Maxim Derboven & Alexie Chaerle
 * @version 1.0 9/12/2020 18:44
 * @since 1.2
 * @description
 * De klasse FileManagement beheert de highscores.txt file en zorgt voor het inloggen/registreren van een speler.
 */

public class FileManagement {
    /**
     * players          Verzameling objecten van players.
     * location         De path naar highscores.txt bestand.
     */
    private List<Player> players = new ArrayList<>();
    private String location = "../blockgame/blockgame/src/model/resources/highscores.txt";


    /**
     * Constructor: bij het instantiëren wordt het highscores.txt bestand ingelezen en in de ArrayList players opgeslagen.
     */
    public FileManagement() {
        readPlayers();
    }


    /**
     * Geeft de huidige locatie van highscores.txt weer.
     * @return String  De locatie van highscores.txt.
     */
    public String getLocation() {
        return location;
    }


    /**
     * Methode om de locatie van het highscores.txt bestand te wijzigen.
     * @param location  De (nieuwe) locatie van highscores.txt
     */
    public void setLocation(String location) {
        this.location = location;
    }


    /**
     * File highscores.txt inlezen en in een ArrayList steken.
     * Private omdat deze methode enkel in deze klasse gebruikt wordt.
     */
    private void readPlayers() {
        try {
            BufferedReader file = new BufferedReader(new FileReader(location));
            String line;
            List<String> rows = new ArrayList<>();
            while ((line = file.readLine()) != null) {
                rows.add(line);
            }
            file.close();
            String[] cutted;
            for (String row : rows) {
                cutted = row.split(":");
                players.add(new Player(cutted[0], cutted[1], Integer.parseInt(cutted[2])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Methode om (nieuwe) gebruikers te registreren.
     * Er wordt een extra lijn toegevoegd in het highscores.txt bestand.
     * @param username  De gebruikersnaam van de player.
     * @param password  Het wachtwoord van de player.
     * @return Player  Instantie van de klasse Player (die gaat spelen).
     * @exception Exception Als de gebruikersnaam al in gebruik is of als het een illegal character bevat (":").
     */
    public Player register(String username, String password) throws Exception {
        for (Player player : players) {
            if (player.getUsername().equalsIgnoreCase(username)) {
                throw new Exception("Username is already in use.");
            }
        }
        if (password.contains(":") | username.contains(":")) {
            throw new Exception("Username can not contain ':'");
        }

        Player player = new Player(username, password);
        players.add(player);

        try {
            Writer output = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(location, true), StandardCharsets.UTF_8));
            output.append(String.format("\n%s:%s:%d", username, password, 0));
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return player;
    }


    /**
     * Zorgt ervoor dat de gebruiker zich kan inloggen.
     * @param username De gebruikersnaam van de player.
     * @param password Het wachtwoord van de player.
     * @return Player  Instantie van de klasse Player (degene die gaat spelen).
     * @exception Exception Indien het wachtwoord onjuist is of de gebruiker niet bestaat.
     */
    public Player login(String username, String password) throws Exception {
        for (Player player : players) {
            if (player.getUsername().equalsIgnoreCase(username)) {
                if (player.getPassword().equals(password)) {
                    return player;
                } else {
                    throw new Exception("Incorrect password.");
                }
            }
        }
        throw new Exception("User: " + username + " does not exist.");
    }


    /**
     * Toont de Leaderboard.
     * Top 10 spelers: Naam + Highscore.
     * @return String  Geformatteerde string(buffer) met de top 10 spelers.
     */
    public String getLeaderboard() {
        //misschien geen stream gebruiken maar wat we hebben geleerd (custom comparator)
        StringBuilder buffer = new StringBuilder();
        for (Player p : players.stream().sorted(Comparator.comparing(Player::getHighscore).reversed()).collect(Collectors.toList())) {
            buffer.append(p + " - " + p.getHighscore() + "\n");
        }
        return buffer.toString();
    }


    /**
     * Highscore van de huidige speler oplsaan in highscores.txt.
     * Toepassen nadat het spel beëindigd is.
     * @param player  Instantie van de klasse Player (de huidige speler).
     */
    public void save(Player player) {
        try {
            BufferedReader file = new BufferedReader(new FileReader(location));
            StringBuilder buffer = new StringBuilder();
            String line;

            while ((line = file.readLine()) != null) {
                if (line.contains(player.getUsername())) {
                    // If line contains the current player's name:
                    String[] credentials = line.split(":");
                    line = credentials[0] + ":" + credentials[1] + ":" + player.getHighscore();
                }
                buffer.append(line);
                buffer.append('\n');
            }
            file.close();

            // Write new line to highscores.txt
            FileOutputStream fileOut = new FileOutputStream(location);
            fileOut.write(buffer.toString().getBytes());
            fileOut.close();

        } catch (Exception e) {
            System.out.println("File not found");
        }
    }
}

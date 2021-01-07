package model;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Maxim Derboven & Alexie Chaerle
 * @version 1.0 17/12/2020 10:27
 */
public class FileManagement {
    /**
     * ATTRIBUTEN
     * Arraylist van highscores.txt
     */
    private List<Player> players = new ArrayList<>();
    private String location = "../blockgame/blockgame/src/model/resources/highscores.txt";

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public FileManagement() {
        readPlayers();
    }

    /**
     * File highscores.txt inlezen en in een ArrayList steken.
     * (Wordt enkel in deze klasse gebruikt)
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
     * Gaat een gebruiker aanmaken door een lijn toe te voegen in het bestand highscores.txt
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
            Writer output = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(location, true), "UTF-8"));
            output.append(String.format("\n%s:%s:%d", username, password, 0));
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return player;
    }

    /**
     * laat de gebruiker inloggen
     * @param args Unused.
     * @return Nothing.
     * @exception IOException On input error.
     * @see IOException
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
     * Toont een overzicht van de Leaderboard
     */

    public String getLeaderboard() {
        //misschien geen stream gebruiken maar wat we hebben geleerd (custom comperator)
        StringBuilder buffer = new StringBuilder();
        for (Player p : players.stream().sorted(Comparator.comparing(Player::getHighscore).reversed()).collect(Collectors.toList())) {
            buffer.append(p + " - " + p.getHighscore() + "\n");
        }
        return buffer.toString();
    }

    /**
     * nieuwe highscore (zetten na game)
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

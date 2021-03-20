package blockgame.model;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Maxim Derboven & Alexie Chaerle
 * @version 1.0 9/12/2020 18:44
 * @description De klasse FileManagement beheert de highscores.txt file en zorgt voor het inloggen/registreren van een speler.
 * @since 1.2
 */

public class FileManagement {
    /**
     * players          Verzameling objecten van players.
     * location         De path naar highscores.txt bestand.
     */
    private List<Player> players = new ArrayList<>();
    private Path location = Paths.get(".." + File.separator + "blockgame" + File.separator + "blockgame" + File.separator + "resources" + File.separator + "data" + File.separator + "highscores.txt");


    /**
     * Constructor: bij het instantiëren wordt het highscores.txt bestand ingelezen en in de ArrayList players opgeslagen.
     */
    public FileManagement() {
        readPlayers();
    }


    /**
     * Geeft de huidige locatie van highscores.txt weer.
     *
     * @return String  De locatie van highscores.txt.
     */
    public String getLocation() {
        return location.toString();
    }


    /**
     * Methode om de locatie van het highscores.txt bestand te wijzigen.
     *
     * @param location De (nieuwe) locatie van highscores.txt
     */
    public void setLocation(String location) {
        this.location = Paths.get(location);
        players.clear();
        readPlayers();
    }


    /**
     * Caesar Cipher Encryt Methode
     * @param message de lijn die geenctrypteerd moet worden.
     * @return String de geecrypteerde lijn
     */
    private String encrypt(String message) {
        // https://stackoverflow.com/questions/29226813/simple-encryption-in-java-no-key-password

        // Reverse the string
        String reverse = new StringBuffer(message).reverse().toString();

        StringBuilder tmp = new StringBuilder();
        final int OFFSET = 4;
        for (int i = 0; i < reverse.length(); i++) {
            tmp.append((char) (reverse.charAt(i) + OFFSET));
        }
        return tmp.toString();
    }


    /**
     * Caesar Cipher Decrypt Methode
     * @param message de lijn die gedeenctrypteerd moet worden.
     * @return String de gedeecrypteerde lijn
     */
    private String decrypt(String message) {
        // https://stackoverflow.com/questions/29226813/simple-encryption-in-java-no-key-password
        StringBuilder tmp = new StringBuilder();
        final int OFFSET = 4;
        for (int i = 0; i < message.length(); i++) {
            tmp.append((char) (message.charAt(i) - OFFSET));
        }
        return new StringBuffer(tmp.toString()).reverse().toString();
    }


    /**
     * File highscores.txt inlezen en in een ArrayList steken.
     * Private omdat deze methode enkel in deze klasse gebruikt wordt.
     */
    private void readPlayers() {
        try {
            String[] c;
            for (String line : Files.readAllLines(location)) {
                c = decrypt(line).split(":");
                players.add(new Player(c[0], c[1], Integer.parseInt(c[2])));
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }


    /**
     * Methode om (nieuwe) gebruikers te registreren.
     * Er wordt een extra lijn toegevoegd in het highscores.txt bestand.
     *
     * @param username De gebruikersnaam van de player.
     * @param password Het wachtwoord van de player.
     * @return Player  Instantie van de klasse Player (die gaat spelen).
     * @throws Exception Als de gebruikersnaam al in gebruik is of als het een illegal character bevat (":").
     */
    protected void register(String username, String password) throws Exception {

        // Kijken of username al in gebruik is
        for (Player player : players) {
            if (player.getUsername().equalsIgnoreCase(username)) {
                throw new Exception("Username is already in use.");
            }
        }
        if (username.isEmpty() | password.isEmpty()) {
            throw new Exception("Fill in all fields.");
        }
        if (password.contains(":") | username.contains(":")) {
            throw new Exception("Credentials can not contain ':'");
        }

        // Als de username die de speler intikt niet in gebruik is komt hij hier:
        Player player = new Player(username, password);
        players.add(player);

        // Extra lijn toevoegen
        save();
    }


    /**
     * Zorgt ervoor dat de gebruiker zich kan inloggen.
     *
     * @param username De gebruikersnaam van de player.
     * @param password Het wachtwoord van de player.
     * @return Player  Instantie van de klasse Player (degene die gaat spelen).
     * @throws Exception Indien het wachtwoord onjuist is of de gebruiker niet bestaat.
     */
    public Player login(String username, String password) throws Exception {
        if (username.isEmpty() | password.isEmpty()) {
            throw new Exception("Fill in all fields.");
        }
        for (Player player : players) {
            if (player.getUsername().equalsIgnoreCase(username)) {
                if (player.getPassword().equals(password)) {
                    return player;
                } else {
                    throw new Exception("Incorrect credentials.");
                }
            }
        }
        throw new Exception("User: " + username + " does not exist.");
    }


    /**
     * Toont de Leaderboard.
     * Top 10 spelers: Naam + Highscore.
     *
     * @return String  Geformatteerde string(buffer) met de top 10 spelers.
     */
    public List<String> getLeaderboard() {
        List<String> array = new ArrayList<>();
        int index = 0;
        for (Player p : players.stream().sorted(Comparator.comparing(Player::getHighscore).reversed()).collect(Collectors.toList())) {
            if (index < 10) {
                array.add(p.getUsername() + ";" + p.getHighscore());
                index++;
            }
        }
        return array;
    }


    /**
     * Highscore van de huidige speler oplsaan in highscores.txt.
     * Toepassen nadat het spel beëindigd is.
     */

    public void save() throws IOException {
            // Extra lijn toevoegen
            StringBuilder gebruikers = new StringBuilder();
            for (Player pl : players) {
                gebruikers.append(encrypt(String.format("%s:%s:%d", pl.getUsername(), pl.getPassword(), pl.getHighscore()))).append("\n");
            }
            Files.write(location, gebruikers.toString().getBytes(StandardCharsets.UTF_8));
    }

}

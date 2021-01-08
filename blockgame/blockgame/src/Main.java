import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author Maxim Derboven
 * @version 1.0 9/12/2020 18:44
 */
public class Main {

    private static model.Game game;
    private static final Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args) {

        game = new model.Game(); //init

        System.out.print("-------------- \nMENU\n--------------\n");
        System.out.println("(1) Login");
        System.out.println("(2) Register");
        System.out.println("(3) Leaderboard");
        System.out.print("Choice : ");

        try {
            int choice = keyboard.nextInt();
            switch (choice) {
                case 1:
                    System.out.println();
                    System.out.print("-------------- \nLOGIN\n--------------\n");
                    System.out.print("Username: ");
                    String lusername = keyboard.next();

                    System.out.print("Password: ");
                    String lpassword = keyboard.next();

                    try {
                        game.login(lusername, lpassword);
                        menu();
                    } catch (Exception e) {
                        //e.printStackTrace();
                        System.out.println("\033[1;31m" + "Incorrect credentials" + "\033[0m");
                    }
                    break;

                case 2:
                    System.out.println();
                    System.out.print("-------------- \nREGISTER\n--------------\n");
                    System.out.print("Username: ");
                    String rusername = keyboard.next();

                    System.out.print("Password: ");
                    String rpassword = keyboard.next();

                    try {
                        game.register(rusername, rpassword);
                        menu();
                    } catch (Exception e) {
                        //e.printStackTrace();
                        System.out.println("\033[1;31m" + "Username already exists" + "\033[0m");
                    }
                    break;

                case 3:
                    System.out.println();
                    System.out.println("-------------- \nLEADERBOARD\n--------------");
                    System.out.print(game.getAm().getLeaderboard());
                    System.out.println("--------------");
                    break;

                default:
                    System.out.println("\033[1;31m" + "Please select a valid choice"+ "\033[0m");
                    break;
            }
        } catch (InputMismatchException e) {
            System.out.println("\033[1;31m" + "Please select a valid choice"+ "\033[0m");
        }
    }

    public static void menu() {
        System.out.println();
        System.out.print("-------------- \nMENU\n--------------\n");
        System.out.println("(1) Settings");
        System.out.println("(2) Start game");
        System.out.print("Choice : ");
        try {
            int choice = keyboard.nextInt();
            switch (choice) {
                case 1:
                    System.out.println();
                    System.out.print("-------------- \nSETTINGS\n--------------\n");
                    System.out.println("(1) board size: " + "\u001B[34m" + game.getBoard().getSize() + "\033[0m");
                    System.out.println("(2) with difficulty: " + "\u001B[34m" + game.getPlayablePieces().isDifficulty() + "\033[0m");
                    System.out.println("(3) playable pieces: " + "\u001B[34m" + game.getPlayablePieces().getCapacity() + "\033[0m");
                    System.out.println("(4) file location (experimental): " + "\u001B[34m" + game.getAm().getLocation() + "\033[0m");
                    System.out.println("(5) return");
                    System.out.print("Choice : ");
                    choice = keyboard.nextInt();
                    switch (choice) {
                        case 1:
                            System.out.println();
                            System.out.print("-------------- \nSETTINGS\n--------------\n");
                            System.out.print("board size: ");
                            game.getBoard().setSize(keyboard.nextInt());
                            menu();
                            break;
                        case 2:
                            System.out.println();
                            System.out.print("-------------- \nSETTINGS\n--------------\n");
                            System.out.print("[Highscores do not count when difficulty is disabled.]\n");
                            System.out.print("Difficulty (true/false): ");
                            game.getPlayablePieces().setDifficulty(keyboard.nextBoolean());
                            menu();
                            break;
                        case 3:
                            System.out.println();
                            System.out.print("-------------- \nSETTINGS\n--------------\n");
                            System.out.print("capacity : ");
                            game.getPlayablePieces().setCapacity(keyboard.nextInt());
                            menu();
                            break;
                        case 4:
                            System.out.println();
                            System.out.print("-------------- \nSETTINGS\n--------------\n");
                            System.out.print("location : ");
                            game.getAm().setLocation(keyboard.next());
                            menu();
                            break;
                        case 5:
                            menu();
                            break;
                    }
                    break;
                case 2:
                    System.out.println();
                    System.out.println("Loading game...");
                    try {
                        Thread.sleep(2000);
                        System.out.println("Loading scoreboard...");
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    game.start();
                    System.out.println("Game over: (╯°□°）╯︵ ┻━┻");
                    break;
            }
        } catch (InputMismatchException e) {
            System.out.println("\033[1;31m" + "Please select a valid choice"+ "\033[0m");
        }
    }
}
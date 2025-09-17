import java.util.Scanner;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.Serializable;

public class MedievalGame implements Serializable {
    private static final long serialVersionUID = 1L;
    
    /* Instance Variables */
    private Player player;
    
    /* Main Method */
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        MedievalGame game = new MedievalGame();
        game.player = game.start(console);
        
        game.addDelay(500);
        System.out.println("\nLet's take a quick look at you to make sure you're ready to head out the door.");
        System.out.println(game.player);
        
        game.addDelay(1000);
        System.out.println("\nWell, you're off to a good start, let's get your game saved so we don't lose it.");
        game.save();
        
        game.addDelay(2000);
        System.out.println("We just saved your game...");
        System.out.println("Now we are going to try to load your character to make sure the save worked...");
        
        game.addDelay(1000);
        System.out.println("Deleting character...");
        String charName = game.player.getName();
        game.player = null;
        
        game.addDelay(1500);
        game.player = game.load(charName, console);
        System.out.println("Loading character...");
        
        game.addDelay(2000);
        System.out.println("Now let's print out your character again to make sure everything loaded:");
        
        game.addDelay(500);
        System.out.println(game.player);
    } // End of main
    
    private void save() {
        String filename = player.getName() + ".svr";
        try (FileOutputStream userSaveFile = new FileOutputStream(filename);
             ObjectOutputStream playerSaver = new ObjectOutputStream(userSaveFile)) {
            playerSaver.writeObject(player);
        } catch (IOException e) {
            System.out.println("There was an error saving the game, your file might not be available the next time you go to load a game.");
        }
    }
    
    /* Instance Methods */
    private Player start(Scanner console) {
        Player player;
        Art.homeScreen();
        System.out.println("Welcome to the Medieval Game!");
        String answer;
        while (true) {
            System.out.print("Would you like to load a saved game? (Enter 'y' for yes or 'n' for no): ");
            answer = console.next().toLowerCase();
            
            if (answer.equals("y")) {
                System.out.print("Enter your previous character name: ");
                String charName = console.next();
                player = load(charName, console);
                break;
            } else if (answer.equals("n")) {
                System.out.print("Enter your desired character name: ");
                String charName = console.next();
                player = new Player(charName);
                break;
            } else {
                System.out.println("Please enter 'y' for yes or 'n' for no.");
            }
        }
        return player;
    } // End of start
    
    private Player load(String playerName, Scanner console) {
        Player loadedPlayer = null;
        try {
            FileInputStream userSaveFile = new FileInputStream(playerName + ".svr");
            ObjectInputStream playerLoader = new ObjectInputStream(userSaveFile);
            loadedPlayer = (Player) playerLoader.readObject();
            playerLoader.close();
        } catch (IOException | ClassNotFoundException e) {
            addDelay(1500);
            System.out.println("There was an error loading your saved game data. We are creating a new player with the name: " + playerName);
            addDelay(2000);
            loadedPlayer = new Player(playerName);
        }
        return loadedPlayer;
    } // End of load
    
    // Adds a delay to the console so it seems like the computer is "thinking"
    // or "responding" like a human, not instantly like a computer.
    private void addDelay(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

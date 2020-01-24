import java.util.*;

/**
 * Main method that runs the game. Repeatedly allows for user input, and prints
 * out proper information due to any given input. Stops when player has won or
 * decides to quit.
 * 
 * @author Bjorn
 *
 */
public class Main {

    /**
     * Method to represent player interaction with game.
     * 
     * @param args
     * 
     * @author Bjorn
     */
    public static void main(String[] args) {
        Player player = new Player();

        HashMap<String, Item> items = ReadIn.createItems();
        HashMap<String, Room> rooms = ReadIn.createRooms(items);
        HashMap<String, Score> scores = ReadIn.createScore();
        Scanner inp = new Scanner(System.in);
        System.out.println("Welcome to Untitled Bakery Game!");
        System.out.println("This is one of the most boring games ever made.");
        System.out.println("Your goal is to make a couple recipes in order to complete the game.");
        System.out.println("Good luck and hopefully you don't fall asleep while playing!");
        System.out.println();
        System.out.println(rooms.get(player.getLocation()).getDescription());

        while (true) { // allows for player to repeatedly enter input

            // scanner set up to read user input
            String input = inp.nextLine();
            input = input.toLowerCase();
            String[] command = input.split(" ");

            // if command is to take
            if (command[0].equals("take") || command[0].equals("get")) {
                // making sure input has command and item
                if (command.length >= 2) {
                    String temp = "";
                    // create string of object name
                    for (int i = 1; i < command.length; i++) {
                        temp = temp + command[i] + " ";
                    }
                    temp = temp.trim();

                    player.add(temp, rooms);

                    // check if we meet goal for taking object
                    player.checkScores(scores, "take " + temp);

                } else if (command.length == 1) { // else if no item
                    System.out.println("Sorry, but you can't take nothing.");
                    System.out.println();
                }

            } else if (command[0].equals("drop")) { // player wants to drop
                // making sure input has command and item
                if (command.length >= 2) {
                    String temp = "";
                    // create string of object name
                    for (int i = 1; i < command.length; i++) {
                        temp = temp + command[i] + " ";
                    }
                    temp = temp.trim();
                    player.drop(temp, rooms);

                } else if (command.length == 1) { // else if no item
                    System.out.println("Sorry, but you can't drop nothing.");
                    System.out.println();
                }
            
            } else if (command[0].equals("bake")) { // if want to bake recipe
                // making sure input has command and recipe   
                if (command.length >= 2) {
                    String temp = "";
                    // create string of object name
                    for (int i = 1; i < command.length; i++) {
                        temp = temp + command[i] + " ";
                    }
                    temp = temp.trim();
                    
                    player.bake(temp);
                    
                    // check if we meet goal for baking object
                    player.checkScores(scores, "bake " + temp);
                    
                } else if (command.length == 1) { // else if no item
                    System.out.println("Sorry, but you can't bake nothing.");
                    System.out.println();
                }
                
            } else if (command[0].equals("look")) { // if player wants to look
                // if player wants to look at object
                if (command.length >= 2) {
                    String temp = "";
                    // create string of object name
                    for (int i = 1; i < command.length; i++) {
                        temp = temp + command[i] + " ";
                    }
                    temp = temp.trim();
                    player.look(temp);
                    System.out.println();
                } else if (command.length == 1) { // else want to look at room
                    player.look(rooms);
                    System.out.println();
                } 

            } else if (command[0].equals("move") || command[0].equals("go")) { // player wants to move
                if (command.length >= 2) {
                    player.move(command[1], rooms);
                    System.out.println(rooms.get(player.getLocation()).getDescription());
                } else if (command.length == 1) { // else if no direction
                    System.out.println("Sorry, but you can't move to nothing");
                    System.out.println();
                }
                
            } else if (command[0].equals("north") || command[0].equals("east") || command[0].equals("south")
            || command[0].equals("west") || command[0].equals("up") || command[0].equals("down")) { // wants to move, only by giving direction
                player.move(command[0], rooms);
                System.out.println(rooms.get(player.getLocation()).getDescription());
            } else if (command.length == 1) { // else
                if (command[0].equals("score")) {// if want to check score
                    System.out.println(player.getScore());
                    System.out.println();
                } else if (command[0].equals("inventory")) { // if want to check inventory
                    player.getInventory();
                    System.out.println();
                } else if (command[0].equals("back")) { // if want to go back to previous room
                    player.back(rooms);
                } else if (command[0].equals("about")) { // if want to see about game
                    System.out.println("This game is made by Bjorn and Minco");
                    System.out.println("It was made due to a school assignement");
                    System.out.println("We hope you enjoy our game as much as we do");
                    System.out.println("For any questions feel free to contact us.");
                    System.out.println("You can find our contact information in the Technical Notes document.");
                } else if (command[0].equals("quit")) { // if want to quit
                    System.out.println("Thank you for playing!");
                    System.exit(0);
                }
            } else { // else no such command
                System.out.println("No such command, please try again");
                System.out.println();
            }

            // if we have completed all goals
            if (scores.isEmpty()) {
                System.out.println("Congratulations! You have made both the recipes required to feel " + '\n'
                    + "good about yourself. You store the recipes on the kitchen and you make yourself " + '\n'
                    + "ready to go home.");
                System.out.println();
                System.out.println("You finished with a total score of " + player.getScore());
                System.exit(0);
            }
        }
    }
}

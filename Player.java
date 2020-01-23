import java.util.*;

/**
 * Class made to represent a player in the game "Untitled Bakery Game." Will Contain
 * information about the players location, as well as what they have in their
 * inventory. Methods include move, to move the player throughout he world, as
 * well as getters for instance variables.
 * 
 * @author Bjorn and Minco
 *
 */
public class Player {
    private static Stack<String> previousRooms = new Stack<>();
    private static String location_; // name of room player is in
    private static Map<String, Item> inventory_ = new HashMap<String, Item>(); // inventory of items
    private static int score_ = 0; // overall score

    /**
     * Constructor that initializes the player location to the first room.
     * 
     * @author Bjorn
     */
    public Player() {
        location_ = "Kitchen"; // set to first room
    }

    /**
     * Method that will be used to add current player location into stack.
     * 
     * @param room
     *            room that represents location of player
     * @author Bjorn
     */
    public void addRoom(String room)
    {
        previousRooms.push(room);
    }
    
    /**
     * Move is a method that will be responsible for moving the player from the
     * room they are currently in, to any of the possible adjacent rooms. The direction
     * must be north, south, east, west, up or down.
     * 
     * @param direction
     *            string to represent direction player wishes to travel
     * @param rooms
     *            list that contains all the room objects created
     * @author Bjorn & Minco
     */
    public void move(String direction, HashMap<String, Room> rooms) {
        direction = direction.toLowerCase();
        Room current = rooms.get(location_);

        String[] temp = current.getNeighbors();

        if (direction.equals("north")) { // if direction is north...
            if (!temp[0].equals("-")) { // if there is a room in said direction
                addRoom(location_);
                location_ = temp[0];
            } else {
                System.out.println("Sorry, cannot go this way, try again!");
                System.out.println();
            }
        } else if (direction.equals("east")) { // if direction is east...
            if (temp[1].equals("Trap Room")) {
                addRoom(location_);
                goRandomRoom();
            } else if (!temp[1].equals("-")) { // if there is a room in said direction
                addRoom(location_);
                location_ = temp[1];
            } else {
                System.out.println("Sorry, cannot go this way, try again!");
                System.out.println();
            }
        } else if (direction.equals("south")) { // if direction is south...
            if (!temp[2].equals("-")) { // if there is a room in said direction
                addRoom(location_);
                location_ = temp[2];
            } else {
                System.out.println("Sorry, cannot go this way, try again!");
                System.out.println();
            }
        } else if (direction.equals("west")) { // if direction is west...
            if (temp[3].equals("Trap Room")) {
                addRoom(location_);
                goRandomRoom();
            } else if (!temp[3].equals("-")) { // if there is a room in said direction
                addRoom(location_);
                location_ = temp[3];
            } else {
                System.out.println("Sorry, cannot go this way, try again!");
                System.out.println();
            }
        } else if (direction.equals("up")) { // if direction is up...
            if (temp[4].equals("Warehouse") && !inventory_.containsKey("key")) { // if there is a room in said direction
                System.out.println("You need a key to unlock the door");
                System.out.println();
            } else if (!temp[4].equals("-")) { // if there is a room in said direction
                addRoom(location_);
                location_ = temp[4];
            } else {
                System.out.println("Sorry, cannot go this way, try again!");
                System.out.println();
            }
        } else if (direction.equals("down")) { // if direction is down...
            if (temp[5].equals("Basement") && !inventory_.containsKey("key")) {
                System.out.println("You need a key to unlock the door");
                System.out.println();
            } else if (!temp[5].equals("-")) { // if there is a room in said direction
                addRoom(location_);
                location_ = temp[5];
            } else {
                System.out.println("Sorry, cannot go this way, try again!");
                System.out.println();
             }
        } else { // else
            System.out.println("Sorry, not a valid direction, try again!");
            System.out.println();
        }
    }

    /**
     * Back is a method that will be responsible for returning the player from
     * the room they are currently in, to the room they were previously.
     * 
     * @param rooms
     *            stack that contains all the previous visited rooms
     * @author Bjorn
     */
    public void back(HashMap<String, Room> rooms)
    {
        if (previousRooms.size() > 0) { // if stack is greater then zero
            location_ = previousRooms.pop();
            System.out.println(rooms.get(getLocation()).getDescription());
        }
        else { // else
            System.out.println("There is nowhere to go back to...");
            System.out.println();
        }
    }
    
    /**
     * Method that will be used to random determine which room the player will
     * be put in. It will always have a random number.
     * 
     * @author Bjorn
     */
    public void goRandomRoom() {
        int random = (int)(Math.random() * 8);
        
        // select a random room
        if (random == 1) {
            location_ = "Kitchen";
        } else if (random == 2) {
            location_ = "Cooling Space";
        } else if (random == 3) {
            location_ = "Oven Room";
        } else if (random == 4) {
            location_ = "Recipes Room";
        } else if (random == 5) {
            location_ = "Warehouse";
        } else if (random == 6) {
            location_ = "Transport Platform";
        } else if (random == 7) {
            location_ = "Hallway";
        } else if (random == 8) {
            location_ = "BreakRoom";
        }
        System.out.println("Oh no! There is a blackhole sucking you in it... It brings you to a random room...");
        System.out.println();
    }
    
    /**
     * Method that will be used to add an object from the room you are in to
     * your inventory. Object must be in room you are in.
     * 
     * @param item
     *            represents item you wish to add to your inventory
     * @author Bjorn & Minco
     */
    public void add(String item, HashMap<String, Room> rooms) {
        // check whether object is in room you are in
        Room current = rooms.get(location_);

        Item temp = null;
        if (inventory_.size() < 3) {
            if (current.getInventory().containsKey(item)) { // item is in room
                if (item.equals("paper of truth") || item.equals("ugandan knuckles") || item.equals("piece of paper")
                || item.equals("ok boomer") || item.equals("the boys") || item.equals("broken bottle")) {
                    System.out.println("You can't take this item");
                    System.out.println();
                } else {
                    temp = current.getInventory().get(item);
        
                    current.getInventory().remove(item);
                    inventory_.put(temp.getName(), temp);
                    System.out.println(item + " was successfully added");
                    System.out.println();
                }
            } else { // else
                System.out.println("Sorry, " + item + " is not in the room.");
                System.out.println();
            }
        } else {
            System.out.println("Sorry, but you can't carry any more items.");
            System.out.println();
        }
    }

    /**
     * Method that will be responsible for dropping an item from you inventory
     * to the room you are in. Must already have object in your inventory.
     * 
     * @param item
     *            represents name of item you wish to drop
     * @author Bjorn & Minco
     */
    public void drop(String item, HashMap<String, Room> rooms) {
        Item temp = null;
        
        if (inventory_.containsKey(item)) { // if item is in inventory
            if (!item.equals("key")) {
                temp = inventory_.get(item);
                inventory_.remove(item);
    
                Room current = rooms.get(location_);
    
                current.getInventory().put(temp.getName(), temp);
                System.out.println(item + " was successfully dropped");
                System.out.println();
            } else {
                System.out.println("You can't drop your key, because you need it to open the door");
                System.out.println();
            }
        } else { // else
            System.out.println("Sorry, " + item + " is not in your inventory.");
            System.out.println();
        }
    }
    
    /**
     * Method that will be responsible for baking the specified recipe from your
     * inventory when you are in the kitchen. Must already have specified recipe
     * in your inventory.
     * 
     * @param item
     *            represents recipe you wish to bake from your inventory
     * @author Bjorn
     */
    public void bake(String item) {
        Item temp = null;
        
        if (getLocation().equals("Kitchen")) { // if location player is kitchen
            if (item.equals("strawberry cake mix")) {
                if (inventory_.containsKey("strawberry cake mix") && inventory_.containsKey("baking powder")) {
                    temp = inventory_.get(item);
                    inventory_.remove(item);
                    
                    temp = inventory_.get("baking powder");
                    inventory_.remove("baking powder");
                    
                    System.out.println("Congratulations! You have made the strawberry cake!");
                    System.out.println();
                } else {
                    System.out.println("You don't have all the required ingredients");
                    System.out.println();
                }
            } else if (item.equals("apple pie mix")) {
                if (inventory_.containsKey("apple pie mix") && inventory_.containsKey("flower")) {
                    temp = inventory_.get(item);
                    inventory_.remove(item);
                    
                    temp = inventory_.get("flower");
                    inventory_.remove("flower");
                    
                    System.out.println("Congratulations! You have made the apple pie!");
                    System.out.println();
                } else {
                    System.out.println("You don't have all the required ingredients");
                    System.out.println();
                }
            } else {
                System.out.println("That is not a recipe you can make");
                System.out.println();
            }
        } else {
            System.out.println("Sorry, but you need to be in the kitchen if you want to make any of the recipes.");
            System.out.println();
        }
    }
        
    /**
     * Getter method that returns the players location.
     * 
     * @return 
     *          String representing name of room player is in
     * @author Bjorn
     */
    public String getLocation() {
        return location_;
    }

    /**
     * Getter method that prints out the objects in the players inventory.
     * 
     * @author Minco
     */
    public void getInventory() {
        if (inventory_.isEmpty()) { // if inventory is empty
            System.out.println("Inventory is empty");
        } else { // else
            for (Map.Entry<String, Item> elt : inventory_.entrySet()) {
                System.out.println(elt.getKey());
            }
        }
    }

    /**
     * Method that gets an items description if it is in your inventory.
     * 
     * @param item
     *          name of item
     * @author Minco
     */
    public void look(String item) {
        if (item.equals(inventory_.get(item))) {
            System.out.println(inventory_.get(item).getDescription());
        } else {
            System.out.println("You don't have that item in your inventory");
        }
    }

    /**
     * Method that gets description of room currently in.
     * 
     * @param rooms
     *          collection of rooms in game
     * @author Bjorn
     */
    public void look(HashMap<String, Room> rooms) {
        rooms.get(location_).look();
    }

    /**
     * Getter method that returns players score.
     * 
     * @return
     *          int representing score
     * @author Minco
     */
    public int getScore() {
        return score_;
    }
    
    /**
     * Method used to check whether a given action from player warrants giving them
     * points.
     * 
     * @param scores
     *          collection of Score objects
     * @param ref
     *          represents string used for finding particular key value
     * @author Minco
     */
    public void checkScores(HashMap<String, Score> scores, String ref) {
        if (scores.containsKey(ref)) { // if scores contains key
            score_ = score_ + scores.get(ref).getScore();
            scores.remove(ref);
        }
    }
}

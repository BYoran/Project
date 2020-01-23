
/**
 * A class that will represent one object from the game. Will include constructor as
 * well as getter methods for important information about the objects.
 * 
 * @author Minco
 *
 */
public class Item {
    private String name_; // name of item
    private String description_; // short description
    private String location_; // name of room item starts in

    /**
     * Constructor that initializes objects name, description, and starting location
     * 
     * @param name
     *          represents objects name
     * @param descrip
     *          represents objects description
     * @param location
     *          represents objects starting location
     * @author Minco
     */
    public Item (String name, String descrip, String location) {
        name_ = name;
        description_ = descrip;
        location_ = location;
    }
    
    /**
     * Method used to print out the items description for player to read.
     * 
     * @author Minco
     */
    public void look() {
        System.out.println(description_);
    }
        
    /**
     * Getter method that returns the items name.
     * 
     * @return
     *          String representing items name
     * @author Minco
     */
    public String getName () {
        return name_;
    }
    
    /**
     * Getter method that returns items starting location.
     * 
     * @return
     *          String representing items starting location
     * @author Minco
     */
    public String getLocation () {
        return location_;
    }
    
    /**
     * Getter method that returns items description.
     * 
     * @return
     *          String representing items description
     * @author Minco
     */
    public String getDescription () {
        return description_;
    }
}

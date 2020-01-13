 

/**
 * A class that will represent one object from the world. Will include constructor as
 * well as getter methods for important information about the objects.
 * 
 * @author 
 *
 */
public class Item {
    private String name_; //name of item
    private String description_; //short description
    private String location_; //name of room item starts in

    /**
     * Constructor that initializes objects name, description, and starting location
     * 
     * @param name
     *          represents objects name
     * @param descrip
     *          represents objects description
     * @param location
     *          represents objects starting location
     */
    public Item (String name, String descrip, String location) {
        name_ = name;
        description_ = descrip;
        location_ = location;
    }
    
    /**
     * Method used to print out the items description for player to read.
     */
    public void look() {
        System.out.println(description_);
    }

    /**
     * Method that creates the items 
     *
     */
    
     public void createItem() {
         Item Strawberry_cake_mix, Apple_pie_mix, Key;
         Strawberry_cake_mix = new Item("Stawberry cake mix","A cake mix for strawberry cake. It's one of those simplistic mixes where the only thing you have to add is water.So easy even you couln't fack it up", "basement");
         Apple_pie_mix = new Item("Apple pie mix", "A pie mix for apple pie. It's an very simple mix, you only have to add water. Who knows where the apples come from?!", "basement");
         Key = new Item("key", "a very keylike key", "warehouse");

        }
        
    /**
     * Getter method that returns the items name.
     * @return
     *      String representing items name.
     */
    public String getName () {
        return name_;
    }
    
    /**
     * Getter method that returns items starting location.
     * @return
     *      String representing items starting location.
     */
    public String getLocation () {
        return location_;
    }
    
    /**
     * Getter method that returns items description.
     * @return
     *      String representing items description.
     */
    public String getDescription () {
        return description_;
    }
}
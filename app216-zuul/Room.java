import java.util.Set;
import java.util.HashMap;
import java.util.ArrayList;

/*
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighboring room.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 1.0 (February 2002)
 */

public class Room 
{
    private String description;
    private HashMap <String, Room> exits;        // stores exits of this room.
    private ArrayList<Item> items;

    /**
     * Create a room described "description". Initially, it has no exits.
     * "description" is something like "in a kitchen" or "in an open court 
     * yard".
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<String, Room>();
        items = new ArrayList<Item>();      // added by pld
    }

    /**
     * Define an exit from this room.
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }

    /**
     * Return the description of the room (the one that was defined in the
     * constructor).
     */
    public String getShortDescription()
    {
        return description;
    }

    /**
     * Return a long description of this room, in the form:
     *     You are in the kitchen.
     *     Exits: north west
     * Note that this returns a single long (multiline) String!
     */
    public String getLongDescription()
    {
        String longDesc =  "You are " + description + ".\n" + getExitString();
        if (items.size() > 0) {
            longDesc += "\nThe following things are here:\n";
            for (Item item : items) {
                longDesc += "\t" + item.getDesc() + "\n";
            }
        }
        return longDesc;
    }

    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     * Note use of keySet() method!
     */
    private String getExitString()
    {
        String returnString = "Exits:";
        Set<String> directions = exits.keySet();
        for(String dir : directions)
            returnString += " " + dir;          // append the string
        return returnString;
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     */
    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }
    
    /**
     * add an item to the room
     */
    public void addItem(Item i) {
        items.add(i);
    }
}



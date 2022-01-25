import java.util.ArrayList;

/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 1.0 (February 2002)
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private ArrayList<Item> inventory;
    
    private final int MAXWEIGHT = 10;       // max weight of items on inventory
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        inventory = new ArrayList<Item>();
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room outside, forrest, pub, lab, office;
      
        // create the rooms
        outside = new Room("outside the main entrance of the university");
        forrest = new Room("in a lecture theatre");
        forrest.addItem(new Item("wood", 2));
        pub = new Room("in the campus pub");
        pub.addItem(new Item("water", 3));
        lab = new Room("in a computing lab");
        office = new Room("in the computing admin office");
        office.addItem(new Item("memo", 1));
        office.addItem(new Item("laptop", 7));
        
        // initialise room exits
        outside.setExit("east", forrest);
        outside.setExit("south", lab);
        outside.setExit("west", pub);

        forrest.setExit("west", outside);

        pub.setExit("east", outside);

        lab.setExit("north", outside);
        lab.setExit("east", office);

        office.setExit("west", lab);

        currentRoom = outside;  // start game outside
        
        inventory.add(new Item("flashlight", 4));
        inventory.add(new Item("pen", 1));
        inventory.add(new Item("cellphone"));
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * If this command ends the game, true is returned, otherwise false is
     * returned.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help"))
            printHelp();
        else if (commandWord.equals("go"))
            goRoom(command);
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        } else if (commandWord.equals("inventory")) {
            printInventory();
        } else if (commandWord.equals("take")) {
            doTake(command);
        } else if (commandWord.equals("drop")) {
            doDrop(command);
        } else if (commandWord.equals("look")) {
            System.out.println(currentRoom.getLongDescription());
        }
        
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /** 
     * Try to go to one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null)
            System.out.println("There is no door!");
        else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game. Return true, if this command
     * quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else
            return true;  // signal that we want to quit
    }
    
    /**
     * printInventory prints the contents of "inventory"
     */
    private void printInventory() {
        if (inventory.size() == 0) {
            System.out.println("you are not carrying anything");
        } else {
            System.out.print("You have the following:");
            for (int n = 0; n < inventory.size(); n++) {
                Item item = inventory.get(n);
                System.out.print(" " + item.getDesc());
            }
            System.out.println();
            System.out.println("Total weight: " + totalWeight(inventory));
        }
    }
    
    /**
     * findByName: given a string and an ArrayList of Items, 
     * find the Item with the matching name, or else return null
     */
    private Item findByName(String s, ArrayList<Item> L) {
        int n=0;
        while (n < L.size()) {
            Item i = L.get(n);
            if (s.equals(i.getDesc())) 
                return i;
            n++;
        }
        return null;    // not found above
    }
   
    
    /**
     * totalWeight: given an ArrayList of Items, returns total of weights
     */
    private int totalWeight(ArrayList<Item> L) {
        int n=0;
        int sum = 0;
        while (n < L.size()) {
            Item i = L.get(n);
            sum += i.getWeight();
            n++;
        }
        return sum;    // not found above
    }
   
    
    /**
     * doDrop: 
     *   1. checks if user specified a thing to be dropped
     *   2. checks that it's there in inventory
     *   3. deletes that item from inventory, and adds to currentRoom
     *   4. prints a message about it.
     */
    public void doDrop(Command c) {
        if (! c.hasSecondWord()) {  // "DROP",but no object named
            System.out.println("Drop what?");
            return;
        }
        String s = c.getSecondWord();
        Item i = findByName(s, inventory);
        if (i == null) {
            System.out.println("You don't have a " + s);
            return;
        }
        inventory.remove(i);    
        currentRoom.addItem(i);
        System.out.println("You have dropped the " + s);
    }
    
     /**
     * doTake: doesn't do anything yet
     */
    public void doTake(Command c) {
        System.out.println("Doesn't do anything yet.");
    }
    
}

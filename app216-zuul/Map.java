
/**
 * This class is reponsible for creating and
 * linking all the Locations in the game to
 * form a 2D or 3D network
 *
 *  [gate]<---->[forrest]<---->[cave]
 *                 |
 *               [lake]<---->[camp]
 *                 ^
 *                 v
 * [mountain]<->[field]<->[cemetery]
 *             
 * @author Derek Peacock and Nicholas Day
 * @version 2021-08-22
 */
public class Map
{
    // Need to add a list of exits

    private Location gate, forrest, cave, lake, camp, field, mountain, cemetery; 

    private Location currentLocation;

    /**
     * Constructor for objects of class Map
     */
    public Map()
    {
        createLocations();
    }

    /**
     * Create all the Locations and link their exits together.
     * Set the current location to the outside.
     * Both locations need to have been created before
     * their exists are linked.
     */
    private void createLocations()
    {
        createForrest();
        createGate();
        createCave();
        createLake();
        createCamp();
        createField();
        createCemetery();
        createMountain();

        currentLocation = forrest;  // start game at the gate
    }

    /**
     * Create the outside and link it to the
     * theatre, lab and pub
     */
    private void createGate()
    {
        gate = new Location("outside the main gate of the forrest");
        
        gate.setExit("east", forrest);
        forrest.setExit("west", gate);
    
    }

    private void createForrest()
    {
        forrest = new Location("outskirts of a forrest");
    }

    /**
     * Create the cave and link it to the forrest
     */
    private void createCave()
    {
        cave = new Location("in a dark cave");
        
        cave.setExit("west", forrest);
        forrest.setExit("east", cave);
    }

    /**
     * Create the lake linked to the forrest
     */
    private void createLake()
    {
        lake = new Location("on the bank of a lake");

        lake.setExit("north", forrest);
        forrest.setExit("south", lake);
    }

    private void createCamp()
    {
        // create the Locations
        camp = new Location("in a barrem camp");

        camp.setExit("west", lake);
        lake.setExit("east", camp);
    }

    /**
     * Create the office linked to the lab
     */
    private void createField()
    {
        field = new Location("in a large open field");
         
        field.setExit("north", lake);
        lake.setExit("south", field);

    }

    /**
     * Create the lab and link it to the outside and office
     */
    private void createCemetery()
    {
        // create the Locations
        cemetery = new Location("in a cemetery");

        cemetery.setExit("west", field);
        field.setExit("east", cemetery);
    }

    private void createMountain()
    {
        // create the Locations
        mountain = new Location("at the base of a tall mountain");

        mountain.setExit("east", field);
        field.setExit("west", mountain);
    }

    public Location getCurrentLocation()
    {
        return currentLocation;
    }

    public void enterLocation(Location nextLocation)
    {
        currentLocation = nextLocation;
    }
}


/**
 * Class for portable Items in Zuul.
 * This is a "trivial" class: Two immutable fields, accessors for each
 * 
 * @author Peter Dordal 
 * @version 1.0
 */
public class Item
{
	// description should have no spaces
	private String description;
	private int    weight;

	/**
	 * Constructor for objects of class Item
	 */
	public Item(String d)
	{
		description = d;
		weight = 0;
	}

	/**
	 * Constructor for Items with nonzero weight
	 */
	public Item(String d, int w) {
	    description = d;
	    weight = w;
	}
	/**
	 * getDesc: accessor for description
	 * 
	 */
	public String getDesc()
	{
		return description;
	}
	
	/**
	 * getWeight: accessor for weight
	 * 
	 */
	public int getWeight()
	{
		return weight;
	}
}

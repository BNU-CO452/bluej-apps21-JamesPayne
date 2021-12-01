
/**
 * This app provides a user interface to the
 * stock manager so that users can add, edit,
 * print and remove stock products
 *
 * @author James Payne
 * @version 0.1
 */
public class StockApp
{
    private InputReader reader;

    private StockList stock;

    /**
     * Constructor for objects of class StockApp
     */
    public StockApp()
    {
        reader = new InputReader();

        stock = new StockList();
        //StockDemo demo = new StockDemo(stock);
    }

    /**
     *  Display a list of menu choices and execute
     *  the user's choice.
     */
    public void run()
    {
        boolean finished = false;

        while(!finished)
        {
            printHeading();
            printMenuChoices();

            String choice = reader.getString("Please enter your choice > ");

            finished = executeChoice(choice.toLowerCase());
        }
    }

    private boolean executeChoice(String choice)
    {
        if(choice.equals("quit"))
        {
            return true;
        }
        //Adds a product to the list
        else if(choice.equals("add"))
        {
            int id = reader.getInt("Please enter the ID for the product");
            String name = reader.getString("please enter a name for the prduct");
            Product product = new Product(id, name);
            stock.add(product);
            System.out.println("product " + product.getID()
                +  product.getName() + " has been added");
        }
        //Deletes the product from the list 
        else if(choice.equals("remove"))
        {
            int id = reader.getInt("Please enter the ID of the product");
            String name = reader.getString("please enter the name of the product");
            Product product = stock.getProduct(id, name);
            if (product == null){
                System.out.println("Product does not exist");
                return false;
            }
            stock.remove(product);
            System.out.println("product " + product.getID()
                +  product.getName() + " has been removed");
        }
        //Buys a certain amount of the product
        else if(choice.equals("buy"))
        {
            int id = reader.getInt("enter the id you wish to buy");
            int amount = reader.getInt ("enter the quantitiy you wish to buy");
            if(1 <= amount && amount <=10)
            {
                stock.buyProduct(id, amount);
                System.out.println("Succesfully Purchased" + id + amount);

            }
        }
        //Sells a certain amount of the product
        else if(choice.equals("sell"))
        {
            int id = reader.getInt("enter the id you wish to sell");
            int amount = reader.getInt ("enter the quantitiy you wish to sell");
            if(1 <= amount && amount <=10)
            {
                stock.sellProduct(id, amount);
                System.out.println("Succesfully Sold" + id + amount);

            }
        }
        //Prints the stock list
        else if(choice.equals("print"))
        {
            stock.print();
        }
        else if(choice.equals("search"))
        {
            String phrase = reader.getString("enter the name of the product");
        }
            

        return false;
    }

    /**
     * Print out a menu of operation choices
     */
    private void printMenuChoices()
    {
        System.out.println();
        System.out.println("    Add:        Add a new product");
        System.out.println("    Remove:     Remove an old product");
        System.out.println("    Print:      Print all products");
        System.out.println("    Quit:       Quit the program");
        System.out.println("    Buy:        Buy a product");
        System.out.println("    Sell:       Sell a product");
        System.out.println();        
    }

    /**
     * Print the title of the program and the authors name
     */
    private void printHeading()
    {
        System.out.println("********************************");
        System.out.println("  App21-04: Stock Application ");
        System.out.println("      by James Payne");
        System.out.println("********************************");
    }
}
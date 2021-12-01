
/**
 * Demonstrate the StockManager and Product classes.
 * The demonstration becomes properly functional as
 * the StockManager class is completed.
 * 
 * @author James Payne
 * @version 2021.12.1
 */
public class StockDemo
{
    // The stock manager.
    private StockList stock;

    /**
     * Create a StockManager and populate it with at least
     * 10 sample products.
     */
    public StockDemo(StockList stock)
    {
        this.stock = stock;
        
        // Add at least 10 products, they must be unique to you
        // Make sure the ids are sequential numbers
        
        stock.add(new Product(101, "Apple"));
        stock.add(new Product(102, "Orange"));
        stock.add(new Product(103, "Banana"));
        stock.add(new Product(104, "Grape"));
        stock.add(new Product(105, "Peach"));
        stock.add(new Product(106, "Strawberry"));
        stock.add(new Product(107, "BlueBerry"));
        stock.add(new Product(108, "Pear"));
        stock.add(new Product(109, "Melon"));
        stock.add(new Product(110, "Cucumber"));
    }
    
    /**
     * Provide a demonstration of how the ProductList meets all
     * the user requirements by making a delivery of each product 
     * buying it in various amounts and then selling each
     * product by various amounts. Make sure all the requirements
     * have been demonstrated.
     */
    public void runDemo()
    {
        // Show details of all of the products before delivery.
        
        stock.print();

        buyProduct();
        stock.print();        

        sellProducts();
        stock.print();        
    }
    
    private void buyProduct()
    {
        stock.buyProduct (102, 4);
        stock.buyProduct (103, 8);
        stock.buyProduct (110, 90);
        stock.buyProduct (101, 9);
        stock.buyProduct (104, 81);
        stock.buyProduct (105, 18);
        stock.buyProduct (106, 1);
        stock.buyProduct (107, 811);
        stock.buyProduct (108, 0);
        stock.buyProduct (109, 811);
         
    }

    private void sellProducts()
    {
        stock.sellProduct (102, 2);
        stock.sellProduct (103, 7);
        stock.sellProduct (110, 60);
        stock.sellProduct (101, 5);
        stock.sellProduct (104, 61);
        stock.sellProduct (105, 13);
        stock.sellProduct (106, 10);
        stock.sellProduct (107, 81);
        stock.sellProduct (108, 0);
        stock.sellProduct (109, 131);
    }    
}
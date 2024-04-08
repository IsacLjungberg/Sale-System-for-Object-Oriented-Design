package integration;

import database.PseudoDB;


/**
 * Integration class responsible for communication between the application and external systems.
 */
public class Integration {
    private PseudoDB database;

    /**
     * Constructs an Integration object with the specified PseudoDB instance.
     *
     * @param database the pseudo database for integration
     */
    public Integration(PseudoDB database){
        this.database = database;
    }

    /**
     * Fetches an item from the database based on its ID.
     *
     * @param id the ID of the item to fetch
     * @return the ItemDTO object representing the fetched item
     * 
     * @exception DatabaseNotFoundException if database can not be reached, here only for a specific item ID
     * @exception ItemNotFoundException if item ID matches item in database 
     */
    public ItemDTO fetchItem(int id) throws ItemNotFoundException, DatabaseNotFoundException{
        if(id == 404){
            throw new DatabaseNotFoundException("Database could not be reached");
        }
        ItemDTO out = database.fetchItem(id);
        if(out == null){
            throw new ItemNotFoundException("Item could not be found in Item database");
        } else {
            return out;
        }
    }

    /**
     * Registers a sale by saving the SaleDTO to the database.
     *
     * @param saleDTO the SaleDTO object representing the sale to be registered
     */
    public void registerSale(SaleDTO saleDTO){
        database.saveSale(saleDTO);
    }

    /**
     * Fetches a previous sale from the database
     *
     * @param index the index of the sale to fetch
     * @return the SaleDTO object representing the fetched sale
     */
    public SaleDTO fetchSale(int index){
        return database.fetchSale(index);
    }


    /**
     * Fetches the most recently added sale
     *
     * @return the SaleDTO object representing the fetched sale
     */
    public SaleDTO fetchLatestSale(){
        return database.fetchSale(numberOfSales()-1);
    }

    private int numberOfSales(){
        return database.getSales().size();
    }
}

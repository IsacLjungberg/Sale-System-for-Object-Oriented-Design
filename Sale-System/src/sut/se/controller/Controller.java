package se.controller;

import se.integration.Integration;
import se.integration.Printer;
import se.integration.SaleDTO;
import se.model.Sale;

/**
 * Controller class responsible for managing sale transactions.
 */
public class Controller {
    private Integration integration;
    private Printer printer;
    private Sale currentSale;
        
    /**
     * Constructs a Controller object with the specified Integration and Printer instances.
     *
     * @param integration the integration instance for communication with external systems
     * @param printer the printer instance for printing receipts
     */
    public Controller(Integration integration, Printer printer){
        this.integration = integration;
        this.printer = printer;
    }

    /**
     * Starts a new sale transaction by initializing a new Sale object.
     */
    public void startSale(){
        currentSale = new Sale(integration);
    }

    /**
     * Scans an item and adds it to the current sale with the specified quantity.
     * 
     * @param id the ID of the item to be scanned
     * @param quantity the quantity of the item to be added
     */
    public void scanItem(int id, int quantity){
        if (quantity > 0) {
            currentSale.addItem(id, quantity);
        }
    }
    
    /**
     * Finalizes the current sale transaction by setting the amount paid, creating a SaleDTO, registering the sale,
     * printing the receipt, and resetting the current sale.
     *
     * @param amountPaid the amount paid by the customer
     */
    public void finalizeSale(int amountPaid){
        currentSale.finalize(amountPaid);
        SaleDTO saleDTO = currentSale.createSaleDTO();
        currentSale = null;

        integration.registerSale(saleDTO);
        printer.printReceipt(saleDTO);
    }
    
    /**
     * Resets the current sale transaction, discarding any scanned items.
     */
    public void resetSale(){
        currentSale = null;
    }

    /**
     * @return the current sale
     */
    public Sale getCurrentSale(){
        return currentSale; 
    }
}

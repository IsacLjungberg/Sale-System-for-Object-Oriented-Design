package se.kth.salesystem.controller;

import se.kth.salesystem.integration.DBHandler;
import se.kth.salesystem.integration.Printer;
import se.kth.salesystem.integration.SaleDTO;
import se.kth.salesystem.model.Sale;

/**
 * Controller class responsible for managing sale transactions.
 */
public class Controller {
    private DBHandler integration;
    private Printer printer;
    private Sale currentSale;

    /**
     * Constructs a Controller object with the specified Integration and Printer
     * instances.
     *
     * @param integration the integration instance for communication with external
     *                    systems
     * @param printer     the printer instance for printing receipts
     */
    public Controller(DBHandler integration, Printer printer) {
        this.integration = integration;
        this.printer = printer;
    }

    /**
     * Starts a new sale transaction by initializing a new Sale object.
     */
    public void startSale() {
        currentSale = new Sale(integration);
    }

    /**
     * Scans an item and adds it to the current sale with the specified quantity.
     * 
     * @param id       the ID of the item to be scanned
     * @param quantity the quantity of the item to be added
     * @return SaleDTO representing the current sale
     */
    public SaleDTO scanItem(int id, int quantity) {
        if (quantity > 0) {
            currentSale.addItem(id, quantity);
        }

        return fetchCurrentSaleDTO();
    }

    /**
     * Converts the current sale to a SaleDTO object.
     * 
     * @return a SaleDTO object representing the current state of the sale
     */
    public SaleDTO fetchCurrentSaleDTO() {
        if(currentSale != null){
            return currentSale.createSaleDTO();
        } else {
            return null;
        }
    }

    /**
     * Finalizes the current sale transaction by setting the amount paid, creating a
     * SaleDTO, registering the sale,
     * printing the receipt, and resetting the current sale.
     *
     * @param amountPaid the amount paid by the customer
     */
    public void finalizeSale(int amountPaid) {
        currentSale.finalize(amountPaid);
        SaleDTO saleDTO = currentSale.createSaleDTO();
        currentSale = null;

        integration.registerSale(saleDTO);
        printer.printReceipt(saleDTO);
    }

    /**
     * Resets the current sale transaction, discarding any scanned items.
     */
    public void resetSale() {
        currentSale = null;
    }
}

package se.kth.salesystem.controller;

import se.kth.salesystem.integration.DBHandler;
import se.kth.salesystem.integration.DatabaseNotFoundException;
import se.kth.salesystem.integration.Printer;
import se.kth.salesystem.integration.SaleDTO;
import se.kth.salesystem.model.ExceptionFileOutput;
import se.kth.salesystem.model.Sale;
import se.kth.salesystem.model.SaleStateException;
import se.kth.salesystem.integration.Discount;
import se.kth.salesystem.integration.ItemNotFoundException;

/**
 * Controller class responsible for managing sale transactions.
 */
public class Controller {
    private DBHandler dbHandler;
    private Printer printer;
    private Sale currentSale;
    private ExceptionFileOutput exceptionLogger;

    /**
     * Constructs a Controller object with the specified Integration and Printer
     * instances.
     *
     * @param dbHandler       the integration instance for communication with
     *                        external systems
     * @param printer         the printer instance for printing receipts
     * @param exceptionLogger the logger for all handled exception
     */
    public Controller(DBHandler dbHandler, Printer printer, ExceptionFileOutput exceptionLogger) {
        this.dbHandler = dbHandler;
        this.printer = printer;
        this.exceptionLogger = exceptionLogger;
    }

    /**
     * Starts a new sale transaction by initializing a new Sale object.
     */
    public void startSale() {
        currentSale = new Sale(dbHandler, exceptionLogger);
    }

    /**
     * Scans an item and adds it to the current sale with the specified quantity.
     * 
     * @param id       the ID of the item to be scanned
     * @param quantity the quantity of the item to be added
     * @return SaleDTO representing the current sale
     * @throws SaleStateException 
     */
    public SaleDTO scanItem(int id, int quantity) throws DatabaseNotFoundException, ItemNotFoundException, SaleStateException {
        if(currentSale != null){
            if(!currentSale.getSaleEnded()){
                if (quantity > 0) {
                    currentSale.addItem(id, quantity);
                }
        
                return fetchCurrentSaleDTO();
            } else {
                throw new SaleStateException("Attempting to add item to finished sale");
            }
        } else {
            throw new SaleStateException("Attempting to add item to non existant sale");
        }
    }

    /**
     * Ends the current sale.
     */
    public void endCurrentSale() {
        currentSale.endSale();
    }

    /**
     * Fetches all discounts that the customer is eligible for and applies them on
     * the current sale.
     * 
     * @param customerId An int representing the customers ID
     */
    public void discountCurrentSale(int customerId) {
        Discount[] discounts = dbHandler.fetchDiscounts(customerId);
        currentSale.discountSale(discounts);
    }

    /**
     * Finalizes the current sale transaction by setting the amount paid, creating a
     * SaleDTO, registering the sale,
     * printing the receipt, and resetting the current sale.
     *
     * @param amountPaid the amount paid by the customer
     * @throws SaleEndedException
     */
    public void finalizeSale(int amountPaid) throws SaleStateException {
        if (currentSale.getSaleEnded()) {
            currentSale.finalize(amountPaid);
            SaleDTO saleDTO = currentSale.createSaleDTO();
            currentSale = null;

            printer.printReceipt(saleDTO);
            dbHandler.registerSale(saleDTO);
        } else {
            throw new SaleStateException("Sale was finalized before it was ended");
        }
    }

    /**
     * Resets the current sale transaction, discarding any scanned items.
     */
    public void resetSale() {
        currentSale = null;
    }

    /**
     * Converts the current sale to a SaleDTO object.
     * 
     * @return a SaleDTO object representng the current state of the sale
     */
    public SaleDTO fetchCurrentSaleDTO() {
        if (currentSale != null) {
            return currentSale.createSaleDTO();
        } else {
            return null;
        }
    }
}

package se.kth.salesystem.controller;

import se.kth.salesystem.integration.DBHandler;
import se.kth.salesystem.integration.Printer;
import se.kth.salesystem.integration.SaleDTO;
import se.kth.salesystem.model.Sale;

/**
 * Controller class responsible for managing sale transactions.
 */
public class Controller {
    private Integration integration;
    private Printer printer;
    private Sale currentSale;
    private ExceptionFileOutput exceptionLogger;

    /**
     * Constructs a Controller object with the specified Integration and Printer
     * instances.
     *
     * @param integration the integration instance for communication with external
     *                    systems
     * @param printer     the printer instance for printing receipts
     */
    public Controller(Integration integration, Printer printer, ExceptionFileOutput exceptionLogger) {
        this.integration = integration;
        this.printer = printer;
        this.exceptionLogger = exceptionLogger;
    }

    /**
     * Starts a new sale transaction by initializing a new Sale object.
     */
    public void startSale() {
        currentSale = new Sale(integration, exceptionLogger);
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
        Discount[] discounts = integration.fetchDiscounts(customerId);
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
            integration.registerSale(saleDTO);
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
     * @return a SAleDTO object representng the current state of the sale
     */
    public SaleDTO fetchCurrentSaleDTO() {
        if (currentSale != null) {
            return currentSale.createSaleDTO();
        } else {
            return null;
        }
    }
}

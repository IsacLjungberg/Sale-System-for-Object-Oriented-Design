package model;

import java.time.LocalDateTime;
import java.util.ArrayList;

import integration.DatabaseNotFoundException;
import integration.Discount;
import integration.Integration;
import integration.SaleDTO;
import integration.ItemDTO;
import integration.ItemNotFoundException;

/**
 * Represents a sale transaction.
 */
public class Sale {
    private String dateAndTime;
    private double totalCost;
    private double totalPaid;
    private double totalVat;
    private double change;
    private ArrayList<Item> items;
    private ItemDTO[] itemsFinal;
    private boolean saleEnded;
    private Integration integration;
    private ExceptionFileOutput exceptionLogger;

    /**
     * Constructs a Sale object with an integration instance.
     *
     * @param integration the integration instance to be used
     */
    public Sale(Integration integration, ExceptionFileOutput exceptionLogger){
        totalCost = 0;
        totalPaid = 0;
        change = 0;
        totalVat = 0;
        dateAndTime = "NULL";
        items = new ArrayList<Item>();
        itemsFinal = null;
        saleEnded = false;
        this.integration = integration;
        this.exceptionLogger = exceptionLogger;
    }

    /**
     * Adds an item to the sale with the given id and quantity.
     * If the item is already present in the sale, increases its quantity.
     * If the item is not present, fetches it from integration and adds it to the sale.
     *
     * @param id the id of the item to be added
     * @param quantity the quantity of the item to be added
     */
    public void addItem(int id, int quantity){
        try{
            Item item = fetchFromItemsInSale(id);
            if(item != null){
                item.addQuantity(quantity);
            } else {
                ItemDTO itemDTO = integration.fetchItem(id);
                item = new Item(itemDTO, quantity);
                items.add(item);
            }
            recalculateCost();
        } catch (ItemNotFoundException exception){
            System.out.println(exception.getMessage());
            exceptionLogger.logMessage(exception.getMessage());
        } catch (DatabaseNotFoundException exception){
            System.out.println(exception.getMessage());
            exceptionLogger.logMessage(exception.getMessage());
        }
    }

    // Recalculates the total cost and total VAT of the sale based on its items.
    private void recalculateCost(){
        totalCost = 0;
        totalVat = 0;

        for(int n = 0; n < items.size(); n++){
            totalCost += (items.get(n).getPrice() + items.get(n).getPrice() * items.get(n).getVatRate()/100) * items.get(n).getQuantity();
            totalVat += items.get(n).getPrice() * items.get(n).getVatRate()/100 * items.get(n).getQuantity();
        }
    }

    //Fetches an item from the sale items list based on its id.
    private Item fetchFromItemsInSale(int id){
        for(int n = 0; n < items.size(); n++){
            if(items.get(n).getId() == id){
                return items.get(n);
            }
        }
        return null;
    }

    /**
     * Creates and returns a SaleDTO object representing the sale.
     *
     * @return the SaleDTO object representing the sale
     */
    public SaleDTO createSaleDTO(){
        return new SaleDTO(dateAndTime, totalCost, totalPaid, totalVat, change, itemsFinal);
    }

    /**
     * Ends the sale, preventing the addition of new item and readying the sale for
     * the application of discounts
     *
     */
    public void endSale(){
        itemsFinal = new ItemDTO[items.size()];
        for(int n = 0; n < items.size(); n++){
            itemsFinal[n] = items.get(n).createItemDTO();
        }
        items = null;
        saleEnded = true;
    }

    /**
     * Adjusts the total cost of sale by calculating the new total with the passed discounts.
     * 
     * @param discounts Array with discounts used for calculating new total
     */
    public void discountSale(Discount[] discounts){
        for(int n = 0; n < discounts.length; n++){
            totalCost = discounts[n].discount(itemsFinal, totalCost);
        }
    }

    /**
     * Finalizes the sale by setting the date and time, total paid, and change.
     *
     * @param amountPaid the amount paid by the customer
     */
    public void finalize(int amountPaid){
        dateAndTime = LocalDateTime.now().toString();
        totalPaid = amountPaid;
        change = amountPaid - totalCost;
    }

    /**
     * @return the date and time of the sale
     */
    public String getDateAndTime() {
        return dateAndTime;
    }

    /**
     * @return the total cost of the sale
     */
    public double getTotalCost() {
        return totalCost;
    }

    /**
     * @return the total amount paid
     */
    public double getTotalPaid() {
        return totalPaid;
    }

    /**
     * @return the total VAT amount
     */
    public double getTotalVat(){
        return totalVat;
    }

    /**
     * @return the change amount
     */
    public double getChange() {
        return change;
    }

    /**
     * @return the list of items in the sale
     */
    public ArrayList<Item> getItems() {
        return items;
    }

    /**
     * @return boolean representing if the sale has been ended
     */
    public boolean getSaleEnded(){
        return saleEnded;
    }
}

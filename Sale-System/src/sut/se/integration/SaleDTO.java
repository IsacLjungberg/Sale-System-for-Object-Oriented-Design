package se.integration;

/**
 * The SaleDTO class represents the data transfer object of the Sale object.
 */
public class SaleDTO {
    private String dateAndTime;
    private double totalCost;
    private double totalPaid;
    private double totalVat;
    private double change;
    private ItemDTO[] itemDTOs;

    /**
     * Constructs a new SaleDTO object.
     * 
     * @param dateAndTime the date and time of the sale
     * @param totalCost the total cost of the sale
     * @param totalPaid the total amount paid
     * @param totalVat the total VAT of the sale
     * @param change the amount of change for transaction
     * @param itemDTOs an array of ItemDTO objects representing the items purchased
     * @see ItemDTO
     */

    public SaleDTO(String dateAndTime, double totalCost, double totalPaid, double totalVat, double change, ItemDTO[] itemDTOs){
        this.dateAndTime = dateAndTime;
        this.totalCost = totalCost;
        this.totalPaid = totalPaid;
        this.totalVat = totalVat;
        this.change = change;
        this.itemDTOs = itemDTOs;
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
     * @return the total paid of the sale
     */
    public double getTotalPaid() {
        return totalPaid;
    }

    /**
     * @return the total VAT of the sale
     */
    public double getTotalVat(){
        return totalVat;
    }

    /**
     * @return the amount of change for the transaction
     */
    public double getChange() {
        return change;
    }

    /**
     * @return an array of ItemDTO objects
     * @see ItemDTO
     */
    public ItemDTO[] getItemDTOs() {
        return itemDTOs;
    }
}

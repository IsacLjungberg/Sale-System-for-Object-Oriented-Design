package se.kth.salesystem.integration;

/**
 * The ItemDTO class represents a data transfer object for item.
 */
public class ItemDTO {
    private String name;
    private String description;
    private int id;
    private double price;
    private double vatRate;
    private int quantity;


    /**
     * constructor for item data transfer object
     * 
     * @param name name of the item
     * @param description description of the item
     * @param id identifier of the item
     * @param price price of the item without vat
     * @param vatRate percentage of item price to be added as vat for the cost customer will pay
     * @param quantity quantity of the item. Means number left in storage when fetched from db, and number sold when saved in a sale
     */
    public ItemDTO(String name, String description, int id, double price, double vatRate, int quantity){
        this.name = name;
        this.description = description;
        this.id = id;
        this.price = price;
        this.vatRate = vatRate;
        this.quantity = quantity;
    }

    /**
     * @return the name of the item
     */
    public String getName(){
        return name;
    }

    /**
     * @return the description of the item
     */
    public String getDescription(){
        return description;
    }

    /**
     * @return the id of the item
     */
    public int getId(){
        return id;
    }

    /**
     * @return the price of the item
     */
    public double getPrice(){
        return price;
    }

    /**
     * @return the vat rate of the item
     */
    public double getVatRate(){
        return vatRate;
    }

    /**
     * @return the quantity of the item
     */
    public int getQuantity(){
        return quantity;
    }
}
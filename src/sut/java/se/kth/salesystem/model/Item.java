package se.kth.salesystem.model;

import se.kth.salesystem.integration.ItemDTO;
/**
 * The Item class represents an item being purchased with its attributes.
 */
public class Item {
    private String name;
    private String description;
    private int id;
    private double price;
    private double vatRate;
    private int quantity;

    /**
     * Constructs an Item object using the provided ItemDTO and quantity.
     *
     * @param itemDTO the ItemDTO object containing item information
     * @param quantity the quantity of the item
     */
    Item(ItemDTO itemDTO, int quantity){
        this.name = itemDTO.getName();
        this.description = itemDTO.getDescription();
        this.id = itemDTO.getId();
        this.price = itemDTO.getPrice();
        this.vatRate = itemDTO.getVatRate();
        this.quantity = quantity;
    }

    /**
     * @return the name of the item
     */
    public String getName() {
        return name;
    }

    /**
     * @return the description of the item
     */
    public String getDescription() {
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
     * @return the VAT rate of the item
     */
    public double getVatRate() {
        return vatRate;
    }

    /**
     * @return the quantity of the item
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Creates and returns an ItemDTO object representing the item.
     *
     * @return the ItemDTO object representing the item
     */
    public ItemDTO createItemDTO(){
        return new ItemDTO(this.name, this.description, this.id, this.price, this.vatRate, this.quantity);
    }

    /**
     * Increases the quantity of the item by the specified amount.
     *
     * @param quantity the quantity to be added
     */
    public void addQuantity(int quantity){
        this.quantity += quantity;
    }
}

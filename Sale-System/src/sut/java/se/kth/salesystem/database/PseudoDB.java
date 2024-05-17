package se.kth.salesystem.database;

import java.util.ArrayList;
import se.kth.salesystem.integration.ItemDTO;
import se.kth.salesystem.integration.SaleDTO;

/**
 * Pseudo database used to simulate real application.
 */
public class PseudoDB {

    private static PseudoDB instance;

    private ArrayList<SaleDTO> sales;
    private ArrayList<ItemDTO> items;
    private ArrayList<Discount> discounts;

    /**
     * Constructs a new instance of PseudoDB, initializing the sales and items
     * lists.
     * Also populates the items list with sample ItemDTO objects.
     */
    private PseudoDB() {
        sales = new ArrayList<SaleDTO>();
        items = new ArrayList<ItemDTO>();
        discounts = new ArrayList<Discount>();

        items.add(new ItemDTO("Test", "Item för tester", 0, 10, 1, 0));
        items.add(new ItemDTO("Apelsiner", "1 kilo riktigt goda apelsiner", 1, 25, 6, 0));
        items.add(new ItemDTO("Vodka", "1 liter redig alkohol", 2, 300, 25, 0));
        items.add(new ItemDTO("Billys", "En pan pizza", 3, 20, 12, 0));
        items.add(new ItemDTO("Franks Blåbär", "Franks med blåbärs smak", 4, 20, 6, 0));

        discounts.add(new AmountDiscount(1, 3));
        int[] customerIds = { 1 };
        discounts.add(new PercentDiscount(customerIds, 50));
    }

    /**
     * Getter for database instance, creates a new one if it has not been created
     * yet.
     * 
     * @return The programs database
     */
    public static PseudoDB getInstance() {
        if (instance == null) {
            instance = new PseudoDB();
        }

        return instance;
    }

    /**
     * Saves a sale representeed by the provided SaleDTO object by adding it to the
     * list of sales database.
     * 
     * @param saleDTO the SaleDTO object representing the sale
     */
    public void saveSale(SaleDTO saleDTO) {
        sales.add(saleDTO);
        for (ItemDTO item : saleDTO.getItemDTOs()) {
            updateItemQuantity(item.getId(), item.getQuantity());
        }
    }

    /**
     * Fetches a previous sale from the sales database using a provided index.
     * 
     * @param index
     */
    public SaleDTO fetchSale(int index) {
        return sales.get(index);
    }

    // Updates the quantity of an item in the database
    private void updateItemQuantity(int id, int amount) {
        ItemDTO item = items.get(id);
        items.remove(id);
        items.add(id, new ItemDTO(item.getName(), item.getDescription(), item.getId(), item.getPrice(),
                item.getVatRate(), (item.getQuantity() + amount)));
    }

    /**
     * Using a provided Id fetches ItemDTO correspondin to Id from items list
     * database.
     * 
     * @param id int representing identification of a item
     * @return the ItemDTO object representing the item with the specified Id,
     *         returns null if no such item is found
     */
    public ItemDTO fetchItem(int id) {
        for (int n = 0; n < items.size(); n++) {
            if (items.get(n).getId() == id) {
                return items.get(n);
            }
        }
        return null;
    }

    /**
     * Saves an ItemDTO to items list database.
     * 
     * @param itemDTO the ItemDTO to be added
     */
    public void saveItem(ItemDTO itemDTO) {
        items.add(itemDTO);
    }

    public Discount[] fetchDiscounts(int id) {
        ArrayList<Discount> currentDiscounts = new ArrayList<Discount>();
        for (int n = 0; n < discounts.size(); n++) {
            if (discounts.get(n).appliesToCustomer(id)) {
                currentDiscounts.add(discounts.get(n));
            }
        }

        Discount[] currentDiscountsArray = new Discount[currentDiscounts.size()];
        currentDiscounts.toArray(currentDiscountsArray);

        return currentDiscountsArray;
    }

    /**
     * @return the ArrayList of sales
     */
    public ArrayList<SaleDTO> getSales() {
        return sales;
    }

    /**
     * @return the ArrayList of items
     */
    public ArrayList<ItemDTO> getItems() {
        return items;
    }
}
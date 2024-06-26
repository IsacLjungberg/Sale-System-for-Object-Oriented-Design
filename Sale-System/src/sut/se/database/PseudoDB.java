package se.database;
import java.util.ArrayList;

import se.integration.ItemDTO;
import se.integration.SaleDTO;

/**
 * Pseudo database used to simulate real application.
 */
public class PseudoDB{
    private ArrayList<SaleDTO> sales;
    private ArrayList<ItemDTO> items;

    /** 
     * Constructs a new instance of PseudoDB, initializing the sales and items lists.
     * Also populates the items list with sample ItemDTO objects.
     */
    public PseudoDB(){
        sales = new ArrayList<SaleDTO>();
        items = new ArrayList<ItemDTO>();

        items.add(new ItemDTO("Mjölk", "1 liter mjölk", 0, 10, 6, 10));
        items.add(new ItemDTO("Apelsiner", "1 kilo riktigt goda apelsiner", 1, 25, 6, 0));
        items.add(new ItemDTO("Vodka", "1 liter sprit", 2, 300, 25, 0));
        items.add(new ItemDTO("Billys", "1 pan pizza", 3, 20, 12, 0));
        items.add(new ItemDTO("Franks Blueberry", "Franks energidryck med blåbärs smak", 4, 20, 6, 0));
    }

    /**
     * Saves a sale representeed by the provided SaleDTO object by adding it to the list of sales database.
     * 
     * @param saleDTO the SaleDTO object representing the sale
     */
    public void saveSale(SaleDTO saleDTO){
        sales.add(saleDTO);
        for (ItemDTO  item : saleDTO.getItemDTOs()) {
            updateItemQuantity(item.getId(), item.getQuantity());
        }
    }

    /**
     * Fetches a sale DTO representing a previous sale
     * 
     * @param index representing the sale
     */
    public SaleDTO fetchSale(int index){
        return sales.get(index);
    }

    private void updateItemQuantity(int id, int amount){
        ItemDTO item = items.get(id);
        items.remove(id);
        items.add(id, new ItemDTO(item.getName(), item.getDescription(), item.getId(), item.getPrice(), item.getVatRate(), (item.getQuantity() + amount)));
    }

    /**
     * Using a provided Id fetches ItemDTO correspondin to Id from items list database.
     * 
     * @param id int representing identification of a item
     * @return the ItemDTO object representing the item with the specified Id, returns null if no such item is found
     */
    public ItemDTO fetchItem(int id){
        for(int n = 0; n < items.size(); n++){
            if(items.get(n).getId() == id){
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
    public void saveItem(ItemDTO itemDTO){
        items.add(itemDTO);
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
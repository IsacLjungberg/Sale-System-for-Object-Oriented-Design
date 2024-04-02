import java.time.LocalDateTime;
import java.util.ArrayList;

public class Sale {
    private String dateAndTime;
    private int totalCost;
    private int totalPaid;
    private int change;
    private ArrayList<Item> items;
    private Integration integration;

    public Sale(Integration integration){
        totalCost = 0;
        totalPaid = 0;
        change = 0;
        ArrayList<Item> items = new ArrayList<Item>(); //great
        dateAndTime = LocalDateTime.now().toString();
        this.integration = integration;
    }

    public void addItem(int id, int quantity){
        Item item = fetchFromSale(id);
        if(item != null){
           item.changeQuantity(quantity); 
        } else {
            ItemDTO itemDTO = integration.fetchItem(id);
            if(itemDTO == null){
                System.out.println("No such item");
            } else {
                items.add(new Item(itemDTO, quantity));
            }
        }
    }

    private Item fetchFromSale(int id){
        for(int n = 0; n < items.size(); n++){
            if(items.get(n).getId() == id){
                return items.get(n);
            }
        }
        return null;
    }

    public SaleDTO createSaleDTO(){
        ArrayList<ItemDTO> temp = new ArrayList<>();

        for (int i = 0; i < items.size(); i++) {
            temp.add(items.get(i).createItemDTO());
        }
        ItemDTO itemDTOs[] = temp.toArray(new ItemDTO[temp.size()]);

        return new SaleDTO(dateAndTime, totalCost, totalPaid, change, itemDTOs);
    }

    public String getDateAndTime() {
        return dateAndTime;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public int getTotalPaid() {
        return totalPaid;
    }

    public int getChange() {
        return change;
    }

    public ArrayList<Item> getItems() {
        return items;
    }
}

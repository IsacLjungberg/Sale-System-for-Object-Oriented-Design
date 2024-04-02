import java.time.LocalDateTime;
import java.util.ArrayList;

public class Sale {
    private String dateAndTime;
    private double totalCost;
    private double totalPaid;
    private double totalVat;
    private double change;
    private ArrayList<Item> items;
    private Integration integration;

    public Sale(Integration integration){
        totalCost = 0;
        totalPaid = 0;
        change = 0;
        totalVat = 0;
        this.items = new ArrayList<Item>(); //great dog
        dateAndTime = LocalDateTime.now().toString();
        this.integration = integration;
    }

    public void addItem(int id, int quantity){
        Item item = fetchFromItemsInSale(id); 
        if(item != null){
           item.addQuantity(quantity); 
        } else {
            ItemDTO itemDTO = integration.fetchItem(id);
            if(itemDTO == null){
                System.out.println("No such item");
            } else {
                item = new Item(itemDTO, quantity);        
                items.add(item);
            }
        }
        recalculateCost();
    }

    private void recalculateCost(){
        totalCost = 0;
        totalVat = 0;

        for(int n = 0; n < items.size(); n++){
            totalCost += (items.get(n).getPrice() + items.get(n).getPrice() * items.get(n).getVatRate()/100) * items.get(n).getQuantity();
            totalVat += items.get(n).getPrice() * items.get(n).getVatRate()/100 * items.get(n).getQuantity();
        }
    }

    private Item fetchFromItemsInSale(int id){ //dogname
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

        return new SaleDTO(dateAndTime, totalCost, totalPaid, totalVat, change, itemDTOs);
    }

    public void finalize(int amountPaid){
        totalPaid = amountPaid;
        change = amountPaid - totalCost;
    }

    public String getDateAndTime() {
        return dateAndTime;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public double getTotalPaid() {
        return totalPaid;
    }

    public double getTotalVat(){
        return totalVat;
    }

    public double getChange() {
        return change;
    }

    public ArrayList<Item> getItems() {
        return items;
    }
}

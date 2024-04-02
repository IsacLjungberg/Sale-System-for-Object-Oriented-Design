public class SaleDTO {
    private String dateAndTime;
    private int totalCost;
    private int totalPaid;
    private int change;
    private ItemDTO[] itemDTOs;

    public SaleDTO(String dateAndTime, int totalCost, int totalPaid, int change, ItemDTO[] itemDTOs){
        this.dateAndTime = dateAndTime;
        this.totalCost = totalCost;
        this.totalPaid = totalPaid;
        this.change = change;
        this.itemDTOs = itemDTOs;
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

    public ItemDTO[] getItemDTOs() {
        return itemDTOs;
    }
}

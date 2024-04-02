public class SaleDTO {
    private String dateAndTime;
    private double totalCost;
    private double totalPaid;
    private double totalVat;
    private double change;
    private ItemDTO[] itemDTOs;

    public SaleDTO(String dateAndTime, double totalCost, double totalPaid, double totalVat, double change, ItemDTO[] itemDTOs){
        this.dateAndTime = dateAndTime;
        this.totalCost = totalCost;
        this.totalPaid = totalPaid;
        this.totalVat = totalVat;
        this.change = change;
        this.itemDTOs = itemDTOs;
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

    public ItemDTO[] getItemDTOs() {
        return itemDTOs;
    }
}

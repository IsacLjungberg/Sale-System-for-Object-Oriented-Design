public class Item {
    private String name;
    private String description;
    private int id;
    private double price;
    private double vatRate;
    private int quantity;

    /* 
    public Item(String name, String description, int id, int price, int vat){
        this.name = name;
        this.description = description;
        this.id = id;
        this.price = price;
        this.vatRate = vat;
        quantity = 1;
    }
    */

    public Item(ItemDTO dto, int quantity){
        this.name = dto.getName();
        this.description = dto.getDescription();
        this.id = dto.getId();
        this.price = dto.getPrice();
        this.vatRate = dto.getVatRate();
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getId(){
        return id;
    }

    public double getPrice(){
        return price;
    }

    public double getVatRate() {
        return vatRate;
    }

    public int getQuantity() {
        return quantity;
    }

    public ItemDTO createItemDTO(){
        return new ItemDTO(this.name, this.description, this.id, this.price, this.vatRate, this.quantity);
    }

    public void addQuantity(int quantity){
        this.quantity += quantity;
    }
}

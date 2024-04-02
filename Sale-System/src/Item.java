public class Item {
    private String name;
    private String description;
    private int id;
    private int price;
    private int vat;
    private int quantity;

    public Item(String name, String description, int id, int price, int vat){
        this.name = name;
        this.description = description;
        this.id = id;
        this.price = price;
        this.vat = vat;
        quantity = 1;
    }

    public Item(ItemDTO dto, int quantity){
        this.name = dto.getName();
        this.description = dto.getDescription();
        this.id = dto.getId();
        this.price = dto.getPrice();
        this.vat = dto.getVat();
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

    public int getPrice(){
        return price;
    }

    public int getVat() {
        return vat;
    }

    public int getQuantity() {
        return quantity;
    }

    public ItemDTO createItemDTO(){
        return new ItemDTO(this.name, this.description, this.id, this.price, this.vat, this.quantity);
    }

    public void changeQuantity(int quantity){
        this.quantity += quantity;
    }
}

public class ItemDTO {
    private String name;
    private String description;
    private int id;
    private int price;
    private int vat;
    private int quantity;

    public ItemDTO(String name, String description, int id, int price, int vat, int quantity){
        this.name = name;
        this.description = description;
        this.id = id;
        this.price = price;
        this.vat = vat;
        this.quantity = quantity;
    }

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    public int getId(){
        return id;
    }

    public int getPrice(){
        return price;
    }

    public int getVat(){
        return vat;
    }

    public int getQuantity(){
        return quantity;
    }
}
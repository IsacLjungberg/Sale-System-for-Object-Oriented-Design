public class ItemDTO {
    private String name;
    private String description;
    private int id;
    private double price;
    private double vatRate;
    private int quantity;

    public ItemDTO(String name, String description, int id, double price, double vat, int quantity){
        this.name = name;
        this.description = description;
        this.id = id;
        this.price = price;
        this.vatRate = vat;
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

    public double getPrice(){
        return price;
    }

    public double getVatRate(){
        return vatRate;
    }

    public int getQuantity(){
        return quantity;
    }
}
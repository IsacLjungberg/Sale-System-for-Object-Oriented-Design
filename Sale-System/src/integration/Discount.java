package integration;

public interface Discount {
    public boolean appliesTo(int id);

    public double discount(ItemDTO[] itemDTOs, double totalCost);
}

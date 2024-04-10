package integration;

/**
 * Interface for discount application.
 */
public interface Discount {

    /**
     * Checks if the discount applies to a customer using a passed ID.
     * 
     * @param id The ID of the customer to check for discount eligibility
     * @return true if customer is eligible otherwise false
     */
    public boolean appliesToCustomer(int id);

    /**
     * Calculates new total cost using provided totalCost and by checking the itemDTOs in purchase
     * 
     * @param itemDTOs A ItemDTO array representing items being purchased
     * @param totalCost The total before the program was signaled for discount
     * @return double representing the new total cost after using applicable discounts
     */
    public double discount(ItemDTO[] itemDTOs, double totalCost);
}

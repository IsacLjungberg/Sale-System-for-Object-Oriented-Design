package se.kth.salesystem.integration;

/**
 * Discount handling for percentage based discounts.
 * Implements the discount interface.
 */
public class PercentDiscount implements Discount{
    private int[] appliesToCustomerIds;
    public int percentage;
    
    /**
     * Constructs the AmountDiscount class which specifies which using a provided ID and amount specifies which item gives a discount when bought in a bunch.
     * 
     * @param appliesToCustomerIds An int array of customer IDs which are eligible for discounts
     * @param percentage The percentage of discount which a customer can be eligible for
     */
    public PercentDiscount(int[] appliesToCustomerIds, int percentage){
        this.appliesToCustomerIds = appliesToCustomerIds;
        this.percentage = percentage;
    }

    /**
     * Customer eligibility for the percentage based discount.
     * Here provided to the customers specified in the appliesToCustomerIds array.
     * 
     * @param id The ID of the customer asking for a discount
     * @return Returns true if the customer in question is eligible, otherwise false
     */
    @Override
    public boolean appliesToCustomer(int id){
        for(int n = 0; n < appliesToCustomerIds.length; n++){
            if(appliesToCustomerIds[n] == id){
                return true;
            }
        }
        return false;
    }

    /**
     * Discount calculation which when passed the items in purchase and total before discount returns the new total cost.
     * 
     * @param itemDTOs A ItemDTO array representing items being purchased
     * @param totalCost The total before the program was signaled for discount
     * @return double representing the new total cost after using applicable discounts
     */
    @Override
    public double discount(ItemDTO[] itemDTOs, double totalCost){
        return totalCost - totalCost * ((double) percentage/100);
    }
}
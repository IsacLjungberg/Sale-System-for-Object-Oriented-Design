package integration;

/**
 * Discount handling for quantity based eligibility.
 * Implements the Discount interface.
 */
public class AmountDiscount implements Discount{
    private int appliesToItemIds;
    private int appliesToAmount;

    /**
     * Constructs the AmountDiscount class which specifies which using a provided ID and amount specifies which item gives a discount when bought in a bunch.
     * 
     * @param appliesToItemIds The ID of the item which has a amount based discount
     * @param appliesToAmount The amount that the specified item has to be purchased in to get one free
     */
    public AmountDiscount(int appliesToItemIds, int appliesToAmount){
        this.appliesToItemIds = appliesToItemIds;
        this.appliesToAmount = appliesToAmount;
        
    }

    /**
     * Customer eligibility for the amount based discount.
     * Here provided to all customers.
     * 
     * @param id The ID of the customer, unused in this implementation
     * @return Always true as the discount is given to all customers
     */
    @Override
    public boolean appliesToCustomer(int id){
        return true;
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
        for(ItemDTO dto : itemDTOs){
            if(dto.getId() == appliesToItemIds){
        
                if(dto.getQuantity() >= appliesToAmount){
                    totalCost -= (dto.getPrice() + (dto.getVatRate()/100) * dto.getPrice()) * Math.floor(dto.getQuantity()/3);
                }
            }
        }
        return totalCost;
    }
}

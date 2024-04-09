package integration;

public class PercentDiscount implements Discount{
    private int[] appliesToCustomerIds;
    public int percentage;
    
    public PercentDiscount(int[] appliesToCustomerIds, int percentage){
        this.appliesToCustomerIds = appliesToCustomerIds;
        this.percentage = percentage;
    }

    @Override
    public boolean appliesTo(int id){
        for(int n = 0; n < appliesToCustomerIds.length; n++){
            if(appliesToCustomerIds[n] == id){
                return true;
            }
        }
        return false;
    }

    @Override
    public double discount(ItemDTO[] itemDTOs, double totalCost){
        return totalCost - totalCost * ((double) percentage/100);
    }
}

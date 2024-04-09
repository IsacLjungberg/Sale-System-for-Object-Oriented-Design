package integration;

public class AmountDiscount implements Discount{
    private int appliesToItemIds;
    private int appliesToAmount;

    public AmountDiscount(int appliesToItemIds, int appliesToAmount){
        this.appliesToItemIds = appliesToItemIds;
        this.appliesToAmount = appliesToAmount;
        
    }

    @Override
    public boolean appliesTo(int id){
        return true;
    }

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

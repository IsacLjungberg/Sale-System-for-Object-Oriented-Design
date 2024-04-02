import java.util.ArrayList;

public class PseudoDB{
    private ArrayList<SaleDTO> sales;
    private ArrayList<ItemDTO> items;
    public PseudoDB(){
        
    }

    public void saveSale(SaleDTO dto){
        sales.add(dto);
    }

    public ItemDTO fetchItem(int id){
        for(int n = 0; n < items.size(); n++){
            if(items.get(n).getId() == id){
                return items.get(n);
            }
        }
        return null;
    }

    public void saveItem(ItemDTO dto){
        items.add(dto);
    }
}
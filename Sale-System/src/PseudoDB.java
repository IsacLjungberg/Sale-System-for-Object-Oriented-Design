import java.util.ArrayList;

public class PseudoDB{
    private ArrayList<SaleDTO> sales;
    private ArrayList<ItemDTO> items;
    public PseudoDB(){
        sales = new ArrayList<SaleDTO>();
        items = new ArrayList<ItemDTO>();

        items.add(new ItemDTO("Apelsiner", "1 kilo riktigt goda apelsiner", 1, 25, 6, 1));
        items.add(new ItemDTO("Vodka", "1 liter redig alkohol", 2, 300, 25, 1));
        items.add(new ItemDTO("Billys", "En pan pizza", 3, 20, 12, 1));
        items.add(new ItemDTO("Franks Blåbär", "En pan pizza", 4, 20, 6, 1));
        
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
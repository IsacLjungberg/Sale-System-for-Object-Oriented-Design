public class Integration {
    private PseudoDB database;
    public Integration(PseudoDB database){
        this.database = database;
    }

    public ItemDTO fetchItem(int id){
        return database.fetchItem(id);
    }

    public void registerSale(SaleDTO saleDTO){
        database.saveSale(saleDTO);
    }
}

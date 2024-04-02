public class Controller {
    Integration integration;
    Printer printer;
    Sale currentSale;
        
    public Controller(Integration integration, Printer printer){
        this.integration = integration;
        this.printer = printer;
    }

    public void startSale(){
        currentSale = new Sale(integration);
    }

    public void scanItem(int id, int quantity){
        if (quantity > 0) {
            currentSale.addItem(id, quantity);
        }
    }
    
    public double finalizeSale(int amountPaid){
        currentSale.finalize(amountPaid);
        double change = currentSale.getChange();
        SaleDTO dto = currentSale.createSaleDTO();
        currentSale = null;

        integration.registerSale(dto);
        printer.printReceipt(dto);
        
        return(change);
    }

    public Sale getSaleStatus(){
        return currentSale; //maybe send back saleDTO?
    }
}

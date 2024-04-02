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
        currentSale.addItem(id, quantity);
    }
    
    public int finalizeSale(int amountPaid){
        return(currentSale.getTotalCost() - amountPaid);
    }

    public Sale getSaleStatus(){
        return currentSale; //maybe send back saleDTO?
    }
}

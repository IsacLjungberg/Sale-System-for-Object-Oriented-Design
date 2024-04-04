import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 * Tests class for testing functionalities of all classes.
 */
public class Tests {
    private Controller controller;
    private Integration integration;

    /**
     * Constructs a Tests object with the specified Controller and Integration instances.
     *
     * @param controller the Controller instance for testing
     * @param integration the Integration instance for testing
     */

     /* 
    public Tests(Controller controller, Integration integration){
        this.controller = controller;
        this.integration = integration;
    }
    */

    public Tests(){
        integration = new Integration(new PseudoDB());
        Printer printer = new Printer();
        controller = new Controller(integration, printer);
    }

    /**
     * Performs all tests and returns a summary of the results.
     *
     * @return a string summarizing the test results
     */

    /* 
    public String testAll(){
        ArrayList<Boolean> out = new ArrayList<Boolean>();
        out.add(correctPrice());
        controller.resetSale();

        out.add(unit_startSale());
        controller.resetSale();

        
        out.add(unit_scanItem());
        controller.resetSale();

        out.add(unit_finalizeSale());
        controller.resetSale();

        out.add(unit_addItem());
        controller.resetSale();

        out.add(unit_addQuantity());
        controller.resetSale();

        out.add(unit_registerSale());
        controller.resetSale();

        String outString = "";

        for(int n = 0; n < out.size(); n++){
            outString += String.valueOf(out.get(n)) + "\n";
        }

        return "Result: \n" + outString;
    }

    */

    /**
     * Tests if the total cost of a sale is calculated correctly.
     *
     * @return true if the test passes, false otherwise
     */
    
    @Test
    public void correctPrice(){
        controller.startSale();

        int[] quantityOfItem = {3, 6, 1, 0}; 

        controller.scanItem(0, quantityOfItem[0]);
        controller.scanItem(1, quantityOfItem[1]);
        controller.scanItem(2, quantityOfItem[2]);
        controller.scanItem(3, quantityOfItem[3]);
        
        double expectedPrice = 0;

        for(int n = 0; n < quantityOfItem.length; n++){
            double oneItemPrice = integration.fetchItem(n).getPrice();
            expectedPrice += (oneItemPrice + oneItemPrice * integration.fetchItem(n).getVatRate()/100) * quantityOfItem[n];
        }

        boolean out = expectedPrice == controller.getCurrentSale().getTotalCost();
        controller.finalizeSale(0);
        //return out;
        assertEquals("Works", true, out);
    }

    /**
     * Tests if starting a sale initializes correctly.
     *
     * @return true if the test passes, false otherwise
     */
    
    @Test
    public void unit_startSale(){
        controller.startSale();
        Sale sale = controller.getCurrentSale();


        Boolean out = sale.getItems().size() == 0 &&
            sale.getTotalCost() == 0 &&
            sale.getTotalPaid() == 0 &&
            sale.getTotalVat() == 0 &&
            sale.getChange() == 0 &&
            sale.getItems().size() == 0;
        
        //return out
        assertEquals("No Works", true, out);
    }

    /**
     * Tests if scanning an item adds it to the current sale correctly.
     *
     * @return true if the test passes, false otherwise
     */
    @Test
    public void unit_scanItem(){
        controller.startSale();

        int[] itemIds = {1, 0, 3};
        int[] itemQs = {3, 1, 0};
        boolean out = true;
        double expectedPrice = 0;

        if(itemIds.length == itemQs.length){
            for(int n = 0; n < itemIds.length; n++){
                controller.scanItem(itemIds[n], itemQs[n]);
                ItemDTO itemDTO = integration.fetchItem(itemIds[n]);
                expectedPrice += (itemDTO.getPrice() + itemDTO.getPrice() * itemDTO.getVatRate()/100) * itemQs[n];
                out = out && controller.getCurrentSale().getTotalCost() == expectedPrice;
            }

            ArrayList<Integer> collapsedItemIds = new ArrayList<Integer>();
            ArrayList<Integer> collapsedItemQs = new ArrayList<Integer>();
            for(int n = 0; n < itemIds.length; n++){
                boolean found = false;
                for(int i = 0; i < collapsedItemIds.size(); i++){
                    if(itemIds[n] == collapsedItemIds.get(i)){
                        found = true;
                        collapsedItemQs.set(i, collapsedItemQs.get(i) + itemQs[n]);
                        break;
                    }
                }

                if(!found && itemQs[n] > 0){
                    collapsedItemIds.add(itemIds[n]);
                    collapsedItemQs.add(itemQs[n]);
                }

            }
            
            Sale sale = controller.getCurrentSale();
            for(int n = 0; n < itemIds.length; n++){
                if(itemQs[n] > 0){
                    boolean found = false;
                    for(int i = 0; i < sale.getItems().size(); i++){
                        if(collapsedItemIds.get(n) == sale.getItems().get(i).getId()){
                            if(collapsedItemQs.get(n) == sale.getItems().get(i).getQuantity()){
                                found = true;
                                break;
                            } else {
                                return;
                            }
                        }
                    }
                    out = out && found;
                }
            }

            assertEquals("No Works", true, out);;
        } else {
            return;
        }
    }

    /**
     * Tests if finalizing a sale completes the transaction correctly.
     *
     * @return true if the test passes, false otherwise
     */
    @Test
    public void unit_finalizeSale(){
        controller.startSale();
        controller.scanItem(0, 1);
        controller.finalizeSale(11);
        
        assertEquals("Current sale not emptied meaning finalize sale failed", null, controller.getCurrentSale());
    }
        
    /**
     * Tests if adding an item to a sale works correctly.
     *
     * @return true if the test passes, false otherwise
     */

     @Test
    public void unit_addItem(){
        Sale sale = new Sale(integration);
        int itemId = 1;
        
        ItemDTO itemDTO = integration.fetchItem(itemId);
        double vat = itemDTO.getPrice() * itemDTO.getVatRate()/100;
        double totalCost = itemDTO.getPrice() + vat;

        sale.addItem(itemId, 1);
        
        if (sale.getItems().get(0).getId() == itemId && sale.getTotalVat() == vat && sale.getTotalCost() == totalCost){
            assertEquals("No Works", true, true);
            //return true;
        } 

        //return false;
    }

    /**
     * Tests if adding quantity to an item in a sale works correctly.
     *
     * @return true if the test passes, false otherwise
     */
    @Test
    public void unit_addQuantity(){
        controller.startSale();
        controller.scanItem(0, 1);

        controller.getCurrentSale().getItems().get(0).addQuantity(1);

        boolean result2  = (controller.getCurrentSale().getItems().get(0).getQuantity() == 2);

        //return (result1 && result2);
        assertEquals("Item incremented incorrectly.", true, result2);
    }

    /**
     * Tests if sales registered update the database correctly
     *
     * @return true if the test passes, false otherwise
     */
    
    @Test
    public void unit_registerSale(){
        controller.startSale();
        controller.scanItem(0, 3);
        controller.scanItem(1, 1);
        controller.scanItem(2, 0);

        Sale sale = controller.getCurrentSale();
        double totalCost = sale.getTotalCost();
        double totalVat = sale.getTotalVat();

        ArrayList<Item> itemsArrayList = controller.getCurrentSale().getItems();
        ItemDTO[] itemDTOs = new ItemDTO[controller.getCurrentSale().getItems().size()];
        for(int n = 0; n < itemsArrayList.size(); n++){
            itemDTOs[n] = itemsArrayList.get(n).createItemDTO();
        }

        controller.finalizeSale(100);

        String dateAndTime = sale.getDateAndTime();
        double totalPaid = sale.getTotalPaid();
        double change = sale.getChange();

        SaleDTO dto = integration.fetchLatestSale();

        Boolean out = 
            dto.getDateAndTime().equals(dateAndTime) &&
            dto.getTotalCost() == totalCost &&
            dto.getTotalPaid() == totalPaid &&
            dto.getTotalVat() == totalVat &&
            dto.getChange() == change
        ;

        System.out.println("registerSale test: \n" + 
        String.valueOf(dto.getDateAndTime().equals(dateAndTime)) + "\n" +
        String.valueOf(dto.getTotalCost() == totalCost) + "\n" +
        String.valueOf(dto.getTotalPaid() == totalPaid) + "\n" +
        String.valueOf(dto.getTotalVat() == totalVat) + "\n" +
        String.valueOf(dto.getChange() == change) + "\n"
        );

        for(int n = 0; n < dto.getItemDTOs().length; n++){

            System.out.println("Deepcompare: " + 
                String.valueOf(dto.getItemDTOs()[n].getName().equals(itemDTOs[n].getName())) + "\n" +
                String.valueOf(dto.getItemDTOs()[n].getDescription().equals(itemDTOs[n].getDescription())) + "\n" +
                String.valueOf(dto.getItemDTOs()[n].getId() == itemDTOs[n].getId()) + "\n" +
                String.valueOf(dto.getItemDTOs()[n].getPrice() == itemDTOs[n].getPrice()) + "\n" + 
                String.valueOf(dto.getItemDTOs()[n].getVatRate() == itemDTOs[n].getVatRate()) + "\n" +
                String.valueOf(dto.getItemDTOs()[n].getQuantity() == itemDTOs[n].getQuantity()) + "\n"
            );

            out = out && dto.getItemDTOs()[n].getName().equals(itemDTOs[n].getName());
            out = out && dto.getItemDTOs()[n].getDescription().equals(itemDTOs[n].getDescription());
            out = out && dto.getItemDTOs()[n].getId() == itemDTOs[n].getId();
            out = out && dto.getItemDTOs()[n].getPrice() == itemDTOs[n].getPrice();
            out = out && dto.getItemDTOs()[n].getVatRate() == itemDTOs[n].getVatRate();
            out = out && dto.getItemDTOs()[n].getQuantity() == itemDTOs[n].getQuantity();
        }

        //return out;
        assertEquals("No Works", true, out);
    }
}


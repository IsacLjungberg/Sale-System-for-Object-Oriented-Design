package se.kth.salesystem.integration;

/**
 * The Printer class provides functionality to print receipts for sales.
 */
public class Printer {
    
    /**
     * Constructs a new Printer instance.
     */
    public Printer(){

    }


    /**
     * Prints a receipt of given sale
     * 
     * @param saleDTO the SaleDTO object containing information about the sale
     * @see SaleDTO
     */
    public void printReceipt(SaleDTO saleDTO){
        System.out.println("---------------------------- Begin Receipt ----------------------------");
        System.out.println("Time of sale: " + saleDTO.getDateAndTime());
        System.out.println();

        for (ItemDTO itemDTO : saleDTO.getItemDTOs()) {
            System.out.printf("%s\t", itemDTO.getName());
            System.out.printf("%d x ", itemDTO.getQuantity());
            System.out.printf("%.2f SEK\t", itemDTO.getPrice());
            System.out.printf("%.2f SEK\t", itemDTO.getQuantity() * itemDTO.getPrice());
            System.out.printf("%.2f %%VAT\n", itemDTO.getVatRate());
        }
        
        System.out.println();
        System.out.printf("Total: \t\t%,.2f SEK\n", saleDTO.getTotalCost());
        System.out.printf("VAT: \t\t%,.2f SEK\n",saleDTO.getTotalVat());
        System.out.printf("Cash: \t\t%,.2f SEK\n",saleDTO.getTotalPaid());
        System.out.printf("Change: \t%,.2f SEK\n",saleDTO.getChange());
        System.out.println("----------------------------- End Receipt -----------------------------");
    }
}

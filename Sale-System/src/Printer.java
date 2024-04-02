public class Printer {
    public Printer(){

    }

    public void printReceipt(SaleDTO saleDTO){
        System.out.println("---------------------------- Begin Receipt ----------------------------");
        System.out.println("Time of sale: " + saleDTO.getDateAndTime());
        System.out.println();
        for (ItemDTO itemDTO : saleDTO.getItemDTOs()) {
            System.out.print(itemDTO.getName() + "\t");
            System.out.print(itemDTO.getQuantity() + " x ");
            System.out.print(itemDTO.getPrice() + " SEK\t");
            System.out.print(itemDTO.getQuantity() * itemDTO.getPrice() + " SEK\t");
            System.out.println(itemDTO.getVatRate() + " %VAT");
        }
        System.out.println();
        System.out.println("Total: \t\t" + saleDTO.getTotalCost() + " SEK");
        System.out.println("VAT: \t\t" + saleDTO.getTotalVat());
        System.out.println("Cash: \t\t" + saleDTO.getTotalPaid() + " SEK");
        System.out.println("Change: \t\t" + saleDTO.getChange() + " SEK");
        System.out.println("----------------------------- End Receipt -----------------------------");
    }
}

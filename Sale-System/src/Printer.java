public class Printer {
    public Printer(){

    }

    public void printReceipt(SaleDTO saleDTO){
        System.out.println(saleDTO.getDateAndTime());
        for (ItemDTO itemDTO : saleDTO.getItemDTOs()) {
            System.out.println(itemDTO.getName() + " ");
            System.out.print(itemDTO.getPrice() + " ");
            System.out.print(itemDTO.getVat()+ " ") ;
            System.out.print(itemDTO.getQuantity() + " ");
        }
        System.out.println(saleDTO.getTotalCost());
        System.out.println(saleDTO.getTotalPaid());
        System.out.println(saleDTO.getChange());
    }
}

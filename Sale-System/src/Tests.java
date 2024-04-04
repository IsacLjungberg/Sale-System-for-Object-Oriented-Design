public class Tests {
    private Controller controller;
    private Integration integration;
    public Tests(Controller controller, Integration integration){
        this.controller = controller;
        this.integration = integration;
    }

    public String testAll(){
        firstExampleFlow();
        secondExampleFlow();

        boolean[] out = new boolean[1];
        out[0] = correctPrice();

        String outString = "";

        for(int n = 0; n < out.length; n++){
            outString += String.valueOf(out[n]) + "\n";
        }

        return "Result: \n" + outString;
    }

    public void firstExampleFlow(){
        controller.startSale();
        controller.scanItem(0, 1);
        controller.scanItem(1, 1);
        controller.scanItem(1, 1);
        controller.scanItem(2, 1);
        controller.scanItem(2, 1);
        controller.scanItem(2, 1);
        controller.scanItem(2, 1);
        controller.scanItem(2, 1);
        controller.scanItem(3, 20);
        controller.finalizeSale(2000);
    }

    public void secondExampleFlow(){
        controller.startSale();
        controller.scanItem(4, 100);
        controller.finalizeSale(2000);
    }

    public boolean correctPrice(){
        controller.startSale();

        int[] quantityOfItem = {3, 6, 1, 0}; 

        controller.scanItem(0, quantityOfItem[0]);
        controller.scanItem(1, quantityOfItem[1]);
        controller.scanItem(2, quantityOfItem[2]);
        controller.scanItem(3, quantityOfItem[3]);
        
        double expectedPrice = 0;

        for(int n = 0; n < 3; n++){
            System.out.println("n: " + n);
            double oneItemPrice = integration.fetchItem(n).getPrice();
            expectedPrice += (oneItemPrice + oneItemPrice * integration.fetchItem(n).getVatRate()/100) * quantityOfItem[n];
        }

        System.out.println("Price Expected: " + expectedPrice + "\nSale Price: " + controller.getSaleStatus().getTotalCost());
        boolean out = expectedPrice == controller.getSaleStatus().getTotalCost();
        controller.finalizeSale(0);
        return out;
    }
}

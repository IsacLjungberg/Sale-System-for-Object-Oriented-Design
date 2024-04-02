public class Tests {
    private Controller controller;
    public Tests(Controller controller){
        this.controller = controller;
    }

    public String testAll(){
        return firstExampleFlow() + secondExampleFlow();
    }

    public String firstExampleFlow(){
        controller.startSale();
        controller.scanItem(1, 1);
        controller.scanItem(2, 1);
        controller.scanItem(2, 1);
        controller.scanItem(1, 1);
        controller.scanItem(3, 1);
        controller.scanItem(3, 1);
        controller.scanItem(3, 1);
        controller.scanItem(3, 1);
        controller.scanItem(4, 20);
        return "Change: " + Double.toString(controller.finalizeSale(2000));
    }

    public String secondExampleFlow(){
        controller.startSale();
        controller.scanItem(4, 100);
        return "Change: " + Double.toString(controller.finalizeSale(2000));
    }
}

package se.kth.salesystem.view;

import se.kth.salesystem.controller.Controller;
import se.kth.salesystem.model.SaleStateException;

/**
 * Hard-coded functions to run program and testing
 */

public class View{
    Controller controller;

    /**
     * Constructs a view with a controller to make calls to
     * 
     * @param controller to make calls to
     */
    public View(Controller controller){
        this.controller = controller;
    }

    /**
     * Runs a predefined set of example flows
     */

    public void runExampleFlows() {
        firstExampleFlow();
        secondExampleFlow();
    }


    /**
     * Example flow where a sale is started, several items are scanned, and the sale is finalized
     */
    public void firstExampleFlow() {
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
        controller.endCurrentSale();
        try {
            controller.finalizeSale(2000);
        } catch (SaleStateException e) {
            System.out.println(e.getMessage());
        }
        
    }

    /**
     * Example flow where a sale is started, several items are scanned, and the sale is finalized
     */
    public void secondExampleFlow() {
        controller.startSale();
        controller.scanItem(1, 3);
        controller.scanItem(0, 1);
        controller.scanItem(3, 0);
        controller.scanItem(404, 1);
        controller.scanItem(100, 1);
        controller.endCurrentSale();
        try {
            controller.finalizeSale(2000);
        } catch (SaleStateException e) {
            System.out.println(e.getMessage());
        }
    }
}
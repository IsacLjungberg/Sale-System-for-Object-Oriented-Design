package se.kth.salesystem.view;

import java.io.PrintWriter;
import java.io.StringWriter;

import se.kth.salesystem.controller.Controller;
import se.kth.salesystem.integration.DatabaseNotFoundException;
import se.kth.salesystem.integration.ItemNotFoundException;
import se.kth.salesystem.integration.SaleDTO;
import se.kth.salesystem.model.ExceptionFileOutput;
import se.kth.salesystem.model.SaleStateException;

/**
 * Hard-coded functions to run program and testing
 */

public class View{
    Controller controller;
    SaleDTO currentSaleDTO;

    /**
     * Constructs a view with a controller to make calls to
     * 
     * @param controller to make calls to
     */
    public View(Controller controller){
        this.controller = controller;
        currentSaleDTO = null;
    }

    /**
     * Runs a predefined set of example flows
     */

    public void runExampleFlows() {
        try {
            firstExampleFlow();
        } catch (Exception e) {
            printStackTraceOfException(e);
        }

        try {
            secondExampleFlow();
        } catch (Exception e) {
            printStackTraceOfException(e);
        }

        try {
            thirdExampleFlow();
        } catch (Exception e) {
            printStackTraceOfException(e);
        }
    }

    private void printStackTraceOfException(Exception e){
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        ExceptionFileOutput.getInstance().logMessage(sw.toString());
    }


    /**
     * Example flow where a sale is started, several items are scanned, and the sale is finalized
     */
    public void firstExampleFlow() {
        try {
            startSale();
            currentSaleDTO = scanItem(0, 1);
            currentSaleDTO = scanItem(1, 1);
            currentSaleDTO = scanItem(1, 1);
            currentSaleDTO = scanItem(2, 1);
            currentSaleDTO = scanItem(2, 1);
            currentSaleDTO = scanItem(2, 1);
            currentSaleDTO = scanItem(2, 1);
            currentSaleDTO = scanItem(2, 1);
            currentSaleDTO = scanItem(3, 20);
            controller.endCurrentSale();
            controller.finalizeSale(2000);
        } catch (SaleStateException e) {
            System.out.println("Trying to finalize a non ended sale");
        }
        
    }

    /**
     * Example flow where a sale is started, several items are scanned, and the sale is finalized
     */
    public void secondExampleFlow() {
        try {
            startSale();
            currentSaleDTO = scanItem(1, 3);
            currentSaleDTO = scanItem(0, 1);
            currentSaleDTO = scanItem(3, 0);
            currentSaleDTO = scanItem(404, 1);
            currentSaleDTO = scanItem(100, 1);
            controller.endCurrentSale();
            controller.finalizeSale(2000);
        } catch (SaleStateException e) {
            System.out.println("Trying to finalize a non ended sale");
        }
    }

    /**
     * Example flow for a forced RuntimeException
     */
    public void thirdExampleFlow(){
        Integer noInteger = null;
        noInteger += 1;
    }

    private void startSale(){
        currentSaleDTO = null;
        controller.startSale();
    }

    public SaleDTO scanItem(int id, int quantity){
        try {
            return controller.scanItem(id, quantity);
        } catch (ItemNotFoundException e) {
            System.out.println("Scanned item was not found, sale was not updated");
            return currentSaleDTO;
        } catch (DatabaseNotFoundException e) {
            System.out.println("Database was not found, sale was not updated");
            return currentSaleDTO;
        } catch (SaleStateException e) {
            System.out.println("Trying to add item to ended sale, sale was not updated");
            return currentSaleDTO;
        }
    }
}
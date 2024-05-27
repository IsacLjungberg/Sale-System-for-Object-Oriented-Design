package se.kth.salesystem.controller;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import se.kth.salesystem.database.PseudoDB;
import se.kth.salesystem.integration.DBHandler;
import se.kth.salesystem.integration.DatabaseNotFoundException;
import se.kth.salesystem.integration.ItemNotFoundException;
import se.kth.salesystem.integration.Printer;
import se.kth.salesystem.integration.SaleDTO;
import se.kth.salesystem.model.ExceptionFileOutput;
import se.kth.salesystem.model.SaleStateException;
import se.kth.salesystem.view.View;

import static org.junit.Assert.*;

import java.util.ArrayList;

public class ControllerTest {

    PseudoDB db;
    DBHandler dbHandler;
    Printer printer;
    Controller controller;
    ExceptionFileOutput exceptionLogger;
    View view;

    @Before
    public void setUp() {
        db = PseudoDB.getInstance();
        db.resetDatabase();
        dbHandler = new DBHandler(db);
        exceptionLogger = ExceptionFileOutput.getInstance();
        printer = new Printer();
        controller = new Controller(dbHandler, printer, exceptionLogger);
        view = new View(controller);
    }

    @After
    public void tearDown() {
        dbHandler = null;
        printer = null;
        controller = null;
        view = null;
    }

    @Test
    public void startSaleTest() {
        controller.startSale();
        boolean testBool = controller.fetchCurrentSaleDTO() != null;
        assertEquals("Start sale failed to initialise a sale", true, testBool);
    }

    @Test
    public void scanItemTest() throws SaleStateException, DatabaseNotFoundException, ItemNotFoundException{
        controller.startSale();
        controller.scanItem(2, 1);
        controller.scanItem(0, 2);
        controller.scanItem(3, 0);

        boolean testBool = controller.fetchCurrentSaleDTO().getTotalCost() == 396.2;

        assertEquals("Price of sale was not updated in accordance with added items"
                + controller.fetchCurrentSaleDTO().getTotalCost(), true, testBool);
    }

    @Test
    public void finalizeSaleTest() throws SaleStateException, DatabaseNotFoundException, ItemNotFoundException {
        controller.startSale();
        controller.scanItem(2, 1);
        controller.scanItem(0, 2);
        controller.scanItem(3, 0);
        controller.endCurrentSale();
        controller.finalizeSale(400);

        ArrayList<SaleDTO> oldSales = db.getSales();

        boolean testBool = oldSales.get(0).getTotalPaid() == 400 && oldSales.get(0).getTotalCost() == 396.2;

        assertEquals("Incorrect sale saved to the database", true, testBool);
    }

    @Test
    public void resetSaleTest() throws SaleStateException, DatabaseNotFoundException, ItemNotFoundException{
        controller.startSale();
        controller.scanItem(2, 1);
        controller.scanItem(0, 2);

        controller.resetSale();

        boolean testBool = controller.fetchCurrentSaleDTO() == null;

        assertEquals("Current sale not nullified after reset", true, testBool);
    }

    @Test
    public void finalizeUnendedSaleTest() throws DatabaseNotFoundException, ItemNotFoundException{
        try {
            controller.startSale();
            controller.scanItem(0, 1);
            controller.finalizeSale(100);
            assertFalse("Did not throw SaleStateException", true);
        } catch (SaleStateException e) {
            assertTrue(true);
        }
    }

    @Test
    public void addingItemToEndedSale() throws DatabaseNotFoundException, ItemNotFoundException{
        try {
            controller.startSale();
            controller.endCurrentSale();
            controller.scanItem(0, 1);
            assertFalse("Did not throw SaleStateException", true);
        } catch (SaleStateException e) {
            assertTrue(true);
        }
    }

    @Test
    public void addingItemToNonStartedSale() throws DatabaseNotFoundException, ItemNotFoundException{
        try {
            controller.scanItem(0, 1);
            assertFalse("Did not throw SaleStateException", true);
        } catch (SaleStateException e) {
            assertTrue(true);
        }
    }

    @Test
    public void exceptionLoggerSingeltonTest(){
        ExceptionFileOutput temp = ExceptionFileOutput.getInstance();
        assertEquals(("The exception logger has two different instances"), temp, exceptionLogger);
    }

    @Test
    public void discountTest() throws SaleStateException, DatabaseNotFoundException, ItemNotFoundException{
        controller.startSale();
        controller.scanItem(0, 1);
        controller.scanItem(1, 3);
        controller.scanItem(2, 1);

        controller.endCurrentSale();
        controller.discountCurrentSale(1);
        controller.finalizeSale(1000);
        
        double totalCost = dbHandler.fetchLatestSale().getTotalCost();
        boolean testBool = totalCost == (465.1 - 25 * 1.06) * 0.5;
        assertTrue("Price is not right after discount, price after is " + totalCost, testBool);
    }
    
}

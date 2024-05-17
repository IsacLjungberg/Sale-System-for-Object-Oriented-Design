package se.kth.salesystem.controller;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import se.kth.salesystem.database.PseudoDB;
import se.kth.salesystem.integration.DBHandler;
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
    public void scanItemTest() {
        controller.startSale();
        controller.scanItem(2, 1);
        controller.scanItem(0, 2);
        controller.scanItem(3, 0);

        boolean testBool = controller.fetchCurrentSaleDTO().getTotalCost() == 396.2;

        assertEquals("Price of sale was not updated in accordance with added items"
                + controller.fetchCurrentSaleDTO().getTotalCost(), true, testBool);
    }

    @Test
    public void finalizeSaleTest() throws SaleStateException {
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
    public void resetSaleTest() {
        controller.startSale();
        controller.scanItem(2, 1);
        controller.scanItem(0, 2);

        controller.resetSale();

        boolean testBool = controller.fetchCurrentSaleDTO() == null;

        assertEquals("Current sale not nullified after reset", true, testBool);
    }
}
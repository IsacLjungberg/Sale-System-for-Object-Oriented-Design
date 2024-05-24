package se.kth.salesystem.view;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import se.kth.salesystem.integration.DBHandler;
import se.kth.salesystem.integration.DatabaseNotFoundException;
import se.kth.salesystem.integration.ItemNotFoundException;
import se.kth.salesystem.integration.Printer;
import se.kth.salesystem.model.ExceptionFileOutput;
import se.kth.salesystem.model.SaleStateException;
import se.kth.salesystem.model.TotalRevenueFileOutput;
import se.kth.salesystem.controller.Controller;
import se.kth.salesystem.database.PseudoDB;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;


public class ViewTest {

    View view;
    PrintStream origOut = System.out;
    ByteArrayOutputStream outputToStream;
    Controller controller;

    @Before
    public void setUp() throws FileNotFoundException{
        DBHandler dbHandler = new DBHandler(PseudoDB.getInstance());
        Printer printer = new Printer();
        ExceptionFileOutput exceptionLogger = ExceptionFileOutput.getInstance();
        TotalRevenueFileOutput revenueLogger = TotalRevenueFileOutput.getInstance();
        controller = new Controller(dbHandler, printer, exceptionLogger);
        view = new View(controller);
        TotalRevenueView totalRevView = new TotalRevenueView();
        outputToStream = new ByteArrayOutputStream();

        dbHandler.addObserver(totalRevView);
        dbHandler.addObserver(revenueLogger);

        System.setOut(new PrintStream(outputToStream));
    }

    @After
    public void tearDown(){
        System.setOut(origOut);

        outputToStream = null;
    }

    @Test
    public void DatabaseNotFoundExceptionToViewTest() throws SaleStateException, ItemNotFoundException, DatabaseNotFoundException {
        controller.startSale();
        view.scanItem(404, 1);
    
        String testException = outputToStream.toString();
        assertTrue("Incorrect output to view or output contains invalid information, output is: " + testException, testException.contains("Database was not found, sale was not updated"));
    }

    @Test
    public void ItemNotFoundExceptionToViewTest() throws SaleStateException, DatabaseNotFoundException {
        controller.startSale();
        view.scanItem(100, 1);

        String testException = outputToStream.toString();
        assertTrue("Incorrect output to view or output contains invalid information, output is: " + testException, testException.contains("Scanned item was not found, sale was not updated"));
    }

    @Test
    public void SaleStateExceptionToViewTest() throws DatabaseNotFoundException, ItemNotFoundException {
        controller.startSale();
        view.scanItem(0, 1);
        controller.endCurrentSale();
        view.scanItem(0, 1);
    
        String testException = outputToStream.toString();
        assertTrue("Incorrect output to view or output contains invalid information, output is: " + testException, testException.contains("Trying to add item to ended sale, sale was not updated"));
    }
}

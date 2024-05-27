package se.kth.salesystem.integration;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import se.kth.salesystem.database.PseudoDB;
import se.kth.salesystem.model.SaleStateException;
import se.kth.salesystem.model.TotalRevenueFileOutput;
import se.kth.salesystem.view.TotalRevenueView;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class PrinterTest {
    ByteArrayOutputStream outputToStream;
    PrintStream origOut = System.out;
    Printer printer;

    @Before
    public void setUp(){
        DBHandler dbHandler = new DBHandler(PseudoDB.getInstance());
        printer = new Printer();
        TotalRevenueFileOutput revenueLogger = TotalRevenueFileOutput.getInstance();
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
    public void printerTest() throws DatabaseNotFoundException, ItemNotFoundException, SaleStateException{
        ItemDTO[] itemDTOs = {new ItemDTO("Mjölk", "Mjölk", 0, 100, 6, 10)};
        SaleDTO saleDTO = new SaleDTO("0", 100, 100, 100, 100, itemDTOs);

        printer.printReceipt(saleDTO);

        String testOutput = outputToStream.toString();

        String[] parsed = testOutput.split("\n");
        Boolean testBool = 
        parsed[3].contains("Mjölk") &&
        parsed[3].split("Mjölk")[1].contains("10 x 100,00 SEK") &&
        parsed[3].split("10 x 100,00 SEK")[1].contains("1000,00 SEK") &&
        parsed[3].split("1000,00 SEK")[1].contains("6,00 %VAT") &&
        parsed[6].contains("100,00 SEK") &&
        parsed[7].contains("100,00 SEK") &&
        parsed[8].contains("100,00 SEK");

        assertTrue("Printer output is incorrect ", testBool);
    }
}

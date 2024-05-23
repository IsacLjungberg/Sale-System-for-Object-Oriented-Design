package se.kth.salesystem.model;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import se.kth.salesystem.database.PseudoDB;
import se.kth.salesystem.integration.DBHandler;
import se.kth.salesystem.integration.DatabaseNotFoundException;
import se.kth.salesystem.integration.ItemNotFoundException;
import se.kth.salesystem.integration.SaleDTO;

import static org.junit.Assert.*;

public class SaleTest {
    PseudoDB db;
    DBHandler dbHandler;
    ExceptionFileOutput exceptionLogger;
    Sale sale;

    @Before
    public void setUp() {
        db = PseudoDB.getInstance();
        dbHandler = new DBHandler(db);
        exceptionLogger = ExceptionFileOutput.getInstance();
        sale = new Sale(dbHandler, exceptionLogger);
    }

    @After
    public void tearDown() {
        sale = null;
    }

    @Test
    public void addItemTest() throws ItemNotFoundException, DatabaseNotFoundException {
        sale.addItem(1, 1);
        sale.addItem(1, 1);
        sale.addItem(3, 2);

        assertEquals("Add item does not correctly increase cost", true, sale.getTotalCost() == 97.8);
    }

    @Test
    public void createSaleDTOTest() throws ItemNotFoundException, DatabaseNotFoundException {
        sale.addItem(1, 1);
        sale.addItem(1, 1);
        sale.addItem(3, 2);

        SaleDTO dto = sale.createSaleDTO();

        assertEquals("The saleDTO is created incorrectly", true, dto.getTotalCost() == 97.8);
    }

    @Test
    public void finalizeTest() throws ItemNotFoundException, DatabaseNotFoundException {
        sale.addItem(1, 1);
        sale.addItem(1, 1);
        sale.addItem(3, 2);

        sale.finalize(100);

        boolean testBool = (sale.getTotalPaid() == 100 && sale.getChange() == (100 - 97.8));

        assertEquals(
                "Finalize records incorrect amount paid or calculates change incorrectly",
                true, testBool);
    }

    
}

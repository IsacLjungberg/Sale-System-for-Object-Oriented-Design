package se.kth.salesystem.model;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import se.kth.salesystem.database.PseudoDB;
import se.kth.salesystem.integration.DBHandler;
import se.kth.salesystem.integration.SaleDTO;

import static org.junit.Assert.*;

public class SaleTest {
    PseudoDB db;
    DBHandler integration;
    Sale sale;

    @Before
    public void setUp() {
        db = new PseudoDB();
        integration = new DBHandler(db);
        sale = new Sale(integration);
    }

    @After
    public void tearDown() {
        sale = null;
    }

    @Test
    public void addItemTest() {
        sale.addItem(1, 1);
        sale.addItem(1, 1);
        sale.addItem(3, 2);
        sale.addItem(100, 1);

        boolean testBool = sale.getTotalCost() == 97.8;
        assertEquals("Add item does not correctly increase cost", true, testBool);
    }

    @Test
    public void createSaleDTOTest() {
        sale.addItem(1, 1);
        sale.addItem(1, 1);
        sale.addItem(3, 2);
        sale.addItem(100, 1);

        SaleDTO dto = sale.createSaleDTO();
        boolean testBool = dto.getTotalCost() == 97.8;

        assertEquals("The saleDTO is created incorrectly", true, testBool);
    }

    @Test
    public void finalizeTest() {
        sale.addItem(1, 1);
        sale.addItem(1, 1);
        sale.addItem(3, 2);
        sale.addItem(100, 1);

        sale.finalize(100);

        boolean testBool = (sale.getTotalPaid() == 100 && sale.getChange() == (100 - 97.8));

        assertEquals(
                "Finalize records incorrect amount paid or calculates change incorrectly" + sale.getChange() + "test",
                true, testBool);
    }
}

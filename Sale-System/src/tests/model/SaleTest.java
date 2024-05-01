package tests.model;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import sut.database.PseudoDB;
import sut.integration.Integration;
import sut.integration.SaleDTO;
import sut.model.Sale;

import static org.junit.Assert.*;


public class SaleTest {
    PseudoDB db;
    Integration integration;
    Sale sale;

    @Before
    public void setUp(){
        db = new PseudoDB();
        integration = new Integration(db);
        sale = new Sale(integration);
    }

    @After
    public void tearDown(){
        sale = null;
    }

    @Test
    public void addItemTest(){
        sale.addItem(1, 1);
        sale.addItem(3, 2);
        
        boolean testBool = sale.getTotalCost() == 71.3;
        assertEquals("Add item does not correctly increase cost", true, testBool);
    }
    
    @Test
    public void createSaleDTOTest(){
        sale.addItem(1, 1);
        sale.addItem(3, 2);

        SaleDTO dto = sale.createSaleDTO();
        boolean testBool = dto.getTotalCost() == 71.3;
        
        assertEquals("The saleDTO is created incorrectly", true, testBool);
    }

    @Test
    public void finalizeTest(){
        sale.addItem(1, 1);
        sale.addItem(3, 2);

        sale.finalize(80);

        boolean testBool = (sale.getTotalPaid() == 80 && sale.getChange() == (80 - 71.3));

        assertEquals("Finalize records incorrect amount paid or calculates change incorrectly" + sale.getChange() + "test", true, testBool);
    }
}

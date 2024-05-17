package se.kth.salesystem.model;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import se.kth.salesystem.database.PseudoDB;
import se.kth.salesystem.integration.DBHandler;
import se.kth.salesystem.integration.DatabaseNotFoundException;
import se.kth.salesystem.integration.ItemDTO;
import se.kth.salesystem.integration.ItemNotFoundException;

import static org.junit.Assert.*;

public class ItemTest {
    
    PseudoDB db;
    DBHandler dbHandler;
    Item item;

    @Before
    public void setUp() throws ItemNotFoundException, DatabaseNotFoundException{
        db = PseudoDB.getInstance();
        dbHandler = new DBHandler(db);
        item = new Item(dbHandler.fetchItem(0), 1);
    }

    @After
    public void tearDown(){
        item = null;
    }

    @Test
    public void createItemDTOTest(){
        ItemDTO testItemDTO = item.createItemDTO();

        boolean testBool = (testItemDTO.getId() == item.getId() && testItemDTO.getName() == item.getName() && testItemDTO.getPrice() == item.getPrice() && testItemDTO.getQuantity() == item.getQuantity() && testItemDTO.getVatRate() == item.getVatRate() && testItemDTO.getDescription() == item.getDescription() && testItemDTO.getDescription() == item.getDescription());

        assertEquals("Creating the item DTO failed or the DTO contains incorrect information", true, testBool);
    }
    
    @Test
    public void addQuantity(){
        item.addQuantity(1);

        boolean testBool = item.getQuantity() == 2;

        assertEquals("Incorrectly incremented item quantity", true, testBool);
    }

}
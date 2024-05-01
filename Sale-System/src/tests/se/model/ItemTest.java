package se.model;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import se.database.PseudoDB;
import se.integration.Integration;
import se.integration.ItemDTO;

import static org.junit.Assert.*;

public class ItemTest {
    
    PseudoDB db;
    Integration integration;
    Item item;

    @Before
    public void setUp(){
        db = new PseudoDB();
        integration = new Integration(db);
        item = new Item(integration.fetchItem(0), 1);
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

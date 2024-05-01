package tests.model;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import sut.database.PseudoDB;
import sut.integration.Integration;
import sut.model.Item;

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
    }
    
    @Test
    public void addQuantity(){
        item.addQuantity(1);

        boolean testBool = item.getQuantity() == 2;

        assertEquals("Incorrectly incremented item quantity", true, testBool);
    }

}

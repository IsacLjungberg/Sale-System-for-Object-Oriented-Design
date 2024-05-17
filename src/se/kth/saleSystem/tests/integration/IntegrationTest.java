package se.kth.salesystem.integration;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import se.kth.salesystem.database.PseudoDB;

import static org.junit.Assert.*;

public class IntegrationTest {

    PseudoDB db;
    Integration integration;

    @Before
    public void setUp(){
        db = new PseudoDB();
        integration = new Integration(db);
    }

    @After
    public void tearDown(){
        db = null;
        integration = null;
    }

    @Test
    public void fetchItemTest(){
        ItemDTO dto = integration.fetchItem(0);
        boolean testBool = dto.getName().equals("Mjölk") && dto.getDescription().equals("1 liter mjölk") && dto.getPrice() == 10 && dto.getVatRate() == 6 && dto.getQuantity() == 10;
        assertEquals("Item fetched does not have the correct data", true, testBool);
    }

    @Test
    public void registerSaleTest(){
        ItemDTO itemDTO = db.fetchItem(0);
        ItemDTO[] itemDTOArr = new ItemDTO[]{itemDTO};
        SaleDTO saleDTO = new SaleDTO("Test Date", 1, 1, 1, 0, itemDTOArr);

        integration.registerSale(saleDTO);
        
        boolean testBool = db.getSales().contains(saleDTO);

        assertEquals("Sale DTO not properly registered as it is not contained in the database", true, testBool);
    }
    
    @Test
    public void fetchSaleTest(){
        ItemDTO itemDTO = db.fetchItem(0);
        ItemDTO[] itemDTOArr = new ItemDTO[]{itemDTO};
        SaleDTO saleDTO = new SaleDTO("Test Date", 1, 1, 1, 0, itemDTOArr);

        db.getSales().add(saleDTO);
        
        SaleDTO fetchedSale = integration.fetchSale(0);
        
        boolean testBool = fetchedSale.getTotalCost() == saleDTO.getTotalCost() && fetchedSale.getTotalPaid() == saleDTO.getTotalPaid();

        assertEquals("Sale DTO in the database is is not properly fetched", true, testBool);
    }

    @Test
    public void fetchLatestSaleTest(){
        ItemDTO itemDTO = db.fetchItem(0);
        ItemDTO[] itemDTOArr = new ItemDTO[]{itemDTO};
        SaleDTO saleDTOOld = new SaleDTO("Test Date", 1, 1, 1, 0, itemDTOArr);
        SaleDTO saleDTONew = new SaleDTO("Test Date", 2, 2, 2, 0, itemDTOArr);

        integration.registerSale(saleDTOOld);
        integration.registerSale(saleDTONew);
        
        boolean testBool = integration.fetchLatestSale() == saleDTONew;

        assertEquals("Fetched sale is not the latest sale or fetching failed", true, testBool);

    }


}

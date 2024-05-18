package se.kth.salesystem.integration;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import se.kth.salesystem.database.PseudoDB;
import se.kth.salesystem.model.TotalRevenueFileOutput;

import static org.junit.Assert.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DBHandlerTest {

    PseudoDB db;
    DBHandler dbHandler;

    @Before
    public void setUp() {
        db = PseudoDB.getInstance();
        dbHandler = new DBHandler(db);
        db.resetDatabase();
    }

    @After
    public void tearDown() {
        db = null;
        dbHandler = null;
    }

    @Test
    public void fetchItemTest() throws ItemNotFoundException, DatabaseNotFoundException {
        ItemDTO dto = dbHandler.fetchItem(0);
        boolean testBool = dto.getName().equals("Mjölk") && dto.getDescription().equals("1 liter mjölk")
                && dto.getPrice() == 10 && dto.getVatRate() == 6 && dto.getQuantity() == 10;
        assertEquals("Item fetched does not have the correct data", true, testBool);
    }

    @Test
    public void registerSaleTest() {
        ItemDTO itemDTO = db.fetchItem(0);
        ItemDTO[] itemDTOArr = new ItemDTO[] { itemDTO };
        SaleDTO saleDTO = new SaleDTO("Test Date", 1, 1, 1, 0, itemDTOArr);

        dbHandler.registerSale(saleDTO);

        boolean testBool = db.getSales().contains(saleDTO);

        assertEquals("Sale DTO not properly registered as it is not contained in the database", true, testBool);
    }

    @Test
    public void fetchSaleTest() {
        ItemDTO itemDTO = db.fetchItem(0);
        ItemDTO[] itemDTOArr = new ItemDTO[] { itemDTO };
        SaleDTO saleDTO = new SaleDTO("Test Date", 1, 1, 1, 0, itemDTOArr);

        db.getSales().add(saleDTO);

        SaleDTO fetchedSale = dbHandler.fetchSale(0);

        boolean testBool = fetchedSale.getTotalCost() == saleDTO.getTotalCost()
                && fetchedSale.getTotalPaid() == saleDTO.getTotalPaid();

        assertEquals("Sale DTO in the database is is not properly fetched", true, testBool);
    }

    @Test
    public void fetchLatestSaleTest() {
        ItemDTO itemDTO = db.fetchItem(0);
        ItemDTO[] itemDTOArr = new ItemDTO[] { itemDTO };
        SaleDTO saleDTOOld = new SaleDTO("Test Date", 1, 1, 1, 0, itemDTOArr);
        SaleDTO saleDTONew = new SaleDTO("Test Date", 2, 2, 2, 0, itemDTOArr);

        dbHandler.registerSale(saleDTOOld);
        dbHandler.registerSale(saleDTONew);

        boolean testBool = dbHandler.fetchLatestSale() == saleDTONew;

        assertEquals("Fetched sale is not the latest sale or fetching failed", true, testBool);

    }

    @Test
    public void addObserverTest() {
        TotalRevenueFileOutput obs = TotalRevenueFileOutput.getInstance();
        dbHandler.addObserver(obs);
        
        ItemDTO itemDTO = db.fetchItem(0);
        ItemDTO[] itemDTOArr = new ItemDTO[] { itemDTO };
        SaleDTO saleDTO = new SaleDTO("Test Date", 1, 1, 1, 0, itemDTOArr);
        dbHandler.registerSale(saleDTO);

        boolean testBool = false;
        String line = "";
        try (BufferedReader br = new BufferedReader(new FileReader("Revenue_log.txt"))) {
            //String line;
            line = br.readLine();
            line = line.substring(line.indexOf("]") + 2);
            System.out.println(line);
            if (line.equals("Total revenue: 1.0")) {
                testBool = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertEquals("Observe does not write to file correctly", true, testBool);
    }

    @Test(expected = DatabaseNotFoundException.class)
    public void DatabaseNotFoundExceptionTest() throws ItemNotFoundException, DatabaseNotFoundException {
        dbHandler.fetchItem(404);
        assertFalse("Did not throw DatabaseNotFoundException", true);
    }

    @Test(expected = ItemNotFoundException.class)
    public void itemNotFoundExceptionTest() throws ItemNotFoundException, DatabaseNotFoundException {
        dbHandler.fetchItem(68);
        assertFalse("Did not throw ItemNotFoundException", true);
    }
}

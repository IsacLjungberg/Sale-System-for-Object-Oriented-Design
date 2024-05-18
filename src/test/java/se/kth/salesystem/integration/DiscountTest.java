package se.kth.salesystem.integration;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.*;

public class DiscountTest {
    double totalCost;
    ItemDTO[] items;

    @Before
    public void setUp() {
        totalCost = 1742;
        items = new ItemDTO[3];
        items[0] = new ItemDTO("Item 0", "An Item", 0, 100, 6, 3);
        items[1] = new ItemDTO("Item 1", "An Item", 1, 200, 12, 1);
        items[2] = new ItemDTO("Item 2", "An Item", 2, 1000, 20, 1);
    }

    @After
    public void tearDown() {
        
    }

    @Test
    public void amountDiscountTest() {
        Discount discount = new AmountDiscount(0, 3);
        totalCost = discount.discount(items, totalCost);
        boolean testBool = totalCost == 1636;
        assertTrue("Price is not correct after amount discount, price after is: " + totalCost, testBool);
    }

    @Test 
    public void percentDiscountTest() {
        int[] ids = {1};
        Discount discount = new PercentDiscount(ids, 10);
        totalCost = discount.discount(items, totalCost);
        boolean testBool = totalCost == (1742 * 0.9);
        assertTrue("Price is not correct after percent discount, price after is: " + totalCost, testBool);
    }

}
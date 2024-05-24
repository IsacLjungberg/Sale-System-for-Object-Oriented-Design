package se.kth.salesystem.view;

import se.kth.salesystem.integration.ObserverTemplate;

/**
 * The TotalRevenueView class is responsible for displaying the total revenue.
 * It implements the Observer interface to receive updates on the total amount.
 */
public class TotalRevenueView extends ObserverTemplate {

    /**
     * Constructor for the TotalRevenueView class.
     * Creates an instance of TotalRevenueView.
     */
    public TotalRevenueView() {}

    @Override
    protected void doShowTotalRevenue(double totalRevenue) throws Exception {
        System.out.println("Total Revenue: " + totalRevenue);
    }

    @Override
    protected void handleErrors(Exception e) {
        System.out.println("Error " + e.getMessage());
    }
}


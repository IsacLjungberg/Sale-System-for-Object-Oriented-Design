package se.kth.salesystem.view;

import se.kth.salesystem.integration.Observer;

/**
 * The TotalRevenueView class is responsible for displaying the total revenue.
 * It implements the Observer interface to receive updates on the total amount.
 */
public class TotalRevenueView implements Observer {

    /**
     * Constructor for the TotalRevenueView class.
     * Creates an instance of TotalRevenueView.
     */
    public TotalRevenueView() {}

    /**
     * Updates the view with the new total amount.
     *
     * @param totalAmount The new total amount to be displayed.
     */
    @Override
    public void update(double totalAmount) {
        System.out.printf("Total paid amount: %,.2f \n\n", totalAmount);
    }
}


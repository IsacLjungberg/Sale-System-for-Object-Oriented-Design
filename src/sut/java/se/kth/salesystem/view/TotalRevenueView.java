package se.kth.salesystem.view;

import java.io.StringWriter;
import java.io.PrintWriter;

import se.kth.salesystem.integration.ObserverTemplate;
import se.kth.salesystem.model.ExceptionFileOutput;

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
        System.out.println("Error when writing total revenue");
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        ExceptionFileOutput.getInstance().logMessage(sw.toString());
    }
}


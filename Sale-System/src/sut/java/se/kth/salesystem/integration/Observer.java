package se.kth.salesystem.integration;

/**
 * Interface for observer handling.
 */
public interface Observer {
    /**
     * Updates observers with new total amount.
     * 
     * @param totalAmount The new total of a amount
     */
    public void update(double totalAmount);
}

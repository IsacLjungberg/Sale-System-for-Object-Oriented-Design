package model;

/**
 * Exception thworn if the state of the sale blocks program functionality.
 */
public class SaleStateException extends Exception {
    public SaleStateException(String errorMessage){
        super(errorMessage);
    }
}

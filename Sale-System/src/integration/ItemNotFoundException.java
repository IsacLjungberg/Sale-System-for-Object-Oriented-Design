
package integration;

/**
 * Exception thrown to indicate a Item not being found.
 */
public class ItemNotFoundException extends Exception {
    public ItemNotFoundException(String errorMessage){
        super(errorMessage);
    }
}

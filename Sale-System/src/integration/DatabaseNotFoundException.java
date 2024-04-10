package integration;

/**
 * Exception thrown to indicate a database not being found.
 */
public class DatabaseNotFoundException extends Exception{
    public DatabaseNotFoundException(String errorMessage){
        super(errorMessage);
    }
}

package integration;

public class DatabaseNotFoundException extends Exception{
    public DatabaseNotFoundException(String errorMessage){
        super(errorMessage);
    }
}

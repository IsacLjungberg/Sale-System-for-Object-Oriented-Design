package se.kth.salesystem.startup;

import se.kth.salesystem.controller.Controller;
import se.kth.salesystem.database.PseudoDB;
import se.kth.salesystem.integration.DBHandler;
import se.kth.salesystem.integration.Printer;
import se.kth.salesystem.view.View;

/**
 * Main class contains the main method to start the program.
 */
public class Main {
    /**
     * Main method acts as startup by initalizing necessary classes.
     */
    public static void main(String[] args) throws Exception {
        DBHandler integration = new DBHandler(new PseudoDB());
        Printer printer = new Printer();
        Controller controller = new Controller(integration, printer);
        View view = new View(controller);
        view.runExampleFlows();
    }
}

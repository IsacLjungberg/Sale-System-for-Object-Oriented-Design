package se.startup;

import se.controller.Controller;
import se.database.PseudoDB;
import se.integration.Integration;
import se.integration.Printer;
import se.view.View;

/**
 * Main class contains the main method to start the program.
 */
public class Main {
    /**
     * Main method acts as startup by initalizing necessary classes.
     */
    public static void main(String[] args) throws Exception {
        Integration integration = new Integration(new PseudoDB());
        Printer printer = new Printer();
        Controller controller = new Controller(integration, printer);
        View view = new View(controller);
        view.runExampleFlows();
    }
}

package sut.startup;

import sut.controller.Controller;
import sut.database.PseudoDB;
import sut.integration.Integration;
import sut.integration.Printer;
import sut.view.View;

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

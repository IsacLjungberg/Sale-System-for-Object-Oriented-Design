package startup;

import static org.junit.Assert.fail;

import controller.*;
import database.*;
import integration.*;
import view.*;
import model.*;
import Tests.Tests;

/**
 * Main class contains the main method to start the program.
 */
public class Main {
    /**
     * Main method acts as startup by initalizing necessary classes.
     */
    public static void main(String[] args) {

        Integration integration = new Integration(PseudoDB.getInstance());
        Printer printer = new Printer();
        ExceptionFileOutput exceptionLogger = ExceptionFileOutput.getInstance();
        TotalRevenueFileOutput revenueLogger = TotalRevenueFileOutput.getInstance();
        Controller controller = new Controller(integration, printer, exceptionLogger);
        View view = new View(controller);
        TotalRevenueView totalRevView = new TotalRevenueView();

        integration.addObeserver(totalRevView);
        integration.addObeserver(revenueLogger);

        view.runExampleFlows();

        Tests tests = new Tests();
        tests.unit_Discounts();

    }
}

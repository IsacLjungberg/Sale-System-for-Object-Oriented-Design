package se.kth.salesystem.startup;

import se.kth.salesystem.controller.Controller;
import se.kth.salesystem.database.PseudoDB;
import se.kth.salesystem.integration.DBHandler;
import se.kth.salesystem.integration.Printer;
import se.kth.salesystem.model.ExceptionFileOutput;
import se.kth.salesystem.model.TotalRevenueFileOutput;
import se.kth.salesystem.view.TotalRevenueView;
import se.kth.salesystem.view.View;



/**
 * Main class contains the main method to start the program.
 */
public class Main {
    /**
     * Main method acts as startup by initalizing necessary classes.
     */
    public static void main(String[] args) {

        DBHandler dbHandler = new DBHandler(PseudoDB.getInstance());
        Printer printer = new Printer();
        ExceptionFileOutput exceptionLogger = ExceptionFileOutput.getInstance();
        TotalRevenueFileOutput revenueLogger = TotalRevenueFileOutput.getInstance();
        Controller controller = new Controller(dbHandler, printer, exceptionLogger);
        View view = new View(controller);
        TotalRevenueView totalRevView = new TotalRevenueView();

        dbHandler.addObeserver(totalRevView);
        dbHandler.addObeserver(revenueLogger);

        view.runExampleFlows();
    }
}

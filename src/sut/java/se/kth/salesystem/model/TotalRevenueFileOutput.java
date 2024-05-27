package se.kth.salesystem.model;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import se.kth.salesystem.integration.Observer;


/**
 * The TotalRevenueFileOutput class is responsible for logging the total revenue to a file.
 * It implements the Observer interface to receive updates on the total revenue.
 */
public class TotalRevenueFileOutput implements Observer {
    private static TotalRevenueFileOutput instance = null;

    private static final String LOG_FILE = "Revenue_log.txt";
    private PrintWriter writer;

    /**
     * Constructor for the TotalRevenueFileOutput class.
     * Initializes the PrintWriter object to write to the log file.
     */
    private TotalRevenueFileOutput() {
        try {
            writer = new PrintWriter(new FileWriter(LOG_FILE), true);
        } catch (IOException e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            ExceptionFileOutput.getInstance().logMessage(sw.toString());
        }
    }

    /**
     * Returns the singleton instance of TotalRevenueFileOutput.
     * If the instance is null, a new instance is created.
     *
     * @return The singleton instance of TotalRevenueFileOutput.
     */
    public static TotalRevenueFileOutput getInstance(){
        if (instance == null) {
            instance = new TotalRevenueFileOutput();
        }

        return instance;
    }

    /**
     * Logs a message to the log file with a timestamp.
     *
     * @param message The message to be logged.
     */
    public void logMessage(String message) {
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        writer.println("[" + timeStamp + "] " + message);
    }

    /**
     * Updates the log with the new total revenue.
     *
     * @param totalAmount The new total revenue to be logged.
     */
    @Override
    public void update(double totalAmount) {
        logMessage("Total revenue: " + Double.toString(totalAmount));
    }
}


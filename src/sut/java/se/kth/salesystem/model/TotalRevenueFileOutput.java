package se.kth.salesystem.model;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import se.kth.salesystem.integration.ObserverTemplate;


/**
 * The TotalRevenueFileOutput class is responsible for logging the total revenue to a file.
 * It implements the Observer interface to receive updates on the total revenue.
 */
public class TotalRevenueFileOutput extends ObserverTemplate {
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
            handleErrors(e);
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

	protected void doShowTotalRevenue (double totalRevenue) throws Exception {
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        writer.println("[" + timeStamp + "]" + "Total revenue: " + Double.toString(totalRevenue));
	}

	protected void handleErrors(Exception e) {
		StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        ExceptionFileOutput.getInstance().logMessage(sw.toString());
	}
}


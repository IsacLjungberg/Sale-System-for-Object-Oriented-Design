package model;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The ExceptionFileOutput class is responsible for logging exceptions to a text file.
 */
public class ExceptionFileOutput {
    private static ExceptionFileOutput instance = null;

    private static final String LOG_FILE = "Exception_log.txt";
    private PrintWriter writer;

    /**
     * Constructor for the ExceptionFileOutput class.
     * Initializes the PrintWriter object to write to the log file.
     */
    private ExceptionFileOutput() {
        try {
            writer = new PrintWriter(new FileWriter(LOG_FILE), true);
        } catch (IOException e) {
            System.out.println("Exception logger not working");
            e.printStackTrace();
        }
    }

    /**
     * Returns the singleton instance of ExceptionFileOutput.
     * If the instance is null, a new instance is created.
     *
     * @return The singleton instance of ExceptionFileOutput.
     */
    public static ExceptionFileOutput getInstance(){
        if (instance == null) {
            instance = new ExceptionFileOutput();
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
        writer.flush();
    }
}



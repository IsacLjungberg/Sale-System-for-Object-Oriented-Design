package model;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExceptionFileOutput {

    private static final String LOG_FILE = "Exception_log.txt";
    private PrintWriter writer;

    public ExceptionFileOutput() {
        try {
            writer = new PrintWriter(new FileWriter(LOG_FILE), true);
        } catch (IOException e) {
            System.out.println("Logger not working");
            e.printStackTrace();
        }
    }

    public void logMessage(String message) {
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        writer.println("[" + timeStamp + "] " + message);
    }
}


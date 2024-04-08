package model;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {

    private static final String LOG_FILE = "log.txt";
    private static PrintWriter writer;

    // Constructor to initialize the PrintWriter
    public Logger() {
        try {
            writer = new PrintWriter(new FileWriter(LOG_FILE, true));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void log(String message) {
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        writer.println("[" + timeStamp + "] " + message);
    }
}


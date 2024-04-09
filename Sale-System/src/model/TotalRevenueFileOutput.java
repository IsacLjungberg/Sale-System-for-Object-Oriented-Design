package model;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import integration.Observer;

public class TotalRevenueFileOutput implements Observer{

    private static final String LOG_FILE = "Revenue_log.txt";
    private PrintWriter writer;

    public TotalRevenueFileOutput() {
        try {
            writer = new PrintWriter(new FileWriter(LOG_FILE), true);
        } catch (IOException e) {
            System.out.println("Revenue logger not working");
            e.printStackTrace();
        }
    }

    public void logMessage(String message) {
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        writer.println("[" + timeStamp + "] " + message);
    }

    @Override
    public void update(double totalAmount) {
        logMessage("Total revenue: "+Double.toString(totalAmount));
    }
}

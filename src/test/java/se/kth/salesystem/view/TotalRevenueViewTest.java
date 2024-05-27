package se.kth.salesystem.view;

import org.junit.Before;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.*;

public class TotalRevenueViewTest {
    ByteArrayOutputStream outputToStream;
    PrintStream origOut = System.out;
    TotalRevenueView viewObserver;
    
    @Before
    public void setUp(){  
        outputToStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputToStream));  
        viewObserver = new TotalRevenueView();
    }

    @After
    public void tearDown(){
        System.setOut(origOut);
        outputToStream = null;
    }

    @Test
    public void totalRevenueViewTest() {
        viewObserver.update(100);

        String testOutput = outputToStream.toString();
        Boolean testBool = testOutput.contains("100,00");

        assertTrue("Output when writing to view is incorrect, output is: " + testOutput, testBool);
    }
}
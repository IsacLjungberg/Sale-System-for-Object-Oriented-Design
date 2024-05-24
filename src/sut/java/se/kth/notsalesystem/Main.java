package se.kth.notsalesystem;

import java.util.Random;

/**
 * Main class contains the main method to start the program.
 */
public class Main {
    public static void main(String[] args) {
        CompositionDiceRandom rand = new CompositionDiceRandom();
        for(int n = 0; n < 100; n++){
            System.out.println(rand.rollDice("2d6"));
        }
    }
}

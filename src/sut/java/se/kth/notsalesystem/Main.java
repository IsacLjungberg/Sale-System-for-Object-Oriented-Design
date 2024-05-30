package se.kth.notsalesystem;

/**
 * Main class contains the main method to start the program.
 */
public class Main {
    /* 
    public static void main(String[] args) {
        CompositionDiceRandom rand = new CompositionDiceRandom();
        for(int n = 0; n < 100; n++){
            System.out.println(rand.rollDice("2d6"));
        }
    }
    */

    public static void main(String[] args) {
        CompositionDiceRandom compRand = new CompositionDiceRandom();
        InheritanceDiceRandom inheriRand = new InheritanceDiceRandom();

        int[] compRandResult = new int[13];
        int[] inheriRandResult = new int[13];
        int numberOfRolls = 1000000;

        for(int n = 0; n < numberOfRolls; n++){
            compRandResult[compRand.rollDice("2d6")] += 1;
            inheriRandResult[inheriRand.rollDice("2d6")] += 1;
        }
        System.out.println("Composition Implementation: ");
        printDistribution(numberOfRolls, compRandResult);
        System.out.println("\nInheritance Implementation: ");
        printDistribution(numberOfRolls, inheriRandResult);
    }

    static private void printDistribution(int count, int[] array){
        for(int n = 0; n < array.length; n++){
            System.out.println(n + ": " + array[n] + " (" + (double) array[n] / count * 100 + ")");
        }
    }
}

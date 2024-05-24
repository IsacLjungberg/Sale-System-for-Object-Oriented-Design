package se.kth.notsalesystem;

import java.util.Random;

public class CompositionDiceRandom {
    Random rand;

    public CompositionDiceRandom(){
        rand = new Random();
    }

    public int rollDice(String diceString){
        String[] diceArray = diceString.split("d");
        int numberOfDice = Integer.parseInt(diceArray[0]);
        int valueOfDice = Integer.parseInt(diceArray[1]);

        int out = 0;
        for(int n = 0; n < numberOfDice; n++){
            out += nextInt(valueOfDice) + 1;
        }

        return out;
    }

    public int nextInt(int cap){
        return rand.nextInt(cap);
    }
}

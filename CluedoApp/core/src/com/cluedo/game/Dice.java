package com.cluedo.game;
import java.util.Random;


public class Dice {

    int sum=0;
    int numberDice=1;
    int sideDice=6;
    Random random=new Random();

    public Dice(int num, int sideNum) {
        this.numberDice = num;
        this.sideDice = sideNum;
    }

    public int randomDiceValue(){
        /*
         nextInt method to get a random int value between 0 and specified value
         drawn from random number generator
        */

        return random.nextInt(sideDice)+1;
    }

    public int roll(){

        for (int i = 0; i < numberDice; i++) {

            sum += randomDiceValue();
        }
        return sum;
    }


}

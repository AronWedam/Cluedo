package com.cluedo.game;

import java.util.List;
import java.util.Random;

public class CardHandOut {
    
    public int handOutSuspect(){
        List<Integer> list = Card.getSuspectCards();
        Random rand = new Random();
        int mySuspectCard = list.get(rand.nextInt(list.size()));
        list.remove(mySuspectCard);

        return mySuspectCard;
    }

    public int handOutWeapon(){
        List<Integer> list = Card.getWeaponCards();
        Random rand = new Random();
        int myWeaponCard = list.get(rand.nextInt(list.size()));
        list.remove(myWeaponCard);

        return myWeaponCard;
    }

    public int handOutRoom(){
        List<Integer> list = Card.getRoomCards();
        Random rand = new Random();
        int myRoomCard = list.get(rand.nextInt(list.size()));
        list.remove(myRoomCard);

        return myRoomCard;
    }

}

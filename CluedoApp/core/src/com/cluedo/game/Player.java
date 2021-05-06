package com.cluedo.game;

import java.lang.reflect.Array;

public class Player {

    private static CardHandOut cardHandOut;


    public static void myCards() {
        int mySuspectCard = cardHandOut.handOutSuspect();
        int myWeaponCard = cardHandOut.handOutWeapon();
        int myRoomCard = cardHandOut.handOutRoom();
    }






}

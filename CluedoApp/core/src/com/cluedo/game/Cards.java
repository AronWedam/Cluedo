package com.cluedo.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.smartcardio.Card;

public class Cards {

    private int type;
    private int value;

    public static final int numSuspects = 6;
    public static final int numRooms = 9;
    public static final int numWeapons = 6;
    public static final int TOTAL = numRooms + numSuspects + numWeapons;

    //for the card stack
    public static final int stackSuspect = 0;
    public static final int stackWeapon = 1;
    public static final int stackRoom = 2;

    public static boolean suspectCard = false;
    public static boolean weaponCard = false;
    public static boolean roomCard = false;

    //suspect cards
    public static final Integer suspectMissScarlett = 0;
    public static final Integer suspectColonelMustard = 1;
    public static final Integer suspectMrsWhite = 2;
    public static final Integer suspectReverend = 3;
    public static final Integer suspectMrsPeacock = 4;
    public static final Integer suspectProfessorPlum = 5;

    private static List<Integer> suspectCards = Arrays.asList(suspectColonelMustard, suspectMissScarlett, suspectMrsWhite,
            suspectReverend, suspectMrsPeacock, suspectProfessorPlum);

    //rooms in the house
    public static final Integer roomEntrance = 0;
    public static final Integer roomGarden = 1;
    public static final Integer roomDining = 2;
    public static final Integer roomKitchen = 3;
    public static final Integer roomBallroom = 4;
    public static final Integer roomMusicroom = 5;
    public static final Integer roomGameroom = 6;
    public static final Integer roomStudy = 7;
    public static final Integer roomLibrary = 8;

    private static List<Integer> roomCards = Arrays.asList(roomBallroom, roomDining, roomEntrance,
            roomGameroom, roomGarden, roomKitchen, roomLibrary, roomMusicroom, roomStudy);

    //types of weapons
    public static final int weaponKnife = 0;
    public static final int weaponRope = 1;
    public static final int weaponGun = 2;
    public static final int weaponPoison = 3;
    public static final int weaponPipe = 4;
    public static final int weaponCandle = 5;

    private static List<Integer> weaponCards = Arrays.asList(weaponCandle, weaponGun, weaponKnife,
            weaponPipe, weaponPoison, weaponRope);

    public Cards(int type, int value) {
        this.type = type;
        this.value = value;
    }

    public static List<Integer> getRoomCards() {
        return roomCards;
    }

    public static List<Integer> getSuspectCards(){
        return suspectCards;
    }

    public static List<Integer> getWeaponCards(){
        return weaponCards;
    }


    public int getType() {
        return type;
    }

    public int getValue() {
        return value;
    }


    public int hashCode() {
        return type + value;
    }


    public boolean equals(Object obj) {
        if (obj instanceof Cards) {
            Cards c = (Cards) obj;
            return (c.type == this.type && c.value == this.value);
        } else {
            return false;
        }
    }

}

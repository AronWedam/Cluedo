package com.cluedo.game;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Card {

    public enum TYPE{
        SUSPECT, ROOMS, WEAPONS, EVIDENCE
    }

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

    public static final int TYPE_SUSPECT = 0;
    public static final int TYPE_WEAPON = 1;
    public static final int TYPE_ROOM = 2;

    //suspect cards
    public static final int suspectMissScarlett = 0;
    public static final int suspectColonelMustard = 1;
    public static final int suspectMrsWhite = 2;
    public static final int suspectReverend = 3;
    public static final int suspectMrsPeacock = 4;
    public static final int suspectProfessorPlum = 5;

    private static List<Integer> suspectCards = new LinkedList<>(Arrays.asList(suspectColonelMustard, suspectMissScarlett, suspectMrsWhite,
            suspectReverend, suspectMrsPeacock, suspectProfessorPlum));

    //rooms in the house
    public static final int roomEntrance = 0;
    public static final int roomGarden = 1;
    public static final int roomDining = 2;
    public static final int roomKitchen = 3;
    public static final int roomBallroom = 4;
    public static final int roomMusicroom = 5;
    public static final int roomGameroom = 6;
    public static final int roomStudy = 7;
    public static final int roomLibrary = 8;

    private static List<Integer> roomCards = new LinkedList<>(Arrays.asList(roomBallroom, roomDining, roomEntrance,
            roomGameroom, roomGarden, roomKitchen, roomLibrary, roomMusicroom, roomStudy));

    //types of weapons
    public static final int weaponKnife = 0;
    public static final int weaponRope = 1;
    public static final int weaponGun = 2;
    public static final int weaponPoison = 3;
    public static final int weaponPipe = 4;
    public static final int weaponCandle = 5;

    private static List<Integer> weaponCards = new LinkedList<>(Arrays.asList(weaponCandle, weaponGun, weaponKnife,
            weaponPipe, weaponPoison, weaponRope));

    public Card(int type, int value) {
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
        if (obj instanceof Card) {
            Card c = (Card) obj;
            return (c.type == this.type && c.value == this.value);
        } else {
            return false;
        }
    }

}

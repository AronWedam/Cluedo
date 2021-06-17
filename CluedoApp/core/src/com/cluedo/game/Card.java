package com.cluedo.game;

import java.util.ArrayList;

public class Card {
    private int type;
    private int value;

    public static final int TYPE_SUSPECT = 0;
    public static final int TYPE_WEAPON = 1;
    public static final int TYPE_ROOM = 2;

    private static ArrayList<Card> roomsArrayList = new ArrayList<>();
    private static ArrayList<Card> weaponsArrayList = new ArrayList<>();
    private static ArrayList<Card> suspectsArrayList = new ArrayList<>();

    private static final Card suspect0 = new Card(0, 0);
    private static final Card suspect1 = new Card(0, 1);
    private static final Card suspect2 = new Card(0, 2);
    private static final Card suspect3 = new Card(0,3);
    private static final Card suspect4 = new Card(0, 4);
    private static final Card suspect5 = new Card(0,5);

    private static final Card weapon0 = new Card(1,0);
    private static final Card weapon1 = new Card(1, 1);
    private static final Card weapon2 = new Card(1,2);
    private static final Card weapon3 = new Card(1,3);
    private static final Card weapon4 = new Card(1,4);
    private static final Card weapon5 = new Card(1,5);

    private static final Card room0 = new Card(2,0);
    private static final Card room1 = new Card(2, 1);
    private static final Card room2 = new Card(2,2);
    private static final Card room3 = new Card(2, 3);
    private static final Card room4 = new Card(2,4);
    private static final Card room5 = new Card(2, 5);
    private static final Card room6 = new Card(2,6);
    private static final Card room7 = new Card(2, 7);
    private static final Card room8 = new Card(2, 8);

    public Card(int type, int value) {
        this.type = type;
        this.value = value;
    }

    public static void setSuspectsArrayList() {
        suspectsArrayList.add(suspect0);
        suspectsArrayList.add(suspect1);
        suspectsArrayList.add(suspect2);
        suspectsArrayList.add(suspect3);
        suspectsArrayList.add(suspect4);
        suspectsArrayList.add(suspect5);
    }

    public static void setWeaponsArrayList(){
        weaponsArrayList.add(weapon0);
        weaponsArrayList.add(weapon1);
        weaponsArrayList.add(weapon2);
        weaponsArrayList.add(weapon3);
        weaponsArrayList.add(weapon4);
        weaponsArrayList.add(weapon5);
    }

    public static void setRoomsArrayList() {
        roomsArrayList.add(room0);
        roomsArrayList.add(room1);
        roomsArrayList.add(room2);
        roomsArrayList.add(room3);
        roomsArrayList.add(room4);
        roomsArrayList.add(room5);
        roomsArrayList.add(room6);
        roomsArrayList.add(room7);
        roomsArrayList.add(room8);
    }

    public static ArrayList<Card> getSuspectsArrayList() {
        return suspectsArrayList;
    }

    public static ArrayList<Card> getWeaponsArrayList() {
        return weaponsArrayList;
    }

    public static ArrayList<Card> getRoomsArrayList() {
        return roomsArrayList;
    }

    public int getValue() {
        return value;
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

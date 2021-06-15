package com.cluedo.game;


import java.util.ArrayList;

public class Card {

    private int type;
    private int value;

    public static final int numSuspects = 6;
    public static final int numRooms = 9;
    public static final int numWeapons = 6;


    public static final int TYPE_SUSPECT = 0;
    public static final int TYPE_WEAPON = 1;
    public static final int TYPE_ROOM = 2;

    public static ArrayList<Card> suspectsArrayList = new ArrayList<>();
    public static ArrayList<Card> weaponsArrayList = new ArrayList<>();
    public static ArrayList<Card> roomsArrayList = new ArrayList<>();


    public Card(int type, int value) {
        this.type = type;
        this.value = value;

        initializeArrayList(numSuspects, suspectsArrayList);
        initializeArrayList(numRooms, roomsArrayList);
        initializeArrayList(numWeapons, weaponsArrayList);
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

    private void initializeArrayList(int number, ArrayList<Card> arrayList){
        for(int i = 0; i < number; i++){
            Card card = new Card(type, i);
            arrayList.add(card);
        }
    }

}

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

    public static final Card suspect0 = new Card(0, 0);
    public static final Card suspect1 = new Card(0, 1);
    public static final Card suspect2 = new Card(0, 2);
    public static final Card suspect3 = new Card(0,3);
    public static final Card suspect4 = new Card(0, 4);
    public static final Card suspect5 = new Card(0,5);

    public static final Card weapon0 = new Card(1,0);
    public static final Card weapon1 = new Card(1, 1);
    public static final Card weapon2 = new Card(1,2);
    public static final Card weapon3 = new Card(1,3);
    public static final Card weapon4 = new Card(1,4);
    public static final Card weapon5 = new Card(1,5);

    public static final Card room0 = new Card(2,0);
    public static final Card room1 = new Card(2, 1);
    public static final Card room2 = new Card(2,2);
    public static final Card room3 = new Card(2, 3);
    public static final Card room4 = new Card(2,4);
    public static final Card room5 = new Card(2, 5);
    public static final Card room6 = new Card(2,6);
    public static final Card room7 = new Card(2, 7);
    public static final Card room8 = new Card(2, 8);


    public static ArrayList<Card> suspectsArrayList = new ArrayList<Card>();

    public static void setSuspectsArrayList() {
        suspectsArrayList.add(suspect0);
        suspectsArrayList.add(suspect1);
        suspectsArrayList.add(suspect2);
        suspectsArrayList.add(suspect3);
        suspectsArrayList.add(suspect4);
        suspectsArrayList.add(suspect5);
    }

    public static ArrayList<Card> weaponsArrayList = new ArrayList<>();

    public static void setWeaponsArrayList(){
        weaponsArrayList.add(weapon0);
        weaponsArrayList.add(weapon1);
        weaponsArrayList.add(weapon2);
        weaponsArrayList.add(weapon3);
        weaponsArrayList.add(weapon4);
        weaponsArrayList.add(weapon5);
    }

    public static ArrayList<Card> roomsArrayList = new ArrayList<>();

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


    public Card(int type, int value) {
        this.type = type;
        this.value = value;
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

    public ArrayList<Card> initializeArrayList(int number){
        ArrayList<Card>  cards = new ArrayList<Card>();
        for(int i = 0; i < number; i++){
            Card card = new Card(type, i);
            cards.add(card);
        }
        return cards;
    }

}

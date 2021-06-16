package com.cluedo.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import sun.util.resources.cldr.ar.CalendarData_ar_DZ;


public class CardHandOut {

    private CardHandOut(){
        Card.setRoomsArrayList();
        Card.setSuspectsArrayList();
        Card.setWeaponsArrayList();
    };

    private static CardHandOut cardHandOut = null;

    public static CardHandOut getInstance(){
        if(cardHandOut == null){
            cardHandOut = new CardHandOut();
        }
        return cardHandOut;
    }

    int currentType = -1;
    ArrayList<Card> currArrayList;

    public Card randomlyPickCardOfType(int type) {
        Card picked_card = null;
        ArrayList<Card> picks = new ArrayList<>();

        int total = 0;
        if (type == Card.TYPE_SUSPECT) {
            total = Card.suspectsArrayList.size();
            currUsedArrayList(0);
        }
        if (type == Card.TYPE_ROOM) {
            total = Card.roomsArrayList.size();
            currUsedArrayList(1);
        }
        if (type == Card.TYPE_WEAPON) {
            total = Card.weaponsArrayList.size();
            currUsedArrayList(2);
        }


        //for (int i = 0; i < total; i++)
            //Card card = currArrayList.get(i);
            //picks.add(card);
        //}

        picks = currArrayList;

        //FOR ME BUT Still Exception
        for(int i = 0; i < total; i++){
            System.out.println(picks.get(i));
        }

        Collections.shuffle(picks);

        picked_card = picks.get(0);
        deleteUsedCard(picked_card.getValue());

        return picked_card;
    }

    private ArrayList<Card> currUsedArrayList(int i){
        if(i == 0){
            currArrayList = Card.suspectsArrayList;
        }else if(i == 1){
            currArrayList =  Card.roomsArrayList;
        }else if(i == 2) {
            currArrayList =  Card.weaponsArrayList;
        }
        return currArrayList;
    }

    private void deleteUsedCard(int i){
        int cardToRemove = Card.suspectsArrayList.indexOf(i);

        if(currentType ==  0){
            Card.suspectsArrayList.remove(cardToRemove);
        }else if(currentType == 1){
            Card.roomsArrayList.remove(cardToRemove);
        }else if(currentType == 2) {
            Card.weaponsArrayList.remove(cardToRemove);
        }
    }
}

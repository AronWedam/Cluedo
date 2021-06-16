package com.cluedo.game;

import java.util.ArrayList;
import java.util.Collections;

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
        Card picked_card;
        ArrayList<Card> picks;

        if (type == Card.TYPE_SUSPECT) {
            currUsedArrayList(0);
        }
        if (type == Card.TYPE_ROOM) {
            currUsedArrayList(1);
        }
        if (type == Card.TYPE_WEAPON) {
            currUsedArrayList(2);
        }

        picks = currArrayList;

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

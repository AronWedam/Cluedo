package com.cluedo.game;

import java.util.ArrayList;
import java.util.Collections;

public class CardHandOut {

    int currentType = -1;
    public ArrayList<Card> currArrayList;

    public CardHandOut(){
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


    public Card randomlyPickCardOfType(int type) {
        Card picked_card;
        ArrayList<Card> picks;

        if (type == Card.TYPE_SUSPECT) {
            currUsedArrayList(0);
        }
        if (type == Card.TYPE_WEAPON) {
            currUsedArrayList(1);
        }
        if (type == Card.TYPE_ROOM) {
            currUsedArrayList(2);
        }

        picks = currArrayList;

        Collections.shuffle(picks);

        picked_card = picks.get(0);
        deleteUsedCard(picked_card.getType());

        return picked_card;
    }

    private ArrayList<Card> currUsedArrayList(int i){
        if(i == 0){
            currArrayList = Card.suspectsArrayList;
        }else if(i == 1) {
            currArrayList =  Card.weaponsArrayList;
        }else if(i == 2){
            currArrayList =  Card.roomsArrayList;
        }
        return currArrayList;
    }

    private void deleteUsedCard(int i){
        if(currentType ==  0){
            Card.suspectsArrayList.remove(i);
        }else if(currentType == 1) {
            Card.weaponsArrayList.remove(i);
        }else if(currentType == 2){
            Card.roomsArrayList.remove(i);
        }
    }
}

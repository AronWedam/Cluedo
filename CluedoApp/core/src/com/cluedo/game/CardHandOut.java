package com.cluedo.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class CardHandOut {

    public Card randomlyPickCardOfType(int type) {
        Card picked_card = null;
        ArrayList<Card> picks = new ArrayList<>();

        int total = 0;
        if (type == Card.TYPE_SUSPECT) {
            total = Card.numSuspects;
        }
        if (type == Card.TYPE_ROOM) {
            total = Card.numRooms;
        }
        if (type == Card.TYPE_WEAPON) {
            total = Card.numWeapons;
        }

        for (int i = 0; i < total; i++) {
            Card card = new Card(type, i);
            picks.add(card);
        }

        Collections.shuffle(picks);

        picked_card = picks.get(0);

        return picked_card;
    }


}

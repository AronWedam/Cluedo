package com.cluedo.game;

public class Murderer {
    public Player player;


    private final Card murdererWeaponCard;
    private final Card murdererSuspectCard;
    private final Card murdererRoomCard;

    public Murderer() {
        this.player = player;

        player.setMyWeaponCard();
        player.setMySuspectCard();
        player.setMyRoomCard();

        murdererWeaponCard = player.getMyWeaponCard();
        murdererSuspectCard = player.getMySuspectCard();
        murdererRoomCard = player.getMyRoomCard();
    }

    public Card getMurdererWeaponCard() {
        return murdererWeaponCard;
    }

    public String getMurdererWeaponString(){
        return murdererWeaponCard.toString();
    }

    public Card getMurdererSuspectCard() {
        return murdererSuspectCard;
    }

    public String getMurdererSuspectString(){
        return murdererWeaponCard.toString();
    }

    public String getMurdererRoomString(){return murdererRoomCard.toString();}

    public Card getMurdererRoomCard() {
        return murdererRoomCard;
    }
}

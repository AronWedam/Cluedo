package com.cluedo.game;

public class Murderer {
    private Player player;
    private final Card murdererWeaponCard;
    private final Card murdererSuspectCard;
    private final Card murdererRoomCard;

    public Murderer() {
        player.setMyWeaponCard();
        player.setMySuspectCard();
        player.setMyRoomCard();

        murdererWeaponCard = player.getMyWeaponCard();
        murdererSuspectCard = player.getMySuspectCard();
        murdererRoomCard = player.getMyRoomCard();
    }
}

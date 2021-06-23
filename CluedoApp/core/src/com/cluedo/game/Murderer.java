package com.cluedo.game;

import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.cluedo.game.network.ConnectionService;

public class Murderer {

    public static ConnectionService connectionService;

    public static boolean isActuallyTheMurderer(CheckBox cbAccusedWeapon,
                                                CheckBox cBAccusedSuspect,
                                         CheckBox cBAccusedRoom){
        connectionService = ConnectionService.GetInstance();

        String weapon = cbAccusedWeapon.toString().split(" ")[1];
        String person = cBAccusedSuspect.toString().split(" ")[1];
        String room = cBAccusedRoom.toString().split(" ")[1];

        return checkIfCorrectMurderer(weapon, person, room);
    }

    public static boolean checkIfCorrectMurderer(String weapon, String suspect, String room){
        connectionService = ConnectionService.GetInstance();

        if(weapon.equals(connectionService.getWeapon())){
            if(suspect.equals(connectionService.getSuspect())){
                return room.equals(connectionService.getRoom());
            }
        }
        return false;
    }


}

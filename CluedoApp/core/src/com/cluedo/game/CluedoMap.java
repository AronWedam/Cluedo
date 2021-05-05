package com.cluedo.game;

import com.badlogic.gdx.math.Vector2;

public class CluedoMap {

    public Vector2[][] mapconstants = new Vector2[29][29];

    public CluedoMap(){
        setup();
    }

    public void setup() {
        int t = 0;
        int u = 0;

        for(int i = 0; i < mapconstants.length; i++) {
            for (int j = 0; j < mapconstants[i].length; j++) {
                mapconstants[i][j] = new Vector2();
            }
        }

        mapconstants[0][0].x = 0;
        mapconstants[0][0].y = 0;

        for(int i = 0; i < mapconstants.length; i++){
            for(int j = 0; j < mapconstants[i].length; j++){
                mapconstants[i][j].y = t;
                mapconstants[i][j].x = u;
                t += 32;
            }
            t = 0;
            u = u + 32;
        }

    }


}

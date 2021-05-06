package com.cluedo.game;

import com.badlogic.gdx.math.Vector2;

public class CluedoMap {

    public Vector2[][] mapconstants = new Vector2[29][29];

    public Vector2[] invalidtiles = new Vector2[200];

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

        for(int i = 0; i < invalidtiles.length; i++){
            invalidtiles[i] = new Vector2();
        }

        //left corner lower room
        invalidtiles[0] = mapconstants[8][0];
        invalidtiles[1] = mapconstants[8][1];
        invalidtiles[2] = mapconstants[8][2];
        invalidtiles[3] = mapconstants[8][3];
        invalidtiles[4] = mapconstants[8][4];
        invalidtiles[5] = mapconstants[8][5];
        invalidtiles[6] = mapconstants[0][7];
        invalidtiles[7] = mapconstants[1][7];
        invalidtiles[8] = mapconstants[2][7];
        invalidtiles[9] = mapconstants[3][7];
        invalidtiles[10] = mapconstants[4][7];
        invalidtiles[11] = mapconstants[5][7];
        invalidtiles[12] = mapconstants[6][7];

        //midlle lower room
        invalidtiles[13] = mapconstants[12][0];
        invalidtiles[14] = mapconstants[12][1];
        invalidtiles[15] = mapconstants[12][2];
        invalidtiles[16] = mapconstants[12][3];
        invalidtiles[17] = mapconstants[12][4];
        invalidtiles[18] = mapconstants[12][5];
        invalidtiles[19] = mapconstants[12][6];
        invalidtiles[20] = mapconstants[12][7];
        invalidtiles[21] = mapconstants[12][8];
        invalidtiles[22] = mapconstants[13][8];
        invalidtiles[23] = mapconstants[14][8];
        invalidtiles[25] = mapconstants[17][8];
        invalidtiles[26] = mapconstants[18][8];
        invalidtiles[27] = mapconstants[19][8];
        invalidtiles[28] = mapconstants[19][7];
        invalidtiles[29] = mapconstants[19][6];
        invalidtiles[30] = mapconstants[19][4];
        invalidtiles[31] = mapconstants[19][3];
        invalidtiles[32] = mapconstants[19][2];
        invalidtiles[33] = mapconstants[19][1];
        invalidtiles[34] = mapconstants[19][0];
        //right corner lower room
        invalidtiles[35] = mapconstants[22][0];
        invalidtiles[36] = mapconstants[22][1];
        invalidtiles[37] = mapconstants[22][2];
        invalidtiles[38] = mapconstants[22][3];
        invalidtiles[39] = mapconstants[22][4];
        invalidtiles[40] = mapconstants[22][5];
        invalidtiles[41] = mapconstants[24][5];
        invalidtiles[42] = mapconstants[25][5];
        invalidtiles[43] = mapconstants[26][5];
        invalidtiles[44] = mapconstants[27][5];
        invalidtiles[45] = mapconstants[28][5];


        //TODO go on with other rooms

    }




}

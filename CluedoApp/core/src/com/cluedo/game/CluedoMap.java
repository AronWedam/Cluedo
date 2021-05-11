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
        //right middle lower room
        invalidtiles[46] = mapconstants[28][8];
        invalidtiles[47] = mapconstants[27][8];
        invalidtiles[48] = mapconstants[26][8];
        invalidtiles[49] = mapconstants[25][8];
        invalidtiles[50] = mapconstants[24][8];
        invalidtiles[51] = mapconstants[24][9];
        invalidtiles[52] = mapconstants[23][9];
        invalidtiles[53] = mapconstants[23][10];
        invalidtiles[54] = mapconstants[22][10];
        invalidtiles[55] = mapconstants[22][12];
        invalidtiles[56] = mapconstants[23][12];
        invalidtiles[57] = mapconstants[23][13];
        invalidtiles[58] = mapconstants[24][13];
        invalidtiles[59] = mapconstants[25][13];
        invalidtiles[60] = mapconstants[26][13];
        invalidtiles[61] = mapconstants[27][13];
        invalidtiles[62] = mapconstants[28][13];
        //right middle upper room
        invalidtiles[63] = mapconstants[28][15];
        invalidtiles[64] = mapconstants[27][15];
        invalidtiles[65] = mapconstants[25][15];
        invalidtiles[66] = mapconstants[24][15];
        invalidtiles[67] = mapconstants[23][15];
        invalidtiles[68] = mapconstants[22][15];
        invalidtiles[69] = mapconstants[22][16];
        invalidtiles[70] = mapconstants[22][18];
        invalidtiles[71] = mapconstants[22][19];
        invalidtiles[72] = mapconstants[23][19];
        invalidtiles[73] = mapconstants[24][19];
        invalidtiles[74] = mapconstants[25][19];
        invalidtiles[75] = mapconstants[26][19];
        invalidtiles[76] = mapconstants[27][19];
        invalidtiles[77] = mapconstants[28][19];
        //right corner upper room
        invalidtiles[78] = mapconstants[28][22];
        invalidtiles[79] = mapconstants[27][22];
        invalidtiles[80] = mapconstants[26][22];
        invalidtiles[81] = mapconstants[25][22];
        invalidtiles[82] = mapconstants[24][22];
        invalidtiles[83] = mapconstants[23][24];
        invalidtiles[84] = mapconstants[22][24];
        invalidtiles[85] = mapconstants[22][25];
        invalidtiles[86] = mapconstants[22][26];
        invalidtiles[87] = mapconstants[22][27];
        invalidtiles[88] = mapconstants[22][28];
        //middle upper room
        invalidtiles[89] = mapconstants[19][28];
        invalidtiles[90] = mapconstants[19][27];
        invalidtiles[91] = mapconstants[19][26];
        invalidtiles[92] = mapconstants[19][25];
        invalidtiles[93] = mapconstants[18][25];
        invalidtiles[94] = mapconstants[18][24];
        invalidtiles[95] = mapconstants[18][23];
        invalidtiles[96] = mapconstants[18][22];
        invalidtiles[97] = mapconstants[18][21];
        invalidtiles[98] = mapconstants[17][21];
        invalidtiles[99] = mapconstants[12][21];
        invalidtiles[100] = mapconstants[11][21];
        invalidtiles[101] = mapconstants[11][22];
        invalidtiles[102] = mapconstants[11][23];
        invalidtiles[103] = mapconstants[11][24];
        invalidtiles[104] = mapconstants[11][25];
        invalidtiles[105] = mapconstants[10][25];
        invalidtiles[106] = mapconstants[10][26];
        invalidtiles[107] = mapconstants[10][27];
        invalidtiles[108] = mapconstants[10][28];
        //left corner upper room
        invalidtiles[109] = mapconstants[7][28];
        invalidtiles[110] = mapconstants[7][27];
        invalidtiles[111] = mapconstants[7][26];
        invalidtiles[112] = mapconstants[7][25];
        invalidtiles[113] = mapconstants[7][24];
        invalidtiles[114] = mapconstants[6][22];
        invalidtiles[115] = mapconstants[5][22];
        invalidtiles[116] = mapconstants[4][22];
        invalidtiles[117] = mapconstants[3][22];
        invalidtiles[118] = mapconstants[2][22];
        invalidtiles[119] = mapconstants[1][22];
        invalidtiles[120] = mapconstants[0][22];
        //left middle room
        invalidtiles[121] = mapconstants[0][19];
        invalidtiles[122] = mapconstants[1][19];
        invalidtiles[123] = mapconstants[2][19];
        invalidtiles[124] = mapconstants[3][19];
        invalidtiles[125] = mapconstants[4][19];
        invalidtiles[126] = mapconstants[4][18];
        invalidtiles[127] = mapconstants[5][18];
        invalidtiles[128] = mapconstants[6][18];
        invalidtiles[129] = mapconstants[7][18];
        invalidtiles[130] = mapconstants[8][18];
        invalidtiles[131] = mapconstants[8][17];
        invalidtiles[132] = mapconstants[8][15];
        invalidtiles[133] = mapconstants[8][14];
        invalidtiles[134] = mapconstants[8][13];
        invalidtiles[135] = mapconstants[7][13];
        invalidtiles[136] = mapconstants[5][13];
        invalidtiles[137] = mapconstants[4][13];
        invalidtiles[138] = mapconstants[4][12];
        invalidtiles[139] = mapconstants[3][12];
        invalidtiles[140] = mapconstants[2][12];
        invalidtiles[141] = mapconstants[1][12];
        invalidtiles[142] = mapconstants[0][12];
    }




}
